/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.custom.comment.service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import com.alibaba.fastjson.JSON;
import com.ningpai.comment.bean.Comment;
import com.ningpai.comment.dao.CommentMapper;
import com.ningpai.comment.service.impl.CommentServiceMapperImpl;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.util.PageBean;

/**
 * 评论服务类实现类--单元测试
 * @author jiping 
 * @since 2015年9月17日 下午7:22:06 
 * @version 0.0.1
 */
public class CommentServiceMapperTest extends UnitilsJUnit3{
    /**
     * 需要测试的接口
     */
    @TestedObject
    private CommentServiceMapperImpl commentServiceMapper = new CommentServiceMapperImpl();
    
    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<CommentMapper> commentMapperMock;
    @InjectIntoByType
    Mock<CustomerServiceMapper> customerServiceMapperMock;
    
    /**
     * js数据
     */
    @FileContent("commentList.js")
    private String commentListJS;
    
    /**
     * 共享数据
     */
    List<Comment> commentList;
    Customer customer;
    
    /**
     * 初始化
     */
    public void setUp(){
        commentList = JSON.parseArray(commentListJS, Comment.class);
//        customer = JSON.parseObject(customerJS,Customer.class);
    }
    
    /**
     * 测试查询所有评论 分页
     */
    @Test
    public void testSelectAllComment(){
        Map<String, Integer> paramMap= new HashMap<String, Integer>();
        Comment comment = new Comment();
        commentMapperMock.returns(1L).selectAllCommentCount();
        commentMapperMock.returns(1L).selectCommentCount(comment);
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 15);
        comment.setStartRowNum(0);
        comment.setEndRowNum(15);
        commentMapperMock.returns(commentList).selectCommentByLimit(paramMap);
        commentMapperMock.returns(commentList).selectCommentByComment(comment);
        assertEquals(1,commentServiceMapper.selectAllComment(new PageBean(), new Comment()).getList().size());
    }
    
    /**
     * 测试删除评论
     */
    @Test
    public void testDeleteComment(){
       commentMapperMock.returns(1).deleteCommentById(1L);
       assertEquals(1, commentServiceMapper.deleteComment(new String[]{"1"}));
    }
    
    /**
     * 测试根据评论编号 查询评论
     */
    @Test
    public void testSelectByCommentId(){
        commentMapperMock.returns(commentList.get(0)).selectByCommentId(1L);
        assertNotNull(commentServiceMapper.selectByCommentId(1L));
    }
    
    /**
     * 测试查询有咨询 分页
     */
    @Test
    public void testSelectAllConsult(){
        Map<String, Integer> paramMap= new HashMap<String, Integer>();
        Comment comment = new Comment();
        commentMapperMock.returns(1L).selectAllConsultCount();
        commentMapperMock.returns(1L).selectConsultCount(comment);
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 15);
        comment.setStartRowNum(0);
        comment.setEndRowNum(15);
        commentMapperMock.returns(commentList).selectAllConsult(paramMap);
        commentMapperMock.returns(commentList).selectConsultByConsult(comment);
        assertEquals(1,commentServiceMapper.selectAllConsult(new PageBean(), new Comment()).getList().size());
    }
    
    /**
     * 测试根据商品编号查询所有评论
     */
    @Test
    public void testSelectCommByGoodsId(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("goodsId", 1L);
        paramMap.put("commentType", new Character('1'));
        paramMap.put("paramString", "test");
        paramMap.put("item", "test1");
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 15);
        commentMapperMock.returns(1).selectGoodAllCommCount(paramMap);
        commentMapperMock.returns(commentList).selectAllCommentByGoodsId(paramMap);
        assertNotNull(commentServiceMapper.selectCommByGoodsId(new PageBean(), 1L,new Character('1'), "test", "test1"));
    }
    
    /**
     * 测试根据商品编号查询所有评论
     */
    @Test
    public void testSelectCommByGoodsIdTwo(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("goodsId", 1L);
        paramMap.put("commentType", new Character('1'));
        paramMap.put("paramString", "test");
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 15);
        commentMapperMock.returns(1).selectGoodAllCommCount(paramMap);
        commentMapperMock.returns(commentList).selectAllCommentByGoodsId(paramMap);
        assertNotNull(commentServiceMapper.selectCommByGoodsId(new PageBean(), 1L,new Character('1'), "test"));
    }
    
    /**
     * 测试修改评论
     */
    @Test
    public void testUpdateComment(){
        commentMapperMock.returns(1).updateComment(commentList.get(0));
        assertEquals(1, commentServiceMapper.updateComment(commentList.get(0)));
    }
    
    /**
     * 测试添加商品评论
     */
    public void testAddGoodsComment(){
        commentMapperMock.returns(1).addGoodsComment(commentList.get(0));
        assertEquals(1, commentServiceMapper.addGoodsComment(commentList.get(0)));
    }
    
    /**
     * 测试查询会员咨询
     */
    @Test
    public void testQueryCustConsult(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Comment comment = new Comment();
        comment.setCustomerId(1L);
        comment.seteFlag("0");
        commentMapperMock.returns(1L).queryCustConsultCount(comment);
        paramMap.put("comment", comment);
        paramMap.put(CustomerConstantStr.STARTNUM, 0);
        paramMap.put(CustomerConstantStr.ENDNUM, 15);
        commentMapperMock.returns(commentList).queryCustConsult(paramMap);
        assertNotNull(commentServiceMapper.queryCustConsult(1L, "0", new PageBean()));
    }
    
    /**
     * 测试查询会员评论
     */
    @Test
    public void testQueryCustComment(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Comment comment = new Comment();
        comment.setCustomerId(1L);
        commentMapperMock.returns(1L).queryCustCommentCount(comment);
        paramMap.put("comment", comment);
        paramMap.put(CustomerConstantStr.STARTNUM, 0);
        paramMap.put(CustomerConstantStr.ENDNUM, 15);
        commentMapperMock.returns(commentList).queryCustConsult(paramMap);
        assertNotNull(commentServiceMapper.queryCustComment(1L, new PageBean()));
    }
    
    /**
     * 测试查询会员评论 前台我的评论使用 确认收货过的订单商品都可以评价
     */
    @Test
    public void testqueryCustComment(){
        // 条件封装容器
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orderId", 1L);
        paramMap.put("customerId", 1L);
        commentMapperMock.returns(1L).queryCommentByCust(paramMap);
        paramMap.put(CustomerConstantStr.STARTNUM, 0);
        paramMap.put(CustomerConstantStr.ENDNUM, 15);
        commentMapperMock.returns(commentList).queryCommentByCust(paramMap);
        assertNotNull(commentServiceMapper.queryCommentByCust(1L, 1L, new PageBean()));
    }
    
    /**
     * 测试导出所有评论
     */
    @Test
    public void testExportComment(){
        // 模拟response数据
        MockHttpServletResponse response= new MockHttpServletResponse();
        commentMapperMock.returns(commentList).queryAllComment();
        // 设置下载时客户端Excel的名称
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("商品评论备份.xls"));
       commentServiceMapper.exportComment(response);
    }
    
    /**
     * 测试导出评论模板
     */
    @Test
    public void testExportCommentTemp(){
        // 模拟response数据
        MockHttpServletResponse response= new MockHttpServletResponse();
        // 设置下载时客户端Excel的名称
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("商品评论模板.xls"));
        commentServiceMapper.exportCommentTemp(response);
    }
    
    /**
     * 测试导入评论
     */
