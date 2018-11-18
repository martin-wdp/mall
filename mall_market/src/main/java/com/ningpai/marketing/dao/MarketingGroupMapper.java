/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.marketing.bean.MarketingGroup;

/**
 * 优惠分组实体
 * 
 * @author NINGPAI-LIH
 * @since 2014年6月6日09:48:10
 */
public interface MarketingGroupMapper {

    /**
     * 查询所有的分组信息
     * 
     * @return 所有的分组信息
     */
    List<MarketingGroup> selectAll();

    /**
     * 按条件查询所有的分组信息
     * 
     * @return 所有的分组信息
     */
    List<Object> selectByPrimary(Map<String, Object> map);

    /**
     * 查询列表总数
     * 
     * @param map
     * @return
     */
    int selectGourpListCount(Map<String, Object> map);

    /**
     * 插入促销分组
     * 
     * @param group
     *            要插入的分组
     */
    void insertMarketingGroup(MarketingGroup group);

    /**
     * 修改促销分组
     * 
     * @param group
     *            修改参数
     */
    void updateByPrimaryKeySelective(MarketingGroup group);

    /**
     * 查询分组是否能被删除
     * 
     * @param groupId
     *            要删除id
     * @return
     */
    int delGroupByCodexIs(Long groupId);

    /**
     * 根据id删除优惠分组
     * 
     * @param groupId
     */
    void deleteByPrimaryKey(Long groupId);

}
