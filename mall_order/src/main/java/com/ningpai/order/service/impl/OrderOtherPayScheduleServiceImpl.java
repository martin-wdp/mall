/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.order.bean.Order;
import com.ningpai.order.bean.OrderOtherPay;
import com.ningpai.order.bean.OrderOtherPaySchedule;
import com.ningpai.order.dao.OrderOtherPayScheduleMapper;
import com.ningpai.order.service.OrderOtherPayScheduleService;
import com.ningpai.order.service.OrderService;

/**
 * 订单支付信息
 * 
 * @author ggn
 *
 */
@Service("OrderOtherPayScheduleService")
public class OrderOtherPayScheduleServiceImpl implements OrderOtherPayScheduleService {

    @Resource(name = "OrderOtherPayScheduleMapper")
    private OrderOtherPayScheduleMapper mapper;

    @Resource(name = "OrderService")
    private OrderService orderService;

    /**
     * 返回需要退款的多人代付信息
     * 
     * @return
     */
    @Override
    public List<OrderOtherPaySchedule> queryOrderOtherPayRefund() {
        return mapper.queryOrderOtherPayRefund();
    }

    /**
     * 根据订单编号查询代付信息
     * 
     * @param orderCode
     * @return
     */
    public OrderOtherPaySchedule selectOrderOtherPayScheduleByOrderCode(String orderCode) {
        return mapper.selectByPrimaryKey(orderCode);
    }

    /**
     * 插入信息
     * 
     * @param otherPaySchedule
     * @return
     */
    public int insertOrderOtherPaySchedule(OrderOtherPaySchedule otherPaySchedule) {
        return mapper.insertSelective(otherPaySchedule);
    }

    /**
     * 根据订单编号修改代付信息
     * 
     * @param otherPaySchedule
     * @return
     */
    public int updateOrderOtherPaySchedule(OrderOtherPaySchedule otherPaySchedule) {
        return mapper.updateByPrimaryKeySelective(otherPaySchedule);
    }

    /**
     * 代付付款
     * 
     * @param otherPaySchedule
     * @param otherPay
     * @return
     */
    public int payOther(OrderOtherPaySchedule otherPaySchedule, OrderOtherPay otherPay) {

        // 代付剩余金额
        BigDecimal orderResiduePrice = otherPaySchedule.getOrderResiduePrice();
        // 代付金额
        BigDecimal orderPayPrice = otherPay.getOrderPayPrice();
        otherPaySchedule.setOrderResiduePrice(orderPayPrice);
        mapper.payOther(otherPaySchedule);
        if ((orderResiduePrice.subtract(orderPayPrice)).signum() <= 0) {
            Order order = orderService.getPayOrderByCode(otherPaySchedule.getOrderCode());
            orderService.payOrder(order.getOrderId());
            otherPaySchedule.setOrderPayStatus("3");
            mapper.updateByPrimaryKeySelective(otherPaySchedule);
        }
        return 0;
    }

}
