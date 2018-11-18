package com.junit.order.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.coupon.bean.Coupon;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.gift.bean.Gift;
import com.ningpai.gift.service.GiftService;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.goods.vo.GoodsProductDetailViewVo;
import com.ningpai.marketing.bean.Marketing;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.order.bean.BarterOrder;
import com.ningpai.order.bean.Order;
import com.ningpai.order.bean.OrderCoupon;
import com.ningpai.order.bean.OrderExpress;
import com.ningpai.order.bean.OrderGift;
import com.ningpai.order.bean.OrderGoods;
import com.ningpai.order.bean.OrderGoodsInfoCoupon;
import com.ningpai.order.bean.OrderGoodsInfoGift;
import com.ningpai.order.bean.OrderMarketing;
import com.ningpai.order.dao.BarterOrderMapper;
import com.ningpai.order.dao.OrderCouponMapper;
import com.ningpai.order.dao.OrderExpressMapper;
import com.ningpai.order.dao.OrderGiftMapper;
import com.ningpai.order.dao.OrderGoodsInfoCouponMapper;
import com.ningpai.order.dao.OrderGoodsInfoGiftMapper;
import com.ningpai.order.dao.OrderGoodsMapper;
import com.ningpai.order.dao.OrderMapper;
import com.ningpai.order.dao.OrderMarketingMapper;
import com.ningpai.order.service.BarterService;
import com.ningpai.order.service.impl.BarterServiceImpl;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;

/**
 * 换单业务层测试
 * @author djk
 *
 */
