/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.api.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ningpai.util.MyLogger;
import net.sf.json.JSONObject;

/**
 * 第三方授权Token 工具类
 * 
 * @author NINGPAI-ZHOUY
 * @since 2013年12月23日下午3:08:24
 * @version 1.0
 */
public class TokenUtil {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(TokenUtil.class);

    private TokenUtil() {
    }

    /**
     * 从第三方授权返回数据获取Access Token
     * 
     * @param string
     *            第三方授权返回数据
     * @return Access Token
     */
    public static String getAccessToken(String string) {
        String accessToken = "";
        try {
            JSONObject json = JSONObject.fromObject(string);
            if (null != json) {
                accessToken = json.getString("access_token");
            }
        } catch (Exception e) {
            LOGGER.error(""+e);
            Matcher m = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)&refresh_token=(\\w+)$").matcher(string);
            if (m.find()) {
                accessToken = m.group(1);
            } else {
                Matcher m2 = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)$").matcher(string);
                if (m2.find()) {
                    accessToken = m2.group(1);
                }
            }
        }
        return accessToken;
    }

    /**
     * 从第三方授权返回数据获取OpenID
     * 
     * @param string
     *            第三方授权返回数据
     * @return OpenID
     */
    public static String getOpenId(String string) {
        String openid = null;
        Matcher m = Pattern.compile("\"openid\"\\s*:\\s*\"(\\w+)\"").matcher(string);
        if (m.find()) {
            openid = m.group(1);
        }
        return openid;
    }

    /**
     * Sina UID和 QQ分离
     * 
     * @param string
     *            第三方授权返回数据
     * @return Sina UID
     */
    public static String getUid(String string) {
        JSONObject json = JSONObject.fromObject(string);
        return json.getString("uid");
    }

}
