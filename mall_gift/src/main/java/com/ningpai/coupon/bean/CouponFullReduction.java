/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠券满减信息
 * 
 * @author ggn
 * 
 */
public class CouponFullReduction {
    /*
     *  满减ID
     */
    private Long reductionId;
    /*
     *  优惠券ID
     */
    private Long couponId;
    /*
     *  满多少
     */
    private BigDecimal fullPrice;
    /*
     *  减多少
     */
    private BigDecimal reductionPrice;
    /*
     *  修改时间
     */
    private Date updateTime;
    /*
     *  是否删除
     */
    private String delFlag;

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getReductionId() {
        return reductionId;
    }

    public void setReductionId(Long reductionId) {
        this.reductionId = reductionId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }

    public BigDecimal getReductionPrice() {
        return reductionPrice;
    }

    public void setReductionPrice(BigDecimal reductionPrice) {
        this.reductionPrice = reductionPrice;
    }
    /**
     * 时间
     */
    public Date getUpdateTime() {
        if (this.updateTime != null) {
            return new Date(this.updateTime.getTime()); // 正确值
        } else {
            return null;
        }
    }
    /**
     * 时间
     */
    public void setUpdateTime(Date updateTime) {
        if (updateTime != null) {
            Date tEmp = (Date) updateTime.clone();
            if (tEmp != null) {
                this.updateTime = tEmp;
            }
        }
    }
}
