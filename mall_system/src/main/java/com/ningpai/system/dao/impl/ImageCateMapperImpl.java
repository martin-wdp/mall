/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.ImageCate;
import com.ningpai.system.dao.ImageCateMapper;

/**
 * 图片分类实现类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月17日 上午10:33:29
 * @version 1.0
 */
@Repository("imageCateMapper")
public class ImageCateMapperImpl extends BasicSqlSupport implements
        ImageCateMapper {
    /**
     * 删除图片分类
     * 
     * @param imgcateId
     * @return
     */
    public int deleteByPrimaryKey(Long imgcateId) {
        return 0;
    }

    /**
     * 添加图片分类
     * 
     * @param record
     * @return
     */
    public int insert(ImageCate record) {
        return 0;
    }

    /**
     * 添加图片分类--可选字段
     * 
     * @param record
     * @return
     */
    public int insertSelective(ImageCate record) {
        return 0;
    }

    /**
     * 查询单个图片分类
     * 
     * @param imgcateId
     * @return
     */
    public ImageCate selectByPrimaryKey(Long imgcateId) {
        return null;
    }

    /**
     * 修改图片分类--可选字段
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(ImageCate record) {
        return 0;
    }

    /**
     * 修改图片分类
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKey(ImageCate record) {
        return 0;
    }

    /**
     * 查询所有分类
     * 
     * @return List
     */
    public List<ImageCate> findImageCate() {

        return this
                .selectList("com.ningpai.system.dao.ImageCateMapper.findCateAll");
    }

}
