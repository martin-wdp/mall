package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.Street;
import com.ningpai.system.util.AddressUtil;

/**
 * 街道DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月13日 上午11:18:16
 * @version 1.0
 */
public interface StreetMapper {
    /**
     * 根据主键删除
     * 
     * @param streetId
     *            待删除的街道ID
     * @return 删除的行数
     */
    int deleteByPrimaryKey(Long streetId);

    /**
     * 插入新纪录
     * 
     * @param record
     *            待插入的实体
     * @return 插入的行数
     */
    int insertSelective(Street record);

    /**
     * 根据主键查询
     * 
     * @param streetId
     *            主键ID
     * @return 查询到的实体
     */
    Street selectByPrimaryKey(Long streetId);

    /**
     * 更新记录
     * 
     * @param record
     *            待更新的实体
     * @return 更新的行数
     */
    int updateByPrimaryKeySelective(Street record);

    /**
     * 根据参数查询列表
     * 
     * @param map
     *            封装了所有的参数
     * @return 查询到的集合
     */
    List<Object> queryAllStreetByPb(Map<String, Object> map);

    /**
     * 根据参数查询行数
     * 
     * @param map
     *            封装了参数
     * @return 查询到的行数
     */
    int queryTotalCount(Map<String, Object> map);

    /**
     * 根据区县ID查询所有的街道
     * 
     * @param districtId
     *            区县地址
     * @return 查询到的街道的集合
     */
    List<Street> queryStreetByDistrictId(Long districtId);

    /**
     * 根据街道名称查询是否已经存在
     * 
     * @param map
     *            街道名称
     * @return 查询到的行数
     */
    int queryStreetByStreetName(Map<String, Object> map);

    /**
     * 根据街道ID查询所属的区县，城市和省份名称
     * 
     * @param streetId
     * @return
     */
    AddressUtil queryAddressNameByStreetId(Long streetId);
}
