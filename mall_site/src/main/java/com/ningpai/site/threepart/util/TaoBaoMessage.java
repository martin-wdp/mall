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
 * 淘宝信息
 * @Description 淘宝信息
 * @author Songhl
 * @since 2015年8月28日 14:06:09
 */
public final class TaoBaoMessage {
    /**
     * 私有构造方法
     */
    private TaoBaoMessage() {

    }

    /**
     * 获取淘宝信息
     * @param appid
     * @param accessToken
     * @param openid
     * @return
     * @throws IOException
     */
    public static Map<String, String> getTaoBaoMessage(String appid, String accessToken, String openid) throws IOException {
        Map<String, String> userData = new HashMap<String, String>();
        String userInfo = "";
        String url = "https://oauth.taobao.com/taobao.user.buyer.get?client_id=" + appid + "&access_token=" + accessToken + "&taobao_user_id=" + openid + "&format=json&fields=nick,sex,avatar";
        GetMethod getMethod = new GetMethod(url);
        HttpClient client = new HttpClient();
        try {
            client.executeMethod(getMethod);
            userInfo = getMethod.getResponseBodyAsString();
            JSONObject jsonData = JSONObject.fromObject(userInfo);
            userData.put("nickname", jsonData.getString("nick").toString());
            userData.put("gender", jsonData.getString("sex").toString());
            userData.put("headimg", jsonData.getString("avatar").toString());
        } finally {
            userInfo = null;
        }
        return userData;
    }

}
