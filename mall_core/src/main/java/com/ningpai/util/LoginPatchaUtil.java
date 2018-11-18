/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.util;

/**
 * 后台登录验证码显示开关工具类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年7月17日上午10:19:04
 */
public class LoginPatchaUtil {

    /**
     * 是否显示验证码<br/>
     * 0:不显示 1:显示
     */
    private static int SHOWPATCHA = 1;

    private LoginPatchaUtil() {
    }

    public static int getSHOWPATCHA() {
        return SHOWPATCHA;
    }

    public static void setSHOWPATCHA(int sHOWPATCHA) {
        SHOWPATCHA = sHOWPATCHA;
    }

}
