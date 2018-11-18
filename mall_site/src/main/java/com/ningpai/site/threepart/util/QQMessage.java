/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.site.threepart.util;

import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * QQ信息
 * @Description QQ信息
 * @author Songhl
 * @since 2015年8月28日 14:10:08
 */
public final class QQMessage {
    /**
     * 私有构造方法
     */
    private QQMessage() {

    }

    /**
     * 获取QQ信息
     * @param appid
     * @param accessToken
     * @param openid
     * @return
     * @throws IOException
     */
    public static Map<String, String> getQQMessage(String appid, String accessToken, String openid) throws IOException {
        Map<String, String> userData = new HashMap<String, String>();
        String userInfo = "";
        String url = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=" + appid + "&access_token=" + accessToken + "&openid=" + openid + "&format=json";
        GetMethod getMethod = new GetMethod(url);
        HttpClient client = new HttpClient();
        try {
            client.executeMethod(getMethod);
            userInfo = getMethod.getResponseBodyAsString();
            JSONObject jsonData = JSONObject.fromObject(userInfo);
            userData.put("nickname", jsonData.getString("nickname").toString());
            userData.put("gender", jsonData.getString("gender").toString());
            userData.put("headimg", jsonData.getString("figureurl_2").toString());
        } finally {
            userInfo = null;
        }
        return userData;
    }

}
