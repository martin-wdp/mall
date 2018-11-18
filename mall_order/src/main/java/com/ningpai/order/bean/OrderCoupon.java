/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean;

import com.ningpai.coupon.bean.Coupon;

/**
 * 订单促销优惠券
 * 
 * @author ggn 2014-04-09
 * 
 */
public class OrderCoupon {
    /**
     * 主表ID
     */
    private Long orderCouponId;
    /**
     * 订单营销ID
     */
    private Long orderMarketingId;
    /**
     * 优惠券ID
     */
    private Long couponId;
    /**
     * 优惠劵码
     */
    private String couponNo;
    /**
     * 优惠券信息
     */
    private Coupon coupon;

    public Long getOrderCouponId() {
        return orderCouponId;
    }

    public void setOrderCouponId(Long orderCouponId) {
        this.orderCouponId = orderCouponId;
    }

    public Long getOrderMarketingId() {
        return orderMarketingId;
    }

    public void setOrderMarketingId(Long orderMarketingId) {
        this.orderMarketingId = orderMarketingId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

}
