/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.manager.service;

import javax.servlet.http.HttpServletRequest;

import com.ningpai.manager.bean.Manager;

/**
 * 手机短信验证码接口
 * */
public interface ManagerSmsService {

    /**
     * 发送手机验证码
     * 
     * @param request
     * @param mobile
     *            目标手机
     * @return 1:成功 <br/>
     *         0:网络连接超时 <br/>
     *         -1:没过60秒
     */
    int sendPost(HttpServletRequest request, String mobile);

    /**
     * 验证手机验证码
     * 
     * @param request
     * @return 0失败 1成功 -1失效
     */
    int getMCode(HttpServletRequest request, String code);

    /**
     * 查询管理员手机
     * 
     * @param managerId
     *            管理员编号
     * @return 管理员信息 {@link Manager}
     */
    Manager queryManagerById(Long managerId);
}
