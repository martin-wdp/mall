/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.temp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.temp.bean.ClassifyBarQuick;
import com.ningpai.temp.dao.ClassifyBarQuickMapper;
import com.ningpai.temp.service.ClassifyBarQuickService;

/**
 * SERVICE实现类-分类导航关联快捷分类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年7月3日上午11:51:30
 */
@Service("ClassifyBarQuickService")
public class ClassifyBarQuickServiceImpl implements ClassifyBarQuickService {

    @Resource(name = "ClassifyBarQuickMapper")
    private ClassifyBarQuickMapper barQuickMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.temp.service.ClassifyBarQuickService#selectByClassifyBarId
     * (java.lang.Long)
     */
    @Override
    public List<ClassifyBarQuick> selectByClassifyBarId(Long classifybarQuickId) {
        return barQuickMapper.selectByClassifyBarId(classifybarQuickId);
    }

}
