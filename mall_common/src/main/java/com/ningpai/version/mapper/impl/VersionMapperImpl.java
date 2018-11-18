/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.version.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.version.bean.Version;
import com.ningpai.version.mapper.VersionMapper;

/**
 * @see com.ningpai.version.mapper.VersionMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年6月26日 上午10:48:46
 * @version 0.0.1
 */
@Repository("versionMapper")
public class VersionMapperImpl extends BasicSqlSupport implements VersionMapper {
    /*
     * 查询版本信息
     * 
     * @see com.ningpai.version.mapper.VersionMapper#seleceVersion()
     */
    @Override
    public List<Version> seleceVersion() {
        return this.selectList("com.ningpai.version.mapper.VersionMapper.seleceVersion");
    }

    /*
     * 查询版本条数
     * 
     * @see com.ningpai.version.mapper.VersionMapper#selectVersionSize(com.ningpai .version.bean.Version)
     */
    @Override
    public Long selectVersionSize(Version version) {
        return this.selectOne("com.ningpai.version.mapper.VersionMapper.selectVersionSize", version);
    }

    /*
     * 查询版本集合
     * 
     * @see com.ningpai.version.mapper.VersionMapper#selectAllVersion(java.util.Map)
     */
    @Override
    public List<Object> selectAllVersion(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.version.mapper.VersionMapper.selectAllVersion", paramMap);
    }

    /*
     * 根据主键删除版本信息
     * 
     * @see com.ningpai.version.mapper.VersionMapper#deleteByPrimaryKey(java.lang .Long)
     */
    @Override
    public int deleteByPrimaryKey(Long versionId) {
        return 0;
    }

    /*
     * 插入信息
     * 
     * @see com.ningpai.version.mapper.VersionMapper#insert(com.ningpai.version.bean .Version)
     */
    @Override
    public int insert(Version record) {
        return 0;
    }

    /*
     * 插入信息
     * 
     * @see com.ningpai.version.mapper.VersionMapper#insertSelective(com.ningpai. version.bean.Version)
     */
    @Override
    public int insertSelective(Version record) {
        return this.insert("com.ningpai.version.mapper.VersionMapper.insertSelective", record);
    }

    /*
     * 根据主键查询
     * 
     * @see com.ningpai.version.mapper.VersionMapper#selectByPrimaryKey(java.lang .Long)
     */
    @Override
    public Version selectByPrimaryKey(Long versionId) {
        return this.selectOne("com.ningpai.version.mapper.VersionMapper.selectByPrimaryKey", versionId);
    }

    /*
     * 更新
     * 
     * @see com.ningpai.version.mapper.VersionMapper#updateByPrimaryKeySelective( com.ningpai.version.bean.Version)
     */
    @Override
    public int updateByPrimaryKeySelective(Version record) {
        return this.update("com.ningpai.version.mapper.VersionMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 更新
     * 
     * @see com.ningpai.version.mapper.VersionMapper#updateByPrimaryKey(com.ningpai .version.bean.Version)
     */
    @Override
    public int updateByPrimaryKey(Version record) {
        return 0;
    }

    /*
     * 查询最新版本
     * 
     * @see com.ningpai.version.mapper.VersionMapper#showNewVersion()
     */
    @Override
    public Version showNewVersion() {
        return this.selectOne("com.ningpai.version.mapper.VersionMapper.showNewVersion");
    }

}
