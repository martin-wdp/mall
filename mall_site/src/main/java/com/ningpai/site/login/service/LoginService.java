/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.login.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录Service
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年4月15日 下午3:56:00
 * @version 0.0.1
 */
public interface LoginService {

    /**
     * 验证用户
     * 
     * @param username
     *            登录名
     * @param password
     *            密码
     * @return 0密码错误 1成功 2账号不存在
     */
    int checkCustomerExists(HttpServletRequest request, String username, String password);
}
