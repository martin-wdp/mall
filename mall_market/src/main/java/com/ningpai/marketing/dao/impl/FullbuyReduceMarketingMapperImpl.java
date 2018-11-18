/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.FullbuyReduceMarketing;
import com.ningpai.marketing.dao.FullbuyReduceMarketingMapper;

/**
 * @author ggn 2014-03-25 满减促销接口
 */
@Repository("FullbuyReduceMarketingMapper")
public class FullbuyReduceMarketingMapperImpl extends BasicSqlSupport implements
        FullbuyReduceMarketingMapper {

    /*
     * 查询满减活动信息
     * 
     * @param marketingId {@link java.lang.Long}
     * 
     * @return FullbuyReduceMarketing
     * 
     * @see com.ningpai.marketing.dao.FullbuyReduceMarketingMapper#
     * selectFullbuyReduceMarketingByMarketingId(java.lang.Long)
     */
    @Override
    public FullbuyReduceMarketing selectFullbuyReduceMarketingByMarketingId(
            Long marketingId) {
        return this
                .selectOne(
                        "com.ningpai.web.dao.FullbuyReduceMarketingMapper.selectFullbuyReduceMarketingByMarketingId",
                        marketingId);
    }

    /*
     * 插入满减
     * 
     * @param frm {@link com.ningpai.marketing.bean.FullbuyReduceMarketing}
     * 
     * @return int
     * 
     * @see com.ningpai.marketing.dao.FullbuyReduceMarketingMapper#
     * insertFullbuyReduceMarketing
     * (com.ningpai.marketing.bean.FullbuyReduceMarketing)
     */
    @Override
    public int insertFullbuyReduceMarketing(FullbuyReduceMarketing frm) {
        return this.insert(
                "com.ningpai.web.dao.FullbuyReduceMarketingMapper.insert", frm);
    }

    /*
     * 删除满减
     * 
     * @param marketingId {@link java.lang.Long}
     * 
     * @return int
     * 
     * @see com.ningpai.marketing.dao.FullbuyReduceMarketingMapper#
     * deleteFullbuyReduceMarketing(java.lang.Long)
     */
    @Override
    public int deleteFullbuyReduceMarketing(Long marketingId) {
        return this
                .update("com.ningpai.web.dao.FullbuyReduceMarketingMapper.deleteFullbuyReduceMarketing",
                        marketingId);
    }

    /*
     * 修改满减
     * 
     * @param frm
     * 
     * @return
     * 
     * @see com.ningpai.marketing.dao.FullbuyReduceMarketingMapper#
     * deleteFullbuyReduceMarketing(java.lang.Long)
     */
    @Override
    public int modifyFullbuyReduceMarketing(FullbuyReduceMarketing frm) {
        return this
                .update("com.ningpai.web.dao.FullbuyReduceMarketingMapper.modifyFullbuyReduceMarketing",
                        frm);
    }

    /*
     * 根据促销ID查询促销满减信息
     * 
     * @param marketingId 促销ID{@link java.lang.Long}
     * 
     * @return list
     * 
     * @see com.ningpai.marketing.dao.FullbuyReduceMarketingMapper#
     * selectFullbuyReduceMarketingsByMarketingId(java.lang.Long)
     */
    @Override
    public List<FullbuyReduceMarketing> selectFullbuyReduceMarketingsByMarketingId(
            Long marketingId) {
        return this
                .selectList(
                        "com.ningpai.web.dao.FullbuyReduceMarketingMapper.selectFullbuyReduceMarketingByMarketingId",
                        marketingId);
    }

}
