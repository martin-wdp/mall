/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.other.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证登录
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年7月1日 上午10:05:12
 * @version 0.0.1
 */
public final class LoginUtil {

    private LoginUtil() {

    }

    /**
     * 验证登录
     * 
     * @param request
     * @return
     */
    public static boolean checkLoginStatus(HttpServletRequest request) {
        if (request.getSession().getAttribute("cust") == null) {
            return false;
        }
        return true;
    }

}
