/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.gift.gift.service;


import com.alibaba.fastjson.JSON;
import com.ningpai.gift.bean.GiftPic;
import com.ningpai.gift.dao.GiftPicMapper;
import com.ningpai.gift.service.GiftPicService;
import com.ningpai.gift.service.impl.GiftPicServiceImpl;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.ArrayList;
import java.util.List;

/**
 * 赠品图片接口测试类
 *
 */
public class GiftPicServiceTest extends UnitilsJUnit3 {

    /**
     * HttpServletResponse获取方式
     *  MockHttpServletResponse response = new MockHttpServletResponse();
     *  HttpServletRequest获取方式
     *  MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
     */
    /**
     * 准备GiftCateServiceImpl返回的模拟数据
     */
    @FileContent("giftPicList.js")
    private String giftPicListJs;


    @TestedObject
    private GiftPicService giftPicService = new GiftPicServiceImpl();

    @InjectIntoByType
    Mock<GiftPicMapper> giftPicMapperMock;


    //各个测试用例共享的测试数据
    List<GiftPic> list;
    /**
     * 初始化数据
     */
    @Override
    public void setUp(){
       list = JSON.parseArray(giftPicListJs, GiftPic.class);
    }
    /**
     * 根据图片ID删除赠品图片
     */
    @Test
    public void testDelGiftPicByPicId(){
        giftPicMapperMock.returns(1).delGiftPicByPicId(10L);
     assertEquals(1,giftPicService.delGiftPicByPicId(10L));
    }

    /**
     * 查询赠品图片列表根据赠品Id
     *
     */
    @Test
    public void testSelectGiftPicByGiftId(){
        giftPicMapperMock.returns(list).selectGiftPicByGiftId(10L);
        assertNotNull(giftPicService.selectGiftPicByGiftId(10L));
    }

    /**
     * 新boss保存赠品图片
     */
    @Test
    public void testNewuploadImage(){
        giftPicMapperMock.returns(1).savePic(list.get(0));
        assertTrue(giftPicService.newuploadImage(10L,new String[]{"123"}));
    }

    /**
     * 插入图片
     */
    @Test
    public void testUploadImage(){
        giftPicMapperMock.returns(10L).savePic(list.get(0));
        assertEquals(null,giftPicService.uploadImage(10L,"1",new ArrayList<MultipartFile>(),new MockHttpServletRequest()));
    }

}
