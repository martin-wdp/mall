package com.junit.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.system.bean.Pay;
import com.ningpai.system.dao.PayMapper;
import com.ningpai.system.service.PayService;
import com.ningpai.system.service.impl.PayServiceImpl;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 支付方式设置服务层接口 测类
 * @author djk
 *
 */
public class PayServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private PayService payService = new PayServiceImpl();
	
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<PayMapper> payMapperMock;
	
	/**
	 * 分页辅助类
	 */
	private PageBean pageBean;
	
	/**
	 * 分页参数Bean
	 */
	private SelectBean selectBean;
	
	/**
	 * 参数
	 */
	private Map<String, Object> map;
	
	/**
	 * 返回结果
	 */
	private List<Object> lists;
	
	@Override
	protected void setUp() throws Exception 
	{
		pageBean = new PageBean();
		selectBean = new SelectBean();
		lists = new ArrayList<>();
		lists.add(new Object());
		map = new HashMap<String, Object>();
		map.put("startRowNum", pageBean.getStartRowNum());
		map.put("endRowNum", pageBean.getEndRowNum());
		map.put("condition", selectBean.getCondition());
		map.put("searchText", selectBean.getSearchText());
	}
	
	/**
	 * 分页查询支付方式测试
	 */
	@Test
	public void testFindByPageBean()
	{
		payMapperMock.returns(1).findTotalCount(selectBean);
		payMapperMock.returns(lists).findByPageBean(map);
		assertEquals(1, payService.findByPageBean(pageBean, selectBean).getList().size());
	}
	
	/**
	 * 分页查询支付方式为微信支付的数据测试
	 */
	@Test
	public void testFindPayByPayType()
	{
		payMapperMock.returns(1).findTotalCountByPayType(selectBean);
		payMapperMock.returns(lists).findPayByPayType(map);
		assertEquals(1, payService.findPayByPayType(pageBean, selectBean).getList().size());
	}
	
	/**
	 * 查询所有的支付方式测试
	 */
	@Test
	public void testQueryAllPaySet()
	{
		Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("startRowNum", 0);
        maps.put("endRowNum", 0);
        maps.put("condition", -1);
		List<Object> lists = new ArrayList<>();
		lists.add(new Object());
		payMapperMock.returns(lists).findByPageBean(maps);
		assertEquals(1, payService.queryAllPaySet().size());
	}
	
	/**
	 *  添加支付方式测试
	 */
	@Test
	public void testInsertPay()
	{
		Pay pay1 = new Pay();
		
		Pay pay = new Pay();
		pay.setPayDefault("1");
		payMapperMock.returns(pay1).selectByDefault("1");
		payMapperMock.returns(1).updateByPrimaryKeySelective(pay1);
		payMapperMock.returns(1).insertSelective(pay);
		assertEquals(1, payService.insertPay(pay));
	}
	
	/**
	 * 删除支付方式测试
	 */
	@Test
	public void testDeletePay()
	{
		String [] ids = {"1"};
		payMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, payService.deletePay(ids));
	}
	
	/**
	 * 根据payId查询支付信息测试
	 */
	@Test
	public void testFindByPayId()
	{
		payMapperMock.returns(new Pay()).selectByPrimaryKey(1L);
		assertNotNull(payService.findByPayId(1L));
	}

	/**
	 * 修改支付设置信息测试
	 */
	@Test
	public void testUpdatePay()
	{
		Pay pay1 = new Pay();
		Pay pay = new Pay();
		pay.setPayDefault("1");
		payMapperMock.returns(pay1).selectByDefault("1");
		payMapperMock.returns(1).updateByPrimaryKeySelective(pay1);
		payMapperMock.returns(1).updateByPrimaryKeySelective(pay);
		assertEquals(1, payService.updatePay(pay));
	}
	
	/**
	 * 已启用改为不启用，不启用改为已启用测试
	 */
	@Test
	public void testUpdateUserdStatus()
	{
		Pay pay = new Pay();
		pay.setIsOpen("0");
		payMapperMock.returns(pay).selectByPrimaryKey(1L);
		payMapperMock.returns(1).updateByPrimaryKeySelective(pay);
		assertEquals(true, payService.updateUserdStatus(1L));
		assertEquals("1", pay.getIsOpen());
	}
	
	
	/**
	 * 其他支付接口设置取消默认测试
	 */
	@Test
	public void testChangeDefault()
	{
		payMapperMock.returns(1).changeDefaultToNO(1L);
		payMapperMock.returns(new Pay()).selectByPrimaryKey(1L);
		payMapperMock.returns(1).updateByPrimaryKeySelective(new Pay());
		assertEquals(true, payService.changeDefault(1L));
	}
	

	/**
	 * 根据主键删除支付接口测试
	 */
	@Test
	public void testdeletePaySetById()
	{
		payService.deletePaySetById(1L);
	}
	
	/**
	 * 修改支付问题描述测试
	 */
	@Test
	public void testUpdatePayHelp()
	{
		payMapperMock.returns(1).upadtePayHelp(new Pay());
		assertEquals(1, payService.updatePayHelp(new Pay()));
	}
}
