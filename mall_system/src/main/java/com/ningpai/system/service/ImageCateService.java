/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;

import java.util.List;

import com.ningpai.system.bean.ImageCate;

/**
 * 图片分类服务层接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月17日 上午10:37:52
 * @version 1.0
 */
public interface ImageCateService {

    /**
     * 查询所有分类
     * 
     * @return List
     */
    List<ImageCate> findCateAll();
}
