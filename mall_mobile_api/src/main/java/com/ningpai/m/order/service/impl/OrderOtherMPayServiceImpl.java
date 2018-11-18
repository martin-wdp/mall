/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.order.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.m.order.service.OrderOtherMPayService;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.Order;
import com.ningpai.order.bean.OrderOtherPay;
import com.ningpai.order.bean.OrderOtherPaySchedule;
import com.ningpai.order.service.OrderOtherPayScheduleService;
import com.ningpai.order.service.OrderOtherPayService;
import com.ningpai.order.service.OrderService;

/**
 * 代付信息service实现
 * 
 * @author NINGPAI-LIH
 * 
 */
@Service("OrderOtherMPayService")
public class OrderOtherMPayServiceImpl extends BasicSqlSupport implements OrderOtherMPayService {

    @Resource(name = "OrderOtherPayScheduleService")
    private OrderOtherPayScheduleService orderOtherPayScheduleService;

    @Resource(name = "OrderOtherPayService")
    private OrderOtherPayService orderOtherPayService;

    @Resource(name = "OrderService")
    private OrderService orderService;

    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;

    @Resource(name = "OrderOtherPayService")
    private OrderOtherPayService otherPayService;

    /*
     * 
     * 
     * @see
     * com.ningpai.m.order.service.OrderPayService#updateOrderOtherPay(com.ningpai
     * .order.bean.OrderOtherPaySchedule)
     */
    @Override
    public void updateOrderOtherPay(OrderOtherPaySchedule otherPay, HttpServletRequest request) {
        OrderOtherPaySchedule otherPaySchedule = orderOtherPayScheduleService.selectOrderOtherPayScheduleByOrderCode(otherPay.getOrderCode());
        try {
            otherPay.setOrderRemark(new String(otherPay.getOrderRemark().getBytes("ISO-8859-1"), ConstantUtil.UTF));
        } catch (UnsupportedEncodingException e) {
            Customer cust = (Customer) request.getSession().getAttribute("cust");
            OperaLogUtil.addOperaException(cust.getCustomerUsername(), e, request);
        }
        // 如果代付信息不为空，则修改该信息
        if (otherPaySchedule != null) {

            orderOtherPayScheduleService.updateOrderOtherPaySchedule(otherPay);
        } else {
            otherPay.setOrderPayCreateTime(new Date());
            // 如果代付信息为空，插入该信息
            orderOtherPayScheduleService.insertOrderOtherPaySchedule(otherPay);
        }
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.m.order.service.OrderOtherMPayService#selectOtherPay(java
     * .lang.Long)
     */
    @Override
    public Map<String, Object> selectOtherPay(Long orderId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Order order = orderService.orderDetail(orderId);
        // 多人代付进度表
        OrderOtherPaySchedule orderSchedule = orderOtherPayScheduleService.selectOrderOtherPayScheduleByOrderCode(order.getOrderCode());
        List<OrderOtherPay> otherPays = orderOtherPayService.queryOrderPayBySuccess(order.getOrderCode());
        map.put("order", order);
        map.put("orderSchedule", orderSchedule);
        map.put("otherPays", otherPays);
        map.put("cust", customerServiceMapper.queryCustomerInfo(order.getCustomerId()));
        return map;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.m.order.service.OrderOtherMPayService#updateOrderOtherSingle
     * (com.ningpai.order.bean.OrderOtherPay)
     */
    @Override
    public void updateOrderOtherSingle(OrderOtherPay otherPay, Long custId, HttpServletRequest request) {
        try {
            otherPay.setOrderRemark(new String(otherPay.getOrderRemark().getBytes("ISO-8859-1"), ConstantUtil.UTF));
        } catch (UnsupportedEncodingException e) {
            Customer cust = (Customer) request.getSession().getAttribute("cust");
            OperaLogUtil.addOperaException(cust.getCustomerUsername(), e, request);
        }
        otherPay.setOrderPayType("0");
        otherPay.setOrderPayCustid(custId);
        OrderOtherPay orderPayCode = otherPayService.queryOtherPayByOrderCode(otherPay);
        otherPay.setOrderPayCode(otherPay.getOrderCode());
        if (orderPayCode != null) {
            otherPayService.updateOtherPay(otherPay);
        } else {
            otherPayService.insertOtherPay(otherPay);

        }
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.m.order.service.OrderOtherMPayService#selectOtherPaySingle
     * (java.lang.Long)
     */
    @Override
    public Map<String, Object> selectOtherPaySingle(Long orderId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Order order = orderService.orderDetail(orderId);
        OrderOtherPay otherPay = new OrderOtherPay();
        otherPay.setOrderCode(order.getOrderCode());
        otherPay.setOrderPayType("0");
        OrderOtherPay orderPayCode = otherPayService.queryOtherPayByOrderCode(otherPay);
        OrderOtherPaySchedule orderOtherPaySchedule = orderOtherPayScheduleService.selectOrderOtherPayScheduleByOrderCode(order.getOrderCode());
        if (orderOtherPaySchedule != null) {
            order.setOrderPrice(orderOtherPaySchedule.getOrderResiduePrice());
        }
        map.put("order", order);
        map.put("otherPay", orderPayCode);
        map.put("cust", customerServiceMapper.queryCustomerInfo(order.getCustomerId()));
        return map;
    }

}
