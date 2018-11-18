/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.MarketingScope;
import com.ningpai.marketing.dao.MarketingScopeMapper;

/**
 * @author ggn 2014-03-24 范围接口实现类
 */
@Repository("MarketingScopeMapper")
public class MarketingScopeMapperImpl extends BasicSqlSupport implements
        MarketingScopeMapper {

    /*
     * 批量查询接口范围
     * 
     * @param paramMap {@link java.util.Map}
     * 
     * @return List
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingScopeMapper#selectMarketingScope(java
     * .util.Map)
     */
    @Override
    public List<MarketingScope> selectMarketingScope(
            Map<String, Object> paramMap) {
        return this
                .selectList(
                        "com.ningpai.marketing.dao.MarketingScopeMapper.selectMarketingScope",
                        paramMap);
    }

    /*
     * 批量插入
     * 
     * @param mslist {@link java.util.List}
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingScopeMapper#insertMarketingScope(java
     * .util.List)
     */
    @Override
    public int insertMarketingScope(List<MarketingScope> list) {
        return this
                .insert("com.ningpai.marketing.dao.MarketingScopeMapper.insertMarketingScope",
                        list);
    }

    /*
     * 删除范围信息
     * 
     * @param marketingId {@link java.lang.Long}
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingScopeMapper#deleteMarketingScope(java
     * .lang.Long)
     */
    @Override
    public int deleteMarketingScope(Long marketingId) {
        return this
                .update("com.ningpai.marketing.dao.MarketingScopeMapper.deleteMarketingScope",
                        marketingId);
    }

    /*
     * 查询范围
     * 
     * @param paramMap {@link java.util.Map}
     * 
     * @return List
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingScopeMapper#selectMarketScopeByMap
     * (java.util.Map)
     */
    @Override
    public List<MarketingScope> selectMarketScopeByMap(
            Map<String, Object> paramMap) {
        return this
                .selectList(
                        "com.ningpai.marketing.dao.MarketingScopeMapper.selectMarketScopeByMapMay",
                        paramMap);
    }

    /*
     * 获取“查询范围信息”
     * 
     * @param marketingId
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingScopeMapper#selectMarketScopeByMap
     * (java.util.Map)
     */
    @Override
    public List<MarketingScope> queryMarketingScope(Long marketingId) {
        return this
                .selectList(
                        "com.ningpai.marketing.dao.MarketingScopeMapper.queryMarketingScope",
                        marketingId);
    }

    /*
     * 
     * 获取范围信息
     * 
     * @param map
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingScopeMapper#selectMarketScopeByMap
     * (java.util.Map)
     */
    @Override
    public Integer queryScopeByMarketingIdAndScopeId(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.marketing.dao.MarketingScopeMapper.queryScopeByMarketingIdAndScopeId",
                        map);
    }

    /*
     * 修改范围信息
     * 
     * @param ms
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingScopeMapper#selectMarketScopeByMap
     * (java.util.Map)
     */
    @Override
    public int modifyMarketingScope(MarketingScope ms) {
        return this
                .update("com.ningpai.marketing.dao.MarketingScopeMapper.modifyMarketingScope",
                        ms);
    }

    /*
     * 单个插入范围信息
     * 
     * @param ms
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingScopeMapper#selectMarketScopeByMap
     * (java.util.Map)
     */
    @Override
    public int insertMarketingScopeSingle(MarketingScope ms) {
        return this
                .insert("com.ningpai.marketing.dao.MarketingScopeMapper.insertMarketingScopeSingle",
                        ms);
    }

    /*
     * 计算goods是否存在
     * 
     * @param map
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingScopeMapper#selectMarketScopeByMap
     * (java.util.Map)
     */
    @Override
    public Integer countGoodsByMidAndGid(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.marketing.dao.MarketingScopeMapper.countGoodsByMidAndGid",
                        map);
    }

    /*
     * 移除操作
     * 
     * @param map
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingScopeMapper#selectMarketScopeByMap
     * (java.util.Map)
     */
    @Override
    public Integer removeGoodsByMidAndGid(Map<String, Object> map) {
        return this
                .update("com.ningpai.marketing.dao.MarketingScopeMapper.removeGoodsByMidAndGid",
                        map);
    }

}
