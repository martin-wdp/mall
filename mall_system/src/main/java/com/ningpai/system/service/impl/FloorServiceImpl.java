/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.system.bean.Floor;
import com.ningpai.system.dao.FloorMapper;
import com.ningpai.system.service.FloorService;

/**
 * 楼层层数服务层实现类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月6日 上午11:18:57
 * @version 1.0
 */
@Service("floorService")
public class FloorServiceImpl implements FloorService {
    /** spring注解 */
    @Resource
    private FloorMapper floorMapper;

    /**
     * 查询所有楼层信息
     * 
     * @return List
     */
    public List<Floor> findAll() {
        // 查询所有楼层信息
        return floorMapper.findAll();
    }
}
