package com.ningpai.freighttemplate.dao;

import java.util.List;

import com.ningpai.freighttemplate.bean.SysProvince;

/**
 * 省 mapper 2014-12-17
 * 
 * @author ggn
 *
 */
public interface SysProvinceMapper {

    /**
     * 查询所有省
     * 
     * @return List
     */
    List<SysProvince> selectAllProvince();

    /**
     * 根据主键查询省份信息
     * 
     * @param provinceId
     * @return SysProvince
     */
    SysProvince selectProvinceById(Long provinceId);

}
