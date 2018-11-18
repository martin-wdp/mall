/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.gift.gift.service;


import com.alibaba.fastjson.JSON;
import com.ningpai.gift.bean.Gift;
import com.ningpai.gift.bean.GiftPic;
import com.ningpai.gift.dao.GiftMapper;
import com.ningpai.gift.dao.GiftPicMapper;
import com.ningpai.gift.service.GiftPicService;
import com.ningpai.gift.service.GiftService;
import com.ningpai.gift.service.impl.GiftPicServiceImpl;
import com.ningpai.gift.service.impl.GiftServiceImpl;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;
import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.*;

/**
 * 赠品接口测试类
 *
 */
public class GiftServiceTest extends UnitilsJUnit3 {

    /**
     * HttpServletResponse获取方式
     *  MockHttpServletResponse response = new MockHttpServletResponse();
     *  HttpServletRequest获取方式
     *  MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
     */
    /**
     * 准备GiftCateServiceImpl返回的模拟数据
     */
    @FileContent("giftList.js")
    private String giftListJs;
    @FileContent("giftPicList.js")
    private String giftPicListJs;
    @FileContent("gift.js")
    private String giftJs;

    @TestedObject
    private GiftService giftService = new GiftServiceImpl();
    private GiftPicService giftPicService = new GiftPicServiceImpl();


    @InjectIntoByType
    Mock<GiftMapper> giftMapperMock;
    @InjectIntoByType
    Mock<GiftPicMapper> giftPicMapperMock;



    //各个测试用例共享的测试数据
    Gift gift;
    List<Gift> list;
    List<GiftPic> list2;
    /**
     * 初始化数据
     */
    @Override
    public void setUp(){
        list = JSON.parseArray(giftListJs, Gift.class);
        list2 = JSON.parseArray(giftPicListJs,GiftPic.class);
        gift = JSON.parseObject(giftJs,Gift.class);
    }
    /**
     * 查询赠品列表
     */
    @Test
    public void testSearchGiftList(){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("flag","0");
        giftMapperMock.returns(1).searchGiftListCount(paramMap);
        paramMap.put("start","1");
        paramMap.put("number","1");
        giftMapperMock.returns(list).searchGiftList(paramMap);
        assertNotNull(giftService.searchGiftList(list.get(0),new PageBean()));
    }

    /**
     * 确定添加赠品
     */
    @Test
    public void testDoAddGift(){
        giftMapperMock.returns(1).doAddGift(list.get(0));
        assertEquals(1,giftService.doAddGift(list.get(0)));
    }

    /**
     * 查询赠品详细信息
     */
    @Test
    public void testSelectGiftDetailById(){
        giftMapperMock.returns(list.get(0)).selectGiftDetailById(111L);
        giftPicMapperMock.returns(list2).selectGiftPicByGiftId(111L);
        assertNotNull(giftService.selectGiftDetailById(111L));
    }

    /**
     * 修改赠品
     */
    @Test
    public void testDoUpdateGift(){
        giftMapperMock.returns(1).doUpdateGift(gift);
        assertEquals(1, giftService.doUpdateGift(gift));
    }

    /**
     * 删除赠品
     */
    @Test
    public void testDelGift(){
        giftMapperMock.returns(1).delGift(111L);
        giftPicMapperMock.returns(1).deleteGiftPicByGiftId(111L);
        assertEquals(1, giftService.delGift(111L));
    }
    /**
     * 批量删除赠品
     */
    @Test
    public void testDelAllGift(){
        List<Long> lst = new ArrayList<Long>();
        lst.add(111L);
        lst.add(222L);
        giftMapperMock.returns(1).delAllGift(lst);
        giftPicMapperMock.returns(1).deleteAllGiftPicByGiftId(lst);
        assertEquals(1, giftService.delAllGift(new Long[]{111L,222L}));
    }
}
