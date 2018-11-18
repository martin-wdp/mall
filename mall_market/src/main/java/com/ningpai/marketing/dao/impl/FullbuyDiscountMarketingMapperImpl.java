/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.FullbuyDiscountMarketing;
import com.ningpai.marketing.dao.FullbuyDiscountMarketingMapper;

/**
 * @author ggn 2014-03-25 满折实现类
 */
@Repository("FullbuyDiscountMarketingMapper")
public class FullbuyDiscountMarketingMapperImpl extends BasicSqlSupport
        implements FullbuyDiscountMarketingMapper {

    /*
     * 查询满折活动
     * 
     * @param marketingId {@link java.lang.Long}
     * 
     * @return FullbuyDiscountMarketing
     * 
     * @see com.ningpai.marketing.dao.FullbuyDiscountMarketingMapper#
     * selectFullbuyDiscountMarketingByMarketingId(java.lang.Long)
     */
    @Override
    public FullbuyDiscountMarketing selectFullbuyDiscountMarketingByMarketingId(
            Long marketingId) {
        return this
                .selectOne(
                        "com.ningpai.marketing.dao.FullbuyDiscountMarketingMapper.selectFullbuyDiscountMarketingByMarketingId",
                        marketingId);
    }

    /*
     * 插入满折信息
     * 
     * @param fdm {@link com.ningpai.marketing.bean.FullbuyDiscountMarketing}
     * 
     * @return int
     * 
     * @see com.ningpai.marketing.dao.FullbuyDiscountMarketingMapper#
     * insertFullbuyDiscountMarketing
     * (com.ningpai.marketing.bean.FullbuyDiscountMarketing)
     */
    @Override
    public int insertFullbuyDiscountMarketing(FullbuyDiscountMarketing fdm) {
        return this
                .insert("com.ningpai.marketing.dao.FullbuyDiscountMarketingMapper.insert",
                        fdm);
    }

    /**
     * 删除满折信息
     *
     * @param marketingId
     *            {@link java.lang.Long}
     * @return int
     *
     */
    @Override
    public int deleteFullbuyDiscountMarketing(Long marketingId) {
        return this
                .update("com.ningpai.marketing.dao.FullbuyDiscountMarketingMapper.deleteFullbuyDiscountMarketing",
                        marketingId);
    }

    /**
     * 修改满折信息
     * 
     * @param fdm
     * @return
     */
    @Override
    public int modifyFullbuyDiscountMarketing(FullbuyDiscountMarketing fdm) {
        return this
                .update("com.ningpai.marketing.dao.FullbuyDiscountMarketingMapper.modifyFullbuyDiscountMarketing",
                        fdm);
    }

    /**
     * 根据促销ID查询满折信息
     *
     * @param marketingId
     *            促销id{@link java.lang.Long}
     * @return list
     */

    @Override
    public List<FullbuyDiscountMarketing> selectFullbuyDiscountMarketingsByMarketingId(
            Long marketingId) {
        return this
                .selectList(
                        "com.ningpai.marketing.dao.FullbuyDiscountMarketingMapper.selectFullbuyDiscountMarketingByMarketingId",
                        marketingId);
    }

}
