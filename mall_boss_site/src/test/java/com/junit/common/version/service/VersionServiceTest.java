/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.common.version.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.util.PageBean;
import com.ningpai.version.bean.Version;
import com.ningpai.version.mapper.VersionMapper;
import com.ningpai.version.service.VersionService;
import com.ningpai.version.service.impl.VersionServiceImpl;
import com.ningpai.version.util.CommonConstant;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统日志Service测试
 */
public class VersionServiceTest extends UnitilsJUnit3 {

    /**
     * 需要测试的Service
     */
    @TestedObject
    private VersionService versionService = new VersionServiceImpl();

    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<VersionMapper> versionMapperMock;
    /**
     * JS数据
     */
    @FileContent("versionList.js")
    private String versionListJs;


    /**
     * 共享数据
     */
    List<Version> versionList;
    /**
     * 初始化
     */
    @Override
    public void setUp(){
        versionList = JSON.parseArray(versionListJs, Version.class);
    }

    /**
     * 查询版本信息
     */
    public void testSeleceVersion(){
        versionMapperMock.returns(versionList).seleceVersion();
        assertEquals(1, versionService.seleceVersion().size());
    };

    /**
     * 查询所有版本 分页
     */
    public void testSelectAllVersion(){
        versionMapperMock.returns(1L).selectVersionSize(new Version());
        Map<String, Object>  paramMap = new HashMap<String, Object>();
        paramMap.put("version", new Version());
        paramMap.put(CommonConstant.STARTNUM, 0);
        paramMap.put(CommonConstant.ENDNUM, 15);
        versionMapperMock.returns(versionList).selectAllVersion(paramMap);
        assertEquals(1, versionService.selectAllVersion(new PageBean(), new Version()).getList().size());
    }

    /**
     * 增加版本信息
     */
    public void testAddVersion(){
        versionMapperMock.returns(1).insertSelective(versionList.get(0));
        assertEquals(1, versionService.addVersion(versionList.get(0),"2015-01-01 01:01:01"));
    }


    /**
     * 修改版本信息
     */
    public void testUpdateVersion(){
        versionMapperMock.returns(1).updateByPrimaryKeySelective(versionList.get(0));
        assertEquals(1, versionService.updateVersion(versionList.get(0), "2015-01-01 01:01:01"));
    };

    /**
     * 查询版本信息
     */
    public void testShowVersion(){
        versionMapperMock.returns(versionList.get(0)).selectByPrimaryKey(1L);
        assertNotNull(versionService.showVersion(1L));
    }

    /**
     * 查询最新版本
     */
    public void testShowNewVersion(){
        versionMapperMock.returns(versionList.get(0)).showNewVersion();
        assertNotNull(versionService.showNewVersion());

    }

}
