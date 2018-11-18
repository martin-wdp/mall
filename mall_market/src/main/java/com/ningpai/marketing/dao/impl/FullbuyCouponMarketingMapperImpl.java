/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.FullbuyCouponMarketing;
import com.ningpai.marketing.dao.FullbuyCouponMarketingMapper;

/**
 * @author ggn 2014-03-25 满送优惠券实现类
 */
@Repository("FullbuyCouponMarketingMapper")
public class FullbuyCouponMarketingMapperImpl extends BasicSqlSupport implements
        FullbuyCouponMarketingMapper {

    /*
     * 查询满赠优惠信息
     * 
     * @param marketingId {@link java.lang.Long}
     * 
     * @return List
     * 
     * @see com.ningpai.marketing.dao.FullbuyCouponMarketingMapper#
     * selectFullbuyCouponMarketingListByMarketingId(java.lang.Long)
     */
    @Override
    public List<FullbuyCouponMarketing> selectFullbuyCouponMarketingListByMarketingId(
            Long marketingId) {
        return this
                .selectList(
                        "com.ningpai.marketing.dao.FullbuyCouponMarketingMapper.selectFullbuyCouponMarketingListByMarketingId",
                        marketingId);
    }

    /*
     * 批量插入
     * 
     * @param list {@link java.util.List}
     * 
     * @return int
     * 
     * @see com.ningpai.marketing.dao.FullbuyCouponMarketingMapper#
     * insertFullbuyCouponMarketing(java.util.List)
     */
    @Override
    public int insertFullbuyCouponMarketing(List<FullbuyCouponMarketing> list) {
        return this
                .insert("com.ningpai.marketing.dao.FullbuyCouponMarketingMapper.insertFullbuyCouponMarketing",
                        list);
    }

    /*
     * 删除满送优惠券
     * 
     * @param marketingId
     * 
     * @return int
     * 
     * @see com.ningpai.marketing.dao.FullbuyCouponMarketingMapper#
     * deleteFullbuyCouponMarketing(java.lang.Long)
     */
    @Override
    public int deleteFullbuyCouponMarketing(Long marketingId) {
        return this
                .update("com.ningpai.marketing.dao.FullbuyCouponMarketingMapper.deleteFullbuyCouponMarketing",
                        marketingId);
    }

}
