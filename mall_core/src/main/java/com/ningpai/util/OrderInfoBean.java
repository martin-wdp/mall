/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.util;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单信息
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月21日 下午4:41:20
 * @version 1.0
 */
public class OrderInfoBean {
    // 订单ID
    private Long orderId;
    // 订单编号
    private String orderNo;
    // 商品总金额
    private BigDecimal moneyPaid;
    // 购买时间
    private Date payTime;
    // 收货地址
    private String shippingAddress;
    // 收货人
    private String shippingPerson;
    // 收货人电话
    private String shippingPhone;
    // 收货人手机
    private String shippingMobile;
    // 省
    private String shippingProvince;
    // 市
    private String shippingCity;
    // 区县
    private String shippingCounty;

    public String getShippingAddress() {

        return shippingAddress;
    }

    public String getShippingPhone() {
        return shippingPhone;
    }

    public void setShippingPhone(String shippingPhone) {
        this.shippingPhone = shippingPhone;
    }

    public String getShippingMobile() {
        return shippingMobile;
    }

    public void setShippingMobile(String shippingMobile) {
        this.shippingMobile = shippingMobile;
    }

    public String getShippingProvince() {
        return shippingProvince;
    }

    public void setShippingProvince(String shippingProvince) {
        this.shippingProvince = shippingProvince;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingCounty() {
        return shippingCounty;
    }

    public void setShippingCounty(String shippingCounty) {
        this.shippingCounty = shippingCounty;
    }

    public String getShippingPerson() {
        return shippingPerson;
    }

    public void setShippingPerson(String shippingPerson) {
        this.shippingPerson = shippingPerson;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getMoneyPaid() {
        return moneyPaid;
    }

    public void setMoneyPaid(BigDecimal moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    /**
     * 获取支付时间
     * @return 支付时间
     */
    public Date getPayTime() {
        if (this.payTime != null) {
            return new Date(this.payTime.getTime());
        }
        return null;
    }

    /**
     * 设置支付时间
     * @param payTime 支付时间
     */
    public void setPayTime(Date payTime) {
        if (payTime != null) {
            Date tEmp = (Date) payTime.clone();
            if (tEmp != null) {
                this.payTime = tEmp;
            }
        }
    }

}
