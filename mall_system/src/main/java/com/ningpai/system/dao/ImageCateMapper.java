/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao;

import java.util.List;

import com.ningpai.system.bean.ImageCate;

/**
 * 图片分类接口层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月17日 上午10:30:13
 * @version 1.0
 */
public interface ImageCateMapper {
    /**
     * 删除图片分类
     * 
     * @param imgcateId
     * @return
     */
    int deleteByPrimaryKey(Long imgcateId);

    /**
     * 添加图片分类
     * 
     * @param record
     * @return
     */
    int insert(ImageCate record);

    /**
     * 添加图片分类--可选字段
     * 
     * @param record
     * @return
     */
    int insertSelective(ImageCate record);

    /**
     * 查询单个图片分类
     * 
     * @param imgcateId
     * @return
     */
    ImageCate selectByPrimaryKey(Long imgcateId);

    /**
     * 修改图片分类--可选字段
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ImageCate record);

    /**
     * 修改图片分类
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(ImageCate record);

    /**
     * 查询所有分类
     * 
     * @return List
     */
    List<ImageCate> findImageCate();
}
