/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.InterLogin;
import com.ningpai.system.dao.InterLoginMapper;

/**
 * 登录接口实现层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月16日 上午11:40:57
 * @version
 */
@Repository("interLoginMapper")
public class InterLoginMapperImpl extends BasicSqlSupport implements
        InterLoginMapper {
    /**
     * 删除登录接口信息
     * 
     * @param loginId
     * @return
     */
    public int deleteByPrimaryKey(Long loginId) {
        return this.update(
                "com.ningpai.system.dao.InterLoginMapper.deleteByPrimaryKey",
                loginId);
    }

    /**
     * 插入一条登录接口信息
     * 
     * @param record
     * @return int
     */
    public int insert(InterLogin record) {
        return 0;
    }

    /**
     * 插入一条登录接口信息--可选字段
     * 
     * @param record
     * @return int
     */
    public int insertSelective(InterLogin record) {

        return this.insert(
                "com.ningpai.system.dao.InterLoginMapper.insertSelective",
                record);
    }

    /**
     * 查询单个登录接口信息
     * 
     * @param loginId
     * @return
     */
    public InterLogin selectByPrimaryKey(Long loginId) {
        // 查询单条登录接口信息
        return this.selectOne(
                "com.ningpai.system.dao.InterLoginMapper.selectByPrimaryKey",
                loginId);
    }

    /**
     * 修改登录接口信息--可选字段
     * 
     * @param record
     * @return int
     */
    public int updateByPrimaryKeySelective(InterLogin record) {

        return this
                .update("com.ningpai.system.dao.InterLoginMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 修改登录接口信息
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKey(InterLogin record) {

        return 0;
    }

    /**
     * 分页查询登录接口信息
     * 
     * @param map
     * @return
     */
    public List<Object> findByPageBean(Map<String, Integer> map) {
        // 查询登录接口信息
        return this.selectList(
                "com.ningpai.system.dao.InterLoginMapper.findByPageBean", map);
    }

    /**
     * 查询总行数
     * 
     * @return int
     */
    public int findTotalCount() {
        // 查询总行数
        return this
                .selectOne("com.ningpai.system.dao.InterLoginMapper.findTotalCount");
    }

}
