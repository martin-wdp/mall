package com.ningpai.site.order.controller;

import com.lianft.ups.common.sdk.ServiceProperties;
import com.lianft.ups.common.sdk.exception.XMLException;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.common.util.alipay.config.AlipayConfig;
import com.ningpai.common.util.alipay.util.AlipayNotify;

import com.ningpai.common.util.alipay.util.UtilDate;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.order.bean.Order;
import com.ningpai.order.bean.OrderGoods;
import com.ningpai.order.dao.OrderGoodsMapper;
import com.ningpai.order.service.OrderService;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.vo.CustomerAllInfo;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.site.order.bean.WxPayDto;
import com.ningpai.site.order.bean.WxPayResult;
import com.ningpai.site.order.common.GetWxOrderno;
import com.ningpai.site.order.service.IPayService;
import com.ningpai.site.order.service.SiteOrderService;
import com.ningpai.site.order.util.RequestHandler;
import com.ningpai.site.util.MD5Util;
import com.ningpai.site.util.PayUtil;
import com.ningpai.system.bean.Pay;
import com.ningpai.system.bean.Receivables;
import com.ningpai.system.service.PayService;
import com.ningpai.system.service.PaymentService;
import com.ningpai.system.service.ReceivablesService;
import com.ningpai.util.MyLogger;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.unionpay.mpi.LogUtil;
import com.unionpay.mpi.MpiConfig;
import com.unionpay.mpi.MpiUtil;
import org.apache.xml.security.signature.XMLSignatureException;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.CacheRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.lianft.ups.common.sdk.LianftService;
import com.lianft.ups.common.sdk.entity.Merchant;
import com.lianft.ups.common.sdk.entity.Request;
import com.lianft.ups.common.sdk.entity.RequestGroup;
import com.lianft.ups.common.sdk.entity.TxInfo;
import com.lianft.ups.common.sdk.entity.UPS;
import org.springframework.web.servlet.view.RedirectView;

import static com.ningpai.site.order.common.WXPayHelp.getMoney;
import static com.ningpai.site.order.common.WXPayHelp.getNonceStr;
import static com.ningpai.site.order.common.WXPayHelp.parseXmlToList2;
import static java.math.BigDecimal.*;

import java.io.BufferedOutputStream;

/**
 * 支付Controller
 *
 * @author lih
 * @version 2.0
 * @since 2015/8/11 16:21
 */
@Controller
public class PaySiteController {

    /**
     * 单比订单或者多笔订单生成不同的收款单信息 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(PaySiteController.class);

    private static final String LOGGERINFO1 = "支付请求失败";
    private static final String UTF_8 = "UTF-8";
    private static final String ISO_8859_1 = "ISO-8859-1";
    private static final String OUT_TRADE_NO = "out_trade_no";
    private static final String TRADE_STATUS = "trade_status";
    private static final String ORDER_PAYSUCCESS = "order/paysuccess";
    private static final String ORDER_PAYBYWX = "order/payorderbywx";
    private static final String ORDER = "order";
    private static final String PRICE = "price";
    private static final String SUCCESS = "success";
    private static final String UNKNOWN = "unknown";

    @Resource(name = "receivablesService")
    private ReceivablesService receivablesService;

    @Resource(name = "payService")
    private PayService payService;

    @Resource(name = "PaymentService")
    PaymentService paymentService;

    @Resource(name = "SiteOrderService")
    private SiteOrderService siteOrderService;

    @Resource(name = "OrderService")
    private OrderService orderser;

    @Resource(name = "customerServiceSite")
    private CustomerServiceInterface customerServiceInterface;

    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    @Resource(name = "IPayService")
    private IPayService iPayService;

    @Resource(name = "OrderGoodsMapper")
    private OrderGoodsMapper orderGoodsMapper;

    @Resource(name = "GoodsProductService")
    private GoodsProductService goodsProductService;

    /**
     * 根据支付的订单的数量 来设置订单的属性
     *
     * @param order
     *            订单对象
     * @return
     */
    public void getReceivables(Pay p, HttpServletRequest request, Order order) {

        /* 根据订单号去查询是否有记录 */
        Receivables receivables1 = this.receivablesService.queryByOrderCode(order.getOrderCode());
        if (null == receivables1) {
            /* 添加收款单信息 */
            Receivables receivables = new Receivables();
            Random random = new Random();
            int code = random.nextInt(10001);
            String codes = "2015" + code;
            // 收款单编号 产生的随机数
            receivables.setCashRegisterCode(codes);
            // 收款单IP
            receivables.setPayIp(getIpAddr(request));
            // 会员ID
            /*receivables.setCustomerId(order.getCustomerUsername());*/
            receivables.setCustomerId(order.getCustomerUsername());//order.getCustomerUsername()为空
            // 收款账号
            receivables.setPayAccount(p.getPayAccount());
            // 给收款单信息设置IP地址
            // receivables.setPayIp(InetAddress.getLocalHost().getHostAddress());
            // 设置生成时间
            receivables.setPayTime(new Date());
            // 设置付款时间
            receivables.setReceivablesTime(new Date());
            // 给收款单信息设置支付方式
            receivables.setPayMode(p.getPayName());
            // 设置支付类型
            receivables.setPayType("在线支付");
            // 给收款单信息设置付款金额
            receivables.setPayMoney(order.getOrderPrice());
            receivables.setOrderCode(order.getOrderCode());
            // 给付款单信息设置是否支付成功
            receivables.setPayStatus("1");
            // 执行为收款单信息添加信息操作
            this.receivablesService.addReceivables(receivables);
        }
    }

