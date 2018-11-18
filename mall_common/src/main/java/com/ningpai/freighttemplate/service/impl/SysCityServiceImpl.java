package com.ningpai.freighttemplate.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.freighttemplate.bean.SysCity;
import com.ningpai.freighttemplate.dao.SysCityMapper;
import com.ningpai.freighttemplate.service.SysCityService;

/**
 * 城市实现类
 * 
 * @author ggn
 *
 */
@Service("SysCityService")
public class SysCityServiceImpl implements SysCityService {
    // Spring注入
    @Resource(name = "SysCityMapper")
    private SysCityMapper sysCityMapper;

    /*
     * 查询城市
     * @see
     * com.ningpai.freighttemplate.service.SysCityService#selectAllCityByProvinceId
     * (java.lang.Long)
     */
    @Override
    public List<SysCity> selectAllCityByProvinceId(Long freightProvinceId) {

        if (freightProvinceId != null) {
            // 查询所有城市
            return sysCityMapper.selectAllCityByProvinceId(freightProvinceId);
        } else {
            return new ArrayList<SysCity>();
        }
    }

}
