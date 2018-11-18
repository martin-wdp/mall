package com.ningpai.cloudshop.common;

import com.ningpai.util.MyLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 常量 Created by liangck on 15/6/24.
 */
public final class Commons {

    /**
     * 应用状态值
     */
    public static final String STATE = "1";

    /**
     * 应用唯一标识
     */
    public static String APPKEY;

    /**
     * 应用迷药
     */
    public static String APPSECRET;

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(Commons.class);

    static {
        Properties p = new Properties();
        InputStream in = Commons.class.getResourceAsStream("/prop/appinfo.properties");
        try {
            p.load(in);
            if (p.containsKey("appkey")) {
                APPKEY = p.getProperty("appkey");
            }
            if (p.containsKey("appsecret")) {
                APPSECRET = p.getProperty("appsecret");
            }

            in.close();
        } catch (IOException e) {
            LOGGER.error("",e);
        }
    }

    /**
     * 开放平台请求地址
     */
    public static final String OPEN_API_URL = "http://gw.api.qianmi.com/api";

    /**
     * 开放平台沙箱地址
     */
    public static final String OPEN_API_SANDBOX = "http://gw.api.sandbox.qianmi.com/api";

    /**
     * 开放平台授权地址
     */
    public static final String RELEASE_ENVIRONMENT = "http://oauth.qianmi.com/token";

    /**
     * 开放平台沙箱授权地址
     */
    public static final String SANDBOX_ENVORONMENT = "http://oauth.sandbox.qianmi.com/token";

    /**
     * accessToken 在session中存取的name值
     */
    public static final String ACCESSTOKEN_SESSION_NAME = "accessToken";
}
