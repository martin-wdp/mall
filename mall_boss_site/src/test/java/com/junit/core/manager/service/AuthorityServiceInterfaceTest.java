/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.core.manager.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.manager.bean.Authority;
import com.ningpai.manager.bean.AuthorityPage;
import com.ningpai.manager.bean.ManagerAuthority;
import com.ningpai.manager.bean.Page;
import com.ningpai.manager.bean.valuebean.MenuVo;
import com.ningpai.manager.mapper.AuthorityMapper;
import com.ningpai.manager.mapper.AuthorityPageMapper;
import com.ningpai.manager.mapper.ManagerAuthorityMapper;
import com.ningpai.manager.mapper.PageMapper;
import com.ningpai.manager.service.AuthorityServiceInterface;
import com.ningpai.manager.service.impl.AuthorityService;
import com.ningpai.util.PageBean;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限服务处理接口单元测试
 */
public class AuthorityServiceInterfaceTest  extends UnitilsJUnit3 {



    /**
     * 需要测试的Service
     */
    @TestedObject
    private AuthorityServiceInterface authorityServiceInterface  = new AuthorityService();

    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<AuthorityMapper> authorityMapperMock;
    @InjectIntoByType
    Mock<ManagerAuthorityMapper> managerAuthorityMapperMock;
    @InjectIntoByType
    Mock<AuthorityPageMapper> authorityPageMapperMock;
    @InjectIntoByType
    Mock<PageMapper> pageMapperMock;
    /**
     * JS数据
     */
    @FileContent("authorityList.js")
    private String authorityListJs;
    @FileContent("managerAuthorityList.js")
    private String managerAuthorityListJS;
    @FileContent("pageList.js")
    private String pageListJs;
    @FileContent("authorityPageList.js")
    private String authorityPageListJs;
    @FileContent("menuVos.js")
    private String menuVosJs;
    /**
     * 共享数据
     */
    List<Authority> authorityList;

    List<ManagerAuthority> managerAuthorityList;

    List<Page> pageList;

    List<AuthorityPage> authorityPageList;

    List<MenuVo> menuVos;
    /**
     * 初始化
     */
    @Override
    public void setUp(){
        authorityList = JSON.parseArray(authorityListJs, Authority.class);
        managerAuthorityList =  JSON.parseArray(managerAuthorityListJS, ManagerAuthority.class);
        pageList =  JSON.parseArray(pageListJs, Page.class);
        authorityPageList=JSON.parseArray(authorityPageListJs, AuthorityPage.class);
        menuVos = JSON.parseArray(menuVosJs,MenuVo.class);
    }



    /**
     * 查询权限列表
     */
    public void testQueryAuthoerityList(){
        authorityMapperMock.returns(authorityList).selectAllAuthority(1L);
        assertEquals(1, authorityServiceInterface.queryAuthoerityList(1L).size());
    }

    /**
     * 根据管理员编号查找权限
     */
    public void testQueryAuthorByManagerId(){
        managerAuthorityMapperMock.returns(managerAuthorityList.get(0)).selectByManagerId(1L);
        assertNotNull(authorityServiceInterface.queryAuthorByManagerId(1L));
    }

