package com.junit.third.goods.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.bean.GoodsTag;
import com.ningpai.third.goods.dao.ThirdOtherMapper;
import com.ningpai.third.goods.service.ThirdOtherService;
import com.ningpai.third.goods.service.impl.ThirdOtherServiceImpl;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

public class ThirdOtherServiceTest extends UnitilsJUnit3
{
    /**
     * 需要测试的Service
     */
    @TestedObject
    private ThirdOtherService otherService = new ThirdOtherServiceImpl();
    
    /**
     * 模拟
     */
    @InjectIntoByType
    private Mock<ThirdOtherMapper> thirdOtherMapperMock;
    
    /**
     * 商品分类实体类集合
     */
    private List<GoodsCate> goodsCates = new ArrayList<>();
    
    /**
     * 商品分类实体类
     */
    private GoodsCate goodsCate = new GoodsCate();
    
    /**
     * 商品品牌类集合
     */
    private List<GoodsBrand> goodsBrands = new ArrayList<>();
    
    /**
     * 商品品牌类
     */
    private GoodsBrand goodsBrand = new GoodsBrand();
    
    /**
     * 商品标签类集合
     */
    private List<GoodsTag> goodsTags = new ArrayList<>();
    
    /**
     * 商品标签类
     */
    private GoodsTag goodsTag = new GoodsTag();
    
    /**
     *  分页辅助类
     */
    private PageBean pageBean = new PageBean();
    
    @Override
    protected void setUp() throws Exception 
    {
    	goodsTags.add(goodsTag);
    	goodsBrands.add(goodsBrand);
    	goodsCates.add(goodsCate);
    }
    
    /**
     * 根据第三方ID查询所有的签约的分类信息测试
     */
    @Test
    public void testQueryGrandCateForThirdnew()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thirdId", 1L);
        map.put("catId", 1L);
        thirdOtherMapperMock.returns(goodsCates).queryGrandCateForThirdnew(map);
    	assertEquals(1, otherService.queryGrandCateForThirdnew(1L, 1L).size());
    }
    
    /**
     * 根据第三方ID查询签约的品牌测试
     */
    @Test
    public void testQueryGrandBrandByThirdId()
    {
    	thirdOtherMapperMock.returns(goodsBrands).queryGrandListByThirdId(1L);
    	assertEquals(1, otherService.queryGrandBrandByThirdId(1L).size());
    }
    
    /**
     * 查询所有的商品标签测试
     */
    @Test
    public void testQueryAllGoodsTagForThird()
    {
    	thirdOtherMapperMock.returns(goodsTags).queryAllGoodsTagForThirdId();
    	assertEquals(1, otherService.queryAllGoodsTagForThird().size());
    }
    
    /**
     *  根据第三方ID查询所有的签约的分类信息测试
     */
    @Test
    public void testQueryGrandCateForThird()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thirdId", 1L);
        map.put("catId", 1L);
        thirdOtherMapperMock.returns(goodsCates).queryAllGoodsCateForThirdtwo(map);
    	assertEquals(1, otherService.queryGrandCateForThirdtwo(1L, 1L).size());
    }
    
    /**
     * 根据第三方分类ID查询第三方签约的分类信息测试
     */
    @Test
    public void testQueryGoodsCateForThirdById()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thirdId", 1L);
        map.put("catId", 1L);
        thirdOtherMapperMock.returns(goodsCate).queryGoodsCateForThirdById(map);
    	assertNotNull(otherService.queryGoodsCateForThirdById(1L, 1L));
    }
    
    /**
     * 根据第三方的信息 查询相关的商品集合测试
     */
    @Test
    public void testqueryAboutGoodsForThirdByThirdInfo()
    {
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("thirdId", 1L);
        map.put("catId", 1L);
        thirdOtherMapperMock.returns(1).queryAboutGoodsCountByCatId(map);
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        thirdOtherMapperMock.returns(list).queryAboutGoodsListByCatId(map);
    	assertEquals(1, otherService.queryAboutGoodsForThirdByThirdInfo(pageBean, 1L, 1L).getList().size());
    }
    /**
     * 根据第三方的信息，查询相关的商品组合测试
     */
    @Test
    public void testQueryThirdGroupByParam()
    {
    	SelectBean bean = new SelectBean();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thirdId", 1L);
        map.put("condition", bean.getCondition());
        map.put("searchText", bean.getSearchText());
        thirdOtherMapperMock.returns(1).queryThirdTotalAcount(map);
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        thirdOtherMapperMock.returns(list).queryThirdGroupByParam(map);
    	assertEquals(1, otherService.queryThirdGroupByParam(pageBean, 1L, bean).getList().size());
    }
    
    /**
     * 保存签约的品牌信息测试
     */
    @Test
    public void testSaveGrantBrand()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thirdId", 1L);
        map.put("brandId", 1L);
        thirdOtherMapperMock.returns(1).insertThirdGrantbrand(map);
    	assertEquals(1, otherService.saveGrantBrand(1L, 1L));
    }
    
    /**
     * 保存签约的分类信息测试
     */
    @Test
    public void testSaveGrantCat()
    {
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("thirdId", 1L);
        map.put("catId", 1L);
        thirdOtherMapperMock.returns(1).insertThirdGrantCat(map);
    	assertEquals(1, otherService.saveGrantCat(1L, 1L));
    }
}
