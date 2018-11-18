package com.ningpai.freighttemplate.dao;

import java.util.List;

import com.ningpai.freighttemplate.bean.SysDistrict;

/**
 * 区县接口
 * 
 * @author ggn
 *
 */
public interface SysDistrictMapper {

    /**
     * 查询区县列表
     * 
     * @param freightCityId
     * @return List
     */
    List<SysDistrict> selectAllDistrictByCityId(Long freightCityId);

}
