/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.bean;

import java.math.BigDecimal;

/**
 * 买折促销信息 2014-03-25
 * 
 * @author ggn
 * 
 */
public class DiscountMarketing {
    /*
     * 买折促销Id
     */
    private Long discountMarketingId;
    /*
     * 促销ID
     */
    private Long marketingId;
    /*
     * 折扣多少
     */
    private BigDecimal discountValue;
    /*
     * 是否删除
     */
    private String delFlag;

    public Long getDiscountMarketingId() {
        return discountMarketingId;
    }

    public void setDiscountMarketingId(Long discountMarketingId) {
        this.discountMarketingId = discountMarketingId;
    }

    public Long getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
