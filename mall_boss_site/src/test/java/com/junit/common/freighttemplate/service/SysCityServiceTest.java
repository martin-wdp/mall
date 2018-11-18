package com.junit.common.freighttemplate.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.freighttemplate.bean.SysCity;
import com.ningpai.freighttemplate.dao.SysCityMapper;
import com.ningpai.freighttemplate.service.SysCityService;
import com.ningpai.freighttemplate.service.impl.SysCityServiceImpl;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.List;

/**
 * 城市单元测试service
 *
 */
public class SysCityServiceTest  extends UnitilsJUnit3 {

    /**
     * 需要测试的Service
     */
    @TestedObject
    private SysCityService sysCityService = new SysCityServiceImpl();


    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<SysCityMapper> sysCityMapperMock;
    /**
     * JS数据
     */
    @FileContent("syscityList.js")
    private String syscityListJs;


    /**
     * 共享数据
     */
    List<SysCity> sysCityList;
    /**
     * 初始化
     */
    @Override
    public void setUp(){
        sysCityList = JSON.parseArray(syscityListJs,SysCity.class);
    }

    /**
     * 查询城市
     */
    public void testSelectAllCityByProvinceId(){
        sysCityMapperMock.returns(sysCityList).selectAllCityByProvinceId(1L);
        assertEquals(1,sysCityService.selectAllCityByProvinceId(1L).size());
    }
}
