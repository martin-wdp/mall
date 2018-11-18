/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean;

import java.util.Date;

/**
 * 订单e店宝日志
 * 
 * @author NINGPAI-LIH
 *
 */
public class OrderELog {
    /*
     * 订单e店宝日志id
     */
    private Long orderELogId;

    /*
     * 日志id
     */
    private Long orderId;

    /*
     * 订单编号
     */
    private String orderCode;

    /*
     * 删除标记
     */
    private String flag;

    /*
     * 日志内容
     */
    private String orderLogContent;

    /*
     * 创建时间
     */
    private Date createTime;

    /*
     * 修改时间
     */
    private Date modifyTime;

    public Long getOrderELogId() {
        return orderELogId;
    }

    public void setOrderELogId(Long orderELogId) {
        this.orderELogId = orderELogId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getOrderLogContent() {
        return orderLogContent;
    }

    public void setOrderLogContent(String orderLogContent) {
        this.orderLogContent = orderLogContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
