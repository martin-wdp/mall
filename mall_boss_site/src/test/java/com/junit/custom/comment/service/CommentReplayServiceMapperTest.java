/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.custom.comment.service;

import java.util.List;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import com.alibaba.fastjson.JSON;
import com.ningpai.comment.bean.Comment;
import com.ningpai.comment.bean.CommentReplay;
import com.ningpai.comment.dao.CommentMapper;
import com.ningpai.comment.dao.CommentReplayMapper;
import com.ningpai.comment.service.CommentReplayServiceMapper;
import com.ningpai.comment.service.impl.CommentReplayServiceMapperImpl;
import com.ningpai.customer.bean.Customer;
import com.ningpai.other.util.IPAddress;

/**
 * 添加回复接口实现类--单元测试
 * 
 * @author jiping
 * @since 2015年9月16日 下午7:39:26
 * @version 0.0.1
 */
public class CommentReplayServiceMapperTest extends UnitilsJUnit3 {

    /**
     * 需要测试的接口
     */
    @TestedObject
    private CommentReplayServiceMapper commentReplayServiceMapper = new CommentReplayServiceMapperImpl();

    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<CommentReplayMapper> commentReplayMapperMock;
    @InjectIntoByType
    Mock<CommentMapper> commentMapperMock;

    /**
     * JS数据
     */
    @FileContent("commentList.js")
    private String commentListJS;
    @FileContent("commentReplayList.js")
    private String commentReplayListJS;

    /**
     * 共享数据
     */
    List<CommentReplay> commentReplayList;
    List<Comment> commentList;

    /**
     * 初始化
     */
    public void setUp() {
        commentReplayList = JSON.parseArray(commentReplayListJS,
                CommentReplay.class);
        commentList = JSON.parseArray(commentListJS, Comment.class);
    }

    /**
     * 测试添加回复
     */
    @Test
    public void testInsertSelective() {
        commentMapperMock.returns(commentList.get(0)).selectByCommentId(1L);
        commentMapperMock.returns(1).updateComment(commentList.get(0));
        commentReplayMapperMock.returns(1).insertSelective(
                commentReplayList.get(0));
        assertEquals(1,
                commentReplayServiceMapper.insertSelective(commentReplayList
                        .get(0)));
    }

    /**
     * 测试根据评论编号获取所有回复
     */
    @Test
    public void testSelectByCommentId() {
        commentReplayMapperMock.returns(commentReplayList)
                .selectByCommentId(1L);
        assertNotNull(commentReplayServiceMapper.selectByCommentId(1L));
    }

    /**
     * 测试修改回复
     */
    @Test
    public void testUpdateCommentRep() {
        commentReplayMapperMock.returns(1).updateCommentRep(
                commentReplayList.get(0));
        assertEquals(1,
                commentReplayServiceMapper.updateCommentRep(commentReplayList
                        .get(0)));
    }

    /**
     * 测试添加回复 -- 前台回复
     */
    @Test
    public void testAddCommentRepaly() {
        // 模拟request数据
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
        Customer cust = new Customer();
        cust.setCustomerNickname("小明");
        request.getSession().setAttribute("cust", cust);
        request.getSession().setAttribute("customerId", 1);
        CommentReplay cr = new CommentReplay();
        // 设置IP
        cr.setReplayIp(IPAddress.getIpAddr(request));
        cr.setCommentId(1L);
        cr.setCommentContent("测试内容");
        // 回复后立即显示
        cr.setIsDisplay("1");
        cr.setCustomerNickname("小明");
        cr.setCustomerId(1);
        commentReplayMapperMock.returns(1).insertSelective(cr);
        assertEquals(1, commentReplayServiceMapper.addCommentRepaly(request,
                1L, "测试内容"));
    }
}
