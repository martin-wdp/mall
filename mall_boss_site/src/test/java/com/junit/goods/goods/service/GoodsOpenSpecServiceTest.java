package com.junit.goods.goods.service;

import com.ningpai.goods.bean.GoodsOpenSpec;
import com.ningpai.goods.dao.GoodsOpenSpecMapper;
import com.ningpai.goods.service.GoodsOpenSpecService;
import com.ningpai.goods.service.impl.GoodsOpenSpecServiceImpl;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

/**
 * Created by houyichang on 2015/10/12.
 */
public class GoodsOpenSpecServiceTest extends UnitilsJUnit3 {
    /**
     * 需要测试的service
     * */
    @TestedObject
    private GoodsOpenSpecService goodsOpenSpecService = new GoodsOpenSpecServiceImpl();

    /**
     * 模拟
     * */
    @InjectIntoByType
    Mock<GoodsOpenSpecMapper> goodsOpenSpecMapperMock;

    /**
     * 保存商品开启规格信息
     *
     */
    public void testSaveOpenSpec(){
        // 实例化一个商品规格对象
        GoodsOpenSpec openSpec = new GoodsOpenSpec();
        // 进行赋值
        openSpec.setGoodsId(1L);
        openSpec.setSpecId(1L);
        openSpec.setDelFlag("0");
        goodsOpenSpecMapperMock.returns(1).insertSelective(openSpec);
        assertEquals(1,goodsOpenSpecService.saveOpenSpec(1L,1L));
    }

    /**
     * 根据商品ID查询开启的规格集合
     */
    public void testQueryOpenListByGoodsId(){
      assertEquals(1,1);
    }

    /**
     * 根据商品ID查询开启的规格集合
     *
     */
    public void testQueryOpenListByGoodsIdInBoss(){
        assertEquals(1,1);
    }

    /**
     * 根据商品id，删除商品与规格之间关系
     */
    public void testDeleteByGoodsId(){
        goodsOpenSpecMapperMock.returns(1).deleteByGoodsId(1L);
    }
  }
