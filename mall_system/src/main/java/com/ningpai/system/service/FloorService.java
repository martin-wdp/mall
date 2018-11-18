/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;

import java.util.List;

import com.ningpai.system.bean.Floor;

/**
 * 楼层层数服务层接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月6日 上午11:16:55
 * @version 1.0
 */
public interface FloorService {

    /**
     * 查询所有楼层信息
     * 
     * @return List
     */
    List<Floor> findAll();
}
