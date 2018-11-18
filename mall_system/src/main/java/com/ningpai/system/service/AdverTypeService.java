/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;

import java.util.List;

import com.ningpai.system.bean.AdverType;

/**
 * 广告分类服务层接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月15日 上午9:53:18
 * @version 1.0
 */
public interface AdverTypeService {

    /**
     * 查询所有广告分类
     * 
     * @return List<AdverType>
     */
    List<AdverType> findAll();
}