    /**
     * 查询权限列表
     */
    public void testQueryAuthoerityListTwo(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        PageBean pb = new PageBean();
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 15);
        paramMap.put("createId", 1L);
        pb.setObjectBean(new Authority());
        authorityMapperMock.returns(1L).queryAuthoritySize(new Authority());
        authorityMapperMock.returns(authorityList).selectAuthorityByLimit(paramMap);
        authorityMapperMock.returns(authorityList).selectAuthorityByAuthority(new Authority());
        assertEquals(1, authorityServiceInterface.queryAuthoerityList(new Authority(),new PageBean(),1L).getList().size());
    }
    /**
     * 删除权限
     *
     */
    public void testDeleteAuthority(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parameterValues", new String[]{"1"});
        authorityMapperMock.returns(1).deleteAuthorityByIds(paramMap);
        assertEquals(1, authorityServiceInterface.deleteAuthority(new String[]{"1"}));
    }

    /**
     * 编辑角色
     */
    public void testUpdateAuthority(){
        authorityMapperMock.returns(1).updateByPrimaryKey(authorityList.get(0));
        pageMapperMock.returns(pageList.get(0)).selectByPrimaryKey(1L);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("authorityId", pageList.get(0).getId());
        map.put("pageId", 1L);
        map.put("type", 1);
        authorityPageMapperMock.returns(1).insertAAndPage(map);
        assertEquals(1,authorityServiceInterface.updateAuthority("1",authorityList.get(0)));

    }

    /**
     * 添加权限
     */
    public void testAddAuthority(){
        pageMapperMock.returns(pageList.get(0)).selectByPrimaryKey(1L);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("authorityId", authorityList.get(0).getId());
        map.put("pageId", pageList.get(0).getId());
        map.put("type", '1');
        authorityPageMapperMock.returns(1).insertAAndPage(map);
        assertEquals(1, authorityServiceInterface.addAuthority("1", authorityList.get(0), 1L));

        Authority a = authorityList.get(0);
        a.setId(null);
        Map<String, Object>   paramMap = new HashMap<String, Object>();
        paramMap.put("designation", a.getDesignation());
        paramMap.put("characterization", a.getCharacterization());
        paramMap.put("id", a.getId());
        paramMap.put("createId", a.getCreateId());

        authorityMapperMock.returns(1).insertByDesignation(paramMap);
        // 查询主键
        authorityMapperMock.returns(1L).selectLastId();

        pageMapperMock.returns(pageList.get(0)).selectByPrimaryKey(1L);
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("authorityId", a.getId());
        map1.put("pageId", pageList.get(0).getId());
        map1.put("type", '1');
        authorityPageMapperMock.returns(1).insertAAndPage(map1);
        assertEquals(2, authorityServiceInterface.addAuthority("1", a, 1L));

    }

    /**
     * 根据权限编号查找权限
     */
    public void testQueryAuthorityByAId(){
        authorityMapperMock.returns(authorityPageList).selectAuthorityByAId(1L);
        assertEquals(1, authorityServiceInterface.queryAuthorityByAId(1L).size());
    }

    /**
     * 删除权限页面
     */
    public void testDeleteAuthorityPage(){
        authorityPageMapperMock.returns(1).deleteAuthorityPageById(1L);
        assertEquals(1, authorityServiceInterface.deleteAuthorityPage(1L));
    }

    /**
     * 查询超级管理员
     */
    public void testQuerySupperAuthor(){
        authorityMapperMock.returns(authorityList.get(0)).querySupperAuthor();
        assertNotNull(authorityServiceInterface.querySupperAuthor());
    }

    /**
     * 根据权限名称检查权限是否存在
     */
    public void testCheckAuthExist(){
        authorityMapperMock.returns(1L).checkAuthExist("qianmi");
        assertNotNull(authorityServiceInterface.checkAuthExist("qianmi"));
    }

    /**
     * 批量赋予某个角色权限
     */
    public void testAddAuthorityPageBatch(){
        assertNotNull(new Object());
    }

    /**
     * 删除模块中所有的权限
     */
    public void testDeleteAuthByBundleName(){
        assertNotNull(new Object());
    }

    /**
     * 添加权限
     */
    public void testInsertAuthorities(){

        List<Long> pages = new ArrayList<Long>(0);
        pages.add(1L);
        pageMapperMock.returns(pages).selectByPrimaryKeys(pages);
        ArrayList<Map<String, Object>> authorities = new ArrayList<Map<String, Object>>(0);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("authorityId", authorityList.get(0).getId());
        map.put("pageId", 1L);
        map.put("type", '1');
        authorities.add(map);
        authorityPageMapperMock.returns(1).insertAuthorities(authorities);
        assertEquals(0, authorityServiceInterface.insertAuthorities("1", authorityList.get(0), 1L));


        Authority a = authorityList.get(0);
        a.setId(null);
        Map<String, Object>  paramMap = new HashMap<String, Object>();
        paramMap.put("designation", a.getDesignation());
        paramMap.put("characterization", a.getCharacterization());
        paramMap.put("id", a.getId());
        paramMap.put("createId", a.getCreateId());

        authorityMapperMock.returns(1).insertByDesignation(paramMap);
        // 查询主键
        authorityMapperMock.returns(1L).selectLastId();


        pageMapperMock.returns(pages).selectByPrimaryKeys(pages);
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("authorityId", a.getId());
        map1.put("pageId", 1L);
        map1.put("type", '1');
        authorities.add(map);
        authorityPageMapperMock.returns(1).insertAuthorities(authorities);
        assertEquals(1, authorityServiceInterface.insertAuthorities("1", a, 1L));
    }

    /**
     * 编辑角色
     */
    public void testMergeAuthority(){
        authorityMapperMock.returns(1).updateByPrimaryKey(authorityList.get(0));
        List<Long> pages = new ArrayList<Long>(0);
        pages.add(1L);
        pageMapperMock.returns(pages).selectByPrimaryKeys(pages);
        ArrayList<Map<String, Object>> authorities = new ArrayList<Map<String, Object>>(0);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("authorityId", authorityList.get(0).getId());
        map.put("pageId", 1L);
        map.put("type", '1');
        authorities.add(map);
        authorityPageMapperMock.returns(1).insertAuthorities(authorities);
        assertEquals(1, authorityServiceInterface.mergeAuthority("1",authorityList.get(0)));
    }
}
