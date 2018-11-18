package com.ningpai.site.order.controller;

import com.alibaba.fastjson.JSON;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.common.util.alipay.util.UtilDate;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.order.bean.Order;
import com.ningpai.order.service.OrderService;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.site.order.bean.WxPayDto;
import com.ningpai.site.order.bean.WxPayResult;
import com.ningpai.site.order.common.GetWxOrderno;
import com.ningpai.site.order.service.SiteOrderService;
import com.ningpai.site.order.util.RequestHandler;
import com.ningpai.system.bean.Pay;
import com.ningpai.system.bean.Receivables;
import com.ningpai.system.service.PayService;
import com.ningpai.system.service.ReceivablesService;
import com.ningpai.util.MyLogger;
import com.unionpay.mpi.LogUtil;
import com.unionpay.mpi.MpiConfig;
import com.unionpay.mpi.MpiUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static com.ningpai.site.order.common.WXPayHelp.getMoney;
import static com.ningpai.site.order.common.WXPayHelp.getNonceStr;
import static com.ningpai.site.order.common.WXPayHelp.parseXmlToList2;

/**
 * Created by pl on 2016/2/19.
 * Desc:
 */
@Controller
public class PayByTherdController {

    /**
     * 单比订单或者多笔订单生成不同的收款单信息 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(PaySiteController.class);

    private static final String UNKNOWN = "unknown";
    private static final String ORDER_PAYBYWX = "order/payorderbywx";
    private static final String LOGGERINFO1 = "支付请求失败";

    @Resource(name = "receivablesService")
    private ReceivablesService receivablesService;

    @Resource(name = "payService")
    private PayService payService;

    @Resource(name = "SiteOrderService")
    private SiteOrderService siteOrderService;

    @Resource(name = "OrderService")
    private OrderService orderser;

    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    /***
     * ASPC 微信支付
     * zz：pl
     * @return
     */
    @RequestMapping(value = "/payorderwxbythred",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String payOrderByWX(String goodsName,String orderId, HttpServletRequest request,String orderAmount){

        WxPayDto tpWxPay1 = new WxPayDto();
        tpWxPay1.setBody(goodsName);
        tpWxPay1.setOrderId(orderId);
        tpWxPay1.setSpbillCreateIp(request.getRemoteAddr());
        //tpWxPay1.setSpbillCreateIp("192.168.15.186");
        tpWxPay1.setTotalFee(orderAmount);

        Pay p = payService.findByPayId(42l);
        String codeURL= getCodeurl(tpWxPay1,p);

        if(codeURL==null||"".equals(codeURL)){
            return "err";
        }
        return codeURL;
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
        String notify_url = p.getBackUrl()+"/paywxNotiByThred.htm";
        //String notify_url = "http://thertno.ngrok.cc/paywxNotiByThred.htm";
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
     * 微信回调函数
     * zz：pl
     */
    @RequestMapping("/paywxNotiByThred")
    public void orderStateBackByWX(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //把如下代码贴到的你的处理回调的servlet 或者.do 中即可明白回调操作
        LOGGER.error("微信支付回调数据开始");
        //示例报文
        //String notityXml = "<xml><appid>wxb0fe165dcba0d8fd</appid><mch_id>1283883301</mch_id><nonce_str>1617205358</nonce_str><sign>F5A9AEB25BF52BAC7D8DAB02A6CB0B33</sign><body><![CDATA[[0xe7][0x94][0x9f][0xe5][0x8c][0x96][0xe7][0xa7][0x91][0xe6][0x8a][0x80]]]></body><out_trade_no>10216022314000001</out_trade_no><attach></attach><total_fee>1</total_fee><spbill_create_ip>192.168.15.186</spbill_create_ip><notify_url>http://thertno.ngrok.cc/paywxNotiByThred.htm</notify_url><trade_type>NATIVE</trade_type></xml>";
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

        if("SUCCESS".equals(wpr.getResultCode())) {

            String orderId = wpr.getOutTradeNo(); //获取后台通知的数据，其他字段也可用类似方式获取
            //String respCode = valideData.get("respCode"); //获取应答码，收到后台通知了respCode的值一般是00，可以不需要根据这个应答码判断。

            ApscOrder apscOrder = new ApscOrder();

            String sql = "select * from opdd where docnum= ? ";
            List<Object> newlist = new ArrayList<>();
            newlist.add(orderId);
            List<Map<String, Object>> neworderlist = apscOrder.findModeResult(sql, newlist);
            String DocEntry = "";
            if (neworderlist.size() > 0) {
                DocEntry = neworderlist.get(0).get("DocEntry").toString();

                //String SQL = "SELECT A.*,B.ShortName FROM BCPL8 A,BCPL B WHERE A.DocEntry=B.DocEntry AND A.DatarowID = ? ";
                String SQL = "select * from bcpl8 where purchaseID= ? ";
                List<Object> list = new ArrayList<>();
                list.add(DocEntry);
                List<Map<String, Object>> orderlist = apscOrder.findModeResult(SQL, list);

                String PayStat = "";
                String DatarowID = "";//根据订单编号换内部编号
                if (orderlist.size() > 0) {
                    PayStat = orderlist.get(0).get("PayStat").toString();
                    DatarowID = orderlist.get(0).get("DatarowID").toString();
                    if (!"0".equals(PayStat)) {
                        Date currentTime = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String dateString = formatter.format(currentTime);
                        SQL = "UPDATE BCPL8 SET PayStat = 0,SubKind=1,BankID = '',OrderTime = '" + dateString + "',PayAmount = " + (Double.parseDouble(wpr.getTotalFee()) / 100) + ",DealID = '',DealTime = '" + dateString + "',Fee = " + (Double.parseDouble("0")) + " WHERE DatarowID = '" + DatarowID + "'  " + "exec [pPayOnline2] '" + DatarowID + "'";
                        if (apscOrder.updateByPreparedStatement(SQL)) {
                            //支付成功
                            resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                                    + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                        }
                    } else {
                        //支付成功
                        resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                                + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    }
                } else {
                    //支付成功
                    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                            + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                }
            }

        }

        LOGGER.error("微信支付回调数据结束");

        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }

    public static void main(String args[]) throws SQLException {
        ApscOrder apscOrder=new ApscOrder();
        String SQL = "SELECT A.*,B.ShortName FROM BCPL8 A,BCPL B WHERE A.DocEntry=B.DocEntry AND A.DatarowID = ? ";
        List<Object> list=new ArrayList<>();
        list.add("0A9E8596-9C42-46E5-B362-F5EDCD52A2DE");
        List<Map<String, Object>> orderlist = apscOrder.findModeResult(SQL, list);

        String PayStat="";
        if (orderlist.size()>0) {
            PayStat = orderlist.get(0).get("PayStat").toString();
            if (PayStat != "0") {
                Date currentTime = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = formatter.format(currentTime);
                SQL = "UPDATE BCPL8 SET PayStat = 0,SubKind=1,BankID = '',OrderTime = '" + dateString + "',PayAmount = " + (Double.parseDouble("120.000")) + ",DealID = '',DealTime = '" + dateString + "',Fee = " + (Double.parseDouble("0")) + " WHERE DatarowID = '0A9E8596-9C42-46E5-B362-F5EDCD52A2DE'  " + "exec [pPayOnline2] '0A9E8596-9C42-46E5-B362-F5EDCD52A2DE'";
                if(apscOrder.updateByPreparedStatement(SQL)){
                    String sql="1";
                }else{
                    String sql="2";
                }
            }else{
                String sql="3";
            }
        }else{
            String sql="4";
        }
    }

    /***
     * 请求银联快捷支付
     * @param orderId
     * @param request
     * @param response
     * @param orderAmount
     */
    @RequestMapping(value = "/payOrderByYL",produces="text/plain;charset=UTF-8")
    public void payOrderByYL(String orderId,HttpServletRequest request,HttpServletResponse response,String orderAmount,String returnUrl)
    {
        Pay p = payService.findByPayId(12l);
        // 字符集编码
        String encoding = ConstantUtil.UTF;
        /**
         * 初始化证书
         */
        String rootPath = request.getSession().getServletContext().getRealPath("WEB-INF/unionconfig");
        // MpiConfig.getConfig().loadPropertiesFromSrc();//
        // 从classpath加载mpi.properties文件
        // MpiConfig.getConfig().loadProperties(pro);从properties中加载
        MpiConfig.getConfig().loadPropertiesFromPath(rootPath); // 从指定目录下加载rootPAth不包含文件名 0A9E8596-9C42-46E5-B362-F5EDCD52A2DE
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
        requestData.put("orderId",orderId);             //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
        requestData.put("txnTime", UtilDate.getOrderNum());        //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
        requestData.put("currencyCode", "156");         			  //交易币种（境内商户一般是156 人民币）
        requestData.put("txnAmt", getMoney(orderAmount));  //交易金额，单位分，不要带小数点
        requestData.put("reqReserved", "buyao");        		      //请求方保留域，透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节

        //前台通知地址 （需设置为外网能访问 http https均可），支付成功后的页面 点击“返回商户”按钮的时候将异步通知报文post到该地址
        //如果想要实现过几秒中自动跳转回商户页面权限，需联系银联业务申请开通自动返回商户权限
        //异步通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
        //String returnUrl = p.getBackUrl();
        //requestData.put("frontUrl", "http://172.18.137.63:8080/ACPSample_WuTiaoZhuan/frontRcvResponse");
        requestData.put("frontUrl", returnUrl);

        //后台通知地址（需设置为【外网】能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，失败的交易银联不会发送后台通知
        //后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
        //注意:1.需设置为外网能访问，否则收不到通知    2.http https均可  3.收单后台通知后需要10秒内返回http200或302状态码
        //    4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，那么银联会间隔一段时间再次发送。总共发送5次，每次的间隔时间为0,1,2,4分钟。
        //    5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
        // 服务器异步通知页面路径
        String notifyUrl = p.getPayUrl() + "/payYLnotifysuccess.htm";
        //String notifyUrl = "http://thertno.ngrok.cc/payYLnotifysuccess.htm";
        requestData.put("backUrl", notifyUrl);
        /**
         * 签名
         */
        MpiUtil.sign(requestData, encoding);
        String html=PaySiteController.createAutoFormHtml(requestUrl, requestData,encoding);


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


    /***
     * 请求银联网银支付
     * @param orderId
     * @param request
     * @param response
     * @param orderAmount
     */
    @RequestMapping(value = "/payOrderByWY",produces="text/plain;charset=UTF-8")
    public void payOrderByWY(String orderId,HttpServletRequest request,HttpServletResponse response,String orderAmount,String returnUrl)
    {
        Pay p = payService.findByPayId(43l);
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
        requestData.put("orderId",orderId);             //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
        requestData.put("txnTime", UtilDate.getOrderNum());        //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
        requestData.put("currencyCode", "156");         			  //交易币种（境内商户一般是156 人民币）
        requestData.put("txnAmt", getMoney(orderAmount));  //交易金额，单位分，不要带小数点
        requestData.put("reqReserved", "buyao");        		      //请求方保留域，透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节

        //前台通知地址 （需设置为外网能访问 http https均可），支付成功后的页面 点击“返回商户”按钮的时候将异步通知报文post到该地址
        //如果想要实现过几秒中自动跳转回商户页面权限，需联系银联业务申请开通自动返回商户权限
        //异步通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
        //String returnUrl = p.getBackUrl();
        //requestData.put("frontUrl", "http://172.18.137.63:8080/ACPSample_WuTiaoZhuan/frontRcvResponse");
        requestData.put("frontUrl", returnUrl);

        //后台通知地址（需设置为【外网】能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，失败的交易银联不会发送后台通知
        //后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
        //注意:1.需设置为外网能访问，否则收不到通知    2.http https均可  3.收单后台通知后需要10秒内返回http200或302状态码
        //    4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，那么银联会间隔一段时间再次发送。总共发送5次，每次的间隔时间为0,1,2,4分钟。
        //    5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
        // 服务器异步通知页面路径
        String notifyUrl = p.getPayUrl() + "/payYLnotifysuccess.htm";
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
        String html=PaySiteController.createAutoFormHtml(requestUrl, requestData, encoding);


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

    /**
     * 银联支付异步通知
     *
     * @param req
     * @param resp
     */
    @RequestMapping("/payYLnotifysuccess")
    public void payYLnotifysuccess(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        LOGGER.info("银联支付异步通知接收后台通知开始");

        req.setCharacterEncoding("ISO-8859-1");
        String encoding = req.getParameter(ConstantUtil.param_encoding);
        // 获取银联通知服务器发送的后台通知参数
        Map<String, String> reqParam = PaySiteController.getAllRequestParam(req);

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

            ApscOrder apscOrder=new ApscOrder();

            String sql="select * from opdd where docnum= ? ";
            List<Object> newlist=new ArrayList<>();
            newlist.add(orderId);
            List<Map<String, Object>> neworderlist = apscOrder.findModeResult(sql, newlist);
            String DocEntry="";
            if (neworderlist.size()>0) {
                DocEntry = neworderlist.get(0).get("DocEntry").toString();

                //String SQL = "SELECT A.*,B.ShortName FROM BCPL8 A,BCPL B WHERE A.DocEntry=B.DocEntry AND A.DatarowID = ? ";
                String SQL = "select * from bcpl8 where purchaseID= ? ";
                List<Object> list=new ArrayList<>();
                list.add(DocEntry);
                List<Map<String, Object>> orderlist = apscOrder.findModeResult(SQL, list);

                String PayStat="";
                String DatarowID="";//根据订单编号换内部编号
                if (orderlist.size()>0) {
                    PayStat = orderlist.get(0).get("PayStat").toString();
                    DatarowID = orderlist.get(0).get("DatarowID").toString();
                    if (!"0".equals(PayStat)) {
                        Date currentTime = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String dateString = formatter.format(currentTime);
                        SQL = "UPDATE BCPL8 SET PayStat = 0,SubKind=1,BankID = '',OrderTime = '" + dateString + "',PayAmount = " + (Double.parseDouble(valideData.get("txnAmt"))/100) + ",DealID = '',DealTime = '" + dateString + "',Fee = " + (Double.parseDouble("0")) + " WHERE DatarowID = '" + DatarowID + "'  " + "exec [pPayOnline2] '" + DatarowID + "'";
                        if(apscOrder.updateByPreparedStatement(SQL)){
                            resp.getWriter().print("ok");
                        }
                    }else{
                        resp.getWriter().print("ok");
                    }
                }else{
                    resp.getWriter().print("ok");
                }
            }
            //返回给银联服务器http 200  状态码
            //resp.getWriter().print("ok");
        }
        LogUtil.writeLog("银联支付异步接收后台通知结束");
    }
}
