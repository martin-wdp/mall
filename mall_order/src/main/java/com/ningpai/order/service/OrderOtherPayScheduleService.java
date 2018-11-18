/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.service;

import java.util.List;

import com.ningpai.order.bean.OrderOtherPay;
import com.ningpai.order.bean.OrderOtherPaySchedule;

/**
 * 多人代付service
 * 
 * @author NINGPAI-LIH
 * 
 */
public interface OrderOtherPayScheduleService {

    /**
     * 根据订单编号查询代付信息
     * 
     * @param orderCode
     * @return
     */
    OrderOtherPaySchedule selectOrderOtherPayScheduleByOrderCode(
            String orderCode);

    /**
     * 插入信息
     * 
     * @param otherPaySchedule
     * @return
     */
    int insertOrderOtherPaySchedule(OrderOtherPaySchedule otherPaySchedule);

    /**
     * 根据订单编号修改代付信息
     * 
     * @param otherPaySchedule
     * @return
     */
    int updateOrderOtherPaySchedule(OrderOtherPaySchedule otherPaySchedule);

    /**
     * 代付付款
     * 
     * @param otherPaySchedule
     * @param otherPay
     * @return
     */
    int payOther(OrderOtherPaySchedule otherPaySchedule, OrderOtherPay otherPay);

    /**
     * 返回需要退款的多人代付信息
     * 
     * @return
     */
    List<OrderOtherPaySchedule> queryOrderOtherPayRefund();

}
