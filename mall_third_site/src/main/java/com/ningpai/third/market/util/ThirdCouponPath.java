/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.third.market.util;

/**
 * <p>优惠劵用到的常量类</p>
 * @author NINGPAI-ggn
 * @since 2014年4月15日 下午4:02:38
 * @version 2.0
 */
public final class ThirdCouponPath {

    /**
     * 优惠券列表方法
     */
    public static final String INITCOUPONLIST = "thirdcouponlist.html";
    /**
     * 优惠券列表页面
     */
    public static final String COUPONLIST = "/coupon/thirdcouponlist";

    /**
     * 添加优惠券
     */
    public static final String ADDCOUPON = "/coupon/addthirdcoupon";
    /**
     * 跳转修改页面
     */
    public static final String TOUPDATECOUPON = "/coupon/updatethirdcoupon";

    private ThirdCouponPath() {
        super();
    }

}
