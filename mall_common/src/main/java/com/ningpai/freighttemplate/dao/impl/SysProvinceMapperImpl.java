package com.ningpai.freighttemplate.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.freighttemplate.bean.SysProvince;
import com.ningpai.freighttemplate.dao.SysProvinceMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 省实现类
 * 
 * @author ggn
 *
 */
@Repository("SysProvinceMapper")
public class SysProvinceMapperImpl extends BasicSqlSupport implements
        SysProvinceMapper {

    /*
     * 查询所有省
     * @see
     * com.ningpai.freighttemplate.dao.SysProvinceMapper#selectAllProvince()
     */
    @Override
    public List<SysProvince> selectAllProvince() {
        return this
                .selectList("com.qpmall.web.dao.SysProvinceMapper.selectAllProvince");
    }

    /**
     * 根据主键查询省份信息
     * @param provinceId
     * @return
     */
    @Override
    public SysProvince selectProvinceById(Long provinceId) {
        return this.selectOne(
                "com.qpmall.web.dao.SysProvinceMapper.selectByPrimaryKey",
                provinceId);
    }

}
