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

import com.ningpai.coupon.bean.CouponRange;
import com.ningpai.coupon.dao.CouponRangeMapper;
import com.ningpai.goods.dao.GoodsBrandMapper;
import com.ningpai.goods.dao.GoodsMapper;
import com.ningpai.third.goods.dao.ThirdCateMapper;
import com.ningpai.third.market.service.ThirdCouponService;
import com.ningpai.third.market.service.impl.ThirdCouponServiceImpl;

/**
 *  第三方优惠券service接口测试
 * @author djk
 *
 */
public class ThirdCouponServiceTest extends UnitilsJUnit3
{
	  /**
	   * 需要测试的类
	   */
	  @TestedObject
	  private ThirdCouponService thirdCouponService = new ThirdCouponServiceImpl();
	  
	  /**
	   * 模拟
	   */
	  @InjectIntoByType
	  private Mock<CouponRangeMapper> couponRangeMapperMock;
	  
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
		  List<CouponRange> scopelist = new ArrayList<>();
		  scopelist.add(new CouponRange());
		  List<Object> lists = new ArrayList<>();
		  lists.add(new Object());
	      Map<String, Object> paramMap = new HashMap<String, Object>();
	      paramMap.put("couponId", 1L);
	      paramMap.put("couponRangeType", "1");
		  couponRangeMapperMock.returns(scopelist).selectCouponRange(paramMap);
		  goodsBrandMapperMock.returns(lists).selectProductBrandList(null);
		  assertEquals(1, thirdCouponService.selectThirdMarketingScope(1L, "1").size());
	  }
}
