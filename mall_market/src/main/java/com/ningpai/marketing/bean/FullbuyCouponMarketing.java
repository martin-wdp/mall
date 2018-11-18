/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.bean;

import java.math.BigDecimal;

/**
 * 满赠优惠券 2014-03-25
 * 
 * @author ggn
 * 
 */
public class FullbuyCouponMarketing {
    /*
     * 满赠优惠券 ID
     */
    private Long fullbuyCouponMarketingId;
    /*
     * 促销ID
     */
    private Long marketingId;
    /*
     * 满多少
     */
    private BigDecimal fullPrice;
    /*
     * 优惠券ID
     */
    private Long couponId;
    /*
     * 优惠券ID
     */
    private String delFlag;

    public Long getFullbuyCouponMarketingId() {
        return fullbuyCouponMarketingId;
    }

    public void setFullbuyCouponMarketingId(Long fullbuyCouponMarketingId) {
        this.fullbuyCouponMarketingId = fullbuyCouponMarketingId;
    }

    public Long getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
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
