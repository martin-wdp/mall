/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.marketing.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.MarketingRush;
import com.ningpai.marketing.dao.MarketingRushMapper;

/**
 * 抢购表
 * 
 * @author NINGPAI-LIH
 * @since 2014年11月26日14:07:00
 * 
 */
@Repository("MarketingRushMapper")
public class MarketingRushMapperImpl extends BasicSqlSupport implements
        MarketingRushMapper {

    /*
     * 批量插入抢购信息
     * 
     * @param list 抢购表
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingRushMapper#insertRushs(java.util.List)
     */
    @Transactional
    @Override
    public int insertRush(MarketingRush marketingRush) {
        return this
                .insert("com.ningpai.marketing.dao.MarketingRushMapper.insertSelective",
                        marketingRush);
    }

    @Override
    public int deleteRushMarketing(Long marketId) {
        return this
                .insert("com.ningpai.marketing.dao.MarketingRushMapper.deleteRushMarketing",
                        marketId);
    }

    /*
     * 
     * 根据促销查询抢购信息
     * 
     * @param marketId 促销
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingRushMapper#selectByMarketId(java.lang
     * .Long)
     */
    @Override
    public List<MarketingRush> selectByMarketId(Long marketId) {
        return this
                .selectList(
                        "com.ningpai.marketing.dao.MarketingRushMapper.selectByMarketId",
                        marketId);
    }

}
