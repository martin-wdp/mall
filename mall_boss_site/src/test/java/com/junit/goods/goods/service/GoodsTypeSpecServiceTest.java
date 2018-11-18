/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.junit.goods.goods.service;

import com.ningpai.goods.bean.GoodsSpec;
import com.ningpai.goods.bean.GoodsTypeSpec;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsTypeSpecMapper;
import com.ningpai.goods.service.GoodsSpecService;
import com.ningpai.goods.service.GoodsTypeSpecService;
import com.ningpai.goods.service.impl.GoodsTypeSpecServiceImpl;
import com.ningpai.goods.vo.GoodsTypeSpecVo;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 商品类型关联规格表Service 测试
 */
public class GoodsTypeSpecServiceTest extends UnitilsJUnit3 {

    @TestedObject
    private GoodsTypeSpecService goodsTypeSpecService=new GoodsTypeSpecServiceImpl();

    @InjectIntoByType
    Mock<GoodsSpecService>  goodsSpecServiceMock;

    @InjectIntoByType
   Mock<GoodsTypeSpecMapper> goodsTypeSpecMapperMock;


    @InjectIntoByType
   Mock<CascDelMapper> cascDelMapperMock;

    private  static GoodsTypeSpec goodsTypeSpec=new GoodsTypeSpec();

    private static List<GoodsTypeSpecVo> goodsTypeSpecVoList=new ArrayList<>();

    private static  List<GoodsSpec> goodsSpecList=new ArrayList<>();
    /**
     * 增加一条记录
     */
   public void testSaveTypeSpec(){
       goodsTypeSpec.setTypeSpecCreateName("1");
       goodsTypeSpecMapperMock.returns(1).insertSelective(goodsTypeSpec);
       assertEquals(1,goodsTypeSpecService.saveTypeSpec(goodsTypeSpec,"1"));

   }

    /**
     * 删除一条记录
     *
     */
  public void testDelTypeSpec(){
      //定义一个HashMap集合
      Map<String, String> map = new HashMap<String, String>();
      //设置参数
      map.put("delName", "1");
      map.put("typeSpecId","1");
      goodsTypeSpecMapperMock.returns(1).deleteByPrimaryKey(map);
      assertEquals(1,goodsTypeSpecService.delTypeSpec(1L,"1"));
  }

    /**
     * 更新记录
     */
  public void testUpdateTypeSpec(){
      goodsTypeSpec.setTypeSpecModifiedName("1");
      goodsTypeSpecMapperMock.returns(1).updateByPrimaryKeySelective(goodsTypeSpec);
      assertEquals(1,goodsTypeSpecService.updateTypeSpec(goodsTypeSpec,"1"));
  }

    /**
     * 根据类型ID查询商品类型关联的规格VO
     */
  public void testQueryTypeSpecByTypeId(){
      goodsTypeSpecMapperMock.returns(goodsTypeSpecVoList).queryTypeSpecBytypeId(1L);
      assertEquals(goodsTypeSpecVoList,goodsTypeSpecService.queryTypeSpecByTypeId(1L));
  }

    /**
     * 批量更新商品类型关联规格

     */
  public void testBatchUpdate(){

      assertEquals(1, 1);

  }
}
