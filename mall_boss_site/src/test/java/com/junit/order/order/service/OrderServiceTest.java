package com.junit.order.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.coupon.bean.Coupon;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.customer.dao.CustomerAddressMapper;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.freighttemplate.bean.Express;
import com.ningpai.freighttemplate.bean.FreightTemplate;
import com.ningpai.freighttemplate.dao.ExpressInfoMapper;
import com.ningpai.freighttemplate.dao.FreightTemplateMapper;
import com.ningpai.gift.bean.Gift;
import com.ningpai.gift.service.GiftService;
import com.ningpai.goods.bean.ProductWare;
import com.ningpai.goods.dao.GoodsProductMapper;
import com.ningpai.goods.dao.ProductWareMapper;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.goods.service.ProductWareService;
import com.ningpai.goods.vo.GoodsProductDetailViewVo;
import com.ningpai.goods.vo.GoodsProductVo;
import com.ningpai.marketing.bean.Marketing;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.order.bean.Order;
import com.ningpai.order.bean.OrderContainer;
import com.ningpai.order.bean.OrderContainerRelation;
import com.ningpai.order.bean.OrderCoupon;
import com.ningpai.order.bean.OrderExpress;
import com.ningpai.order.bean.OrderGift;
import com.ningpai.order.bean.OrderGoods;
import com.ningpai.order.bean.OrderGoodsInfo;
import com.ningpai.order.bean.OrderGoodsInfoCoupon;
import com.ningpai.order.bean.OrderGoodsInfoGift;
import com.ningpai.order.bean.OrderMarketing;
import com.ningpai.order.bean.OrderPicking;
import com.ningpai.order.dao.BackOrderMapper;
import com.ningpai.order.dao.OrderContainerMapper;
import com.ningpai.order.dao.OrderContainerRelationMapper;
import com.ningpai.order.dao.OrderCouponMapper;
import com.ningpai.order.dao.OrderExpressMapper;
import com.ningpai.order.dao.OrderGiftMapper;
import com.ningpai.order.dao.OrderGoodsInfoCouponMapper;
import com.ningpai.order.dao.OrderGoodsInfoGiftMapper;
import com.ningpai.order.dao.OrderGoodsInfoMapper;
import com.ningpai.order.dao.OrderGoodsMapper;
import com.ningpai.order.dao.OrderMapper;
import com.ningpai.order.dao.OrderMarketingMapper;
import com.ningpai.order.service.OrderCouponService;
import com.ningpai.order.service.OrderService;
import com.ningpai.order.service.impl.OrderServiceImpl;
import com.ningpai.other.bean.CityBean;
import com.ningpai.other.bean.DistrictBean;
import com.ningpai.other.bean.ProvinceBean;
import com.ningpai.system.bean.LogisticsCompany;
import com.ningpai.system.dao.IsBackOrderMapper;
import com.ningpai.system.service.ILogisticsCompanyBiz;
import com.ningpai.system.service.IsBackOrderService;
import com.ningpai.system.service.PaymentService;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;

/**
 * 订单service测试
 * @author djk
 *
 */
public class OrderServiceTest extends UnitilsJUnit3
{

	/**
	 * 需要测试的接口类
	 */
	@TestedObject
	private OrderService orderService = new OrderServiceImpl();
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderCouponService> orderCouponServiceMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<FreightTemplateMapper> freightTemplateMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<ProductWareService> productWareServiceMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<BackOrderMapper> backOrderMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<CustomerAddressMapper> customerAddressMapperMock;
	
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
	private Mock<ProductWareMapper> productWareMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<GiftService> giftServiceMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderGoodsMapper> orderGoodsMapperMock;
	
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
	private Mock<OrderGoodsInfoGiftMapper> orderGoodsInfoGiftMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderExpressMapper> orderExpressMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderGoodsInfoMapper> orderGoodsInfoMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderContainerRelationMapper> orderContainerRelationMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderContainerMapper> orderContainerMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<ILogisticsCompanyBiz> iLogisticsCompanyBizMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderMapper> orderMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderMarketingMapper> orderMarketingMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<GoodsProductMapper> goodsProductMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<MarketingService> marketingServiceMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<GoodsProductService> goodsProductServiceMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<ExpressInfoMapper> expressInfoMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<IsBackOrderMapper> isBackOrderMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<IsBackOrderService> isBackOrderServiceMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<CustomerServiceMapper> customerServiceMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<PaymentService> paymentServiceMock;
	
	/**
	 * 订单主表
	 */
	private Order order = new Order();
	
	/**
	 * 订单主表 集合
	 */
	private List<Order> orderList = new ArrayList<>();
	
	/**
	 * 订单商品信息表集合
	 */
	private  List<OrderGoods> orderGoodsLists = new ArrayList<>();
	
	/**
	 * 订单商品信息表
	 */
	private OrderGoods orderGoods = new OrderGoods();
	
	/**
	 * 货品信息Vo
	 */
	private GoodsProductVo goodsProductVo = new GoodsProductVo();
	
	/**
	 * 分页辅助类
	 */
	private PageBean pageBean = new PageBean();
	
	/**
	 *  货品信息查看详细信息Vo
	 */
	private GoodsProductDetailViewVo goodsProductDetailViewVo = new GoodsProductDetailViewVo();

