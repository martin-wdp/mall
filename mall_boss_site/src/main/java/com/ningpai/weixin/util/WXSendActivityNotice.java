/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.weixin.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.springframework.stereotype.Service;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.weixin.bean.WXGroup;
import com.ningpai.weixin.service.WXActivityService;

/**
 * 此工具类用于微信群发
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年8月30日 上午10:36:30
 * @version 0.0.1
 */
@Service
public class WXSendActivityNotice {

    private static final String MEDIAID = "media_id";

    // Spring注入
    private static WXActivityService wxActivityService;

    /**
     * 发送消息
     * 
     * @param request
     * @param response
     * @param path
     *            图片路径
     * @param content
     *            内容
     * @param title
     *            标题
     * @return
     * @throws FileNotFoundException
     */
    @SuppressWarnings("deprecation")
    public static int sendWXNotice(HttpServletRequest request, HttpServletResponse response, String path, String content, String title) throws FileNotFoundException {
        String token = OwerAcessTokenUtil.getAcessToken(request, response);
        int result = 1;
        if (token != null) {
            /** 上传图片 **/
            String murl = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=" + token + "&type=image";
            PostMethod postMethod = new PostMethod(murl);
            Part[] parts = { new FilePart("media", new File(path)) };
            postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
            HttpClient mClite = new HttpClient();
            postMethod.getParams().setContentCharset(ConstantUtil.UTF);
            String mres = "";
            String resup = "";
            String res = "";
            Map<String, String> mediaMap = new HashMap<String, String>();
            Map<String, String> imgMediaMap = new HashMap<String, String>();
            Map<String, String> sendMap = new HashMap<String, String>();
            List<WXGroup> wxGroup = new ArrayList<WXGroup>();
            String userIds = "";
            try {
                mClite.executeMethod(postMethod);
                mres = postMethod.getResponseBodyAsString();
                // asd
                mediaMap = WeiXinUtil.getMediaMap(mres);
                if (mediaMap.get(MEDIAID) != null) {
                    /** 上传图文 **/
                    String uploadnews = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=" + token;
                    PostMethod uploadnewsMethod = new PostMethod(uploadnews);
                    uploadnewsMethod.setRequestBody("{ \"articles\": [ { \"thumb_media_id\":\"" + mediaMap.get(MEDIAID) + "\", \"title\":\"" + title + "\", \"content\":\""
                            + content + "\"} ]  }");
                    HttpClient uploadnewsClite = new HttpClient();
                    uploadnewsMethod.getParams().setContentCharset(ConstantUtil.UTF);
                    uploadnewsClite.executeMethod(uploadnewsMethod);
                    resup = uploadnewsMethod.getResponseBodyAsString();
                    //
                    imgMediaMap = WeiXinUtil.getImgMediaMap(resup);
                    // 上传图文成功 准备发送图文信息
                    if (imgMediaMap.get(MEDIAID) != null) {
                        wxGroup = wxActivityService.selectAllGroup();
                        for (WXGroup group : wxGroup) {
                            userIds += "\"" + group.getOpenid() + "\",";
                        }
                        userIds = userIds.substring(0, userIds.length() - 1);
                        /** 图文 **/
                        String sendUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=" + token;
                        PostMethod sendPostMethod = new PostMethod(sendUrl);
                        sendPostMethod.setRequestBody("{ \"touser\": [" + userIds + "] ,\"mpnews\":{\"media_id\":\"" + imgMediaMap.get(MEDIAID) + "\"},\"msgtype\" :\"mpnews\"}");
                        HttpClient sendClient = new HttpClient();
                        sendPostMethod.getParams().setContentCharset(ConstantUtil.UTF);
                        sendClient.executeMethod(sendPostMethod);
                        res = sendPostMethod.getResponseBodyAsString();
                        sendMap = WeiXinUtil.getWxSendResponse(res);
                        result = 1;
                    }
                }
            } catch (Exception e) {
                // 获取token失败
                OperaLogUtil.addOperaException("System error!", e, request);
                result = -1;
            }
        } else {
            try {
                // 获取数据失败 throw NullPointerException ...
                throw new NullPointerException();
            } catch (Exception e) {
                // 获取token失败
                OperaLogUtil.addOperaException("Getting token failed!", e, request);
                result = -1;
            }
        }

        return result;
    }

    /**
     * get()
     * 
     * @return WXActivityService
     */
    public WXActivityService getWxActivityService() {
        return wxActivityService;
    }

    /**
     * SET方法
     * 
     * @param wxActivityService
     */
    @Resource(name = "wXActivityServicec")
    public void setWxActivityService(WXActivityService wxActivityService) {
        WXSendActivityNotice.wxActivityService = wxActivityService;
    }

}
