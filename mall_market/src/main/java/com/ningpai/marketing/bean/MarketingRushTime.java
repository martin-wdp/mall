/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.bean;

import java.util.Date;

/**
 * 抢购时间段
 * 
 * @author NINGPAI-LIH
 * 
 */
public class MarketingRushTime {

    /**
     * 主键id
     */
    private Long tId;
    /**
     * 抢购时间段
     */
    private Long tDate;
    /**
     * 是否开启
     */
    private String flag;
    /**
     * 是否删除
     */
    private String deflag;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备用字段1
     */
    private String temp1;
    /**
     * 备用字段2
     */
    private String temp2;

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public Long gettDate() {
        return tDate;
    }

    public void settDate(Long tDate) {
        this.tDate = tDate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDeflag() {
        return deflag;
    }

    public void setDeflag(String deflag) {
        this.deflag = deflag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}
