/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.site.threepart.util;

import com.ningpai.util.MyLogger;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * QQ微博信息
 */
public class QQWeiBoMessage {
    public static final MyLogger LOGGER = new MyLogger(QQWeiBoMessage.class);
    /**
     * 私有构造方法
     */
    private QQWeiBoMessage(){
    }

    /**
     * 获取QQ微博信息
     * @param appid
     * @param accessToken
     * @param openid
     * @return
     * @throws IOException
     */
    public static Map<String, String> getQQWeiBoMessage(String appid, String accessToken, String openid) throws IOException {
        Map<String, String> userData = new HashMap<String, String>();
        String userInfo = "";
        String url = "https://open.t.qq.com/api/user/info?format=json&oauth_consumer_key=" + appid + "&access_token=" + accessToken + "&openid=" + openid + "&clientip=CLIENTIP&oauth_version=2.a&scope=all";
        GetMethod getMethod = new GetMethod(url);
        HttpClient client = new HttpClient();
        try {
            client.executeMethod(getMethod);
            userInfo = getMethod.getResponseBodyAsString();
            JSONObject jsonData = JSONObject.fromObject(userInfo);
            userData.put("nickname", jsonData.getString("nick").toString());
            userData.put("gender", jsonData.getString("sex").toString());
            userData.put("headimg", jsonData.getString("head").toString());
        } catch (Exception e) {
            LOGGER.error("",e);
            client=null;
        }
        return userData;
    }

}
