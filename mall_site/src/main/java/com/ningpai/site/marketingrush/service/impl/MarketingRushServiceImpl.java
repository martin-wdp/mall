/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.marketingrush.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ningpai.common.util.alipay.config.AlipayConfig;
import com.ningpai.common.util.alipay.util.AlipaySubmit;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.marketing.bean.Marketing;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.order.bean.OrderVice;
import com.ningpai.order.service.OrderViceService;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.goods.bean.GoodsDetailBean;
import com.ningpai.site.goods.service.GoodsProductService;
import com.ningpai.site.goods.vo.GoodsProductVo;
import com.ningpai.site.marketingrush.service.MarketingRushService;
import com.ningpai.system.bean.Pay;
import com.ningpai.system.service.PayService;
import com.ningpai.util.UtilDate;

/**
 * 抢购功能实现类
 * 
 * @author jiping
 * @since 2015年1月20日 下午1:58:46
 * @version 0.0.1
 */
@Service("MarketingRushService")
public class MarketingRushServiceImpl implements MarketingRushService {

    // 付款方式
    @Resource(name = "payService")
    private PayService payService;

    // 团购订单
    @Resource(name = "OrderViceService")
    private OrderViceService orderViceService;

    // 商品
    @Resource(name = "HsiteGoodsProductService")
    private GoodsProductService goodsProductService;

    // 促销
    @Resource(name = "MarketingService")
    private MarketingService marketingService;

    // 会员
    @Resource(name = "customerServiceSite")
    private CustomerServiceInterface customerServiceInterface;

    /*
     * 
     * @see
     * com.ningpai.site.marketingrush.service.MarketingRushService#subGrouponOrder
     * (javax.servlet.http.HttpServletRequest, java.lang.Long, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.Long, java.lang.Long)
     */
    @Override
    public String subMarketingRushOrder(HttpServletRequest request, Long custAddress, String chInvoiceTitle, String chInvoiceType, String chInvoiceContent, Long productId,
            Long productNum) {
        String vip = "0";
        Object obj = request.getSession().getAttribute("vip");
        if(null != obj){
            vip = (String)obj;
        }

        // 地址
        Long distinctId = Long.parseLong(request.getSession().getAttribute("distinctId").toString());
        // 查询货品信息
        GoodsDetailBean detailBean = goodsProductService.queryDetailBeanByProductId(productId, distinctId);
        Marketing marketing = marketingService.selectRushMarket(productId, 5L, detailBean.getProductVo().getGoods().getCatId(), detailBean.getBrand().getBrandId());
        // 计算订单价格

        /*BigDecimal price = detailBean.getProductVo().getGoodsInfoPreferPrice().multiply(marketing.getRushs().get(0).getRushDiscount());*/
        //2015-12-09 wuyanbo 修改抢购价计算方式
        BigDecimal price = marketing.getRushs().get(0).getRushPrice();
        if("1".equals(vip)){
            price = marketing.getRushs().get(0).getRushVipPrice();
        }
        if (productNum > detailBean.getProductVo().getGoodsInfoStock()) {
            return null;
        }
        // 总价格
        BigDecimal sumPrice = price.multiply(new BigDecimal(productNum));
        // 抢购订单生成
        OrderVice orderVice = new OrderVice();
        String orderCode = UtilDate.mathString(new Date());
        while (orderViceService.existOrderCode(orderCode) > 0) {
            orderCode = UtilDate.mathString(new Date());
        }
        orderVice.setOrderCode(orderCode);
        orderVice.setBusinessId(detailBean.getProductVo().getThirdId());
        orderVice.setCreateTime(new Date());
        orderVice.setCustomerId((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
        orderVice.setDelFlag("0");
        orderVice.setGoodsInfoId(productId);
        orderVice.setGoodsNum(productNum);
        orderVice.setOrderPrice(sumPrice);
        orderVice.setOrderPrePrice(new BigDecimal(0));
        orderVice.setStoreName(detailBean.getProductVo().getThirdName());
        orderVice.setOrderStatus("0");
        orderVice.setOrderType("1");
        // 发票内容
        orderVice.setTemp1(chInvoiceTitle);
        orderVice.setTemp2(chInvoiceType);
        orderVice.setTemp3(chInvoiceContent);
        orderVice.setMarketingId(marketing.getMarketingId());
        // 收货地址
        // 收货地址
        CustomerAddress ca = customerServiceInterface.queryCustAddress(custAddress);
        orderVice.setShippingMobile(ca.getAddressMoblie());
        orderVice.setShippingPerson(ca.getAddressName());
        orderVice.setShippingAddress(ca.getProvince().getProvinceName() + "省" + ca.getCity().getCityName() + "市" + ca.getDistrict().getDistrictName());
        orderVice.setShippingAddressDetail(ca.getAddressDetail());
        orderVice.setDistrictId(distinctId);
        orderViceService.insertOrder(orderVice);
        return orderCode;
    }

    /*
     * 
     * @see
     * com.ningpai.site.marketingrush.service.MarketingRushService#payGrouponOrder
     * (java.lang.String, java.lang.Long)
     */
    @Override
    public String payMarketingRushOrder(String orderCode, Long payId) {
        String sHtmlText = "";
        // 查询使用的支付信息
        Pay p = payService.findByPayId(payId);
        if (p != null && "1".equals(p.getPayType())) {
                OrderVice order = orderViceService.payOrder(orderCode);

                AlipayConfig.partner = p.getApiKey();
                AlipayConfig.key = p.getSecretKey();
                // 支付类型
                String paymentType = "1";
                // 必填，不能修改
                // 服务器异步通知页面路径
                String notifyUrl = p.getPayUrl() + "order/paygrouonusccessyb.html";
                // 需http://格式的完整路径，不能加?id=123这类自定义参数
                // 页面跳转同步通知页面路径
                String returnUrl = p.getPayUrl() + "order/paygrouonusccess.html";
                // 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
                // 卖家支付宝帐户
                String sellerEmail = p.getPayAccount();
                // 必填
                // 商户订单号
                String outTradeNo = order.getOrderCode();
                // 商户网站订单系统中唯一订单号，必填
                // 订单名称
                GoodsProductVo goodsProduct = goodsProductService.queryProductByProductId(order.getGoodsInfoId());
                String subject = goodsProduct.getProductName();
                // 必填
                // 付款金额
                String totalFee = order.getOrderPrice().toString();
                // 订单描述
                String body = "网购订单";
                // 商品展示地址
                String showUrl = new String();
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
                sParaTemp.put("out_trade_no", outTradeNo);
                sParaTemp.put("subject", subject);
                sParaTemp.put("total_fee", totalFee);
                sParaTemp.put("body", body);
                sParaTemp.put("show_url", showUrl);
                sParaTemp.put("anti_phishing_key", antiPhishingKey);
                sParaTemp.put("exter_invoke_ip", exterInvokeIp);
                // 建立请求
                sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
        }

        return sHtmlText;

    }

}
