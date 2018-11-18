/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.goods.controller.GoodsAtteController;

/**
 * HttpMethodUtil
 * 
 * @author ggn
 *
 */
public class HttpMethodUtil {

    /** 记录日志对象 */
    private static final Logger LOGGER = Logger.getLogger(GoodsAtteController.class);

    /**
     * 构造
     */
    private HttpMethodUtil() {
    }

    /**
     * 
     * URL加密
     * 
     * @throws UnsupportedEncodingException
     */
    public static String encodeUri(String s) throws UnsupportedEncodingException {
        String str = "";
        str = URLEncoder.encode(s, ConstantUtil.UTF);
        return str;
    }

    /**
     * 
     * 新的md5签名，首尾放secret。
     * 
     * @param secret
     *            分配给您的APP_SECRET
     */
    public static String md5Signature(TreeMap<String, String> params, String secret) {

        String result = null;

        StringBuilder orgin = getBeforeSign(params, new StringBuilder(secret));

        if (orgin == null) {
            return result;
        }

        // orgin.append(secret);

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            result = byte2hex(md.digest(orgin.toString().getBytes(ConstantUtil.UTF)));

        } catch (Exception e) {
            LOGGER.error("",e);
            throw new java.lang.RuntimeException("sign error !");

        }

        return result;

    }

    /**
     * 
     * 二行制转字符串
     */
    private static String byte2hex(byte[] b) {

        StringBuilder hs = new StringBuilder();

        String stmp = "";

        for (int n = 0; n < b.length; n++) {

            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));

            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }

        }

        return hs.toString().toUpperCase();

    }

    /**
     * 
     * 添加参数的封装方法
     */
    private static StringBuilder getBeforeSign(TreeMap<String, String> params, StringBuilder orgin) {

        if (params == null) {
            return null;
        }

        // 添加参数
       
        Map<String, String> treeMap = new TreeMap<String, String>();

        treeMap.putAll(params);
        //迭代器
        Iterator<String> iter = treeMap.keySet().iterator();
        while (iter.hasNext()) {
            //循环
            String name = (String) iter.next();

            orgin.append(name).append(params.get(name));

        }
        return orgin;

    }

    /**
     * 连接到TOP服务器并获取数据
     * 
     */
    public static String getResult(String urlStr, String content) {

        URL url = null;

        HttpURLConnection connection = null;

        try {

            url = new URL(urlStr);

            connection = (HttpURLConnection) url.openConnection();

            // http正文内，因此需要设为true, 默认情况下是false; 
            connection.setDoOutput(true);

            //设置是否从httpUrlConnection读入，默认情况下是true;  
            connection.setDoInput(true);

            //设定请求的方法为"POST"，默认是GET   
            connection.setRequestMethod("POST");

            //Post 请求不能使用缓存   
            connection.setUseCaches(false);

            // 设定传送的内容类型是可序列化的java对象   
            // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            //连接
            connection.connect();

            DataOutputStream out = new DataOutputStream(connection.getOutputStream());

            //utf格式写入
            out.write(content.getBytes(ConstantUtil.UTF));

            //刷新
            out.flush();

            //关闭
            out.close();

            BufferedReader reader =

            new BufferedReader(new InputStreamReader(connection.getInputStream(), ConstantUtil.UTF));

            StringBuilder buffer = new StringBuilder();

            String line = "";

            ///读取单个字符。
            while ((line = reader.readLine()) != null) {
                //拼接字符
                buffer.append(line);

            }

            reader.close();

            return buffer.toString();

        } catch (IOException e) {

            LOGGER.error("连接TOP服务器并获取数据失败！", e);

        } finally {

            if (connection != null) {

                connection.disconnect();

            }

        }

        return null;

    }

}
