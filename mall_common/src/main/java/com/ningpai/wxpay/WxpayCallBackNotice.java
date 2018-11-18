/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.wxpay;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.ningpai.util.MyLogger;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.common.util.wxap.util.Sha1Util;

/**
 * 微信支付回调通知发货接口
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年9月2日 下午5:33:24
 * @version 1.0
 */
public class WxpayCallBackNotice {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(WxpayCallBackNotice.class);

    /**
     * WxpayCallBackNotice
     */
    private WxpayCallBackNotice() {
    }

    /**
     * 发货通知接口
     * 
     * @param appId
     *            appID
     * @param appKey
     *            appKey
     * @param appSecret
     *            appSecret
     * @param openId
     *            用户的openID
     * @param transId
     *            财富通交易单号码
     * @param outTradeNo
     *            商户订单号码
     * @return 通知是否成功 true:成功 false:失败
     * @throws IOException
     */
    public static boolean noticeDelvier(String appId, String appKey, String appSecret, String openId, String transId, String outTradeNo) throws IOException {
        boolean bool = true;
        // 获取时间戳
        String time = Sha1Util.getTimeStamp();
        // 签名方式
        String signMethod = "sha1";
        // 发货状态
        String deliverStatus = "1";
        // 发货标记
        String deliverMsg = "ok";
        // 获取签名
        String sign = getSign(appId, appKey, openId, transId, outTradeNo, time);
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod("https://api.weixin.qq.com/pay/delivernotify?access_token=" + getAccessToken(appId, appSecret));
        String ms = "{\"appid\": \"" + appId + "\"," + "\"openid\": \"" + openId + "\"," + "\"transid\": \"" + transId + "\"," + "\"out_trade_no\": \"" + outTradeNo + "\","
                + "\"deliver_timestamp\": \"" + time + "\"," + "\"deliver_status\": \"" + deliverStatus + "\"," + "\"deliver_msg\": \"" + deliverMsg + "\","
                + "\"sign_method\": \"" + signMethod + "\"," + "\"app_signature\": \"" + sign + "\"}";
        ((PostMethod) method).addParameter("data", ms);
        HttpMethodParams param = method.getParams();
        param.setContentCharset(ConstantUtil.UTF);
        client.executeMethod(method);
        // 打印服务器返回的状态
        InputStream stream = method.getResponseBodyAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(stream, ConstantUtil.UTF));
        StringBuilder buf = new StringBuilder();
        String line;
        while (null != (line = br.readLine())) {
            buf.append(line).append("\n");
        }
        String errcode = JSONObject.fromObject(buf.toString()).getString("errcode");
        if ("0".equals(errcode)) {
            bool = true;
        }
        br.close();
        // 释放连接
        method.releaseConnection();
        return bool;
    }

    /**
     * 生成签名
     * 
     * @param appId
     *            appId
     * @param appKey
     *            appKey
     * @param openId
     *            用户openID
     * @param transId
     *            财付通订单编号
     * @param outTradeNo
     *            商户订单编号
     * @param timestamp
     *            时间戳
     * @return 商城的签名
     */
    public static String getSign(String appId, String appKey, String openId, String transId, String outTradeNo, String timestamp) {
        String sign = "appid=" + appId + "&appkey=" + appKey + "&deliver_msg=ok" + "&deliver_status=1" + "&deliver_timestamp=" + timestamp + "&openid=" + openId + "&out_trade_no="
                + outTradeNo + "&transid=" + transId;
        return Sha1Util.getSha1(sign);
    }

    /**
     * 根据appid和appsecret查询access_token
     * 
     * @param appid
     * @param secret
     * @return 查询到的access_token
     */
    public static String getAccessToken(String appid, String secret) {
        String result = null;
        HttpClient httpClient = null;
        GetMethod getMethod = null;
        StringBuilder temp = null;
        BufferedReader buffer = null;
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
            httpClient = new HttpClient();
            getMethod = new GetMethod(url);
            httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "GBK");

            getMethod.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode == 200) {
                temp = new StringBuilder();
                InputStream in2 = getMethod.getResponseBodyAsStream();
                buffer = new BufferedReader(new InputStreamReader(in2, "GBK"));
                for (String tempstr = ""; (tempstr = buffer.readLine()) != null;) {
                    temp = temp.append(tempstr);
                }
                buffer.close();
                in2.close();
                result = temp.toString().trim();
                return JSONObject.fromObject(result).getString("access_token");
            } else {
                return "Can't get page:";
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(""+e);
            return "";
        } catch (IOException e) {
            LOGGER.error(""+e);
            return "";
        } finally {
            httpClient = null;
            getMethod = null;
            temp = null;
            buffer = null;
        }
    }

    /**
     * main
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        WxpayCallBackNotice.noticeDelvier("wx54348d130baacafd",
                "LTD5WYXxE8xbFAghepFKezy04NaqXDojDEeFFV5ZgFfhVXX8zNF98yS4kvsTGhlTx2wGRU3JJsEIdnawrAX0AG06dRQ5VaFMyfndnO6ZcremCphgFlJhKwg0dvCWru2e",
                "a5dd3cf98642ab3f3b774e4426c039ac", "o5X-Mji_SJiJMfRv6jWVh7kf5Peg", "1220343401201409023188003855", "20140902171403");
    }
}
