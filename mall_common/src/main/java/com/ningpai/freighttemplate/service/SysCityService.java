package com.ningpai.freighttemplate.service;

import java.util.List;

import com.ningpai.freighttemplate.bean.SysCity;

/**
 * 城市service
 * @author ggn
 *
 */
public interface SysCityService {

    /**
     * 查询城市
     * @param freightProvinceId
     * @return List
     */
    List<SysCity> selectAllCityByProvinceId(Long freightProvinceId);

}
