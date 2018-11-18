/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.coupon.bean.CouponStraightDown;
import com.ningpai.coupon.dao.CouponStraightDownMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * @author ggn
 * 
 */
@Repository("CouponStraightDownMapper")
public class CouponStraightDownMapperImpl extends BasicSqlSupport implements
        CouponStraightDownMapper {

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.dao.CouponStraightDownMapper#selectCouponStraightDown
     * (java.lang.Long)
     */
    @Override
    public CouponStraightDown selectCouponStraightDown(Long couponId) {
        return this
                .selectOne(
                        "com.ningpai.web.dao.CouponStraightDownMapper.selectCouponStraightDown",
                        couponId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.dao.CouponStraightDownMapper#insertStraightDown(com
     * .ningpai.coupon.bean.CouponStraightDown)
     */
    @Override
    public int insertStraightDown(CouponStraightDown straightDown) {
        return this
                .insert("com.ningpai.web.dao.CouponStraightDownMapper.insertStraightDown",
                        straightDown);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.dao.CouponStraightDownMapper#deleteStraightDown(java
     * .lang.Long)
     */
    @Override
    public int deleteStraightDown(Long couponId) {
        return this
                .update("com.ningpai.web.dao.CouponStraightDownMapper.deleteStraightDown",
                        couponId);
    }

}
