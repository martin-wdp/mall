/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao;

import com.ningpai.marketing.bean.MarketingCodex;

/**
 * @author ggn
 * 
 */
public interface MarketingCodexMapper {

    /**
     * 插件促销表
     * 
     * @param mc
     *            {@link com.ningpai.marketing.bean.MarketingCodex}
     * @return int
     */
    int insertMarketingCodex(MarketingCodex mc);

    /**
     * 删除规则信息
     * 
     * @param marketingId
     *            {@link java.lang.Long}
     * @return int
     */
    int deleteMarketingCodex(Long marketingId);

    /**
     * 获取促销规则信息
     * 
     * @param marketingId
     * @return
     */
    MarketingCodex searchMarketRuleByMarketingId(Long marketingId);

}
