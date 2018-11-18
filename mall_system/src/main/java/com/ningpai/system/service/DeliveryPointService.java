/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.system.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ningpai.system.bean.DeliveryPoint;
import com.ningpai.util.PageBean;

/**
 * SERVICE-自提点
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月18日上午10:53:28
 */
public interface DeliveryPointService {
    /**
     * 删除
     * 
     * @param deliveryPointId
     * @return
     */
    int deleteDeliveryPoint(Long deliveryPointId);

    /**
     * 批量删除
     * 
     * @param deliveryPointId
     * @return
     */
    int batchDelDeliveryPoint(Long[] deliveryPointIds);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int saveDeliveryPoint(DeliveryPoint record);

    /**
     * 根据ID查询
     * 
     * @param deliveryPointId
     * @return
     */
    DeliveryPoint getDeliveryPoint(Long deliveryPointId);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateDeliveryPoint(DeliveryPoint record);

    /**
     * 根据区县ID分页查询所有未删除的
     * 
     * @param pb
     * @param districtId
     * @return
     */
    PageBean selectAllDeliveryPointByPb(PageBean pb, Long districtId);

    /**
     * 根据区县ID查询未删除、已启用的自提点-前台用
     * 
     * @return
     */
    List<DeliveryPoint> selectDeliveryPointByDistrictIdForSite(@Param(value = "districtId") Long districtId);

    /**
     * 根据当前城市id查询得到该城市下的所有自提点
     * @param cityId
     * @return
     */
    List<DeliveryPoint> selectDeliveryPointBycityId(Long cityId);

    /**
     * 修改自提点的启用状态
     * 
     * @param districtId
     * @return
     */
    int changeUserdStatus(Long districtId);
}
