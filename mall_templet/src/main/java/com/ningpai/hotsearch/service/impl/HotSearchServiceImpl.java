/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.hotsearch.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.hotsearch.bean.HotSearch;
import com.ningpai.hotsearch.dao.HotSearchMapper;
import com.ningpai.hotsearch.service.HotSearchService;
import com.ningpai.util.PageBean;

/**
 * 热门搜索业务实现类
 * 
 * @author YANhb
 * 
 */
@Service("hotSearchService")
public class HotSearchServiceImpl implements HotSearchService {

    // spring注入
    @Resource
    private HotSearchMapper hotSearchMapper;

    /*
     * 根据主键Id，删除热门搜索记录
     * 
     * @see
     * com.ningpai.hotsearch.service.HotSearchService#removeHotSearchById(java
     * .lang.Long)
     */
    @Override
    public int removeHotSearchById(Long hotSearchId) {
        HotSearch hotSearch = hotSearchMapper.selectByPrimaryKey(hotSearchId);
        hotSearch.setDelFlag("1");
        return hotSearchMapper.updateByPrimaryKeySelective(hotSearch);
    }

    /*
     * 选择字段添加热门搜索记录
     * 
     * @see
     * com.ningpai.hotsearch.service.HotSearchService#addHotSearchSelective(
     * com.ningpai.hotsearch.bean.HotSearch)
     */
    @Override
    public int addHotSearchSelective(HotSearch hotSearch) {
        // 设置更新时间
        hotSearch.setUpdateDate(new Date());
        // 返回结果
        return hotSearchMapper.insertSelective(hotSearch);
    }

    /*
     * 根据主键ID，查询热门搜索表记录
     * 
     * @see
     * com.ningpai.hotsearch.service.HotSearchService#selectHotSearchById(java
     * .lang.Long)
     */
    @Override
    public HotSearch selectHotSearchById(Long hotSearchId) {
        return hotSearchMapper.selectByPrimaryKey(hotSearchId);
    }

    /*
     * 修改热门搜索记录可选择字段，根据主键Id
     * 
     * @see
     * com.ningpai.hotsearch.service.HotSearchService#modifyHostSearchSelectiveById
     * (com.ningpai.hotsearch.bean.HotSearch)
     */
    @Override
    public int modifyHostSearchSelectiveById(HotSearch hotSearch) {
        hotSearch.setUpdateDate(new Date());
        return hotSearchMapper.updateByPrimaryKeySelective(hotSearch);
    }

    /*
     * 根据条件查询分页热门搜索记录
     * 
     * @see
     * com.ningpai.hotsearch.service.HotSearchService#queryHotBySelectivePageSize
     * (java.lang.String, java.lang.Long, java.lang.Long,
     * com.ningpai.util.PageBean)
     */
    @Override
    public PageBean queryHotBySelectivePageSize(String keyword, Long tempid,
            Long channelid, PageBean pageBean) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // keyword - 关键字
            map.put("keyword", keyword);
            // tempid - 模板Id
            map.put("tempid", tempid);
            // channelid - 频道Id
            map.put("channelid", channelid);
            // 设置行数
            pageBean.setRows(hotSearchMapper.selectHotSearchCount(map));
            // 开始行数
            map.put("startRowNum", pageBean.getStartRowNum());
            // 结束行数
            map.put("endRowNum", pageBean.getEndRowNum());
            // 设置参数
            pageBean.setList(hotSearchMapper.selectHotBySelectivePageSize(map));
        } finally {
            // 置空
            map = null;
        }
        // 返回集合
        return pageBean;
    }

    /*
     * 批量删除
     * 
     * @see
     * com.ningpai.hotsearch.service.HotSearchService#batchDelHotSearch(java
     * .lang.Long[])
     */
    @Override
    public int batchDelHotSearch(Long[] hots) {
        int count = 0;
        List<Object> list = new ArrayList<Object>();
        try {
            if (hots != null && hots.length >= 0) {
                for (int i = 0; i < hots.length; i++) {
                    Long id = hots[i];
                    list.add(id);
                }
                count = hotSearchMapper.batchDelHotSearch(list);
            }
        } finally {
            list = null;
        }
        return count;
    }

    /*
     * 根据模板ID、频道ID查询热门搜索-前台展示用
     * 
     * @see
     * com.ningpai.hotsearch.service.HotSearchService#selectHotBySelectiveForSite
     * (java.lang.Long, java.lang.Long)
     */
    @Override
    public List<HotSearch> selectHotBySelectiveForSite(Long tempid,
            Long channelid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tempid", tempid);
        map.put("channelid", channelid);
        return this.hotSearchMapper.selectHotBySelectiveForSite(map);
    }
}
