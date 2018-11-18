/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.hotsearch.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.hotsearch.bean.HotSearch;
import com.ningpai.hotsearch.dao.HotSearchMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 热门搜索
 * @author ggn
 *
 */
@Repository("hotSearchMapper")
public class HotSearchMapperImpl extends BasicSqlSupport implements
        HotSearchMapper {

    /*
     * 根据热门搜索Id删除
     * 
     * @see
     * com.ningpai.hotsearch.dao.HotSearchMapper#deleteByPrimaryKey(java.lang
     * .Long)
     */
    @Override
    public int deleteByPrimaryKey(Long hotSearchId) {
        return this.delete(
                "com.ningpai.hotsearch.dao.HotSearchMapper.deleteByPrimaryKey",
                hotSearchId);
    }

    /*
     * 添加搜索记录
     * 
     * @see
     * com.ningpai.hotsearch.dao.HotSearchMapper#insert(com.ningpai.hotsearch
     * .bean.HotSearch)
     */
    @Override
    public int insert(HotSearch record) {
        return this.insert("com.ningpai.hotsearch.dao.HotSearchMapper.insert",
                record);
    }

    /*
     * 选择性添加搜索记录
     * 
     * @see
     * com.ningpai.hotsearch.dao.HotSearchMapper#insertSelective(com.ningpai
     * .hotsearch.bean.HotSearch)
     */
    @Override
    public int insertSelective(HotSearch record) {
        return this.insert(
                "com.ningpai.hotsearch.dao.HotSearchMapper.insertSelective",
                record);
    }

    /*
     * 根据主键查询热门搜索
     * 
     * @see
     * com.ningpai.hotsearch.dao.HotSearchMapper#selectByPrimaryKey(java.lang
     * .Long)
     */
    @Override
    public HotSearch selectByPrimaryKey(Long hotSearchId) {
        return this.selectOne(
                "com.ningpai.hotsearch.dao.HotSearchMapper.selectByPrimaryKey",
                hotSearchId);
    }

    /*
     * 根据Id选择修改属性
     * 
     * @see
     * com.ningpai.hotsearch.dao.HotSearchMapper#updateByPrimaryKeySelective
     * (com.ningpai.hotsearch.bean.HotSearch)
     */
    @Override
    public int updateByPrimaryKeySelective(HotSearch record) {
        return this
                .update("com.ningpai.hotsearch.dao.HotSearchMapper.updateByPrimaryKeySelective",
                        record);
    }

    /*
     * 根据主键修改所有热门搜索记录
     * 
     * @see
     * com.ningpai.hotsearch.dao.HotSearchMapper#updateByPrimaryKey(com.ningpai
     * .hotsearch.bean.HotSearch)
     */
    @Override
    public int updateByPrimaryKey(HotSearch record) {
        return this.update(
                "com.ningpai.hotsearch.dao.HotSearchMapper.updateByPrimaryKey",
                record);
    }

    /*
     * 热门搜索查询分页
     * 
     * @see
     * com.ningpai.hotsearch.dao.HotSearchMapper#selectHotBySelectivePageSize
     * (java.util.Map)
     */
    @Override
    public List<Object> selectHotBySelectivePageSize(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.hotsearch.dao.HotSearchMapper.selectHotBySelectivePageSize",
                        map);
    }

    /*
     * 根据条件查询热门搜索的总条数
     * 
     * @see
     * com.ningpai.hotsearch.dao.HotSearchMapper#selectHotSearchCount(java.util
     * .Map)
     */
    @Override
    public int selectHotSearchCount(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.hotsearch.dao.HotSearchMapper.selectHotSearchCount",
                        map);
    }

    /*
     * 批量删除
     * 
     * @see
     * com.ningpai.hotsearch.dao.HotSearchMapper#batchDelHotSearch(java.util
     * .List)
     */
    @Override
    public int batchDelHotSearch(List<Object> list) {
        return this
                .delete("com.ningpai.hotsearch.dao.HotSearchMapper.batchRemoveHotSearch",
                        list);
    }

    /*
     * 
     * 根据模板ID、频道ID查询热门搜索-前台展示用
     * 
     * @see
     * com.ningpai.hotsearch.dao.HotSearchMapper#selectHotBySelectiveForSite
     * (java.util.Map)
     */
    @Override
    public List<HotSearch> selectHotBySelectiveForSite(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.hotsearch.dao.HotSearchMapper.selectHotBySelectiveForSite",
                        map);
    }
}
