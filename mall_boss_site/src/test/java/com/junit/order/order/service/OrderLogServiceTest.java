package com.junit.order.order.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.order.bean.OrderLog;
import com.ningpai.order.dao.OrderLogMapper;
import com.ningpai.order.service.OrderLogService;
import com.ningpai.order.service.impl.OrderLogServiceImpl;


/**
 * 订单记录测试类
 * @author djk
 *
 */
public class OrderLogServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	@TestedObject
	private OrderLogService orderLogService = new OrderLogServiceImpl();
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderLogMapper> orderLogMapperMock;
	
	/**
	 * 根据订单id查询订单下所属的操作记录测试
	 */
	@Test
	public void testSelectOrderLogByOrderId()
	{
		 List<OrderLog>  list = new ArrayList<>();
		 list.add(new OrderLog());
		 orderLogMapperMock.returns(list).selectOrderLogByParam(1L);
		 assertEquals(1, orderLogService.selectOrderLogByOrderId(1L).size());
	}
	
	/**
	 * 插入订单记录测试
	 */
	@Test
	public void testInsertSelective()
	{
		orderLogMapperMock.returns(1).insertSelective(new OrderLog());
		assertEquals(1, orderLogService.insertSelective("a", 1L, "a", "1"));
	}
}
