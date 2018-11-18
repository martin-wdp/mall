/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.gift.gift.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.gift.bean.GiftCate;
import com.ningpai.gift.dao.GiftCateMapper;
import com.ningpai.gift.service.GiftCateService;
import com.ningpai.gift.service.impl.GiftCateServiceImpl;
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
 * 赠品分类测试类
 * @author NINGPAI-LIH
 * @since 2015年3月2日17:00:53
 *
 */
public class GiftCateServiceTest extends UnitilsJUnit3 {

    /**
     * HttpServletResponse获取方式
     *  MockHttpServletResponse response = new MockHttpServletResponse();
     *  HttpServletRequest获取方式
     *  MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
     */
    /**
     * 准备GiftCateServiceImpl返回的模拟数据
     */
    @FileContent("giftCateList.js")
    private String giftCateListJs;
    @FileContent("giftCate.js")
    private String giftCateJs;

    @TestedObject
    private GiftCateService giftCateService = new GiftCateServiceImpl();

    @InjectIntoByType
    Mock<GiftCateMapper> giftCateMapperMock;


    //各个测试用例共享的测试数据
    List<GiftCate> list;
    GiftCate giftCate;
    /**
     * 初始化数据
     */
    @Override
    public void setUp(){
       list = JSON.parseArray(giftCateListJs,GiftCate.class);
       giftCate = JSON.parseObject(giftCateJs,GiftCate.class);
    }
    /**
     * 查询分类
     */
    @Test
    public void testFirstSearchGiftCateList(){
        giftCateMapperMock.returns(list).searchGiftCateList(155L);
     assertEquals(3,giftCateService.searchGiftCateList(155L).size());
    }

    /**
     * 分页查询赠品分类
     *
     */
    @Test
    public void testSearchGiftCateList(){
        Map<String, Object> paramMap = MapUtil.getParamsMap(giftCate);
        giftCateMapperMock.returns(1).searchGiftCateListCount(paramMap);
        giftCateMapperMock.returns(list).searchGiftParentCateList(paramMap);
        giftCateMapperMock.returns(list).searchGiftCateList();
        assertNotNull(giftCateService.searchGiftCateList(giftCate,new PageBean()));
    }

    /**
     * 不带分页查询赠品分类
     */
    @Test
    public void testSearchGiftCateListNoPage(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("giftCateId",155L);
        paramMap.put("start", null);
        paramMap.put("number", null);
        giftCateMapperMock.returns(list).searchGiftParentCateList(paramMap);
        giftCateMapperMock.returns(list).searchGiftCateList();
        assertEquals(0, giftCateService.searchGiftCateListNoPage(giftCate).size());
    }

    /**
     * 查询赠品分类详细信息
     *
     */
    @Test
    public void testSearchGiftCateById(){
        giftCateMapperMock.returns(giftCate).searchGiftCateById(153L);
        assertNotNull(giftCateService.searchGiftCateById(153L));
    }

    /**
     * 添加赠品分类信息
     */
    @Test
    public void testAddGiftCate(){
        giftCateMapperMock.returns(giftCate).searchGiftCateById(153L);
        giftCateMapperMock.returns(1).addGiftCate(giftCate);
        assertEquals(1, giftCateService.addGiftCate(giftCate));
    }

    /**
     * 修改赠品分类
     */
    @Test
    public void testUpdateGiftCate(){
        giftCateMapperMock.returns(giftCate).searchGiftCateById(153L);
        giftCateMapperMock.returns(1).updateGiftCate(giftCate);
        assertEquals(1,giftCateService.updateGiftCate(giftCate));
    }

    /**
     * 删除赠品分类
     *
     */
    @Test
    public void testDelGiftCate(){
        giftCateMapperMock.returns(1).delGiftCate(150L);
        assertNotNull(giftCateService.delGiftCate(150L));
    }
    /**
     *批量删除赠品分类
     */
    @Test
    public void testDelAllGiftCate(){
        List<Long> list = new ArrayList<Long>();
        list.add(155L);
        giftCateMapperMock.returns(1).delAllGiftCate(list);
        assertEquals(1,giftCateService.delAllGiftCate(new Long[]{155L}));
    }

    /**
     * 查询列表 select使用
     */
    @Test
    public void testSelectGiftListUseSelect(){
        giftCateMapperMock.returns(list).selectGiftListUseSelect();
        assertEquals(3,giftCateService.selectGiftListUseSelect().size());
    }

    /**
     * 查询分类
     */
    @Test
    public void testLastSearchGiftCateList(){
        giftCateMapperMock.returns(list).searchGiftCateList();
        assertEquals(3, giftCateService.searchGiftCateList().size());
    }

    /**
     * 验证是否可以删除 如果传递过来的分类 下面有子类 就返回false表示不可以删除
     */
    @Test
    public void testCheckDelGiftCate(){
        giftCateMapperMock.returns(1).querySonCateByParentId(153L);
        assertEquals(true, giftCateService.checkDelGiftCate(155L));
    }
}
