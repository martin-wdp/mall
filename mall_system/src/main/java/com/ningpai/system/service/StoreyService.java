/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;

import java.util.List;

import com.ningpai.system.bean.GoodsCateSystem;
import com.ningpai.system.bean.Storey;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 楼层服务层接口
 * @author NINGPAI-LiHaoZe 
 * @since 2014年1月4日 下午6:35:02 
 * @version 1.0
 */
public interface StoreyService {
    
    /**
     * 分页查询楼层信息
     * @param pageBean
     * @return PageBean
     */
    PageBean findByPageBean(PageBean pageBean, SelectBean selectBean);
    
    /**
     * 查询所有商品父分类
     * @return List
     */
    List<GoodsCateSystem> findParentGoodsCate();

    /**
     * 添加一条楼层信息
     * @param storey
     * @return int
     */
    int insertStorey(Storey storey);

    /**
     * 根据楼层层数查询楼层信息
     * @param floorId
     * @return
     */
    Storey findStoreyByFloorId(Long floorId);

    /**
     * 删除楼层信息
     * @param parameterValues
     * @return
     */
    int deleteStorey(String[] seIds);

    
    /**
     * 根据ID查询楼层信息
     * @param seId
     * @return Storey
     */
    Storey findStoreyById(Long seId);

    /**
     * 修改楼层信息
     * @param storey
     * @return int
     */
    int updateStorey(Storey storey);
}
