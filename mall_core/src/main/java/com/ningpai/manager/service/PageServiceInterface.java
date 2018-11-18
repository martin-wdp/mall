/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.manager.service;

import java.util.List;

import com.ningpai.manager.bean.Page;
import com.ningpai.manager.bean.valuebean.MenuVo;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

/**
 * 菜单Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月8日 下午10:06:14
 * @version 1.0
 */
public interface PageServiceInterface {
    /**
     * 保存菜单实体
     * 
     * @param page
     *            菜单实体
     * @return 保存的行数
     */
    int savePage(Page page);

    /**
     * 更新菜单
     * 
     * @param page
     *            待更新的菜单实体
     * @return 更新的行行数
     */
    int updatePage(Page page);

    /**
     * 删除菜单实体
     * 
     * @param pageId
     *            待删除的菜单的ID
     * @return 删除的行数
     */
    int delPage(Long pageId);

    /**
     * 批量删除Page
     * 
     * @param pageIds
     * @return
     */
    int batchDelPage(Long[] pageIds);

    /**
     * 根据主键查询Page实体
     * 
     * @param pageId
     *            主键ID
     * @return 查询到的Page实体
     */
    Page queryPageByPrimaryKey(Long pageId);

    /**
     * 根据分页帮助类查询分页列表
     * 
     * @param pb
     *            {@link com.ningpai.goods.util.PageBean}
     * @return {@link com.ningpai.goods.util.PageBean}
     */
    PageBean queryCateListByPageBean(PageBean pb, SelectBean selectBean);

    /**
     * 查询所有的菜单
     * 
     * @return 查询到的列表
     */
    List<MenuVo> queryAllMenuVo();

    /**
     * 验证是否可以删除 如果传递过来的分类 下面有子类 就返回false表示不可以删除
     * 
     * @param specId
     *            将要删除的分类ID
     * @return 是否可以删除
     */
    boolean checkDelWithPageId(Long pageId);

    /**
     * 获得分类下的所有的子分类
     * 
     * @param pb
     *            分页辅助类 {@link com.ningpai.util.PageBean}
     * @return 整理好的分类列表
     */
    List<Object> getCateList(PageBean pb, SelectBean selectBean);

    /**
     * 使用递归根据父分类ID计算所有的自己分类
     * 
     * @param parentId
     *            第一级的父分类ID
     * @param allCateList
     *            所有的分类 {@link java.util.List}
     *            {@link com.ningpai.goods.vo.GoodsCateVo}
     * @return 计算好的分类实体
     */
    List<MenuVo> calcCateVo(Long parentId, List<MenuVo> allmenuVo);

    /**
     * 查询所有的菜单 节点
     * 
     * @return 查询到的列表
     */
    List<MenuVo> queryAllParentMenuVo();

    /**
     * 查询所有的菜单 页面
     * 
     * @return 查询到的列表
     */
    List<MenuVo> queryAllChildrenMenuVo();

    /**
     * 查询当前的登录用户的权限菜单
     * 
     * @param managerId
     * @return List
     */
    List<MenuVo> queryAllParentMenuVoByLogin(Long managerId);

    /**
     * 新Boss删除菜单
     * 
     * @param pageId
     * @return int
     */
    int newBatchDelPage(Long pageId);

    /**
     * 查询bundleName的菜单
     * @param bundleName 模块配置的bundleName
     * @return 菜单集合 List
     */
    List<MenuVo> queryMenuByBundleName(String bundleName);


    /**
     * 删除bundleName的菜单
     * @param bundleName 模块配置的bundleName
     * @return 菜单集合 List
     */
    void deleteMenuByBundleName(String bundleName);
}
