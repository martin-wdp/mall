package com.ningpai.freighttemplate.dao;

import java.util.List;

import com.ningpai.freighttemplate.bean.SysCity;

/**
 * 城市mapper 2014-12-17
 * 
 * @author ggn
 *
 */
public interface SysCityMapper {

    /**
     * 查询城市名称
     * 
     * @param valueOf
     * @return SysCity
     */
    SysCity selectCityById(Long valueOf);

    /**
     * 查询城市列表
     * 
     * @param freightProvinceId
     * @return List
     */
    List<SysCity> selectAllCityByProvinceId(Long freightProvinceId);

}
