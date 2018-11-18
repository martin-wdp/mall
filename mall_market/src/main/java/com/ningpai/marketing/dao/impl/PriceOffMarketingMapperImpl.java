/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.PriceOffMarketing;
import com.ningpai.marketing.dao.PriceOffMarketingMapper;

/**
 * @author ggn 2014-03-25 直降实现类
 */
@Repository("PriceOffMarketingMapper")
public class PriceOffMarketingMapperImpl extends BasicSqlSupport implements
        PriceOffMarketingMapper {

    /*
     * 
     * 查询直降信息
     * 
     * @param marketingId {@link java.lang.Long}
     * 
     * @return PriceOffMarketing
     * 
     * @see com.ningpai.marketing.dao.PriceOffMarketingMapper#
     * selectPriceOffMarketingByMarketingId(java.lang.Long)
     */
    @Override
    public PriceOffMarketing selectPriceOffMarketingByMarketingId(
            Long marketingId) {
        return this
                .selectOne(
                        "com.ningpai.web.dao.PriceOffMarketingMapper.selectPriceOffMarketingByMarketingId",
                        marketingId);
    }

    /*
     * 插入直降信息
     * 
     * @param poff {@com.ningpai.marketing.bean.PriceOffMarketing}
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.PriceOffMarketingMapper#insertPriceOffMarketing
     * (com.ningpai.marketing.bean.PriceOffMarketing)
     */
    @Override
    public int insertPriceOffMarketing(PriceOffMarketing poff) {
        return this.insert(
                "com.ningpai.web.dao.PriceOffMarketingMapper.insert", poff);
    }

    /*
     * 删除直降
     * 
     * @param marketingId
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.PriceOffMarketingMapper#deletePriceOffMarketing
     * (java.lang.Long)
     */
    @Override
    public int deletePriceOffMarketing(Long marketingId) {
        return this
                .update("com.ningpai.web.dao.PriceOffMarketingMapper.deletePriceOffMarketing",
                        marketingId);
    }

    /*
     * 修改促销信息
     * 
     * @param poff
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.PriceOffMarketingMapper#modifyPriceOffMarketing
     * (java.lang.Long)
     */
    @Override
    public int modifyPriceOffMarketing(PriceOffMarketing poff) {
        return this
                .update("com.ningpai.web.dao.PriceOffMarketingMapper.modifyPriceOffMarketing",
                        poff);
    }

}
