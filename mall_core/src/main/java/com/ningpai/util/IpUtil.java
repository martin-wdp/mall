package com.ningpai.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

/**
 * @Description: ip工具类
 * @author qiyuanyuan
 */
public class IpUtil {

    private static final Logger LOGGER = Logger.getLogger(IpUtil.class);

    private static final String UNKNOWN = "unknown";

    private IpUtil() {
    }

    /**
     * 获取客户端ip
     * 
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        // 如果是多级代理，那么取第一个ip为客户ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }
        return ip;
    }

    /**
     * 
     根据获取地区信息
     * 
     * @param ip
     *            客户端ip
     * @return String[] [0]国家 [1]省 [2]市 [3]县 [4]运营商
     */
    public static String[] getClientArea(HttpServletRequest request) {
        String ip = getIpAddr(request);
        ip = "117.88.148.251";
        request.getSession().setAttribute("ipAddress", ip);
        String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=" + (ip == null ? "" : ip);
        String returnString = "";
        String[] areas = { "", "", "", "", "" };
        GetMethod postMethod = new GetMethod(url);
        HttpClient client = new HttpClient();
        // 设置编码字符
        client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        try {
            client.executeMethod(postMethod);
            returnString = StringUtil.fromUnicode(postMethod.getResponseBodyAsString());
            Map<String, Object> result = JsonUtil.getMapFromJson(returnString);
            if ("1".toString().equals(result.get("ret"))) {
                // 国家
                areas[0] = result.get("country").toString();
                // 省
                areas[1] = result.get("province").toString();
                // 市
                areas[2] = result.get("city").toString();
                // 县
                areas[3] = result.get("district").toString();
                // 运营商
                areas[4] = result.get("isp").toString();
            }
        } catch (Exception e) {
            LOGGER.error("根据ip为：" + ip + "获取地区信息失败！", e);
        }
        return areas;
    }
}
