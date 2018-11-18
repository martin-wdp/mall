/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.CouponMarketing;
import com.ningpai.marketing.dao.CouponMarketingMapper;

/**
 * @author ggn 2014-03-25 优惠券促销信息 实现类
 */
@Repository("CouponMarketingMapper")
public class CouponMarketingMapperImpl extends BasicSqlSupport implements
        CouponMarketingMapper {

    /*
     * 查询优惠券促销信息列表
     * 
     * @param marketingId {@link java.lang.Long}
     * 
     * @return List
     * 
     * @see com.ningpai.marketing.dao.CouponMarketingMapper#
     * selectCouponMarketingListByMarketingId(java.lang.Long)
     */
    @Override
    public List<CouponMarketing> selectCouponMarketingListByMarketingId(
            Long marketingId) {
        return this
                .selectList(
                        "com.ningpai.web.dao.CouponMarketingMapper.selectCouponMarketingListByMarketingId",
                        marketingId);
    }

    /*
     * 插入买送优惠券
     * 
     * @param list {@link java.util.List}
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.CouponMarketingMapper#insertCouponMarketing
     * (java.util.List)
     */
    @Override
    public int insertCouponMarketing(List<CouponMarketing> list) {
        return this
                .insert("com.ningpai.web.dao.CouponMarketingMapper.insertCouponMarketing",
                        list);
    }

    /*
     * 删除优惠券促销信息
     * 
     * @param marketingId {@link java.lang.Long}
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.CouponMarketingMapper#deleteCouponMarketing
     * (java.lang.Long)
     */
    @Override
    public int deleteCouponMarketing(Long marketingId) {
        return this
                .update("com.ningpai.web.dao.CouponMarketingMapper.deleteCouponMarketing",
                        marketingId);
    }

}
