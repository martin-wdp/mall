/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.common.util.wxap;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ningpai.common.util.wxap.util.MD5Util;
import com.ningpai.common.util.wxap.util.Sha1Util;
import com.ningpai.common.util.wxap.util.TenpayUtil;

/**
 * '微信支付服务器签名支付请求请求类
 * '============================================================================
 * 'api说明： 'init(app_id, app_secret, partner_key, app_key);
 * '初始化函数，默认给一些参数赋值，如cmdno,date等。 'setKey(key_)'设置商户密钥 'getLasterrCode(),获取最后错误号
 * 'GetToken();获取Token 'getTokenReal();Token过期后实时获取Token
 * 'createMd5Sign(signParams);生成Md5签名 'genPackage(packageParams);获取package包
 * 'createSHA1Sign(signParams);创建签名SHA1 'sendPrepay(packageParams);提交预支付
 * 'getDebugInfo(),获取debug信息
 * '============================================================================
 * '
 */
public class RequestHandler {
    /**
     * 预支付网关url地址
     */
    private String gateUrl;
    /**
     * 查询支付通知网关URL
     */
    private String notifyUrl;
    /**
     * 商户参数
     */
    private String appid;
    /**
     * appkey
     */
    private String appkey;
    /**
     * partnerkey
     */
    private String partnerkey;
    /**
     * appsecret
     */
    private String appsecret;
    /**
     * key
     */
    private String key;
    /**
     * 请求的参数
     */
    private SortedMap parameters;
    /**
     * Token
     */
    private String Token;
    /**
     * charset
     */
    private String charset;
    /**
     * debug信息
     */
    private String debugInfo;
    /**
     * last_errcode
     */
    private String last_errcode;
    /**
     * request
     */
    private HttpServletRequest request;
    /**
     * response
     */
    private HttpServletResponse response;

    /**
     * 初始构造函数。
     * 
     * @return
     */
    public RequestHandler(HttpServletRequest request, HttpServletResponse response) {
        this.last_errcode = "0";
        this.request = request;
        this.response = response;
        this.charset = "GBK";
        this.parameters = new TreeMap();
        // 验证notify支付订单网关
        notifyUrl = "https://gw.tenpay.com/gateway/simpleverifynotifyid.xml";

    }

    /**
     * 建立请求，以表单HTML形式构造（默认）
     * 
     * @param sParaTemp
     *            请求参数数组
     * @param strButtonName
     *            确认按钮显示文字
     * @return 提交表单HTML文本
     */
    public static String buildRequest(Map<String, String> sParaTemp, String strButtonName) {
        // 待请求参数数组
        List<String> keys = new ArrayList<String>(sParaTemp.keySet());

        StringBuilder sbHtml = new StringBuilder();
        sbHtml.append(" <script language='javascript'>");
        sbHtml.append("document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {");
        sbHtml.append("WeixinJSBridge.invoke('getBrandWCPayRequest',{");
        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sParaTemp.get(name);
            if (!"appkey".equals(name)) {
                sbHtml.append("'" + name + "'" + ":" + "'" + value + "'");
                if (i < (keys.size() - 1)) {
                    sbHtml.append(",");
                }
            }

        }

        sbHtml.append(" },function(res){");
        sbHtml.append("  alert(res.err_msg); if(res.err_msg == 'get_brand_wcpay_request:ok' ) {}");
        sbHtml.append("  });");
        sbHtml.append(" }, false)");
        sbHtml.append("</script>");
        // submit按钮控件请不要含有name属性

        return sbHtml.toString();
    }

    /**
     * 初始化函数。
     */
    public void init(String appId, String appSecret, String appKey, String partnerKey) {
        this.last_errcode = "0";
        this.Token = "token_";
        this.debugInfo = "";
        this.appkey = appKey;
        this.appid = appId;
        this.partnerkey = partnerKey;
        this.appsecret = appSecret;
    }

    /**
     * init
     */
    public void init() {
    }

    /**
     * 获取最后错误号
     */
    public String getLasterrCode() {
        return last_errcode;
    }

    /**
     * 获取入口地址,不包含参数值
     */
    public String getGateUrl() {
        return gateUrl;
    }

    /**
     * 获取参数值
     * 
     * @param parameter
     *            参数名称
     * @return String
     */
    public String getParameter(String parameter) {
        String s = (String) this.parameters.get(parameter);
        return (null == s) ? "" : s;
    }

    /**
     * 设置密钥
     */

    public void setKey(String key) {
        this.partnerkey = key;
    }

    /**
     * 设置微信密钥
     */
    public void setAppKey(String key) {
        this.appkey = key;
    }

    /**
     * 特殊字符处理
     */
    public String UrlEncode(String src) throws UnsupportedEncodingException {
        return URLEncoder.encode(src, this.charset).replace("+", "%20");
    }

    /**
     * 获取package的签名包
     */
    public String genPackage(SortedMap<String, String> packageParams) throws UnsupportedEncodingException {
        String sign = createSign(packageParams);

        StringBuilder sb = new StringBuilder();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            sb.append(k + "=" + UrlEncode(v) + "&");
        }

        // 去掉最后一个&
        return sb.append("sign=" + sign).toString();
    }

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     */
    public String createSign(SortedMap<String, String> packageParams) {
        StringBuilder sb = new StringBuilder();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + partnerkey);
        return MD5Util.MD5Encode(sb.toString(), this.charset).toUpperCase();
    }

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     */
    public String createSign2(SortedMap<String, String> packageParams) {
        StringBuilder sb = new StringBuilder();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + partnerkey);
        return Sha1Util.getSha1(sb.toString());
    }

    /**
     * 创建package签名
     */
    public boolean createMd5Sign(String signParams) {
        StringBuilder sb = new StringBuilder();
        Set es = this.parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (!"sign".equals(k) && null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }

        // 算出摘要
        String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
        String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();

        String tenpaySign = this.getParameter("sign").toLowerCase();

        // debug信息
        this.setDebugInfo(sb.toString() + " => sign:" + sign + " tenpaySign:" + tenpaySign);

        return tenpaySign.equals(sign);
    }

    /**
     * 输出XML
     */
    public String parseXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        Set es = this.parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"appkey".equals(k)) {

                sb.append("<" + k + ">" + getParameter(k) + "</" + k + ">\n");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 设置debug信息
     */
    protected void setDebugInfo(String debugInfo) {
        this.debugInfo = debugInfo;
    }

    /**
     * setPartnerkey
     * 
     * @param partnerkey
     */
    public void setPartnerkey(String partnerkey) {
        this.partnerkey = partnerkey;
    }

    public String getDebugInfo() {
        return debugInfo;
    }

    public String getKey() {
        return key;
    }

}
