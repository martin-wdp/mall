/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.estore.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.system.estore.bean.EStore;
import com.ningpai.system.estore.dao.impl.EStoreMapperImpl;
import com.ningpai.system.estore.service.EStoreService;

/**
 * E店宝service实现类
 * 
 * @author jiping
 * @since 2014年12月2日 上午11:00:35
 * @version 0.0.1
 */
@Service("estoreService")
public class EStoreServiceImpl implements EStoreService {
    // spring注解
    private EStoreMapperImpl estoreMapper;

    public EStoreMapperImpl getEstoreMapper() {
        return estoreMapper;
    }

    @Resource(name = "estoreMapper")
    public void setEstoreMapper(EStoreMapperImpl estoreMapper) {
        this.estoreMapper = estoreMapper;
    }

    @Override
    public EStore findEStore() {
        return estoreMapper.findEStore();
    }

    /*
     * 
     * 
     * @see com.ningpai.system.estore.service.EStoreService#updateEStore(com.ningpai.system.estore.bean.EStore)
     */
    @Override
    public int updateEStore(EStore estore) {
        return estoreMapper.updateByPrimaryKeySelective(estore);
    }
}
