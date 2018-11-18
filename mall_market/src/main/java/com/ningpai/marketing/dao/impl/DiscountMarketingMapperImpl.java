/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.DiscountMarketing;
import com.ningpai.marketing.dao.DiscountMarketingMapper;

/**
 * @author ggn 买折促销实现类
 */
@Repository("DiscountMarketingMapper")
public class DiscountMarketingMapperImpl extends BasicSqlSupport implements
        DiscountMarketingMapper {

    /*
     * 查询买折信息
     * 
     * @param marketingId {@link java.lang.Long}
     * 
     * @return DiscountMarketing
     * 
     * @see com.ningpai.marketing.dao.DiscountMarketingMapper#
     * selectDiscountMarketingByMarketingId(java.lang.Long)
     */
    @Override
    public DiscountMarketing selectDiscountMarketingByMarketingId(
            Long marketingId) {
        return this
                .selectOne(
                        "com.ningpai.web.dao.DiscountMarketingMapper.selectDiscountMarketingByMarketingId",
                        marketingId);
    }

    /*
     * 插入买着信息
     * 
     * @param dm {@link com.ningpai.marketing.bean.DiscountMarketing}
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.DiscountMarketingMapper#insertDiscountMarketing
     * (com.ningpai.marketing.bean.DiscountMarketing)
     */
    @Override
    public int insertDiscountMarketing(DiscountMarketing dm) {
        return this.insert(
                "com.ningpai.web.dao.DiscountMarketingMapper.insert", dm);
    }

    /*
     * 删除买折信息
     * 
     * @param marketingId {@link java.lang.Long}
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.DiscountMarketingMapper#deleteDiscountMarketing
     * (java.lang.Long)
     */
    @Override
    public int deleteDiscountMarketing(Long marketingId) {
        return this
                .update("com.ningpai.web.dao.DiscountMarketingMapper.deleteDiscountMarketing",
                        marketingId);
    }

    /**
     * 修改买折信息
     * 
     * @param dm
     * @return
     *
     */
    @Override
    public int modifyDiscountMarketing(DiscountMarketing dm) {
        return this
                .update("com.ningpai.web.dao.DiscountMarketingMapper.modifyDiscountMarketing",
                        dm);
    }

}
