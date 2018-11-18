package com.junit.order.order.service;

import java.math.BigDecimal;
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
import com.ningpai.customer.bean.CustomerConsume;
import com.ningpai.customer.bean.CustomerInfo;
import com.ningpai.customer.bean.CustomerPoint;
import com.ningpai.customer.bean.CustomerPointLevel;
import com.ningpai.customer.dao.CustomerConsumeMapper;
import com.ningpai.customer.dao.CustomerInfoMapper;
import com.ningpai.customer.dao.CustomerPointMapper;
import com.ningpai.customer.dao.IntegralSetMapper;
import com.ningpai.customer.service.PointLevelServiceMapper;
import com.ningpai.gift.bean.Gift;
import com.ningpai.gift.service.GiftService;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.dao.GoodsProductMapper;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.goods.vo.GoodsProductDetailViewVo;
import com.ningpai.goods.vo.GoodsProductVo;
import com.ningpai.marketing.bean.Marketing;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.order.bean.BackOrder;
import com.ningpai.order.bean.BackOrderGeneral;
import com.ningpai.order.bean.Order;
import com.ningpai.order.bean.OrderCoupon;
import com.ningpai.order.bean.OrderExpress;
import com.ningpai.order.bean.OrderGift;
import com.ningpai.order.bean.OrderGoods;
import com.ningpai.order.bean.OrderGoodsInfoCoupon;
import com.ningpai.order.bean.OrderGoodsInfoGift;
import com.ningpai.order.bean.OrderMarketing;
import com.ningpai.order.dao.BackOrderMapper;
import com.ningpai.order.dao.OrderCouponMapper;
import com.ningpai.order.dao.OrderExpressMapper;
import com.ningpai.order.dao.OrderGiftMapper;
import com.ningpai.order.dao.OrderGoodsInfoCouponMapper;
import com.ningpai.order.dao.OrderGoodsInfoGiftMapper;
import com.ningpai.order.dao.OrderGoodsMapper;
import com.ningpai.order.dao.OrderMapper;
import com.ningpai.order.dao.OrderMarketingMapper;
import com.ningpai.order.service.BackOrderService;
import com.ningpai.order.service.impl.BackOrderServiceImpl;
import com.ningpai.other.bean.IntegralSet;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;

/**
 * 测试类
 * @author djk
 *
 */
public class BackOrderServiceTest  extends UnitilsJUnit3
{
    /**
     * 订单商品赠品信息集合
     */
    List<OrderGoodsInfoGift> orderGoodsInfoGiftList = new ArrayList<>();
    /**
     * 订单促销级联表集合
     */
    List<OrderMarketing> orderMarketingList = new ArrayList<>();
	/**
	 * 需要测试的接口类
	 */
	@TestedObject
	private BackOrderService backOrderService = new BackOrderServiceImpl();
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<BackOrderMapper> backOrderMapperMock;
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<GoodsProductMapper> goodsProductMapperMock;
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<CustomerPointMapper> customerPointMapperMock;
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<IntegralSetMapper> integralSetMapperMock;
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<CustomerInfoMapper> customerInfoMapperMock;
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<CustomerConsumeMapper> customerConsumeMapperMock;
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
	private Mock<PointLevelServiceMapper> PointLevelServiceMapperMock;
	/**
	 * 退单信息
	 */
	private BackOrder backOrder = new BackOrder();
	/**
	 * 订单主表
	 */
	private Order order = new Order();
	/**
	 * 订单商品
	 */
	private OrderGoods orderGoods = new OrderGoods();
	/**
	 * 订单商品信息表集合
	 */
	private List<OrderGoods> orderGoodsList = new ArrayList<>();
	/**
	 *  货品信息Vo
	 */
	private GoodsProductVo goodsProductVo = new GoodsProductVo();
	/**
	 * 订单商品 送优惠券集合
	 */
    private List<OrderGoodsInfoCoupon> orderGoodsInfoCouponList = new ArrayList<>();
	/**
	 * 订单商品 送优惠券
	 */
    private OrderGoodsInfoCoupon orderGoodsInfoCoupon = new OrderGoodsInfoCoupon();
    /**
     * 订单商品赠品信息
     */
    private OrderGoodsInfoGift orderGoodsInfoGift = new OrderGoodsInfoGift();
    /**
     * 订单促销级联表
     */
    private OrderMarketing orderMarketing = new OrderMarketing();
    