public class BarterServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	@TestedObject
	private BarterService barterService = new BarterServiceImpl();
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<GoodsProductService> goodsProductServiceMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderMapper> orderMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderGoodsMapper> orderGoodsMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderMarketingMapper> orderMarketingMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<MarketingService> marketingServiceMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderGoodsInfoCouponMapper> orderGoodsInfoCouponMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<CouponService> couponServiceMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderGoodsInfoGiftMapper> orderGoodsInfoGiftMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<GiftService> giftServiceMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderCouponMapper> orderCouponMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderGiftMapper> orderGiftMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderExpressMapper> orderExpressMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<BarterOrderMapper> barterOrderMapperMock;
	
	/**
	 * 换单信息测试
	 */
	private BarterOrder barterOrder = new BarterOrder();
	
	/**
	 * 订单主表
	 */
	private Order order = new Order();
	
	/**
	 *  订单商品信息表集合
	 */
	private List<OrderGoods> orderGoodLists = new ArrayList<>();
	
	/**
	 *  订单商品信息表
	 */
	private OrderGoods orderGoods = new OrderGoods();
	
	/**
	 * 订单商品 送优惠券集合
	 */
	private List<OrderGoodsInfoCoupon> orderGoodsInfoCouponsList = new ArrayList<>();
	
	/**
	 * 订单商品 送优惠券
	 */
	private OrderGoodsInfoCoupon orderGoodsInfoCoupon = new OrderGoodsInfoCoupon();
	
	/**
	 * 订单商品赠品信息集合
	 */
	private List<OrderGoodsInfoGift> orderGoodsInfoGiftList = new ArrayList<>();
	
	/**
	 * 订单商品赠品信息
	 */
	private OrderGoodsInfoGift orderGoodsInfoGift = new OrderGoodsInfoGift();
	
	/**
	 * 单促销级联表集合
	 */
	private List<OrderMarketing> orderMarketingList = new ArrayList<>();
	
	/**
	 * 单促销级联
	 */
	private OrderMarketing orderMarketing = new OrderMarketing();
	
	/**
	 * 订单促销优惠券集合
	 */
	private  List<OrderCoupon> orderCouponsList = new ArrayList<>();
	
	/**
	 * 订单促销优惠券
	 */
	private OrderCoupon orderCoupon = new OrderCoupon();
	
	/**
	 * 订单-促销赠品集合
	 */
	private List<OrderGift> orderGiftsList  = new ArrayList<>();
	
	/**
	 * 订单-促销赠品
	 */
	private OrderGift orderGift = new OrderGift();
	
	@Override
	protected void setUp() throws Exception 
	{
		orderMarketingList.add(orderMarketing);
		orderGift.setGiftId(1L);
		orderGiftsList.add(orderGift);
		orderCoupon.setCouponId(1L);
		orderCouponsList.add(orderCoupon);
		orderMarketing.setMarketingId(1L);
		orderMarketing.setHaveCouponStatus("1");
		orderMarketing.setHaveGiftStatus("1");
		orderGoodsInfoGift.setGiftId(1L);
		orderGoodsInfoGiftList.add(orderGoodsInfoGift);
		orderGoodsInfoCoupon.setCouponId(1L);
		orderGoodsInfoCouponsList.add(orderGoodsInfoCoupon);
		orderGoods.setOrderGoodsId(1L);
		orderGoods.setGoodsInfoId(1L);
		orderGoods.setGoodsMarketingId(1L);
		orderGoods.setHaveCouponStatus("1");
		orderGoods.setHaveGiftStatus("1");
		orderGoodLists.add(orderGoods);
	}
	
	
	/**
	 * 换单分页测试
	 */
	@Test
	public void testQueryBarterPageSize()
	{
		PageBean pageBean = new PageBean();
	    Map<String, Object> paramMap = MapUtil.getParamsMap(barterOrder);
	    barterOrderMapperMock.returns(1).selectBarterGetCount(paramMap);
        paramMap.put("startRowNum", pageBean.getStartRowNum());
        paramMap.put("endRowNum", pageBean.getEndRowNum());
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        barterOrderMapperMock.returns(list).selectBarterPageSize(paramMap);
		assertEquals(1, barterService.queryBarterPageSize(pageBean, barterOrder, "", "").getList().size());
	}
	
	/**
	 * 批量删除测试
	 */
	@Test
	public void testBatchDelBarterOrder()
	{
		Long [] ids = {1L};
		List<Long> lists = new ArrayList<>();
		lists.add(1L);
		barterOrderMapperMock.returns(1).batchBarterOrder(lists);
		assertEquals(1, barterService.batchDelBarterOrder(ids));
	}
	
	/**
	 * 换单详细信息测试
	 */
	@Test
	public void testQueryBarterDetails()
	{
		barterOrder.setOrderId(1L);
		barterOrder.setBarterOrderCode("1");
		barterOrderMapperMock.returns(barterOrder).selectBarterDetails(1L);
		orderMapperMock.returns(order).orderDetail(1L);
		orderGoodsMapperMock.returns(orderGoodLists).selectBarterGoodList("1");
		orderExpressMapperMock.returns(new OrderExpress()).selectOrderExpress(1L);
		goodsProductServiceMock.returns(new GoodsProductDetailViewVo()).queryViewVoByProductId(1L);
		marketingServiceMock.returns(new Marketing()).marketingDetail(1L);
		orderGoodsInfoCouponMapperMock.returns(orderGoodsInfoCouponsList).selectOrderGoodsInfoCoupon(1L);
		couponServiceMock.returns(new Coupon()).searchCouponById(1L);
		orderGoodsInfoGiftMapperMock.returns(orderGoodsInfoGiftList).selectOrderGoodsInfoGift(1L);
		giftServiceMock.returns(new Gift()).selectGiftDetailById(1L);
		orderMarketingMapperMock.returns(orderMarketingList).selectOrderMarketingList(1L);
		marketingServiceMock.returns(new Marketing()).marketingDetail(1L);
		orderCouponMapperMock.returns(orderCouponsList).selectOrderCoupon(1L);
		couponServiceMock.returns(new Coupon()).searchCouponById(1L);
		orderGiftMapperMock.returns(orderGiftsList).selectOrderGiftList(1L);
		giftServiceMock.returns(new Gift()).selectGiftDetailById(1L);
		assertNotNull(barterService.queryBarterDetails(1L));
	}
	
	/**
	 * 修改换货单审核约定测试
	 */
	@Test
	public void testModifyBarterCheck()
	{
		barterOrderMapperMock.returns(1).updateByPrimaryKeySelective(barterOrder);
		assertEquals(1, barterService.modifyBarterCheck(barterOrder));
	}
	
	/**
	 * 查询到的换单数量测试
	 */
	@Test
	public void testQueryBarterOrderCount()
	{
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thirdId", 1L);
        barterOrderMapperMock.returns(1).queryBarterOrderCount(map);
		assertEquals(1, barterService.queryBarterOrderCount(1L));
	}
	
	/**
	 * 查询到的换单数量(已买)测试
	 */
	@Test
	public void testQueryBarterOrderCountBuy()
	{
	    Map<String, Object> map = new HashMap<String, Object>();
        map.put("customerId", 1L);
        barterOrderMapperMock.returns(1).queryBarterOrderCountBuy(map);
		assertEquals(1, barterService.queryBarterOrderCountBuy(1L));
	}
}
