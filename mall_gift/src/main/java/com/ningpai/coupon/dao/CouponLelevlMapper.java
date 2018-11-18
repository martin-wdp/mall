/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.dao;

import java.util.List;

import com.ningpai.coupon.bean.CouponLelvel;

/**
 * @author ggn
 * 
 */
public interface CouponLelevlMapper {

    /**
     * 添加优惠券等级范围
     * 
     * @param list
     * @return int
     */
    int addCouponLelvel(List<CouponLelvel> list);

    /**
     * 查询优惠券等级范围
     * 
     * @param couponId
     * @return List
     */
    List<CouponLelvel> selectCouponLelvel(Long couponId);

    /**
     * 删除优惠等级范围
     * 
     * @param couponId
     * @return int
     */
    int deleteCouponLelvel(Long couponId);

    /**
     * 查询等级名称
     * 
     * @param couponId
     * @return
     */
    List<CouponLelvel> queryLevelNameByCouponId(Long couponId);

}
