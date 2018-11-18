package com.ningpai.freighttemplate.service;

import java.util.List;

import com.ningpai.freighttemplate.bean.SysProvince;

/**
 * 省会service
 * @author ggn
 *
 */
public interface SysProvinceService {

    /**
     * 查询所有城市
     * @return List
     */
    List<SysProvince> selectAllProvince();

    /**
     * 根据主键查询省份信息
     * @param provinceId
     * @return SysProvince
     */
    SysProvince selectProvinceById(Long provinceId);

}
