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

import com.ningpai.system.bean.LogisticsSingle;
import com.ningpai.system.dao.LogisticsSingleMapper;
import com.ningpai.system.service.LogisticsSingleService;
import com.ningpai.system.service.impl.LogisticsSingleServiceImpl;
import com.ningpai.util.PageBean;

/**
 *  物流单模板Service测试类
 * @author djk
 *
 */
public class LogisticsSingleServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private LogisticsSingleService logisticsSingleService = new LogisticsSingleServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<LogisticsSingleMapper> logisticsSingleMapperMock;
	
	/**
	 * 获得物流单模板列表测试
	 */
	@Test
	public void testGetLogisticsSingleList()
	{
		PageBean pageBean = new PageBean();
		
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thirdId", 1L);
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        logisticsSingleMapperMock.returns(1).selectLogisticsSingleCount(map);
        List<Object> lists = new ArrayList<>();
        lists.add(new Object());
        logisticsSingleMapperMock.returns(lists).selectAll(map);
		assertEquals(1, logisticsSingleService.getLogisticsSingleList(pageBean, 1L).getList().size());
	}
	
	/**
	 *  添加物流单模板信息测试
	 */
	@Test
	public void testAddLogisticsSingle()
	{
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thirdId", 1L);
        map.put("companyId", 1L);
        logisticsSingleMapperMock.returns(null).selectLogisticsSingle(map);
        logisticsSingleMapperMock.returns(1).addLogisticsSingle(new LogisticsSingle());
		assertEquals(1, logisticsSingleService.addLogisticsSingle(new LogisticsSingle()));
	}
	
	/**
	 * 根据条件查询模板信息测试
	 */
	@Test
	public void testSelectLogisticsSingle()
	{
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thirdId", 1L);
        map.put("companyId", 1L);
        logisticsSingleMapperMock.returns(new LogisticsSingle()).selectLogisticsSingle(map);
		assertNotNull(logisticsSingleService.selectLogisticsSingle(1L, 1L));
	}
	
	/**
	 * 删除物流单模板测试
	 */
	@Test
	public void testDelLogisticsSingleById()
	{
		logisticsSingleMapperMock.returns(1).delLogisticsSingleById(1L);
		assertEquals(1, logisticsSingleService.delLogisticsSingleById(1L));
	}
	
	/**
	 * 删除物流单模板测试
	 */
	@Test
	public void testDelLogisticsSingle()
	{
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("logisticsSingleId", 1L);
        map.put("thirdId", 1L);
        logisticsSingleMapperMock.returns(1).delLogisticsSingle(map);
		assertEquals(1, logisticsSingleService.delLogisticsSingle(1L, 1L));
	}
	
	/**
	 *  根据Id 查询物流单模板信息测试
	 */
	@Test
	public void testSelectLogisticsSingleById()
	{
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("logisticsSingleId", 1L);
        map.put("thirdId", 1L);
        logisticsSingleMapperMock.returns(new LogisticsSingle()).selectLogisticsSingle(map);
		assertNotNull(logisticsSingleService.selectLogisticsSingleById(1L, 1L));
	}
	
	/**
	 * 修改物流单模板信息测试
	 */
	@Test
	public void testUpdateLogisticsSingle()
	{
		logisticsSingleMapperMock.returns(1).updateLogisticsSingle(new LogisticsSingle());
		assertEquals(1, logisticsSingleService.updateLogisticsSingle(new LogisticsSingle()));
	}
	
}
