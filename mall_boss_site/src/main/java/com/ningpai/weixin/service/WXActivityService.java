/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.weixin.service;

import java.util.List;

import com.ningpai.weixin.bean.WXGroup;

/**
 * 微信活动Service
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年9月3日 下午3:16:28
 * @version 0.0.1
 */
public interface WXActivityService {

    /**
     * 查询所有群发用户
     * 
     * @return List
     */
    List<WXGroup> selectAllGroup();

}
