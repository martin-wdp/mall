package com.ningpai.order.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 代付实体类
 * */
public class OrderOtherPay {
    /**
     * 代付id
     */
    private Long otherPayId;

    /**
     * 代付订单号
     */
    private String orderCode;

    /**
     * 代付金额
     */
    private BigDecimal orderPayPrice;

    /**
     * 订单用户留言
     */
    private String orderRemark;

    /**
     * 当前代付状态 0:未开始 1：正在付款 2：已完成 3：已取消
     */
    private String orderPayStatus;

    /**
     * 代付人留言
     */
    private String orderPayRemark;

    /**
     * 代付人id
     */
    private Long orderPayCustid;

    /**
     * 代付人名称
     */
    private String orderPayName;

    /**
     * 代付人付款时间
     */
    private Date orderPayTime;

    /**
     * 代付创建时间
     */
    private Date orderCreateTime;

    /**
     * 0：单人代付 1：多人代付
     */
    private String orderPayType;

    /**
     * 多人代付进度id
     */
    private Long orderManyId;

    /**
     * 代付订单号
     */
    private String orderPayCode;

    /**
     * 代付人头像
     */
    private String customerImg;

    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    public String getOrderPayCode() {
        return orderPayCode;
    }

    public void setOrderPayCode(String orderPayCode) {
        this.orderPayCode = orderPayCode;
    }

    public Long getOtherPayId() {
        return otherPayId;
    }

    public void setOtherPayId(Long otherPayId) {
        this.otherPayId = otherPayId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public String getOrderPayRemark() {
        return orderPayRemark;
    }

    public void setOrderPayRemark(String orderPayRemark) {
        this.orderPayRemark = orderPayRemark;
    }

    public Long getOrderPayCustid() {
        return orderPayCustid;
    }

    public void setOrderPayCustid(Long orderPayCustid) {
        this.orderPayCustid = orderPayCustid;
    }

    public String getOrderPayName() {
        return orderPayName;
    }

    public void setOrderPayName(String orderPayName) {
        this.orderPayName = orderPayName;
    }

    public Date getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(Date orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getOrderPayType() {
        return orderPayType;
    }

    public void setOrderPayType(String orderPayType) {
        this.orderPayType = orderPayType;
    }

    public Long getOrderManyId() {
        return orderManyId;
    }

    public void setOrderManyId(Long orderManyId) {
        this.orderManyId = orderManyId;
    }
}