    /**
     * 订单促销优惠券集合
     */
    private  List<OrderCoupon> orderCouponList = new ArrayList<>();
    
    /**
     * 订单促销优惠券
     */
    private OrderCoupon orderCoupon = new OrderCoupon();
    
    /**
     * 订单-促销赠品集合
     */
    private List<OrderGift> orderGiftList = new ArrayList<>();
    
    /**
     * 订单-促销赠品
     */
    private OrderGift orderGift = new OrderGift();
    
    /**
     * 实体类
     */
    private IntegralSet inte = new IntegralSet();
    
    /**
     * 会员详细信息
     */
    private CustomerInfo info = new CustomerInfo(); 
	@Override
	protected void setUp() throws Exception 
	{
		info.setInfoPointSum(10);
		inte.setExchange(1);
		order.setOrderId(1L);
		order.setCustomerId(1L);
		order.setPayId(1L);
		order.setOrderPrice(new BigDecimal(1));
		orderGift.setGiftId(1L);
		orderGiftList.add(orderGift);
		orderCoupon.setCouponId(1L);
		orderCouponList.add(orderCoupon);
		orderMarketing.setHaveCouponStatus("1");
		orderMarketing.setHaveGiftStatus("1");
		orderMarketing.setOrderMarketingId(1L);
		orderMarketing.setMarketingId(1L);
		orderMarketingList.add(orderMarketing);
		orderGoodsInfoGift.setGiftId(1L);
		orderGoodsInfoGiftList.add(orderGoodsInfoGift);
		orderGoodsInfoCoupon.setCouponId(1L);
		orderGoodsInfoCouponList.add(orderGoodsInfoCoupon);
		orderGoods.setGoodsInfoId(1L);	
		orderGoods.setOrderGoodsId(1L);
		orderGoods.setHaveCouponStatus("1");
		orderGoods.setHaveGiftStatus("1");
		orderGoods.setGoodsMarketingId(1L);
		orderGoodsList.add(orderGoods);
		backOrder.setUploadDocuments("1,1");
		backOrder.setOrderCode("1");
		backOrder.setOrderId(1L);
		backOrder.setBackOrderId(1L);
		backOrder.setBackOrderCode("1");
		backOrder.setBackGoodsIdAndSum("1,1");
		goodsProductVo.setGoodsInfoPreferPrice(new BigDecimal(1));
	}
	
	/**
	 * 退单信息列表测试
	 */
	@Test
	public void testBackOrderList()
	{
		PageBean pb = new PageBean();
		Map<String, Object> paramMap = MapUtil.getParamsMap(backOrder);
		backOrderMapperMock.returns(1).searchBackOrderThirdCount(paramMap);
        paramMap.put("start", pb.getStartRowNum());
        paramMap.put("number", pb.getEndRowNum());
        List<Object> backorders = new ArrayList<>();
        backorders.add(backOrder);
        backOrderMapperMock.returns(backorders).searchBackOrderLisThird(paramMap);
        backOrderMapperMock.returns(goodsProductVo).selectGoodsById(1L);
		assertEquals(1, backOrderService.backOrderList(pb, backOrder, "", "").getList().size());
	}
	
