/*
 * Copyright 2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.InterLogin;

/**
 * 登录接口层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月16日 上午11:39:25
 * @version
 */
public interface InterLoginMapper {
    /**
     * 删除登录接口信息
     * 
     * @param loginId
     * @return
     */
    int deleteByPrimaryKey(Long loginId);

    /**
     * 插入一条登录接口信息
     * 
     * @param record
     * @return int
     */
    int insert(InterLogin record);

    /**
     * 插入一条登录接口信息--可选字段
     * 
     * @param record
     * @return int
     */
    int insertSelective(InterLogin record);

    /**
     * 查询单个登录接口信息
     * 
     * @param loginId
     * @return
     */
    InterLogin selectByPrimaryKey(Long loginId);

    /**
     * 修改登录接口信息--可选字段
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(InterLogin record);

    /**
     * 修改登录接口信息
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(InterLogin record);

    /**
     * 分页查询登录接口信息
     * 
     * @param map
     * @return
     */
    List<Object> findByPageBean(Map<String, Integer> map);

    /**
     * 查询总行数
     * 
     * @return int
     */
    int findTotalCount();
}
