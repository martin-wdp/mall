package com.junit.third.grandbrand.service;

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
import com.ningpai.third.grandbrand.mapper.GrandBrandMapper;
import com.ningpai.third.grandbrand.service.GrandBrandService;
import com.ningpai.third.grandbrand.service.impl.GrandBrandServiceImpl;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;

/**
 * 品牌授权管理Service测试
 * @author djk
 *
 */
public class GrandBrandServiceTest  extends UnitilsJUnit3
{
	  /**
     * 需要测试的Service
     */
    @TestedObject
    private GrandBrandService grandBrandService = new GrandBrandServiceImpl();
    
    /**
     * 模拟
     */
    @InjectIntoByType
    private Mock<GrandBrandMapper> grandBrandMapperMock;
    
    /**
     * 商品品牌类测试
     */
    private GoodsBrand goodsBrand = new GoodsBrand();
    
    /**
     * 分页辅助类
     */
    private PageBean pageBean = new PageBean();
    
    /**
     *  查询该品牌商品的数量测试
     */
    @Test
    public void testCheckGoodCount()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("brandId", 1L);
        map.put("thirdId", 1L);
        grandBrandMapperMock.returns(1).checkGoodCount(map);
    	assertEquals(1, grandBrandService.checkGoodCount(1L, 1L));
    }
    
    /**
     * 分页查询测试
     */
    @Test
    public void testQueryAllGoodsGrandBrand()
    {
        Map<String, Object> map = MapUtil.getParamsMap(goodsBrand);
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        map.put("thirdId", 1L);
        map.put("rateStatus", "1");
        map.put("forBrand", null);
        grandBrandMapperMock.returns(1).searchGrandBrandCount(map);
        List<Object> lists = new ArrayList<>();
        lists.add(new Object());
        grandBrandMapperMock.returns(lists).queryAllThirdGrandBrand(map);
    	assertEquals(1, grandBrandService.queryAllGoodsGrandBrand(pageBean, goodsBrand, "1", 1L).getList().size());
    }
    
    /**
     *  查询全部品牌测试
     */
    @Test
    public void testQueryAllGoodsGrandBrand2()
    {
        List<Object> lists = new ArrayList<>();
        lists.add(new Object());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thirdId", 1L);
        grandBrandMapperMock.returns(lists).queryAllByThirdGoodsBrand(map);
    	assertEquals(1, grandBrandService.queryAllGoodsGrandBrand(1L).size());
    }
    
    /**
     * 查询为加入的品牌测试
     */
    @Test
    public void testQueryForGoodsGrandBrand()
    {
    	Map<String, Object> map = MapUtil.getParamsMap(goodsBrand);
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        map.put("thirdId", 1L);
        map.put("rateStatus", null);
        map.put("forBrand", "a");
        grandBrandMapperMock.returns(1).searchGrandBrandCount(map);
        List<Object> lists = new ArrayList<>();
        lists.add(new Object());
        grandBrandMapperMock.returns(lists).forQueryAllThirdGoodsBrand(map);
    	assertEquals(1, grandBrandService.queryForGoodsGrandBrand(pageBean, goodsBrand, 1L, "a").getList().size());
    }
    
    /**
     * 循环申请品牌
     */
    @Test
    public void testForTheGoodsBrand()
    {
    	String [] brandId = {"1"};
    	grandBrandService.forTheGoodsBrand(brandId, 1L);	
    }
    
    /**
     * 更改品牌标记为删除测试
     */
    @Test
    public void testUpdateGrandBrand()
    {
    	grandBrandService.updateGrandBrand(goodsBrand);
    }
    
    /**
     * 批量修改品牌标记为删除测试
     */
    @Test
    public void testUpdateGrandBrands()
    {
    	Long[] brandIds = {1L};
    	grandBrandService.updateGrandBrands(brandIds, 1L);
    }
}
