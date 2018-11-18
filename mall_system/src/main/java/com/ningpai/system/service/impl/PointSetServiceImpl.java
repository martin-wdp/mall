/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.system.bean.PointSet;
import com.ningpai.system.dao.PointSetMapper;
import com.ningpai.system.service.PointSetService;

/**
 * 积分设置服务层实现类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月16日 下午2:39:31
 * @version 1.0
 */
@Service("pointSetService")
public class PointSetServiceImpl implements PointSetService {
    /** 积分设置dao */
    @Resource(name = "pointSetMapper")
    private PointSetMapper pointSetMapper;

    /**
     * 查找积分设置信息
     * 
     * @return PointSet
     */
    public PointSet findPointSet() {
        return pointSetMapper.findPointSet();
    }

    /**
     * 修改积分设置
     * 
     * @param pointSet
     * @return
     */
    public int updatePointSet(PointSet pointSet) {
        return pointSetMapper.updateByPrimaryKeySelective(pointSet);
    }
}
