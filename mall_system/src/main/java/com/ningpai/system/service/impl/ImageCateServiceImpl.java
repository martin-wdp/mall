/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.system.bean.ImageCate;
import com.ningpai.system.dao.ImageCateMapper;
import com.ningpai.system.service.ImageCateService;

/**
 * 图片分类服务层实现类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月17日 上午10:40:36
 * @version 1.0
 */
@Service("imageCateService")
public class ImageCateServiceImpl implements ImageCateService {
    /** spring注解 */
    @Resource(name = "imageCateMapper")
    private ImageCateMapper imageCateMapper;

    /**
     * 查询所有分类
     * 
     * @return List
     */
    public List<ImageCate> findCateAll() {
        return imageCateMapper.findImageCate();
    }

}
