/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.core.manager.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.manager.mapper.NoticeMapper;
import com.ningpai.manager.service.NoticeService;
import com.ningpai.manager.service.impl.NoticeServiceImpl;
import com.ningpai.util.OrderInfoBean;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息Service
 */
public class NoticeServiceTest  extends UnitilsJUnit3 {

    /**
     * 需要测试的Service
     */
    @TestedObject
    private NoticeService noticeService  = new NoticeServiceImpl();

    @InjectIntoByType
    Mock<NoticeMapper> noticeMapperMock;

    @FileContent("orderInfoBeanList.js")
    private String orderInfoBeanListJs;

    /**
     * 共享数据
     */
    List<OrderInfoBean> orderInfoBeanList;

    /**
     * 初始化
     */
    @Override
    public void setUp(){
        orderInfoBeanList = JSON.parseArray(orderInfoBeanListJs, OrderInfoBean.class);
    }
    /**
     * 查询消息
     */
    public void testSelectNotice() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("qFlag", "1");
        paramMap.put("count", 10L);
        noticeMapperMock.returns(orderInfoBeanList).selectNotice(paramMap);
        assertEquals(1,noticeService.selectNotice(10L).size());
    }

    /**
     * 消息数量
     */
    public void testSelectNoticeNum() {
        noticeMapperMock.returns(1L).selectNoticeNum();
        assertEquals(1,noticeService.selectNoticeNum().intValue());
    }

    /**
     * 删除所有消息
     */
    public void testUpdateNotice() {
        noticeMapperMock.returns(1).updateNotice();
        assertEquals(1,noticeService.updateNotice());
    }

    /**
     * 更具id修改新增消息状态
     */
    public void testUpdateById() {
        noticeMapperMock.returns(1).updateById(1L);
        assertEquals(1, noticeService.updateById(1L));
    }
}