	/**
	 *  装箱单表测试
	 */
	private OrderContainer orderContainer = new OrderContainer();
	/**
	 * 订单促销级联表
	 */
	private OrderMarketing orderMarketing = new OrderMarketing();
	
	/**
	 * 订单促销级联表集合
	 */
	private List<OrderMarketing> orderMarketingList = new ArrayList<>();
	
	/**
	 *  订单物流信息
	 */
	private OrderExpress orderExpress = new OrderExpress();
	
	/**
	 *  订单商品 送优惠券集合
	 */
	private List<OrderGoodsInfoCoupon> orderGoodsInfoCouponList = new ArrayList<>();
	
	/**
	 * 订单商品 送优惠券
	 */
	private OrderGoodsInfoCoupon orderGoodsInfoCoupon = new OrderGoodsInfoCoupon();
	
	/**
	 *  订单商品赠品信息集合
	 */
    private List<OrderGoodsInfoGift> orderGoodsInfoGiftList = new ArrayList<>();
    
    /**
     * 订单商品赠品信息
     */
	private OrderGoodsInfoGift orderGoodsInfoGift = new OrderGoodsInfoGift();
	
	/**
	 * 订单促销优惠券集合
	 */
	private List<OrderCoupon> orderCouponList = new ArrayList<>();
	
	/**
	 * 订单促销优惠券
	 */
	private OrderCoupon orderCoupon = new OrderCoupon();
	
	/**
	 *  订单-促销赠品集合
	 */
	private List<OrderGift> orderGiftList = new ArrayList<>();
	
	/**
	 *  订单-促销赠品
	 */
	private OrderGift orderGift = new OrderGift();
	
	/**
	 * 查询货品id和货品数量集合
	 */
	private List<OrderGoodsInfo> orderGoodsInfos = new ArrayList<>();
	
	/**
	 * 查询货品id和货品数量
	 */
	private OrderGoodsInfo orderGoodsInfo = new OrderGoodsInfo();
	
	/**
	 *  装箱单表 集合
	 */
	private  List<OrderContainer> containers = new ArrayList<>();
	
	/**
	 *  货品信息Vo集合
	 */
	private List<GoodsProductVo> goodsProductVos = new ArrayList<>();
	
	
	@Override
	protected void setUp() throws Exception
	{
		goodsProductVo.setGoodsInfoPreferPrice(new BigDecimal(10));
		goodsProductVo.setGoodsInfoId(1L);
		goodsProductVos.add(goodsProductVo);
		orderContainer.setGoodsInfoId(1L);
		orderContainer.setContainerStatus("1");
		containers.add(orderContainer);
		orderGoodsInfo.setGiftNum(1L);
		orderGoodsInfo.setGiftId(1L);
		orderGoodsInfo.setGoodsInfoId(1L);
		orderGoodsInfo.setGoodsInfoNum(1L);
		orderGoodsInfos.add(orderGoodsInfo);
		orderMarketingList.add(orderMarketing);
		orderCoupon.setCouponId(1L);
		orderGift.setGiftId(1L);
		orderCouponList.add(orderCoupon);
		orderGiftList.add(orderGift);
		orderMarketing.setHaveCouponStatus("1");
		orderMarketing.setHaveGiftStatus("1");
		orderMarketing.setMarketingId(1L);
		orderMarketing.setOrderMarketingId(1L);
		orderGoodsInfoGift.setGiftId(1L);
		orderGoodsInfoGiftList.add(orderGoodsInfoGift);
		orderGoodsInfoCoupon.setCouponId(1L);
		orderGoodsInfoCouponList.add(orderGoodsInfoCoupon);
		order.setCustomerId(1L);
		order.setOrderId(1L);
		orderExpress.setOrderId(1L);
		goodsProductVo.setGoodsInfoName("a");
		orderGoods.setOrderGoodsId(1L);
		orderGoods.setGoodsInfoId(1L);
		orderGoods.setGoodsMarketingId(1L);
		orderGoods.setHaveCouponStatus("1");
		orderGoods.setHaveGiftStatus("1");
		orderGoodsLists.add(orderGoods);
		orderList.add(order);
	}
	
	
	/**
	 * 删除单个退单信息测试
	 */
	@Test
	public void testDeleteBackOrderById()
	{
		orderMapperMock.returns(1).deleteBackOrderById(1L, 1L);
		assertEquals(1, orderService.deleteBackOrderById(1L, 1L));
	}
	
	/**
	 * 根据时间查询订单信息测试
	 */
	public void testSelectOrderListByTime()
	{
		Date date = new Date();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startTime", date);
        map.put("endTime", date);
        orderMapperMock.returns(orderList).selectOrderListByTime(map);
		assertEquals(1, orderService.selectOrderListByTime(date, date).size());
	}
	
	/**
	 * 查询每天前十条数量测试
	 */
	@Test
	public void testSelectTopOrderGoods()
	{
		orderGoodsMapperMock.returns(orderGoodsLists).selectTopOrderGoods();
		assertEquals(1, orderService.selectTopOrderGoods().size());
	}
	
	/**
	 * 根据时间查询订单总数量测试
	 */
	@Test
	public void testSelectOrderCountByCurdate()
	{
		orderMapperMock.returns(1).selectOrderCountByCurdate();
		assertEquals(1, orderService.selectOrderCountByCurdate());
	}
	
