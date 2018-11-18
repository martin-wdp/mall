/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.PresentationMarketing;
import com.ningpai.marketing.dao.PresentationMarketingMapper;

/**
 * @author ggn 2014-03-25 买赠实现类
 */
@Repository("PresentationMarketingMapper")
public class PresentationMarketingMapperImpl extends BasicSqlSupport implements
        PresentationMarketingMapper {
    /*
     * 查询促销赠品信息
     * 
     * @param marketingId
     * 
     * @return List
     * 
     * @see com.ningpai.marketing.dao.PresentationMarketingMapper#
     * selectPresentationMarketingListByMarketingId(java.lang.Long)
     */
    @Override
    public List<PresentationMarketing> selectPresentationMarketingListByMarketingId(
            Long marketingId) {
        return this
                .selectList(
                        "com.ningpai.web.dao.PresentationMarketingMapper.selectPresentationMarketingListByMarketingId",
                        marketingId);
    }

    /*
     * 批量插入赠品信息
     * 
     * @param list
     * 
     * @return int
     * 
     * @see com.ningpai.marketing.dao.PresentationMarketingMapper#
     * insertPresentationMarketing(java.util.List)
     */
    @Override
    public int insertPresentationMarketing(List<PresentationMarketing> list) {
        return this
                .insert("com.ningpai.web.dao.PresentationMarketingMapper.insertPresentationMarketing",
                        list);
    }

    /*
     * 删除买赠
     * 
     * @param marketingId
     * 
     * @return int
     * 
     * @see com.ningpai.marketing.dao.PresentationMarketingMapper#
     * deletePresentationMarketing(java.lang.Long)
     */
    @Override
    public int deletePresentationMarketing(Long marketingId) {
        return this
                .update("com.ningpai.web.dao.PresentationMarketingMapper.deletePresentationMarketing",
                        marketingId);
    }

}
