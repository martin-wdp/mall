package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.City;
import com.ningpai.system.util.AddressUtil;
import com.ningpai.system.vo.CityVo;

/**
 * 城市DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月12日 下午6:08:36
 * @version 1.0
 */
public interface CityMapper {
    /**
     * 根据主键ID删除
     * 
     * @param cityId
     *            城市ID
     * @return 删除的行数
     */
    int deleteByPrimaryKey(Long cityId);

    /**
     * 插入一条新纪录
     * 
     * @param record
     *            待插入的实体
     * @return 插入的行数
     */
    int insertSelective(City record);

    /**
     * 根据主键ID查询城市信息
     * 
     * @param cityId
     *            主键ID
     * @return 查询到的城市实体
     */
    City selectByPrimaryKey(Long cityId);

    /**
     * 更新记录
     * 
     * @param record
     *            待更新的实体
     * @return 更新的行数
     */
    int updateByPrimaryKeySelective(City record);

    /**
     * 根据参数查询所有的行数
     * 
     * @param map
     *            查询参数
     * @return 查询到的行数
     */
    int queryTotalCount(Map<String, Object> map);

    /**
     * 根据分页参数和查询参数查询列表
     * 
     * @param map
     *            封装了分页参数
     * @return 查询到的列表
     */
    List<Object> queryCityListByPb(Map<String, Object> map);

    /**
     * 根据省份ID查询城市的列表
     * 
     * @param provinceId
     *            省份ID
     * @return 查询到的城市的列表
     */
    List<CityVo> queryCityByProvinceId(Long provinceId);

    /**
     * 根据城市名称查询城市是否存在
     * 
     * @param map
     *            城市名称
     * @return 查询到的行数
     */
    int queryCityByCityName(Map<String, Object> map);

    /**
     * 根据城市ID查询城市名称和所属的省份名称
     * 
     * @param cityId
     *            城市ID
     * @return 地址帮助类
     */
    AddressUtil queryProvinceNameByCityId(Long cityId);

    /**
     * 根据城市名称查询城市信息
     *
     * @param map
     *            城市名称cityName ,provinceId省份id
     * @return 查询到的城市实体
     *
     * add by 付陈林 2015年12月26日
     */
    City selectByCityName(Map<String, Object> map);

}
