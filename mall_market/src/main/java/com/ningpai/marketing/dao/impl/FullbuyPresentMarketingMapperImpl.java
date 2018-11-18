/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.FullbuyPresentMarketing;
import com.ningpai.marketing.dao.FullbuyPresentMarketingMapper;

/**
 * @author ggn 2014-03-25 满赠促销实现类
 */
@Repository("FullbuyPresentMarketingMapper")
public class FullbuyPresentMarketingMapperImpl extends BasicSqlSupport
        implements FullbuyPresentMarketingMapper {

    /*
     * 查询满赠促销信息
     * 
     * @param marketingId {@link java.lang.Long}
     * 
     * @return List
     * 
     * @see com.ningpai.marketing.dao.FullbuyPresentMarketingMapper#
     * selectFullbuyPresentMarketingListByMarketingId(java.lang.Long)
     */
    @Override
    public List<FullbuyPresentMarketing> selectFullbuyPresentMarketingListByMarketingId(
            Long marketingId) {
        return this
                .selectList(
                        "com.ningpai.marketing.dao.FullbuyPresentMarketingMapper.selectFullbuyPresentMarketingListByMarketingId",
                        marketingId);
    }

    /*
     * 插入满赠
     * 
     * @param list {@link java.util.List}
     * 
     * @return int
     * 
     * @see com.ningpai.marketing.dao.FullbuyPresentMarketingMapper#
     * insertFullbuyPresentMarketing(java.util.List)
     */
    @Override
    public int insertFullbuyPresentMarketing(List<FullbuyPresentMarketing> list) {
        return this
                .insert("com.ningpai.marketing.dao.FullbuyPresentMarketingMapper.insertFullbuyPresentMarketing",
                        list);
    }

    /*
     * 删除满赠
     * 
     * @param marketingId {@link java.lang.Long}
     * 
     * @return int
     * 
     * @see com.ningpai.marketing.dao.FullbuyPresentMarketingMapper#
     * updateFullbuyPresentMarketing(java.lang.Long)
     */
    @Override
    public int updateFullbuyPresentMarketing(Long marketingId) {
        return this
                .update("com.ningpai.marketing.dao.FullbuyPresentMarketingMapper.updateFullbuyPresentMarketing",
                        marketingId);
    }

}
