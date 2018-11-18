/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.junit.goods.goods.service;

import com.ningpai.goods.bean.GoodsTypeParam;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsTypeParamMapper;
import com.ningpai.goods.dao.GoodsTypeSpecMapper;
import com.ningpai.goods.service.GoodsTypeParamService;
import com.ningpai.goods.service.impl.GoodsTypeParamServiceImpl;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品详细参数Service 单元测试
 */
public class GoodsTypeParamServiceTest extends UnitilsJUnit3{

    @TestedObject
    private GoodsTypeParamService goodsTypeParamService=new GoodsTypeParamServiceImpl();

    @InjectIntoByType
    private Mock<GoodsTypeParamMapper> goodsTypeParamMapperMock;

    @InjectIntoByType
    private Mock<CascDelMapper> cascDelMapperMock;

    private static GoodsTypeParam goodsTypeParam=new GoodsTypeParam();

    private static List<GoodsTypeParam> goodsTypeParamList=new ArrayList<>();

    /**
     * 添加商品详细参数
     */
  public void testSaveTypeParam(){
      goodsTypeParam.setParamCreateName("1");
      goodsTypeParamMapperMock.returns(1).insertSelective(goodsTypeParam);
      assertEquals(1,goodsTypeParamService.saveTypeParam(goodsTypeParam,"1"));
  }

    /**
     * 根据主键ID删除详细参数
     */
  public void testDelTypeParam(){
      //定义一个HashMap集合
      Map<String, String> map = new HashMap<String, String>();
      // 封装参数
      map.put("delName", "1");
      map.put("paramId", "1");
      goodsTypeParamMapperMock.returns(1).deleteByPrimaryKey(map);
      assertEquals(1,goodsTypeParamService.delTypeParam(1L,"1"));

  }

    /**
     * 更新商品详细参数
     */
   public void testUpdate(){
       goodsTypeParam.setParamCreateName("1");
       goodsTypeParamMapperMock.returns(1).updateByPrimaryKeySelective(goodsTypeParam);
       assertEquals(1,goodsTypeParamService.update(goodsTypeParam,"1"));
   }

    /**
     * 根据主键ID查询商品详细参数
     */
   public void testQueryByPrimaryKey(){
       goodsTypeParam.setParamId(1L);
       goodsTypeParamMapperMock.returns(goodsTypeParam).selectByPrimaryKey(1L);
       assertEquals(goodsTypeParam,goodsTypeParamService.queryByPrimaryKey(1L));
   }

    /**
     * 根据类型ID查询详细参数的列表
     */
   public void testQueryParamListByTypeId(){
       goodsTypeParamMapperMock.returns(goodsTypeParamList).queryTypeParamByTypeId(1L);
       assertEquals(goodsTypeParamList,goodsTypeParamService.queryParamListByTypeId(1L));
   }

    /**
     * 批量进行操作
     */
  public void testBatchUpateParam(){
      goodsTypeParam.setParamCreateName("1");
      goodsTypeParamMapperMock.returns(1).updateByPrimaryKeySelective(goodsTypeParam);
      assertEquals(0,goodsTypeParamService.batchUpateParam(1L,"1",new String []{"1"},new String[]{"0"},new String[]{"1"},new String[]{"1"}));
  }
}
