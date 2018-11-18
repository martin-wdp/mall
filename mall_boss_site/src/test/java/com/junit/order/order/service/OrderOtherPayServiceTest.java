package com.junit.order.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.order.bean.OrderOtherPay;
import com.ningpai.order.bean.OrderOtherPaySchedule;
import com.ningpai.order.dao.OrderOtherPayMapper;
import com.ningpai.order.service.OrderOtherPayScheduleService;
import com.ningpai.order.service.OrderOtherPayService;
import com.ningpai.order.service.impl.OrderOtherOtherPayServiceImpl;

/**
 * 代付测试类
 * @author djk
 *
 */
public class OrderOtherPayServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	@TestedObject
	private OrderOtherPayService orderOtherPayService = new OrderOtherOtherPayServiceImpl();
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderOtherPayMapper> orderOtherPayMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderOtherPayScheduleService> orderOtherPayScheduleServiceMock;
	
	/**
	 * 代付实体类
	 */
	private OrderOtherPay orderOtherPay = new OrderOtherPay();
	
	
	/**
	 * 插入代付信息测试
	 */
	@Test
	public void testInsertOtherPay()
	{
		orderOtherPayMapperMock.returns(1).insertSelective(orderOtherPay);
		assertEquals(1, orderOtherPayService.insertOtherPay(orderOtherPay));
	}
	
	/**
	 * 更新代付信息测试
	 */
	@Test
	public void testUpdateOtherPay()
	{
		orderOtherPayMapperMock.returns(1).updateByOrderCodex(orderOtherPay);
		assertEquals(1, orderOtherPayService.updateOtherPay(orderOtherPay));
	}
	
	/**
	 * 查询代付信息测试
	 */
	@Test
	public void testQueryOtherPayByOrderCode()
	{
		orderOtherPayMapperMock.returns(orderOtherPay).queryOrderPayByOrderCode(orderOtherPay);
		assertNotNull(orderOtherPayService.queryOtherPayByOrderCode(orderOtherPay));
	}
	
	/**
	 * 根据代付编号查询代付信息测试
	 */
	@Test
	public void testSelectOthertByOrderPayCode()
	{
		orderOtherPayMapperMock.returns(orderOtherPay).queryOrderByCode("a");
		assertNotNull(orderOtherPayService.selectOthertByOrderPayCode("a"));
	}
	
	/**
	 * 判断是否可以支付测试
	 */
	@Test
	public void testIsNoPay()
	{
		OrderOtherPaySchedule orderOtherPaySchedule = new OrderOtherPaySchedule();
		orderOtherPaySchedule.setOrderPayPrice(new BigDecimal(1));
		orderOtherPaySchedule.setOrderResiduePrice(new BigDecimal(1));
		List<OrderOtherPay> lists = new ArrayList<>();
		orderOtherPay.setOrderCode("a");
		orderOtherPay.setOrderPayPrice(new BigDecimal(1));
		lists.add(orderOtherPay);
		Boolean result = true;
		orderOtherPayMapperMock.returns(lists).queryOrderPayBylately("a");
		orderOtherPayScheduleServiceMock.returns(orderOtherPaySchedule).selectOrderOtherPayScheduleByOrderCode("a");
		assertEquals(result, orderOtherPayService.isNoPay(orderOtherPay));
	}
	
	/**
	 * 根据订单编号查询成功的代付信息测试
	 */
	@Test
	public void testQueryOrderPayBySuccess()
	{
		List<OrderOtherPay> list = new ArrayList<>();
		list.add(orderOtherPay);
		orderOtherPayMapperMock.returns(list).queryOrderPayBySuccess("a");
		assertEquals(1, orderOtherPayService.queryOrderPayBySuccess("a").size());
	}
	
	/**
	 * 查询需要退款的付款信息
	 */
	@Test
	public void testQueryOrderPayRefund()
	{
		List<OrderOtherPay> list = new ArrayList<>();
		list.add(orderOtherPay);
		orderOtherPayMapperMock.returns(list).queryOrderPayRefund("a");
		assertEquals(1, orderOtherPayService.queryOrderPayRefund("a").size());
	}
}
