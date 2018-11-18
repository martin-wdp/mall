/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.m.weixin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.m.weixin.bean.ThreePart;
import com.ningpai.m.weixin.dao.ThreePartMapper;
import com.ningpai.m.weixin.service.ThreePartService;

/**
 * @see com.ningpai.m.weixin.service.ThreePartService
 * @author NINGPAI-zhangqiang
 * @since 2014年8月26日 下午6:05:44
 * @version 0.0.1
 */
@Service("threePartServiceM")
public class ThreePartServiceImpl implements ThreePartService {

    //第三方接口
    private ThreePartMapper threePartMapper;

    /**
     * 查询第三方信息
     * @param openid
     * @return ThreePart
     */
    @Override
    public ThreePart selectThreePart(String openid) {
        return threePartMapper.selectThreePart(openid);
    }

    /*
     * 插入第三方
     * @see
     * com.ningpai.site.threepart.service.ThreePartService#insertThreePart(com
     * .ningpai.site.threepart.bean.ThreePart)
     */
    @Override
    public int insertThreePart(ThreePart tp) {
        return threePartMapper.insertThreePart(tp);
    }

    /**
     * GET方法
     * @return
     */
    public ThreePartMapper getThreePartMapper() {
        return threePartMapper;
    }

    /**
     * Spring注入
     * @param threePartMapper
     */
    @Resource(name = "threePartMapperM")
    public void setThreePartMapper(ThreePartMapper threePartMapper) {
        this.threePartMapper = threePartMapper;
    }

}