    /**
     * 确认付款
     *
     * @param orderCount
     *            当前支付的订单数量
     * @param orderId
     *            需要支付的订单Id
     * @param request
     * @param payId
     * @param response
     * @throws UnsupportedEncodingException
     * @throws UnknownHostException
     */
    @RequestMapping("/payorder")
    public void payOrder(Long orderCount, Long orderId, HttpServletRequest request, Long payId, HttpServletResponse response) throws UnsupportedEncodingException{
        // 获取用户id
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 判断用户id是否为空
        if (customerId == null) {
            return;
        }
        // 返回订单商品的商品名称，取第一个
        String goodsName = orderser.queryGoodsInfoName(orderId);
        // 获取订单信息
        Order order = siteOrderService.getPayOrder(orderId);

        // 查询使用的支付信息
        Pay p = payService.findByPayId(payId);
        if (p != null) {
            // 多笔订单 循环生成多笔付款单信息
            if (null != orderCount) {
                // 根据主订单号查询所有的子订单信息
                List<Order> orderslist = siteOrderService.getPayOrderByOldCode(order.getOrderOldCode());
                for (int i = 0; i < orderslist.size(); i++) {
                    // 创建单个订单对象
                    Order childOrder = orderslist.get(i);
                    // 如果是在线支付
                    if ("1".equals(childOrder.getOrderLinePay())) {
                        // 单笔订单
                        this.getReceivables(p, request, childOrder);
                    }
                }
            } else {
                // 单笔订单 生成单笔的付款单信息
                this.getReceivables(p, request, order);
            }

            // 支付宝支付
            if ("1".equals(p.getPayType())) {
                // 获取支付用的信息
                String sHtmlText = iPayService.getAlipay(request.getRemoteAddr(),p, order, goodsName, orderCount);
                // 设置编码集
                response.setContentType("text/html;charset=UTF-8");
                // 设置编码格式
                response.setCharacterEncoding(ConstantUtil.UTF);

                try {
                    // 写入
                    response.getWriter().write(sHtmlText);
                    // 获取单个订单对象
                    // Order orders = siteOrderService.getPayOrder(orderId);
                    // 订单日志记录
                    LOGGER.info("给订单号为：【" + order.getOrderCode() + "】的订单返回表单成功");
                } catch (IOException e) {
                    LOGGER.error(LOGGERINFO1 + e);
                }
            }else if("6".equals(p.getPayType())){//联付通个人支付
                // 获取支付用的信息
                String sHtmlText = null;
                try {
                    LOGGER.info("start联付通个人支付:");
                    sHtmlText = payOrderByLianFT(p, order);
                    // 设置编码集
                    response.setContentType("text/html;charsetUTF-8");
                    // 设置编码格式
                    response.setCharacterEncoding(ConstantUtil.UTF);
                    // 写入
                    response.getWriter().write(sHtmlText);
                    // 获取单个订单对象
                    // Order orders = siteOrderService.getPayOrder(orderId);
                    // 订单日志记录
                    LOGGER.info("给订单号为：【" + order.getOrderCode() + "】的订单付款成功");

                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.error(LOGGERINFO1 + e);
                }
            }
            else if("5".equals(p.getPayType())){//联付通企业支付
                // 获取支付用的信息
                String sHtmlText = null;
                try {
                    sHtmlText = payOrderByLianFTQiYe(p, order);
                    // 设置编码集
                    response.setContentType("text/html;charsetUTF-8");
                    // 设置编码格式
                    response.setCharacterEncoding(ConstantUtil.UTF);
                    // 写入
                    response.getWriter().write(sHtmlText);
                    // 获取单个订单对象
                    // Order orders = siteOrderService.getPayOrder(orderId);
                    // 订单日志记录
                    LOGGER.info("给订单号为：【" + order.getOrderCode() + "】的订单付款成功");

                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.error(LOGGERINFO1 + e);
                }
            }
            else if ("2".equals(p.getPayType())) {//银联支付
                // 字符集编码
                String encoding = ConstantUtil.UTF;
                /**
                 * 初始化证书
                 */
                String rootPath = request.getSession().getServletContext().getRealPath("WEB-INF/unionconfig");
                // MpiConfig.getConfig().loadPropertiesFromSrc();//
                // 从classpath加载mpi.properties文件
                // MpiConfig.getConfig().loadProperties(pro);从properties中加载
                MpiConfig.getConfig().loadPropertiesFromPath(rootPath); // 从指定目录下加载rootPAth不包含文件名
                /**
                 * 组装请求报文
                 */
                String requestUrl = MpiConfig.getConfig().getFrontRequestUrl();



                //前台页面传过来的
                //String merId = req.getParameter("merId");
                //String txnAmt = req.getParameter("txnAmt");

                Map<String, String> requestData = new HashMap<String, String>();

                /***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
                requestData.put("version", ConstantUtil.version);   			  //版本号，全渠道默认值
                requestData.put("encoding", ConstantUtil.UTF); 			  //字符集编码，可以使用UTF-8,GBK两种方式
                requestData.put("signMethod", "01");            			  //签名方法，只支持 01：RSA方式证书加密
                requestData.put("txnType", "01");               			  //交易类型 ，01：消费
                requestData.put("txnSubType", "01");            			  //交易子类型， 01：自助消费
                requestData.put("bizType", "000201");           			  //业务类型，B2C网关支付，手机wap支付
                requestData.put("channelType", "07");           			  //渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板  08：手机

                /***商户接入参数***/
                requestData.put("merId", p.getPayAccount());    	          //商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
                //requestData.put("merId", "898110255330226");    	          //商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
                requestData.put("accessType", "0");             			  //接入类型，0：直连商户
                requestData.put("orderId",order.getOrderCode());             //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
                requestData.put("txnTime", UtilDate.getOrderNum());        //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
                requestData.put("currencyCode", "156");         			  //交易币种（境内商户一般是156 人民币）
                requestData.put("txnAmt", getMoney(order.getOrderPrice().toString()));  //交易金额，单位分，不要带小数点
                requestData.put("reqReserved", "buyao");        		      //请求方保留域，透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节

                //前台通知地址 （需设置为外网能访问 http https均可），支付成功后的页面 点击“返回商户”按钮的时候将异步通知报文post到该地址
                //如果想要实现过几秒中自动跳转回商户页面权限，需联系银联业务申请开通自动返回商户权限
                //异步通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
                String returnUrl = p.getBackUrl();
                //requestData.put("frontUrl", "http://172.18.137.63:8080/ACPSample_WuTiaoZhuan/frontRcvResponse");
                requestData.put("frontUrl", returnUrl);

                //后台通知地址（需设置为【外网】能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，失败的交易银联不会发送后台通知
                //后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
                //注意:1.需设置为外网能访问，否则收不到通知    2.http https均可  3.收单后台通知后需要10秒内返回http200或302状态码
                //    4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，那么银联会间隔一段时间再次发送。总共发送5次，每次的间隔时间为0,1,2,4分钟。
                //    5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
                // 服务器异步通知页面路径
                String notifyUrl = p.getPayUrl() + "/unionpaynotifysuccess.html";
                requestData.put("backUrl", notifyUrl);
                /**
                 * 签名
                 */
                MpiUtil.sign(requestData, encoding);
                String html=createAutoFormHtml(requestUrl, requestData,encoding);


                LOGGER.error("打印请求HTML，此为请求报文，为联调排查问题的依据：" + html);
                //将生成的html写到浏览器中完成自动跳转打开银联支付页面；这里调用signData之后，将html写到浏览器跳转到银联页面之前均不能对html中的表单项的名称和值进行修改，如果修改会导致验签不通过
                //resp.getWriter().write(html);

                response.setContentType("text/html;charset=UTF-8");
                response.setCharacterEncoding(ConstantUtil.UTF);

                try {
                    response.getWriter().write(html);
                } catch (IOException e) {
                    LOGGER.error(LOGGERINFO1 + e);
                }
            } else if ("8".equals(p.getPayType())) {//银联网银支付
                // 字符集编码
                String encoding = ConstantUtil.UTF;
                /**
                 * 初始化证书
                 */
                String rootPath = request.getSession().getServletContext().getRealPath("WEB-INF/wypay");
                // MpiConfig.getConfig().loadPropertiesFromSrc();//
                // 从classpath加载mpi.properties文件
                // MpiConfig.getConfig().loadProperties(pro);从properties中加载
                MpiConfig.getConfig().loadPropertiesFromPath(rootPath); // 从指定目录下加载rootPAth不包含文件名
                /**
                 * 组装请求报文
                 */
                String requestUrl = MpiConfig.getConfig().getFrontRequestUrl();



                //前台页面传过来的
                //String merId = req.getParameter("merId");
                //String txnAmt = req.getParameter("txnAmt");

                Map<String, String> requestData = new HashMap<String, String>();

                /***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
                requestData.put("version", ConstantUtil.version);   			  //版本号，全渠道默认值
                requestData.put("encoding", ConstantUtil.UTF); 			  //字符集编码，可以使用UTF-8,GBK两种方式
                requestData.put("signMethod", "01");            			  //签名方法，只支持 01：RSA方式证书加密
                requestData.put("txnType", "01");               			  //交易类型 ，01：消费
                requestData.put("txnSubType", "01");            			  //交易子类型， 01：自助消费
                requestData.put("bizType", "000201");           			  //业务类型，B2C网关支付，手机wap支付
                requestData.put("channelType", "07");           			  //渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板  08：手机

                /***商户接入参数***/
                requestData.put("merId", p.getPayAccount());    	          //商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
                //requestData.put("merId", "898110255330226");    	          //商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
                requestData.put("accessType", "0");             			  //接入类型，0：直连商户
                requestData.put("orderId",order.getOrderCode());             //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
                requestData.put("txnTime", UtilDate.getOrderNum());        //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
                requestData.put("currencyCode", "156");         			  //交易币种（境内商户一般是156 人民币）
                requestData.put("txnAmt", getMoney(order.getOrderPrice().toString()));  //交易金额，单位分，不要带小数点
                requestData.put("reqReserved", "buyao");        		      //请求方保留域，透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节

                //前台通知地址 （需设置为外网能访问 http https均可），支付成功后的页面 点击“返回商户”按钮的时候将异步通知报文post到该地址
                //如果想要实现过几秒中自动跳转回商户页面权限，需联系银联业务申请开通自动返回商户权限
                //异步通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
                String returnUrl = p.getBackUrl();
                //requestData.put("frontUrl", "http://172.18.137.63:8080/ACPSample_WuTiaoZhuan/frontRcvResponse");
                requestData.put("frontUrl", returnUrl);

                //后台通知地址（需设置为【外网】能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，失败的交易银联不会发送后台通知
                //后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
                //注意:1.需设置为外网能访问，否则收不到通知    2.http https均可  3.收单后台通知后需要10秒内返回http200或302状态码
                //    4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，那么银联会间隔一段时间再次发送。总共发送5次，每次的间隔时间为0,1,2,4分钟。
                //    5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
                // 服务器异步通知页面路径
                String notifyUrl = p.getPayUrl() + "/unionpaynotifysuccess.html";
                requestData.put("backUrl", notifyUrl);

                //requestData.put("customerIp", "192.168.21.70");//request.getRemoteAddr()

                //////////////////////////////////////////////////
                //
                //       报文中特殊用法请查看 PCwap网关跳转支付特殊用法.txt
                //
                //////////////////////////////////////////////////

                /**请求参数设置完毕，以下对请求参数进行签名并生成html表单，将表单写入浏览器跳转打开银联页面**/
                //Map<String, String> submitFromData = SDKUtil.signData(requestData,SDKUtil.encoding_UTF8);  //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
                /**
                 * 签名
                 */
                MpiUtil.sign(requestData, encoding);
                String html=createAutoFormHtml(requestUrl, requestData,encoding);


                LOGGER.error("打印请求HTML，此为请求报文，为联调排查问题的依据：" + html);
                //将生成的html写到浏览器中完成自动跳转打开银联支付页面；这里调用signData之后，将html写到浏览器跳转到银联页面之前均不能对html中的表单项的名称和值进行修改，如果修改会导致验签不通过
                //resp.getWriter().write(html);

                response.setContentType("text/html;charset=UTF-8");
                response.setCharacterEncoding(ConstantUtil.UTF);

                try {
                    response.getWriter().write(html);
                } catch (IOException e) {
                    LOGGER.error(LOGGERINFO1 + e);
                }
            }
        }

    }

