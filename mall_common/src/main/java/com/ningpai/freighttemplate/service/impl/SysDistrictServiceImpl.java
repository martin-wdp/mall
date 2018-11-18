package com.ningpai.freighttemplate.service.impl;

import com.ningpai.freighttemplate.bean.SysCity;
import com.ningpai.freighttemplate.bean.SysDistrict;
import com.ningpai.freighttemplate.dao.SysCityMapper;
import com.ningpai.freighttemplate.dao.SysDistrictMapper;
import com.ningpai.freighttemplate.service.SysDistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 区县service 实现类
 * 
 * @author ggn
 *
 */
@Service("SysDistrictService")
public class SysDistrictServiceImpl implements SysDistrictService {
    // Spring注入
    @Resource(name = "SysDistrictMapper")
    private SysDistrictMapper sysDistrictMapper;
    // Spring注入
    @Resource(name = "SysCityMapper")
    private SysCityMapper sysCityMapper;

    /*
     * 查询区县
     * @see com.ningpai.freighttemplate.service.SysDistrictService#
     * selectAllDistrictByCityId(java.lang.Long)
     */
    @Override
    public List<SysDistrict> selectAllDistrictByCityId(Long freightCityId) {

        if (freightCityId != null) {
            // 根据物流模板城市ID查询所有区县
            return sysDistrictMapper.selectAllDistrictByCityId(freightCityId);
        } else {
            return new ArrayList<SysDistrict>();
        }
    }

    /*
     * 获取指定城市下面的所有区县ID
     * @see
     * com.ningpai.freighttemplate.service.SysDistrictService#selectCityById
     * (java.lang.Long)
     */
    @Override
    public SysCity selectCityById(Long cityId) {
        return sysCityMapper.selectCityById(cityId);
    }
}
