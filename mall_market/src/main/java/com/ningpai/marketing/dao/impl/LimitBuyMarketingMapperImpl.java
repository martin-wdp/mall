/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao.impl;

import org.springframework.stereotype.Service;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.LimitBuyMarketing;
import com.ningpai.marketing.dao.LimitBuyMarketingMapper;

/**
 * @author ggn
 * 
 */
@Service("LimitBuyMarketingMapper")
public class LimitBuyMarketingMapperImpl extends BasicSqlSupport implements
        LimitBuyMarketingMapper {

    /*
     * 插入限购
     * 
     * @param limit
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.LimitBuyMarketingMapper#insertLimitBuyMarketing
     * (com.ningpai.marketing.bean.LimitBuyMarketing)
     */
    @Override
    public int insertLimitBuyMarketing(LimitBuyMarketing limit) {
        return this
                .insert("com.ningpai.web.dao.LimitBuyMarketingMapper.insertLimitBuyMarketing",
                        limit);
    }

    /*
     * 查询限购
     * 
     * @param marketingId
     * 
     * @return LimitBuyMarketing
     * 
     * @see com.ningpai.marketing.dao.LimitBuyMarketingMapper#
     * selectLimitBuyMarketingByMarketingId(java.lang.Long)
     */
    @Override
    public LimitBuyMarketing selectLimitBuyMarketingByMarketingId(
            Long marketingId) {
        return this
                .selectOne(
                        "com.ningpai.web.dao.LimitBuyMarketingMapper.selectLimitBuyMarketingByMarketingId",
                        marketingId);
    }

    /*
     * 删除限购
     * 
     * @param marketingId
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.LimitBuyMarketingMapper#deleteLimitBuyMarketing
     * (java.lang.Long)
     */
    @Override
    public int deleteLimitBuyMarketing(Long marketingId) {
        return this
                .delete("com.ningpai.web.dao.LimitBuyMarketingMapper.deleteLimitBuyMarketing",
                        marketingId);
    }

}
