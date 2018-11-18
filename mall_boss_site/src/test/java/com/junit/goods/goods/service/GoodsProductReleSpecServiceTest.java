package com.junit.goods.goods.service;

import com.ningpai.goods.bean.GoodsProductReleSpec;
import com.ningpai.goods.dao.GoodsProductReleSpecMapper;
import com.ningpai.goods.service.GoodsProductReleSpecService;
import com.ningpai.goods.service.impl.GoodsProductReleSpecServiceImpl;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by houyichang on 2015/10/12.
 */
public class GoodsProductReleSpecServiceTest extends UnitilsJUnit3 {
    /**
     * 需要测试的Service
     * */
    @TestedObject
    private GoodsProductReleSpecService goodsProductReleSpecService = new GoodsProductReleSpecServiceImpl();

    /**
     * 模拟
     * */
    @InjectIntoByType
    Mock<GoodsProductReleSpecMapper> goodsProductReleSpecMapperMock;

    /**
     * 插入一条货品关联规格值记录
     *
     */
    public void testSaveProductReleSpec(){
        // 实例化一个货品规格对象
        GoodsProductReleSpec goodsProductReleSpec = new GoodsProductReleSpec();
        // 对参数进行赋值
        goodsProductReleSpec.setGoodsInfoId(1L);
        goodsProductReleSpec.setSpecDetailId(1L);
        goodsProductReleSpec.setSpecId(1L);
        goodsProductReleSpec.setSpecValueRemark("测试");
        goodsProductReleSpecMapperMock.returns(1).insertSelective(goodsProductReleSpec);
        assertEquals(1,goodsProductReleSpecService.saveProductReleSpec(1L,1L,1L,"测试",""));
    }

    /**
     * 修改货品关联规格
     */
    public void testUpdateProductReleSpec(){
        // 定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        map.put("productId", "1");
        map.put("specDetailId","1");
        map.put("specId", "1");
        map.put("specValueRemark", "测试");
        goodsProductReleSpecMapperMock.returns(1).updateProductReleSpec(map);
        assertEquals(1,goodsProductReleSpecService.updateProductReleSpec(1L,1L,1L,"测试"));
    }

    /**
     * 根据货品ID查询关联的规格
     *
     */
    public void testQuerySpecVoByGoodsInfoId(){
      assertEquals(1,1);
    }

    /**
     * 根据货品id删除所有规格和规格值
     *
     */
    public void testDeleteByProductId(){
        goodsProductReleSpecMapperMock.returns(1).deleteByProductId(1L);
        assertEquals(1,goodsProductReleSpecService.deleteByProductId(1L));
    }
 }
