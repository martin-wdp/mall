package com.junit.third.market.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.goods.dao.GoodsBrandMapper;
import com.ningpai.goods.dao.GoodsMapper;
import com.ningpai.marketing.bean.MarketingScope;
import com.ningpai.marketing.dao.MarketingScopeMapper;
import com.ningpai.third.goods.dao.ThirdCateMapper;
import com.ningpai.third.market.service.ThirdMarketingService;
import com.ningpai.third.market.service.impl.ThirdMarketingServiceImpl;

/**
 * 新第三方促销范围service接口测试
 * @author djk
 *
 */
public class ThirdMarketingServiceTest extends UnitilsJUnit3
{
	  /**
	   * 需要测试的类
	   */
	  @TestedObject
	  private ThirdMarketingService thirdMarketingService = new ThirdMarketingServiceImpl();
	  
	  /**
	   * 模拟
	   */
	  @InjectIntoByType
	  private Mock<MarketingScopeMapper> marketingScopeMapperMock;
	  
	  /**
	   * 模拟
	   */
	  @InjectIntoByType
	  private Mock<ThirdCateMapper> thirdCateMapperMock;
	  
	  /**
	   * 模拟
	   */
	  @InjectIntoByType
	  private Mock<GoodsBrandMapper> goodsBrandMapperMock;
	  
	  /**
	   * 模拟
	   */
	  @InjectIntoByType
	  private Mock<GoodsMapper> goodsMapperMock;
	  
	  /**
	   * 查询范围信息测试
	   */
	  @Test
	  public void testSelectThirdMarketingScope()
	  {
	      List<MarketingScope> scopelist = new ArrayList<>();
	      scopelist.add(new MarketingScope());
		  List<Object> lists = new ArrayList<>();
		  lists.add(new Object());
          Map<String, Object> paramMap = new HashMap<String, Object>();
          paramMap.put("marketingId", 1L);
          paramMap.put("scopeType", "1");
          marketingScopeMapperMock.returns(scopelist).selectMarketingScope(paramMap);
          goodsBrandMapperMock.returns(lists).selectProductBrandList(null);
		  assertNotNull(thirdMarketingService.selectThirdMarketingScope(1L, "1", 1L));
	  }
}
