/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;

import java.util.List;

import com.ningpai.system.bean.Auth;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 第三方登录服务层接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月25日 下午2:29:35
 * @version 1.0
 */
public interface AuthService {

    /**
     * 第三方登录分页查询
     * 
     * @param pageBean
     * @return PageBean
     */
    PageBean findByPageBean(PageBean pageBean, SelectBean selectBean);

    /**
     * 添加第三方登录
     * 
     * @param auth
     * @return int
     */
    int insertAuth(Auth auth);

    /**
     * 删除第三方登录
     * 
     * @param authIds
     *            删除人
     * @return int
     */
    int deleteAuth(String[] authIds);

    /**
     * 根据id查询信息
     * 
     * @param authId
     * @return Auth
     */
    Auth findAuthByAuthId(Long authId);

    /**
     * 修改第三方登录信息
     * 
     * @param auth
     * @return int
     */
    int updateAuth(Auth auth);

    /**
     * 根据类型查询
     */
    Auth findAuthByAuthType(String authType);

    /**
     * 根据第三方登录接口ID修改启用状态
     * 
     * @param authId
     * @return
     */
    boolean updateUserdStatus(Long authId);

    /**
     * 查找已启用的第三方登录接口
     * 
     * @return
     */
    List<Auth> findByShow();

    /**
     * 根据主键删除记录
     * @param authId 主键id
     * @return 执行结果
     */
    int deleteAuthById(Long authId);
    
    /**
     * 查询微信登录接口（移动版登录接口）
     * @param pageBean
     * @param selectBean
     * @return
     */
    public PageBean findBywxPageBean(PageBean pageBean, SelectBean selectBean);
}
