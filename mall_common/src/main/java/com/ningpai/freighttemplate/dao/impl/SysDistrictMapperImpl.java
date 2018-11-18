package com.ningpai.freighttemplate.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.freighttemplate.bean.SysDistrict;
import com.ningpai.freighttemplate.dao.SysDistrictMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 区县实现类
 * 
 * @author ggn
 *
 */
@Repository("SysDistrictMapper")
public class SysDistrictMapperImpl extends BasicSqlSupport implements
        SysDistrictMapper {

    /*
     * 查询区县列表
     * @see
     * com.ningpai.freighttemplate.dao.SysDistrictMapper#selectAllDistrictByCityId
     * (java.lang.Long)
     */
    @Override
    public List<SysDistrict> selectAllDistrictByCityId(Long cityId) {

        return this
                .selectList(
                        "com.qpmall.web.dao.SysDistrictMapper.selectAllDistrictByCityId",
                        cityId);
    }

}
