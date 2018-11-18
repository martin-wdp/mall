/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao;

import com.ningpai.system.bean.BasicSet;

/**
 * 站点基本信息接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月16日 下午4:53:10
 * @version 1.0
 */
public interface BasicSetMapper {
    /**
     * 删除站点基本信息
     * 
     * @param bsetId
     * @return
     */
    int deleteByPrimaryKey(Long bsetId);

    /**
     * 添加站点基本信息
     * 
     * @param record
     * @return
     */
    int insert(BasicSet record);

    /**
     * 添加站点基本信息
     * 
     * @param record
     * @return
     */
    int insertSelective(BasicSet record);

    /**
     * 查询单个站点基本信息
     * 
     * @param record
     * @return
     */
    BasicSet selectByPrimaryKey(Long bsetId);

    /**
     * 修改站点信息
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(BasicSet record);

    /**
     * 修改站点信息
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(BasicSet record);

    /**
     * 查询站点信息
     * 
     * @return BasicSet
     */
    BasicSet findBasicSet();

    /**
     * 查询店铺是否开启
     * @return 开启状态
     */
    String getStoreStatus();

}
