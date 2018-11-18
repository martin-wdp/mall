/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.custom.comment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import com.alibaba.fastjson.JSON;
import com.ningpai.comment.bean.Share;
import com.ningpai.comment.bean.ShareImg;
import com.ningpai.comment.bean.ShareReply;
import com.ningpai.comment.dao.ShareMapper;
import com.ningpai.comment.service.ShareService;
import com.ningpai.comment.service.impl.ShareServiceImpl;
import com.ningpai.comment.vo.ShareReplyVo;
import com.ningpai.comment.vo.ShareVo;
import com.ningpai.customer.service.CustomerPointServiceMapper;
import com.ningpai.util.PageBean;

/**
 * 晒单Service--单元测试
 * @author jiping 
 * @since 2015年9月19日 下午1:47:15 
 * @version 0.0.1
 */
public class ShareServicetTest  extends UnitilsJUnit3{
    /**
     * 需要测试的接口
     */
    @TestedObject
    private ShareService shareService = new ShareServiceImpl();
   
    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<ShareMapper> shareMapperMock;
    @InjectIntoByType
    Mock<CustomerPointServiceMapper> customerPointServiceMapperMock;
    
    /**
     * JS数据
     */
    @FileContent("shareList.js")
    private String shareListJS;
    @FileContent("shareImg.js")
    private String shareImgJS;
    @FileContent("shareReply.js")
    private String shareReplyJS;
    @FileContent("shareVo.js")
    private String shareVoJS;
    @FileContent("shareReplyVoList.js")
    private String shareReplyVoListJS;
    /**
     * 共享数据
     */
    List<Share> shareList;
    ShareImg shareImg;
    ShareReply shareReplay;
    ShareVo shareVo;
    List<ShareReplyVo> shareReplayVoList;
    /**
     * 初始化
     */
    public void setUp(){
        shareList=JSON.parseArray(shareListJS,Share.class);
        shareImg=JSON.parseObject(shareImgJS,ShareImg.class);
        shareReplay= JSON.parseObject(shareReplyJS,ShareReply.class);
        shareVo= JSON.parseObject(shareVoJS, ShareVo.class);
        shareReplayVoList = JSON.parseArray(shareReplyVoListJS,ShareReplyVo.class);
    }
    
    /**
     * 测试查询前size个热门晒单
     */
    @Test
    public void testGetTopShare(){
        shareMapperMock.returns(shareList).queryTopShare(1);
        assertNotNull(shareService.getTopShare(1));
    }
    
    /**
     * 测试保存晒单
     */
    @Test
    public void testSaveShare(){
        // 更新订单“晒单标记”
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("shareId", 1);
        paramMap.put("orderGoodsId", 1);
        paramMap.put("orderId", 1);
        shareMapperMock.returns(1).updateShareIdByOrderGoodsId(paramMap);
        customerPointServiceMapperMock.returns(1).addIntegralByType(1L, "11");
        shareService.saveShare(shareList.get(0), 1L, 1L, "http://wl-helijia.b0.upaiyun.com/1441969669931.jpg",1L);
    }
    
    /**
     * 测试保存晒单
     */
    @Test
    public void testSaveShareTwo(){
        // 更新订单“晒单标记”
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("shareId", 1);
        paramMap.put("orderGoodsId", 1);
        shareMapperMock.returns(1).updateShareIdByOrderGoodsId(paramMap);
        customerPointServiceMapperMock.returns(1).addIntegralByType(1L, "11");
        shareService.saveShare(1L, shareList.get(0), 1L, 1L, "http://wl-helijia.b0.upaiyun.com/1441969669931.jpg");
    }
    
    /**
     * 测试根据晒单Id查询晒单详情
     */
    @Test
    public void testQueryShareById(){
        shareMapperMock.returns(shareList.get(0)).selectShareById(1L);
        assertNotNull(shareService.queryShareById(1L));
    }
    
    /**
     * 测试保存晒单回复
     */
    @Test
    public void testSaveShareReply(){
        shareMapperMock.returns(1).saveShareReply(shareReplay);
        assertEquals(1, shareService.saveShareReply(shareReplay));
    }
    
    /**
     * 测试根据条件查询晒单列表
     */
    @Test
    public void testSelectAllShareByShare(){
        shareMapperMock.returns(1L).selectAllShareCount(new ShareVo());
        // 设置查询对象的起始编号和结束编号
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("share", new ShareVo());
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 15);
        shareMapperMock.returns(shareList).selectAllShare(paramMap);
        assertNotNull(shareService.selectAllShareByShare(new PageBean(), new ShareVo()));
    }
    
    /**
     * 测试查询晒单详情
     */
    @Test
    public void testSelectShareDetail(){
        shareMapperMock.returns(shareVo).selectShareDetail(1L);
        assertNotNull(shareService.selectShareDetail(1L));
    }
    
    /**
     * 测试修改晒单显示
     */
    @Test
    public void testUpdateShare(){
        shareMapperMock.returns(1).updateShare(shareList.get(0));
        assertEquals(1, shareService.updateShare(shareList.get(0)));
    }
    
    /**
     * 测试修改评论是否显示
     */
    @Test
    public void testUpdateShareRep(){
        shareMapperMock.returns(1).updateShareReplay(shareReplay);
        assertEquals(1, shareService.updateShareRep(shareReplay));
    }
    
    /**
     * 测试删除晒单
     */
    @Test
    public void testDeleteShare(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parameterValues",new String[]{"1L"});
        shareMapperMock.returns(1).deleteShareByBids(paramMap);
        assertEquals(1, shareService.deleteShare(new String[]{"1L"}));
    }
    
    /**
     * 测试将单条显示到首页
     */
    @Test
    public void testUpdateShareToIndexOne(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parameterValues",new String[] { shareList.get(0).getShareId().toString() });
        paramMap.put("isDisplay", '2');
        shareMapperMock.returns(1).updateShareToIndex(paramMap);
        assertEquals(1, shareService.updateShareToIndexOne(shareList.get(0)));
    }
    
    /**
     * 测试将晒单推荐到首页
     */
    @Test
    public void testUpdateShareToIndex(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parameterValues",new String[] {"1L"});
        paramMap.put("isDisplay", '2');
        shareMapperMock.returns(1).updateShareToIndex(paramMap);
        assertEquals(1, shareService.updateShareToIndex(new String[]{"1L"}));
    }
    
    /**
     * 测试判断首页展示的晒单条数是否超过限制 8条
     */
    @Test
    public void testCheckIndexShareCount(){
        shareMapperMock.returns(1L).queryIndexShareCount();
        assertTrue(shareService.checkIndexShareCount(1L));
    }
    
    /**
     * 测试根据评论编号获取所有回复内容
     */
    @Test
    public void testQueryShareReplyByShareId(){
        shareMapperMock.returns(shareReplayVoList).queryShareReplyByShareId(1L);
        assertNotNull(shareService.queryShareReplyByShareId(1L));
    }
}
