/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.marketingrush.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 抢购功能接口
 * 
 * @author jiping
 * @since 2015年1月19日 下午5:31:46
 * @version 0.0.1
 */
public interface MarketingRushService {
    /**
     * 抢购订单提交
     * 
     * @param request
     *            请求
     * @param custAddress
     *            收货地址
     * @param chInvoiceTitle
     *            发票抬头
     * @param chInvoiceType
     *            发票抬头
     * @param chInvoiceContent
     *            发票内容
     * @param productId
     *            商品id
     * @param productNum
     *            购买的商品数量
     * @return 订单号
     */
    public String subMarketingRushOrder(HttpServletRequest request, Long custAddress, String chInvoiceTitle, String chInvoiceType, String chInvoiceContent, Long productId,
            Long productNum);

    /**
     * 获取支付宝付款请求
     * 
     * @param orderCode
     *            订单编号
     * @param payId
     *            支付id
     * @return 支付请求
     */
    String payMarketingRushOrder(String orderCode, Long payId);
}
