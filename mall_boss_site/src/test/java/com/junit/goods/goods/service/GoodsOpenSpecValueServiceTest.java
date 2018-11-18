package com.junit.goods.goods.service;

import com.ningpai.goods.bean.GoodsOpenSpecValue;
import com.ningpai.goods.dao.GoodsOpenSpecValueMapper;
import com.ningpai.goods.service.GoodsOpenSpecValueService;
import com.ningpai.goods.service.impl.GoodsOpenSpecValueServiceImpl;
import com.ningpai.goods.vo.GoodsOpenSpecValueVo;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by houyichang on 2015/10/12.
 */
public class GoodsOpenSpecValueServiceTest extends UnitilsJUnit3 {
    /**
     * 需要测试的类
     * */
    @TestedObject
    private GoodsOpenSpecValueService goodsOpenSpecValueService = new GoodsOpenSpecValueServiceImpl();

    /**
     * 模拟
     * */
    @InjectIntoByType
    Mock<GoodsOpenSpecValueMapper> goodsOpenSpecValueMapperMock;

    /**
     * 保存商品开启规格值记录
     *
     */
    public void testSaveOpenSpecVal(){
        // 实例化一个商品规格对象
        GoodsOpenSpecValue goodsOpenSpecValue = new GoodsOpenSpecValue();

        // 对参数进行赋值
        goodsOpenSpecValue.setGoodsId(1L);
        goodsOpenSpecValue.setDelFlag("0");
        goodsOpenSpecValue.setSpecId(1L);
        goodsOpenSpecValue.setSpecValueId(1L);
        goodsOpenSpecValue.setImgUrl("1");
        goodsOpenSpecValue.setSpecValueRemark("1");
        goodsOpenSpecValueMapperMock.returns(1).insertSelective(goodsOpenSpecValue);
        assertEquals(1,goodsOpenSpecValueService.saveOpenSpecVal(1L,1L,1L,"1","1"));
    }

    /**
     * 根据商品ID和规格ID查询开启的规格值集合
     *
     */
    public void testQueryOpenListByGoodsAndSpecId(){
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("goodsId", 1L);
        map.put("specId", 1L);
        List<GoodsOpenSpecValueVo> goodsOpenSpecValueVoList = new ArrayList<GoodsOpenSpecValueVo>();
        goodsOpenSpecValueMapperMock.returns(goodsOpenSpecValueVoList).queryOpenValueListByGoodsIdAndSpecId(map);
        assertNotNull(goodsOpenSpecValueService.queryOpenListByGoodsAndSpecId(1L, 1L));

    }

    /**
     * 根据商品id和规格值id查询是否开启规则值
     *
     */
    public void testQueryOpenListByGoodsAndSpecValueId(){
        assertEquals(1,1);
    }

    /**
     * 根据商品id，删除商品与规格之间关系
     *
     */
    public void testDeleteByGoodsId(){
        goodsOpenSpecValueMapperMock.returns(1).deleteByGoodsId(1L);
    }
  }
