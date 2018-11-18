/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.index.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.common.util.alipay.config.AlipayConfig;
import com.ningpai.common.util.alipay.util.AlipaySubmit;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.order.bean.Order;
import com.ningpai.order.service.BackOrderService;
import com.ningpai.order.service.BarterService;
import com.ningpai.order.service.OrderService;
import com.ningpai.publicpackage.InformationPublic;
import com.ningpai.system.bean.Pay;
import com.ningpai.system.service.PayService;
import com.ningpai.third.auth.bean.CustomerConsume;
import com.ningpai.third.auth.service.CustomerConsumeService;
import com.ningpai.third.goods.service.ThirdGoodsService;
import com.ningpai.third.order.service.ThirdOrderService;
import com.ningpai.third.seller.bean.StoreInfo;
import com.ningpai.third.seller.service.SellerService;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.third.util.SellerConstants;
import com.ningpai.util.MyLogger;
import com.ningpai.util.UtilDate;

/**
 * <p>
 * 第三方登录Controller
 * </p>
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年5月4日 下午2:50:16
 * @version 2.0
 */
@Controller
public class IndexController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(IndexController.class);

    private static final String OUT_TRADE_NO = "out_trade_no";
    private static final String TRADE_STATUS = "trade_status";
    private static final String TRADE_SUCCESS_D = "TRADE_SUCCESS";

    // 资讯文章接口
    private InformationPublic informationPublic;
    // 商家信息Service接口
    private SellerService sellerService;
    // 促销serivce接口
    private MarketingService marketingService;
    // 商家订单 接口
    private OrderService orderService;
    // 商家商品接口
    private ThirdGoodsService goodsService;
    // 第三方订单接口
    private ThirdOrderService thirdOrderService;
    // 退单接口
    private BackOrderService backOrderService;
    // 换单业务层接口
    private BarterService barterService;
    // 货品信息
    private GoodsProductService goodsProductService;
    // 会员消费记录
    private CustomerConsumeService customerConsumeService;
    // 会员服务处理接口
    private CustomerServiceMapper customerServiceMapper;

    @Resource(name = "payService")
    private PayService payService;

    /**
     * 跳转首页
     * 
     * @param request
     * @param n
     *            导航栏头部索引
     * @param l
     *            导航栏左侧索引
     * @return
     */
    @RequestMapping("/third/index")
    public ModelAndView index(HttpServletRequest request, String n, String l) {
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 商家id
        Long storeId = (Long) request.getSession().getAttribute(SellerConstants.THIRDID);
        StoreInfo storeInfo = null;
        try {
            // 根据ID查询 单个的店铺信息
            storeInfo = sellerService.selectByStoreId(storeId);
            resultMap.put("yesOrder",
            // 根据第三方ID和标记查询订单数量和订单的总金额
                    thirdOrderService.querySalesOrderCountByFlag(0, storeInfo.getStoreId()));
            resultMap.put("toOrder",
            // 根据第三方ID和标记查询订单数量和订单的总金额
                    thirdOrderService.querySalesOrderCountByFlag(1, storeInfo.getStoreId()));
            return new ModelAndView("index/index").addAllObjects(resultMap);
        } finally {
            resultMap = null;
        }
    }

    /**
     * 中专
     * 
     * @return
     */
    @RequestMapping("/jumpindex")
    public ModelAndView jumpIndex() {
        return new ModelAndView(new RedirectView("index"));
    }

    public InformationPublic getInformationPublic() {
        return informationPublic;
    }

    @Resource(name = "InformationPublic")
    public void setInformationPublic(InformationPublic informationPublic) {
        this.informationPublic = informationPublic;
    }

    public SellerService getSellerService() {
        return sellerService;
    }

    @Resource(name = "sellerService")
    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    public MarketingService getMarketingService() {
        return marketingService;
    }

    @Resource(name = "MarketingService")
    public void setMarketingService(MarketingService marketingService) {
        this.marketingService = marketingService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    @Resource(name = "OrderService")
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public ThirdGoodsService getGoodsService() {
        return goodsService;
    }

    @Resource(name = "ThirdGoodsService")
    public void setGoodsService(ThirdGoodsService goodsService) {
        this.goodsService = goodsService;
    }

    public ThirdOrderService getThirdOrderService() {
        return thirdOrderService;
    }

    @Resource(name = "ThirdOrderService")
    public void setThirdOrderService(ThirdOrderService thirdOrderService) {
        this.thirdOrderService = thirdOrderService;
    }

    public BackOrderService getBackOrderService() {
        return backOrderService;
    }

    @Resource(name = "BackOrderService")
    public void setBackOrderService(BackOrderService backOrderService) {
        this.backOrderService = backOrderService;
    }

    public BarterService getBarterService() {
        return barterService;
    }

    @Resource(name = "barterService")
    public void setBarterService(BarterService barterService) {
        this.barterService = barterService;
    }

    public GoodsProductService getGoodsProductService() {
        return goodsProductService;
    }

    @Resource(name = "GoodsProductService")
    public void setGoodsProductService(GoodsProductService goodsProductService) {
        this.goodsProductService = goodsProductService;
    }

    public CustomerConsumeService getCustomerConsumeService() {
        return customerConsumeService;
    }

    @Resource(name = "customerConsumeService")
    public void setCustomerConsumeService(CustomerConsumeService customerConsumeService) {
        this.customerConsumeService = customerConsumeService;
    }

    public CustomerServiceMapper getCustomerServiceMapper() {
        return customerServiceMapper;
    }

    @Resource(name = "customerServiceMapper")
    public void setCustomerServiceMapper(CustomerServiceMapper customerServiceMapper) {
        this.customerServiceMapper = customerServiceMapper;
    }

    /**
     * 跳转到充值页面
     * 
     * @param request
     * @return
     */
    @RequestMapping("/jumpRecharge")
    public ModelAndView jumpRecharge(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 商家ID
        Long storeId = (Long) request.getSession().getAttribute(SellerConstants.THIRDID);
        StoreInfo storeInfo = null;
        try {
            // 商家信息
            storeInfo = sellerService.selectByStoreId(storeId);
            // 查询所有的支付方式
            resultMap.put("paylist", payService.queryAllPaySet());
            // 装载店铺信息
            resultMap.put("seller", storeInfo);
            // 跳转到充值页面
            return new ModelAndView("index/recharge").addAllObjects(resultMap);
        } finally {
            resultMap = null;
        }
    }

    /**
     * 跳转到支付宝充值页面
     * 
     * @param request
     * @param payId
     *            支付类型ID
     * @param price
     *            价格
     * @param response
     * @param customerId
     *            会员id
     * @param storeId
     *            店铺ID
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/recharge")
    public void recharge(HttpServletRequest request, Long payId, BigDecimal price, HttpServletResponse response, Long customerId, Long storeId) throws UnsupportedEncodingException {
        /* 生成充值订单 */
        Order order = new Order();
        // 订单编号
        String orderCode = UtilDate.mathString(new Date());
        // 设置订单 单号
        order.setOrderCode(orderCode);
        // 支付时间
        String orderOldCode = UtilDate.mathString(new Date());
        // 旧的订单 单号
        order.setOrderOldCode(orderOldCode);
        // 订单总金额
        order.setOrderPrice(price);
        // 之前订单的价格
        order.setOrderOldPrice(price);
        // 设置订单价格
        order.setOrderPrePrice(BigDecimal.valueOf(0));
        // 订单状态为未支付
        order.setOrderStatus("0");
        // customerId
        order.setCustomerId(customerId);
        // 支付时间
        order.setPayTime(new Date());
        order.setDelFlag("0");
        // 支付类型ID
        order.setPayId(payId);
        // 商家ID
        order.setBusinessId(storeId);
        order.setDealerType("0");
        // 创建时间
        order.setCreateTime(new Date());
        order.setOrderMType("0");
        // 在线支付
        order.setOrderLinePay("1");
        // // 判断是否是手机订单 0：PC订单 1：手机订单 2：微信订单
        order.setExpressPrice(BigDecimal.valueOf(0));

        // 查询使用的支付信息
        Pay p = payService.findByPayId(payId);
        if (p != null) {
            // 客户身份
            AlipayConfig.partner = p.getApiKey();
            // secret_key
            AlipayConfig.key = p.getSecretKey();
            // 支付类型
            String paymentType = "1";
            // 必填，不能修改
            // 服务器异步通知页面路径
            String notifyUrl = p.getPayUrl() + "third/thirdPaynotifysuccess.html";
            // 需http://格式的完整路径，不能加?id=123这类自定义参数
            // 页面跳转同步通知页面路径
            String returnUrl = p.getBackUrl();
            // 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
            // 卖家支付宝帐户
            String sellerEmail = p.getPayAccount();
            // 必填
            // 商户订单号
            String outTradeNo = orderCode;
            // 商户网站订单系统中唯一订单号，必填
            // 订单名称
            String subject = "第三方充值";
            // 必填
            // 付款金额
            String totalFee = price + "";
            // 商品展示地址
            String showUrl = "";
            // 需以http://开头的完整路径，例如：http://localhost/myorder.html
            // 防钓鱼时间戳
            String antiPhishingKey = "";
            // 若要使用请调用类文件submit中的query_timestamp函数
            // 客户端的IP地址
            String exterInvokeIp = "";
            // 非局域网的外网IP地址，如：221.0.0.1
            Map<String, String> sParaTemp = new HashMap<String, String>();
            sParaTemp.put("service", "create_direct_pay_by_user");
            sParaTemp.put("partner", AlipayConfig.partner);
            sParaTemp.put("_input_charset", AlipayConfig.input_charset);
            sParaTemp.put("payment_type", paymentType);
            sParaTemp.put("notify_url", notifyUrl);
            sParaTemp.put("return_url", returnUrl);
            sParaTemp.put("seller_email", sellerEmail);
            sParaTemp.put(OUT_TRADE_NO, outTradeNo);
            sParaTemp.put("subject", subject);
            sParaTemp.put("total_fee", totalFee);
            sParaTemp.put("body", subject);
            sParaTemp.put("show_url", showUrl);
            sParaTemp.put("anti_phishing_key", antiPhishingKey);
            sParaTemp.put("exter_invoke_ip", exterInvokeIp);

            // 建立请求
            String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "post", "确认");
            response.setContentType("text/html;charsetUTF-8");
            response.setCharacterEncoding(ConstantUtil.UTF);

            try {
                response.getWriter().write(sHtmlText);
                LOGGER.info("给订单号为：【" + orderCode + "】的订单付款成功");
                // 添加订单
                orderService.insertOrder(order);
            } catch (IOException e) {
                LOGGER.error("支付请求失败" + e);
            }
        }
    }

    /**
     * 前台通知
     * 
     * @param req
     * @param resp
     */
    @RequestMapping("/thirdPaysuccess")
    public ModelAndView unionPaySuccess(HttpServletRequest req, HttpServletResponse resp) {

        try {
            // 中文转码
            req.setCharacterEncoding("ISO-8859-1");
        } catch (UnsupportedEncodingException e1) {
            LOGGER.error("支付前台通知错误" + e1);
        }
        // 获得请求参数集合
        Map<String, String> respParam = getAllRequestParam(req);
        if (TRADE_SUCCESS_D.equals(respParam.get(TRADE_STATUS))) {
            // 交易成功
            if (TRADE_SUCCESS_D.equals(respParam.get(TRADE_STATUS))) {
                // 交易成功
                // 订单编号 orderCode
                String orderCode = respParam.get(OUT_TRADE_NO);
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("orderCode", orderCode);
                // 设置重定向的路径
                return new ModelAndView("order/thirdpaysuccess");
            } else {
                try {
                    resp.getWriter().write("交易失败");
                } catch (IOException e) {
                    LOGGER.error("支付回调失败" + e);
                }
                return null;
            }
        }
        return null;
    }

    /**
     * 后台通知
     * 
     * @param req
     * @param resp
     */
    @RequestMapping("/thirdPaynotifysuccess")
    public void unionpayNotifySuccess(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 中文转码
            req.setCharacterEncoding("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("支付回调，字符转换错误" + e);
        }
        // 获得请求参数集合
        Map<String, String> respParam = getAllRequestParam(req);
        if (TRADE_SUCCESS_D.equals(respParam.get(TRADE_STATUS))) {
            // 交易成功
            // 订单编号 orderCode
            String orderCode = respParam.get(OUT_TRADE_NO);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            // 订单单号
            paramMap.put("orderCode", orderCode);
            // 根据参数查询订单列表
            Order order = orderService.selectByParam(paramMap).get(0);
            // 金额
            String price = respParam.get("total_fee");
            CustomerConsume record = new CustomerConsume();
            // 金额
            record.setBalanceNum(BigDecimal.valueOf(Long.valueOf(price)));
            // 备注
            record.setBalanceRemark("第三方充值");
            // 类型
            record.setBalanceType("2");
            // 创建时间
            record.setCreateTime(new Date());
            // 会员id
            record.setCustomerId(order.getCustomerId());
            // 会员消费记录状态
            record.setDelFlag("0");
            // 订单单号
            record.setOrderNo(orderCode);
            // //支付类型
            record.setPayType("0");
            // //充值人类型 0会员 ，1第三方商家，3供应商
            record.setIsSeller("1");
            // 添加充值记录
            customerConsumeService.saveConsume(record);
            // 更新订单状态
            order.setOrderStatus("11");
            // 修改订单信息
            thirdOrderService.updateThirdOrderByParam(order);
        }
    }

    /**
     * 获取请求参数中所有的信息
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
            }
        }
        return res;
    }

}
