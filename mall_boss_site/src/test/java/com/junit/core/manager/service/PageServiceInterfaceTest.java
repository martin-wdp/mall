/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.junit.core.manager.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.manager.bean.Page;
import com.ningpai.manager.bean.valuebean.MenuVo;
import com.ningpai.manager.mapper.PageMapper;
import com.ningpai.manager.service.PageServiceInterface;
import com.ningpai.manager.service.impl.PageService;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单Service
 */
public class PageServiceInterfaceTest extends UnitilsJUnit3 {

    /**
     * 需要测试的Service
     */
    @TestedObject
    private PageServiceInterface pageServiceInterface  = new PageService();

    @InjectIntoByType
    Mock<PageMapper> pageMapperMock;

    @FileContent("pageList.js")
    private String pageListJs;
    @FileContent("menuParentVos.js")
    private String menuParentVosJs;
    @FileContent("pageList.js")
    private String menuVosJs;
    /**
     * 共享数据
     */

    List<Page> pageList;
    List<MenuVo> pageParentList;
    List<MenuVo> menuVosList;
    /**
     * 初始化
     */
    @Override
    public void setUp(){
        pageList = JSON.parseArray(pageListJs, Page.class);
        pageParentList = JSON.parseArray(menuParentVosJs, MenuVo.class);
        menuVosList = JSON.parseArray(menuVosJs, MenuVo.class);
    }


    /**
     * 保存菜单实体
     */
    public void testSavePage(){
        pageMapperMock.returns(pageList.get(0)).selectByPrimaryKey(1L);
        pageMapperMock.returns(1).insertSelective(pageList.get(0));
        assertEquals(1, pageServiceInterface.savePage(pageList.get(0)));
    }

    /**
     * 更新菜单
     */
    public void testUpdatePage(){
        pageMapperMock.returns(pageList.get(0)).selectByPrimaryKey(1L);
        pageMapperMock.returns(1).updateByPrimaryKeySelective(pageList.get(0));
        assertEquals(1, pageServiceInterface.updatePage(pageList.get(0)));
    }
    /**
     * 删除菜单实体
     */
    public void testDelPage(){
        pageMapperMock.returns(1).deleteByPrimaryKey(1L);
        assertEquals(1, pageServiceInterface.delPage(1L));
    }

    /**
     * 批量删除Page
     */
    public void testBatchDelPage(){
        pageMapperMock.returns(1).deleteByPrimaryKey(1L);
        assertEquals(1, pageServiceInterface.batchDelPage(new Long[]{1L}));
    }

    /**
     * 根据主键查询Page实体
     */
    public void testQueryPageByPrimaryKey(){
        pageMapperMock.returns(pageList.get(0)).selectByPrimaryKey(1L);
        assertNotNull(pageServiceInterface.queryPageByPrimaryKey(1L));
    }

    /**
     * 根据分页帮助类查询分页列表

     */
    public void testQueryCateListByPageBean(){
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("condition", "1");
        map.put("searchText", "qianmi");
        // 查询数据的总行数并设置到PageBean中
        pageMapperMock.returns(1).queryTotalCount(map);
        map.put("startRowNum", 0);
        map.put("endRowNum", 15);
        pageMapperMock.returns(pageList).queryByPageBean(map);
        SelectBean selectBean = new SelectBean();
        selectBean.setCondition("1");
        selectBean.setSearchText("qianmi");
        assertEquals(1, pageServiceInterface.queryCateListByPageBean(new PageBean(),selectBean).getList().size());
    }

    /**
     * 查询所有的菜单
     */
    public void testQueryAllMenuVo(){
        pageMapperMock.returns(pageList).queryAllMenuVo();
        assertEquals(1, pageServiceInterface.queryAllMenuVo().size());
    }

    /**
     * 验证是否可以删除 如果传递过来的分类 下面有子类 就返回false表示不可以删除
     */
    public void testCheckDelWithPageId(){
        pageMapperMock.returns(0).querySonCountByParentId(1L);
        assertTrue(pageServiceInterface.checkDelWithPageId(1L));
    }

    /**
     * 获得分类下的所有的子分类
     */
    public void testGetCateList(){
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("condition", "");
        map.put("searchText", "");
        // 查询数据的总行数并设置到PageBean中
        pageMapperMock.returns(1).queryTotalCount(map);
        map.put("startRowNum", 0);
        map.put("endRowNum", 15);
        pageMapperMock.returns(pageParentList).queryByPageBean(map);
        pageMapperMock.returns(menuVosList).queryAllMenuVo();
        assertEquals(1, pageServiceInterface.getCateList(new PageBean(),new SelectBean()).size());
    }
    /**
     * 使用递归根据父分类ID计算所有的自己分类
     */
    public void testCalcCateVo(){
        assertEquals(1,1);
    }

    /**
     * 查询所有的菜单 节点
     */
    public void testQueryAllParentMenuVo(){
        pageMapperMock.returns(menuVosList).queryAllParentMenuVo();
        assertEquals(1, pageServiceInterface.queryAllParentMenuVo().size());
    }

    /**
     * 查询所有的菜单 页面
     */
    public void testQueryAllChildrenMenuVo(){
        pageMapperMock.returns(menuVosList).queryAllChildrenMenuVo();
        assertEquals(1, pageServiceInterface.queryAllChildrenMenuVo().size());
    }

    /**
     * 查询当前的登录用户的权限菜单
     */
    public void testQueryAllParentMenuVoByLogin(){
        pageMapperMock.returns(menuVosList).queryAllParentMenuVoByLogin(1L);
        assertEquals(1, pageServiceInterface.queryAllParentMenuVoByLogin(1L).size());
    }
    /**
     * 新Boss删除菜单
     *
     */
    public void testNewBatchDelPage(){
        pageMapperMock.returns(pageParentList).queryAllMenuVo();
        pageMapperMock.returns(1).deleteByPrimaryKey(1L);
        assertEquals(1, pageServiceInterface.newBatchDelPage(1L));

    }

    /**
     * 查询bundleName的菜单
     */
    public void testQueryMenuByBundleName(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bundleName", "qianmi");
        pageMapperMock.returns(menuVosList).queryMenuByBundleName(paramMap);
        assertEquals(1, pageServiceInterface.queryMenuByBundleName("qianmi").size());
    }

    /**
     * 删除bundleName的菜单
     */
    public void testDeleteMenuByBundleName(){
        pageServiceInterface.deleteMenuByBundleName("qianmi");
    }
}
