/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.service;

import java.util.List;

import com.ningpai.order.bean.OrderLog;

/**
 * 订单记录
 * 
 * @author NINGPAI-LIH
 * @since 2014年8月2日16:31:11
 * 
 */
public interface OrderLogService {

    /**
     * 根据订单id查询订单下所属的操作记录
     * 
     * @param orderId
     *            订单id
     * @return 操作记录集合
     */
    List<OrderLog> selectOrderLogByOrderId(Long orderId);

    /**
     * 插入订单记录
     * 
     * @param reason
     *            订单记录信息
     * @param orderId
     *            订单id
     * @param customerName
     *            操作人
     * @param status
     *            操作类型
     * @return
     */
    int insertSelective(String reason, Long orderId, String customerName,
            String status);
}
