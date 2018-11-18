/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.dao;

import com.ningpai.coupon.bean.CouponStraightDown;

/**
 * @author ggn 2014-04-03
 */
public interface CouponStraightDownMapper {

    /**
     * 查询优惠券直降信息
     * 
     * @param couponId
     *            {@link java.lang.Long}
     * @return CouponStraightDown
     */
    CouponStraightDown selectCouponStraightDown(Long couponId);

    /**
     * 插入直降
     * 
     * @param straightDown
     */
    int insertStraightDown(CouponStraightDown straightDown);

    /**
     * 删除直降
     * 
     * @param couponId
     *            {@link java.lang.Long}
     */
    int deleteStraightDown(Long couponId);

}
