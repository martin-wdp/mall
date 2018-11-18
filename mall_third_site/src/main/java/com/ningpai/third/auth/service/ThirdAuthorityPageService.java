/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.auth.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.ningpai.third.auth.bean.ThirdManager;
import com.ningpai.third.auth.bean.ThirdPage;

/**
 * <p>第三方商家权限页面Service</p>
 * @author NINGPAI-zhangqiang
 * @since 2014年5月7日 下午2:44:48
 * @version 0.0.1
 */
public interface ThirdAuthorityPageService {

    /**
     * 查询用户权限页面
     * 
     * @param cid
     *            用户编号
     * @return List<ThirdPage>
     */
    List<ThirdPage> queryMenuByManager(Long cid);

    /**
     * 根据商家编号查找商家角色列表
     * 
     * @param stordId
     *            商家编号 {@link java.lang.Long}
     * @return List<ThirdManager> 商家角色 {@link com.ningpai.third.auth.bean.ThirdManager}
     */
    List<ThirdManager> queryThirdManagerByStoreId(Long stordId);

    /**
     * 查找商家权限页面列表
     * 
     * @return List<ThirdPage> 商家角色 {@link ThirdPage}
     */
    List<ThirdPage> loadAllAuthority();

    /**
     * 根据权限编号查找权限页面
     * 
     * @param authorityId
     *            权限编号 {@link java.lang.Long}
     * @return List<ThirdPage> 商家角色 {@link ThirdPage}
     */
    List<ThirdPage> queryThirdPageByAuthId(Long authorityId);

    /**
     * 跟新管理员权限
     * @param request
     * @param pagesId
     * @param authId 权限ID
     * @return
     */
    String updateAuthority(HttpServletRequest request, String[] pagesId, Long authId);

}
