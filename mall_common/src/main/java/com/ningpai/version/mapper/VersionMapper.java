/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.version.mapper;

import java.util.List;
import java.util.Map;

import com.ningpai.version.bean.Version;

/**
 * 版本Mapper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年6月26日 上午10:51:31
 * @version 0.0.1
 */
public interface VersionMapper {
    /**
     * 根据主键删除版本信息
     * @param versionId
     * @return
     */
    int deleteByPrimaryKey(Long versionId);

    /**
     * 插入信息
     * @param record
     * @return
     */
    int insert(Version record);

    /**
     * 插入信息
     * @param record
     * @return
     */
    int insertSelective(Version record);

    /**
     * 根据主键查询
     * @param versionId
     * @return
     */
    Version selectByPrimaryKey(Long versionId);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Version record);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Version record);

    /**
     * 查询版本信息
     * 
     * @return 版本信息 {@link List}
     * @see Version
     */
    List<Version> seleceVersion();

    /**
     * 查询版本条数
     * 
     * @param version
     * @return
     */
    Long selectVersionSize(Version version);

    /**
     * 查询版本集合
     * 
     * @param paramMap
     * @return
     */
    List<Object> selectAllVersion(Map<String, Object> paramMap);

    /**
     * 查询最新版本
     * 
     * @return
     */
    Version showNewVersion();
}
