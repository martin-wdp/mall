package com.junit.common.freighttemplate.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.freighttemplate.bean.SysLogisticsCompany;
import com.ningpai.freighttemplate.dao.SysLogisticsCompanyMapper;
import com.ningpai.freighttemplate.service.SysLogisticsCompanyService;
import com.ningpai.freighttemplate.service.impl.SysLogisticsCompanyServiceImpl;
import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.List;

/**
 * 快递公司接口Service测试
 *
 */
public class SysLogisticsCompanyServiceTest extends UnitilsJUnit3 {

    /**
     * 需要测试的Service
     */
    @TestedObject
    private SysLogisticsCompanyService sysLogisticsCompanyService = new SysLogisticsCompanyServiceImpl();

    /**
     * JS 数据
     */
    @FileContent("companyList.js")
    private String companyListJs;

    /**
     * 共享数据
     */
    List<SysLogisticsCompany> sysLogisticsCompanyList;


    @InjectIntoByType
    Mock<SysLogisticsCompanyMapper> sysLogisticsCompanyMapperMock;
    /**
     * 初始化数据
     */
    public void setUp(){
        sysLogisticsCompanyList  = JSON.parseArray(companyListJs, SysLogisticsCompany.class);

    }
    /**
     * 查询快递公司列表
     */
    @Test
   public void testSelectAllCompnay(){
        sysLogisticsCompanyMapperMock.returns(sysLogisticsCompanyList).selectAllCompnay();
        assertEquals(1,sysLogisticsCompanyService.selectAllCompnay().size());
   }

}
