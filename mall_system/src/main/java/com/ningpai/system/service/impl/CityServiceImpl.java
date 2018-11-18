/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.system.bean.City;
import com.ningpai.system.dao.CityMapper;
import com.ningpai.system.service.CityService;
import com.ningpai.system.util.AddressUtil;
import com.ningpai.system.util.SelectBean;
import com.ningpai.system.vo.CityVo;
import com.ningpai.util.PageBean;

/**
 * 城市Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月13日 上午9:46:59
 * @version 1.0
 */
@Service("CityService")
public class CityServiceImpl implements CityService {
    /** 城市注解 */
    private CityMapper cityMapper;

    public CityMapper getCityMapper() {
        return cityMapper;
    }

    @Resource(name = "CityMapper")
    public void setCityMapper(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    /**
     * 保存城市信息
     * 
     * @see
     * com.ningpai.system.service.CityService#saveCity(com.ningpai.system.bean
     * .City)
     */
    public int saveCity(String cityName, String provinceId, String citySort) {
        City city = new City();
        try {
            if (null != cityName) {
                city.setCityName(cityName);
                city.setCitySort(citySort);
                city.setProvinceId(Long.parseLong(provinceId));
                city.setDelFlag("0");
                return this.cityMapper.insertSelective(city);
            }
            return 0;
        } finally {
            city = null;
        }

    }

    /**
     * 删除城市信息
     * 
     * @see com.ningpai.system.service.CityService#delPCity(java.lang.Long)
     */
    public int delPCity(Long cityId) {
        return this.cityMapper.deleteByPrimaryKey(cityId);
    }

    /**
     * 更新城市信息
     * 
     * @see
     * com.ningpai.system.service.CityService#updateCity(com.ningpai.system.
     * bean.City)
     */
    public int updateCity(String cityId, String cityName, String citySort) {
        City city = new City();
        try {
            if (null != cityId) {
                city.setCityId(Long.parseLong(cityId));
                city.setCityName(cityName);
                city.setCitySort(citySort);
                return this.cityMapper.updateByPrimaryKeySelective(city);
            } else {
                return 0;
            }
        } finally {
            city = null;
        }
    }

    /**
     * 根据主键ID查询城市信息
     * 
     * @see
     * com.ningpai.system.service.CityService#findCityByPrimaryKey(java.lang
     * .Long)
     */
    public City findCityByPrimaryKey(Long cityId) {
        return this.cityMapper.selectByPrimaryKey(cityId);
    }

    /**
     * 根据分页实体以及查询参数查询列表
     * 
     * @see
     * com.ningpai.system.service.CityService#findListByPageBean(com.ningpai
     * .util.PageBean, com.ningpai.system.util.SelectBean)
     */
    public PageBean findListByPageBean(PageBean pb, SelectBean selectBean) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            pb.setRows(this.cityMapper.queryTotalCount(map));
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(this.cityMapper.queryCityListByPb(map));
        } finally {
            map = null;
        }
        return pb;
    }

    /**
     * 批量删除城市实体
     * 
     * @see
     * com.ningpai.system.service.CityService#batchDelCity(java.lang.Long[])
     */
    public int batchDelCity(String[] cityIds) {
        int count = 0;
        if (null != cityIds && cityIds.length > 0) {
            for (int i = 0; i < cityIds.length; i++) {
                count += this.delPCity(Long.parseLong(cityIds[i]));
            }
        }
        return count;
    }

    /**
     * 根据省份ID查询城市的列表
     * 
     * @see
     * com.ningpai.system.service.CityService#queryCityByProvinceId(java.lang
     * .Long)
     */
    public List<CityVo> queryCityByProvinceId(Long provinceId) {
        return this.cityMapper.queryCityByProvinceId(provinceId);
    }

    /**
     * 验证城市名称是否可用
     * 
     * @see
     * com.ningpai.system.service.CityService#checkCityName(java.lang.String)
     */
    public boolean checkCityName(String cityName) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("cityName", cityName);
            return this.cityMapper.queryCityByCityName(map) > 0 ? false : true;
        } finally {
            map = null;
        }
    }

    /**
     * 根据城市名称查询所属的省份名称
     * 
     * @see
     * com.ningpai.system.service.CityService#queryAddressNameByCityId(java.
     * lang.Long)
     */
    public AddressUtil queryAddressNameByCityId(Long cityId) {
        return this.cityMapper.queryProvinceNameByCityId(cityId);
    }

    /**
     * 根据省份id和城市名称查询所有城市
     *
     * @param provinceId
     *            省份id
     * @param cityName
     *            城市名称
     * @return
     */
    @Override
    public List<Object> queryCityByProvinceIdAndCityName(Long provinceId,
            String cityName) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("provinceId", provinceId);
        paramMap.put("cityName", cityName);
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 1000);
        return this.cityMapper.queryCityListByPb(paramMap);
    }

}
