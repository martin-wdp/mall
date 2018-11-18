package com.junit.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.handler.DispatcherServletWebRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.system.bean.Payment;
import com.ningpai.system.dao.PaymentMapper;
import com.ningpai.system.service.PaymentService;
import com.ningpai.system.service.impl.PaymentServiceImpl;
import com.ningpai.util.PageBean;

/**
 * SERVICE-支付方式测试类
 * @author djk
 *
 */
public class PaymentServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private PaymentService paymentService = new PaymentServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<PaymentMapper> paymentMapperMock; 
	
	/**
	 * 实体类-支付方式
	 */
	private Payment payment = new Payment();
	
	@Override
	protected void setUp() throws Exception 
	{
		RequestContextHolder.setRequestAttributes(new DispatcherServletWebRequest(new MockMultipartHttpServletRequest()));
	}
	
	/**
	 * 根据ID删除支付方式-修改删除标记测试
	 */
	@Test
	public void testDeletePayment()
	{
		paymentMapperMock.returns(payment).selectByPrimaryKey(1L);
		paymentMapperMock.returns(1).updateByPrimaryKeySelective(payment);
		assertEquals(1, paymentService.deletePayment(1L));
	}
	
	/**
	 *  添加支付方式测试
	 */
	@Test
	public void testCreatePayment()
	{
		paymentMapperMock.returns(1).insertSelective(payment);
		assertEquals(1, paymentService.createPayment(payment));
	}
	
	/**
	 * 根据ID查询支付方式
	 */
	@Test
	public void testGetPayment()
	{
		paymentMapperMock.returns(new Payment()).selectByPrimaryKey(1L);
		assertNotNull(paymentService.getPayment(1L));
	}
	
	/**
	 * 修改支付方式测试
	 */
	@Test
	public void testUpdatePayment()
	{
		paymentMapperMock.returns(1).updateByPrimaryKeySelective(payment);
		assertEquals(1, paymentService.updatePayment(payment));
	}
	
	/**
	 *  分页查询支付方式测试 
	 */
	@Test
	public void testSelectAllByPb()
	{
		PageBean pb = new PageBean();
        Map<String, Object> map = new HashMap<String, Object>();
        paymentMapperMock.returns(1).selectAllCount();
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        paymentMapperMock.returns(list).selectAllByPb(map);
		assertEquals(1, paymentService.selectAllByPb(pb).getList().size());
	}
	
	/**
	 * 查询所有前台可展示的支付方式测试
	 */
	@Test
	public void testSelectAllForSite()
	{
		List<Payment>  lists = new ArrayList<>();
		lists.add(new Payment());
		paymentMapperMock.returns(lists).selectAllForSite();
		assertEquals(1, paymentService.selectAllForSite().size());
	}
	
	/**
	 * 修改支付方式启用状态测试
	 */
	@Test
	public void testSetPaymentOpenStatus()
	{
		Payment payment = new Payment();
		payment.setIsOpen("0");
		paymentMapperMock.returns(payment).selectByPrimaryKey(1L);
		paymentMapperMock.returns(1).updateByPrimaryKeySelective(payment);
		assertEquals(true, paymentService.setPaymentOpenStatus(1L));
		assertEquals("1", payment.getIsOpen());
	}
	
	/**
	 * 检查是启用的支付方式的数量，要修改的支付方式为唯一开启的支付方式时也不能关闭测试
	 */
	@Test
	public void testCheckOpenCount()
	{
		Payment payment = new Payment();
		payment.setIsOpen("1");
		paymentMapperMock.returns(payment).selectByPrimaryKey(1L);
		paymentMapperMock.returns(2).selectCountForSite();
		assertEquals(true, paymentService.checkOpenCount(1L));
	}
	
	/**
	 * 检查是否能删除支付方式，有且只有一个支付方式时不能删除。 要删除的支付方式为唯一开启的支付方式时也不能删除测试
	 */
	@Test
	public void testCheckDelete()
	{
		Payment payment = new Payment();
		payment.setIsOpen("1");
		paymentMapperMock.returns(2).selectAllCount();
		paymentMapperMock.returns(payment).selectByPrimaryKey(1L);
		paymentMapperMock.returns(2).selectCountForSite();
		assertEquals(true, paymentService.checkDelete(1L));
	}
}
