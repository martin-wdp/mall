package com.junit.order.order.service;

import com.ningpai.order.bean.BackOrderLog;
import com.ningpai.order.dao.BackOrderLogMapper;
import com.ningpai.order.service.BackOrderLogService;
import com.ningpai.order.service.impl.BackOrderLogServiceImpl;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhoux on 2015/6/10.
 */
public class BackOrderLogServiceTest extends UnitilsJUnit3 {

    @TestedObject
    private BackOrderLogService backOrderLogService = new BackOrderLogServiceImpl();

    @InjectIntoByType
    Mock<BackOrderLogMapper> backOrderLogMapperMock;



    /**
     * 记录退单操作日志
     */
    public void testInsert(){
        BackOrderLog backOrderLog = new BackOrderLog();
        backOrderLogMapperMock.returns(1).insert(backOrderLog);
        assertEquals(1,backOrderLogService.insert(backOrderLog));
    }

    /**
     * 获取退单日志信息
     */
    public void testQueryByBackId(){
        BackOrderLog backOrderLog = new BackOrderLog();
        List<BackOrderLog> backOrderLogList = new ArrayList<BackOrderLog>();
        backOrderLogList.add(backOrderLog);
        backOrderLogMapperMock.returns(backOrderLogList).queryByBackId(1L);
        assertEquals(1,backOrderLogService.queryByBackId(1L).size());
    }

}
