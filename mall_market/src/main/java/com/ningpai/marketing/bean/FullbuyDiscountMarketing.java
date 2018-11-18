/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.bean;

import java.math.BigDecimal;

/**
 * 满折活动 2014-03-25
 * 
 * @author ggn
 * 
 */
public class FullbuyDiscountMarketing {
    /**
     * 满折Id
     */
    private Long fullbuyDiscountMarketingId;
    /**
     * 促销ID
     */
    private Long marketingId;
    /**
     * 满多少
     */
    private BigDecimal fullPrice;
    /**
     * 折扣多少
     */
    private BigDecimal fullbuyDiscount;
    /**
     * 会员满多少
     */
    private BigDecimal vipFullPrice;
    /**
     * 会员折扣多少
     */
    private BigDecimal vipFullbuyDiscount;
    /**
     * 是否删除
     */
    private String delFlag;

    public BigDecimal getVipFullPrice() {
        return vipFullPrice;
    }

    public void setVipFullPrice(BigDecimal vipFullPrice) {
        this.vipFullPrice = vipFullPrice;
    }

    public BigDecimal getVipFullbuyDiscount() {
        return vipFullbuyDiscount;
    }

    public void setVipFullbuyDiscount(BigDecimal vipFullbuyDiscount) {
        this.vipFullbuyDiscount = vipFullbuyDiscount;
    }

    public Long getFullbuyDiscountMarketingId() {
        return fullbuyDiscountMarketingId;
    }

    public void setFullbuyDiscountMarketingId(Long fullbuyDiscountMarketingId) {
        this.fullbuyDiscountMarketingId = fullbuyDiscountMarketingId;
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

    public BigDecimal getFullbuyDiscount() {
        return fullbuyDiscount;
    }

    public void setFullbuyDiscount(BigDecimal fullbuyDiscount) {
        this.fullbuyDiscount = fullbuyDiscount;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
