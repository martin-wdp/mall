/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.third.auth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import com.alibaba.fastjson.JSON;
import com.ningpai.customer.bean.Customer;
import com.ningpai.third.auth.bean.ThirdManager;
import com.ningpai.third.auth.bean.ThirdPage;
import com.ningpai.third.auth.mapper.ThirdAuthorityMapper;
import com.ningpai.third.auth.mapper.ThirdAuthorityPageMapper;
import com.ningpai.third.auth.mapper.ThirdManagerAuthorityMapper;
import com.ningpai.third.auth.mapper.ThirdManagerMapper;
import com.ningpai.third.auth.service.ThirdAuthorityPageService;
import com.ningpai.third.auth.service.impl.ThirdAuthorityPageServiceImpl;

/**
 * 
 * @author jiping
 * @since 2015年9月23日 下午7:11:04
 * @version
 */
public class ThirdAuthorityPageServiceTest extends UnitilsJUnit3 {

    /**
     * 需要测试的类
     */
    @TestedObject
    private ThirdAuthorityPageService thirdAuthorityPageService = new ThirdAuthorityPageServiceImpl();

    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<ThirdManagerMapper> thirdManagerMapperMock;
    @InjectIntoByType
    Mock<ThirdManagerAuthorityMapper> managerAuthorityMapperMock;
    @InjectIntoByType
    Mock<ThirdAuthorityPageMapper> authorityPageMapperMock;
    @InjectIntoByType
    Mock<ThirdAuthorityMapper> authorityMapperMock;

    /**
     * JS数据
     */
    @FileContent("thirdPageList.js")
    private String thirdPageListJs;
    @FileContent("thirdManagerList.js")
    private String thirdManagerListJs;

    /**
     * 共享数据
     */
    List<ThirdManager> thirdManagerList;
    List<ThirdPage> thirdPageList;

    /**
     * 初始化
     */
    public void setUp() {
        thirdManagerList = JSON.parseArray(thirdManagerListJs,
                ThirdManager.class);
        thirdPageList = JSON.parseArray(thirdPageListJs, ThirdPage.class);
    }

    /**
     * 查询用户权限页面
     */
    @Test
    public void testQueryMenuByManager() {
        thirdManagerMapperMock.returns(new Customer()).selectCustByCid(1L);
        assertNotNull(thirdAuthorityPageService.queryMenuByManager(1L));
    }

    /**
     * 根据商家编号查找商家角色列表
     */
    @Test
    public void testQueryThirdManagerByStoreId() {
        thirdManagerMapperMock.returns(thirdManagerList)
                .queryThirdManagerByStoreId(1L);
        assertNotNull(thirdAuthorityPageService.queryThirdManagerByStoreId(1L));
    }

    /**
     * 查找商家权限页面列表
     */
    @Test
    public void testLoadAllAuthority() {
        thirdManagerMapperMock.returns(thirdPageList).selectAllAuthority();
        assertNotNull(thirdAuthorityPageService.loadAllAuthority());
    }

    /**
     * 根据权限编号查找权限页面
     */
    @Test
    public void testQueryThirdPageByAuthId() {
        authorityPageMapperMock.returns(thirdPageList)
                .selectAllPageByAuthorityID(1L);
        assertNotNull(thirdAuthorityPageService.queryThirdPageByAuthId(1L));
    }

    /**
     * 更新管理员权限
     */
    @Test
    public void testUpdateAuthority() {
        // 获取当前存在的权限页面
        authorityPageMapperMock.returns(thirdPageList)
                .selectAllPageByAuthorityID(1L);
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String[] pagesId = new String[] { "1" };
        List<Long> delNums = new ArrayList<Long>();
        List<Long> addNums = new ArrayList<Long>();
        delNums.add(thirdPageList.get(0).getId());
        paramMap.put("authorityId", 1);
        paramMap.put("preIds", delNums);
        // 删除权限
        authorityPageMapperMock.returns(1).delPageToAuthority(paramMap);
        addNums.add(Long.parseLong(pagesId[0]));
        paramMap = new HashMap<String, Object>();
        paramMap.put("authorityId", 1);
        paramMap.put("pageId", new ArrayList<Long>(1));
        // 添加权限
        authorityPageMapperMock.returns(1).addPageToAuthority(paramMap);
        assertEquals(null,
                thirdAuthorityPageService.updateAuthority(request, pagesId, 1L));
    }
}