    private Order checkOrder(Order order) {
        // 用来存储订单的总价格
        BigDecimal orderSumPrice = new BigDecimal(0.00);
        // 飞快验证主订单号
        if (null != order.getOrderOldCode()) {
            // 根据订单那的主订单号 查询主订单号下面所有的小订单信息
            List<Order> orders = siteOrderService.getPayOrderByOldCode(order.getOrderOldCode());
            // 如果需要支付的订单数大于1
            if (null != orders && orders.size() > 1) {
                for (int i = 0; i < orders.size(); i++) {
                    // 获取单个的子订单信息
                    Order childOrder = orders.get(i);
                    // 如果订单的支付方式是货到付款 就不计算价格
                    if (!"0".equals(childOrder.getOrderLinePay())) {
                        // 把子订单的信息进行相加
                        orderSumPrice = orderSumPrice.add(childOrder.getOrderPrice());
                    }
                }
                // 把子订单的价格相加重新赋值给order对象 用于支付宝支付 不需持久化到数据库
                order.setOrderPrice(orderSumPrice);
                // 把主订单号赋值给订单单号 也是用于支付宝支付 不需要持久化到数据库
                order.setOrderCode(order.getOrderOldCode());
            }
        }
        return order;
    }


    /**银联支付
     * 功能：前台交易构造HTTP POST交易表单的方法示例<br>
     * @param action 表单提交地址<br>
     * @param hiddens 以MAP形式存储的表单键值<br>
     * @param encoding 上送请求报文域encoding字段的值<br>
     * @return 构造好的HTTP POST交易表单<br>
     */
    public static String createAutoFormHtml(String action, Map<String, String> hiddens,String encoding) {
        StringBuffer sf = new StringBuffer();
        sf.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset="+encoding+"\"/></head><body>");
        sf.append("<form id = \"pay_form\" action=\"" + action
                + "\" method=\"post\">");
        if (null != hiddens && 0 != hiddens.size()) {
            Set<Map.Entry<String, String>> set = hiddens.entrySet();
            Iterator<Map.Entry<String, String>> it = set.iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> ey = it.next();
                String key = ey.getKey();
                String value = ey.getValue();
                sf.append("<input type=\"hidden\" name=\"" + key + "\" id=\""
                        + key + "\" value=\"" + value + "\"/>");
            }
        }
        sf.append("</form>");
        sf.append("</body>");
        sf.append("<script type=\"text/javascript\">");
        sf.append("document.all.pay_form.submit();");
        sf.append("</script>");
        sf.append("</html>");
        return sf.toString();
    }


    /**
     * 构造HTTP POST交易表单的方法示例
     *
     * @param action
     *            表单提交地址
     * @param hiddens
     *            以MAP形式存储的表单键值
     * @return 构造好的HTTP POST交易表单
     */
    public static String createHtml(String action, Map<String, String> hiddens) {
        StringBuilder sf = new StringBuilder();
        sf.append("<form id = \"sform\" action=\"" + action + "\" method=\"post\">");
        if (null != hiddens && !hiddens.isEmpty()) {
            Set<Map.Entry<String, String>> set = hiddens.entrySet();
            Iterator<Map.Entry<String, String>> it = set.iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> ey = it.next();
                String key = ey.getKey();
                String value = ey.getValue();
                sf.append("<input type=\"hidden\" name=\"" + key + "\" id=\"" + key + "\" value=\"" + value + "\"/>");
            }
        }
        sf.append("</form>");
        sf.append("</body>");
        sf.append("<script type=\"text/javascript\">");
        sf.append("document.all.sform.submit();");
        sf.append("</script>");
        return sf.toString();
    }

    /**
     * 支付宝同步支付成功
     *
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/paysucccess")
    public ModelAndView paySuccess(HttpServletRequest request) {
        // 获取支付宝信息
        Pay p = payService.findByPayId(25L);

        // 设置商户号
        AlipayConfig.partner = p.getApiKey();
        // 设置商户秘钥
        AlipayConfig.key = p.getSecretKey();

        AlipayConfig.seller_email = p.getPayAccount();

        // 获取该订单的会员信息
        CustomerAllInfo ca = null;
        //
        Map<String, Object> map = null;
        // 主订单号下面的所有订单信息
        List<Order> orderslist = null;
        // 保存订单对象集合
        List<Order> orderList = new ArrayList<Order>();
        // 支付的订单价格
        BigDecimal price = new BigDecimal(0);

        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                StringBuilder buf = new StringBuilder();
                if (i == values.length - 1) {
                    buf.append(values[i]);
                } else {
                    buf.append(values[i]);
                    buf.append(",");
                }
                valueStr += buf.toString();
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            try {
                valueStr = new String(valueStr.getBytes(ISO_8859_1), UTF_8);
            } catch (UnsupportedEncodingException e) {
                LOGGER.info("支付失败" + e);
            }
            params.put(name, valueStr);
        }

        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//

        try {
            // 商户订单号
            String outTradeNo = new String(request.getParameter(OUT_TRADE_NO).getBytes(ISO_8859_1), UTF_8);
            // 支付宝交易号
            // String tradeNo = new
            // String(request.getParameter("trade_no").getBytes(ISO_8859_1),UTF_8);

            // 交易状态
            String tradeStatus = new String(request.getParameter(TRADE_STATUS).getBytes(ISO_8859_1), UTF_8);

            // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

            // 计算得出通知验证结果
            boolean verifyResult = AlipayNotify.verify(params);
            //System.out.println(verifyResult + "====== 验证结果=============");
            // if(verify_result){//验证成功
            // ////////////////////////////////////////////////////////////////////////////////////////
            // 请在这里加上商户的业务逻辑程序代码

            // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            if ("TRADE_FINISHED".equals(tradeStatus) || "TRADE_SUCCESS".equals(tradeStatus)) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 如果有做过处理，不执行商户的业务程序
                Order orderOne = siteOrderService.getPayOrderByCode(outTradeNo);
                if (null != orderOne) {
                    // 计算订单的总价格
                    price = price.add(orderOne.getOrderPrice());
                    // 获取该订单的会员信息
                    ca = customerServiceInterface.selectByPrimaryKey(orderOne.getCustomerId());
                    request.getSession().setAttribute("cust", ca);
                    request.getSession().setAttribute(CustomerConstantStr.CUSTOMERID, ca.getCustomerId());
                    // 根据订单编号订单下所有商品并随机返回一个
                    map = siteOrderService.queryGoodsProduceByOrderId(orderOne.getOrderId(), null);
                    orderList.add(orderOne);
                } else {
                    // 根据主订单号查询所有的子订单信息
                    orderslist = siteOrderService.getPayOrderByOldCode(outTradeNo);
                    // 同时支付的订单 如果大于1笔
                    if (null != orderslist) {
                        for (int i = 0; i < orderslist.size(); i++) {
                            // 创建单个订单对象
                            Order childOrder = orderslist.get(i);
                            // 计算订单的总价格
                            price = price.add(childOrder.getOrderPrice());
                            // 只需要保存一次会员信息 因为都时同一个会员提交的订单
                            if (null == ca) {
                                // 获取该订单的会员信息
                                ca = customerServiceInterface.selectByPrimaryKey(childOrder.getCustomerId());
                                request.getSession().setAttribute("cust", ca);
                                request.getSession().setAttribute(CustomerConstantStr.CUSTOMERID, ca.getCustomerId());
                            }
                            if (null == map) {
                                // 根据订单编号订单下所有商品并随机返回一个
                                map = siteOrderService.queryGoodsProduceByOrderId(childOrder.getOrderId(), null);
                            }
                            // 把订单对象装入要返回到页面的集合
                            orderList.add(childOrder);

                        }
                    }
                }
            }

            // 该页面可做页面美工编辑
            //System.out.println("验证成功<br />");
            // ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

            // ////////////////////////////////////////////////////////////////////////////////////////
            // }else{
            // 该页面可做页面美工编辑
            // System.out.println("验证失败");
            // }
        } catch (UnsupportedEncodingException e) {
            LOGGER.info(""+e);
        }

        ModelAndView mav = new ModelAndView(ORDER_PAYSUCCESS).addObject(ORDER, orderList).addObject("gs", map.get("list")).addObject(PRICE, price);
        return topAndBottomService.getSimpleTopAndBottom(mav);

    }

    /**
     * 支付宝支付异步回调
     *
     * @param request
     * @param response
     * @return ModelAndView
     * @throws java.io.UnsupportedEncodingException
     */
    @RequestMapping("/paysucccessyb")
    public void paySuccessyb(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.error("支付宝支付异步回调:paysucccessyb ///end");
        // 获取支付宝信息
        Pay p = payService.findByPayId(25L);

        // 设置商户号
        AlipayConfig.partner = p.getApiKey();
        // 设置商户秘钥
        AlipayConfig.key = p.getSecretKey();

        AlipayConfig.seller_email = p.getPayAccount();
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<?, ?> requestParams = request.getParameterMap();
        for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            StringBuilder valueStr = new StringBuilder();
            for (int i = 0; i < values.length; i++) {
                if (i == values.length - 1) {
                    valueStr.append(values[i]);
                } else {
                    valueStr.append(values[i]);
                    valueStr.append(",");
                }
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes(ISO_8859_1), "gbk");
            params.put(name, valueStr.toString());
        }

        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        // 商户订单号
        String outTradeNo = new String(request.getParameter(OUT_TRADE_NO).getBytes(ISO_8859_1), ConstantUtil.UTF);
        // 支付宝交易号
        // 交易状态
        String tradeStatus = new String(request.getParameter(TRADE_STATUS).getBytes(ISO_8859_1), ConstantUtil.UTF);
        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        if (AlipayNotify.verify(params)) {// 验证成功
            LOGGER.error("支付宝支付交易状态paySuccessyb:"+tradeStatus+"///end");
            LOGGER.error("支付宝支付返回订单号paySuccessyb:"+outTradeNo+"///end");
            chechOrderStatus(tradeStatus, outTradeNo);
            sendSucess(response, SUCCESS);
        } else {// 验证失败
            sendSucess(response, "fail");
        }

    }

    /**
     * 支付宝回调（多笔）
     *
     * @param request
     * @param response
     * @return ModelAndView
     * @throws java.io.UnsupportedEncodingException
     */
    @RequestMapping("/paysucccessybmulti")
    public void paySuccessybmulti(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取支付宝信息
        Pay p = payService.findByPayId(25L);

        // 设置商户号
        AlipayConfig.partner = p.getApiKey();
        // 设置商户秘钥
        AlipayConfig.key = p.getSecretKey();

        AlipayConfig.seller_email = p.getPayAccount();
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<?, ?> requestParams = request.getParameterMap();
        for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            StringBuilder valueStr = new StringBuilder();
            for (int i = 0; i < values.length; i++) {
                if (i == values.length - 1) {
                    valueStr.append(values[i]);
                } else {
                    valueStr.append(values[i]);
                    valueStr.append(",");
                }
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes(ISO_8859_1), "gbk");
            params.put(name, valueStr.toString());
        }

        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        // 商户订单号
        String outTradeNo = new String(request.getParameter(OUT_TRADE_NO).getBytes(ISO_8859_1), ConstantUtil.UTF);
        // 支付宝交易号
        // 交易状态
        String tradeStatus = new String(request.getParameter(TRADE_STATUS).getBytes(ISO_8859_1), ConstantUtil.UTF);
        LOGGER.error("支付宝支付交易状态paySuccessybmulti:"+tradeStatus+"///end");
        LOGGER.error("支付宝支付返回订单号paySuccessybmulti:"+outTradeNo+"///end");
        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        if (AlipayNotify.verify(params)) { // 验证成功
            // ////////////////////////////////////////////////////////////////////////////////////////
            // 请在这里加上商户的业务逻辑程序代码
            /**
             * 根据主订单号查询所有的子订单信息 如果能查到值证明传过来的是主订单号 如果没值证明传过来的是子订单号
             */
            List<Order> orderslist = siteOrderService.getPayOrderByOldCode(outTradeNo);
            // 多笔订单支付
            if (null != orderslist && orderslist.size() > 1) {
                LOGGER.error("orderslist.size():"+orderslist.size()+"///end");
                for (int i = 0; i < orderslist.size(); i++) {
                    // 多笔订单支付
                    Order childOrder = orderslist.get(i);
                    // 更改订单那的支付状态
                    //错误 childOrder.getOrderCargoStatus chechOrderStatus(tradeStatus, childOrder.getOrderCargoStatus());
                    chechOrderStatus(tradeStatus, childOrder.getOrderCode());
                    LOGGER.error("childOrder.getOrderCode():"+childOrder.getOrderCode()+"///end");
                }
            }
            // ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

            sendSucess(response, SUCCESS);

            // ////////////////////////////////////////////////////////////////////////////////////////
        } else {// 验证失败
            sendSucess(response, "fail");
        }

    }

    /**
     *
     * @param tradeStatus
     *            支付宝交易号 交易状态
     * @param outTradeNo
     *            支付宝 支付成功 返回的订单号
     * @return
     */
    public void chechOrderStatus(String tradeStatus, String outTradeNo) {

        // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
        Receivables receivables = this.receivablesService.queryByOrderCode(outTradeNo);
        if ("TRADE_FINISHED".equals(tradeStatus)) {
            // 判断该笔订单是否在商户网站中已经做过处理
            // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
            // 如果有做过处理，不执行商户的业务程序
            // 重新将session登入
            Order or = siteOrderService.getPayOrderByCode(outTradeNo);
            if ("0".equals(or.getOrderStatus())) {
                siteOrderService.payOrder(or.getOrderId());
                if (null != receivables) {
                    // 修改订单支付状态为支付成功
                    receivables.setReceivablesTime(new Date());
                    this.receivablesService.updatePayStatus(receivables);
                }
            }
            // 注意：
            // 该种交易状态只在两种情况下出现
            // 1、开通了普通即时到账，买家付款成功后。
            // 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
        } else if ("TRADE_SUCCESS".equals(tradeStatus)) {
            // 判断该笔订单是否在商户网站中已经做过处理
            // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
            // 如果有做过处理，
            Order or = siteOrderService.getPayOrderByCode(outTradeNo);
            LOGGER.error("or.getOrderStatus() : " + or.getOrderStatus());
            if ("0".equals(or.getOrderStatus())) {
                LOGGER.error("or.getOrderStatus() : " + or.getOrderId());
                siteOrderService.payOrder(or.getOrderId());
                LOGGER.error("receivables1 : " + receivables.getOrderCode());
                if (null != receivables) {
                    // 修改订单支付状态为支付成功
                    receivables.setReceivablesTime(new Date());
                    LOGGER.error("receivables2 : " + receivables.getOrderCode() + "  " + receivables.getPayStatus());
                    this.receivablesService.updatePayStatus(receivables);//收款表 订单状态
                    receivables = this.receivablesService.queryByOrderCode(outTradeNo);
                    LOGGER.error("receivables3 : " + receivables.getPayStatus());
                }
            }
            // 注意：
            // 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
        }
    }

    /**
     * 银联支付同步回调
     *
     * @param request
     *            请求
     * @return
     */
    @RequestMapping("qmpaysuc")
    public ModelAndView qmpaysuc(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        /**
         * 初始化证书
         */
        String rootPath = request.getSession().getServletContext().getRealPath("WEB-INF/unionconfig");
        // MpiConfig.getConfig().loadPropertiesFromSrc();//
        // 从classpath加载mpi.properties文件
        // MpiConfig.getConfig().loadProperties(pro);从properties中加载
        MpiConfig.getConfig().loadPropertiesFromPath(rootPath); // 从指定目录下加载rootPAth不包含文件名

        StringBuffer buffer=new StringBuffer();
        LOGGER.info("银联支付同步通知开始");

        request.setCharacterEncoding("ISO-8859-1");
        String encoding = request.getParameter(ConstantUtil.param_encoding);
        // 获取银联通知服务器发送的后台通知参数
        Map<String, String> reqParam = getAllRequestParam(request);

        LogUtil.printRequestLog(reqParam);

        Map<String, String> valideData = null;
        if (null != reqParam && !reqParam.isEmpty()) {
            Iterator<Map.Entry<String, String>> it = reqParam.entrySet().iterator();
            valideData = new HashMap<String, String>(reqParam.size());
            while (it.hasNext()) {
                Map.Entry<String, String> e = it.next();
                String key = (String) e.getKey();
                String value = (String) e.getValue();
                value = new String(value.getBytes("ISO-8859-1"), encoding);
                valideData.put(key, value);
                buffer.append(key+":"+value+";");
            }
        }

        //重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
        if (!MpiUtil.validate(valideData, encoding)) {
            LogUtil.writeLog("银联支付验证签名结果[失败].");
            //验签失败，需解决验签问题
            int i=10/0;
        } else {
            LogUtil.writeLog("银联支付验证签名结果[成功].");
            //【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态

            String orderOldCode =valideData.get("orderId"); //获取后台通知的数据，其他字段也可用类似方式获取
            String respCode =valideData.get("respCode"); //获取应答码，收到后台通知了respCode的值一般是00，可以不需要根据这个应答码判断。
            // 订单号
            //String orderOldCode = valideData.get("orderId");//"2016011916532134";//request.getParameter("20160119165320");
            List<Order> orderslist = new ArrayList<Order>();
            // 重新将session登入
            Order or = siteOrderService.getPayOrderByCode(orderOldCode);
            orderslist.add(or);
            CustomerAllInfo ca = customerServiceInterface.selectByPrimaryKey(or.getCustomerId());
            request.getSession().setAttribute("cust", ca);
            request.getSession().setAttribute(CustomerConstantStr.CUSTOMERID, ca.getCustomerId());
            Map<String, Object> map = siteOrderService.queryGoodsProduceByOrderId(or.getOrderId(), null);
            ModelAndView mav = new ModelAndView(ORDER_PAYSUCCESS).addObject(ORDER, orderslist).addObject("gs", map.get("list")).addObject(PRICE, or.getOrderPrice());
            return topAndBottomService.getSimpleTopAndBottom(mav);

        }
        LogUtil.writeLog("BackRcvResponse接收后台通知结束");
        //返回给银联服务器http 200  状态码
        resp.getWriter().print("ok");
        LOGGER.info("银联支付同步返回参数："+buffer.toString());
        return new ModelAndView("error/comming_soon");
    }

    /**
     * 银联支付异步通知
     *
     * @param req
     * @param resp
     */
    @RequestMapping("/unionpaynotifysuccess")
    public void unionpayNotifySuccess(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("银联支付异步通知接收后台通知开始");

        req.setCharacterEncoding("ISO-8859-1");
        String encoding = req.getParameter(ConstantUtil.param_encoding);
        // 获取银联通知服务器发送的后台通知参数
        Map<String, String> reqParam = getAllRequestParam(req);

        LogUtil.printRequestLog(reqParam);

        Map<String, String> valideData = null;
        if (null != reqParam && !reqParam.isEmpty()) {
            Iterator<Map.Entry<String, String>> it = reqParam.entrySet().iterator();
            valideData = new HashMap<String, String>(reqParam.size());
            while (it.hasNext()) {
                Map.Entry<String, String> e = it.next();
                String key = (String) e.getKey();
                String value = (String) e.getValue();
                value = new String(value.getBytes("ISO-8859-1"), encoding);
                valideData.put(key, value);
            }
        }

        //重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
        if (!MpiUtil.validate(valideData, encoding)) {
            LogUtil.writeLog("验证签名结果[失败].");
            //验签失败，需解决验签问题

        } else {
            LogUtil.writeLog("验证签名结果[成功].");
            //【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态

            String orderId =valideData.get("orderId"); //获取后台通知的数据，其他字段也可用类似方式获取
            String respCode =valideData.get("respCode"); //获取应答码，收到后台通知了respCode的值一般是00，可以不需要根据这个应答码判断。

            Order or = siteOrderService.getPayOrderByCode(orderId);
            // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            Receivables receivables = this.receivablesService.queryByOrderCode(orderId);
            if ("0".equals(or.getOrderStatus())) {
                siteOrderService.payOrder(or.getOrderId());
                if (null != receivables) {
                    // 修改订单支付状态为支付成功
                    receivables.setReceivablesTime(new Date());
                    this.receivablesService.updatePayStatus(receivables);//收款表 订单状态
                }
            }
            //返回给银联服务器http 200  状态码
            resp.getWriter().print("ok");
        }
        LogUtil.writeLog("银联支付异步接收后台通知结束");
    }

    /**
     * 银联支付）获取请求参数中所有的信息
     *
     * @param request
     * @return
     */
    public static Map<String, String> getAllRequestParam(final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                //在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
                //System.out.println("ServletUtil类247行  temp数据的键=="+en+"     值==="+value);
                if (null == res.get(en) || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        return res;
    }

    /**
     * 发送成功
     *
     * @param response
     * @param msg
     * @throws IOException
     */
    public void sendSucess(HttpServletResponse response, String msg) throws IOException {
        PrintWriter out = response.getWriter();
        out.println(msg);
        out.flush();
        out.close();
    }

    /**
     * 获取ip地址
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /***
     * 联付通企业支付
     * zz：pl
     * @param order 订单
     */
    public String payOrderByLianFTQiYe(Pay p,Order order) throws Exception {
        UPS ups = new UPS();
        RequestGroup reqg = new RequestGroup();
        Request request = new Request();

        ups.setRequestGroup(reqg);
        reqg.setRequest(request);

        request.setVersion("1.0");
        request.setTxType("TX20");

        UUID gUUid =UUID.randomUUID();
        String uudi = gUUid.toString();
        request.setId(uudi.replace("-", ""));
        ////--定义域-------------------------------------------
        TxInfo txInfo = new TxInfo();
        Merchant merchant = new Merchant();
        com.lianft.ups.common.sdk.entity.Order order_Q = new com.lianft.ups.common.sdk.entity.Order();
        ////--把域添加到报文中----------------------------------
        request.setTxInfo(txInfo);
        request.setMerchant(merchant);
        request.setOrder(order_Q);


        //--交易信息-------------------------------------------
        txInfo.setTxTraceNo(order.getOrderCode());
        //Double dd= Double.valueOf(String.valueOf(order.getOrderPrice()));
        BigDecimal bigD = order.getOrderPrice();
        bigD = bigD.multiply(new BigDecimal(100)).divide(BigDecimal.valueOf(1), 1, ROUND_HALF_UP);
        Long result = bigD.longValue();
        txInfo.setTxTotalAmount(result);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//时:分:秒:毫秒
        txInfo.setMerchantTxTime(sdf.format(d));
        txInfo.setUsageType("4");
        txInfo.setUsageDesc("企业网关支付");
//		txInfo.setTxPayEndDate("");

        //--商户信息-------------------------------------------
        merchant.setMerchantID(p.getApiKey());

        //--订单信息-------------------------------------------
        order_Q.setUserName(order.getShippingPerson());
        //order_Q.setOrderNo(System.currentTimeMillis()+"");
        order_Q.setOrderNo(order.getOrderCode());
        order_Q.setOrderAmount(result);
        order_Q.setOrderTime(sdf.format(order.getCreateTime()));
        order_Q.setOrderDesc("网购支付");
        order_Q.setSettleType("1010");
        //order_Q.setBuyerEntID("");

        String filePath=PaySiteController.class.getClassLoader().getResource("com/ningpai/web/config/lianft_merchant.properties").getFile();
        LianftService lianftService = LianftService.getInstance(filePath);
        String res = lianftService.sendBackString(ups, "TX20");
        return res;//订单号201511091535494
    }

    /***
     * 联付通个人支付
     * zz：pl
     * @param order 订单
     */
    public String payOrderByLianFT(Pay p,Order order) throws Exception {
        UPS ups = new UPS();
        RequestGroup reqg = new RequestGroup();
        Request request = new Request();

        ups.setRequestGroup(reqg);
        reqg.setRequest(request);

        request.setVersion("1.0");
        request.setTxType("TX10");

        UUID gUUid =UUID.randomUUID();
        String uudi = gUUid.toString();
        request.setId(uudi.replace("-", ""));
        ////--定义域-------------------------------------------
        TxInfo txInfo = new TxInfo();
        Merchant merchant = new Merchant();
        com.lianft.ups.common.sdk.entity.Order lF_order = new com.lianft.ups.common.sdk.entity.Order();
        ////--把域添加到报文中----------------------------------
        request.setTxInfo(txInfo);
        request.setMerchant(merchant);
        request.setOrder(lF_order);


        //--交易信息-------------------------------------------
        txInfo.setTxTraceNo(order.getOrderCode());//订单流水号(订单编号)
        //Double dd= order.getOrderPrice().doubleValue();
        BigDecimal bigD = order.getOrderPrice();
        bigD = bigD.multiply(new BigDecimal(100)).divide(BigDecimal.valueOf(1), 1, ROUND_HALF_UP);
        Long result = bigD.longValue();
        txInfo.setTxTotalAmount(result);//支付金额，以分为单位
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");//时:分:秒:毫秒
        txInfo.setMerchantTxTime(sdf.format(d));//交易发生时间
//		txInfo.setTxPayEndDate("");

        //--商户信息-------------------------------------------
        merchant.setMerchantID(p.getApiKey());//商户注册代码，由联付通分配

        //--订单信息-------------------------------------------
        //lF_order.setUserName("");
        //lF_order.setOrderNo(System.currentTimeMillis()+"");
        lF_order.setOrderNo(order.getOrderCode());
        lF_order.setOrderAmount(result);
        lF_order.setOrderTime(sdf.format(order.getCreateTime()));
        lF_order.setOrderDesc("网购支付");
        lF_order.setSettleType("2010");

        String filePath=PaySiteController.class.getClassLoader().getResource("com/ningpai/web/config/lianft_merchant.properties").getFile();
        //String filePath=PaySiteController.class.getClassLoader().getResource("/data/station/qpmall_site/WEB-INF/classes/com/ningpai/web/config/lianft_merchant.properties").getFile();

        System.out.println(filePath);
        //filePath=filePath.substring(1,filePath.length());
        LianftService lianftService = LianftService.getInstance(filePath);
        String res = lianftService.sendBackString(ups, "TX10");
        return res;
    }

    /***
     * 联付通后台回调函数
     * @param request
     * @param response
     */
    @RequestMapping("/paysucccessByLianFT")
    public void paySuccessByLianFT(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String responBody= request.getParameter("NOTIFYMSG");
        //String responBody= "";
        LOGGER.error("后台异步回调："+responBody);
        //System.out.println(responBody);
        UPS ups=null;
        TxInfo txInfo=null;

        try {
            String filePath=PaySiteController.class.getClassLoader().getResource("com/ningpai/web/config/lianft_merchant.properties").getFile();
            ups = LianftService.getInstance(filePath).parseResponse(responBody);
            txInfo =ups.getResponseGroup().getResponse().getTxInfo();
        }
        catch (Exception e){
            LOGGER.error("堆栈信息：start");
            e.getStackTrace();
            e.printStackTrace();
            System.out.println(e.getStackTrace());
            LOGGER.error("堆栈信息：end");
            LOGGER.error("后台回调异常:"+e.getMessage());
        }
        if(ups==null){
            LOGGER.error("ups is null");
        }
        if(txInfo==null){
            LOGGER.error("txInfo is null");
        }

        if(ups!=null&&txInfo!=null){
            String txTraceNo = txInfo.getTxTraceNo();
            String txStatus = txInfo.getTxStatus();
            LOGGER.info("txTraceNo:"+txTraceNo+"//txStatus："+txStatus);
            if(!"".equals(txTraceNo)&&"1".equals(txStatus)){
                Order or = siteOrderService.getPayOrderByCode(txTraceNo);
                // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
                Receivables receivables = this.receivablesService.queryByOrderCode(txTraceNo);
                if ("0".equals(or.getOrderStatus())||"20".equals(or.getOrderStatus())) {
                    siteOrderService.payOrder(or.getOrderId());
                    if (null != receivables) {
                        // 修改订单支付状态为支付成功
                        receivables.setReceivablesTime(new Date());
                        this.receivablesService.updatePayStatus(receivables);//收款表 订单状态
                    }
                }
                sendSucess(response, "1");
            }
            else if(!"".equals(txTraceNo)&&"5".equals(txStatus)){//一提交至银行 等待银行处理
                Order or = siteOrderService.getPayOrderByCode(txTraceNo);
                // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
                //Receivables receivables = this.receivablesService.queryByOrderCode(txTraceNo);//查询收款表
                if ("0".equals(or.getOrderStatus())) {
                    siteOrderService.payOrder(or.getOrderId());
                    /*if (null != receivables) {
                        // 修改订单支付状态为支付成功
                        receivables.setReceivablesTime(new Date());
                        this.receivablesService.updatePayStatus(receivables);//收款表 订单状态
                    }*/
                }
                sendSucess(response, "1");
            }
            else{
                sendSucess(response, "0");
            }
        } else {
            sendSucess(response, "0");
        }
        sendSucess(response, "0");
    }

    /**
     * 联付通支付成功前台回调
     *
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/paysucccesslf")
    public ModelAndView getsussecc(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = null;
        // 主订单号下面的所有订单信息
        List<Order> orderslist = null;
        // 保存订单对象集合
        List<Order> orderList = new ArrayList<Order>();
        // 支付的订单价格
        BigDecimal price = new BigDecimal(0);

        // 获取该订单的会员信息
        CustomerAllInfo ca = null;

        String responBody= request.getParameter("NOTIFYMSG");
        //String responBody= "eJylVdtyqkgUffcrLE/VvHiSBgGVGeCU4A0lahTQ+NbBRtFuQBq8ff1pvCSaSs1MzfiAvfderL32BVB+HQgu7lBCgyhUS/wzVyqi0IsWQbhUS47dfqqXfmkFxRlNigwZUrW0StP4TwD2+/0zDmDop89eRECcRGvkpSCLaUlTxojGUUhRMViopXe5WhHriyrHeUisC4IsC9xCFOt8zYd1uS4yvHsRoLH8CrgZin2wjzHS7FmFea8Gc5qhH52DCfTQINIqHC/xFZ7nOVGqyDx/xl5jig7Dzd9BH+M5a5RC3CBRFqYaz10S37kYYpLCNKPaOc/1rDgULtFZYK7107oEmoh62s1/NhQjIjFMUBOm6FMVL4mirID7mKIHpzNTmGH8ByTxX+cLE371Ky8occaWdjeWbUwgxpexwCPNPM9DlGL/eZUSBVzxufpLJ5nDW8G8ttvJbGoc++X9uXN9Gux4m7CmTIJlyJqQoG8WZC88R8kSVBgZ4GTAAAsaLH+U2Eblt6HFWUFBMWAYhYEHcXCCKZv9C0pX0aLYwMsoCdIV+Y7UHue8PBi3jCdG/OTxYviUeziBl9hOge9Jr6nPiv9Nmq/aEwqf6AryeYYvRIx6jHyUsAcIFZ2xqZZ+/PPuFxS2fCH1o4TQe+N/Fv/BwzjBQ4ZmsEQ0/S+l38q+Z/ggdCHOkPbSq0Rrt+sOykdOlg+C1efmjY2Jlhan3m68IAv5Dl2blRsP+/DR2Cu2R+a7Q8VtmfY62TRHOp+92bqrAwGHRuelG5jmcNgkwrBxTOwyrvO60aATuSbQSf/kzQ8rX+bsVmXNZmDgwnQP/f37bJd2nO5r3/QtAnbEMzgshH626pbfKqAXT0deXJfpzpXGTYJxhv1RGIvlZS1otlvczmpLg9ZmPi1IDXPaqAaT09paG6u3oGburN1gvj720+68OYuXPt8aTGJdp+xpsfteqy2f2vLa6fUcqdHMsDs5dU44c4hUrhXWw/YIVyGSB9NgO3cp5vadzo4buZgc9O3Wmk+HMgHbfTcm7swhZRzXWuam7M/bOz+BiShkr+6rXp/6lgMKWF5kPatvLGatF7vmtPf9U8/cd/tkPh3FvU3H00Xr/VVVC3d7fBtNHx2vk5hJnMxeRPB6NCnNUDJBSQDxg2sACdKMgbpln4Q4CAmMfhaHzqP5YFmq3vtZnNjnP0M1Bgr4wnahv+QaZOQdJRpfrUqixHN1+YJ+CBbuGT4kgrsKwGddnzWzhWZfN+03WEVNqQ==";
        LOGGER.error("前台回调："+responBody);
        if(responBody==null||"".equals(responBody)){
            return new ModelAndView("redirect:/customer/myorder.html");
        }
        UPS ups=null;
        TxInfo txInfo=null;

        try {
            String filePath=PaySiteController.class.getClassLoader().getResource("com/ningpai/web/config/lianft_merchant.properties").getFile();
            LOGGER.error("filePath：" + filePath);
            ups = LianftService.getInstance(filePath).parseResponse(responBody);
            if(ups==null){
                LOGGER.error("ups is null");
            }
            txInfo =ups.getResponseGroup().getResponse().getTxInfo();
            if(txInfo==null){
                LOGGER.error("txInfo is null");
            }
        }
        catch (Exception e){
            LOGGER.error("堆栈信息：start");
            e.getStackTrace();
            e.printStackTrace();
            System.out.println(e.getStackTrace());
            LOGGER.error("堆栈信息：end");
            LOGGER.error("后台回调异常:"+e.getMessage());
        }

        if(ups!=null&&txInfo!=null){
            String txTraceNo = txInfo.getTxTraceNo();
            String txStatus = txInfo.getTxStatus();
            LOGGER.info("txTraceNo:"+txTraceNo+"//txStatus："+txStatus);
            if(!"".equals(txTraceNo)&&"1".equals(txStatus)){
                Order orderOne = siteOrderService.getPayOrderByCode(txTraceNo);
                if (null != orderOne) {
                    // 计算订单的总价格
                    price = price.add(orderOne.getOrderPrice());
                    // 获取该订单的会员信息
                    ca = customerServiceInterface.selectByPrimaryKey(orderOne.getCustomerId());
                    request.getSession().setAttribute("cust", ca);
                    request.getSession().setAttribute(CustomerConstantStr.CUSTOMERID, ca.getCustomerId());
                    // 根据订单编号订单下所有商品并随机返回一个
                    map = siteOrderService.queryGoodsProduceByOrderId(orderOne.getOrderId(), null);
                    orderList.add(orderOne);
                } else {
                    // 根据主订单号查询所有的子订单信息
                    orderslist = siteOrderService.getPayOrderByOldCode(txTraceNo);
                    // 同时支付的订单 如果大于1笔
                    if (null != orderslist) {
                        for (int i = 0; i < orderslist.size(); i++) {
                            // 创建单个订单对象
                            Order childOrder = orderslist.get(i);
                            // 计算订单的总价格
                            price = price.add(childOrder.getOrderPrice());
                            // 只需要保存一次会员信息 因为都时同一个会员提交的订单
                            if (null == ca) {
                                // 获取该订单的会员信息
                                ca = customerServiceInterface.selectByPrimaryKey(childOrder.getCustomerId());
                                request.getSession().setAttribute("cust", ca);
                                request.getSession().setAttribute(CustomerConstantStr.CUSTOMERID, ca.getCustomerId());
                            }
                            if (null == map) {
                                // 根据订单编号订单下所有商品并随机返回一个
                                map = siteOrderService.queryGoodsProduceByOrderId(childOrder.getOrderId(), null);
                            }
                            // 把订单对象装入要返回到页面的集合
                            orderList.add(childOrder);
                        }
                    }
                }
            }
        }
        ModelAndView mav = new ModelAndView(ORDER_PAYSUCCESS).addObject(ORDER, orderList).addObject("gs", map.get("list")).addObject(PRICE, price);
        return topAndBottomService.getSimpleTopAndBottom(mav);
    }


    /***
     * PC端微信支付
     * zz：pl
     * @return
     */
    @RequestMapping("/payorderwx")
    public ModelAndView payOrderByWX(Long orderCount, Long orderId, HttpServletRequest request, Long payId, HttpServletResponse response){

        // 获取用户id
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 判断用户id是否为空
        if (customerId == null) {
            return new ModelAndView(new RedirectView("login.html"));
        }
        // 返回订单商品的商品名称，取第一个
        String goodsName = orderser.queryGoodsInfoName(orderId);
        // 获取订单信息
        Order order = siteOrderService.getPayOrder(orderId);

        // 查询使用的支付信息
        Pay p = payService.findByPayId(payId);
        if (p != null) {
            // 多笔订单 循环生成多笔付款单信息
            if (null != orderCount) {
                // 根据主订单号查询所有的子订单信息
                List<Order> orderslist = siteOrderService.getPayOrderByOldCode(order.getOrderOldCode());
                for (int i = 0; i < orderslist.size(); i++) {
                    // 创建单个订单对象
                    Order childOrder = orderslist.get(i);
                    // 如果是在线支付
                    if ("1".equals(childOrder.getOrderLinePay())) {
                        // 单笔订单
                        this.getReceivables(p, request, childOrder);
                    }
                }
            } else {
                // 单笔订单 生成单笔的付款单信息
                this.getReceivables(p, request, order);
            }
        }

        WxPayDto tpWxPay1 = new WxPayDto();
        tpWxPay1.setBody(goodsName);
        tpWxPay1.setOrderId(order.getOrderCode());
        tpWxPay1.setSpbillCreateIp(request.getRemoteAddr());
        //BigDecimal b = new BigDecimal("100");
        tpWxPay1.setTotalFee((order.getOrderPrice()).toString());
        String codeURL= getCodeurl(tpWxPay1,p);

        if(codeURL==null){
            ModelAndView mav=new ModelAndView();
            mav.setViewName("index/error");
            return topAndBottomService.getSimpleTopAndBottom(mav);
        }

        ModelAndView mav=new ModelAndView(ORDER_PAYBYWX);
        mav.addObject("codeURL",codeURL);
        mav.addObject("orderCode",order.getOrderCode());
        mav.addObject("order",order);
        mav.addObject("orderName",goodsName);
        mav.addObject("mobile",order.getShippingMobile().substring(0,3)+"****"+order.getShippingMobile().substring(7,11));
        return topAndBottomService.getSimpleTopAndBottom(mav);
    }

    /**
     * 获取微信扫码支付二维码连接
     */
    public static String getCodeurl(WxPayDto tpWxPayDto,Pay p){
        // 1 参数
        // 订单号
        String orderId = tpWxPayDto.getOrderId();
        // 附加数据 原样返回
        String attach = "";
        // 总金额以分为单位，不带小数点
        String totalFee = getMoney(tpWxPayDto.getTotalFee());

        // 订单生成的机器 IP
        String spbill_create_ip = tpWxPayDto.getSpbillCreateIp();
        // 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
        String notify_url = p.getBackUrl()+"/orderStateBackByWX.htm";
        String trade_type = "NATIVE";

        // 商户号
        String mch_id = p.getPartner();
        // 随机字符串
        String nonce_str = getNonceStr();

        // 商品描述根据情况修改
        String body = tpWxPayDto.getBody();

        // 商户订单号
        String out_trade_no = orderId;

        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", p.getApiKey());
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body);
        packageParams.put("attach", attach);
        packageParams.put("out_trade_no", out_trade_no);

        // 这里写的金额为1 分到时修改
        packageParams.put("total_fee", totalFee);
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", notify_url);

        packageParams.put("trade_type", trade_type);

        RequestHandler reqHandler = new RequestHandler(null, null);
        reqHandler.init(p.getApiKey(), p.getSecretKey(), p.getPartnerKey());

        String sign = reqHandler.createSign(packageParams);
        String xml = "<xml>" + "<appid>" + p.getApiKey() + "</appid>" + "<mch_id>"
                + mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
                + "</nonce_str>" + "<sign>" + sign + "</sign>"
                + "<body><![CDATA[" + body + "]]></body>"
                + "<out_trade_no>" + out_trade_no
                + "</out_trade_no>" + "<attach>" + attach + "</attach>"
                + "<total_fee>" + totalFee + "</total_fee>"
                + "<spbill_create_ip>" + spbill_create_ip
                + "</spbill_create_ip>" + "<notify_url>" + notify_url
                + "</notify_url>" + "<trade_type>" + trade_type
                + "</trade_type>" + "</xml>";
        String code_url = "";
        String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";


        code_url = new GetWxOrderno().getCodeUrl(createOrderURL, xml);
        LOGGER.error("code_url----------------" + code_url);

        return code_url;
    }

    @RequestMapping(value = "/GetOrderStateByWX",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String GetOrderStateByWX(String orderCode){
        Order or = siteOrderService.getPayOrderByCode(orderCode);
        if("1".equals(or.getOrderStatus())){
            return "1";
        }
        return "0";
    }

    /***
     * 微信回调函数
     * zz：pl
     */
    @RequestMapping("/orderStateBackByWX")
    public void orderStateBackByWX(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //把如下代码贴到的你的处理回调的servlet 或者.do 中即可明白回调操作
        LOGGER.error("微信支付回调数据开始");
        //示例报文
        //String notityXml = "<xml><appid><![CDATA[wxb0fe165dcba0d8fd]]></appid><bank_type><![CDATA[CFT]]></bank_type><cash_fee><![CDATA[100]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1283883301]]></mch_id><nonce_str><![CDATA[0959283194]]></nonce_str><openid><![CDATA[o6JSFxFj2J3qFydWtpksmWD-6m4c]]></openid><out_trade_no><![CDATA[2015120909592532]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[C59AC7DD09D115D876B4DDF6AB3AD912]]></sign><time_end><![CDATA[20151209100040]]></time_end><total_fee>100</total_fee><trade_type><![CDATA[NATIVE]]></trade_type><transaction_id><![CDATA[1005291002201512091973761317]]></transaction_id></xml>";
        String inputLine;
        String notityXml = "";
        String resXml = "";

        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                notityXml += inputLine;
            }
            request.getReader().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        LOGGER.error("接收到的报文：" + notityXml);

        Map m = parseXmlToList2(notityXml);
        WxPayResult wpr = new WxPayResult();
        wpr.setAppid(m.get("appid").toString());
        wpr.setBankType(m.get("bank_type").toString());
        wpr.setCashFee(m.get("cash_fee").toString());
        wpr.setFeeType(m.get("fee_type").toString());
        wpr.setIsSubscribe(m.get("is_subscribe").toString());
        wpr.setMchId(m.get("mch_id").toString());
        wpr.setNonceStr(m.get("nonce_str").toString());
        wpr.setOpenid(m.get("openid").toString());
        wpr.setOutTradeNo(m.get("out_trade_no").toString());
        wpr.setResultCode(m.get("result_code").toString());
        wpr.setReturnCode(m.get("return_code").toString());
        wpr.setSign(m.get("sign").toString());
        wpr.setTimeEnd(m.get("time_end").toString());
        wpr.setTotalFee(m.get("total_fee").toString());
        wpr.setTradeType(m.get("trade_type").toString());
        wpr.setTransactionId(m.get("transaction_id").toString());

        if("SUCCESS".equals(wpr.getResultCode())){
            Order or = siteOrderService.getPayOrderByCode(wpr.getOutTradeNo());
            // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            Receivables receivables = this.receivablesService.queryByOrderCode(wpr.getOutTradeNo());
            if ("0".equals(or.getOrderStatus())) {
                siteOrderService.payOrder(or.getOrderId());
                if (null != receivables) {
                    // 修改订单支付状态为支付成功
                    receivables.setReceivablesTime(new Date());
                    this.receivablesService.updatePayStatus(receivables);//收款表 订单状态
                }
            }
            //支付成功
            resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                    + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
        }else{
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }

        LOGGER.error("微信支付回调数据结束");

        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }

    //测试
    @RequestMapping("/paysucccesslfsdsd")
    public ModelAndView Test() throws Exception {
        ModelAndView mav=new ModelAndView();
        mav.setViewName("index/error");
        return topAndBottomService.getSimpleTopAndBottom(mav);
        /*String str="";
        String filePath=PaySiteController.class.getClassLoader().getResource("com/ningpai/web/config/lianft_merchant.properties").getFile();
        UPS ups = LianftService.getInstance(filePath).parseResponse(str);
        TxInfo txInfo =ups.getResponseGroup().getResponse().getTxInfo();

        if(ups!=null&&txInfo!=null){
            String txTraceNo = txInfo.getTxTraceNo();
            String txStatus = txInfo.getTxStatus().trim();
            if(!"".equals(txTraceNo)&&"1".equals(txStatus)){
                Order or = siteOrderService.getPayOrderByCode(txTraceNo);
                // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
                Receivables receivables = this.receivablesService.queryByOrderCode(txTraceNo);
                if ("0".equals(or.getOrderStatus())) {
                    siteOrderService.payOrder(or.getOrderId());
                    if (null != receivables) {
                        // 修改订单支付状态为支付成功
                        receivables.setReceivablesTime(new Date());
                        this.receivablesService.updatePayStatus(receivables);//收款表 订单状态
                    }
                }
            }
            else{
            }
        } else {
        }*/
    }

    @Test
    public void test2(){
        Double dd= BigDecimal.valueOf(100.11).doubleValue();
        BigDecimal bigD = new BigDecimal(dd);
        bigD = bigD.multiply(new BigDecimal(100)).divide(BigDecimal.valueOf(1), 1, ROUND_HALF_UP);
        Long result = bigD.longValue();
        System.out.println(result);
    }
}
