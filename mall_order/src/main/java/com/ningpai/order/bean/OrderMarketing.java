/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean;

import java.util.List;

import com.ningpai.marketing.bean.Marketing;

/**
 * 订单促销级联表 2014-04-09
 * 
 * @author ggn
 * 
 */
public class OrderMarketing {
    /**
     * 订单促销级联ID
     */
    private Long orderMarketingId;
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 促销ID
     */
    private Long marketingId;
    /**
     * 促销类型
     */
    private String marketingType;
    /**
     * 是否有赠品
     */
    private String haveGiftStatus;
    /**
     * 是否有优惠券
     */
    private String haveCouponStatus;
    /**
     * 订单赠送优惠券列表信息
     */
    private List<OrderCoupon> orderCouponList;
    /**
     * 订单赠送的赠品
     */
    private List<OrderGift> orderGiftList;
    /**
     * 促销详细信息
     */
    private Marketing marketing;

    public List<OrderGift> getOrderGiftList() {
        return orderGiftList;
    }

    public void setOrderGiftList(List<OrderGift> orderGiftList) {
        this.orderGiftList = orderGiftList;
    }

    public Marketing getMarketing() {
        return marketing;
    }

    public void setMarketing(Marketing marketing) {
        this.marketing = marketing;
    }

    public List<OrderCoupon> getOrderCouponList() {
        return orderCouponList;
    }

    public void setOrderCouponList(List<OrderCoupon> orderCouponList) {
        this.orderCouponList = orderCouponList;
    }

    public Long getOrderMarketingId() {
        return orderMarketingId;
    }

    public void setOrderMarketingId(Long orderMarketingId) {
        this.orderMarketingId = orderMarketingId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    public String getMarketingType() {
        return marketingType;
    }

    public void setMarketingType(String marketingType) {
        this.marketingType = marketingType;
    }

    public String getHaveGiftStatus() {
        return haveGiftStatus;
    }

    public void setHaveGiftStatus(String haveGiftStatus) {
        this.haveGiftStatus = haveGiftStatus;
    }

    public String getHaveCouponStatus() {
        return haveCouponStatus;
    }

    public void setHaveCouponStatus(String haveCouponStatus) {
        this.haveCouponStatus = haveCouponStatus;
    }
}
