package com.junit.common.freighttemplate.service;

import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import com.alibaba.fastjson.JSON;
import com.ningpai.freighttemplate.bean.SysCity;
import com.ningpai.freighttemplate.bean.SysDistrict;
import com.ningpai.freighttemplate.dao.SysCityMapper;
import com.ningpai.freighttemplate.dao.SysDistrictMapper;
import com.ningpai.freighttemplate.service.SysDistrictService;
import com.ningpai.freighttemplate.service.impl.SysDistrictServiceImpl;

/**
 * 区县service 单元测试
 * @author ggn
 *
 */
public class SysDistrictServiceTest extends UnitilsJUnit3 {

    /**
     * 需要测试的Service
     */
    @TestedObject
    private SysDistrictService sysDistrictService = new SysDistrictServiceImpl();

    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<SysDistrictMapper> sysDistrictMapperMock;
    @InjectIntoByType
    Mock<SysCityMapper> sysCityMapperMock;

    /**
     * JS数据
     */
    @FileContent("sysdistrictList.js")
    private String sysdistrictListJs;
    @FileContent("sysCity.js")
    private String syscityJS;
    /**
     * 共享数据
     */
    List<SysDistrict> districtList;
    SysCity  sysCity;
    /**
     * 初始化
     */
    @Override
    public void setUp(){
        districtList = JSON.parseArray(sysdistrictListJs, SysDistrict.class);
        sysCity= JSON.parseObject(syscityJS, SysCity.class);
    }


    /**
     * 查询区县
     */
    @Test
    public void testSelectAllDistrictByCityId(){
        sysDistrictMapperMock.returns(districtList).selectAllDistrictByCityId(1L);
        assertEquals(1, sysDistrictService.selectAllDistrictByCityId(1L).size());
    }


    /**
     * 获取指定城市下面的所有区县ID
     */
    @Test
    public void testSelectCityById(){
        sysCityMapperMock.returns(sysCity).selectCityById(1L);
        assertNotNull(sysDistrictService.selectAllDistrictByCityId(1L));
    }


}
