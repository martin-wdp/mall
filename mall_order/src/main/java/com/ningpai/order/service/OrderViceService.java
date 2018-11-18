/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.service;

import com.ningpai.order.bean.OrderVice;
import com.ningpai.util.PageBean;

/**
 * 团购抢购订单表
 * 
 * @author NINGPAI-LIH
 * @since 2014年12月4日09:49:55
 * 
 */
public interface OrderViceService {
    /**
     * 抢购团购订单列表
     * 
     * @param orderVice
     *            查询参赛
     * @param pageBean
     *            分页参数
     * @return
     */
    PageBean searchOrderList(OrderVice orderVice, PageBean pageBean);

    /**
     * 订单详情
     * 
     * @param orderId
     *            主键id
     * @return 订单详情
     */
    OrderVice selectDetails(Long orderId);

    /**
     * 插入订单
     * 
     * @param orderVice
     *            订单信息
     * @return 插入结果
     */
    int insertOrder(OrderVice orderVice);

    /**
     * 根据订单编号查询订单信息
     * 
     * @param orderCode
     *            订单编号
     * @return 订单信息
     */
    OrderVice payOrder(String orderCode);

    /**
     * 查询订单号是否存在
     * 
     * @param orderCode
     *            订单编号
     * @return 订单信息
     */
    int existOrderCode(String orderCode);

    /**
     * 修改订单信息
     * 
     * @param orderVice
     *            要修改的信息
     * @return 修改结果
     */
    int updateOrderVice(OrderVice orderVice);

    /**
     * 根据id修改订单信息
     * 
     * @param orderId
     * @return
     */
    int updateOrderViceByOrderId(Long orderId);
}
