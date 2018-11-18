package com.ningpai.system.auth.alipay.util.httpclient;

import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.Header;

import com.ningpai.system.auth.alipay.config.AlipayConfig;

/**
 * 类名：HttpResponse 功能：Http返回对象的封装 详细：封装Http返回信息 版本：3.2 日期：2011-03-17 说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class HttpResponse {

    /**
     * 返回中的Header信息
     */
    private Header[] responseHeaders;

    /**
     * String类型的result
     */
    private String stringResult;

    /**
     * btye类型的result
     */
    private byte[] byteResult;

    /**
     * 得到返回中的Header信息
     * 
     * @return
     */
    public Header[] getResponseHeaders() {
        if (this.responseHeaders != null) {
            return responseHeaders.clone();
        } else {
            return new Header[0];
        }
    }

    /**
     * 设置返回中的Header信息
     * 
     * @param responseHeaders
     */
    public void setResponseHeaders(Header[] responseHeaders) {
        Header[] headerTemp = responseHeaders.clone();
        if (null != headerTemp && headerTemp.length > 0) {
            this.responseHeaders = headerTemp;
        }
    }

    /**
     * btye类型的result
     * 
     * @return
     */
    public byte[] getByteResult() {
        if (byteResult != null) {
            return byteResult.clone();
        }
        if (stringResult != null) {
            return stringResult.getBytes();
        }
        return new byte[0];
    }

    /**
     * btye类型的result
     * 
     * @param byteResult
     */
    public void setByteResult(byte[] byteResult) {
        byte[] byteTemp = byteResult.clone();
        if (null != byteTemp && byteTemp.length > 0) {
            this.byteResult = byteTemp;
        }
    }

    /**
     * String类型的result
     * 
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getStringResult() throws UnsupportedEncodingException {
        if (stringResult != null) {
            return stringResult;
        }
        if (byteResult != null) {
            return new String(byteResult, AlipayConfig.INPUT_CHARSET);
        }
        return null;
    }

    /**
     * String类型的result
     * 
     * @param stringResult
     */
    public void setStringResult(String stringResult) {
        this.stringResult = stringResult;
    }

}
