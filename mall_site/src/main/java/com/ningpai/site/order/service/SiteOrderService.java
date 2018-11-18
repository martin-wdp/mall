/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.site.order.service;

import com.ningpai.order.bean.Order;
import com.ningpai.site.customer.util.OrderContainerUtil;
import com.ningpai.site.shoppingcart.bean.ShoppingCart;
import com.ningpai.site.shoppingcart.bean.ShoppingCartWareUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author ggn
 * 
 */
public interface SiteOrderService {
    /**
     * 删除单个退单信息
     * 
     * @param backOrderId
     *            退单Id
     * @param customerId
     *            当前会员ID
     * @return
     */
    int deleteBackOrderById(Long backOrderId, Long customerId);

    /**
     * 得到优惠金额,总金额,
     * 
     * @param shopCartIds
     * @param custAddress
     * @return
     */
    Map<String, Object> getEveryparamMap(HttpServletRequest requests, Long[] shopCartIds, Long custAddress);

    /**
     * 得到各个商家的金额
     *
     * @param businessId
     * @param shopdata
     * @param custAddress
     * @param vip           1表示企业会员 0表示一般会员 2015.11.02 wuyanbo add
     * @return
     */
    Map<String, Object> getEveryThirdPriceMap(Long businessId, List<ShoppingCart> shopdata, Long custAddress, String vip);

    /**
     * 新订单提交方法
     * 
     * @param request
     * @param shoppingCartId
     * @param customerRemark
     * @param chInvoiceTitle
     * @param chInvoiceContent
     * @return
     * @throws IOException
     */
    List<Order> newsubmitOrder(HttpServletRequest request, Long[] shoppingCartId, String customerRemark, String chInvoiceTitle, String chInvoiceContent) throws IOException;

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
     * @return Map
     * @throws IOException
     */
    List<Order> submitOrder(HttpServletRequest request, Long[] shoppingCartId, String codeNo, Long custAddress, Long chPay, Long chExpress, String chVoinceType,
            String chInvoiceTitle, String chInvoiceContent, Long[] marketingId, Long[] thirdId, ShoppingCartWareUtil cartWareUtil, String customerRemark, Long duihuanjifen,
            BigDecimal result, String vip) throws IOException;

    /**
     * 确认支付
     * 
     * @param orderId
     * @return orderId
     */
    int payOrder(Long orderId);
    /**
     * 已提交至银行等待处理
     *
     * @param orderId
     * @return orderId
     */
    int subBankPayOrder(Long orderId);

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
     * 根据订单编号订单下所有商品并随机返回一个
     * 
     * @param status
     *            当前已经选中的索引位置
     * @return
     */
    Map<String, Object> queryGoodsProduceByOrderId(Long orderId, Long status);

    /**
     * 根据总单编号查询订单信息
     * 
     * @param orderOldCode
     * @return
     */
    List<Order> getPayOrderByOldCode(String orderOldCode);

    /**
     * 查询收货地区仓库是否有库存
     * 
     * @param request
     * @param shoppingCartId
     * @return
     */
    Map<String, Object> checkProduct(HttpServletRequest request, Long[] shoppingCartId, Long distinctId);

    /**
     * 定时任务 发货后自动确认收货
     */
    void receiptConfirmation();
}
