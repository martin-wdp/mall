/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.login.service;
import com.ningpai.third.register.bean.Sms;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录Service
 * @author NINGPAI-zhangqiang
 * @since 2014年4月15日 下午3:56:00
 * @version 0.0.1
 */
public interface LoginService {
    /**
     * 如果当前登录的会员是商家角色下面创建的员工，改员工
     * 对应的角色信息如果已经删除，就禁止该会员登录商家 结果大于1就是角色还存在可以正常登陆
     * 小于1就限制其登陆商家
     * @author zhanghl
     * @param username 用户名
     * @return
     */
    int checkThirdAuthority(String username);

    /**
     * 查询短信信息
     * @return BasicEmailServer
     */
    Sms selectSms();
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
     * 获取错误次数
     * 
     * @param request
     * @return
     */
    Long getErrCount(HttpServletRequest request);
}