	/**
	 * 查询BackOrderdetail测试
	 */
	@Test
	public void testDetail()
	{
		backOrderMapperMock.returns(backOrder).selectBackOrderDetail_new(1L);
        backOrderMapperMock.returns(goodsProductVo).selectGoodsById(1L);
        orderMapperMock.returns(order).getPayOrderByCode("1");
        orderGoodsMapperMock.returns(orderGoodsList).selectBackGoodsList(1L);
        orderExpressMapperMock.returns(new OrderExpress()).selectOrderExpress(1L);
        goodsProductServiceMock.returns(new GoodsProductDetailViewVo()).queryViewVoByProductId(1L);
        marketingServiceMock.returns(new Marketing()).marketingDetail(1L);
        orderGoodsInfoCouponMapperMock.returns(orderGoodsInfoCouponList).selectOrderGoodsInfoCoupon(1L);
        couponServiceMock.returns(new Coupon()).searchCouponById(1L);
        orderGoodsInfoGiftMapperMock.returns(orderGoodsInfoGiftList).selectOrderGoodsInfoGift(1L);
        giftServiceMock.returns(new Gift()).selectGiftDetailById(1L);
        orderMarketingMapperMock.returns(orderMarketingList).selectOrderMarketingList(1L);
        marketingServiceMock.returns(new Marketing()).marketingDetail(1L);
        orderCouponMapperMock.returns(orderCouponList).selectOrderCoupon(1L);
        couponServiceMock.returns(new Coupon()).searchCouponById(1L);
        orderGiftMapperMock.returns(orderGiftList).selectOrderGiftList(1L);
        giftServiceMock.returns(new Gift()).selectGiftDetailById(1L);
        backOrderMapperMock.returns(new BackOrderGeneral()).selectGeneralByBackOrderId(1L);
		assertNotNull(backOrderService.detail(1L));
	}
	
	/**
	 *  退单详细信息
	 */
	@Test
	public void testBackdetail()
	{
		backOrderMapperMock.returns(backOrder).selectBackOrderByBackOrderId(1L);
        backOrderMapperMock.returns(goodsProductVo).selectGoodsById(1L);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderId", 1L);
        map.put("backOrderCode", "1");
        orderGoodsMapperMock.returns(orderGoodsList).queryOrderGoodsByOrderIdAndBackCode(map);
        goodsProductMapperMock.returns(new GoodsProduct()).queryProductByGoodsId(1L);
//		assertNotNull(backOrderService.backdetail(1L, 1L));
	}
	
	/**
     * 当用户退单,后台同意后将把订单完成的积分奖励扣除掉 会员积分列表将增加一条扣除积分信息 会员消费列表将增加一条订单退掉的信息.
	 */
	@Test
	public void testReducePointOrderBack()
	{
		CustomerPointLevel level = new CustomerPointLevel();
		level.setPointNeed("1~10");
		List<CustomerPointLevel> lists = new ArrayList<>();
		lists.add(level);
		order.setOrderIntegral(1L);
		orderMapperMock.returns(order).orderDetail(1L);
		integralSetMapperMock.returns(inte).findPointSet();
		customerPointMapperMock.returns(1).insert(new CustomerPoint());
		customerInfoMapperMock.returns(info).selectCustInfoById(1L);
		PointLevelServiceMapperMock.returns(lists).selectAllPointLevel();
		customerInfoMapperMock.returns(1).updateInfoByCustId(info);
		customerConsumeMapperMock.returns(1).insertSelective(new CustomerConsume());
		assertEquals(0, backOrderService.reducePointOrderBack(1L));
	}
	
