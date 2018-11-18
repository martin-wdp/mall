/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.estore.service;

import com.ningpai.system.estore.bean.EStore;

/**
 * E店宝servie
 * 
 * @author jiping
 * @since 2014年12月2日 上午10:59:08
 * @version 0.0.1
 */
public interface EStoreService {
    /**
     * 查询E店宝信息
     * 
     * @return
     */
    EStore findEStore();

    /**
     * 修改E店宝信息
     * 
     * @param estore
     * @return
     */
    int updateEStore(EStore estore);
}
