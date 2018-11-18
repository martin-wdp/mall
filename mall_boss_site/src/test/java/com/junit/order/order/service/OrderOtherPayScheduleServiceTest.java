package com.junit.order.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.order.bean.Order;
import com.ningpai.order.bean.OrderOtherPay;
import com.ningpai.order.bean.OrderOtherPaySchedule;
import com.ningpai.order.dao.OrderOtherPayScheduleMapper;
import com.ningpai.order.service.OrderOtherPayScheduleService;
import com.ningpai.order.service.OrderService;
import com.ningpai.order.service.impl.OrderOtherPayScheduleServiceImpl;

/**
 * 多人代付service测试
 * @author  djk
 *
 */
public class OrderOtherPayScheduleServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	@TestedObject
	private OrderOtherPayScheduleService orderOtherPayScheduleService = new OrderOtherPayScheduleServiceImpl();
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderOtherPayScheduleMapper> orderOtherPayScheduleMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderService> orderServiceMock;
	
	/**
	 * 订单代付实体类
	 */
	private OrderOtherPaySchedule orderOtherPaySchedule = new OrderOtherPaySchedule();
	
	/**
	 * 根据订单编号查询代付信息测试
	 */
	@Test
	public void testSelectOrderOtherPayScheduleByOrderCode()
	{
		orderOtherPayScheduleMapperMock.returns(orderOtherPaySchedule).selectByPrimaryKey("a");
		assertNotNull(orderOtherPayScheduleService.selectOrderOtherPayScheduleByOrderCode("a"));
	}
	
	/**
	 * 插入信息测试
	 */
	@Test
	public void testInsertOrderOtherPaySchedule()
	{
		orderOtherPayScheduleMapperMock.returns(1).insertSelective(orderOtherPaySchedule);
		assertEquals(1, orderOtherPayScheduleService.insertOrderOtherPaySchedule(orderOtherPaySchedule));
	}
	
	/**
	 * 根据订单编号修改代付信息测试
	 */
	@Test
	public void testUpdateOrderOtherPaySchedule()
	{
		orderOtherPayScheduleMapperMock.returns(1).updateByPrimaryKeySelective(orderOtherPaySchedule);
		assertEquals(1, orderOtherPayScheduleService.updateOrderOtherPaySchedule(orderOtherPaySchedule));
	}
	
	/**
	 * 代付付款测试
	 */
	@Test
	public void testPayOther()
	{
		Order order = new Order();
		order.setOrderId(1L);
		OrderOtherPay orderOtherPay = new OrderOtherPay();
		orderOtherPaySchedule.setOrderResiduePrice(new BigDecimal(1));
		orderOtherPay.setOrderPayPrice(new BigDecimal(1));
		orderOtherPayScheduleMapperMock.returns(1).payOther(orderOtherPaySchedule);
		orderOtherPaySchedule.setOrderCode("1");
		orderServiceMock.returns(new Order()).getPayOrderByCode("1");
		orderServiceMock.returns(1).payOrder(1L);
		orderOtherPayScheduleMapperMock.returns(1).updateByPrimaryKeySelective(orderOtherPaySchedule);
		assertEquals(0, orderOtherPayScheduleService.payOther(orderOtherPaySchedule, orderOtherPay));
	}
	
	/**
	 * 查询需要退款的多人代付
	 */
	@Test
	public void testQueryOrderOtherPayRefund()
	{
		List<OrderOtherPaySchedule> list = new ArrayList<>();
		list.add(orderOtherPaySchedule);
		orderOtherPayScheduleMapperMock.returns(list).queryOrderOtherPayRefund();
		assertEquals(1, orderOtherPayScheduleService.queryOrderOtherPayRefund().size());
	}
}
