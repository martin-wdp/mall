/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.common.logger.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.mock.web.MockHttpServletResponse;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import com.alibaba.fastjson.JSON;
import com.ningpai.logger.bean.OperationLog;
import com.ningpai.logger.mapper.OperaLogMapper;
import com.ningpai.logger.service.OperaLogService;
import com.ningpai.logger.service.impl.OperaLogServiceImpl;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;

/**
 * 操作日志Service测试
 */
public class OperaLogServiceTest  extends UnitilsJUnit3 {


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
    @FileContent("operationLogList.js")
    private String operationLogListJs;


    /**
     * 共享数据
     */
    List<OperationLog> operationLogList;

    /**
     * 初始化
     */
    @Override
    public void setUp(){
        operationLogList = JSON.parseArray(operationLogListJs, OperationLog.class);
    }


    /***
     * 根据ID获取对应日志的操作内容
     */
    public void testSelectLogById(){
        operaLogMapperMock.returns(operationLogList.get(0)).selectLogById(1L);
        assertNotNull(operaLogService.selectLogById(1L));
    }
    /**
     * 添加操作日志
     */
    public void testAddOpearLog() {
        operaLogMapperMock.returns(1).addOperaLog(operationLogList.get(0));
        assertEquals(1, operaLogService.addOperaLog(operationLogList.get(0)));
    }

    /**
     * 去重获取opname
     */
    public void testSelectDistinctOpName(){
        operaLogMapperMock.returns(operationLogList).selectDistinctOpName();
        assertEquals(1, operaLogService.selectDistinctOpName().size());
    }

    /**
     * 查询操作日志
     */

    public void testSelectAllOpera(){
        Map<String, Object> paramMap  = MapUtil.getParamsMap(new OperationLog());
        paramMap.put("startTime", "2015-01-01 01:01:01");
        paramMap.put("endTime", "2018-01-01 01:01:01");
        operaLogMapperMock.returns(1L).selectOperaSize(paramMap);
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 15);
        operaLogMapperMock.returns(operationLogList).selectAllOpera(paramMap);
        assertEquals(1, operaLogService.selectAllOpera(new PageBean(), new OperationLog(), "2015-01-01 01:01:01", "2018-01-01 01:01:01").getList().size());
    }

    /**
     * 根据时间段导出日志
     *
     */
    public void testExportExcel() {
        // HttpServletResponse获取方式
        MockHttpServletResponse response = new MockHttpServletResponse();
        Map<String, Object> paramMap = new HashMap<String, Object>();
            // 设置查询参数
            paramMap.put("days", 7L);
        operaLogMapperMock.returns(operationLogList).selectLogListByDays(paramMap);
        operaLogService.exportExcel(response, 7L);
    }

    /**
     * 删除日志
     */
    public void testDeleteLog(){
        //int deleteLog(String[] opIds);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("opIds", new String[]{"1"});
        operaLogMapperMock.returns(1).deleteLog(paramMap);
        assertEquals(1,operaLogService.deleteLog(new String[]{"1"}));
    }

    /**
     * 查看最新的订单日志
     */
    public void testQueryNewLog(){
        //PageBean  queryNewLog(PageBean pageBean);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        operaLogMapperMock.returns(1L).selectOperaSize(paramMap);
        paramMap.put("startRowNum", 0);
        // 设置结束行数
        paramMap.put("endRowNum", 15);
        operaLogMapperMock.returns(operationLogList).selectAllOpera(paramMap);
        assertEquals(1, operaLogService.queryNewLog(new PageBean()).getList().size());
    }
}
