package com.junit.third.goods.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.goods.service.GoodsService;
import com.ningpai.third.goods.dao.ThirdGoodsMapper;
import com.ningpai.third.goods.service.ThirdGoodsService;
import com.ningpai.third.goods.service.impl.ThirdGoodsServiceImpl;
import com.ningpai.third.goods.util.ThirdGoodsSearchBean;
import com.ningpai.third.goods.vo.SalesProductVo;
import com.ningpai.third.seller.bean.StoreInfo;
import com.ningpai.third.seller.service.SellerService;
import com.ningpai.util.PageBean;

/**
 * 第三方商品Service测试
 * @author djk
 *
 */
public class ThirdGoodsServiceTest extends UnitilsJUnit3
{
    /**
     * 需要测试的Service
     */
    @TestedObject
    private ThirdGoodsService thirdGoodsService = new ThirdGoodsServiceImpl();
    
    /**
     * 模拟
     */
    @InjectIntoByType
    private Mock<ThirdGoodsMapper> thirdGoodsMapperMock;
    
    /**
     * 模拟
     */
    @InjectIntoByType
    private Mock<SellerService> sellerServiceMock;
    
    /**
     * 模拟
     */
    @InjectIntoByType
    private Mock<GoodsService> GoodsServiceMock;
    
    /**
     * 分页辅助类
     */
    private PageBean pageBean = new PageBean();
    
    /**
     * 第三方商品查询辅助Bean
     */
    private ThirdGoodsSearchBean thirdGoodsSearchBean = new ThirdGoodsSearchBean();

    /**
     *  销售的商品集合
     */
    private  List<SalesProductVo>  salesProductVoLists = new ArrayList<>();
    
    /**
     *  销售的商品
     */
    private SalesProductVo salesProductVo = new SalesProductVo();
    @Override
    protected void setUp() throws Exception 
    {
    	salesProductVoLists.add(salesProductVo);
    	thirdGoodsSearchBean.setCondition("1");
    	thirdGoodsSearchBean.setSearchText("1");
    }
    /**
     * 根据分页辅助Bean查询第三方商品列表测试
     */
    @Test
    public void testQueryThirdGoodsList()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("condition", "1");
        map.put("searchText","1");
        map.put("thirdId", 1L);
        map.put("goodsAdded", "1");
        thirdGoodsMapperMock.returns(1).queryThirdGoodsCount(map);
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        List<Object> lists = new ArrayList<>();
        lists.add(new Object());
        thirdGoodsMapperMock.returns(lists).queryThirdGoodsList(map);
    	assertEquals(1, thirdGoodsService.queryThirdGoodsList(pageBean,1L,thirdGoodsSearchBean,1L).getList().size());
    }
    
    /**
     * 测试
     */
    @Test
    public void testQueryThirdGoodsListByFlag()
    {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("goodsAdded", "1");
        map.put("flag", "1");
        map.put("condition", "1");
        map.put("searchText","1");
        map.put("thirdId", 1L);
        thirdGoodsMapperMock.returns(1).queryThirdGoodsCountByFlag(map);
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        List<Object> lists = new ArrayList<>();
        lists.add(new Object());
        thirdGoodsMapperMock.returns(lists).queryThirdGoodsListByFlag(map);
    	assertEquals(1, thirdGoodsService.queryThirdGoodsListByFlag(pageBean, 1L, thirdGoodsSearchBean, 1L, 1L).getList().size());
    }
    
    /**
     * 根据第三方ID查询销量排行的商品测试
     */
    @Test
    public void testQueryTopSalesGoods()
    {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("thirdId", 1L);
    	thirdGoodsMapperMock.returns(salesProductVoLists).queryTopSalesByThirdId(map);
    	assertEquals(1, thirdGoodsService.queryTopSalesGoods(1L).size());
    }
    
    /**
     * 根据分页辅助Bean查询第三方商品复制列表测试
     */
    @Test
    public void testQueryThirdGoodsCopyList()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("goodsAdded", "1");
        map.put("condition", "1");
        map.put("searchText","1");
        map.put("thirdId", 1L);
        thirdGoodsMapperMock.returns(1).queryThirdGoodsCopyCount(map);
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        List<Object> lists = new ArrayList<>();
        lists.add(new Object());
        thirdGoodsMapperMock.returns(lists).queryThirdGoodsCopyList(map);
    	assertEquals(1, thirdGoodsService.queryThirdGoodsCopyList(pageBean, 1L, thirdGoodsSearchBean, 1L).getList().size());
    }
    
    /**
     * 根据商品id数组复制商品测试
     */
    @Test
    public void testCopyByGoodsIds()
    {
    	Long [] goodsIds = {1L};
    	sellerServiceMock.returns(new StoreInfo()).selectByStoreId(null);
    	assertEquals(0, thirdGoodsService.copyByGoodsIds(goodsIds, new MockHttpServletRequest()));
    }
 
    /**
     * 获取前台地址测试
     */
    @Test
    public void testBsetUrl()
    {
    	thirdGoodsMapperMock.returns("1").bsetUrl();
    	assertEquals("1", thirdGoodsService.bsetUrl());
    }
}
