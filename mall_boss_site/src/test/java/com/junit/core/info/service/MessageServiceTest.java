/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.core.info.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.info.bean.MessageBean;
import com.ningpai.info.dao.MessageSendMapper;
import com.ningpai.info.service.MessageService;
import com.ningpai.info.service.impl.MessageServiceImpl;
import com.ningpai.util.PageBean;
import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息设置Service借口测试
 *
 */
public class MessageServiceTest extends UnitilsJUnit3 {

    /**
     * 需要测试的Service
     */
    @TestedObject
    private MessageService messageService = new MessageServiceImpl();

    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<MessageSendMapper> messageSendMapperMock;
    /**
     * JS数据
     */
    @FileContent("messageList.js")
    private String messageListJs;


    /**
     * 共享数据
     */
    List<MessageBean> messageBeanList;
    /**
     * 初始化
     */
    @Override
    public void setUp(){
        messageBeanList = JSON.parseArray(messageListJs, MessageBean.class);
    }




    /**
     * 查询消息设置
     */
    @Test
    public void testSelectInform(){
        // 设置查询参数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("informType", 1);
        map.put("informStatus", 1);
        messageSendMapperMock.returns(messageBeanList.get(0)).selectInformMapper(map);
        assertNotNull(messageService.selectInform(1, 1));
    }
    /**
     * 修改消息设置
     */
    @Test
    public void testUpdateInform(){
        messageSendMapperMock.returns(1).updateInformMapper(messageBeanList.get(0));
        assertEquals(1, messageService.updateInform(messageBeanList.get(0)));
    }
    /**
     * 分页查询列表
     */
    @Test
    public void testSelectList(){
        // 查询列表
        Map<String, Object> Map = new HashMap<String, Object>();
        // 查询总数
        messageSendMapperMock.returns(1).selectAllSize(Map);
        Map.put("startRowNum", 0);
        Map.put("endRowNum", 15);
        messageSendMapperMock.returns(messageBeanList).selectListMapper(Map);
        assertEquals(1, messageService.selectList(new PageBean()).getList().size());

    }
    /**
     * 根据id查询
     */
    @Test
    public void testSelectById(){
        messageSendMapperMock.returns(messageBeanList.get(0)).selectByIdMapper(1);
        assertNotNull(messageService.selectById(1));
    }
    /**
     * 根据id查询出subject字段
     */
    @Test
    public void testFindSubject(){
        messageSendMapperMock.returns("QianMi").findSubjectMapper(1);
        assertEquals("QianMi", messageService.findSubject(1));
    }
    /**
     * 根据id查询出text字
     */
    @Test
    public void testFindText(){
        messageSendMapperMock.returns("QianMi").findTextMapper(1);
        assertEquals("QianMi", messageService.findText(1));
    }
}
