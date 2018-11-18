/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.coupon.bean.CouponFullReduction;
import com.ningpai.coupon.dao.CouponFullReductionMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * @author ggn
 * 
 */
@Repository("CouponFullReductionMapper")
public class CouponFullReductionMapperImpl extends BasicSqlSupport implements
        CouponFullReductionMapper {

    /*
     * 查询优惠券满减信息
     * 
     * @see
     * com.ningpai.coupon.dao.CouponFullReductionMapper#selectCouponFullReduction
     * (java.lang.Long)
     */
    @Override
    public CouponFullReduction selectCouponFullReduction(Long couponId) {
        return this
                .selectOne(
                        "com.ningpai.web.dao.CouponFullReductionMapper.selectCouponFullReduction",
                        couponId);
    }

    /*
     * 优惠券满减信息
     * 
     * @see
     * com.ningpai.coupon.dao.CouponFullReductionMapper#insertFullDuction(com
     * .ningpai.coupon.bean.CouponFullReduction)
     */
    @Override
    public int insertFullDuction(CouponFullReduction fullReduction) {
        return this
                .insert("com.ningpai.web.dao.CouponFullReductionMapper.insertFullDuction",
                        fullReduction);
    }

    /*
     * 删除满减
     * 
     * @see
     * com.ningpai.coupon.dao.CouponFullReductionMapper#deleteFullReduction(
     * java.lang.Long)
     */
    @Override
    public int deleteFullReduction(Long couponId) {
        return this
                .update("com.ningpai.web.dao.CouponFullReductionMapper.deleteFullReduction",
                        couponId);
    }

}
