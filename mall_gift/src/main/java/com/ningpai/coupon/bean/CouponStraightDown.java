/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠券 直降信息
 */
public class CouponStraightDown {
    /*
     *  直降ID
     */
    private Long downId;
    /*
     *  优惠券ID
     */
    private Long couponId;
    /*
     *  直降金额
     */
    private BigDecimal downPrice;
    /*
     *  修改时间
     */
    private Date updateTime;
    /*
     *  删除标记
     */
    private String delFlag;

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getDownId() {
        return downId;
    }

    public void setDownId(Long downId) {
        this.downId = downId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public BigDecimal getDownPrice() {
        return downPrice;
    }

    public void setDownPrice(BigDecimal downPrice) {
        this.downPrice = downPrice;
    }
    /**
     *  时间
     */
    public Date getUpdateTime() {
        if (this.updateTime != null) {
            return new Date(this.updateTime.getTime()); // 正确值
        } else {
            return null;
        }
    }
    /**
     *  时间
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
