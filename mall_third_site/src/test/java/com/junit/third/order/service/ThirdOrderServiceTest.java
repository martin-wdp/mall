package com.junit.third.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.customer.bean.Customer;
import com.ningpai.order.bean.Order;
import com.ningpai.order.service.OrderService;
import com.ningpai.third.order.mapper.ThirdOrderMapper;
import com.ningpai.third.order.service.ThirdOrderService;
import com.ningpai.third.order.service.impl.ThirdOrderServiceImpl;
import com.ningpai.util.DaysOrderUtil;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;

/**
 * 第三方订单servcie测试
 * @author djk
 *
 */
public class ThirdOrderServiceTest extends UnitilsJUnit3
{
	  /**
	   * 需要测试的类
	   */
	  @TestedObject
	  private ThirdOrderService thirdOrderService = new ThirdOrderServiceImpl();
	  
	  /**
	   * 模拟
	   */
	  @InjectIntoByType
	  private Mock<ThirdOrderMapper> thirdOrderMapperMock;
	
	  /**
	   * 模拟
	   */
	  @InjectIntoByType
	  private Mock<OrderService> orderServiceMock;
	  
	  /**
	   *  分页辅助类
	   */
	  private PageBean pageBean = new PageBean();
	  
	  /**
	   * 订单主表
	   */
	  private Order order = new Order();
	  
	  /**
	   * 分页查询第三方所有的订单测试
	   */
	  @Test
	  public void testSearchOrderList()
	  {
	      Map<String, Object> paramMap = MapUtil.getParamsMap(order);
	      thirdOrderMapperMock.returns(1).searchThridOrderCountRow(paramMap);
	      paramMap.put("start", pageBean.getStartRowNum());
	      paramMap.put("number", pageBean.getEndRowNum());
	      List<Object> orders = new ArrayList<>();
	      orders.add(new Object());
	      thirdOrderMapperMock.returns(orders).searchThridOrderList(paramMap);
		  assertEquals(1, thirdOrderService.searchOrderList(pageBean, order).getList().size());
	  }
	  
	  /**
	   *  根据id查询订单测试
	   */
	  @Test
	  public void testSearcharOrderByParam()
	  {
		  thirdOrderMapperMock.returns(order).searcharOrderByParam(1L);
		  assertNotNull(thirdOrderService.searcharOrderByParam(1L));
	  }
	  
	  /**
	   * 修改订单信息测试
	   */
	  @Test
	  public void testUpdateThirdOrderByParam()
	  {
		  thirdOrderService.updateThirdOrderByParam(order);
	  }
	  /**
	   * 修改第三方订单信息（加thirdId）
	   */
	  @Test
	  public void testUpdateThirdOrder()
	  {
		  thirdOrderService.updateThirdOrder(order);
	  }
	  
	  /**
	   * 批量修改订单状态为
	   */
	  @Test
	  public void testUpdateThirdOrderByParams()
	  {
		  Long [] orderId = {1L};
		  thirdOrderService.updateThirdOrderByParams(orderId);
	  }
	  
	  /**
	   * 根据订单状态和第三方ID查询订单数量测试
	   */
	  @Test
	  public void testQueryOrderCountByStaAndThirdId()
	  {
		  Map<String, Object> map = new HashMap<String, Object>();
          map.put("status", "1");
          map.put("thirdId", 1L);
          thirdOrderMapperMock.returns(1).queryOrderCountBySta(map);
		  assertEquals(1, thirdOrderService.queryOrderCountByStaAndThirdId("1", 1L));
	  }
	  
	  /**
	   * 根据第三方ID和标记查询订单数量和订单的总金额测试
	   */
	  @Test
	  public void testQuerySalesOrderCountByFlag()
	  {
	      Map<String, Object> map = new HashMap<String, Object>();
	      thirdOrderMapperMock.returns(new DaysOrderUtil()).queryTodaySalesOrderCount(map);
		  assertNotNull(thirdOrderService.querySalesOrderCountByFlag(1, 1L));
	  }
	  
	  /**
	   * 查询出库列表测试
	   */
	  @Test
	  public void testSearchOrderListByOrderCargo()
	  {
		   Map<String, Object> paramMap = MapUtil.getParamsMap(order);
		   thirdOrderMapperMock.returns(1).searchThridOrderCountRow(paramMap);
		   paramMap.put("start", pageBean.getStartRowNum());
		   paramMap.put("number", pageBean.getEndRowNum());
		   List<Object> orders = new ArrayList<>();
		   orders.add(new Object());
		   thirdOrderMapperMock.returns(orders).searchThridOrderList(paramMap);
		   assertEquals(1, thirdOrderService.searchOrderListByOrderCargo(pageBean, order).getList().size());
	  }
	  
	  /**
	   * 拣货准备测试
	   */
	  @Test
	  public void testOrderPicking()
	  {
		  MockHttpServletRequest request = new MockHttpServletRequest();
		  HttpSession session = new MockHttpSession();
		  session.setAttribute("cust", new Customer());
		  request.setSession(session);
		  Long [] orderId = {1L};
		  Map<String, Object> map = new HashMap<>();
		  orderServiceMock.returns(map).queryByPincking(orderId, null, null, null);
		  assertEquals(0, thirdOrderService.orderPicking(orderId, request).size());
	  }
	  
	  /**
	   * 捡货测试
	   */
	  @Test
	  public void testGoOrderPicking()
	  {
		  Long [] orderId = {1L};
		  orderServiceMock.returns(1).updateOrderCargoStatusByThirdOrderIds(orderId, "1", null);
		  assertEquals(1, thirdOrderService.goOrderPicking(orderId, new MockHttpServletRequest()));
	  }
	  
	  /**
	   * 根据订单状态和customerID查询订单数量测试
	   */
	  @Test
	  public void testQueryOrderCountByStaAndCustomerId()
	  {
	      Map<String, Object> map = new HashMap<String, Object>();
          map.put("status", "1");
          map.put("customerId", 1L);
          thirdOrderMapperMock.returns(1).queryCustomerOrderCountBySta(map);
		  assertEquals(1, thirdOrderService.queryOrderCountByStaAndCustomerId("1", 1L));
	  }
	  
	  /**
	   * 分页查询第三方所有的订单测试
	   */
	  @Test
	  public void testSearchBuyOrderList()
	  {
		  Order order = new Order();
	      Map<String, Object> paramMap = MapUtil.getParamsMap(order);
	      thirdOrderMapperMock.returns(1).searchPuyThridOrderCountRow(paramMap);
	      paramMap.put("start", pageBean.getStartRowNum());
          paramMap.put("number", pageBean.getEndRowNum());
          List<Object> list = new ArrayList<>();
          list.add(new Object());
          thirdOrderMapperMock.returns(list).searchPuyThridOrderList(paramMap);
		  assertEquals(1, thirdOrderService.searchBuyOrderList(pageBean, order).getList().size());
	  }
}
