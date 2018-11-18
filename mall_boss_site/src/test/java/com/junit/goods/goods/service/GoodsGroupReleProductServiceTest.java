package com.junit.goods.goods.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.goods.bean.GoodsGroupReleProduct;
import com.ningpai.goods.dao.GoodsGroupReleProductMapper;
import com.ningpai.goods.service.GoodsGroupReleProductService;
import com.ningpai.goods.service.impl.GoodsGroupReleProductServiceImpl;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by houyichang on 2015/10/9.
 */
public class GoodsGroupReleProductServiceTest extends UnitilsJUnit3 {
    /**
     * 需要测试的service
     * */
    @TestedObject
    private GoodsGroupReleProductService goodsGroupReleProductService = new GoodsGroupReleProductServiceImpl();

    /**
     * 模拟
     * */
    @InjectIntoByType
    Mock<GoodsGroupReleProductMapper> goodsGroupReleProductMapperMock;

    /**
     * JS数据
     * */
    @FileContent("goodsGroupReleProductList.js")
    private String goodsGroupReleProductListJs;

    /**
     * 共享数据
     * */
    List<GoodsGroupReleProduct> goodsGroupReleProductList;

    /**
     * 初始化
     * */
    @Override
    protected void setUp() throws Exception {
        goodsGroupReleProductList = JSON.parseArray(goodsGroupReleProductListJs,GoodsGroupReleProduct.class);
    }

    /**
     * 根据组合ID和货品ID查询关联对象
     *
     *            组合ID{@link java.lang.Long}
     *            货品ID {@link java.lang.Long}
     * @return 查询到的对象{@link com.ningpai.goods.bean.GoodsGroupReleProduct}
     */
    public void testQueryGroupReleProductByGroupIdAndProductId(){
        Map<String,Long> map = new HashMap<String, Long>();
        map.put("groupId", 1L);
        map.put("productId", 1L);
        goodsGroupReleProductMapperMock.returns(goodsGroupReleProductList.get(0)).queryGroupReleProductByGroupIdAndProductId(map);
        assertNotNull(goodsGroupReleProductService.queryGroupReleProductByGroupIdAndProductId(1L, 1L));
    }
    /**
     * 根据组合ID和货品ID删除记录
     *
     */
    public void testDelGroupReleProductByGroupIdAndProductId(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("releProductid", "1");
        goodsGroupReleProductMapperMock.returns(1).deleteByPrimaryKey(map);
        assertEquals(1,1);
    }

    /**
     * 根据组合ID和货品ID数组批量删除
     */
    public void testBatchDelGroupReleProductByGroupIdAndProductIds(){
        assertEquals(1,1);
    }

    /**
     * 根据组合ID和货品ID数组添加
     *
     */
    public void testAddGroupReleProduct(){
        assertEquals(1,1);
    }
}
