/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.Bar;
import com.ningpai.system.util.SelectBean;

/**
 * 前台导航接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月19日 下午4:04:19
 * @version 1.0
 */
public interface BarMapper {
    /**
     * 删除前台导航
     * 
     * @param barId
     * @return
     */
    int deleteByPrimaryKey(Long barId);

    /**
     * 添加前台导航
     * 
     * @param record
     * @return
     */
    int insert(Bar record);

    /**
     * 插入导航信息
     * 
     * @param record
     * @return int
     */
    int insertSelective(Bar record);

    /**
     * 根据ID查询导航信息
     * 
     * @param barId
     * @return Bar
     */
    Bar selectByPrimaryKey(Long barId);

    /**
     * 修改导航信息
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Bar record);

    /**
     * 修改导航信息
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Bar record);

    /**
     * 分页查询导航菜单信息
     * 
     * @param map
     * @return
     */
    List<Object> findByPageBean(Map<String, Object> map);

    /**
     * 查询总行数
     * 
     * @return int
     */
    int findTotalCount(SelectBean selectBean);
}
