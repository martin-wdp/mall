/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.hotsearch.service;

import java.util.List;

import com.ningpai.hotsearch.bean.HotSearch;
import com.ningpai.util.PageBean;

/**
 * 热门搜索
 * 
 * @author ggn
 *
 */
public interface HotSearchService {
    /**
     * 根据主键Id，删除热门搜索记录
     * 
     * @param hotSearchId
     * @return int
     */
    int removeHotSearchById(Long hotSearchId);

    /**
     * 批量删除
     * 
     * @param hots
     * @return int
     */
    int batchDelHotSearch(Long[] hots);

    /**
     * 选择字段添加热门搜索记录
     * 
     * @param hotSearch
     * @return int
     */
    int addHotSearchSelective(HotSearch hotSearch);

    /**
     * 修改热门搜索记录可选择字段，根据主键Id
     * 
     * @param hotSearchId
     * @return int
     */
    int modifyHostSearchSelectiveById(HotSearch hotSearch);

    /**
     * 根据主键ID，查询热门搜索表记录
     * 
     * @param hotSearchId
     * @return int
     */
    HotSearch selectHotSearchById(Long hotSearchId);

    /**
     * 根据条件查询分页热门搜索记录
     * 
     * @param keyword
     *            关键字
     * @param tempid
     *            模板Id
     * @param channelid
     *            频道Id
     * @param pageBean
     *            分页工具类
     * @return int
     */
    PageBean queryHotBySelectivePageSize(String keyword, Long tempid,
            Long channelid, PageBean pageBean);

    /**
     * 根据模板ID、频道ID查询热门搜索-前台展示用
     * 
     * @param map
     * @return int
     */
    List<HotSearch> selectHotBySelectiveForSite(Long tempid, Long channelid);
}