	/**
	 * 返回提交订单时的第一个商品名称测试
	 */
	@Test
	public void testQueryGoodsInfoName()
	{
		orderGoodsMapperMock.returns(orderGoodsLists).selectOrderGoodsList(1L);
		goodsProductServiceMock.returns(goodsProductVo).queryByPrimaryId(1L);
		assertEquals("a", orderService.queryGoodsInfoName(1L));
	}
	
	/**
	 * 更具订单id数组查询订单列表测试
	 */
	@Test
	public void testSearchOrderList()
	{
		List<Long> lIds = new ArrayList<>();
		lIds.add(1L);
		Long [] ids = {1L};
        Map<String, Object> paramMap = MapUtil.getParamsMap(order);
        paramMap.put("startRowNum", pageBean.getStartRowNum());
        paramMap.put("endRowNum", pageBean.getEndRowNum());
        paramMap.put("list", lIds);
        orderMapperMock.returns(1).searchOrderCountByOrderIdsList(paramMap);
        paramMap.put("start", pageBean.getStartRowNum());
        paramMap.put("number", pageBean.getEndRowNum());
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        orderMapperMock.returns(list).searchOrderListByOrderIdList(paramMap);
		assertEquals(1, orderService.searchOrderList(order, pageBean,ids).getList().size());
	}
	
	/**
	 * 新订单列表页面分页测试
	 */
	@Test
	public void testNewajaxgetpagefoot()
	{
        Map<String, Object> paramMap = MapUtil.getParamsMap(order);
        paramMap.put("start", pageBean.getStartRowNum());
        paramMap.put("number", pageBean.getEndRowNum());
        paramMap.put("orderStatus", "1");
        orderMapperMock.returns(1).searchOrderCount(paramMap);
		assertEquals(1,orderService.newajaxgetpagefoot("2", order, pageBean).getRows());
	}
	
	/**
	 * 查询订单列表测试
	 */
	@Test
	public void testSearchOrderList2()
	{
	    Map<String, Object> paramMap = MapUtil.getParamsMap(order);
	    orderMapperMock.returns(1).searchOrderCount(paramMap);
        paramMap.put("start", pageBean.getStartRowNum());
        paramMap.put("number", pageBean.getEndRowNum());
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        orderMapperMock.returns(list).searchOrderList(paramMap);
		assertEquals(1, orderService.searchOrderList(order, pageBean).getList().size());
	}
	
	/**
	 * 新订单查询列表页面测试
	 */
	@Test
	public void testNewsearchOrderList()
	{
	    Map<String, Object> paramMap = MapUtil.getParamsMap(order);
	    paramMap.put("start", pageBean.getStartRowNum());
	    paramMap.put("number", pageBean.getEndRowNum());
	    paramMap.put("orderStatus", "1");    
	    List<Object> obj = new ArrayList<>();
	    obj.add(new Object());
	    orderMapperMock.returns(obj).searchOrderList(paramMap);
	    orderMapperMock.returns(1).searchOrderCount(paramMap);
		assertEquals(1, orderService.newsearchOrderList("2", order, pageBean).size());
	}
	
	/**
	 * 查询订单详细测试
	 */
	@Test
	public void testOrderDetail()
	{
		orderMapperMock.returns(order).orderDetail(1L);
		orderGoodsMapperMock.returns(orderGoodsLists).selectOrderGoodsList(1L);
		orderExpressMapperMock.returns(new OrderExpress()).selectOrderExpress(1L);
		goodsProductServiceMock.returns(goodsProductDetailViewVo).queryViewVoByProductId(1L);
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
		assertNotNull(orderService.orderDetail(1L));
	}
	
	/**
	 * 查询物流信息测试
	 */
	@Test
	public void testExpressDetail()
	{
		orderExpressMapperMock.returns(orderExpress).selectOrderExpress(1L);
		assertNotNull(orderService.expressDetail(1L));
	}
	
	/**
	 * 发货测试
	 */
	@Test
	public void testSendOrder()
	{
		orderExpressMapperMock.returns(1).updateExpress(orderExpress);
		orderMapperMock.returns(1).sendOrder(1L);
		assertEquals(1, orderService.sendOrder(orderExpress));
	}
	
	/**
	 * 发货测试
	 */
	@Test
	public void testSendOrderByP()
	{
	    Order order = new Order();
	    order.setOrderId(1L);
	    order.setOrderStatus("2");
		orderMapperMock.returns(1).updateByPrimaryKeySelective(order);
		assertEquals(1, orderService.sendOrderByP(1L, "2", "1", "1", 1L));
	}
	
	/**
	 *  插入订单测试
	 */
	@Test
	public void testInsertOrder()
	{
		orderMapperMock.returns(1).insertOrder(order);
		assertEquals(1, orderService.insertOrder(order));
	}
	
	/**
	 * 查询刚刚插入的订单ID测试
	 */
	@Test
	public void testSelectLastId()
	{
		orderMapperMock.returns(1L).selectLastId();
		Long result = 1L;
		assertEquals(result, orderService.selectLastId());
	}
	
	/**
	 * 确认支付测试 
	 */
	@Test
	public void testPayOrder()
	{
		orderMapperMock.returns(1).payOrder(1L);
		assertEquals(1, orderService.payOrder(1L));
	}
	
