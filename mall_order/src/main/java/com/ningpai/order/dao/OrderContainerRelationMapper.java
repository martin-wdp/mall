/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.dao;

import com.ningpai.order.bean.OrderContainerRelation;

import java.util.List;

/**
 * 装箱单关系dao
 * 
 * @author NINGPAI-LIH
 * @since 2014年7月1日15:43:09
 * 
 */
public interface OrderContainerRelationMapper {

    /**
     * 根据定单插入相应的装箱单
     * 
     * @param relation
     *            实体类参数
     */
    void insertSelective(OrderContainerRelation relation);

    /**
     * 查询最后一次插入的id
     * 
     * @return
     */
    Long selectLastId();

    /**
     * 根据订单id查询这笔订单下面所有的包裹
     * 
     * @param orderId
     *            订单id
     * @return 订单下的所有包裹
     */
    List<OrderContainerRelation> selectListByOrderIds(Long orderId);

    /**
     * 删除包裹
     * 
     * @param relationId
     *            包裹id
     */
    void delRelationById(Long relationId);

    /**
     * 更新运货单
     * 
     * @param relation
     */
    void updateRelation(OrderContainerRelation relation);

    /**
     * 查询包裹
     * 
     * @param orderId
     *            订单
     * @return
     */
    List<OrderContainerRelation> getExpressNo(Long orderId);

    /**
     * 删除包裹商品
     *
     * @param cId
     *            包裹id
     */
    void delContainerByCId(Long cId);
}
