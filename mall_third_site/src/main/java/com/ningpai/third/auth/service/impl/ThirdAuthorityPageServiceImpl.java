/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.auth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import com.ningpai.customer.bean.Customer;
import com.ningpai.third.auth.bean.ThirdManager;
import com.ningpai.third.auth.bean.ThirdPage;
import com.ningpai.third.auth.mapper.ThirdAuthorityMapper;
import com.ningpai.third.auth.mapper.ThirdAuthorityPageMapper;
import com.ningpai.third.auth.mapper.ThirdManagerAuthorityMapper;
import com.ningpai.third.auth.mapper.ThirdManagerMapper;
import com.ningpai.third.auth.service.ThirdAuthorityPageService;

/**
 * @author NINGPAI-zhangqiang
 * @version 0.0.1
 * @since 2014年5月7日 下午3:17:48
 */
@Service("thirdAuthorityPageService")
public final class ThirdAuthorityPageServiceImpl implements ThirdAuthorityPageService {
    /**
     * 第三方权限接口
     */
    private ThirdManagerMapper thirdManagerMapper;
    /**
     * 第三方管理员权限接口
     */
    private ThirdManagerAuthorityMapper managerAuthorityMapper;
    /**
     * 第三方页面权限接口
     */
    private ThirdAuthorityPageMapper authorityPageMapper;
    /**
     * 第三方权限管理接口
     */
    private ThirdAuthorityMapper authorityMapper;

    /**
     * 查询用户权限页面
     *
     * @param cid 用户编号
     * @return
     */
    public List<ThirdPage> queryMenuByManager(Long cid) {
        Customer cust = thirdManagerMapper.selectCustByCid(cid);
        return getMenuLists(cust);
    }

