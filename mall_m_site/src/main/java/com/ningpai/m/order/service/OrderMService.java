/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.m.order.service;

import com.ningpai.m.order.bean.OrderAddress;
import com.ningpai.m.order.util.OrderContainerUtil;
import com.ningpai.m.shoppingcart.bean.ShoppingCartWareUtil;
import com.ningpai.order.bean.Order;
import com.ningpai.system.bean.Pay;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 订单支付service
 * 
 * @author NINGPAI-LIH
 * 
 */
public interface OrderMService {

    /**
     * 新流程保存订单
     * 
     * @param duiHuanJiFen
     * @param invoiceType
     * @param invoiceTitle
     * @param invoiceContent
     * @param request
     * @param shoppingCartId
     * @param typeId
     * @param orderAddress
     * @param deliveryPointId
     * @return
     * @throws UnsupportedEncodingException
     */
    @Transactional
    List<Order> newsubmitOrder(Long duiHuanJiFen, String invoiceType, String invoiceTitle,String customerRemark, String invoiceContent,HttpServletRequest request, Long[] shoppingCartId, Long typeId,
            OrderAddress orderAddress, Long deliveryPointId) throws UnsupportedEncodingException;

    /**
     * 下单方法法
     * 
     * @param shoppingCartId
     *            商品购物车IDS
     * @param codeNo
     *            使用的优惠券
     * @param custAddress
     *            地址ID
     * @param chPay
     *            支付方式Id
     * @param chExpress
     *            配送方式ID
     * @param typeId
     * @param orderAddress
     * @param duiHuanJiFen
     *            兑换积分
     * @return Map
     * @throws UnsupportedEncodingException
     */
    List<Order> submitOrder(Long duiHuanJiFen, String invoiceType, String invoiceTitle, HttpServletRequest request, Long[] shoppingCartId, String codeNo, Long custAddress,
            Long chPay, Long chExpress, Long[] marketingId, Long[] thirdId, ShoppingCartWareUtil cartWareUtil, String customerRemark, Long typeId, OrderAddress orderAddress,
            Long deliveryPointId) throws UnsupportedEncodingException;

    /**
     * 确认支付
     * 
     * @param orderId
     * @return orderId
     */
    int payOrder(Long orderId);

    /**
     * 查询订单信息
     * 
     * @param orderId
     * @return Order
     */
    Order getPayOrder(Long orderId);

    /**
     * 查询订单根据COde
     * 
     * @param orderCode
     * @return Order
     */
    Order getPayOrderByCode(String orderCode);

    /**
     * 查询订单包裹表
     * 
     * @param orderId
     *            订单id
     * @return 订单所属包裹的运单号
     */
    List<OrderContainerUtil> getExpressNo(Long orderId);

    /**
     * 微信支付工具类
     * 
     * @param request
     * @param response
     * @param order
     * @param pay
     * @param goodsName
     * @return
     */
    Map<String, Object> getWXUrl(String openid,HttpServletRequest request, HttpServletResponse response, Order order, Pay pay, String goodsName);
}
