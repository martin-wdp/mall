/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.gift.gift.service;


import com.alibaba.fastjson.JSON;
import com.ningpai.coupon.bean.ParamIds;
import com.ningpai.gift.dao.GiftOrderMapper;
import com.ningpai.gift.service.GiftOrderService;
import com.ningpai.gift.service.impl.GiftOrderServiceImpl;
import com.ningpai.gift.vo.GiftOrderVo;
import com.ningpai.util.PageBean;
import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 积分订单查询service
 *
 */
public class GiftOrderServiceTest extends UnitilsJUnit3 {

    /**
     * HttpServletResponse获取方式
     *  MockHttpServletResponse response = new MockHttpServletResponse();
     *  HttpServletRequest获取方式
     *  MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
     */
    /**
     * 准备GiftCateServiceImpl返回的模拟数据
     */
    @FileContent("giftOrderVoList.js")
    private String giftOrderVoListJs;


    @TestedObject
    private GiftOrderService giftOrderService = new GiftOrderServiceImpl();

    @InjectIntoByType
    Mock<GiftOrderMapper> giftOrderMapperMock;


    //各个测试用例共享的测试数据
    List<GiftOrderVo> list;
    /**
     * 初始化数据
     */
    @Override
    public void setUp(){
        list = JSON.parseArray(giftOrderVoListJs,GiftOrderVo.class);
    }

    /**
     * 查询积分订单
     */
    @Test
    public void testQueryGiftOrder(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("giftOrderCode", list.get(0).getGiftOrderCode());
        paramMap.put("giftOrderStatus", list.get(0).getGiftOrderStatus());
        paramMap.put("shoppingMobile", list.get(0).getShoppingMobile());
        paramMap.put("shoppingPerson", list.get(0).getShoppingPerson());
        giftOrderMapperMock.returns(1).giftOrderCount(paramMap);
        paramMap.put("startRowNum",0);
        paramMap.put("endRowNum",15);
        giftOrderMapperMock.returns(list).giftOrderList(paramMap);
        assertEquals(2,giftOrderService.queryGiftOrder(list.get(0),new PageBean()).getList().size());
    }

    /**
     * 订单详情
     */
    @Test
    public void testOrderDetail() {
        giftOrderMapperMock.returns(list.get(0)).selectByOrderId(122L);
        assertNotNull(giftOrderService.orderDetail(122L));
    }

    /**
     * 查询订单号是否存在
     */
    @Test
    public void testExistOrderCode(){
        giftOrderMapperMock.returns(1).existOrderCode("123456");
        assertEquals(1,giftOrderService.existOrderCode("123456"));
    }

    /**
     * 修改订单信息
     */
    @Test
    public void testUpdateOrderVice(){
        giftOrderMapperMock.returns(1).updateGiftOrder(list.get(0));
        assertEquals(1,giftOrderService.updateOrderVice(list.get(0)));
    }

    /**
     *根据订单编号查询订单信息
     */
    @Test
    public void testQueryByOrderCode(){
        giftOrderMapperMock.returns(list.get(0)).selectByOrderCode("123456");
        assertNotNull(giftOrderService.queryByOrderCode("123456"));
    }
}
