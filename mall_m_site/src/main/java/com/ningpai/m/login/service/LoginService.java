/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 登录Service
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年8月19日 上午10:31:41
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

    /**
     * 向cookie中存贮是否保存用户密码的开关
     * @return
     */
    int addIsMerPwdCookie(HttpServletRequest request,String isMerPwd,HttpServletResponse response,String password,String userName);
    /**
     * 从cookie中读取是否保存用户密码的开关
     * @return
     */
    Map<String,String> getIsMerPwdCookie(HttpServletRequest request);
}
