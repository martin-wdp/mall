/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.ErrorPage;
import com.ningpai.system.dao.ErrorPageMapper;

/**
 * 异常页面DAO实现类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年2月25日 15:51:25
 * @version 1.0
 */
@Repository("ErrorPageMapper")
public class ErrorPageMapperImpl extends BasicSqlSupport implements
        ErrorPageMapper {
    /**
     * 根据主键删除
     * 
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(Long id) {

        return this
                .delete("com.ningpai.system.dao.ErrorPageMapper.deleteByPrimaryKey",
                        id);
    }

    /**
     * 添加异常页面
     * 
     * @param record
     * @return
     */
    public int insert(ErrorPage record) {

        return this.insert("com.ningpai.system.dao.ErrorPageMapper.insert",
                record);
    }

    /**
     * 添加异常页面-字段可选
     * 
     * @param record
     * @return
     */
    public int insertSelective(ErrorPage record) {

        return this.insert(
                "com.ningpai.system.dao.ErrorPageMapper.insertSelective",
                record);
    }

    /**
     * 更新异常页面-字段可选
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(ErrorPage record) {

        return this
                .update("com.ningpai.system.dao.ErrorPageMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 更新异常页面-包含提示内容
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKeyWithBLOBs(ErrorPage record) {

        return this
                .update("com.ningpai.system.dao.ErrorPageMapper.updateByPrimaryKeyWithBLOBs",
                        record);
    }

    /**
     * 更新异常页面
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKey(ErrorPage record) {

        return this.update(
                "com.ningpai.system.dao.ErrorPageMapper.updateByPrimaryKey",
                record);
    }

    /**
     * 根据主键查询异常页面
     * 
     * @param id
     * @return
     */
    public ErrorPage selectByPrimaryKey(Long id) {

        return this
                .selectOne(
                        "com.ningpai.system.dao.ErrorPageMapper.selectByPrimaryKey",
                        id);
    }

    /**
     * 查询所有异常页面的数量-分页用
     * 
     * @return
     */
    public Integer queryTotalCount() {

        return this
                .selectOne("com.ningpai.system.dao.ErrorPageMapper.queryTotalCount");
    }

    /**
     * 根据分页参数查询异常页面列表
     * 
     * @param map
     * @return
     */
    public List<Object> queryByPageBean(Map<String, Object> map) {

        return this.selectList(
                "com.ningpai.system.dao.ErrorPageMapper.queryByPageBean", map);
    }

}