	/**
	 * 查询订单测试
	 */
	@Test
	public void testGetPayOrder()
	{
		orderMapperMock.returns(order).orderDetail(1L);
		orderGoodsMapperMock.returns(orderGoodsLists).selectOrderGoodsList(1L);
		assertNotNull(orderService.getPayOrder(1L));
	}
	
	/**
	 * 查询订单根据Code测试
	 */
	@Test
	public void testGetPayOrderByCode()
	{
		orderMapperMock.returns(order).getPayOrderByCode("a");
		assertNotNull(orderService.getPayOrderByCode("a"));
	}
	
	/**
	 *  修改订单信息状态根据Id测试
	 */
	@Test
	public void testModifyOrderByKey()
	{
        List<Long> list = new ArrayList<Long>();
        list.add(1L);
		orderMapperMock.returns(orderList).selectOrderList(list);
		orderCouponServiceMock.returns(1).modifyCouponByOrderId(1L, 1L);
		orderMapperMock.returns(1).updateByPrimaryKeySelective(order);
		assertEquals(1, orderService.modifyOrderByKey(1L, "3"));
	}
	
	/**
	 * 修改订单信息状态根据Id测试
	 */
	@Test
	public void testModifyOrderByKey2()
	{
		order = new Order();
        order.setOrderId(1L);
        order.setBusinessId(1L);
        order.setOrderStatus("3");
		orderMapperMock.returns(1).updateOrderStatusByorderId(order);
		assertEquals(1,orderService.modifyOrderByKey(1L, 1L, "3"));
	}
	
	/**
	 *  根据状态查询订单总数测试
	 */
	@Test
	public void testBusinessOrderCount()
	{
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("businessId", 1L);
        paramMap.put("orderStatus", "2");
        paramMap.put("delFlag", "0");
        orderMapperMock.returns(1).searchThirdOrderCount(paramMap);
		assertEquals(1, orderService.businessOrderCount(1L, "2"));
	}
	
	/**
	 * 添加订单促销信息
	 */
	@Test
	public void testInsertSelective()
	{
		orderService.insertSelective(orderMarketing);
	}
	
	/**
	 * 查看最后加入的订单促销id测试
	 */
	@Test
	public void testSelectOrderMarketLastId()
	{
		orderMarketingMapperMock.returns(1L).selectOrderMarketLastId();
		Long result = 1L;
		assertEquals(result, orderService.selectOrderMarketLastId());
	}
	
	/**
	 *插入所有订单赠品测试 
	 */
	@Test
	public void testInsertOrderInfoGift()
	{
		orderService.insertOrderInfoGift(new ArrayList<OrderGift>());
	}
	
	/**
	 * 插入所有的订单优惠劵测试
	 */
	@Test
	public void testInsertCouponInfoGift()
	{
		orderService.insertCouponInfoGift(new ArrayList<OrderCoupon>());
	}
	
	/**
	 *  拣货测试
	 */
	@Test
	public void testQueryByPincking()
	{
		Gift gift = new Gift();
		gift.setGiftCode("1");
		Long [] orderId = {1L};
		List<Long> list = new ArrayList<>();
		list.add(1L);
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		orderMapperMock.returns(order).orderDetail(1L);
		orderGoodsInfoMapperMock.returns(orderGoodsInfos).queryByGoodsInfosCount(list);
		goodsProductServiceMock.returns(goodsProductVo).queryByPrimaryId(1L);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orders", orders);
        map.put("count", 1);
        orderGoodsInfoMapperMock.returns(orderGoodsInfos).queryGiftCountByOrderIds(list);
        giftServiceMock.returns(gift).selectGiftDetailById(1L);
        orderGoodsInfoMapperMock.returns(orderGoodsInfos).queryGiftCountByGoodsIds(list);
        giftServiceMock.returns(gift).selectGiftDetailById(1L);
        map.put("orderGoodsInfos", orderGoodsInfos);
        map.put("gifts", orderGoodsInfos);
        map.put("orderPicking", new OrderPicking());
		assertEquals(5, orderService.queryByPincking(orderId, 1L, "1", "1").size());
	}
	
	/**
	 * 出库测试
	 */
	@Test
	public void testSelectOrderListsByOrderIds()
	{
		Gift gift = new Gift();
		gift.setGiftCode("a");
	    List<Long> list = new ArrayList<Long>();
	    list.add(1L);
		Long[] orderId = {1L};
		orderMapperMock.returns(orderList).selectOrderList(list);
		orderGoodsMapperMock.returns(orderGoodsLists).selectOrderGoodsList(1L);
		goodsProductServiceMock.returns(new GoodsProductDetailViewVo()).queryViewVoByProductId(1L);
		orderGoodsInfoMapperMock.returns(orderGoodsInfos).selectGiftByOrderIdInGoods(1L);
		giftServiceMock.returns(gift).selectGiftDetailById(1L);
		orderGoodsInfoMapperMock.returns(orderGoodsInfos).selectGiftByOrderIdInOrder(1L);
		giftServiceMock.returns(gift).selectGiftDetailById(1L);
		assertEquals(1, orderService.selectOrderListsByOrderIds(orderId, 1L).size());
	}
	
