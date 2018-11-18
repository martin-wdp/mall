/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.dao;

import java.util.List;
import java.util.Map;
import com.ningpai.order.bean.BarterOrder;
/**
 * 贸易订单接口
 * */
public interface BarterOrderMapper {
    /**
     * 根据换单ID删除换单记录
     * 
     * @param barterOrderId
     *            换货ID
     * @return int
     */
    int deleteByPrimaryKey(Long barterOrderId);

    /**
     * 添加换单记录
     * 
     * @param record
     *            换货实体类
     * @return int
     */
    int insert(BarterOrder record);

    /**
     * 按字段添加换单记录
     * 
     * @param record
     *            换货实体类
     * @return int
     */
    int insertSelective(BarterOrder record);

    /**
     * 查询换单记录根据Id
     * 
     * @param barterOrderId
     *            换单ID
     * @return int
     */
    BarterOrder selectByPrimaryKey(Long barterOrderId);

    /**
     * 修改换单任意字段，根据ID
     * 
     * @param record
     *            换单实体类
     * @return int
     */
    int updateByPrimaryKeySelective(BarterOrder record);

    /**
     * 修改换单全部字段根据Id
     * 
     * @param record
     *            换单实体类
     * @return int
     */
    int updateByPrimaryKey(BarterOrder record);

    /**
     * 获取换单的总量
     * 
     * @param map
     *            查询条件
     * @return int {@link Integer}
     */
    int selectBarterGetCount(Map<String, Object> map);

    /**
     * 查取换单的分页
     * 
     * @param map
     *            查询条件
     * @return List
     */
    List<Object> selectBarterPageSize(Map<String, Object> map);

    /**
     * 批量删除
     * 
     * @param list
     *            换单ID集合
     * @return int
     */
    int batchBarterOrder(List<Long> list);

    /**
     * 查询换单详细信息
     * 
     * @param barterOrderId
     *            换单ID
     * @return int
     */
    BarterOrder selectBarterDetails(Long barterOrderId);

    /**
     * 查询换单的数量
     * 
     * @param map
     *            参数Map {@link java.util.Map}
     * @return 查询到的数量 {@link Integer}
     */
    int queryBarterOrderCount(Map<String, Object> map);

    /**
     * 查询换单的数量(已买)
     * 
     * @param map
     *            参数Map {@link java.util.Map}
     * @return 查询到的数量 {@link Integer}
     */
    int queryBarterOrderCountBuy(Map<String, Object> map);
}
