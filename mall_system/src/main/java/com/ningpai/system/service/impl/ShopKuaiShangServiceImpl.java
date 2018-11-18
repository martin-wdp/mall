/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.system.service.impl;

import com.ningpai.system.bean.ShopKuaiShang;
import com.ningpai.system.dao.ShopKuaiShangMapper;
import com.ningpai.system.service.ShopKuaiShangService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 快商通实现类
 */
@Service("ShopKuaiShangService")
public class ShopKuaiShangServiceImpl implements ShopKuaiShangService {
    @Resource(name = "ShopKuaiShangMapper")
    private ShopKuaiShangMapper shopKuaiShangMapper;

    @Override
    public int insertKuaiShang(ShopKuaiShang shopKuaiShang) {
        return shopKuaiShangMapper.insertKuaiShang(shopKuaiShang);
    }

    @Override
    public ShopKuaiShang selectInitone() {
        return shopKuaiShangMapper.selectInitone();
    }

    @Override
    public int updateKuaiShangByPrimaryKey(ShopKuaiShang shopKuaiShang) {
        return shopKuaiShangMapper.updateKuaiShangByPrimaryKey(shopKuaiShang);
    }
}
