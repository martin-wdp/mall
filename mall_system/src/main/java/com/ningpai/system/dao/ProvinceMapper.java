package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.Province;

/**
 * 地区管理省份表
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月12日 下午3:09:03
 * @version 1.0
 */
public interface ProvinceMapper {
    /**
     * 根据主键ID删除省份信息
     * 
     * @param provinceId
     * @return 删除的行数
     */
    int deleteByPrimaryKey(Long provinceId);

    /**
     * 插入一条新的记录
     * 
     * @param record
     *            待插入的实体
     * @return 插入的行数
     */
    int insertSelective(Province record);

    /**
     * 根据主键查询
     * 
     * @param provinceId
     *            主键ID
     * @return 查询到的实体
     */
    Province selectByPrimaryKey(Long provinceId);

    /**
     * 更新记录
     * 
     * @param record
     *            待更新的实体
     * @return 更新的行数
     */
    int updateByPrimaryKeySelective(Province record);

    /**
     * 分页查询省份信息
     * 
     * @param map
     *            封装参数的MAP
     * @return 查询到的列表
     */
    List<Object> queryAllProvice(Map<String, Object> map);

    /**
     * 根据参数查询所有的行数
     * 
     * @param map
     *            封装了查询参数
     * @return 查询到的行数
     */
    Integer queryTotalCount(Map<String, Object> map);

    /**
     * 根据名称查询是否存在
     * 
     * @param map
     *            省份名称
     * @return 查询到的行数
     */
    int queryProvinceByName(Map<String, Object> map);

    /**
     * 查询所有的省份信息
     * 
     * @return 查询到的省份列表
     */
    List<Object> queryAllProvince();

    /**
     * 查询所有省份、城市、县区、街道
     * 
     * @param limitmap
     *            查询参数
     * @return 结果列表
     */
    List<Object> queryAllProviceNew(Map<String, Object> limitmap);
    /**
     * 根据省份名称查询
     * @param provinceName
     *         省份名称
     * @return 结果列表
     *
     * add by 付陈林 2015年12月26日
     * */
    Province selectByProvinceName(String provinceName);
}
