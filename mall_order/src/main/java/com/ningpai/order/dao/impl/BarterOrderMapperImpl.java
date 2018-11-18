/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.BarterOrder;
import com.ningpai.order.dao.BarterOrderMapper;
/**
 * 贸易订单接口实现类
 * */
@Repository("barterOrderMapper")
public class BarterOrderMapperImpl extends BasicSqlSupport implements
        BarterOrderMapper {
    /**
     * 根据换单ID删除换单记录
     * @param barterOrderId
     *            换货ID
     * @return
     */
    public int deleteByPrimaryKey(Long barterOrderId) {
        return this.delete(
                "com.ningpai.order.dao.BarterOrderMapper.deleteByPrimaryKey",
                barterOrderId);
    }

    /**
     * 添加换单记录
     * @param record
     *            换货实体类
     * @return
     */
    public int insert(BarterOrder record) {
        return this.insert("com.ningpai.order.dao.BarterOrderMapper.insert",
                record);
    }

    /**
     * 获取换单的总量
     * @param map
     *            查询条件
     * @return
     */
    public int selectBarterGetCount(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.order.dao.BarterOrderMapper.selectBarterGetCount",
                map);
    }

    /**
     * 查取换单的分页
     * @param map
     *            查询条件
     * @return
     */
    public List<Object> selectBarterPageSize(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.order.dao.BarterOrderMapper.selectBarterPageSize",
                map);
    }

    /**
     * 按字段添加换单记录
     * @param record
     *            换货实体类
     * @return
     */
    public int insertSelective(BarterOrder record) {
        return this.insert(
                "com.ningpai.order.dao.BarterOrderMapper.insertSelective",
                record);
    }

    /**
     * 查询换单记录根据Id
     * @param barterOrderId
     *            换单ID
     * @return
     */
    public BarterOrder selectByPrimaryKey(Long barterOrderId) {
        return this.selectOne(
                "com.ningpai.order.dao.BarterOrderMapper.selectByPrimaryKey",
                barterOrderId);
    }

    /**
     * 修改换单任意字段，根据ID
     * @param record
     *            换单实体类
     * @return
     */
    public int updateByPrimaryKeySelective(BarterOrder record) {
        return this
                .update("com.ningpai.order.dao.BarterOrderMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 修改换单全部字段根据Id
     * @param record
     *            换单实体类
     * @return
     */
    public int updateByPrimaryKey(BarterOrder record) {
        return this.update(
                "com.ningpai.order.dao.BarterOrderMapper.updateByPrimaryKey",
                record);
    }

    /**
     * 批量删除
     * @param list
     *            换单ID集合
     * @return
     */
    public int batchBarterOrder(List<Long> list) {
        return this.delete(
                "com.ningpai.order.dao.BarterOrderMapper.batchBarterOrder",
                list);
    }

    /**
     * 查询换单详细信息
     * @param barterOrderId
     *            换单ID
     * @return
     */
    public BarterOrder selectBarterDetails(Long barterOrderId) {
        return this.selectOne(
                "com.ningpai.order.dao.BarterOrderMapper.selectBarterDetails",
                barterOrderId);
    }

    /**
     * 查询换单的数量
     * @param map
     *            参数Map {@link java.util.Map}
     * @return
     */
    public int queryBarterOrderCount(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.order.dao.BarterOrderMapper.queryBarterOrderCount",
                        map);
    }

    /**
     * 查询换单的数量(已买)
     * @param map
     *            参数Map {@link java.util.Map}
     * @return
     */
    @Override
    public int queryBarterOrderCountBuy(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.order.dao.BarterOrderMapper.queryBarterOrderCountBuy",
                        map);
    }

}
