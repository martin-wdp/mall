/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.weixin.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.m.weixin.bean.WXGroup;
import com.ningpai.m.weixin.dao.WXGroupMapper;
import com.ningpai.m.weixin.service.WXActivityService;

/**
 * @see com.ningpai.m.weixin.service.WXActivityService
 * @author NINGPAI-zhangqiang
 * @since 2014年9月3日 下午3:24:07
 * @version 0.0.1
 */
@Service("wXActivityService")
public class WXActivityServiceImpl implements WXActivityService {

    private WXGroupMapper wxGroupMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.m.weixin.service.WXActivityService#addWxUserIdToGroup(java
     * .lang.String)
     */
    @Override
    public int addWxUserIdToGroup(String openId) {
        WXGroup group = new WXGroup();
        group.setOpenid(openId);
        group.setGetTime(new Date());
        return wxGroupMapper.insertSelective(group);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.m.weixin.service.WXActivityService#checkOpenIdExist(java.
     * lang.String)
     */
    @Override
    public boolean checkOpenIdExist(String openId) {
        if (wxGroupMapper.checkOpenIdExist(openId) > 0) {
            return true;
        }
        return false;
    }

    public WXGroupMapper getWxGroupMapper() {
        return wxGroupMapper;
    }

    @Resource(name = "wXGroupMapper")
    public void setWxGroupMapper(WXGroupMapper wxGroupMapper) {
        this.wxGroupMapper = wxGroupMapper;
    }

}
