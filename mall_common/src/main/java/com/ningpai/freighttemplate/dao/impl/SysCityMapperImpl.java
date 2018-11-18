package com.ningpai.freighttemplate.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.freighttemplate.bean.SysCity;
import com.ningpai.freighttemplate.dao.SysCityMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 城市 实现类 2014-12-17
 * 
 * @author ggn
 *
 */
@Repository("SysCityMapper")
public class SysCityMapperImpl extends BasicSqlSupport implements SysCityMapper {

    /*
     * 查询城市名称
     * @see
     * com.ningpai.freighttemplate.dao.SysCityMapper#selectCityById(java.lang
     * .Long)
     */
    @Override
    public SysCity selectCityById(Long cityId) {

        return this.selectOne(
                "com.qpmall.web.dao.SysCityMapper.selectCityById", cityId);
    }

    /*
     * 查询城市列表
     * @see
     * com.ningpai.freighttemplate.dao.SysCityMapper#selectAllCityByProvinceId
     * (java.lang.Long)
     */
    @Override
    public List<SysCity> selectAllCityByProvinceId(Long provinceId) {

        return this.selectList(
                "com.qpmall.web.dao.SysCityMapper.selectAllCityByProvinceId",
                provinceId);
    }

}
