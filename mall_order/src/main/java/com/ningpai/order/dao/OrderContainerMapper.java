/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.dao;

import com.ningpai.order.bean.OrderContainer;

import java.util.List;
import java.util.Map;

/**
 * 装箱单dao
 * 
 * @author NINGPAI-LIH
 * @since 2014年7月1日15:35:05
 * 
 */
public interface OrderContainerMapper {

    /**
     * 插入内容
     * 
     * @param container
     *            参数
     * @since 2014年7月1日16:35:30
     */
    void insertSelective(OrderContainer container);

    /**
     * 批量插入内容
     * 
     * @param list
     */
    void insertContainers(List<OrderContainer> list);

    /**
     * 查询包裹下的所有商品
     * 
     * @param relationId
     *            关系id
     * @retur 包裹下的所有商品
     */
    List<OrderContainer> queryContainerByRelationId(Long relationId);

    /**
     * 更该包裹
     * 
     * @param container
     *            参数
     */
    void updateRelation(OrderContainer container);

    /**
     * 验证包裹下是有商品
     * 
     * @param relationId
     *            包裹id
     * @return 商品或数量
     */
    Long verifyCount(Long relationId);

    /**
     * 根据包裹内商品的id查询其详细内容
     * 
     * @param containerId
     * @return
     */
    OrderContainer queryContainerByParam(Long containerId);

    /**
     * 根据包裹内id修改
     * 
     * @param container
     *            修改参数
     */
    void updateGoodsNum(OrderContainer container);

    /**
     * 根据rId 和货品Id获得包裹中商品的信息
     * 
     * @param map
     * @return OrderContainer
     */
    OrderContainer queryOrderContainerByGoodInfoId(Map<String, Object> map);
}
