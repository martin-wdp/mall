/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao;

import com.ningpai.marketing.bean.LimitBuyMarketing;

/**
 * @author ggn
 * 
 */
public interface LimitBuyMarketingMapper {
    /**
     * 插入限购
     * 
     * @param limit
     * @return int
     */
    int insertLimitBuyMarketing(LimitBuyMarketing limit);

    /**
     * 查询限购
     * 
     * @param marketingId
     * @return LimitBuyMarketing
     */
    LimitBuyMarketing selectLimitBuyMarketingByMarketingId(Long marketingId);

    /**
     * 删除限购
     * 
     * @param marketingId
     * @return int
     */
    int deleteLimitBuyMarketing(Long marketingId);

}
