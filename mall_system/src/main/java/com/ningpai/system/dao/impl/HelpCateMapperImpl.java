/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.HelpCate;
import com.ningpai.system.dao.HelpCateMapper;
import com.ningpai.system.util.SelectBean;

/**
 * 帮助中心分类接口实现类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月20日 上午11:08:21
 * @version 1.0
 */
@Repository("helpCateMapper")
public class HelpCateMapperImpl extends BasicSqlSupport implements
        HelpCateMapper {
    /**
     * 删除帮助分类
     * 
     * @param helpcateId
     * @return int
     */
    public int deleteByPrimaryKey(Long helpcateId) {
        return this.update(
                "com.ningpai.system.dao.HelpCateMapper.deleteByPrimaryKey",
                helpcateId);
    }

    /**
     * 插入帮助分类
     * 
     * @param record
     * @return
     */
    public int insert(HelpCate record) {
        return 0;
    }

    /**
     * 插入帮助分类--可选字段
     * 
     * @param record
     * @return int
     */
    public int insertSelective(HelpCate record) {
        return this
                .insert("com.ningpai.system.dao.HelpCateMapper.insertSelective",
                        record);
    }

    /**
     * 根据分类id查询信息
     * 
     * @param helpcateId
     * @return HelpCate
     */
    public HelpCate selectByPrimaryKey(Long helpcateId) {

        return this.selectOne(
                "com.ningpai.system.dao.HelpCateMapper.selectByPrimaryKey",
                helpcateId);
    }

    /**
     * 修改帮助分类--可选字段
     * 
     * @param record
     * @return int
     */
    public int updateByPrimaryKeySelective(HelpCate record) {

        return this
                .update("com.ningpai.system.dao.HelpCateMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 修改帮助分类
     * 
     * @param record
     * @return int
     */
    public int updateByPrimaryKey(HelpCate record) {
        return 0;
    }

    /**
     * 分页查询帮助分类
     * 
     * @param map
     * @return List
     */
    public List<Object> findByPageBean(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.system.dao.HelpCateMapper.findByPageBean", map);
    }

    /**
     * 查询帮助分类总行数
     * 
     * @return int
     */
    public int findTotalCount(SelectBean selectBean) {
        return this.selectOne(
                "com.ningpai.system.dao.HelpCateMapper.findTotalCount",
                selectBean);
    }

    /**
     * 查询所有帮助分类
     * 
     * @return List
     */
    public List<HelpCate> findAll() {

        return this.selectList("com.ningpai.system.dao.HelpCateMapper.findAll");
    }

}
