/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.system.service;

import com.ningpai.system.bean.ShopKuaiShang;

/**
 * 快商通实现类
 * @author jiping 
 * @since 2015年8月21日 下午7:45:54 
 * @version
 */
public interface ShopKuaiShangService {

    /**
     * 添加一条区县记录
     *            待添加的实体
     * @return 插入的行数
     */
    int insertKuaiShang(ShopKuaiShang shopKuaiShang);

    /**
     * 根据主键查询快商通信息
     *            主键ID
     * @return 查询到的实体
     */
    ShopKuaiShang selectInitone();

    /**
     * 申请快商通
     * @return
     */
    int updateKuaiShangByPrimaryKey(ShopKuaiShang shopKuaiShang);
    }
