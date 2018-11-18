package com.ningpai.freighttemplate.service;

import java.util.List;

import com.ningpai.freighttemplate.bean.SysCity;
import com.ningpai.freighttemplate.bean.SysDistrict;

/**
 * 区县service
 * @author ggn
 *
 */
public interface SysDistrictService {

    /**  
     * 查询区县
     * @param freightCityId
     * @return List
     */
    List<SysDistrict> selectAllDistrictByCityId(Long freightCityId);


    /**
     * 获取指定城市下面的所有区县ID
     * @param cityId
     * @return SysCity
     */
    SysCity selectCityById(Long cityId);

}
