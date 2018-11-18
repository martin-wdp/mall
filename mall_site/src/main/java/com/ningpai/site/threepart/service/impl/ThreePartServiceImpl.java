/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.site.threepart.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.site.threepart.bean.ThreePart;
import com.ningpai.site.threepart.dao.ThreePartMapper;
import com.ningpai.site.threepart.service.ThreePartService;

/**
 * @author ggn
 * 
 */
@Service("ThreePartService")
public class ThreePartServiceImpl implements ThreePartService {

    private ThreePartMapper threePartMapper;

    @Override
    public ThreePart selectThreePart(String openid) {
        return threePartMapper.selectThreePart(openid);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.site.threepart.service.ThreePartService#insertThreePart(com
     * .ningpai.site.threepart.bean.ThreePart)
     */
    @Override
    public int insertThreePart(ThreePart tp) {
        return threePartMapper.insertThreePart(tp);
    }

    public ThreePartMapper getThreePartMapper() {
        return threePartMapper;
    }

    @Resource(name = "ThreePartMapper")
    public void setThreePartMapper(ThreePartMapper threePartMapper) {
        this.threePartMapper = threePartMapper;
    }

}
