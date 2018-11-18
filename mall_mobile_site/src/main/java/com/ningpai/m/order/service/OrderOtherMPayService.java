/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.order.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ningpai.order.bean.OrderOtherPay;
import com.ningpai.order.bean.OrderOtherPaySchedule;

/**
 * 代付信息service
 * 
 * @author NINGPAI-LIH
 * @since 2014年10月16日10:50:45
 * 
 */
public interface OrderOtherMPayService {
    /**
     * 根据订单编号修改或者查询代付信息
     * 
     * @param orderCode
     *            订单编号
     */
    void updateOrderOtherPay(OrderOtherPaySchedule otherPay, HttpServletRequest request);

    /**
     * 根据订单id查询代付信息以及代付成功信息
     * 
     * @param orderId
     *            订单id
     * @return map
     */
    Map<String, Object> selectOtherPay(Long orderId);

    /**
     * 根据订单id查询单人代付信息
     * 
     * @param orderId
     *            订单id
     * @return map
     */
    Map<String, Object> selectOtherPaySingle(Long orderId);

    /**
     * 修改或添加单人代付信息
     * 
     * @param otherPay
     * @param custId
     * @return
     */
    void updateOrderOtherSingle(OrderOtherPay otherPay, Long custId, HttpServletRequest request);

}
