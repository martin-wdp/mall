/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.pub;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.order.bean.Order;
import com.ningpai.order.bean.OrderExpress;
import com.ningpai.order.service.OrderService;
import com.ningpai.util.PageBean;

/**
 * @author ggn
 * 
 */
@Service("/PubOrderUtil")
public class PubOrderUtil {

    private OrderService orderService;

    /**
     * 查询订单列表
     * 
     * @param order
     *            {@link com.ningpai.order.bean.Order} 订单实体类 实体里面设置第三方businessId就可以查询第三方下的
     * @param pageBean
     *            {@link com.ningpai.util.PageBean} 页面 参数
     * @return PageBean
     */

    public PageBean searchOrderList(Order order, PageBean pageBean) {
        return orderService.searchOrderList(order, pageBean);
    }

    /**
     * 查询订单详细
     * 
     * @param orderId
     *            {@link Long} 订单ID
     * @return Order
     */
    public Order orderDetail(Long orderId) {
        return orderService.orderDetail(orderId);
    }

    /**
     * 查询物流信息
     * 
     * @param orderId
     *            {@link Long} 订单ID
     * @return OrderExpress
     */
    public OrderExpress expressDetail(Long orderId) {

        return orderService.expressDetail(orderId);
    }

    /**
     * 插入订单
     * 
     * @param order
     *            订单实体 可设置第三方订单
     * @return int
     */
    public int insertOrder(Order order) {
        return orderService.insertOrder(order);
    }

    /**
     * 查询刚刚插入的订单ID
     * 
     * @return Long
     */
    public Long selectLastId() {
        return orderService.selectLastId();
    }

    /**
     * 查询订单根据COde
     * 
     * @param orderCode
     *            订单编号
     * @return Order
     */
    public Order getPayOrderByCode(String orderCode) {

        return orderService.getPayOrderByCode(orderCode);
    }

    public OrderService getOrderService() {
        return orderService;
    }

    @Resource(name = "OrderService")
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

}