	/**
	 *  为订单添加默认包裹测试
	 */
	@Test
	public void testInitContainerRelation()
	{
		Gift gift = new Gift();
		gift.setGiftCode("a");
		orderContainerRelationMapperMock.returns(new ArrayList<OrderContainerRelation>()).selectListByOrderIds(1L);
		orderExpressMapperMock.returns(new OrderExpress()).selectOrderExpress(1L);
		orderMapperMock.returns(order).orderDetail(1L);
		orderGoodsMapperMock.returns(orderGoodsLists).selectOrderGoodsList(1L);
		goodsProductServiceMock.returns(new GoodsProductDetailViewVo()).queryViewVoByProductId(1L);
		orderContainerRelationMapperMock.returns(1L).selectLastId();
		orderGoodsInfoMapperMock.returns(orderGoodsInfos).selectGiftByOrderIdInGoods(1L);
		giftServiceMock.returns(gift).selectGiftDetailById(1L);
		orderGoodsInfoMapperMock.returns(orderGoodsInfos).selectGiftByOrderIdInOrder(1L);
		giftServiceMock.returns(gift).selectGiftDetailById(1L);
		orderContainerRelationMapperMock.returns(1L).selectLastId();
		orderService.initContainerRelation(1L);
	}
	
	@Test
	public void testQueryContainerRalation()
	{
		OrderContainerRelation orderContainerRelation = new OrderContainerRelation();
		orderContainerRelation.setRelationId(1L);
		List<OrderContainerRelation> relations =new ArrayList<>();
		relations.add(orderContainerRelation);
		orderContainerRelationMapperMock.returns(relations).selectListByOrderIds(1L);
		orderContainerMapperMock.returns(containers).queryContainerByRelationId(1L);
		giftServiceMock.returns(new Gift()).selectGiftDetailById(1L);
		assertEquals(1, orderService.queryContainerRalation(1L).size());
	}
	
	/**
	 * 新增装箱单测试
	 */
	@Test
	public void testAddContainerRalation()
	{
		orderExpressMapperMock.returns(new OrderExpress()).selectOrderExpress(1L);
		orderService.addContainerRalation(1L);
	}
	
	/**
	 * 更改包裹
	 */
	@Test
	public void testUpdateRelation()
	{
		orderService.updateRelation(orderContainer);
	}
	
	/**
	 * 添加包裹商品
	 */
	@Test
	public void testAddRelation()
	{
		orderService.addRelation(orderContainer);
	}
	
	/**
	 *  根据Id 获得包裹中商品的信息测试
	 */
	@Test
	public void testQueryOrderContainerById()
	{
		orderContainerMapperMock.returns(orderContainer).queryContainerByParam(1L);
		assertNotNull(orderService.queryOrderContainerById(1L));
	}
	
	/**
	 * 根据rId 和货品Id获得包裹中商品的信息测试
	 */
	@Test
	public void testqueryOrderContainerByGoodInfoId()
	{
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rId", 1L);
        map.put("goodInfoId", 1L);
        orderContainerMapperMock.returns(orderContainer).queryOrderContainerByGoodInfoId(map);
		assertNotNull(orderService.queryOrderContainerByGoodInfoId(1L, 1L));
	}
	
	/**
	 * 验证包裹下是否有商品测试
	 */
	@Test
	public void testVerifyCount()
	{
		orderContainerMapperMock.returns(1L).verifyCount(1L);
		Long result = 1L;
		assertEquals(result, orderService.verifyCount(1L));
	}
	
	/**
	 *  删除包裹测试
	 */
	@Test
	public void testDelRelationById()
	{
		orderService.delRelationById(1L);
	}
	
	/**
	 * 删除包裹商品测试
	 */
	@Test
	public void testDelContainerByCId()
	{
		orderService.delContainerByCId(1L);
	}
	
	/**
	 * 发货测试
	 */
	@Test
	public void testUpdateSendOrderGoods()
	{
		Long[] relationIds = {1L};
		String[] expressNo = {"1"};
		int[] expressId = {1};
		iLogisticsCompanyBizMock.returns(new LogisticsCompany()).getLogisticsCompanyById(1);
		
		orderService.updateSendOrderGoods(relationIds, expressNo, expressId);
	}
	
	/**
	 *  第三方发货测试
	 */
	@Test
	public void testUpdateThirdSendOrderGoods()
	{
		Long[] relationIds = {1L};
		String[] expressNo = {"1"};
		int[] expressId = {1};
		LogisticsCompany l = new LogisticsCompany();
		l.setLogComId(1);
		l.setName("a");
		iLogisticsCompanyBizMock.returns(l).getThirdLogisticsCompanyById(1);
		orderService.updateThirdSendOrderGoods(relationIds, expressNo, expressId);
	}
	
	/**
	 * 分解包裹下的商品测试
	 */
	@Test
	public void testSplitOrderGoods()
	{
		orderContainer.setGoodsNum(2L);
		orderContainerMapperMock.returns(orderContainer).queryContainerByParam(1L);
		orderService.splitOrderGoods(1L, 1L);	
	}
	
