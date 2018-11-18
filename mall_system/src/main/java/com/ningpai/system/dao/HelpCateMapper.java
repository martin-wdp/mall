/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.HelpCate;
import com.ningpai.system.util.SelectBean;

/**
 * 帮助中心分类接口层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月20日 上午11:07:46
 * @version 1.0
 */
public interface HelpCateMapper {

    /**
     * 删除帮助分类
     * 
     * @param helpcateId
     * @return int
     */
    int deleteByPrimaryKey(Long helpcateId);

    /**
     * 插入帮助分类
     * 
     * @param record
     * @return
     */
    int insert(HelpCate record);

    /**
     * 插入帮助分类--可选字段
     * 
     * @param record
     * @return int
     */
    int insertSelective(HelpCate record);

    /**
     * 根据分类id查询信息
     * 
     * @param helpcateId
     * @return HelpCate
     */
    HelpCate selectByPrimaryKey(Long helpcateId);

    /**
     * 修改帮助分类--可选字段
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(HelpCate record);

    /**
     * 修改帮助分类
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(HelpCate record);

    /**
     * 分页查询帮助分类
     * 
     * @param map
     * @return List
     */
    List<Object> findByPageBean(Map<String, Object> map);

    /**
     * 查询帮助分类总行数
     * 
     * @return int
     */
    int findTotalCount(SelectBean selectBean);

    /**
     * 查询所有帮助分类
     * 
     * @return List
     */
    List<HelpCate> findAll();
}
