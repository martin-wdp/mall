/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.WebCert;
import com.ningpai.system.dao.WebCertMapper;

/**
 * DAO实现类-网站认证
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年6月5日下午4:41:32
 */
@Repository("WebCertMapper")
public class WebCertMapperImpl extends BasicSqlSupport implements WebCertMapper {

    /**
     * 根据主键删除
     * 
     * @see
     * com.ningpai.system.dao.WebCertMapper#deleteByPrimaryKey(java.lang.Long)
     */
    public int deleteByPrimaryKey(Long certificationId) {

        return this.delete(
                "com.ningpai.system.dao.WebCertMapper.deleteByPrimaryKey",
                certificationId);
    }

    /**
     * 添加
     * 
     * @see
     * com.ningpai.system.dao.WebCertMapper#insert(com.ningpai.system.bean.WebCert
     * )
     */
    public int insert(WebCert record) {

        return this.insert("com.ningpai.system.dao.WebCertMapper.insert",
                record);
    }

    /**
     * 添加-字段可选
     * 
     * @see
     * com.ningpai.system.dao.WebCertMapper#insertSelective(com.ningpai.system
     * .bean.WebCert)
     */
    public int insertSelective(WebCert record) {

        return this.insert(
                "com.ningpai.system.dao.WebCertMapper.insertSelective", record);
    }

    /**
     * 修改-字段可选
     * 
     * @see
     * com.ningpai.system.dao.WebCertMapper#updateByPrimaryKeySelective(com.
     * ningpai.system.bean.WebCert)
     */
    public int updateByPrimaryKeySelective(WebCert record) {

        return this
                .update("com.ningpai.system.dao.WebCertMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 修改
     * 
     * @see
     * com.ningpai.system.dao.WebCertMapper#updateByPrimaryKey(com.ningpai.system
     * .bean.WebCert)
     */
    public int updateByPrimaryKey(WebCert record) {

        return this.update(
                "com.ningpai.system.dao.WebCertMapper.updateByPrimaryKey",
                record);
    }

    /**
     * 根据ID查询
     * 
     * @see
     * com.ningpai.system.dao.WebCertMapper#selectByPrimaryKey(java.lang.Long)
     */
    public WebCert selectByPrimaryKey(Long certificationId) {

        return this.selectOne(
                "com.ningpai.system.dao.WebCertMapper.selectByPrimaryKey",
                certificationId);
    }

    /**
     * 查询所有行数-分页用
     * 
     * @see com.ningpai.system.dao.WebCertMapper#selectCountByPb()
     */
    public Integer selectCountByPb() {
        return this
                .selectOne("com.ningpai.system.dao.WebCertMapper.selectCountByPb");
    }

    /**
     * 根据分页参数查询认证
     * 
     * @see com.ningpai.system.dao.WebCertMapper#selectAllByPb(java.util.Map)
     */
    public List<Object> selectAllByPb(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.system.dao.WebCertMapper.selectAllByPb", map);
    }

    /**
     * 查询所有认证-前台展示用
     * 
     * @see com.ningpai.system.dao.WebCertMapper#selectAll()
     */
    public List<WebCert> selectAll() {
        return this
                .selectList("com.ningpai.system.dao.WebCertMapper.selectAll");
    }
}
