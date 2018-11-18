/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean;

import java.util.Date;

/**
 * 订单操作日志
 * 
 * @author NINGPAI-LIH
 * @since 2014年10月8日10:19:49
 * 
 */
public class OrderLog {
    /**
     * 订单记录id
     */
    private Long orderLogId;
    /**
     * 订单记录原因
     */
    private String orderLogReason;
    /**
     * 订单记录操作人
     */
    private String orderLogPerson;

    /**
     * 订单操作状态 0：修改价格 1：中断订单 2：拣货 3：出库 4：发货 5:退单审核状态6:修改状态
     */
    private String orderLogStatus;

    /**
     * 订单操作id
     */
    private Long orderId;

    /**
     * 订单记录时间
     */
    private Date orderLogTime;

    public Long getOrderLogId() {
        return orderLogId;
    }

    public void setOrderLogId(Long orderLogId) {
        this.orderLogId = orderLogId;
    }

    public String getOrderLogReason() {
        return orderLogReason;
    }

    public void setOrderLogReason(String orderLogReason) {
        this.orderLogReason = orderLogReason;
    }

    public String getOrderLogPerson() {
        return orderLogPerson;
    }

    public void setOrderLogPerson(String orderLogPerson) {
        this.orderLogPerson = orderLogPerson;
    }

    public String getOrderLogStatus() {
        return orderLogStatus;
    }

    public void setOrderLogStatus(String orderLogStatus) {
        this.orderLogStatus = orderLogStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderLogTime() {
        return orderLogTime;
    }

    public void setOrderLogTime(Date orderLogTime) {
        this.orderLogTime = orderLogTime;
    }
}
