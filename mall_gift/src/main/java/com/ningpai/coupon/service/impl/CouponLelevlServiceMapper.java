/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.coupon.bean.CouponLelvel;
import com.ningpai.coupon.dao.CouponLelevlMapper;
import com.ningpai.coupon.service.CouponLelevlService;

/**
 * @author ggn
 * 
 */
@Service("CouponLelevlService")
public class CouponLelevlServiceMapper implements CouponLelevlService {

    private CouponLelevlMapper couponLelevlMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.service.CouponLelevlService#addCouponLelvel(java.util
     * .List)
     */
    @Override
    public int addCouponLelvel(List<CouponLelvel> list) {
        return couponLelevlMapper.addCouponLelvel(list);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.service.CouponLelevlService#selectCouponLelvel(java
     * .lang.Long)
     */
    @Override
    public List<CouponLelvel> selectCouponLelvel(Long couponId) {
        return couponLelevlMapper.selectCouponLelvel(couponId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.coupon.service.CouponLelevlService#deleteCouponLelvel(java
     * .lang.Long)
     */
    @Override
    public int deleteCouponLelvel(Long couponId) {
        return couponLelevlMapper.deleteCouponLelvel(couponId);
    }

    public CouponLelevlMapper getCouponLelevlMapper() {
        return couponLelevlMapper;
    }

    @Resource(name = "CouponLelevlMapper")
    public void setCouponLelevlMapper(CouponLelevlMapper couponLelevlMapper) {
        this.couponLelevlMapper = couponLelevlMapper;
    }

    /*
     * 
     * @see
     * com.ningpai.coupon.service.CouponLelevlService#queryLevelNameByCouponId
     * (java.lang.Long)
     */
    @Override
    public List<CouponLelvel> queryLevelNameByCouponId(Long couponId) {

        return couponLelevlMapper.queryLevelNameByCouponId(couponId);
    }

}
