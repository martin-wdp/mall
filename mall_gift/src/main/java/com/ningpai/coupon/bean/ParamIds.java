/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.bean;

/**
 * @author ggn
 * 
 */
public class ParamIds {
    /*
     * 作用外键ID
     */
    private Long couponRangeFkId;
    /*
     * 作用类型
     */
    private String couponRangeType;

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

}
