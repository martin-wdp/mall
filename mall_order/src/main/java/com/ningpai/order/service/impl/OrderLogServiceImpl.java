/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.order.bean.OrderLog;
import com.ningpai.order.dao.OrderLogMapper;
import com.ningpai.order.service.OrderLogService;

/**
 * 订单记录
 * 
 * @author NINGPAI-LIH
 * @since 2014年8月2日16:30:33
 */
@Service("OrderLogService")
public class OrderLogServiceImpl implements OrderLogService {

    @Resource(name = "OrderLogMapper")
    private OrderLogMapper orderLogMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.order.service.OrderLogService#insertSelective(java.lang.String
     * , java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    public int insertSelective(String reason, Long orderId, String customerName, String status) {
        // 订单记录id
        OrderLog orderLog = new OrderLog();
        // 记录原因
        if (reason != null && !"".equals(reason)) {
            orderLog.setOrderLogReason(reason);
        }
        // 操作订单id
        if (orderId != null) {
            orderLog.setOrderId(orderId);
        }
        // 操作人
        if (customerName != null && !"".equals(customerName)) {
            orderLog.setOrderLogPerson(customerName);
        }
        // 订单操作状态
        if (status != null && !"".equals(status)) {
            orderLog.setOrderLogStatus(status);
        }
        orderLog.setOrderLogTime(new Date());

        return orderLogMapper.insertSelective(orderLog);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.order.service.OrderLogService#selectOrderLogByOrderId(java
     * .lang.Long)
     */
    @Override
    public List<OrderLog> selectOrderLogByOrderId(Long orderId) {
        return orderLogMapper.selectOrderLogByParam(orderId);
    }

}
