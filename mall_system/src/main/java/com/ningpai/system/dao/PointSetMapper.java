/*
 * Copyright 2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.system.dao;

import com.ningpai.system.bean.PointSet;

/**
 * 积分设置接口层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月16日 下午2:30:37
 * @version 1.0
 */
public interface PointSetMapper {

    /**
     * 查询积分设置信息
     * 
     * @return PointSet
     */
    PointSet findPointSet();

    /**
     * 删除积分设置信息
     * 
     * @param psetId
     * @return
     */
    int deleteByPrimaryKey(Long psetId);

    /**
     * 添加积分设置信息
     * 
     * @param record
     * @return
     */
    int insert(PointSet record);

    /**
     * 添加积分设置信息--可选字段
     * 
     * @param record
     * @return
     */
    int insertSelective(PointSet record);

    /**
     * 查询单个积分设置信息
     * 
     * @param psetId
     * @return
     */
    PointSet selectByPrimaryKey(Long psetId);

    /**
     * 修改积分设置--可选字段
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(PointSet record);

    /**
     * 修改积分设置
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(PointSet record);
}
