/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.bean;

import java.math.BigDecimal;

/**
 * 满赠促销信息
 * 
 * @author ggn 2014-03-25
 * 
 */
public class FullbuyPresentMarketing {
    /*
     * 满赠ID
     */
    private Long fullbuyPresentMarketingId;
    /*
     * 促销ID
     */
    private Long marketingId;
    /*
     * 满多少
     */
    private BigDecimal fullPrice;
    /*
     * 赠品id
     */
    private Long productId;
    /*
     * 是否删除
     */
    private String delFlag;

    public Long getFullbuyPresentMarketingId() {
        return fullbuyPresentMarketingId;
    }

    public void setFullbuyPresentMarketingId(Long fullbuyPresentMarketingId) {
        this.fullbuyPresentMarketingId = fullbuyPresentMarketingId;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
