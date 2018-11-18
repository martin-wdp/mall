/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.custom.supplieraudit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.supplieraudit.bean.StoreInfo;
import com.ningpai.supplieraudit.mapper.SupplierStoreInfoMapper;
import com.ningpai.supplieraudit.service.SupplierAuditService;
import com.ningpai.supplieraudit.service.impl.SupplierAuditServiceImpl;
import com.ningpai.util.PageBean;

/**
 * 商家审核接口
 */
public class  SupplierAuditServiceTest extends UnitilsJUnit3 {
    /**
     * 需要测试的Service
     */
    @TestedObject
    private SupplierAuditService supplierAuditService  = new SupplierAuditServiceImpl();

    @InjectIntoByType
    Mock<SupplierStoreInfoMapper> supplierStoreInfoMapperMock;
    /**
     * 查询供应商审核列表
     */
    public void testSelectSupplierAuditList(){
        List<StoreInfo> list = new ArrayList<StoreInfo>();
        StoreInfo si = new StoreInfo();
        si.setCompanyName("qianmi");
        list.add(si);
        supplierStoreInfoMapperMock.returns(1L).selectSupplierAuditCount(new StoreInfo());
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("storeInfo", new StoreInfo());
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum",15);
        paramMap.put("companyName","qianmi");
        // 查询会员信息
        supplierStoreInfoMapperMock.returns(list).selectSupplierAuditList(paramMap);
        assertEquals(1,supplierAuditService.selectSupplierAuditList(si,new PageBean()).getList().size());
    }
}
