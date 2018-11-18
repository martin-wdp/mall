/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.marketing.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.Groupon;
import com.ningpai.marketing.dao.GrouponMapper;

/**
 * 团购实现
 * 
 * @author NINGPAI-LIH
 * @since 2014年10月30日15:59:27
 * 
 */
@Repository("GrouponMapper")
public class GrouponMapperImpl extends BasicSqlSupport implements GrouponMapper {

    /*
     * 
     * 插入团购信息
     * 
     * @param record 团购信息
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.GrouponMapper#insertSelective(com.ningpai.marketing
     * .bean.Groupon)
     */
    @Override
    public int insertSelective(Groupon record) {
        return this.insert("com.ningpai.web.dao.GrouponMapper.insertSelective",
                record);
    }

    /*
     * 根据促销查询团购信息
     * 
     * @param marketId 促销id
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.GrouponMapper#selectByMarketId(java.lang.Long)
     */
    @Override
    public Groupon selectByMarketId(Long marketId) {
        return this.selectOne(
                "com.ningpai.web.dao.GrouponMapper.selectByMarketId", marketId);
    }

    /*
     * 当前参团人数加1
     * 
     * @param marketId 促销id
     * 
     * @return 修改结果
     * 
     * @see
     * com.ningpai.marketing.dao.GrouponMapper#updateCountByMarkertId(java.lang
     * .Long)
     */
    @Override
    public int updateCountByMarkertId(Long marketId) {
        return this.update(
                "com.ningpai.web.dao.GrouponMapper.updateCountByMarkertId",
                marketId);

    }

    @Override
    public int deleteGroupMarketing(Long marketId) {
        return this.update(
                "com.ningpai.web.dao.GrouponMapper.deleteGroupMarketing",
                marketId);

    }

}
