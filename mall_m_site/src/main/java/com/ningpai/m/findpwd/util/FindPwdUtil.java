/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.findpwd.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 找回密码辅助类
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年8月22日 上午10:31:20
 * @version 0.0.1
 */
public final class FindPwdUtil {

    private static final String CHECKCODE = "checkCode";

    private FindPwdUtil() {
    }

    /**
     * 验证身份
     * 
     * @param request
     * @param code
     *            短信验证码
     */
    public static boolean checkCode(HttpServletRequest request, String code) {
        if (code == null || (code != null && code.trim().length() == 0)) {
            throw new IllegalArgumentException();
        }
        if (code.equals("" + (int) request.getSession().getAttribute("mcCode"))) {
            request.getSession().setAttribute(CHECKCODE, code);
            return true;
        }
        return false;
    }

    /**
     * 验证用户上下步骤合法性
     * 
     * @param request
     * @return true 逻辑顺序正确 不是非法来源 false 非法来源,需要注意!
     */
    public static boolean checkSessionCode(HttpServletRequest request) {
        if (request.getSession().getAttribute(CHECKCODE) != null) {
            return true;
        }
        return false;
    }

    /**
     * 验证密码是否已经验证成功
     * 
     * @param request
     * @return true 逻辑顺序正确 不是非法来源 false 非法来源,需要注意!
     */
    public static boolean checkSuccessCode(HttpServletRequest request) {
        if (request.getSession().getAttribute("muFlag") != null) {
            request.getSession().removeAttribute(CHECKCODE);
            request.getSession().removeAttribute("mcCode");
            request.getSession().removeAttribute("muFlag");
            return true;
        }
        return false;
    }
}
