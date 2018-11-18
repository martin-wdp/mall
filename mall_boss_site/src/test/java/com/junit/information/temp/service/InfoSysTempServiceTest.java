/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.junit.information.temp.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.dao.InfoSysTempDao;
import com.ningpai.temp.service.InfoSysTempService;
import com.ningpai.temp.service.impl.InfoSysTempServiceImpl;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SERVICE-频道用模板service
 */
public class InfoSysTempServiceTest extends UnitilsJUnit3 {

    @TestedObject
    private InfoSysTempService infoSysTempService = new InfoSysTempServiceImpl();

    @InjectIntoByType
    Mock<InfoSysTempDao> sysTempDaoMock;

    /**
     * JS数据
     */
    @FileContent("sysTempList.js")
    private String sysTempListJs;
    /**
     * 共享数据
     */
    List<SysTemp> sysTempList;


    public  void setUp(){
        sysTempList = JSON.parseArray(sysTempListJs,SysTemp.class);
    }
    /**
     * 获取所有模板设置对象集合
     */
    public void testQueryAllSystemp(){
        Map<String, Object> map = new HashMap<String, Object>();
        /** 添加key和value */
        map.put("startRowNum", 0L);
        map.put("endRowNum", 1000L);
        sysTempDaoMock.returns(sysTempList).querySysTempByPage(map);
       assertEquals(1, infoSysTempService.queryAllSystemp().size());
    }

    /**
     * 根据主键查询模板设置对象
     */
     public void testGetSystempById(Long systempId){
         sysTempDaoMock.returns(sysTempList.get(0)).getSysTempById(1);
         assertNotNull(infoSysTempService.getSystempById(1L));
     }
}
