/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.core.manager.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.manager.bean.Manager;
import com.ningpai.manager.mapper.ManagerMapper;
import com.ningpai.manager.service.ManagerSmsService;
import com.ningpai.manager.service.impl.ManagerSmsServiceImpl;
import com.ningpai.smscommon.bean.Sms;
import com.ningpai.smscommon.dao.SmsMapper;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.List;

public class ManagerSmsServiceTest extends UnitilsJUnit3 {


    /**
     * 需要测试的Service
     */
    @TestedObject
    private ManagerSmsService managerService  = new ManagerSmsServiceImpl();

    @InjectIntoByType
    Mock<ManagerMapper> managerMapperMock;

    @InjectIntoByType
    Mock<SmsMapper> smsMapperMock;


    /**
     * JS数据
     */
    @FileContent("managerList.js")
    private String managerListJs;
    @FileContent("smsList.js")
    private String smsListJs;

    /**
     * 共享数据
     */
    List<Manager> managerList;
    List<Sms> smsList;

    /**
     * 初始化
     */
    @Override
    public void setUp(){
        managerList = JSON.parseArray(managerListJs,Manager.class);
        smsList = JSON.parseArray(smsListJs,Sms.class);
    }


    /**
     * 发送手机验证码
     */
    @Test
    public void testSendPost(){
        MockHttpServletRequest request = new MockHttpServletRequest("POST","");
        managerMapperMock.returns(managerList.get(0)).selectCaptcha(1L);
        smsMapperMock.returns(smsList.get(0)).selectSms();
        managerMapperMock.returns(1).updateSmsCaptcha(managerList.get(0));
        request.getSession().setAttribute("loginUserId", 1L);
       // assertEquals(1, managerService.sendPost(request, "13588888888"));

    }

    /**
     * 验证手机验证码
     */
    public void testGetMCode(){
        managerMapperMock.returns(managerList.get(0)).selectCaptcha(1L);
        managerMapperMock.returns(1).updateSmsCaptcha(managerList.get(0));
        MockHttpServletRequest request = new MockHttpServletRequest("POST","");
        request.getSession().setAttribute("loginUserId", 1L);
        assertEquals(1, managerService.getMCode(request, "001"));
    }

    /**
     * 查询管理员手机
     */
    public void testQueryManagerById(){
        managerMapperMock.returns(managerList.get(0)).selectCaptcha(1L);
        assertNotNull(managerService.queryManagerById(1L));
    }
}
