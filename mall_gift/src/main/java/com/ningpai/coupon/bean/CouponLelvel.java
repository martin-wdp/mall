/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.bean;

/**
 * 优惠券等级范围
 * 
 * @author ggn
 * 
 */
public class CouponLelvel {
    /*
     *  Id
     */
    private Long couponLelvelId;
    /*
     *  登记ID
     */
    private Long lelvelId;
    /*
     *  是否删除
     */
    private String lelvelDelFlag;
    /*
     *  优惠券ID
     */
    private Long couponId;
    /*
     * 登记名称
     */
    private String levelName;
    
    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getCouponLelvelId() {
        return couponLelvelId;
    }

    public void setCouponLelvelId(Long couponLelvelId) {
        this.couponLelvelId = couponLelvelId;
    }

    public Long getLelvelId() {
        return lelvelId;
    }

    public void setLelvelId(Long lelvelId) {
        this.lelvelId = lelvelId;
    }

    public String getLelvelDelFlag() {
        return lelvelDelFlag;
    }

    public void setLelvelDelFlag(String lelvelDelFlag) {
        this.lelvelDelFlag = lelvelDelFlag;
    }
}
