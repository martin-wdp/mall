/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.weixin.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.customer.bean.Customer;
import com.ningpai.logger.util.OperaLogUtil;

/**
 * 发送微信信息
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年8月30日 上午10:14:33
 * @version 0.0.1
 */
public class WXSendMSG {

    public static final String ERRMSG = "errmsg";

    public static final String OK = "ok";

    private static final String URL_TOKEN = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
    private static final String Body_INFO1 = "{ \"touser\":  \"";
    private static final String Body_INFO2 = "\"url\" :\"http:\\/\\/shop.ningpai.com\\/mobile\\/getwxcode3.htm?toUrl=customer/detail-";
    private static final String Body_INFO3 = ".html\",";
    private static final String Body_INFO4 = "\"topcolor\" :\"#28e650\", ";
    private static final String Body_INFO5 = "\"orderMoneySum\" :{\"value\":\"";
    private static final String Body_INFO6 = "orderNo";
    private static final String Body_INFO7 = "orderPrice";
    private static final String Body_INFO8 = "goodsName";
    private static final String Body_INFO9 = "元\"}, ";
    private static final String Body_INFO10 = "\"}, ";
    private static final String EX_INFO1 = "Sedding accesstoken request failed!";

    private WXSendMSG() {
    }

    /**
     * 当微信订单生成的时候调用此接口,向用户发送订单生成信息
     * 
     * @param paraMap
     *            openId:用户openId orderNo订单编号 orderPrice:订单价格 goodsName:商品名称
     * @param request
     * @param response
     * @return true发送成功 false发送失败
     */
    @SuppressWarnings("deprecation")
    public static boolean sendWxMsgForOrderCreate(Map<?, ?> paraMap, HttpServletRequest request, HttpServletResponse response) {
        String accessToken = OwerAcessTokenUtil.getAcessToken(request, response);
        Map<String, String> resMap = new HashMap<String, String>();
        String murl = URL_TOKEN + accessToken;
        PostMethod postMethod = new PostMethod(murl);

        try {
            postMethod.setRequestBody(Body_INFO1 + paraMap.get(ConstantUtil.OPENID) + "\" ," + Body_INFO2 + paraMap.get(ConstantUtil.ORDERID) + Body_INFO3 + Body_INFO4
                    + "\"template_id\" :\"A6xE6PJ4B60PY25lYfLqrDJClmeP8oBMSqwjQ0yQO_w\",\"data\":{" + "\"first\" :{\"value\":\"您的订单已提交成功,以下是订单信息:\"}, "
                    + "\"orderID\" :{\"value\":\"" + paraMap.get(Body_INFO6) + "\"} ," + Body_INFO5 + paraMap.get(Body_INFO7) + Body_INFO9
                    + "\"backupFieldName\" :{\"value\":\"商品名称\"}, " + "\"backupFieldData\" :{\"value\":\"" + paraMap.get(Body_INFO8) + Body_INFO10
                    + "\"remark\" :{\"value\":\"如有问题请致电400-880-1024或直接在微信留言，福气商城将第一时间为您服务！\"}" + "}}");
            HttpClient mClite = new HttpClient();
            postMethod.getParams().setContentCharset(ConstantUtil.UTF);
            mClite.executeMethod(postMethod);
            resMap = WeiXinUtil.getWxSendResponse(postMethod.getResponseBodyAsString());
            if (!OK.equals(resMap.get(ERRMSG))) {
                throwNullPoniter(request, resMap);
            } else {
                return true;
            }
        } catch (Exception e) {
            // 发送获取accesstoken请求失败
            OperaLogUtil.addOperaException(EX_INFO1, e, request);
        }
        return false;
    }

    private static void throwNullPoniter(HttpServletRequest request, Map<String, String> resMap) {
        try {
            // 获取数据失败 throw NullPointerException ...
            throw new NullPointerException();
        } catch (Exception e) {
            // 获取accesstoken失败
            OperaLogUtil.addOperaException("set error: errmsg " + resMap.get(ERRMSG) + "!", e, request);
        }
    }

