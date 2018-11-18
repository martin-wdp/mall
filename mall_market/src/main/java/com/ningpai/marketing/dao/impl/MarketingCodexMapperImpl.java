/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.MarketingCodex;
import com.ningpai.marketing.dao.MarketingCodexMapper;

/**
 * @author ggn
 * 
 */
@Repository("MarketingCodexMapper")
public class MarketingCodexMapperImpl extends BasicSqlSupport implements
        MarketingCodexMapper {

    /*
     * 插件促销表
     * 
     * @param mc {@link com.ningpai.marketing.bean.MarketingCodex}
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingCodexMapper#insertMarketingCodex(com
     * .ningpai.marketing.bean.MarketingCodex)
     */
    @Override
    public int insertMarketingCodex(MarketingCodex mc) {
        return this.insert(
                "com.ningpai.marketing.dao.MarketingCodexMapper.insert", mc);
    }

    /*
     * 删除规则信息
     * 
     * @param marketingId {@link java.lang.Long}
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingCodexMapper#deleteMarketingCodex(java
     * .lang.Long)
     */
    @Override
    public int deleteMarketingCodex(Long marketingId) {
        return this
                .update("com.ningpai.marketing.dao.MarketingCodexMapper.deleteMarketingCodex",
                        marketingId);
    }

    /**
     * 获取促销规则信息
     * 
     * @param marketingId
     * @return
     */
    @Override
    public MarketingCodex searchMarketRuleByMarketingId(Long marketingId) {
        return this
                .selectOne(
                        "com.ningpai.marketing.dao.MarketingCodexMapper.searchMarketRuleByMarketingId",
                        marketingId);
    }

}
