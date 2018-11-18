/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.manager.service;

import java.util.List;

import com.ningpai.manager.bean.Authority;
import com.ningpai.manager.bean.AuthorityPage;
import com.ningpai.manager.bean.ManagerAuthority;
import com.ningpai.manager.bean.valuebean.MenuVo;
import com.ningpai.util.PageBean;

/**
 * 权限服务处理接口
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年1月9日 上午1:15:25
 * @version 0.0.1
 */
public interface AuthorityServiceInterface {
    /**
     * 查询权限列表
     * 
     * @return List <code>Authority</code> {@link }
     */
    List<Authority> queryAuthoerityList(Long createId);

    /**
     * 根据管理员编号查找权限
     * 
     * @param mid
     *            管理员编号
     * @return
     */
    ManagerAuthority queryAuthorByManagerId(Long mid);

    /**
     * 查询权限列表
     * 
     * @param authority
     * @param pageBean
     * @return
     */
    PageBean queryAuthoerityList(Authority authority, PageBean pageBean,
            Long createId);

    /**
     * 删除权限
     * 
     * @param parameterValues
     * @return 0失败 1成功
     */
    int deleteAuthority(String[] parameterValues);

    /**
     * 编辑角色
     * 
     * @param split
     * @param authority
     * @return
     */
    int updateAuthority(String split, Authority authority);

    /**
     * 添加权限
     * 
     * @param split
     * @param authority
     * @return
     */
    int addAuthority(String split, Authority authority, Long createId);

    /**
     * 根据权限编号查找权限
     * 
     * @param id
     *            权限编号
     * @return 权限
     */
    Authority queryAuthorId(Long id);

    /**
     * 根据权限编号查找权限页面关系信息ID
     * 
     * @param id
     *            权限编号
     * @return 权限页面关系ID
     */
    List<AuthorityPage> queryAuthorityByAId(Long id);

    /**
     * 删除权限页面
     * 
     * @param id
     */
    int deleteAuthorityPage(Long id);

    /**
     * 查询超级管理员
     * 
     * @return 权限
     */
    Authority querySupperAuthor();

    /**
     * 根据权限名称检查权限是否存在
     * 
     * @param authName
     *            权限名称
     * @return Long 0 不存在 1存在
     */
    Long checkAuthExist(String authName);

    /**
     * 根据管理员名称检查管理员是否存在
     *
     * @param username
     *            管理员名称
     * @return Long 0 不存在 1存在
     */
    Authority checkManagerExist(String username);

    /**
     * 批量赋予某个角色权限
     * 
     * @param authorityId
     *            角色id
     * @param menuVos
     *            菜单列表
     */
    void addAuthorityPageBatch(Long authorityId, List<MenuVo> menuVos);

    /**
     * 删除模块中所有的权限
     *
     * @param bundleName 模块的唯一标示
     */
    void deleteAuthByBundleName(String bundleName);

    /**
     * 添加权限
     *
     * @param split
     * @param authority
     * @return
     */
    int insertAuthorities(String split, Authority authority, Long createId);

    /**
     * 编辑角色
     * 
     * @param split
     * @param authority
     * @return
     */
    int mergeAuthority(String split, Authority authority);
}
