/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.system.bean.AdverType;
import com.ningpai.system.dao.AdverTypeMapper;
import com.ningpai.system.service.AdverTypeService;

/**
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月15日 上午9:55:27
 * @version 1.0
 */
@Service("adverTypeService")
public class AdverTypeServiceImpl implements AdverTypeService {
    /** spring注解 */
    @Resource(name = "adverTypeMapper")
    private AdverTypeMapper adverTypeMapper;

    /**
     * 查询所有广告分类
     * 
     * @return List<AdverType>
     */
    public List<AdverType> findAll() {

        return adverTypeMapper.selectAll();
    }

}
