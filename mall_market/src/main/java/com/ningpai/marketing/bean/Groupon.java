/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 团购
 * 
 * @author NINGPAI-LIH
 * @since 2014年10月30日15:19:43
 * 
 */
public class Groupon {
    /*
     * 团购id
     */
    private Long grouponId;

    /*
     * 团购参与人数
     */
    private Long participateNumber;

    /*
     * 团购折扣率
     */
    private BigDecimal grouponDiscount;

    /*
     * 当前参与人数
     */
    private Long participateCount;

    /*
     * 团购状态 0：未开始 1：已开始 2：已完成 3：已作废
     */
    private String grouponStatus;

    /*
     * 团购完成时间
     */
    private Date grouponTime;

    /*
     * 删除标记
     */
    private String flag;

    /*
     * 促销id
     */
    private Long marketId;

    /*
     * 备用字段1
     */
    private String temp1;

    /*
     * 备用字段2
     */
    private String temp2;
    /*
     * 会员折扣
     */
    private BigDecimal grouponVipDiscount;
    /*
     * 会员折后价
     */
    private BigDecimal grouponVipPrice;
    /*
     * 折后价
     */
    private BigDecimal grouponPrice;

    public BigDecimal getGrouponVipDiscount() {
        return grouponVipDiscount;
    }

    public void setGrouponVipDiscount(BigDecimal grouponVipDiscount) {
        this.grouponVipDiscount = grouponVipDiscount;
    }

    public BigDecimal getGrouponVipPrice() {
        return grouponVipPrice;
    }

    public void setGrouponVipPrice(BigDecimal grouponVipPrice) {
        this.grouponVipPrice = grouponVipPrice;
    }

    public BigDecimal getGrouponPrice() {
        return grouponPrice;
    }

    public void setGrouponPrice(BigDecimal grouponPrice) {
        this.grouponPrice = grouponPrice;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Long getGrouponId() {
        return grouponId;
    }

    public void setGrouponId(Long grouponId) {
        this.grouponId = grouponId;
    }

    public Long getParticipateNumber() {
        return participateNumber;
    }

    public void setParticipateNumber(Long participateNumber) {
        this.participateNumber = participateNumber;
    }

    public BigDecimal getGrouponDiscount() {
        return grouponDiscount;
    }

    public void setGrouponDiscount(BigDecimal grouponDiscount) {
        this.grouponDiscount = grouponDiscount;
    }

    public Long getParticipateCount() {
        return participateCount;
    }

    public void setParticipateCount(Long participateCount) {
        this.participateCount = participateCount;
    }

    public String getGrouponStatus() {
        return grouponStatus;
    }

    public void setGrouponStatus(String grouponStatus) {
        this.grouponStatus = grouponStatus;
    }

    public Date getGrouponTime() {
        return grouponTime;
    }

    public void setGrouponTime(Date grouponTime) {
        this.grouponTime = grouponTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
