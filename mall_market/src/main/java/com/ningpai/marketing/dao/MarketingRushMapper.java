/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao;

import java.util.List;

import com.ningpai.marketing.bean.MarketingRush;

/**
 * 抢购表
 * 
 * @author NINGPAI-LIH
 * @since 2014年11月26日13:56:30
 * 
 */
public interface MarketingRushMapper {
    /**
     * 批量插入抢购信息
     * 
     * @param list
     *            抢购表
     * @return
     */
    int insertRush(MarketingRush marketingRush);


    /**
     *
     * @param marketId
     * @return
     */
    int deleteRushMarketing(Long marketId);

    /**
     * 根据促销查询抢购信息
     * 
     * @param marketId
     *            促销
     * @return
     */
    List<MarketingRush> selectByMarketId(Long marketId);
}
