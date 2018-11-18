/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.coupon.bean.CouponLelvel;
import com.ningpai.coupon.dao.CouponLelevlMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * @author ggn
 * 
 */
@Repository("CouponLelevlMapper")
public class CouponLelevlMapperImpl extends BasicSqlSupport implements
        CouponLelevlMapper {

    /*
     * 添加优惠券等级范围
     * 
     * @see
     * com.ningpai.coupon.dao.CouponLelevlMapper#addCouponLelvel(java.util.List)
     */
    @Override
    public int addCouponLelvel(List<CouponLelvel> list) {
        return this.insert(
                "com.ningpai.web.dao.CouponLelvelMapper.addCouponLelvel", list);
    }

    /*
     * 查询优惠券等级范围
     * 
     * @see
     * com.ningpai.coupon.dao.CouponLelevlMapper#selectCouponLelvel(java.lang
     * .Long)
     */
    @Override
    public List<CouponLelvel> selectCouponLelvel(Long couponId) {
        return this.selectList(
                "com.ningpai.web.dao.CouponLelvelMapper.selectCouponLelvel",
                couponId);
    }

    /*
     * 删除优惠等级范围
     * 
     * @see
     * com.ningpai.coupon.dao.CouponLelevlMapper#deleteCouponLelvel(java.lang
     * .Long)
     */
    @Override
    public int deleteCouponLelvel(Long couponId) {
        return this.update(
                "com.ningpai.web.dao.CouponLelvelMapper.deleteCouponLelvel",
                couponId);
    }

    /*
     * 查询等级名称
     * 
     * @see
     * com.ningpai.coupon.dao.CouponLelevlMapper#queryLevelNameByCouponId(java
     * .lang.Long)
     */
    @Override
    public List<CouponLelvel> queryLevelNameByCouponId(Long couponId) {

        return this
                .selectList(
                        "com.ningpai.web.dao.CouponLelvelMapper.queryLevelNameByCouponId",
                        couponId);
    }

}
