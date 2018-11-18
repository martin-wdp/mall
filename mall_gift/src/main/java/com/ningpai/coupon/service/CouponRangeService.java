/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.service;

import java.util.List;

import com.ningpai.coupon.bean.CouponRange;

/**
 * @author ggn 2014-03-19 优惠券范围接口
 */
public interface CouponRangeService {

    /**
     * 查询优惠券范围
     * 
     * @param cr
     *            {@link com.ningpai.coupon.bean.CouponRange}
     * @return List<CouponRange> {@link java.util.List}
     */
    List<CouponRange> selectCouponRangeList(CouponRange cr);

}
