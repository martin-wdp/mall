/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.site.threepart.util;

import com.ningpai.util.UtilDate;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 阿里巴巴信息
 * @Description 阿里巴巴信息
 * @author Songhl
 * @since 2015年8月28日 14:13:46
 */
public final class AlipayMessage {
    /**
     * 私有构造方法
     */
    private AlipayMessage() {

    }

    /**
     * 获取阿里巴巴信息
     * @param method
     * @param accessToken
     * @param appid
     * @param sign
     * @param openid
     * @return
     * @throws IOException
     */
    public static Map<String, String> getAlipayMessage(String method, String accessToken, String appid, String sign, String openid) throws IOException {
        Map<String, String> userData = new HashMap<String, String>();
        String userInfo = "";
        String url = "?method=" + method + "&auth_token=" + accessToken + "&timestamp=" + UtilDate.dataFormat(new Date()) + "&format=json&platform=top&app_id=" + appid + "&version=1.0&sign=" + sign + "&sign_type=RSA&terminal_info=web&return_url=";
        GetMethod getMethod = new GetMethod(url);
        HttpClient client = new HttpClient();
        try {
            client.executeMethod(getMethod);
            userInfo = getMethod.getResponseBodyAsString();
            JSONObject jsonData = JSONObject.fromObject(userInfo);
            userData.put("nickname", jsonData.getString("real_name").toString());
            userData.put("gender", jsonData.getString("sex").toString());
            userData.put("email", jsonData.getString("logon_id").toString());
        } finally {
            userInfo = null;
        }
        return userData;
    }
}
