/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.service;

import java.util.List;

import com.ningpai.coupon.bean.CouponLelvel;

/**
 * @author ggn
 * 
 */
public interface CouponLelevlService {
    /**
     * 优惠券等级范围
     * 
     * @param clist
     * @return int
     */
    int addCouponLelvel(List<CouponLelvel> clist);

    /**
     * 查询此优惠券的等级范围
     * 
     * @param couponId
     * @return List
     */
    List<CouponLelvel> selectCouponLelvel(Long couponId);

    /**
     * 
     * @param couponId
     */
    int deleteCouponLelvel(Long couponId);

    /**
     * 
     * @param couponId
     * @return
     */
    List<CouponLelvel> queryLevelNameByCouponId(Long couponId);

}
