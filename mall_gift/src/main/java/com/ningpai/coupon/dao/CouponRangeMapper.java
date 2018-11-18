/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.coupon.bean.CouponRange;

/**
 * 优惠券范围接口
 * 
 * @author ggn 2014-03-19
 * 
 */

public interface CouponRangeMapper {

    /**
     * 查询优惠券范围
     * 
     * @param cr
     * @return List
     */
    List<CouponRange> selectCouponRangeList(CouponRange cr);

    /**
     * 批量增加优惠券范围
     * 
     * @param rangelist
     * @return //封装优惠券范围 品牌
     */
    int createCouponRange(List<CouponRange> rangelist);

    /**
     * 删除优惠券范围
     * 
     * @param couponId
     * @return int
     */
    int delAllCouponRange(Long couponId);

    /**
     * 查询优惠券范围
     * 
     * @param paramMap
     * @return List
     */
    List<CouponRange> selectCouponRange(Map<String, Object> paramMap);

}
