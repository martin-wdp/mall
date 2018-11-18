/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.api.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单ERP接口实体类
 * 
 * @author NINGPAI-LIH
 * @since 2014年12月18日14:57:18
 *
 */
public class OOrder {
    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 订单价格
     */
    private BigDecimal orderPrice;

    /**
     * 优惠价格
     */
    private BigDecimal orderPrePrice;

    /**
     * 原始价格
     */
    private BigDecimal orderOldPrice;

    /**
     * 订单状态 0未付款 1已付款未发货 2已发货未确认收获 3已经收货 4作废
     */
    private String orderStatus;

    /**
     * 用户ID
     */
    private Long customerId;

    /**
     * 付款时间
     */
    private Date payTime;

    /**
     * 发货时间
     */
    private Date sendExpressTime;

    /**
     * 收货时间
     */
    private Date getGoodsTime;

    /**
     * 收货省
     */
    private String shippingProvince;

    /**
     * 收货城市
     */
    private String shippingCity;

    /**
     * 收货区县
     */
    private String shippingCounty;

    /**
     * 详细地址
     */
    private String shippingAddress;

    /**
     * 收货人
     */
    private String shippingPerson;

    /**
     * 电话
     */
    private String shippingPhone;

    /**
     * 手机
     */
    private String shippingMobile;

    /**
     * 发票抬头
     */
    private String invoiceTitle;

    /**
     * 发票内容
     */
    private String invoiceContent;

    /**
     * 发票类型
     */
    private String invoiceType;

    /**
     * 客户留言
     */
    private String customerRemark;

    /**
     * 支付ID
     */
    private Long payId;

    /**
     * 订单积分
     */
    private Long orderIntegral;

    /**
     * 优惠卷码
     */
    private String couponNo;

    /**
     * 运费
     */
    private BigDecimal expressPrice;

    /**
     * 订单出库状态 0:未拣货 1：已装箱 2：未出库
     */
    private String orderCargoStatus;

    /**
     * 邮编
     */
    private String shippingPostcode;
    /**
     * 退单金额
     */
    private BigDecimal backPrice;
    /**
     * 商家ID
     */
    private Long businessId;
    /**
     * 仓库名称
     */
    private String storeName;

    /**
     * 类型 会员0 经销商2
     */
    private String dealerType;
    /**
     * 主单Code
     */
    private String orderOldCode;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 配送方式 0：快递配送 1：上门自提
     */
    private String orderExpressType;

    /**
     * 邮编
     */
    public String getShippingPostcode() {
        return shippingPostcode;
    }

    public void setShippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode;
    }

    /**
     * 退单金额
     */
    public BigDecimal getBackPrice() {
        return backPrice;
    }

    public void setBackPrice(BigDecimal backPrice) {
        this.backPrice = backPrice;
    }

    /**
     * 商家ID
     */
    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    /**
     * 仓库名称
     */
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * 类型 会员0 经销商2
     */
    public String getDealerType() {
        return dealerType;
    }

    public void setDealerType(String dealerType) {
        this.dealerType = dealerType;
    }

    /**
     * 主单Code
     */
    public String getOrderOldCode() {
        return orderOldCode;
    }

    public void setOrderOldCode(String orderOldCode) {
        this.orderOldCode = orderOldCode;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {

        if(createTime!=null){
            return (Date) createTime.clone();
        }
        return null;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime == null ? null : (Date) createTime.clone();
    }

    /**
     * 配送方式 0：快递配送 1：上门自提
     */
    public String getOrderExpressType() {
        return orderExpressType;
    }

    public void setOrderExpressType(String orderExpressType) {
        this.orderExpressType = orderExpressType;
    }

    /**
     * 订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 订单编号
     */
    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * 订单价格
     */
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * 优惠价格
     */
    public BigDecimal getOrderPrePrice() {
        return orderPrePrice;
    }

    public void setOrderPrePrice(BigDecimal orderPrePrice) {
        this.orderPrePrice = orderPrePrice;
    }

    /**
     * 原始价格
     */
    public BigDecimal getOrderOldPrice() {
        return orderOldPrice;
    }

    public void setOrderOldPrice(BigDecimal orderOldPrice) {
        this.orderOldPrice = orderOldPrice;
    }

    /**
     * 订单状态 0未付款 1已付款未发货 2已发货未确认收获 3已经收货 4作废
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 用户ID
     */
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 付款时间
     */
    public Date getPayTime() {

        if(payTime!=null){
            return (Date) payTime.clone();
        }
        return null;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime == null ? null : (Date) payTime.clone();
    }

    /**
     * 发货时间
     */
    public Date getSendExpressTime() {
        if(sendExpressTime!=null){
            return (Date) sendExpressTime.clone();
        }
        return null;
    }

    public void setSendExpressTime(Date sendExpressTime) {
        this.sendExpressTime = sendExpressTime == null ? null : (Date) sendExpressTime.clone();
    }

    /**
     * 收货时间
     */
    public Date getGetGoodsTime() {
        if(getGoodsTime!=null){
            return (Date) getGoodsTime.clone();
        }
        return null;
    }

    public void setGetGoodsTime(Date getGoodsTime) {
        this.getGoodsTime = getGoodsTime == null ? null : (Date) getGoodsTime.clone();
    }

    /**
     * 收货省
     */
    public String getShippingProvince() {
        return shippingProvince;
    }

    public void setShippingProvince(String shippingProvince) {
        this.shippingProvince = shippingProvince;
    }

    /**
     * 收货市
     */
    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    /**
     * 收货区县
     */
    public String getShippingCounty() {
        return shippingCounty;
    }

    public void setShippingCounty(String shippingCounty) {
        this.shippingCounty = shippingCounty;
    }

    /**
     * 详细地址
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * 收货人
     */
    public String getShippingPerson() {
        return shippingPerson;
    }

    public void setShippingPerson(String shippingPerson) {
        this.shippingPerson = shippingPerson;
    }

    /**
     * 电话
     */
    public String getShippingPhone() {
        return shippingPhone;
    }

    public void setShippingPhone(String shippingPhone) {
        this.shippingPhone = shippingPhone;
    }

    /**
     * 手机
     */
    public String getShippingMobile() {
        return shippingMobile;
    }

    public void setShippingMobile(String shippingMobile) {
        this.shippingMobile = shippingMobile;
    }

    /**
     * 发票抬头
     */
    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    /**
     * 发票内容
     */
    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    /**
     * 发票类型
     */
    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * 客户留言
     */
    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    /**
     * 支付ID
     */
    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    /**
     * 订单积分
     */
    public Long getOrderIntegral() {
        return orderIntegral;
    }

    public void setOrderIntegral(Long orderIntegral) {
        this.orderIntegral = orderIntegral;
    }

    /**
     * 优惠卷码
     */
    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    /**
     * 运费
     */
    public BigDecimal getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(BigDecimal expressPrice) {
        this.expressPrice = expressPrice;
    }

    /**
     * 订单出库状态 0:未拣货 1：已装箱 2：未出库
     */
    public String getOrderCargoStatus() {
        return orderCargoStatus;
    }

    public void setOrderCargoStatus(String orderCargoStatus) {
        this.orderCargoStatus = orderCargoStatus;
    }

}
