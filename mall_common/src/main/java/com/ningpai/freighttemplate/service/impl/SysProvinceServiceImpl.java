package com.ningpai.freighttemplate.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.freighttemplate.bean.SysProvince;
import com.ningpai.freighttemplate.dao.SysProvinceMapper;
import com.ningpai.freighttemplate.service.SysProvinceService;

/**
 * 系统省Service
 * 
 * @author ggn
 *
 */
@Service("SysProvinceService")
public class SysProvinceServiceImpl implements SysProvinceService {

    // spring 注入
    @Resource(name = "SysProvinceMapper")
    private SysProvinceMapper sysProvinceMapper;

    /*
     * 查询所有城市
     * @see
     * com.ningpai.freighttemplate.service.SysProvinceService#selectAllProvince
     * ()
     */
    @Override
    public List<SysProvince> selectAllProvince() {
        // 查询所有省
        return sysProvinceMapper.selectAllProvince();
    }

    /*
     * 根据主键查询省份信息
     * @see
     * com.ningpai.freighttemplate.service.SysProvinceService#selectProvinceById
     * (java.lang.Long)
     */
    @Override
    public SysProvince selectProvinceById(Long provinceId) {
        // 根据省ID查询
        return sysProvinceMapper.selectProvinceById(provinceId);
    }

}
