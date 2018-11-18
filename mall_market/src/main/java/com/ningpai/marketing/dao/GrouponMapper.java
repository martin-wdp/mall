/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao;

import com.ningpai.marketing.bean.Groupon;

/**
 * 团购表dao
 * 
 * @author NINGPAI-LIH
 * @since 2014年10月30日15:17:39
 * 
 */
public interface GrouponMapper {

    /**
     * 插入团购信息
     * 
     * @param record
     *            团购信息
     * @return int
     */
    int insertSelective(Groupon record);

    /**
     * 根据促销查询团购信息
     * 
     * @param marketId
     *            促销id
     * @return
     */
    Groupon selectByMarketId(Long marketId);

    /**
     * 当前参团人数加1
     * 
     * @param marketId
     *            促销id
     * @return 修改结果
     */
    int updateCountByMarkertId(Long marketId);

    /**
     * 删除团购
     * @param marketId
     * @return
     */
    int deleteGroupMarketing(Long marketId);



}
