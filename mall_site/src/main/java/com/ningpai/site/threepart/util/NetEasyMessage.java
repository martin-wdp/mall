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
 * 163网易信息
 * @Description 163网易信息
 * @author Songhl
 * @since 2015年8月28日 14:12:15
 */
public final class NetEasyMessage {
    /**
     * 私有构造方法
     */
    private NetEasyMessage() {

    }

    /**
     * 获取网易信息
     * @param accessToken
     * @param screeName
     * @return
     * @throws IOException
     */
    public static Map<String, String> getNetEasyMessage(String accessToken, String screeName) throws IOException {
        Map<String, String> userData = new HashMap<String, String>();
        String userInfo = "";
        String url = "https://api.t.163.com/users/show.format?access_token=" + accessToken + "&scree_name=";
        GetMethod getMethod = new GetMethod(url);
        HttpClient client = new HttpClient();
        try {
            client.executeMethod(getMethod);
            userInfo = getMethod.getResponseBodyAsString();
            JSONObject jsonData = JSONObject.fromObject(userInfo);
            userData.put("nickname", jsonData.getString("screen_name").toString());
            userData.put("gender", jsonData.getString("gender").toString());
            userData.put("headimg", jsonData.getString("profile_image_url").toString());
        } finally {
            userInfo = null;
        }
        return userData;
    }

}
