/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao;

import com.ningpai.marketing.bean.GrouponParticipation;

/**
 * 团购进度表dao
 * 
 * @author NINGPAI-LIH
 * @since 2014年10月30日15:53:58
 * 
 */
public interface GrouponParticipationMapper {

    /**
     * 插入团购进度表信息
     * 
     * @param record团购进度信息
     * @return int
     */
    int insertSelective(GrouponParticipation record);
}
