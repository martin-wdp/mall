/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.system.service;

import java.util.List;

import com.ningpai.system.bean.City;
import com.ningpai.system.util.AddressUtil;
import com.ningpai.system.util.SelectBean;
import com.ningpai.system.vo.CityVo;
import com.ningpai.util.PageBean;

/**
 * 城市信息Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月13日 上午9:44:16
 * @version 1.0
 */
public interface CityService {
    /**
     * 保存城市信息
     * 
     * @param city
     *            需要保存的城市实体
     * @return 返回插入的行数
     */
    int saveCity(String cityName, String provinceId, String citySort);

    /**
     * 删除城市信息
     * 
     * @param cityId
     *            待删除的城市的主键ID
     * @return 删除的行数
     */
    int delPCity(Long cityId);

    /**
     * 更新城市信息
     * 
     * @param cityId
     *            待更新的城市信息的实体ID
     * @param cityName
     *            待更新的城市信息的实体名称
     * @return 更新的行数
     */
    int updateCity(String cityId, String cityName, String citySort);

    /**
     * 根据主键ID查询城市信息
     * 
     * @param cityId
     *            城市的主键ID
     * @return 查询到的城市信息的实体
     */
    City findCityByPrimaryKey(Long cityId);

    /**
     * 根据分页实体以及查询参数查询列表
     * 
     * @param pb
     *            分页帮助类
     * @return 封住了查询到的列表的实体
     */
    PageBean findListByPageBean(PageBean pb, SelectBean selectBean);

    /**
     * 批量删除城市实体
     * 
     * @param cityIds
     *            城市ID数组
     * @return 删除的行数
     */
    int batchDelCity(String[] cityIds);

    /**
     * 根据省份ID查询城市的列表
     * 
     * @param provinceId
     *            省份ID
     * @return 查询到的列表
     */
    List<CityVo> queryCityByProvinceId(Long provinceId);

    /**
     * 验证城市名称是否可用
     * 
     * @param cityName
     *            待验证的城市名称
     * @return 验证的结果 可用返回true 否则返回false
     */
    boolean checkCityName(String cityName);

    /**
     * 根据城市名称查询所属的省份名称
     * 
     * @param cityId
     *            城市ID
     * @return 查询到的地址帮助Bean
     */
    AddressUtil queryAddressNameByCityId(Long cityId);

    /**
     * 根据省份id和城市名称查询所有城市
     * @param provinceId 省份id
     * @param cityName 城市名称
     * @return
     */
    List<Object> queryCityByProvinceIdAndCityName(Long provinceId, String cityName);
}
