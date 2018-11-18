/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.coupon.bean.CouponRange;
import com.ningpai.coupon.dao.CouponRangeMapper;
import com.ningpai.coupon.service.CouponRangeService;

/**
 * @author ggn 2014-03-19 优惠券范围接口实现类
 */
@Service("CouponRangeService")
public class CouponRangeServiceImpl implements CouponRangeService {

    private CouponRangeMapper couponRangeMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.service.CouponRangeService#selectCouponRangeList(com
     * .ningpai.coupon.bean.CouponRange)
     */
    @Override
    public List<CouponRange> selectCouponRangeList(CouponRange cr) {
        return couponRangeMapper.selectCouponRangeList(cr);
    }

    public CouponRangeMapper getCouponRangeMapper() {
        return couponRangeMapper;
    }

    @Resource(name = "CouponRangeMapper")
    public void setCouponRangeMapper(CouponRangeMapper couponRangeMapper) {
        this.couponRangeMapper = couponRangeMapper;
    }

}
