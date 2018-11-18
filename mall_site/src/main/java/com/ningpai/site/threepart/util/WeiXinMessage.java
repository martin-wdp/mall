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
 * 微信信息
 * @Description 微信信息
 * @author Songhl
 * @since 2015年8月28日 14:05:10
 */
public final class WeiXinMessage {
    /**
     * 私有构造函数
     */
    private WeiXinMessage() {

    }

    /**
     * 获取微信信息
     * @param accessToken
     * @param openid
     * @return
     * @throws IOException
     */
    public static Map<String, String> getWeiXinMessage(String accessToken, String openid) throws IOException {
        Map<String, String> userData = new HashMap<String, String>();
        String userInfo = "";
        String url="https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openid;
        GetMethod getMethod = new GetMethod(url);
        HttpClient client = new HttpClient();
        try {
            client.executeMethod(getMethod);
            userInfo = getMethod.getResponseBodyAsString();
            JSONObject jsonData = JSONObject.fromObject(userInfo);
            userData.put("nickname", jsonData.getString("nickname").toString());
            userData.put("sex", jsonData.getString("sex").toString());
            userData.put("headimgurl", jsonData.getString("headimgurl").toString());
        } finally {
            userInfo = null;
        }
        return userData;
    }

}
