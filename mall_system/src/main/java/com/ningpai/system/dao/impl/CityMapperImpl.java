/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.City;
import com.ningpai.system.dao.CityMapper;
import com.ningpai.system.util.AddressUtil;
import com.ningpai.system.vo.CityVo;

/**
 * 城市Dao实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月13日 上午9:33:07
 * @version 1.0
 */
@Repository("CityMapper")
public class CityMapperImpl extends BasicSqlSupport implements CityMapper {
    /**
     * 根据主键ID删除
     * 
     * @see com.ningpai.system.dao.CityMapper#deleteByPrimaryKey(java.lang.Long)
     */
    public int deleteByPrimaryKey(Long cityId) {
        return this.update(
                "com.ningpai.system.dao.CityMapper.deleteByPrimaryKey", cityId);
    }

    /**
     * 插入一条新纪录
     * 
     * @see
     * com.ningpai.system.dao.CityMapper#insertSelective(com.ningpai.system.
     * bean.City)
     */
    public int insertSelective(City record) {
        return this.insert("com.ningpai.system.dao.CityMapper.insertSelective",
                record);
    }

    /**
     * 根据主键ID查询城市信息
     * 
     * @see com.ningpai.system.dao.CityMapper#selectByPrimaryKey(java.lang.Long)
     */
    public City selectByPrimaryKey(Long cityId) {
        return this.selectOne(
                "com.ningpai.system.dao.CityMapper.selectByPrimaryKey", cityId);
    }

    /**
     * 更新记录
     * 
     * @see
     * com.ningpai.system.dao.CityMapper#updateByPrimaryKeySelective(com.ningpai
     * .system.bean.City)
     */
    public int updateByPrimaryKeySelective(City record) {
        return this
                .update("com.ningpai.system.dao.CityMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 根据参数查询所有的行数
     * 
     * @see
     * com.ningpai.system.dao.CityMapper#queryTotalCount(com.ningpai.system.
     * util.SelectBean)
     */
    public int queryTotalCount(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.system.dao.CityMapper.queryTotalCount", map);
    }

    /**
     * 根据分页参数和查询参数查询列表
     * 
     * @see com.ningpai.system.dao.CityMapper#queryCityListByPb(java.util.Map)
     */
    public List<Object> queryCityListByPb(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.system.dao.CityMapper.queryCityListByPb", map);
    }

    /**
     * 根据省份ID查询城市的列表
     * 
     * @see
     * com.ningpai.system.dao.CityMapper#queryCityByProvinceId(java.lang.Long)
     */
    public List<CityVo> queryCityByProvinceId(Long provinceId) {
        return this.selectList(
                "com.ningpai.system.dao.CityMapper.queryCityByProvinceId",
                provinceId);
    }

    /**
     * 根据城市名称查询城市是否存在
     * 
     * @see
     * com.ningpai.system.dao.CityMapper#queryCityByCityName(java.lang.String)
     */
    public int queryCityByCityName(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.system.dao.CityMapper.queryCityByCityName", map);
    }

    /**
     * 根据城市ID查询城市名称和所属的省份名称
     * 
     * @see
     * com.ningpai.system.dao.CityMapper#queryProvinceNameByCityId(java.lang
     * .Long)
     */
    public AddressUtil queryProvinceNameByCityId(Long cityId) {
        return this.selectOne(
                "com.ningpai.system.dao.CityMapper.queryProvinceNameByCityId",
                cityId);
    }
    /**
     * 根据身份id、城市名称查找城市信息
     * @param map
     *          provinceId省份id ,城市名称cityName
     * @return
     * add by 付陈林 2015年12月26日
     * */
    @Override
    public City selectByCityName(Map<String, Object> map) {
       return this.selectOne(
                "com.ningpai.system.dao.CityMapper.selectByCityName",
                map);
    }

}
