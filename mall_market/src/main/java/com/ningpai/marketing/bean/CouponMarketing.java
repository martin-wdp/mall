/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.bean;

/**
 * 优惠券促销 2014-03-25
 * 
 * @author ggn
 * 
 */
public class CouponMarketing {
    /*
     * 优惠券促销ID
     */
    private Long couponMarketinigId;
    /*
     * 促销ID
     */
    private Long marketingId;
    /*
     * 优惠券ID
     */
    private Long couponId;
    /*
     * 是否删除
     */
    private String delFlag;

    public Long getCouponMarketinigId() {
        return couponMarketinigId;
    }

    public void setCouponMarketinigId(Long couponMarketinigId) {
        this.couponMarketinigId = couponMarketinigId;
    }

    public Long getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
