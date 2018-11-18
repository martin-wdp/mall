/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.core.logcore.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.logcore.bean.OperationLog;
import com.ningpai.logcore.mapper.OperaLogMapper;
import com.ningpai.logcore.service.OperaLogService;
import com.ningpai.logcore.service.impl.OperaLogServiceImpl;
import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

/**
 * 操作日志Service
 */
public class OperaLogServiceTest extends UnitilsJUnit3 {

    /**
     * 需要测试的Service
     */
    @TestedObject
    private OperaLogService operaLogService = new OperaLogServiceImpl();


    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<OperaLogMapper> operaLogMapperMock;
    /**
     * JS数据
     */
    @FileContent("logger.js")
    private String logJs;

    /**
     * 共享数据
     */
    OperationLog log;

    /**
     * 初始化
     */
    @Override
    public void setUp(){
        log = JSON.parseObject(logJs, OperationLog.class);
    }

    /**
     * 添加操作日志
     */
    @Test
    public void testAddOperaLog(){
        operaLogMapperMock.returns(1).addOperaLog(log);
        assertEquals(1,operaLogService.addOperaLog(log));
    }
}
