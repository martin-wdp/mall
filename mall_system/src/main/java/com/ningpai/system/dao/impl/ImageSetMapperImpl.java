/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.ImageSet;
import com.ningpai.system.dao.ImageSetMapper;
import com.ningpai.system.util.SelectBean;

/**
 * 图片设置数据层实现类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月17日 上午9:55:06
 * @version 1.0
 */
@Repository("imageSetMapper")
public class ImageSetMapperImpl extends BasicSqlSupport implements
        ImageSetMapper {
    /**
     * 删除图片设置
     * 
     * @param ruleId
     * @return
     */
    public int deleteByPrimaryKey(Long ruleId) {
        return this.update(
                "com.ningpai.system.dao.ImageSetMapper.deleteByPrimaryKey",
                ruleId);
    }

    /**
     * 添加图片设置
     * 
     * @param record
     * @return
     */
    public int insert(ImageSet record) {
        return 0;
    }

    /**
     * 添加图片设置--可选设置
     * 
     * @param record
     * @return
     */
    public int insertSelective(ImageSet record) {
        return this
                .update("com.ningpai.system.dao.ImageSetMapper.insertSelective",
                        record);
    }

    /**
     * 查询单个图片设置
     * 
     * @param ruleId
     * @return
     */
    public ImageSet selectByPrimaryKey(Long ruleId) {
        return this.selectOne(
                "com.ningpai.system.dao.ImageSetMapper.selectByPrimaryKey",
                ruleId);
    }

    /**
     * 修改图片设置--可选字段
     * 
     * @param record
     * @return int
     */
    public int updateByPrimaryKeySelective(ImageSet record) {
        return this
                .update("com.ningpai.system.dao.ImageSetMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 修改图片设置
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKey(ImageSet record) {
        return 0;
    }

    /**
     * 分页显示图片设置
     * 
     * @return List
     */
    public List<Object> findByPageBean(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.system.dao.ImageSetMapper.findByPageBean", map);
    }

    /**
     * 查询总行数
     * 
     * @return
     */
    public int findTotalCount(SelectBean selectBean) {
        return this.selectOne(
                "com.ningpai.system.dao.ImageSetMapper.findTotalCount",
                selectBean);
    }

}
