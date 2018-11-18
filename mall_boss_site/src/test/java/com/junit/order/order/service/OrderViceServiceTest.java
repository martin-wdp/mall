package com.junit.order.order.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.goods.vo.GoodsProductVo;
import com.ningpai.order.bean.OrderVice;
import com.ningpai.order.dao.OrderViceMapper;
import com.ningpai.order.service.OrderViceService;
import com.ningpai.order.service.impl.OrderViceServiceImpl;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;

/**
 * 
 * @author djk
 *
 */
public class OrderViceServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	@TestedObject
	private OrderViceService orderViceService = new OrderViceServiceImpl();
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<GoodsProductService> goodsProductServiceMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderViceMapper> orderViceMapperMock;
	
	/**
	 * 抢购团购订单表
	 */
	private OrderVice orderVice = new OrderVice();
	
	/**
	 * 抢购团购订单列表测试
	 */
	@Test
	public void testSearchOrderList()
	{
		orderVice.setCreateTime(new Date());
		orderVice.setSendExpressTime(new Date());
		orderVice.setPayTime(new Date());
		PageBean pageBean = new PageBean();
		Map<String, Object> paramMap = MapUtil.getParamsMap(orderVice);
	    paramMap.put("startRowNum", pageBean.getStartRowNum());
	    paramMap.put("endRowNum", pageBean.getEndRowNum());
	    orderViceMapperMock.returns(1).searchOrderViceCount(paramMap);
	    paramMap.put("start", pageBean.getStartRowNum());
        paramMap.put("number", pageBean.getEndRowNum());
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        orderViceMapperMock.returns(list).searchOrderViceList(paramMap);
		assertEquals(1, orderViceService.searchOrderList(orderVice, pageBean).getList().size());
	}
	
	/**
	 * 订单详情测试
	 */
	@Test
	public void testSelectDetails()
	{
		orderVice.setGoodsInfoId(1L);
		orderViceMapperMock.returns(orderVice).selectByPrimaryKey(1L);
		goodsProductServiceMock.returns(new GoodsProductVo()).queryByPrimaryId(1L);
		assertNotNull(orderViceService.selectDetails(1L));
	}
	
	/**
	 *  插入订单测试
	 */
	@Test
	public void testInsertOrder()
	{
		orderViceMapperMock.returns(1).insertSelective(orderVice);
		assertEquals(1, orderViceService.insertOrder(orderVice));
	}
	
	/**
	 * 根据订单编号查询订单信息测试
	 */
	@Test
	public void testPayOrder()
	{
		orderViceMapperMock.returns(orderVice).selectByOrderCode("a");
		assertNotNull(orderViceService.payOrder("a"));
	}
	
	/**
	 * 查询订单号是否存在测试
	 */
	@Test
	public void testExistOrderCode()
	{
		orderViceMapperMock.returns(1L).existOrderCode("a");
		assertEquals(1, orderViceService.existOrderCode("a"));
	}
	
	/**
	 * 修改订单信息测试
	 */
	@Test
	public void testUpdateOrderVice()
	{
		orderViceMapperMock.returns(1).updateByPrimaryKeySelective(orderVice);
		assertEquals(1, orderViceService.updateOrderVice(orderVice));
	}
	
	/**
	 * 根据id修改订单信息测试
	 */
	@Test
	public void testUpdateOrderViceByOrderId()
	{
		orderViceMapperMock.returns(orderVice).selectByPrimaryKey(1L);
		orderViceMapperMock.returns(1).updateByPrimaryKeySelective(orderVice);
		assertEquals(1, orderViceService.updateOrderViceByOrderId(1L));
	}
	
}