    /**
     * 当微信订单支付成功的时候调用此接口,向用户发送订单支付成功信息
     * 
     * @param paraMap
     *            openId:用户openId orderNo订单编号 orderPrice:订单价格 goodsName:商品名称
     * @param request
     * @param response
     * @return true发送成功 false发送失败
     */
    @SuppressWarnings("deprecation")
    public static boolean sendWxMsgForOrderPaySucc(Map<?, ?> paraMap, HttpServletRequest request, HttpServletResponse response) {
        String accessToken = OwerAcessTokenUtil.getAcessToken(request, response);
        Map<String, String> resMap = new HashMap<String, String>();
        String murl = URL_TOKEN + accessToken;
        PostMethod postMethod = new PostMethod(murl);

        try {
            postMethod.setRequestBody(Body_INFO1 + paraMap.get(ConstantUtil.OPENID) + "\" ," + Body_INFO2 + paraMap.get(ConstantUtil.ORDERID) + Body_INFO3 + Body_INFO4
                    + "\"template_id\" :\"dB37CTNFdyXwZTz0mspVvWxLzY4agsBSW3Grujkn5wg\",\"data\":{" + "\"first\" :{\"value\":\"我们已收到您订单编号为" + paraMap.get(Body_INFO6)
                    + "的货款，开始为您打包商品，请耐心等待: )\"}, " + Body_INFO5 + paraMap.get(Body_INFO7) + Body_INFO9 + "\"orderProductName\" :{\"value\":\"" + paraMap.get(Body_INFO8)
                    + Body_INFO10 + "\"Remark\" :{\"value\":\"如有问题请致电400-880-1024或直接在微信留言，福气商城将第一时间为您服务！\"}" + "}}");
            HttpClient mClite = new HttpClient();
            postMethod.getParams().setContentCharset(ConstantUtil.UTF);
            mClite.executeMethod(postMethod);
            resMap = WeiXinUtil.getWxSendResponse(postMethod.getResponseBodyAsString());
            if (!OK.equals(resMap.get(ERRMSG))) {
                throwNullPoniter(request, resMap);
            } else {
                return true;
            }
        } catch (Exception e) {
            // 发送获取accesstoken请求失败
            OperaLogUtil.addOperaException(EX_INFO1, e, request);
        }
        return false;
    }

    /**
     * 当微信订单代付成功的时候调用此接口,向用户发送订单支付成功信息
     * 
     * @param paraMap
     *            openId:用户openId orderNo订单编号 orderPrice:订单价格 goodsName:商品名称
     * @param request
     * @param response
     * @return true发送成功 false发送失败
     */
    @SuppressWarnings("deprecation")
    public static boolean sendWxMsgForOtherPaySucc(Map<?, ?> paraMap, HttpServletRequest request, HttpServletResponse response) {
        String accessToken = OwerAcessTokenUtil.getAcessToken(request, response);
        Map<String, String> resMap = new HashMap<String, String>();
        String murl = URL_TOKEN + accessToken;
        PostMethod postMethod = new PostMethod(murl);

        try {
            postMethod.setRequestBody(Body_INFO1 + paraMap.get(ConstantUtil.OPENID) + "\" ," + Body_INFO2 + paraMap.get(ConstantUtil.ORDERID) + Body_INFO3 + Body_INFO4
                    + "\"template_id\" :\"dB37CTNFdyXwZTz0mspVvWxLzY4agsBSW3Grujkn5wg\",\"data\":{" + "\"first\" :{\"value\":\"我们已收到您订单编号为" + paraMap.get(Body_INFO6)
                    + "的货款，开始为您打包商品，请耐心等待: )\"}, " + Body_INFO5 + paraMap.get(Body_INFO7) + Body_INFO9 + "\"orderProductName\" :{\"value\":\"" + paraMap.get(Body_INFO8)
                    + Body_INFO10 + "\"Remark\" :{\"value\":\"\\n代付人:" + paraMap.get("otherPayName") + "\\n附言:" + paraMap.get("ps")
                    + "\\n如有问题请致电400-880-1024或直接在微信留言，福气商城将第一时间为您服务！\"}" + "}}");
            HttpClient mClite = new HttpClient();
            postMethod.getParams().setContentCharset(ConstantUtil.UTF);
            mClite.executeMethod(postMethod);
            resMap = WeiXinUtil.getWxSendResponse(postMethod.getResponseBodyAsString());
            if (!OK.equals(resMap.get(ERRMSG))) {
                throwNullPoniter(request, resMap);
            } else {
                return true;
            }
        } catch (Exception e) {
            // 发送获取accesstoken请求失败
            OperaLogUtil.addOperaException(EX_INFO1, e, request);
        }
        return false;
    }

