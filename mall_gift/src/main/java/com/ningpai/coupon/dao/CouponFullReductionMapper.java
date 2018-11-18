/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.dao;

import com.ningpai.coupon.bean.CouponFullReduction;

/**
 * 优惠券满减信息
 * 
 * @author ggn 2014-04-03
 */
public interface CouponFullReductionMapper {

    /**
     * 查询优惠券满减信息
     * 
     * @param couponId
     *            {@link java.lang.Long}
     * @return CouponFullReduction
     */
    CouponFullReduction selectCouponFullReduction(Long couponId);

    /**
     * 插入满减
     * 
     * @param fullReduction
     */
    int insertFullDuction(CouponFullReduction fullReduction);

    /**
     * 删除满减
     * 
     * @param couponId
     *            {@link java.lang.Long}
     * @return
     */
    int deleteFullReduction(Long couponId);

}
