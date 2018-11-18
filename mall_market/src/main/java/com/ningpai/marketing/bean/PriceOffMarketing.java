/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.bean;

import java.math.BigDecimal;

/**
 * 直降参数 2014-03-25
 * 
 * @author ggn
 * 
 */
public class PriceOffMarketing {
    // 直降ID
    private Long priceOffId;
    // 促销ID
    private Long marketingId;
    // 直降多少
    private BigDecimal offValue;
    // 是否删除
    private String delFlag;
    // 会员直降多少
    private BigDecimal offVipValue;

    public BigDecimal getOffVipValue() {
        return offVipValue;
    }

    public void setOffVipValue(BigDecimal offVipValue) {
        this.offVipValue = offVipValue;
    }

    public Long getPriceOffId() {
        return priceOffId;
    }

    public void setPriceOffId(Long priceOffId) {
        this.priceOffId = priceOffId;
    }

    public Long getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    public BigDecimal getOffValue() {
        return offValue;
    }

    public void setOffValue(BigDecimal offValue) {
        this.offValue = offValue;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
