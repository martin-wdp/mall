/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import com.ningpai.system.vo.DistrictVo;
import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.DeliveryPoint;
import com.ningpai.system.dao.DeliveryPointMapper;

/**
 * DAO实现类-自提点
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月18日上午10:44:12
 */
@Repository("DeliveryPointMapper")
public class DeliveryPointMapperImpl extends BasicSqlSupport implements
        DeliveryPointMapper {

    /*
     * 删除
     * 
     * @see
     * com.ningpai.system.dao.DeliveryPointMapper#deleteByPrimaryKey(java.lang
     * .Long)
     */
    @Override
    public int deleteByPrimaryKey(Long deliveryPointId) {

        return this
                .delete("com.ningpai.system.dao.DeliveryPointMapper.deleteByPrimaryKey",
                        deliveryPointId);
    }

    /*
     * 批量删除
     * 
     * @see
     * com.ningpai.system.dao.DeliveryPointMapper#batchDeleteByPrimaryKey(java
     * .lang.Long[])
     */
    @Override
    public int batchDeleteByPrimaryKey(Long[] deliveryPointIds) {

        return this
                .delete("com.ningpai.system.dao.DeliveryPointMapper.batchDeleteByPrimaryKey",
                        deliveryPointIds);
    }

    /*
     * 添加
     * 
     * @see
     * com.ningpai.system.dao.DeliveryPointMapper#insert(com.ningpai.system.
     * bean.DeliveryPoint)
     */
    @Override
    public int insert(DeliveryPoint record) {

        return this.insert("com.ningpai.system.dao.DeliveryPointMapper.insert",
                record);
    }

    /*
     * 添加-字段可选
     * 
     * @see
     * com.ningpai.system.dao.DeliveryPointMapper#insertSelective(com.ningpai
     * .system.bean.DeliveryPoint)
     */
    @Override
    public int insertSelective(DeliveryPoint record) {

        return this.insert(
                "com.ningpai.system.dao.DeliveryPointMapper.insertSelective",
                record);
    }

    /*
     * 根据ID查询
     * 
     * @see
     * com.ningpai.system.dao.DeliveryPointMapper#selectByPrimaryKey(java.lang
     * .Long)
     */
    @Override
    public DeliveryPoint selectByPrimaryKey(Long deliveryPointId) {

        return this
                .selectOne(
                        "com.ningpai.system.dao.DeliveryPointMapper.selectByPrimaryKey",
                        deliveryPointId);
    }

    /*
     * 修改-字段可选
     * 
     * @see
     * com.ningpai.system.dao.DeliveryPointMapper#updateByPrimaryKeySelective
     * (com.ningpai.system.bean.DeliveryPoint)
     */
    @Override
    public int updateByPrimaryKeySelective(DeliveryPoint record) {

        return this
                .update("com.ningpai.system.dao.DeliveryPointMapper.updateByPrimaryKeySelective",
                        record);
    }

    /*
     * 修改
     * 
     * @see
     * com.ningpai.system.dao.DeliveryPointMapper#updateByPrimaryKey(com.ningpai
     * .system.bean.DeliveryPoint)
     */
    @Override
    public int updateByPrimaryKey(DeliveryPoint record) {

        return this
                .update("com.ningpai.system.dao.DeliveryPointMapper.updateByPrimaryKey",
                        record);
    }

    /*
     * 根据区县ID查询所有未删除的数量-分页用
     * 
     * @see
     * com.ningpai.system.dao.DeliveryPointMapper#selectAllCount(java.util.Map)
     */
    @Override
    public int selectAllCount(Map<String, Object> map) {

        return this.selectOne(
                "com.ningpai.system.dao.DeliveryPointMapper.selectAllCount",
                map);
    }

    /*
     * 根据区县ID分页查询所有未删除的-分页
     * 
     * @see
     * com.ningpai.system.dao.DeliveryPointMapper#selectAllByPb(java.util.Map)
     */
    @Override
    public List<Object> selectAllByPb(Map<String, Object> map) {

        return this
                .selectList(
                        "com.ningpai.system.dao.DeliveryPointMapper.selectAllByPb",
                        map);
    }

    /*
     * 根据区县ID查询未删除、已启用的自提点-前台用
     * 
     * @see
     * com.ningpai.system.dao.DeliveryPointMapper#selectByDistrictIdForSite(
     * java.util.Map)
     */
    @Override
    public List<DeliveryPoint> selectByDistrictIdForSite(Map<String, Object> map) {

        return this
                .selectList(
                        "com.ningpai.system.dao.DeliveryPointMapper.selectByDistrictIdForSite",
                        map);
    }

    /**
     * 根据县id集合查询所有自提点
     */
    @Override
    public List<DeliveryPoint> selectDeliveryPointBydistrictIds(
            List<DistrictVo> disvo) {

        return this
                .selectList(
                        "com.ningpai.system.dao.DeliveryPointMapper.selectDeliveryPointBydistrictIds",
                        disvo);
    }

}
