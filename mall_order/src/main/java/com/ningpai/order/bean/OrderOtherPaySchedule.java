package com.ningpai.order.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单代付实体类
 * */
public class OrderOtherPaySchedule {
    /**
     * 多人代付进度id
     */
    private Long orderManyId;

    /**
     * 多人代付订单号
     */
    private String orderCode;

    /**
     * 多人代付总价格
     */
    private BigDecimal orderResiduePrice;

    /**
     * 多人代付支付价格
     */
    private BigDecimal orderPayPrice;

    /**
     * 多人代付留言
     */
    private String orderRemark;

    /**
     * 多人代付状态
     */
    private String orderPayStatus;

    /**
     * 多人代付创建时间
     */
    private Date orderPayCreateTime;

    public Date getOrderPayCreateTime() {
        return orderPayCreateTime;
    }

    public void setOrderPayCreateTime(Date orderPayCreateTime) {
        this.orderPayCreateTime = orderPayCreateTime;
    }

    public Long getOrderManyId() {
        return orderManyId;
    }

    public void setOrderManyId(Long orderManyId) {
        this.orderManyId = orderManyId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public BigDecimal getOrderResiduePrice() {
        return orderResiduePrice;
    }

    public void setOrderResiduePrice(BigDecimal orderResiduePrice) {
        this.orderResiduePrice = orderResiduePrice;
    }

    public BigDecimal getOrderPayPrice() {
        return orderPayPrice;
    }

    public void setOrderPayPrice(BigDecimal orderPayPrice) {
        this.orderPayPrice = orderPayPrice;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public String getOrderPayStatus() {
        return orderPayStatus;
    }

    public void setOrderPayStatus(String orderPayStatus) {
        this.orderPayStatus = orderPayStatus;
    }
}