	/**
	 * 查询近一个月新增订单
	 */
	@Test
	public void testSelectNewOrderByParam()
	{
		orderMapperMock.returns(orderList).selectNewOrderByParam();
		orderMapperMock.returns(1L).selectNewOrderCountByParam();
		assertEquals(2, orderService.selectNewOrderByParam().size());
	}
	
	/**
	 * 查询所有包裹测试
	 */
	@Test
	public void testSelectListByOrderIds()
	{
		List<OrderContainerRelation> list = new ArrayList<OrderContainerRelation>();
		list.add(new OrderContainerRelation());
		orderContainerRelationMapperMock.returns(list).selectListByOrderIds(1L);
		assertEquals(1, orderService.selectListByOrderIds(1L).size());
	}
	
	/**
	 * 测试
	 */
	@Test
	public void testQueryYOrder()
	{
		try {
			HttpServletRequest request = new MockHttpServletRequest();
			List<String> list = new ArrayList<>();
			list.add("1");
			Map<String, Object> paramMap = MapUtil.getParamsMap(order);
		    paramMap.put("startRowNum", pageBean.getStartRowNum());
		    paramMap.put("endRowNum", pageBean.getEndRowNum());
		    paramMap.put("list", list);
		    paramMap.put("endPrice", null);
	        paramMap.put("beginPrice", null);
	        orderMapperMock.returns(1).searchOrderCountByOrderIds(paramMap);
	        paramMap.put("start", pageBean.getStartRowNum());
	        paramMap.put("number", pageBean.getEndRowNum());
	        List<Object> lists = new ArrayList<>();
	        lists.add(new Object());
	        orderMapperMock.returns(lists).searchOrderListByOrderIds(paramMap);
			assertEquals(1, orderService.queryYOrder(request, order, pageBean, list).getList().size());
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询某个订单下的所有商品测试
	 */
	@Test
	public void testQueryOrderGoods()
	{
		orderGoodsMapperMock.returns(orderGoodsLists).selectOrderGoodsList(1L);
		assertEquals(1, orderService.queryOrderGoods(1L).size());
	}
	
	/**
	 *  更改订单状态测试
	 */
	@Test
	public void testChangeOrderIds()
	{
	    List<Long> list = new ArrayList<Long>();
	    list.add(1L);
		Long[] orderId = {1L};
	    Map<String, Object> mapStatus = new HashMap<String, Object>();
	    mapStatus.put("list", list);
	    mapStatus.put("orderStatus", "1");
		assertEquals(0, orderService.changeOrderIds(orderId, "1"));
	}
	
	/**
	 *  修改订单金额测试
	 */
	@Test
	public void testModifyOrderPrice()
	{      
		 orderService.modifyOrderPrice(new BigDecimal(1), 1L);
	}
	
	/**
	 * 中断订单测试
	 */
	@Test
	public void testModifyOrderByKey3()
	{
        Order order = new Order();
        order.setOrderId(1L);
        order.setOrderStatus("1");
        order.setOrderCancelRemark("a");
    	orderMapperMock.returns(1).updateByPrimaryKeySelective(order);
		assertEquals(1, orderService.modifyOrderByKey(1L, "1", "a"));
	}
	
	/**
	 * 修改订单的出库状态测试
	 */
	@Test
	public void testUpdateSetCargoStatusByOrderId()
	{
	    Order order = new Order();
	    order.setOrderId(1L);
	    order.setOrderCargoStatus("1");
	    orderMapperMock.returns(1).updateSetCargoStatusByOrderId(order);
		assertEquals(0, orderService.updateSetCargoStatusByOrderId(1L, "1"));
	}
	
	/**
	 * 修改第三方订单的出库状态测试
	 */
	@Test
	public void testUpdateSetCargoStatusByThirdOrderId()
	{
		 Order order = new Order();
	     order.setOrderId(1L);
	     order.setOrderCargoStatus("1");
	     order.setBusinessId(1L);
	     orderMapperMock.returns(1).updateSetCargoStatusByThirdOrderId(order);
		 assertEquals(0, orderService.updateSetCargoStatusByThirdOrderId(1L, "1", 1L));
	}
	
	/**
	 * 批量更改订单状态测试
	 */
	@Test
	public void testUpdateOrderCargoStatusByOrderIds()
	{
		List<Long> list = new ArrayList<>();
		list.add(1L);
		Long [] ids = {1L};
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put("list", list);
	    map.put("orderCargoStatus", "1");
	    orderMapperMock.returns(1).updateSetCargoStatusByOrderIds(map);
		assertEquals(1, orderService.updateOrderCargoStatusByOrderIds(ids, "1"));
	}
	
	/**
	 * 批量更改第三方订单状态测试
	 */
	@Test
	public void testUpdateOrderCargoStatusByThirdOrderIds()
	{
		List<Long> list = new ArrayList<>();
		list.add(1L);
		Long [] ids = {1L};
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("list", list);
	    map.put("orderCargoStatus", "1");
	    map.put("businessId", 1L);
	    orderMapperMock.returns(1).updateSetCargoStatusByThirdOrderIds(map);
		assertEquals(1, orderService.updateOrderCargoStatusByThirdOrderIds(ids, "1", 1L));
	}
	
	/**
	 * 判断订单的出库状态是否符合测试
	 */
	@Test
	public void testjudgeStatus()
	{
		order.setOrderCargoStatus("1");
		orderMapperMock.returns(order).orderDetail(1L);
		assertEquals(1, orderService.judgeStatus("1", 1L));
	}
	
	/**
	 * 根据商品id查询购买商品记录测试
	 */
	@Test
	public void testQueryGoodsProductVoByOrderGoods()
	{
		orderGoodsMapperMock.returns(orderGoodsLists).queryProGoodsInfoCustomer(1L);
		orderGoodsMapperMock.returns(orderGoodsLists).queryProGoodsInfoByCustomerId(null);
		goodsProductServiceMock.returns(new GoodsProductVo()).queryByPrimaryId(1L);
		assertEquals(1, orderService.queryGoodsProductVoByOrderGoods(1L).size());
	}
	
	/**
	 *  根据商品id和客户id查询最近一段时间的订单测试
	 */
	@Test
	public void testSelectGoodsInfoCount()
	{
		Date date = new Date();
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("goodsInfoId", 1L);
	    map.put("starTime", date);
	    map.put("customerId", 1L);
	    Long result = 1L;
	    orderGoodsMapperMock.returns(1L).selectGoodsInfoCount(map);
		assertEquals(result, orderService.selectGoodsInfoCount(1L, 1L, date));
	}
	
	/**
	 *  根据参数查询订单列表测试
	 */
	@Test
	public void testSelectByParam()
	{
		orderMapperMock.returns(orderList).selectByParam(new HashMap<String, Object>());
		assertEquals(1, orderService.selectByParam(new HashMap<String, Object>()).size());
	}
	
	/**
	 * 从快递100接口获取物流信息html链接测试
	 */
	@Test
	public void testQueryExpressInfoUrl()
	{
		Express express = new Express();
		express.setKudi100code("1");
		OrderContainerRelation orderContainerRelation = new OrderContainerRelation();
		orderContainerRelation.setOrderId(1L);
		orderContainerRelation.setOrderExpressId(1L);
		orderMapperMock.returns(order).orderDetail(1L);
		expressInfoMapperMock.returns(express).selectByshoreExpId(1L);
		assertEquals("http://www.kuaidi100.com/kuaidiresult?id=19276705", orderService.queryExpressInfoUrl(orderContainerRelation));
	}
	
	/**
	 * 插入物流信息(E店宝专用)测试
	 */
	@Test
	public void testAddExpress()
	{
		Gift gift = new Gift();
		gift.setGiftCode("a");
		orderExpressMapperMock.returns(orderExpress).selectOrderExpress(1L);
		orderMapperMock.returns(order).orderDetail(1L);
		orderGoodsMapperMock.returns(orderGoodsLists).selectOrderGoodsList(1L);
		goodsProductServiceMock.returns(new GoodsProductDetailViewVo()).queryViewVoByProductId(1L);
		orderGoodsInfoMapperMock.returns(orderGoodsInfos).selectGiftByOrderIdInOrder(1L);
		giftServiceMock.returns(gift).selectGiftDetailById(1L);
		orderGoodsInfoMapperMock.returns(orderGoodsInfos).selectGiftByOrderIdInOrder(1L);
		giftServiceMock.returns(gift).selectGiftDetailById(1L);
		
		assertEquals(0, orderService.addExpress("1", 1L));
	}
	
	/**
	 * 从快递100接口获取物流信息html链接测试
	 */
	@Test
	public void testQueryExpressInfoUrl2()
	{
		iLogisticsCompanyBizMock.returns(new LogisticsCompany()).getLogisticsCompanyById(1);
		
		assertEquals("http://www.kuaidi100.com/kuaidiresult?id=10561949", orderService.queryExpressInfoUrl(1, "1"));
	}
	
	/**
	 * 根据时间分组查询每天销售量测试
	 */
	@Test
	public void testQuerySaleCountByDay()
	{
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("startTime", "1");
        paramMap.put("endTime", "1"+" 23:59:59");
        orderMapperMock.returns(orderList).querySaleCountByDay(paramMap);
		assertEquals(1, orderService.querySaleCountByDay("1", "1").size());
	}
	
	/**
	 *  根据时间分组查询每天销售额测试
	 */
	@Test
	public void testquerySaleMoneyByDay()
	{
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("startTime", "1");
        paramMap.put("endTime", "1"+" 23:59:59");
        orderMapperMock.returns(orderList).querySaleMoneyByDay(paramMap);
		assertEquals(1, orderService.querySaleMoneyByDay("1", "1").size());
	}
	
	/**
	 * 返回订单列表总数量 积分订单列表 退单列表总数量测试
	 */
	@Test
	public void testGetIndexOrderCount()
	{
		assertEquals(5, orderService.getIndexOrderCount().size());
	}
	
	/**
	 * 删除订单测试
	 */
	@Test
	public void testdeleteOrderById()
	{
		orderMapperMock.returns(1).deleteOrderById(1L);
		assertEquals(1, orderService.deleteOrderById(1L));
	}
	
	/**
	 * 查询第三方订单
	 */
	@Test
	public void testSearchThirdOrderList()
	{
		Map<String, Object> paramMap = MapUtil.getParamsMap(order);
		orderMapperMock.returns(1).searchThirdOrderCount(paramMap);
        paramMap.put("startRowNum", pageBean.getStartRowNum());
        paramMap.put("endRowNum", pageBean.getEndRowNum());
        paramMap.put("start", pageBean.getStartRowNum());
        paramMap.put("number", pageBean.getEndRowNum());
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        orderMapperMock.returns(list).searchThirdOrderList(paramMap);
		assertEquals(1, orderService.searchThirdOrderList(order, pageBean).getList().size());
	}
	
	/**
	 * 定时器，定时去掉一定时间内未付款的订单测试
	 */
	@Test
	public void testCancelOrderByTime()
	{
		orderService.cancelOrderByTime();
	}
	
	/**
	 * 根据总订单号查询订单信息测试
	 */
	@Test
	public void testGetPayOrderByOldCode()
	{
		orderMapperMock.returns(orderList).getPayOrderByOldCode("a");
		assertEquals(1, orderService.getPayOrderByOldCode("a").size());
	}
	
	/**
	 * 更新订单货品表 标记为退单货品测试
	 */
	@Test
	public void testUpdateOrderGoodsBack()
	{
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("goodsInfoId", 1L);
        param.put("orderId", 1L);
        param.put("backOrderCode", "1");
        orderGoodsMapperMock.returns(1).updateOrderGoodsBack(param);
		assertEquals(1, orderService.updateOrderGoodsBack(1L, 1L, "1"));
	}
	
	/**
	 * 根据订单id更改订单状态测试
	 */
	@Test
	public void testUpdateStatusBackById()
	{
	     BigDecimal a = new BigDecimal(1);
	     Map<String, Object> param = new HashMap<String, Object>();
         param.put("orderId", 1L);
         param.put("orderStatus", "1");
         param.put("backPrice",a);
         orderMapperMock.returns(1).updateStatusBackById(param);
         assertEquals(1, orderService.updateStatusBackById(1L, "1", a));
	}
	
	/**
	 * 查询退货商品信息（根据orderId和退单编号查询）测试
	 */
	@Test
	public void testQueryOrderGoodsByOrderIdAndBackCode()
	{
		
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put("orderId", 1L);
	    map.put("backOrderCode", "1");
	    orderGoodsMapperMock.returns(orderGoodsLists).queryOrderGoodsByOrderIdAndBackCode(map);
		assertEquals(1, orderService.queryOrderGoodsByOrderIdAndBackCode(1L,"1").size());
	}
	
	/**
	 * 查询首页统计测试
	 */
	@Test
	public void testQueryStatistics()
	{
		assertEquals(3, orderService.queryStatistics().size());
	}
	
	/**
	 * 前台 货品发送后自动收货测试
	 */
	@Test
	public void testReceiptConfirmation()
	{
		orderService.receiptConfirmation();
	}
	
	/**
	 * 获取订单详细测试
	 */
	@Test
	public void testAjaxGetorderDetail()
	{
		HttpServletRequest request = new MockHttpServletRequest();
		Long[] goodsNum = {1L};
		Long[] goodsIdP = {1L};
		assertEquals(1, orderService.ajaxGetorderDetail(request, goodsNum, goodsIdP, "a").size());
	}
	
	@Test
	public void testSaveAddOrder()
	{
		order = new Order();
		BigDecimal bigDecimal = new BigDecimal(1);
		order.setCustomerRemark("a");
		order.setInvoiceType("1");
		order.setInvoiceTitle("a");
		order.setInvoiceContent("a");
		order.setOrderOldPrice(bigDecimal);
		order.setExpressPrice(bigDecimal);
		ProductWare productWare = new ProductWare();
		productWare.setWareStock(2L);
		Long[] goodsIdP ={1L};
		Long[] goodsNum = {1L};
		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setAddressId(1L);
		ProvinceBean province = new ProvinceBean();
		province.setProvinceName("a");
		customerAddress.setProvince(province);
		customerAddress.setCity(new CityBean());
		customerAddress.setDistrict(new DistrictBean());
        Map<String, Object> goodsIds = new HashMap<>();
        goodsIds.put("productIds", goodsIdP);
        goodsProductMapperMock.returns(goodsProductVos).queryGoodsProductVoByProductIds(goodsIds);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("freightIsDefault", "1");
        paramMap.put("freightThirdId", 0);
        freightTemplateMapperMock.returns(new FreightTemplate()).selectFreightTemplateSubOrder(paramMap);
        productWareServiceMock.returns(productWare).queryProductWareByProductIdAndDistinctId(1L, 1L);
        customerAddressMapperMock.returns(customerAddress).selectByaddressId(1L);
        orderMapperMock.returns(1).insertOrder(order);
        assertEquals(5, orderService.saveAddOrder(bigDecimal, bigDecimal, "1_a", "a", goodsIdP, goodsNum, 1L, 1L, "a", "1", "a", customerAddress,"0"));
	}
	
	/**
	 * 查询所有订单信息 此方法供后台订单列表中的导出订单列表功能使用测试
	 */
	@Test
	public void testQueryAllOrderList()
	{
		orderMapperMock.returns(orderList).queryAllOrderList();
		assertEquals(1, orderService.queryAllOrderList().size());
	}
	
	/**
	 *  新查询第三方订单测试
	 */
	@Test
	public void testNewsearchThirdOrderList()
	{
		assertEquals(1, orderService.newsearchThirdOrderList("2", order, pageBean).size());
	}
}
