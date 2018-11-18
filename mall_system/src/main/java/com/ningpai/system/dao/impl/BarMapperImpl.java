/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.Bar;
import com.ningpai.system.dao.BarMapper;
import com.ningpai.system.util.SelectBean;

/**
 * 前台导航实现类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月19日 下午4:05:11
 * @version 1.0
 */
@Repository("barMapper")
public class BarMapperImpl extends BasicSqlSupport implements BarMapper {
    /**
     * 删除前台导航
     * 
     * @param barId
     * @return
     */
    public int deleteByPrimaryKey(Long barId) {
        return this.update(
                "com.ningpai.system.dao.BarMapper.deleteByPrimaryKey", barId);
    }

    /**
     * 添加前台导航
     * 
     * @param record
     * @return
     */
    public int insert(Bar record) {
        return 0;
    }

    /**
     * 插入导航信息
     * 
     * @param record
     * @return int
     */
    public int insertSelective(Bar record) {
        // 插入导航信息
        return this.insert("com.ningpai.system.dao.BarMapper.insertSelective",
                record);
    }

    /**
     * 根据ID查询导航信息
     * 
     * @param barId
     * @return Bar
     */
    public Bar selectByPrimaryKey(Long barId) {
        // 查询单条导航信息
        return this.selectOne(
                "com.ningpai.system.dao.BarMapper.selectByPrimaryKey", barId);
    }

    /**
     * 修改导航信息
     * 
     * @param record
     * @return int
     */
    public int updateByPrimaryKeySelective(Bar record) {
        // 修改导航信息
        return this.update(
                "com.ningpai.system.dao.BarMapper.updateByPrimaryKeySelective",
                record);
    }

    /**
     * 修改导航信息
     * 
     * @param record
     * @return int
     */
    public int updateByPrimaryKey(Bar record) {
        return 0;
    }

    /**
     * 分页查询导航菜单信息
     * 
     * @param map
     * @return
     */
    public List<Object> findByPageBean(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.system.dao.BarMapper.findByPageBean", map);
    }

    /**
     * 查询总行数
     * 
     * @return int
     */
    public int findTotalCount(SelectBean selectBean) {

        return this.selectOne(
                "com.ningpai.system.dao.BarMapper.findTotalCount", selectBean);
    }

}
