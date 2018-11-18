package com.junit.goods.goods.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.goods.bean.ProductWare;
import com.ningpai.goods.bean.WareHouse;
import com.ningpai.goods.dao.ProductWareMapper;
import com.ningpai.goods.service.ProductWareService;
import com.ningpai.goods.service.impl.ProductWareServiceImpl;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fengbin on 2015/10/13.
 */
public class ProductWareServiceTest extends UnitilsJUnit3{
    /**
     * 需要测试的service
     * */
    @TestedObject
    private ProductWareService productWareService = new ProductWareServiceImpl();

    /**
     * 模拟
     * */

     @InjectIntoByType
     Mock<ProductWareMapper> productWareMapperMock;
    /**
     * JS数据
     * */
    @FileContent("wareHouseList.js")
    private String wareHouseListJs;
    @FileContent("productWareList.js")
    private String productWareListJs;

    /**
     * 共享数据
     * */
    private List<WareHouse> wareHouseList;
    private List<ProductWare> productWareList;



    /**
     * 初始化
     * */
    @Override
    protected void setUp() throws Exception {
        wareHouseList = JSON.parseArray(wareHouseListJs, WareHouse.class);
        productWareList = JSON.parseArray(productWareListJs,ProductWare.class);
    }

    /**
     * 查询仓库信息
     *
     */
    public void testFindWare(){
        productWareMapperMock.returns(wareHouseList.get(0)).findWare(11L);
        assertNotNull(productWareService.findWare(11L));
    }

    /**
     * 保存关联信息
     *
     */
    public void testCalcProductWare(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("wareId", 1L);
        map.put("productId", 11L);
        productWareMapperMock.returns(1).queryCountByProductIdAndWareId(map);
        productWareMapperMock.returns(1).updateByPrimaryKeySelective(productWareList.get(0));
        productWareMapperMock.returns(1).insertSelective(productWareList.get(0));
        /*assertEquals(0,productWareService.calcProductWare(5L,new Long[]{100L,100L},new BigDecimal[]{BigDecimal.valueOf(99.2),BigDecimal.valueOf(90.2)},new Long[]{1L,1L}));*/
    }

    /**
     * 根据货品ID和地区ID查询关联记录
     *
     */
    public void testQueryProductWareByProductIdAndDistinctId(){
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("productId", 5L);
        map.put("distinctId", 6L);
        productWareMapperMock.returns(productWareList.get(0)).queryProductWareByProductIdAndDistinctId(map);
        assertNotNull(productWareService.queryProductWareByProductIdAndDistinctId(5L,6L));
    }

}
