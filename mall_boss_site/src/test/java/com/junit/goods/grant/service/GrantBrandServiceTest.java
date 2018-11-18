/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.goods.grant.service;

import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.grant.dao.GrantBrandMapper;
import com.ningpai.grant.service.GrantBrandService;
import com.ningpai.grant.service.impl.GrantBrandServiceImpl;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 品牌授权管理Service
 */
public class GrantBrandServiceTest extends UnitilsJUnit3 {

    @TestedObject
    private GrantBrandService grantBrandService = new GrantBrandServiceImpl();

    @InjectIntoByType
    Mock<GrantBrandMapper> grantBrandMapperMock;


    /**
     * 分页查询
     */
    public void testQueryAllGoodsGrandBrand(){
        GoodsBrand goodsBrand = new GoodsBrand();
        List<GoodsBrand> goodsBeanList  = new ArrayList<GoodsBrand>();
        goodsBeanList.add(goodsBrand);
        Map<String, Object> map = MapUtil.getParamsMap(goodsBrand);
        map.put("startRowNum", 0);
        map.put("endRowNum",15);
        map.put("rateStatus", '0');
        grantBrandMapperMock.returns(1).searchGrandBrandCount(map);
        grantBrandMapperMock.returns(goodsBeanList).queryAllThirdGrandBrand(map);
        assertEquals(1, grantBrandService.queryAllGoodsGrandBrand(new PageBean(), goodsBrand).getList().size());
    }

    /**
    *
    *  要修改的品牌id数组
    */
    public void testUpdateGrantBrands(){
        grantBrandService.updateGrantBrands(new Long[]{1L},"qianmi","1");
    }
}
