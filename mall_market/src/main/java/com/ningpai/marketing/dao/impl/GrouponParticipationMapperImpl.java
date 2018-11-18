/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.marketing.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.GrouponParticipation;
import com.ningpai.marketing.dao.GrouponParticipationMapper;

/**
 * 团购进度
 * 
 * @author NINGPAI-LIH
 * 
 */
@Repository("GrouponParticipationMapper")
public class GrouponParticipationMapperImpl extends BasicSqlSupport implements
        GrouponParticipationMapper {

    /*
     * 插入团购进度表信息
     * 
     * @param record团购进度信息
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.GrouponParticipationMapper#insertSelective(
     * com.ningpai.marketing.bean.GrouponParticipation)
     */
    @Override
    public int insertSelective(GrouponParticipation record) {
        return this
                .update("com.ningpai.web.dao.GrouponParticipationMapper.insertSelective",
                        record);
    }

}
