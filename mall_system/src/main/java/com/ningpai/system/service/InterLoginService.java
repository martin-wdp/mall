/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;

import com.ningpai.system.bean.InterLogin;
import com.ningpai.util.PageBean;

/**
 * 登录接口服务层接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月16日 上午11:41:40
 * @version
 */
public interface InterLoginService {

    /**
     * 修改登录接口信息
     * 
     * @param interLogin
     * @return int
     */
    int updateLogin(InterLogin interLogin);

    /**
     * 分页查询登录信息
     * 
     * @param pageBean
     * @return List
     */
    PageBean findByPageBean(PageBean pageBean);

    /**
     * 添加登录信息
     * 
     * @param interLogin
     * @return
     */
    int insertLogin(InterLogin interLogin);

    /**
     * 删除登录接口信息
     * 
     * @param parameterValues
     * @return int
     */
    int deleteLogin(String[] loginIds);

    /**
     * 根据ID查询登录接口信息
     * 
     * @param loginId
     * @return InterLogin
     */
    InterLogin findLoginByLoginId(Long loginId);

}
