/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.system.service;

import java.util.List;

import com.ningpai.system.bean.Street;
import com.ningpai.system.util.AddressUtil;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 街道Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月13日 上午11:55:37
 * @version 1.0
 */
public interface StreetService {
    /**
     * 保存街道信息
     * 
     * @param streetName
     *            接到信息名称
     * @param districtId
     *            所属的区县ID
     * @return 插入的行数
     */
    int saveStreet(String streetName, String districtId, String streetSort);

    /**
     * 删除街道信息
     * 
     * @param streetId
     *            街道ID
     * @return 删除的行数
     */
    int delStreet(Long streetId);

    /**
     * 更新街道信息
     * 
     * @param streetId
     *            待更新的街道实体ID
     * @param streetName
     *            待更新的加到实体Name
     * @return 更新的行数
     */
    int updateStreet(String streetId, String streetName, String streetSort);

    /**
     * 根据主键查询
     * 
     * @param streetId
     *            街道的主键ID
     * @return 查询到的实体
     */
    Street findStreetByPrimaryKey(Long streetId);

    /**
     * 根据分页参数以及查询参数查询列表
     * 
     * @param pb
     *            分页参数
     * @param selectBean
     *            查询参数
     * @return 将查询到的列表封装进辅助Bean的实体
     */
    PageBean findListByPageBean(PageBean pb, SelectBean selectBean);

    /**
     * 批量删除记录
     * 
     * @param streetIds
     *            待删除的街道Id的数组
     * @return 删除的行数
     */
    int batchDelStreet(String[] streetIds);

    /**
     * 根据区县ID查询街道信息
     * 
     * @param districtId
     *            区县ID
     * @return 查询到的街道的集合
     */
    List<Street> queryStreetByDistrictId(Long districtId);

    /**
     * 验证街道名称是否可用
     * 
     * @param streetName
     *            待验证的街道名称
     * @return 验证的结果 可用返回true 否则返回false
     */
    boolean checkStreetName(String streetName);

    /**
     * 根据街道ID查询所属的区县，城市和省份ID
     * 
     * @param streetId
     *            街道ID
     * @return 查询到的地址帮助Bean
     */
    AddressUtil queryAddressNameByStreetId(Long streetId);

    /**
     * 根据县区id和街道名称查询所有街道
     * @param districtId 县区id
     * @param streetName 街道名称
     * @return
     */
    List<Object> queryStreetByDistrictIdAndStreetName(Long districtId, String streetName);
}
