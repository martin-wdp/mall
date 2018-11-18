package com.junit.common.freighttemplate.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.freighttemplate.bean.SysProvince;
import com.ningpai.freighttemplate.dao.SysProvinceMapper;
import com.ningpai.freighttemplate.service.SysProvinceService;
import com.ningpai.freighttemplate.service.impl.SysProvinceServiceImpl;
import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.List;

/**
 * 省会service单元测试
 *
 */
public class SysProvinceServiceTest extends UnitilsJUnit3 {


    /**
     * 需要测试的Service
     */
    @TestedObject
    private SysProvinceService sysProvinceService = new SysProvinceServiceImpl();

    @InjectIntoByType
    Mock<SysProvinceMapper> sysProvinceMapperMock;

    /**
     * JS数据
     */
    @FileContent("sysProvinceList.js")
    private String sysProvinceListJS;


    /**
     * 共享数据
     */
    List<SysProvince> sysProvinceList;


    /**
     * 初始化数据
     */
    @Override
    public void setUp(){
        sysProvinceList = JSON.parseArray(sysProvinceListJS,SysProvince.class);
    }

    /**
     * 查询所有城市
     */
    @Test
    public void testSelectAllProvince(){
        sysProvinceMapperMock.returns(sysProvinceList).selectAllProvince();
        assertEquals(1, sysProvinceService.selectAllProvince().size());

    }
    /**
     * 根据主键查询省份信息
     */
    @Test
    public void testSelectProvinceById(){
        sysProvinceMapperMock.returns(sysProvinceList.get(0)).selectProvinceById(1L);
        assertNotNull(sysProvinceService.selectProvinceById(1L));

    }

}
