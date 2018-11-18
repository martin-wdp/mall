/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.bean;

import java.util.Date;

/**
 * 优惠券券码
 * 
 * @author ggn 2014-03-19
 * 
 */
public class CouponNo {
    /*
     *  券码ID
     */
    private Long codeId;
    /*
     *  优惠券ID
     */
    private Long couponId;
    /*
     *  券码号
     */
    private String codeNo;
    /*
     *  用户ID
     */
    private Long customerId;
    /*
     *  券码状态 0正常可领取 1已经领取可使用 2已经使用 3过期作废
     */
    private String codeStatus;
    /*
     *  领取方式 0积分兑换 1购买赠送
     */
    private String codeGetType;
    /*
     *  领取时间
     */
    private Date codeGetTime;
    /*
     *  所使用的订单编号
     */
    private String codeUseOrderId;

    /*
     *  优惠劵领取人
     */
    private String customerName;
    /*
     *  是否线下领取
     */
    private Long isCouponGet;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getCodeNo() {
        return codeNo;
    }

    public void setCodeNo(String codeNo) {
        this.codeNo = codeNo;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(String codeStatus) {
        this.codeStatus = codeStatus;
    }

    public String getCodeGetType() {
        return codeGetType;
    }

    public void setCodeGetType(String codeGetType) {
        this.codeGetType = codeGetType;
    }
    /**
     *  时间
     */
    public Date getCodeGetTime() {
        if (this.codeGetTime != null) {
            return new Date(this.codeGetTime.getTime()); // 正确值
        } else {
            return null;
        }
    }
    /**
     *  时间
     */
    public void setCodeGetTime(Date codeGetTime) {
        if (codeGetTime != null) {
            Date tEmp = (Date) codeGetTime.clone();
            if (tEmp != null) {
                this.codeGetTime = tEmp;
            }
        }
    }

    public String getCodeUseOrderId() {
        return codeUseOrderId;
    }

    public void setCodeUseOrderId(String codeUseOrderId) {
        this.codeUseOrderId = codeUseOrderId;
    }

    public Long getIsCouponGet() {
        return isCouponGet;
    }

    public void setIsCouponGet(Long isCouponGet) {
        this.isCouponGet = isCouponGet;
    }

}
