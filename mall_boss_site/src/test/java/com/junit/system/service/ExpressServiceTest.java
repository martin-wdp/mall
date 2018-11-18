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

import com.ningpai.system.bean.Express;
import com.ningpai.system.dao.ExpressMapper;
import com.ningpai.system.service.ExpressService;
import com.ningpai.system.service.impl.ExpressServiceImpl;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 配送方式服务层接口测试类
 * @author djk
 *
 */
public class ExpressServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private ExpressService expressService = new ExpressServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<ExpressMapper> expressMapperMock;
	
	/**
	 * 分页查询配送方式信息测试
	 */
	@Test
	public void testFindByPageBean()
	{
		PageBean pageBean = new PageBean();
		SelectBean selectBean = new SelectBean();
		
	    Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        map.put("condition", selectBean.getCondition());
        map.put("searchText", selectBean.getSearchText());
        expressMapperMock.returns(1).findTotalCount(selectBean);
        List<Object> list = new ArrayList<Object>();
        list.add(new Object());
        expressMapperMock.returns(list).findByPageBean(map);
		assertEquals(1, expressService.findByPageBean(pageBean, selectBean).getList().size());
	}
	
	/**
	 * 添加配送测试
	 */
	@Test
	public void testInsertExpress()
	{
		expressMapperMock.returns(1).insertSelective(new Express());
		assertEquals(1, expressService.insertExpress(new Express()));
	}
	
	/**
	 * 删除配送信息测试
	 */
	@Test
	public void testDeleteExpress()
	{
		String [] ids = {"1"};
		expressMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, expressService.deleteExpress(ids));
	}
	
	/**
	 * 按expid查询配送信息测试
	 */
	@Test
	public void testFindByExpid()
	{
		expressMapperMock.returns(new Express()).selectByPrimaryKey(1L);
		assertNotNull(expressService.findByExpid(1L));
	}
	
	/**
	 * 修改配送信息测试
	 */
	@Test
	public void testUpdateExpress()
	{
		expressMapperMock.returns(1).updateByPrimaryKeySelective(new Express());
		assertEquals(1, expressService.updateExpress(new Express()));
	}
	
}