//    @Test
//    public void testImportCommentByExcel(){
//        // 模拟request数据
//        MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
//        // 模拟response数据
//        MockHttpServletResponse response= new MockHttpServletResponse();
//     // 模拟response数据
//        MockMultipartHttpServletRequest multiRequest= new MockMultipartHttpServletRequest();
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("commentList", commentList);
//        commentMapperMock.returns(1).addGoodsComment(commentList.get(0));
//        MultipartFile file = multiRequest.getFile("importExcel");
//    }
    
    /**
     * 测试导出comment
     */
    @Test
    public void testExecImportComment(){
        // 模拟request数据
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("commentList", commentList);
        commentMapperMock.returns(1).addGoodsComment(commentList.get(0));
        assertEquals("200", commentServiceMapper.execImportComment(map, request));
    }
    
    /**
     * 测试导入商品品牌
     */
//    @Test
//    public void testImportComment(){
//        // 模拟request数据
//        MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
//        commentMapperMock.returns(1L).selectGoodsInfoIdByNo("0001");
//        customerServiceMapperMock.returns(customer).selectCustomerByUserName("xiaoming");
////        assertNotNull(commentServiceMapper.importComment(request, is));
//    }
    
    /**
     * 测试查询第三方店铺的评级
     */
    @Test
    public void testSelectSellerComment(){
        commentMapperMock.returns(commentList.get(0)).selectSellerAvg(1L);
        assertNotNull(commentServiceMapper.selectSellerComment(1L));
    }
    
    /**
     * 测试根据订单商品编号和会员编号查询商品评论晒单信息
     */
    @Test
    public void testQueryCommentByOrderGoodsId(){
        // 创建封装条件容器
        Map<String, Object> map = new HashMap<String, Object>();
        // 填充订单商品编号
        map.put("orderGoodsId", 1);
        // 会员编号
        map.put("customerId", 1);
        commentMapperMock.returns(commentList.get(0)).queryCommentByOrderGoodsId(map);
        assertNotNull(commentServiceMapper.queryCommentByOrderGoodsId(1L, 1L));
    }
}
