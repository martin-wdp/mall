package com.ningpai.common.util.alipay.util.httpClient;


import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.Header;

import com.ningpai.common.util.alipay.config.AlipayConfig;

/**
 *类名：HttpResponse
 *功能：Http返回对象的封装
 *详细：封装Http返回信息
 *版本：3.3
 *日期：2011-08-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class HttpResponse {

    /**
     * 返回中的Header信息
     */
    private Header[] responseHeaders;

    /**
     * String类型的result
     */
    private String   stringResult;

    /**
     * btye类型的result
     */
    private byte[]   byteResult;

    /**
     * 获取response头部
     * @return
     */
    public Header[] getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(Header[] responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    /**
     * 获取byte结果
     * @return
     */
    public byte[] getByteResult() {
        if (byteResult != null) {
            return byteResult;
        }
        if (stringResult != null) {
            return stringResult.getBytes();
        }
        return new byte[0];
    }

    /**
     * 设置byte结果
     * @param byteResult
     */
    public void setByteResult(byte[] byteResult) {
        this.byteResult = byteResult;
    }

    /**
     * 获取string结果
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getStringResult() throws UnsupportedEncodingException {
        if (stringResult != null) {
            return stringResult;
        }
        if (byteResult != null) {
            return new String(byteResult, AlipayConfig.input_charset);
        }
        return null;
    }

    /**
     * 设置string结果
     * @param stringResult
     */
    public void setStringResult(String stringResult) {
        this.stringResult = stringResult;
    }

}
