package com.junit.system.service;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.system.bean.SystemsSet;
import com.ningpai.system.dao.IsBackOrderMapper;
import com.ningpai.system.service.IsBackOrderService;
import com.ningpai.system.service.impl.IsBackOrderServiceImpl;

/**
 * 是否允许退单测试
 * @author djk
 *
 */
public class IsBackOrderServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private IsBackOrderService isBackOrderService = new IsBackOrderServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<IsBackOrderMapper> isBackOrderMapperMock;
	
	/**
	 *  系统设置实体类
	 */
	private SystemsSet systemsSet = new SystemsSet();
	
	/**
	 * 是否退单测试
	 */
	@Test
	public void testGetIsBackOrder()
	{
		isBackOrderMapperMock.returns(systemsSet).getIsBackOrder();
		assertNotNull(isBackOrderService.getIsBackOrder());
	}
	
	/**
	 * 修改是否允许退单的状态测试
	 */
	@Test
	public void testUpdatesetBackOrder()
	{
		isBackOrderMapperMock.returns(1).updatesetBackOrder(systemsSet);
		assertEquals(1, isBackOrderService.updatesetBackOrder(systemsSet));
	}
	
	/**
	 *  根据ID获取是否退单对象测试
	 */
	@Test
	public void testGetIsBackOrderById()
	{
		isBackOrderMapperMock.returns(systemsSet).getIsBackOrderById(1L);
		assertNotNull(isBackOrderService.getIsBackOrderById(1L));
	}
	
	/**
	 * 设置系统自动更新未付款订单作废时间测试
	 */
	@Test
	public void testUpdatesetOrderTime()
	{
		isBackOrderMapperMock.returns(1).updatesetOrderTime(1L);
		Integer result = 1;
		assertEquals(result, isBackOrderService.updatesetOrderTime(1L));
	}
	
	/**
	 *  新后台跟新退货说明测试
	 */
	@Test
	public void testUpdateBackRemark()
	{
		isBackOrderMapperMock.returns(1).updateBackRemark(systemsSet);
		Integer result = 1;
		assertEquals(result, isBackOrderService.updateBackRemark(systemsSet));
	}
	
	/**
	 *  获取系统设置的未付款订单作废时间测试
	 */
	@Test
	public void testGetTimeFromNpset()
	{
		isBackOrderMapperMock.returns(1L).getTimeFromNpset();
		Long result = 1L;
		assertEquals(result, isBackOrderService.getTimeFromNpset());
	}
	
	/**
	 * 更新退货信息
	 */
	@Test
	public void testUpdateBackInfo()
	{
		isBackOrderMapperMock.returns(1).updateBackInfo("a");
		assertEquals(1, isBackOrderService.updateBackInfo("a"));
	}
	
	/**
	 * 
	 */
	@Test
	public void testQueryBackInfoRemark()
	{
		isBackOrderMapperMock.returns("a").queryBackInfoRemark();
		assertEquals("a", isBackOrderService.queryBackInfoRemark());
	}
}
