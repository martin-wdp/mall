/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.manager.mapper;

import java.util.List;

import com.ningpai.manager.bean.ImageSet;
import com.ningpai.manager.bean.UpyunConf;

/**
 * SysHelperMapper
 * 
 * @author ggn
 * 
 */
public interface SysHelperMapper {

    /**
     * 查询配置
     * 
     * @return
     */
    UpyunConf selectUpyunConf();

    /**
     * 查询商品图片列表
     * 
     * @return List
     */
    List<ImageSet> selectImageSet();
}