    /**
     * 订单状态改变时,向用户发送订单状态信息
     * 
     * @param paraMap
     *            openId:用户openId orderNo订单编号 orderStatus订单编号
     *            customerNickName:用户昵称 exName:物流名称 exNo:快递编号
     * @param request
     * @param response
     * @return true发送成功 false发送失败
     */
    @SuppressWarnings("deprecation")
    public static boolean sendWxMsgForChanOrderStatus(Map<?, ?> paraMap, HttpServletRequest request, HttpServletResponse response) {
        String accessToken = OwerAcessTokenUtil.getAcessToken(request, response);
        Map<String, String> resMap = new HashMap<String, String>();
        String murl = URL_TOKEN + accessToken;
        PostMethod postMethod = new PostMethod(murl);
        try {
            postMethod.setRequestBody(Body_INFO1 + paraMap.get(ConstantUtil.OPENID) + "\" ," + Body_INFO2 + paraMap.get(ConstantUtil.ORDERID) + Body_INFO3 + Body_INFO4
                    + "\"template_id\" :\"SsUhTsaeSNm_OaHmyfI7nTFHVI5GZN3gw3ZwIbMg1RM\",\"data\":{" + "\"first\" :{\"value\":\"尊敬的" + paraMap.get("customerNickName") + Body_INFO10
                    + "\"OrderSn\" :{\"value\":\"" + paraMap.get(Body_INFO6) + "\",\"color\":\"#004A80\"} ," + "\"OrderStatus\" :{\"value\":\"" + paraMap.get("orderStatus")
                    + Body_INFO10 + "\"remark\" :{\"value\":\"其他订单信息\\n物流信息: " + paraMap.get("exName") + " \\n快递单号: " + paraMap.get("exNo")
                    + " \\n如有问题请致电400-880-1024或直接在微信留言，福气商城将第一时间为您服务！\"}" + "}}");
            HttpClient mClite = new HttpClient();
            postMethod.getParams().setContentCharset(ConstantUtil.UTF);
            mClite.executeMethod(postMethod);
            resMap = WeiXinUtil.getWxSendResponse(postMethod.getResponseBodyAsString());
            if (!OK.equals(resMap.get(ERRMSG))) {
                throwNullPoniter(request, resMap);
            } else {
                return true;
            }
        } catch (Exception e) {
            // 发送获取accesstoken请求失败
            OperaLogUtil.addOperaException(EX_INFO1, e, request);
        }
        return false;
    }

    /**
     * 当微信订单生成的时候调用此接口,向用户发送订单生成信息
     * 
     * @param orderNo
     *            订单编号
     * @return 0发送失败 1发送成功
     */
    public static int sendWxMsgForOrderCreate(HttpServletRequest request, HttpServletResponse response, String orderNo) {
        String accessToken = OwerAcessTokenUtil.getAcessToken(request, response);
        String openId = "";
        Map<String, String> resMap = new HashMap<String, String>();
        if (request.getSession().getAttribute("cust") != null) {
            openId = ((Customer) request.getSession().getAttribute("cust")).getCustomerNickname();
        } else {
            try {
                // 获取微信登录数据失败 throw NullPointerException ...
                throw new NullPointerException();
            } catch (Exception e) {
                // 获取accesstoken失败
                OperaLogUtil.addOperaException("Getting wxuserinfo failed!", e, request);
            }
        }
        if (accessToken != null && orderNo != null) {
            String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken + "&touser=" + openId + "&msgtype=text&content=您好,您的订单已经成功创建,订单编号:"
                    + orderNo;

            PostMethod postMethod = new PostMethod(url);
            HttpClient clientToken = new HttpClient();
            postMethod.getParams().setContentCharset(ConstantUtil.UTF);
            String res = "";
            try {
                // 获取token
                clientToken.executeMethod(postMethod);
                res = postMethod.getResponseBodyAsString();
                resMap = WeiXinUtil.getWxSendResponse(res);
                if (!OK.equals(resMap.get(ERRMSG))) {
                    throwNullPoniter(request, resMap);
                } else {
                    return 1;
                }
            } catch (Exception e) {
                // 发送获取accesstoken请求失败
                OperaLogUtil.addOperaException(EX_INFO1, e, request);
            }
        }
        return 0;
    }

    /**
     * 当微信订单支付成功的时候调用此接口,向用户发送订单支付成功信息
     * 
     * @param request
     * @param response
     * @param orderNo
     *            订单编号
     * @param orderPrice
     *            订单金额
     * @return 0 发送失败 1发送成功
     */
    public static int sendWxMsgForOrderPaySucc(HttpServletRequest request, HttpServletResponse response, String orderNo, Double orderPrice) {
        String accessToken = OwerAcessTokenUtil.getAcessToken(request, response);
        String openId = "";
        Map<String, String> resMap = new HashMap<String, String>();
        if (request.getSession().getAttribute("cust") != null) {
            openId = ((Customer) request.getSession().getAttribute("cust")).getCustomerNickname();
        } else {
            try {
                // 获取微信登录数据失败 throw NullPointerException ...
                throw new NullPointerException();
            } catch (Exception e) {
                // 获取accesstoken失败
                OperaLogUtil.addOperaException("Getting wxuserinfo failed!", e, request);
            }
        }
        if (accessToken != null && orderNo != null) {
            String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken + "&touser=" + openId + "&msgtype=text&content=您好,您的订单" + orderNo
                    + "支付成功,订单金额:" + orderPrice;
            PostMethod postMethod = new PostMethod(url);
            HttpClient clientToken = new HttpClient();
            postMethod.getParams().setContentCharset(ConstantUtil.UTF);
            String res = "";
            try {
                // 获取token
                clientToken.executeMethod(postMethod);
                res = postMethod.getResponseBodyAsString();
                resMap = WeiXinUtil.getWxSendResponse(res);
                if (!OK.equals(resMap.get(ERRMSG))) {
                    throwNullPoniter(request, resMap);
                } else {
                    return 1;
                }
            } catch (Exception e) {
                // 发送获取accesstoken请求失败
                OperaLogUtil.addOperaException(EX_INFO1, e, request);
            }
        }
        return 0;
    }

}
