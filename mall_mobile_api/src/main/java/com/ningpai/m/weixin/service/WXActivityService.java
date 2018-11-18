/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.weixin.service;

/**
 * 微信活动Service
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年9月3日 下午3:16:28
 * @version 0.0.1
 */
public interface WXActivityService {

    /**
     * 将微信用户的openid添加到群发组当中去
     * 
     * @param openId
     *            微信用户openid
     * @return 0添加失败 1添加成功
     */
    int addWxUserIdToGroup(String openId);

    /**
     * 检测Openid 是否存在
     * 
     * @param openId
     * @return false 不在 true 在
     */
    boolean checkOpenIdExist(String openId);

}
