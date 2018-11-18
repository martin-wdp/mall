/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.Street;
import com.ningpai.system.dao.StreetMapper;
import com.ningpai.system.util.AddressUtil;

/**
 * 街道Dao实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月13日 上午11:35:18
 * @version 1.0
 */
@Repository("StreetMapper")
public class StreetMapperImpl extends BasicSqlSupport implements StreetMapper {
    /**
     * 根据主键删除
     * 
     * @see com.ningpai.system.dao.StreetMapper#deleteByPrimaryKey(java.lang.Long)
     */
    public int deleteByPrimaryKey(Long streetId) {
        return this.update(
                "com.ningpai.system.dao.StreetMapper.deleteByPrimaryKey",
                streetId);
    }

    /**
     * 插入新纪录
     * 
     * @see com.ningpai.system.dao.StreetMapper#insertSelective(com.ningpai.system
     *      .bean.Street)
     */
    public int insertSelective(Street record) {
        return this.insert(
                "com.ningpai.system.dao.StreetMapper.insertSelective", record);
    }

    /**
     * 根据主键查询
     * 
     * @see com.ningpai.system.dao.StreetMapper#selectByPrimaryKey(java.lang.Long)
     */
    public Street selectByPrimaryKey(Long streetId) {
        return this.selectOne(
                "com.ningpai.system.dao.StreetMapper.selectByPrimaryKey",
                streetId);
    }

    /**
     * 更新记录
     * 
     * @see com.ningpai.system.dao.StreetMapper#updateByPrimaryKeySelective(com.ningpai
     *      .system.bean.Street)
     */
    public int updateByPrimaryKeySelective(Street record) {
        return this
                .update("com.ningpai.system.dao.StreetMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 根据参数查询列表
     * 
     * @see com.ningpai.system.dao.StreetMapper#queryAllStreetByPb(java.util.Map)
     */
    public List<Object> queryAllStreetByPb(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.system.dao.StreetMapper.queryAllStreetByPb", map);
    }

    /**
     * 根据参数查询行数
     * 
     * @see com.ningpai.system.dao.StreetMapper#queryTotalCount(java.util.Map)
     */
    public int queryTotalCount(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.system.dao.StreetMapper.queryTotalCount", map);
    }

    /**
     * 根据区县ID查询所有的街道
     * 
     * @see com.ningpai.system.dao.StreetMapper#queryStreetByDistrictId(java.lang
     *      .Long)
     */
    public List<Street> queryStreetByDistrictId(Long districtId) {
        return this.selectList(
                "com.ningpai.system.dao.StreetMapper.queryStreetByDistrictId",
                districtId);
    }

    /**
     * 根据街道名称查询是否已经存在
     * 
     * @see com.ningpai.system.dao.StreetMapper#queryStreetByStreetName(java.lang
     *      .String)
     */
    public int queryStreetByStreetName(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.system.dao.StreetMapper.queryStreetByStreetName",
                map);
    }

    /**
     * 根据街道ID查询所属的区县，城市和省份名称
     * 
     * @see com.ningpai.system.dao.StreetMapper#queryAddressNameByStreetId(java.lang
     *      .Long)
     */
    public AddressUtil queryAddressNameByStreetId(Long streetId) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.StreetMapper.queryAddressNameByStreetId",
                        streetId);
    }
}
