/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.groupon.service;

import com.ningpai.util.PageBean;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 团购功能接口
 * 
 * @author NINGPAI-LIH
 * @since 2014年12月15日12:54:55
 * 
 */
public interface GrouponService {
    /**
     * 团购订单提交
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
    public String subGrouponOrder(HttpServletRequest request, Long custAddress, String chInvoiceTitle, String chInvoiceType, String chInvoiceContent, Long productId,
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
    String payGrouponOrder(String orderCode, Long payId);

    /**
     * 增加团购参与人数
     * 
     * @param marketId
     *            促销id
     * @return 返回结果
     */
    int addGroupCount(Long marketId);

    /**
     * 获取订单集合
     * 
     * @param paramMap
     * @param pb
     * @return
     */
    PageBean selectOrderList(Map<String, Object> paramMap, PageBean pb);
}
