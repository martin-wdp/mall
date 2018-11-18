/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.order.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.ningpai.order.bean.Order;
import com.ningpai.util.DaysOrderUtil;
import com.ningpai.util.PageBean;

/**
 * <p>第三方订单servcie</p>
 * @author NINGPAI-LIH
 * @since 2014年5月19日17:39:01
 * @version 2.0
 */
public interface ThirdOrderService {
    /**
     * 分页查询第三方所有的订单
     * 
     * @param order
     *            {@link com.ningpai.order.bean.Order}
     * @return 将查询到的集合封装进去的分页辅助Bean {@link com.ningpai.util.PageBean}
     */
    PageBean searchOrderList(PageBean pb, Order order);

    /**
     * 根据id查询订单
     * 
     * @param orderId
     *            要查询的订单id
     * @return 根据id返回的订单实体类
     */
    Order searcharOrderByParam(Long orderId);

    /**
     * 修改订单信息
     * 
     * @param order
     *            要修改的订单信息
     */
    void updateThirdOrderByParam(Order order);

    /**
     * 修改第三方订单信息（加thirdId）
     *
     * @param order
     *            要修改的订单信息
     */
    void updateThirdOrder(Order order);

    /**
     * 批量修改订单状态为
     * 
     * @param orderId
     *            要修改的订单信息
     */
    void updateThirdOrderByParams(Long[] orderId);

    /**
     * 根据订单状态和第三方ID查询订单数量
     * 
     * @param status
     *            订单状态
     * @param thirdId
     *            第三方ID
     * @return 查询到的行数
     */
    int queryOrderCountByStaAndThirdId(String status, Long thirdId);

    /**
     * 根据第三方ID和标记查询订单数量和订单的总金额
     * 
     * @param flag
     *            标记 0:前一天 1:当前天 {@link Integer}
     * @param thirdId
     *            第三方ID {@link Long}
     * @return 查询到的辅助Bean的集合 {@link DaysOrderUtil}
     */
    DaysOrderUtil querySalesOrderCountByFlag(int flag, Long thirdId);

    /**
     * 查询出库列表
     * 
     * @param pb
     *            分页参数
     * @param order
     *            订单查询参数
     * @return 分页参数
     */
    PageBean searchOrderListByOrderCargo(PageBean pb, Order order);

    /**
     * 拣货准备
     * 
     * @param orderId
     *            订单Id
     * @param request
     * @return map
     */
    public Map<String, Object> orderPicking(Long[] orderId, HttpServletRequest request);

    /**
     * 捡货
     * 
     * @param orderId 需要拣货的订单ID
     * @param request
     * @return 拣货结果
     */
    public int goOrderPicking(Long[] orderId, HttpServletRequest request);
    /**
     * 根据订单状态和customerID查询订单数量
     * 
     * @param status
     *            订单状态
     * @param customerId
     *            第三方ID
     * @return 查询到的行数
     */
    int queryOrderCountByStaAndCustomerId(String status, Long customerId);
    
    /**
     * 分页查询第三方所有的订单
     * wangtanpeng
     * @param order
     *            {@link com.ningpai.order.bean.Order}
     * @return 将查询到的集合封装进去的分页辅助Bean {@link com.ningpai.util.PageBean}
     */
    PageBean searchBuyOrderList(PageBean pb, Order order);
}
