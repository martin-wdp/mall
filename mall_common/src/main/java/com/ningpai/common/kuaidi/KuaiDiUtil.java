/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.common.kuaidi;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.ningpai.util.MyLogger;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.ningpai.common.util.ConstantUtil;

/**
 * 快递
 */
public class KuaiDiUtil {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(KuaiDiUtil.class);

    /**
     * KuaiDiUtil
     */
    private KuaiDiUtil() {
    }

    /**
     * 根据快递公司编号和运单编号查询快递信息
     * 
     * @param expressType
     *            快递公司类型 {@link String}
     * @param expressNo
     *            快递单号 {@link String}
     * @return 快递100返回的json数据 {@link String}
     */
    public static String execLookKuaiDi(String expressType, String expressNo) {
        // 定义输入输出流 java调用http连接使用的GetMethod构建
        InputStream in;
        Properties p;
        String result;
        HttpClient httpClient;
        GetMethod getMethod;
        StringBuilder temp;
        BufferedReader buffer;
        try {
            in = new BufferedInputStream(new FileInputStream(new File(KuaiDiUtil.class.getResource("/").getPath() + "/com/ningpai/web/config/kuaidi100.properties")));
            p = new Properties();
            p.load(in);
            // 构建URL
            String url = "http://api.kuaidi100.com/api?id=" + p.getProperty("key") + "&com=" + expressType + "&nu=" + expressNo + "&show=" + p.getProperty("show") + "&muti="
                    + p.getProperty("muti") + "&order=" + p.getProperty("order");
            httpClient = new HttpClient();
            // HTTPGET形式请求URL
            getMethod = new GetMethod(url);
            // 获取URL返回http.protocol.content-charset的所有参数
            httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, ConstantUtil.UTF);

            getMethod.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());
            // 发送连接
            int statusCode = httpClient.executeMethod(getMethod);
            in.close();
            if (statusCode == 200) {
                temp = new StringBuilder();
                // 获取URL返回读取
                InputStream in2 = getMethod.getResponseBodyAsStream();
                buffer = new BufferedReader(new InputStreamReader(in2, ConstantUtil.UTF));
                for (String tempstr = ""; (tempstr = buffer.readLine()) != null;) {
                    temp = temp.append(tempstr);
                }
                buffer.close();
                in2.close();
                result = temp.toString().trim();
                return result;
            } else {
                return "Can't get page:";
            }

        } catch (FileNotFoundException e) {
            LOGGER.error(""+e);
            return "";
        } catch (IOException e) {
            LOGGER.error(""+e);
            return "";
        }
    }
    /**
     * 根据快递公司编号和运单编号查询快递信息
     *
     * @param expressType
     *            快递公司类型 {@link String}
     * @param expressNo
     *            快递单号 {@link String}
     * @return 快递100返回的json数据 {@link String}
     */
    public static String execLookKuaiDiNew(String expressType, String expressNo) {
        // 定义输入输出流 java调用http连接使用的GetMethod构建
        InputStream in;
        Properties p;
        String result;
        HttpClient httpClient;
        GetMethod getMethod;
        StringBuilder temp;
        BufferedReader buffer;
        try {
            in = new BufferedInputStream(new FileInputStream(new File(KuaiDiUtil.class.getResource("/").getPath() + "/com/ningpai/web/config/kuaidi100.properties")));
            p = new Properties();
            p.load(in);
            // 构建URL
            String url = "http://www.kuaidi100.com/applyurl?key=" + p.getProperty("key") + "&com=" + expressType + "&nu=" + expressNo + "&show=" + p.getProperty("show") + "&muti="
                    + p.getProperty("muti") + "&order=" + p.getProperty("order");
            httpClient = new HttpClient();
            // HTTPGET形式请求URL
            getMethod = new GetMethod(url);
            // 获取URL返回http.protocol.content-charset的所有参数
            httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, ConstantUtil.UTF);

            getMethod.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());
            // 发送连接
            int statusCode = httpClient.executeMethod(getMethod);
            in.close();
            if (statusCode == 200) {
                temp = new StringBuilder();
                // 获取URL返回读取
                InputStream in2 = getMethod.getResponseBodyAsStream();
                buffer = new BufferedReader(new InputStreamReader(in2, ConstantUtil.UTF));
                for (String tempstr = ""; (tempstr = buffer.readLine()) != null;) {
                    temp = temp.append(tempstr);
                }
                buffer.close();
                in2.close();
                result = temp.toString().trim();
                return result;
            } else {
                return "Can't get page:";
            }

        } catch (FileNotFoundException e) {
            LOGGER.error(""+e);
            return "";
        } catch (IOException e) {
            LOGGER.error(""+e);
            return "";
        }
    }
    //------------------------------------

}
