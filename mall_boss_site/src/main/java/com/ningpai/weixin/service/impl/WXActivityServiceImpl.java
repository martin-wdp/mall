/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.weixin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.weixin.bean.WXGroup;
import com.ningpai.weixin.mapper.WXGroupMapper;
import com.ningpai.weixin.service.WXActivityService;

/**
 * @see com.ningpai.m.weixin.service.WXActivityService
 * @author NINGPAI-zhangqiang
 * @since 2014年9月3日 下午3:24:07
 * @version 0.0.1
 */
@Service("wXActivityServicec")
public class WXActivityServiceImpl implements WXActivityService {

    private WXGroupMapper wxGroupMapper;

    /*
     * 查询所有群发用户
     *
     * @see com.ningpai.weixin.service.WXActivityService#selectAllGroup()
     */
    @Override
    public List<WXGroup> selectAllGroup() {
        return wxGroupMapper.selectAllGroup();
    }

    public WXGroupMapper getWxGroupMapper() {
        return wxGroupMapper;
    }

    @Resource(name = "wXGroupMapperc")
    public void setWxGroupMapper(WXGroupMapper wxGroupMapper) {
        this.wxGroupMapper = wxGroupMapper;
    }

}
