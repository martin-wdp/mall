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
 * 新浪信息
 * @Description 新浪信息
 * @author Songhl
 * @since 2015年8月28日 14:08:27
 */
public final class SinaMessage {
    /**
     * 私有构造方法
     */
    private SinaMessage() {

    }

    /**
     * 获取新浪信息
     * @param accessToken
     * @param uid
     * @return
     * @throws IOException
     */
    public static Map<String, String> getSinaMessage(String accessToken, String uid) throws IOException {
        Map<String, String> userData = new HashMap<String, String>();
        String userInfo = "";
        String url = "https://api.weibo.com/2/users/show.json?access_token=" + accessToken + "&uid=" + uid;
        GetMethod getMethod = new GetMethod(url);
        HttpClient client = new HttpClient();
        try {
            client.executeMethod(getMethod);
            userInfo = getMethod.getResponseBodyAsString();
            JSONObject jsonData = JSONObject.fromObject(userInfo);
            userData.put("screen_name", jsonData.getString("screen_name").toString());
            userData.put("gender", jsonData.getString("gender").toString());
            userData.put("headimg", jsonData.getString("profile_image_url").toString());
            userData.put("appkey", uid);
        } finally {
            userInfo = null;
        }
        return userData;
    }
}
