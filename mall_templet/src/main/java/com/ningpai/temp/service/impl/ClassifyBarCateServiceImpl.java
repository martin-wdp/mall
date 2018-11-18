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

import com.ningpai.temp.bean.ClassifyBarCate;
import com.ningpai.temp.dao.ClassifyBarCateMapper;
import com.ningpai.temp.service.ClassifyBarCateService;

/**
 * SERVICE实现类-分类导航关联商品分类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年7月3日上午11:48:04
 */
@Service("ClassifyBarCateService")
public class ClassifyBarCateServiceImpl implements ClassifyBarCateService {

    @Resource(name = "ClassifyBarCateMapper")
    private ClassifyBarCateMapper barCateMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.temp.service.ClassifyBarCateService#selectByClassifyBarId
     * (java.lang.Long)
     */
    @Override
    public List<ClassifyBarCate> selectByClassifyBarId(Long classifybarId) {
        return barCateMapper.selectByClassifyBarId(classifybarId);
    }

}
