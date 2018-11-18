package com.ningpai.system.auth.alipay.util.httpclient;

import java.io.IOException;
import java.net.UnknownHostException;

import com.ningpai.common.util.tenpay.util.HttpClientUtil;
import com.ningpai.util.MyLogger;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;

/**
 * 类名：HttpProtocolHandler 功能：HttpClient方式访问 详细：获取远程HTTP数据 版本：3.2 日期：2011-03-17
 * 说明： 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public final class HttpProtocolHandler {
    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(HttpProtocolHandler.class);

    /**
     * 默认格式
     */
    private static final String DEFAULT_CHARSET = "GBK";

    /**
     * 连接超时时间，由bean factory设置，缺省为8秒钟
     */
    private static final int DEFAULTCONNECTIONTIMEOUT = 8000;

    /**
     * 回应超时时间, 由bean factory设置，缺省为30秒钟
     */
    private static final int DEFAULTSOTIMEOUT = 30000;

    /**
     * 闲置连接超时时间, 由bean factory设置，缺省为60秒钟
     */
    private static final int DEFAULTIDLECONNTIMEOUT = 60000;

    /**
     * 默认最大测试连接
     */
    private static final int DEFAULTMAXCONNPERHOST = 30;

    /**
     * 默认最大总控制
     */
    private static final int DEFAULTMAXTOTALCONN = 80;

    /**
     * 默认等待HttpConnectionManager返回连接超时（只有在达到最大连接数时起作用）：1秒
     */
    private static final long DEFAULTHTTPCONNECTIONMANAGERTIMEOUT = 3 * 1000;

    /**
     * HTTP连接管理器，该连接管理器必须是线程安全的.
     */
    private HttpConnectionManager connectionManager;

    private static HttpProtocolHandler httpProtocolHandler = new HttpProtocolHandler();

    /**
     * 私有的构造方法
     */
    private HttpProtocolHandler() {
        // 创建一个线程安全的HTTP连接池
        connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(DEFAULTMAXCONNPERHOST);
        connectionManager.getParams().setMaxTotalConnections(DEFAULTMAXTOTALCONN);

        IdleConnectionTimeoutThread ict = new IdleConnectionTimeoutThread();
        ict.addConnectionManager(connectionManager);
        ict.setConnectionTimeout(DEFAULTIDLECONNTIMEOUT);

        ict.start();
    }

    /**
     * 执行Http请求
     *
     * @param request
     * @return
     */
    public HttpResponse execute(HttpRequest request) {
        HttpClient httpclient = new HttpClient(connectionManager);

        // 设置连接超时
        int connectionTimeout = DEFAULTCONNECTIONTIMEOUT;
        if (request.getConnectionTimeout() > 0) {
            connectionTimeout = request.getConnectionTimeout();
        }
        httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);

        // 设置回应超时
        int soTimeout = DEFAULTSOTIMEOUT;
        if (request.getTimeout() > 0) {
            soTimeout = request.getTimeout();
        }
        httpclient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);

        // 设置等待ConnectionManager释放connection的时间
        httpclient.getParams().setConnectionManagerTimeout(DEFAULTHTTPCONNECTIONMANAGERTIMEOUT);

        String charset = request.getCharset();
        charset = charset == null ? DEFAULT_CHARSET : charset;
        HttpMethod method = null;

        if (request.getMethod().equals(HttpRequest.METHOD_GET)) {
            method = new GetMethod(request.getUrl());
            method.getParams().setCredentialCharset(charset);

            // parseNotifyConfig会保证使用GET方法时，request一定使用QueryString
            method.setQueryString(request.getQueryString());
        } else {
            method = new PostMethod(request.getUrl());
            ((PostMethod) method).addParameters(request.getParameters());
            method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=" + charset);

        }

        // 设置Http Header中的User-Agent属性
        method.addRequestHeader("User-Agent", "Mozilla/4.0");
        HttpResponse response = new HttpResponse();

        try {
            httpclient.executeMethod(method);
            if (request.getResultType().equals(HttpResultType.STRING)) {
                response.setStringResult(method.getResponseBodyAsString());
            } else if (request.getResultType().equals(HttpResultType.BYTES)) {
                response.setByteResult(method.getResponseBody());
            }
            response.setResponseHeaders(method.getResponseHeaders());
        } catch (UnknownHostException ex) {
            LOGGER.error("",ex);
            return null;
        } catch (IOException ex) {
            LOGGER.error("",ex);
            return null;
        } catch (Exception ex) {
            LOGGER.error("",ex);
            return null;
        } finally {
            method.releaseConnection();
        }
        return response;
    }

    /**
     * 将NameValuePairs数组转变为字符串
     *
     * @param nameValues
     * @return
     */
    protected String toString(NameValuePair[] nameValues) {
        if (nameValues == null || nameValues.length == 0) {
            return "null";
        }

        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < nameValues.length; i++) {
            NameValuePair nameValue = nameValues[i];

            if (i == 0) {
                buffer.append(nameValue.getName());
                buffer.append("=");
                buffer.append(nameValue.getValue());
            } else {
                buffer.append("&");
                buffer.append(nameValue.getName());
                buffer.append("=");
                buffer.append(nameValue.getValue());
            }
        }

        return buffer.toString();
    }

    /**
     * 工厂方法
     *
     * @return
     */
    public static HttpProtocolHandler getInstance() {
        return httpProtocolHandler;
    }
}
