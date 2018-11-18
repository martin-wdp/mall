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

import com.ningpai.system.bean.Receivables;
import com.ningpai.system.dao.ReceivablesMapper;
import com.ningpai.system.service.ReceivablesService;
import com.ningpai.system.service.impl.ReceivablesServiceImpl;
import com.ningpai.util.PageBean;

/**
 * 收款单逻辑实现接口测试
 * @author djk
 *
 */
public class ReceivablesServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private ReceivablesService receivablesService = new ReceivablesServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<ReceivablesMapper> receivablesMapperMock;
	
	/**
	 * 结果实体
	 */
	private  List<Object> list;
	
	@Override
	protected void setUp() throws Exception 
	{
		list = new ArrayList<>();
		list.add(new Object());
	}
	
	/**
	 * 添加收款单信息测试
	 */
	@Test
	public void testAddReceivables()
	{
		receivablesMapperMock.returns(1).addReceivables(new Receivables());
		assertEquals(1, receivablesService.addReceivables(new Receivables()));
	}
	
	/**
	 * 分页按条件查询收款单信息测试
	 */
	@Test
	public void testQueryReceivableByPageBean()
	{
		receivablesMapperMock.returns(list).queryReceivableByPageBean(new HashMap<String, Object>());
		assertEquals(1, receivablesService.queryReceivableByPageBean(new HashMap<String, Object>()).size());
	}
	
	/**
	 * 根据参数查询行数测试
	 */
	@Test
	public void testQueryCountByMap()
	{
		receivablesMapperMock.returns(1).queryCountByMap(new HashMap<String,Object>());
		assertEquals(1, receivablesService.queryCountByMap(new HashMap<String,Object>()));
	}
	
	/**
	 * 根据订单编号查询收款单信息测试
	 */
	@Test
	public void testQueryByOrderCode()
	{
		receivablesMapperMock.returns(new Receivables()).queryByOrderCode("a");
		assertNotNull(receivablesService.queryByOrderCode("a"));
	}
	
	/**
	 *  根据订单编号修改订单支付状态为已支付测试
	 */
	@Test
	public void testUpdatePayStatus()
	{
		receivablesMapperMock.returns(1).updatePayStatus(new Receivables());
		assertEquals(1, receivablesService.updatePayStatus(new Receivables()));
	}
	
	/**
	 * 分页查询收款单信息测试
	 */
	@Test
	public void testSearchReceivables()
	{
		PageBean pageBean = new PageBean();
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());

        map.put("condition", "1");
        map.put("searchText", "1");
        map.put("status", "1");
        receivablesMapperMock.returns(1).queryCountByMap(map);
        
        // 查询条件封装
        map.put("start", pageBean.getStartRowNum());
        map.put("number", pageBean.getEndRowNum());

        receivablesMapperMock.returns(list).queryReceivableByPageBean(map);
		assertEquals(1, receivablesService.searchReceivables(pageBean, "1", "1", "1").getList().size());
	}
	
	/**
	 * 根据收款单编号查询收款单详细信息测试
	 */
	@Test
	public void testQueryReceivablesDetail()
	{
		receivablesMapperMock.returns(new Receivables()).queryReceivablesDetail(1L);
		assertNotNull(receivablesService.queryReceivablesDetail(1L));
	}
}
