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

import com.ningpai.system.bean.ErrorPage;
import com.ningpai.system.dao.ErrorPageMapper;
import com.ningpai.system.service.ErrorPageService;
import com.ningpai.system.service.impl.ErrorPageServiceImpl;
import com.ningpai.util.PageBean;

/**
 * 异常页面SERVICE测试类
 * @author djk
 *
 */
public class ErrorPageServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private ErrorPageService errorPageService = new ErrorPageServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<ErrorPageMapper> errorPageMapperMock;
	
	/**
	 * 根据主键删除测试
	 */
	@Test
	public void testDelErrorPage()
	{
		errorPageMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, errorPageService.delErrorPage(1L));
	}
	
	/**
	 * 批量删除测试
	 */
	@Test
	public void testBatchDelErrorPage()
	{
		Long [] ids = {1L};
		errorPageService.batchDelErrorPage(ids);;
	}
	
	/**
	 * 添加异常页面测试
	 */
	@Test
	public void testSaveErrorPage()
	{
		errorPageMapperMock.returns(1).insert(new ErrorPage());
		assertEquals(1, errorPageService.saveErrorPage(new ErrorPage()));
	}
	
	/**
	 * 更新异常页面测试
	 */
	@Test
	public void testUpdateErrorPage()
	{
		errorPageMapperMock.returns(1).updateByPrimaryKeySelective(new ErrorPage());
		assertEquals(1, errorPageService.updateErrorPage(new ErrorPage()));
	}
	
	/**
	 * 根据主键查询异常页面测试
	 */
	@Test
	public void testGetErrorPage()
	{
		errorPageMapperMock.returns(new ErrorPage()).selectByPrimaryKey(1L);
		assertNotNull(errorPageService.getErrorPage(1L));
	}
	
	/**
	 * 根据分页参数查询异常页面列表测试
	 */
	@Test
	public void  testqueryErrorPageByPageBean()
	{
		PageBean pb = new PageBean();
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        errorPageMapperMock.returns(1).queryTotalCount();
        List<Object> lists = new ArrayList<Object>();
        lists.add(new Object());
        errorPageMapperMock.returns(lists).queryByPageBean(map);
		assertEquals(1, errorPageService.queryErrorPageByPageBean(pb).getList().size());
	}
	
}
