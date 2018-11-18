/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.bean;

/**
 * 优惠券范围
 * 
 * @author ggn 2014-03-19
 * 
 */

public class CouponRange {
    /*
     *  范围ID
     */
    private Long couponRangeId;
    /*
     *  优惠券ID
     */
    private Long couponId;
    /*
     *  范围外键ID
     */
    private Long couponRangeFkId;
    /*
     *  范围类型 0分类 1品牌 2 SKU
     */
    private String couponRangeType;
    /*
     *  删除标记 0 正常 1删除
     */
    private String delFlag;

    public Long getCouponRangeId() {
        return couponRangeId;
    }

    public void setCouponRangeId(Long couponRangeId) {
        this.couponRangeId = couponRangeId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getCouponRangeFkId() {
        return couponRangeFkId;
    }

    public void setCouponRangeFkId(Long couponRangeFkId) {
        this.couponRangeFkId = couponRangeFkId;
    }

    public String getCouponRangeType() {
        return couponRangeType;
    }

    public void setCouponRangeType(String couponRangeType) {
        this.couponRangeType = couponRangeType;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
