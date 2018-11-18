/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.bean;

import java.util.Date;

/**
 * 
 * @author AthrunNatu 促销范围
 * @since 2014年3月19日上午11:02:47
 */
public class MarketingScope {
    /**
     * 营销范围ID
     */
    private Long marketingScopeId;
    /**
     * 营销ID
     */
    private Long marketingId;
    /**
     * 范围ID
     */
    private Long scopeId;
    /**
     * 范围类型 0分类 1品牌 2商品
     */
    private String scopeType;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modTime;
    /**
     * 标记
     */
    private String flag;

    public Long getMarketingScopeId() {
        return marketingScopeId;
    }

    public void setMarketingScopeId(Long marketingScopeId) {
        this.marketingScopeId = marketingScopeId;
    }

    public Long getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    public Long getScopeId() {
        return scopeId;
    }

    public void setScopeId(Long scopeId) {
        this.scopeId = scopeId;
    }

    public String getScopeType() {
        return scopeType;
    }

    public void setScopeType(String scopeType) {
        this.scopeType = scopeType;
    }

    /**
     * 获取创建时间
     * 
     * @return
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime()); // 正确值
        } else {
            return null;
        }
    }

    /**
     * 设置创建时间
     * 
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                this.createTime = tEmp;
            }
        }
    }

    /**
     * 获取修改时间
     * 
     * @return
     */
    public Date getModTime() {
        if (this.modTime != null) {
            return new Date(this.modTime.getTime()); // 正确值
        } else {
            return null;
        }
    }

    /**
     * 设置修改时间
     * 
     * @param modTime
     */
    public void setModTime(Date modTime) {
        if (modTime != null) {
            Date tEmp = (Date) modTime.clone();
            if (tEmp != null) {
                this.modTime = tEmp;
            }
        }
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
