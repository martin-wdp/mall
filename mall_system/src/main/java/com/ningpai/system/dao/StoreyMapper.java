/*
 * Copyright 2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.GoodsCateSystem;
import com.ningpai.system.bean.Storey;
import com.ningpai.system.util.SelectBean;

/**
 * 楼层接口表
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月4日 下午5:38:34
 * @version 1.0
 */
public interface StoreyMapper {

    /**
     * 删除楼层信息
     * 
     * @param seId
     * @return
     */
    int deleteByPrimaryKey(Long seId);

    /**
     * 添加楼层信息
     */
    int insert(Storey record);

    /**
     * 添加一条楼层信息
     * 
     * @param record
     * @return int
     */
    int insertSelective(Storey record);

    /**
     * 根据ID查询楼层信息
     * 
     * @param seId
     * @return Storey
     */
    Storey selectByPrimaryKey(Long seId);

    /**
     * 修改楼层信息
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Storey record);

    /**
     * 修改楼层信息
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Storey record);

    /**
     * 分页查询楼层信息
     * 
     * @param map
     * @return List
     */
    List<Object> findByPageBean(Map<String, Object> map);

    /**
     * 查询楼层总行数
     * 
     * @return int
     */
    int findTotalCount(SelectBean selectBean);

    /**
     * 查询所有商品父分类
     */
    List<GoodsCateSystem> findParentGoodsCate();

    /**
     * 根据楼层层数查询楼层信息
     * 
     * @param floorId
     * @return Floor
     */
    Storey findStoreyByFloorId(Long floorId);

}
