/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.weixin.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;

/**
 * 发送微信信息
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年8月30日 上午10:14:33
 * @version 0.0.1
 */
public class WXSendMSG {
    
    private WXSendMSG(){
    }

    /**
     * 订单状态改变时,向用户发送订单状态信息
     * 
     * @param paraMap
     *            openId:用户openId orderNo订单编号 orderStatus订单编号 customerNickName:用户昵称 exName:物流名称 exNo:快递编号
     * @param request
     * @param response
     * @return true发送成功 false发送失败
     */
    @SuppressWarnings("deprecation")
    public static boolean sendWxMsgForChanOrderStatus(Map<?, ?> paraMap, HttpServletRequest request, HttpServletResponse response) {
        String accessToken = OwerAcessTokenUtil.getAcessToken(request, response);
        Map<String, String> resMap = new HashMap<String, String>();
        String murl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
        PostMethod postMethod = new PostMethod(murl);
        try {
            postMethod.setRequestBody("{ \"touser\":  \"" + paraMap.get(ConstantUtil.OPENID) + "\" ," + "\"url\" :\"http:\\/\\/shop.ningpai.com\\/mobile\\/getwxcode3.htm?toUrl=customer/detail-" + paraMap.get(ConstantUtil.ORDERID) + ".html\"," + "\"topcolor\" :\"#28e650\", " + "\"template_id\" :\"SsUhTsaeSNm_OaHmyfI7nTFHVI5GZN3gw3ZwIbMg1RM\",\"data\":{" + "\"first\" :{\"value\":\"尊敬的" + paraMap.get("customerNickName") + "\"}, " + "\"OrderSn\" :{\"value\":\"" + paraMap.get("orderNo") + "\",\"color\":\"#004A80\"} ," + "\"OrderStatus\" :{\"value\":\"" + paraMap.get("orderStatus") + "\"}, " + "\"remark\" :{\"value\":\"\\n如有问题请致电400-880-1024或直接在微信留言，福气商城将第一时间为您服务！\"}" + "}}");
            HttpClient mClite = new HttpClient();
            postMethod.getParams().setContentCharset(ConstantUtil.UTF);
            mClite.executeMethod(postMethod);
            resMap = WeiXinUtil.getWxSendResponse(postMethod.getResponseBodyAsString());
            if (!"ok".equals(resMap.get("errmsg"))) {
                try {
                    // 获取数据失败 throw NullPointerException ...
                    throw new NullPointerException();
                } catch (Exception e) {
                    // 获取accesstoken失败
                    OperaLogUtil.addOperaException("set error: errmsg " + resMap.get("errmsg") + "!", e, request);
                }
            } else {
                return true;
            }
        } catch (Exception e) {
            // 发送获取accesstoken请求失败
            OperaLogUtil.addOperaException("Sedding accesstoken request failed!", e, request);
        }
        return false;
    }

}
