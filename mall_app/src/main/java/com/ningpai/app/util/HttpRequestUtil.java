package com.ningpai.app.util;

import com.ningpai.app.bean.AppServer;
import com.ningpai.app.service.AppServerService;
import com.ningpai.util.JsonUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * HttpRequestService 提供跨站访问服务
 * 
 * @date 2015-8-24 10:02:55
 * @author hehu
 */
@Service("HttpRequestService")
public class HttpRequestUtil {

    /** np_app_server的Service */
    @Resource(name = "AppServerService")
    private AppServerService appServerService;

    /** 记录日志对象 */
    private static final Logger LOGGER = Logger.getLogger(HttpRequestUtil.class);

    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @return URL所代表远程资源的响应
     */
    public String sendGet(String url) throws IOException {
        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        // 增加变量
        client.executeMethod(getMethod);
        String str = new String(getMethod.getResponseBodyAsString().getBytes("utf-8"));
        getMethod.releaseConnection();
        return str;
    }

    /**
     * 发送post请求
     * 
     * @param url
     *            请求地址
     * @param params
     *            请求参数
     * @return 返回值
     * @throws IOException
     *             抛出的异常
     */
    public Map<String, Object> sendPost(String url, Map<String, Object> params) throws IOException {
        // System.setProperty("javax.net.ssl.trustStore", "jssecacerts");
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        // 增加变量
        for (Map.Entry<String,Object> entry:params.entrySet()) {
            postMethod.addParameter(entry.getKey(), entry.getValue()==null?"":entry.getValue().toString());
        }
        // postMethod.addRequestHeader("Content-Type","text/html;charset=UTF-8");
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        client.executeMethod(postMethod);
        String str = postMethod.getResponseBodyAsString();
        Map<String, Object> resultMap = JsonUtil.getMapFromJson(str);
        resultMap.put("respCode", postMethod.getStatusCode());
        postMethod.releaseConnection();
        return resultMap;
    }

    /**
     * 检测所给连接是否可用
     * 
     * @param urlStr
     *            http连接地址
     * @return true可用，false不可用
     */
    public boolean isConnect(String urlStr) throws IOException {
        boolean isConnect = false;
        sendGet(urlStr);
        return isConnect;
    }

    /**
     * 调用指定服务器上的URI
     * 
     * @param method
     *            要调用的URI
     * @param params
     *            调用URI时传入的参数，拼接形式传入
     * @param serverType
     *            1Boss，2商家，3前台
     * @param request
     *            HttpServletRequest
     * @return 调用后返回的结果。
     */
    public String invokeMethodOnAllServers(String method, String params, String serverType, HttpServletRequest request) throws IOException {
        // 客户端Boss端网站
        List<AppServer> servers = appServerService.selectAllServersByType(serverType);
        String result = null;
        String serverRoot = "";
        // 循环调用每一台下载的方法
        for (AppServer server : servers) {
            serverRoot = server.getAppServerRoot();
            if (!serverRoot.endsWith("/")) {
                serverRoot += "/";
            }
            // 发送请求，返回结果result
            result = sendGet(serverRoot + method + ".htm?" + params + "&serverId=" + server.getAppServerId());
            LOGGER.info("服务器" + server.getAppServerRoot() + method + ".htm?" + params + "&serverId=" + server.getAppServerId() + "返回值：" + result);
        }

        return result;
    }


    /**
     * 提供主方法，测试发送GET请求和POST请求
     * 
     * @param args
     *            参数
     * @throws Exception
     *             抛出的异常
     */
    public static void main(String[] args) {
        //发送GET请求
        /*String s = HttpRequestUtil.sendGet("http://localhost:4567/host/status", null);
        System.out.println(s);*/



    }
}