	/**
	 * 修改退单审核状态测试
	 */
	@Test
	public void testModifyBackOrderByCheck()
	{
		BackOrder backOrder = new BackOrder();
		backOrder.setBackCheck("4");
		backOrder.setBackOrderId(1L);
		backOrder.setBackOrderCode("1");
		backOrder.setOrderCode("1");
		backOrder.setBackGoodsIdAndSum("1,1");
		backOrderMapperMock.returns(backOrder).selectbackOrderOne(1L);
		backOrderMapperMock.returns(order).selectOrderOne("1");
		backOrderMapperMock.returns(goodsProductVo).selectGoodsById(1L);
		backOrderMapperMock.returns(1).updateOrder(order);
		backOrderMapperMock.returns(backOrder).selectbackOrderOne(1L);
		backOrderMapperMock.returns(order).selectOrderOne("1");
	    Map<String, Object> param = new HashMap<String, Object>();
        param.put("goodsInfoId", 1L);
        param.put("orderId", 1L);
        param.put("backOrderCode", "1");
        orderGoodsMapperMock.returns(1).updateOrderGoodsBack(param);
        backOrder = new BackOrder();
    	backOrder.setBackOrderId(1L);
		backOrder.setBackCheck("4");
    	backOrderMapperMock.returns(1).updateByPrimaryKeySelective(backOrder);
		assertEquals(1, backOrderService.modifyBackOrderByCheck(1L, "4"));
	}
	
	/**
	 * 修改退单信息测试
	 */
	@Test
	public void testModifyBackBeanCheck()
	{
		backOrder.setBackDelFlag("1");
		backOrderMapperMock.returns(1).updateByPrimaryKeySelective(backOrder);
		assertEquals(1, backOrderService.modifyBackBeanCheck(backOrder));
	}
	
	/**
	 * 修改第三方后台退单信息测试
	 */
	@Test
	public void testModifyThirdBackBeanCheck()
	{
		backOrder.setBackDelFlag("1");
		backOrderMapperMock.returns(1).updateByThirdId(backOrder);
		assertEquals(1, backOrderService.modifyThirdBackBeanCheck(backOrder));
	}
	
	/**
	 * 查询第三方退单数量测试
	 */
	@Test
	public void testQueryBackOrderCountBuy()
	{
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("customerId", 1L);
	    backOrderMapperMock.returns(1).queryBackOrderCountBuy(map);
		assertEquals(1, backOrderService.queryBackOrderCountBuy(1L));
	}
	
	/**
	 * 查询第三方退单数量(已买)
	 */
	@Test
	public void testQueryBackOrderCount()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("thirdId", 1L);
		backOrderMapperMock.returns(1).queryBackOrderCount(map);
		assertEquals(1, backOrderService.queryBackOrderCount(1L));
	}
	
	/**
	 * 插入退单信息
	 */
	@Test
	public void testInsertBackOrderInfo()
	{
		backOrderMapperMock.returns(1).insertBackOrderInfo(backOrder);
		assertEquals(1, backOrderService.insertBackOrderInfo(backOrder));
	}
	
	/**
	 * 根据订单编号查找退单信息测试
	 */
	@Test
	public void testQueryBackOrderByOrderCode()
	{
		backOrderMapperMock.returns(backOrder).queryBackOrderByOrderCode("1");
		backOrderMapperMock.returns(goodsProductVo).selectGoodsById(1L);
//		assertNotNull(backOrderService.queryBackOrderByOrderCode("1"));
	}
	
	/**
	 * 根据退单号获取物流号测试
	 */
	@Test
	public void testQueryBackOrderGeneral()
	{
		backOrderMapperMock.returns(new BackOrderGeneral()).queryBackOrderGeneral(1L);
		assertNotNull(backOrderService.queryBackOrderGeneral(1L));
	}
	
	/**
	 * 退款成功后退回库存测试
	 */
	@Test
	public void testAddStockOrderBack()
	{
		order.setWareId(1L);
		orderGoods.setGoodsInfoNum(1L);
		orderMapperMock.returns(order).orderDetail(1L);
		orderGoodsMapperMock.returns(orderGoodsList).selectBackGoodsList(1L);
		 Map<String, Object> map = new HashMap<String, Object>();
         map.put("productId", 1L);
         map.put("stock", 1L);
         map.put("distinctId", 1L);
         goodsProductMapperMock.returns(1).addBaseStock(map);
		Integer result = 1;
		assertEquals(result, backOrderService.addStockOrderBack(1L, 1L));
	}
	
}
