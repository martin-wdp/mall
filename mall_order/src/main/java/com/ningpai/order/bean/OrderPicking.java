/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean;

import java.util.Date;

/**
 * 拣货单表
 * 
 * @author NINGPAI-LIH
 * @since 2014年6月25日10:01:14
 * 
 */
public class OrderPicking {
    /**
     * 件货单id
     */
    private Long pickingId;

    /**
     * 拣货单编号
     */
    private String pickingNo;

    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 拣货 时间
     */
    private Date pickingTime;

    /**
     * 拣货人姓名
     */
    private String pickingName;

    /**
     * 第三方标示
     */
    private Long thirdId;

    /**
     * 状态 0：拣货 1：出库
     */
    private String pickingStatus;

    public String getPickingStatus() {
        return pickingStatus;
    }

    public void setPickingStatus(String pickingStatus) {
        this.pickingStatus = pickingStatus;
    }

    public Long getPickingId() {
        return pickingId;
    }

    public void setPickingId(Long pickingId) {
        this.pickingId = pickingId;
    }

    public String getPickingNo() {
        return pickingNo;
    }

    public void setPickingNo(String pickingNo) {
        this.pickingNo = pickingNo;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getPickingTime() {
        return pickingTime;
    }

    public void setPickingTime(Date pickingTime) {
        this.pickingTime = pickingTime;
    }

    public String getPickingName() {
        return pickingName;
    }

    public void setPickingName(String pickingName) {
        this.pickingName = pickingName;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }
}
