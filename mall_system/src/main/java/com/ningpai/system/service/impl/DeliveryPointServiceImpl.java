/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ningpai.system.service.DistrictService;
import com.ningpai.system.vo.DistrictVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ningpai.system.bean.DeliveryPoint;
import com.ningpai.system.dao.DeliveryPointMapper;
import com.ningpai.system.service.DeliveryPointService;
import com.ningpai.util.PageBean;

/**
 * SERVICE实现类-自提点
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月18日上午11:10:51
 */
@Service("DeliveryPointService")
public class DeliveryPointServiceImpl implements DeliveryPointService {

    /** spring注解 */
    @Resource(name = "DeliveryPointMapper")
    private DeliveryPointMapper deliveryPointMapper;

    /** spring注解 */
    @Resource(name = "DistrictService")
    private DistrictService districtService;

    /**
     * logger日志
     */
    private static final Logger LOGGER = Logger.getLogger(DeliveryPointServiceImpl.class);

    /*
     * 删除
     * 
     * @see
     * com.ningpai.system.service.DeliveryPointService#deleteDeliveryPoint(java
     * .lang.Long)
     */
    @Override
    public int deleteDeliveryPoint(Long deliveryPointId) {
        int n = -1;
        try {
            n = this.deliveryPointMapper.deleteByPrimaryKey(deliveryPointId);
        } catch (Exception e) {
            LOGGER.error("删除自提点异常：=>", e);
        }
        return n;
    }

    /*
     * 批量删除
     * 
     * @see
     * com.ningpai.system.service.DeliveryPointService#batchDelDeliveryPoint
     * (java.lang.Long[])
     */
    @Override
    public int batchDelDeliveryPoint(Long[] deliveryPointIds) {
        int n = -1;
        try {
            n = this.deliveryPointMapper.batchDeleteByPrimaryKey(deliveryPointIds);
        } catch (Exception e) {
            LOGGER.error("批量删除自提点异常：=>", e);
        }
        return n;
    }

    /*
     * 添加
     * 
     * @see
     * com.ningpai.system.service.DeliveryPointService#saveDeliveryPoint(com
     * .ningpai.system.bean.DeliveryPoint)
     */
    @Override
    public int saveDeliveryPoint(DeliveryPoint record) {
        int n = -1;
        try {
            Date date = new Date();
            record.setCreateDate(date);
            record.setUpdateDate(date);
            n = this.deliveryPointMapper.insertSelective(record);
        } catch (Exception e) {
            LOGGER.error("添加自提点异常：=>", e);
        }
        return n;
    }

    /*
     * 根据ID查询
     * 
     * @see
     * com.ningpai.system.service.DeliveryPointService#getDeliveryPoint(java
     * .lang.Long)
     */
    @Override
    public DeliveryPoint getDeliveryPoint(Long deliveryPointId) {
        return this.deliveryPointMapper.selectByPrimaryKey(deliveryPointId);
    }

    /*
     * 修改
     * 
     * @see
     * com.ningpai.system.service.DeliveryPointService#updateDeliveryPoint(com
     * .ningpai.system.bean.DeliveryPoint)
     */
    @Override
    public int updateDeliveryPoint(DeliveryPoint record) {
        int n = -1;
        try {
            record.setUpdateDate(new Date());
            n = this.deliveryPointMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            LOGGER.error("修改自提点异常：=>", e);
        }
        return n;
    }

    /*
     * 根据区县ID分页查询所有未删除的
     * 
     * @see
     * com.ningpai.system.service.DeliveryPointService#selectAllDeliveryPointByPb
     * (com.ningpai.util.PageBean, java.lang.Long)
     */
    @Override
    public PageBean selectAllDeliveryPointByPb(PageBean pb, Long districtId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("districtId", districtId);
            pb.setRows(this.deliveryPointMapper.selectAllCount(map));
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(this.deliveryPointMapper.selectAllByPb(map));
        } catch (Exception e) {
            LOGGER.error("分页查询自提点异常：=>", e);
        } finally {
            map = null;
        }
        return pb;
    }

    /*
     * 根据区县ID查询未删除、已启用的自提点-前台用
     * 
     * @see com.ningpai.system.service.DeliveryPointService#
     * selectDeliveryPointByDistrictIdForSite(java.lang.Long)
     */
    @Override
    public List<DeliveryPoint> selectDeliveryPointByDistrictIdForSite(Long districtId) {
        List<DeliveryPoint> list = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("districtId", districtId);
        try {
            list = this.deliveryPointMapper.selectByDistrictIdForSite(map);
        } catch (Exception e) {
            LOGGER.error("前台查询自提点异常：=>", e);
        }
        return list;
    }

    /**
     * 根据当前城市id查询得到该城市下的所有自提点
     */
    @Override
    public List<DeliveryPoint> selectDeliveryPointBycityId(Long cityId) {
        List<DeliveryPoint> list = null;
        List<DistrictVo> disvo = districtService.queryDistrictByCityId(cityId);
        if (CollectionUtils.isNotEmpty(disvo)) {
            list = this.deliveryPointMapper.selectDeliveryPointBydistrictIds(disvo);
        }
        return list;
    }

    /*
     * 修改自提点的启用状态
     * 
     * @see
     * com.ningpai.system.service.DeliveryPointService#changeUserdStatus(java
     * .lang.Long)
     */
    @Override
    public int changeUserdStatus(Long districtId) {
        int n = -1;
        try {
            DeliveryPoint dp = this.deliveryPointMapper.selectByPrimaryKey(districtId);
            if ("0".equals(dp.getIsUserd())) {
                dp.setIsUserd("1");
            } else {
                dp.setIsUserd("0");
            }
            dp.setUpdateDate(new Date());
            n = this.deliveryPointMapper.updateByPrimaryKeySelective(dp);
        } catch (Exception e) {
            LOGGER.error("修改自提点的启用状态", e);
        }
        return n;
    }
}