    /**
     * 第三方管理员权限关系
     *
     * @param cust
     * @return
     */
    public List<ThirdPage> getMenuLists(Customer cust) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 获取权限编号
        Long num = managerAuthorityMapper.selectAuthIdByCust(cust);
        paramMap.put("customerId", cust.getCustomerId());
        // 所有页面
        List<ThirdPage> menuVoList = authorityPageMapper.selectByAuthorityID(num);
        // 根据管理员ID 从管理员权限关系表中获取到权限ID 根据权限ID在权限页面表中查找到对应的页面集合 父
        List<ThirdPage> parentMenuVos = authorityPageMapper.selectAllMenuVos(num);
        // 递归权限菜单
        fillMenuList(parentMenuVos, menuVoList);
        return parentMenuVos;
    }

    /**
     * 填充权限菜单 递归
     *
     * @param parentMenuVos 父级菜单项
     * @param menuVoList    全部菜单
     */
    private void fillMenuList(List<ThirdPage> parentMenuVos, List<ThirdPage> menuVoList) {
        for (int i = 0; i < parentMenuVos.size(); i++) {
            ThirdPage menuVo = parentMenuVos.get(i);
            for (int j = 0; j < menuVoList.size(); j++) {
                ThirdPage mv = menuVoList.get(j);
                // 发现子级 添加到父级
                if ((menuVo.getId() + "").equals(mv.getParentId() + "")) {
                    if (menuVo.getMenuVos() == null) {
                        menuVo.setMenuVos(new ArrayList<ThirdPage>());
                    }
                    menuVo.getMenuVos().add(mv);
                }
            }
            // 使用已经处理过的菜单 替换之前子菜单为空菜单
            parentMenuVos.set(i, menuVo);
            if (menuVo.getMenuVos() != null) {
                fillMenuList(menuVo.getMenuVos(), menuVoList);
            }
        }
    }

    /**
     * 第三方管理员权限关系
     * @param objs
     */
    public void bb(List<ThirdPage> objs) {
        if (objs != null) {
            for (int i = 0; i < objs.size(); i++) {
                if (objs.get(i).getMenuVos() != null) {
                    bb(objs.get(i).getMenuVos());
                }
            }
        }
    }

    /**
     * 根据商家编号查找商家角色列表
     *
     * @param stordId 商家编号 {@link java.lang.Long}
     * @return
     */
    public List<ThirdManager> queryThirdManagerByStoreId(Long stordId) {
        return thirdManagerMapper.queryThirdManagerByStoreId(stordId);
    }

    /**
     * 查找商家权限页面列表
     *
     * @return
     */
    public List<ThirdPage> loadAllAuthority() {
        return thirdManagerMapper.selectAllAuthority();
    }

    /**
     * 根据权限编号查找权限页面
     *
     * @param authorityId 权限编号 {@link java.lang.Long}
     * @return
     */
    public List<ThirdPage> queryThirdPageByAuthId(Long authorityId) {
        return authorityPageMapper.selectAllPageByAuthorityID(authorityId);
    }

    /**
     * 更新管理员权限
     *
     * @param request
     * @param pagesId
     * @param authId  权限ID
     * @return
     */
    public String updateAuthority(HttpServletRequest request, String[] pagesId, Long authId) {
        // 获取当前存在的权限页面
        List<ThirdPage> pages = authorityPageMapper.selectAllPageByAuthorityID(authId);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<Long> delNums = new ArrayList<Long>();
        List<Long> addNums = new ArrayList<Long>();
        // 找出欲删除的编号
        for (int i = 0; i < pages.size(); i++) {
            //判断是否有可操作的子权限
            if (pagesId.length != 0 && pagesId.length > 0) {
                for (int j = 0; j < pagesId.length; j++) {
                    if (pages.get(i).getId().toString().equals(pagesId[j])) {
                        break;
                    }
                    //把要删除的权限ID循环装入 要删除的集合里面
                    if (!pages.get(i).getId().toString().equals(pagesId[j]) && j == pagesId.length - 1) {
                        delNums.add(pages.get(i).getId());
                    }
                }
            } else {
                //如果只剩下一个父权限ID 就直接不循环子权限数组 直接装入到要删除的集合
                delNums.add(pages.get(i).getId());
            }

        }
        if (pages.isEmpty()) {
            for (int i = 0; i < pagesId.length; i++) {
                addNums.add(Long.parseLong(pagesId[i]));
            }
        } else {
            // 找出要添加的编号
            for (int i = 0; i < pagesId.length; i++) {
                for (int j = 0; j < pages.size(); j++) {
                    if (pages.get(j).getId().toString().equals(pagesId[i])) {
                        break;
                    }
                    if (!pages.get(j).getId().toString().equals(pagesId[i]) && j == pages.size() - 1) {
                        addNums.add(Long.parseLong(pagesId[i]));
                    }
                }
            }
        }

        if (!delNums.isEmpty()) {
            paramMap.put("authorityId", authId);
            paramMap.put("preIds", delNums);
            // 删除权限
            authorityPageMapper.delPageToAuthority(paramMap);
        }
        for (int i = 0; i < addNums.size(); i++) {
            paramMap = new HashMap<String, Object>();
            paramMap.put("authorityId", authId);
            paramMap.put("pageId", addNums.get(i));
            // 添加权限
            authorityPageMapper.addPageToAuthority(paramMap);
        }
        return null;
    }

    public ThirdManagerMapper getThirdManagerMapper() {
        return thirdManagerMapper;
    }

    @Resource(name = "thirdManagerMapper")
    public void setThirdManagerMapper(ThirdManagerMapper thirdManagerMapper) {
        this.thirdManagerMapper = thirdManagerMapper;
    }

    public ThirdManagerAuthorityMapper getManagerAuthorityMapper() {
        return managerAuthorityMapper;
    }

    @Resource(name = "thirdManagerAuthorityMapper")
    public void setManagerAuthorityMapper(ThirdManagerAuthorityMapper managerAuthorityMapper) {
        this.managerAuthorityMapper = managerAuthorityMapper;
    }

    public ThirdAuthorityPageMapper getAuthorityPageMapper() {
        return authorityPageMapper;
    }

    @Resource(name = "thirdAuthorityPageMapper")
    public void setAuthorityPageMapper(ThirdAuthorityPageMapper authorityPageMapper) {
        this.authorityPageMapper = authorityPageMapper;
    }

    public ThirdAuthorityMapper getAuthorityMapper() {
        return authorityMapper;
    }

    @Resource(name = "thirdAuthorityMapper")
    public void setAuthorityMapper(ThirdAuthorityMapper authorityMapper) {
        this.authorityMapper = authorityMapper;
    }

}
