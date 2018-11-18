/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean;

import com.ningpai.coupon.bean.Coupon;

/**
 * 订单商品 送优惠券
 * 
 * @author ggn
 * 
 */
public class OrderGoodsInfoCoupon {
    /**
     * 订单商品优惠券ID
     */
    private Long goodsGiftId;
    /**
     * 订单商品明细ID
     */
    private Long orderGoodsId;
    /**
     * 优惠券ID
     */
    private Long couponId;
    /**
     * 优惠券卷码
     */
    private String couponNo;
    /**
     * 优惠券
     */
    private Coupon coupon;

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Long getGoodsGiftId() {
        return goodsGiftId;
    }

    public void setGoodsGiftId(Long goodsGiftId) {
        this.goodsGiftId = goodsGiftId;
    }

    public Long getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(Long orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
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
}
