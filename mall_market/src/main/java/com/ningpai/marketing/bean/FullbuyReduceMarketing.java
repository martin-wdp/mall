/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.bean;

import java.math.BigDecimal;

/**
 * 满减促销
 * 
 * @author ggn
 * 
 */
public class FullbuyReduceMarketing {
    /*
     * 满减促销ID
     */
    private Long fullbuyReductMarketingId;
    /*
     * 促销Id
     */
    private Long marketingId;
    /*
     * 满多少
     */
    private BigDecimal fullPrice;
    /*
     * 减去多少
     */
    private BigDecimal reducePrice;
    /*
     * 满多少
     */
    private BigDecimal vipFullPrice;
    /*
     * 减去多少
     */
    private BigDecimal vipReducePrice;
    /*
     * 是否删除
     */
    private String delFlag;


    public BigDecimal getVipFullPrice() {
        return vipFullPrice;
    }

    public void setVipFullPrice(BigDecimal vipFullPrice) {
        this.vipFullPrice = vipFullPrice;
    }

    public BigDecimal getVipReducePrice() {
        return vipReducePrice;
    }

    public void setVipReducePrice(BigDecimal vipReducePrice) {
        this.vipReducePrice = vipReducePrice;
    }

    public Long getFullbuyReductMarketingId() {
        return fullbuyReductMarketingId;
    }

    public void setFullbuyReductMarketingId(Long fullbuyReductMarketingId) {
        this.fullbuyReductMarketingId = fullbuyReductMarketingId;
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

    public BigDecimal getReducePrice() {
        return reducePrice;
    }

    public void setReducePrice(BigDecimal reducePrice) {
        this.reducePrice = reducePrice;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
