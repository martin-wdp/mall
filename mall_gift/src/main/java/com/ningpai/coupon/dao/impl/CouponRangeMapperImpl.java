/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.coupon.bean.CouponRange;
import com.ningpai.coupon.dao.CouponRangeMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 优惠券范围实现类
 * 
 * @author ggn 2014-03-19
 * 
 */
@Repository("CouponRangeMapper")
public class CouponRangeMapperImpl extends BasicSqlSupport implements
        CouponRangeMapper {

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.dao.CouponRangeMapper#selectCouponRangeList(com.ningpai
     * .coupon.bean.CouponRange)
     */
    @Override
    public List<CouponRange> selectCouponRangeList(CouponRange cr) {
        return this.selectList(
                "com.ningpai.web.dao.CouponRangeMapper.selectCouponRangeList",
                cr);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.dao.CouponRangeMapper#createCouponRange(java.util.
     * List)
     */
    @Override
    public int createCouponRange(List<CouponRange> list) {
        return this
                .insert("com.ningpai.web.dao.CouponRangeMapper.createCouponRange",
                        list);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.dao.CouponRangeMapper#delAllCouponRange(java.lang.
     * Long)
     */
    @Override
    public int delAllCouponRange(Long couponId) {
        return this.update(
                "com.ningpai.web.dao.CouponRangeMapper.delAllCouponRange",
                couponId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.dao.CouponRangeMapper#selectCouponRange(java.util.Map)
     */
    @Override
    public List<CouponRange> selectCouponRange(Map<String, Object> paramMap) {
        return this.selectList(
                "com.ningpai.web.dao.CouponRangeMapper.selectCouponRange",
                paramMap);
    }

}
