/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.ningpai.common.util.ConstantUtil;

/**
 * HTTP请求工具类
 *
 * @author NINGPAI-ZHOUY
 * @version 1.0
 * @since 2013年12月23日下午2:49:32
 */
public class HttpUtil {

    private static final String USERAGENT = "User-Agent";

    private static final String DEFAULT_CHARSET = ConstantUtil.UTF;

    public static final int CONNECTIMEOUT = 5000;

    public static final int READTIMEOUT = 5000;

    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like " + "Gecko) Chrome/31.0.1650.63 Safari/537.36";

    private HttpUtil() {
    }

    /**
     * Send a post request
     *
     * @param url
     *            Url as string
     * @param body
     *            Request body as string
     * @param headers
     *            Optional map with headers
     * @return response Response as string
     * @throws IOException
     */
    public static String post(String url, String body, Map<String, String> headers) throws IOException {
        Map<String,String> headers1 = headers;
        // set content type
        if (headers1 == null) {
            headers1 = new HashMap<String, String>();
            headers.put("Content-Type", "application/x-www-form-urlencoded");
            headers.put(USERAGENT, USER_AGENT);
        }
        return fetch("POST", url, body, headers1);
    }

    /**
     * Send a post request
     *
     * @param url
     *            Url as string
     * @param body
     *            Request body as string
     * @return response Response as string
     * @throws IOException
     */
    public static String post(String url, String body) throws IOException {
        return post(url, body, null);
    }

    /**
     * Send a get request
     *
     * @param url
     * @return response
     * @throws IOException
     */
    public static String get(String url) throws IOException {
        return get(url, null);
    }

    /**
     * Send a get request
     *
     * @param url
     *            Url as string
     * @param headers
     *            Optional map with headers
     * @return response Response as string
     * @throws IOException
     */
    public static String get(String url, Map<String, String> headers) throws IOException {
        Map<String,String> headers1 = headers;
        if (headers1 == null) {
            headers1 = new HashMap<String, String>();
            headers.put(USERAGENT, USER_AGENT);
        }
        return fetch("GET", url, null, headers1);
    }

    /**
     * Post a form with parameters
     *
     * @param url
     *            Url as string
     * @param params
     *            map with parameters/values
     * @return response Response as string
     * @throws IOException
     */
    public static String postForm(String url, Map<String, String> params) throws IOException {
        return postForm(url, params, null);
    }

    /**
     * Post a form with parameters
     *
     * @param url
     *            Url as string
     * @param params
     *            Map with parameters/values
     * @param headers
     *            Optional map with headers
     * @return response Response as string
     * @throws IOException
     */
    public static String postForm(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        Map<String,String> headers1 = headers;
        if (headers1 == null) {
            headers1 = new HashMap<String, String>();
            headers.put("Content-Type", "application/x-www-form-urlencoded");
            headers.put(USERAGENT, USER_AGENT);
        }

        String body = "";
        if (params != null) {
            boolean first = true;
            for (String param : params.keySet()) {
                if (first) {
                    first = false;
                } else {
                    body += "&";
                }
                String value = params.get(param);
                body += URLEncoder.encode(param, DEFAULT_CHARSET) + "=";
                body += URLEncoder.encode(value, DEFAULT_CHARSET);
            }
        }

        return post(url, body, headers1);
    }

    /**
     * Append query parameters to given url
     *
     * @param url
     *            Url as string
     * @param params
     *            Map with query parameters
     * @return url Url with query parameters appended
     * @throws IOException
     */
    public static String appendQueryParams(String url, Map<String, String> params) throws IOException {
        String fullUrl = new String(url);

        if (params != null) {
            boolean first = fullUrl.indexOf('?') == -1;
            for (String param : params.keySet()) {
                if (first) {
                    fullUrl += '?';
                    first = false;
                } else {
                    fullUrl += '&';
                }
                String value = params.get(param);
                fullUrl += URLEncoder.encode(param, DEFAULT_CHARSET) + '=';
                fullUrl += URLEncoder.encode(value, DEFAULT_CHARSET);
            }
        }

        return fullUrl;
    }

    /**
     * Retrieve the query parameters from given url
     *
     * @param url
     *            Url containing query parameters
     * @return params Map with query parameters
     * @throws IOException
     */
    public static Map<String, String> getQueryParams(String url) throws IOException {
        Map<String, String> params = new HashMap<String, String>();

        int start = url.indexOf('?');
        while (start != -1) {
            int equals = url.indexOf('=', start);
            String param = "";
            if (equals != -1) {
                param = url.substring(start + 1, equals);
            } else {
                param = url.substring(start + 1);
            }

            String value = "";
            if (equals != -1) {
                start = url.indexOf('&', equals);
                if (start != -1) {
                    value = url.substring(equals + 1, start);
                } else {
                    value = url.substring(equals + 1);
                }
            }

            params.put(URLDecoder.decode(param, DEFAULT_CHARSET), URLDecoder.decode(value, DEFAULT_CHARSET));
        }

        return params;
    }

    /**
     * Returns the url without query parameters
     *
     * @param url
     *            Url containing query parameters
     * @return url Url without query parameters
     */
    public static String removeQueryParams(String url) {
        int q = url.indexOf('?');
        if (q != -1) {
            return url.substring(0, q);
        } else {
            return url;
        }
    }

    /**
     * Send a request
     *
     * @param method
     *            HTTP method, for example "GET" or "POST"
     * @param url
     *            Url as string
     * @param body
     *            Request body as string
     * @param headers
     *            Optional map with headers
     * @return response Response as string
     * @throws IOException
     */
    public static String fetch(String method, String url, String body, Map<String, String> headers) throws IOException {
        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setConnectTimeout(CONNECTIMEOUT);
        conn.setReadTimeout(READTIMEOUT);

        if (method != null) {
            conn.setRequestMethod(method);
        }

        if (headers != null) {
            for (String key : headers.keySet()) {
                conn.addRequestProperty(key, headers.get(key));
            }
        }

        if (body != null) {
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            os.write(body.getBytes());
            os.flush();
            os.close();
        }

        InputStream is = conn.getInputStream();
        String response = streamToString(is);
        is.close();

        if (conn.getResponseCode() == 301) {
            String location = conn.getHeaderField("Location");
            return fetch(method, location, body, headers);
        }
        return response;
    }

    /**
     * Read an input stream into a string
     *
     * @param in
     * @return string
     * @throws IOException
     */
    public static String streamToString(InputStream in) throws IOException {
        StringBuilder out = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

}
