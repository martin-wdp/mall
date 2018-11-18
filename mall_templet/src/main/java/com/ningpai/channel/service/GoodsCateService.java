/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.channel.service;

import java.util.List;

import com.ningpai.channel.bean.GoodsCate;

/**
 * 商品分类serivce接口
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月17日 下午4:52:43
 * @version 1.0
 */
public interface GoodsCateService {
    /**
     * 根据主键查询商品分类
     * 
     * @param catId
     * @return
     */
    GoodsCate selectGoosCateById(Long catId);

    /**
     * 查询所有的商品一级分类列表
     * 
     * @return 所有的商品分类列表 {@link java.util.List} {@link com.ningpai.channel.bean.GoodsCate}
     */
    List<GoodsCate> queryAllFirstGradeGoosCate();

    /**
     * 根据父ID查询商品分类
     * 
     * @return
     */
    List<GoodsCate> queryGoosCateByParentId(Long parentId);
}
