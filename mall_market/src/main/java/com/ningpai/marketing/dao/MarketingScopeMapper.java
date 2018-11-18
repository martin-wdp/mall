/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.marketing.bean.MarketingScope;

/**
 * @author ggn 2014-02-24 范围接口
 */
public interface MarketingScopeMapper {

    /**
     * 批量查询接口范围
     * 
     * @param paramMap
     *            {@link java.util.Map}
     * @return List
     */
    List<MarketingScope> selectMarketingScope(Map<String, Object> paramMap);

    /**
     * 批量插入
     * 
     * @param mslist
     *            {@link java.util.List}
     * @return int
     */
    int insertMarketingScope(List<MarketingScope> mslist);

    /**
     * 删除范围信息
     * 
     * @param marketingId
     *            {@link java.lang.Long}
     * @return int
     */
    int deleteMarketingScope(Long marketingId);

    /**
     * 查询范围
     * 
     * @param paramMap
     *            {@link java.util.Map}
     * @return List
     */
    List<MarketingScope> selectMarketScopeByMap(Map<String, Object> paramMap);

    /**
     * 获取“查询范围信息”
     * 
     * @param marketingId
     * @return
     */
    List<MarketingScope> queryMarketingScope(Long marketingId);

    /**
     * 获取范围信息
     * 
     * @param map
     * @return
     */
    Integer queryScopeByMarketingIdAndScopeId(Map<String, Object> map);

    /**
     * 修改范围信息
     * 
     * @param ms
     * @return
     */
    int modifyMarketingScope(MarketingScope ms);

    /**
     * 单个插入范围信息
     * 
     * @param ms
     * @return
     */
    int insertMarketingScopeSingle(MarketingScope ms);

    /**
     * 计算goods是否存在
     * 
     * @param map
     * @return
     */
    Integer countGoodsByMidAndGid(Map<String, Object> map);

    /**
     * 移除操作
     * 
     * @param map
     * @return
     */
    Integer removeGoodsByMidAndGid(Map<String, Object> map);

}
