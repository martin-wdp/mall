/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 授权工具类
 */
@Component("authUtil")
public final class AuthUtil {

//    @Value("${auth.appCode}")
    private String appCode = "APPCODEpaypaybillapp123";

    private AuthUtil(){}


    /**
     * 验证appcode是否合法
     * @param request
     * @return
     */
    public boolean isPassAuth(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String timestamp = request.getHeader("Timestamp");

        if (!appCode.equals(authorization)
                ||  timestamp == null
                || "".equals(timestamp)) {
            return false;
        }

        return true;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}
