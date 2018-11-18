/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.bean;

import java.math.BigDecimal;

/**
 * 抢购
 * 
 * @author NINGPAI-LIH
 * @since 2014年11月26日13:47:12
 * 
 */
public class MarketingRush {
    /**
     *  抢购时间
     */
    private Long rushId;

    /**
     *  抢购折扣
     */
    private Long rushCount;

    /**
     *  优惠id
     */
    private Long marketId;

    /**
     *  抢购时间id
     */
    private Long tId;


    /**
     *  折扣率
     */
    private BigDecimal rushDiscount;

    /**
     *  备用字段1
     */
    private String temp1;

    /**
     *  备用字段2
     */
    private String temp2;

    /**
     *  备用字段3
     */
    private String temp3;

    /**
     *  抢购时间
     */
    MarketingRushTime rushTimes;

    /**
     * 每个ip限购
     */
    private Integer rushLimitation;


    /**
     * 每个抢购图片
     */
    private String rushImage;

    /**
     * 会员抢购折扣
     */
    private BigDecimal rushVipDiscount;

    /**
     * 抢购价
     */
    private BigDecimal rushPrice;

    /**
     * 会员抢购价
     */
    private BigDecimal rushVipPrice;

    public BigDecimal getRushVipDiscount() {
        return rushVipDiscount;
    }

    public void setRushVipDiscount(BigDecimal rushVipDiscount) {
        this.rushVipDiscount = rushVipDiscount;
    }

    public BigDecimal getRushPrice() {
        return rushPrice;
    }

    public void setRushPrice(BigDecimal rushPrice) {
        this.rushPrice = rushPrice;
    }

    public BigDecimal getRushVipPrice() {
        return rushVipPrice;
    }

    public void setRushVipPrice(BigDecimal rushVipPrice) {
        this.rushVipPrice = rushVipPrice;
    }

    public String getRushImage() {
        return rushImage;
    }

    public void setRushImage(String rushImage) {
        this.rushImage = rushImage;
    }

    public Integer getRushLimitation() {
        return rushLimitation;
    }

    public void setRushLimitation(Integer rushLimitation) {
        this.rushLimitation = rushLimitation;
    }

    /**
     * @return
     */
    public MarketingRushTime getRushTimes() {
        return rushTimes;
    }

    /**
     * @param rushTimes
     */
    public void setRushTimes(MarketingRushTime rushTimes) {
        this.rushTimes = rushTimes;
    }

    /**
     * @return
     */
    public Long getRushId() {
        return rushId;
    }

    /**
     * @param rushId
     */
    public void setRushId(Long rushId) {
        this.rushId = rushId;
    }

    /**
     * @return
     */
    public Long getRushCount() {
        return rushCount;
    }

    /**
     * @param rushCount
     */
    public void setRushCount(Long rushCount) {
        this.rushCount = rushCount;
    }

    /**
     * @return
     */
    public Long getMarketId() {
        return marketId;
    }

    /**
     * @param marketId
     */
    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    /**
     * @return
     */
    public Long gettId() {
        return tId;
    }

    /**
     * @param tId
     */
    public void settId(Long tId) {
        this.tId = tId;
    }

    /**
     * @return
     */
    public BigDecimal getRushDiscount() {
        return rushDiscount;
    }

    /**
     * @param rushDiscount
     */
    public void setRushDiscount(BigDecimal rushDiscount) {
        this.rushDiscount = rushDiscount;
    }

    /**
     * @return
     */
    public String getTemp1() {
        return temp1;
    }

    /**
     * @param temp1
     */
    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    /**
     * @return
     */
    public String getTemp2() {
        return temp2;
    }

    /**
     * @param temp2
     */
    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    /**
     * @return
     */
    public String getTemp3() {
        return temp3;
    }

    /**
     * @param temp3
     */
    public void setTemp3(String temp3) {
        this.temp3 = temp3;
    }
}
