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

import com.ningpai.system.bean.HelpCate;
import com.ningpai.system.dao.HelpCateMapper;
import com.ningpai.system.dao.HelpCenterMapper;
import com.ningpai.system.service.HelpCateService;
import com.ningpai.system.service.impl.HelpCateServiceImpl;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 帮助分类服务层接口 测试
 * @author djk
 *
 */
public class HelpCateServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private HelpCateService helpCateService = new HelpCateServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<HelpCateMapper> helpCateMapperMock;
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<HelpCenterMapper> helpCenterMapperMock;
	
	/**
	 * 分页查询帮助中心分类测试
	 */
	@Test
	public void testFindByPageBean()
	{
		PageBean pb = new PageBean();
		SelectBean selectBean = new SelectBean();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        map.put("condition", selectBean.getCondition());
        map.put("searchText", selectBean.getSearchText());
        helpCateMapperMock.returns(1).findTotalCount(selectBean);
        List<Object> list  = new ArrayList<>();
        list.add(new Object());
        helpCateMapperMock.returns(list).findByPageBean(map);
		assertEquals(1, helpCateService.findByPageBean(pb, selectBean).getList().size());
	}
	
	/**
	 *  添加帮助分类测试
	 */
	@Test
	public void testInsertHelpCate()
	{
		helpCateMapperMock.returns(1).insertSelective(new HelpCate());
		assertEquals(1, helpCateService.insertHelpCate(new HelpCate()));
	}
	
	/**
	 * 删除帮助分类测试
	 */
	@Test
	public void testDeleteHelpCate()
	{
		String [] ids = {"1"};
		helpCenterMapperMock.returns(1).deleteByHelpCateId(1L);
		helpCateMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(2, helpCateService.deleteHelpCate(ids)); 
	}
	
	/**
	 * 根据Id查询帮助分类
	 */
	@Test
	public void testfindByHelpcateId()
	{
		helpCateMapperMock.returns(new HelpCate()).selectByPrimaryKey(1L);
		assertNotNull(helpCateService.findByHelpcateId(1L));
	}
	
	/**
	 * 修改帮助分类测试
	 */
	@Test
	public void testUpdateHelpCate()
	{
		helpCateMapperMock.returns(1).updateByPrimaryKeySelective(new HelpCate());
		assertEquals(1, helpCateService.updateHelpCate(new HelpCate()));
	}
	
	/**
	 * 查询所有帮助分类测试
	 */
	@Test
	public void testFindAll()
	{
		List<HelpCate> lists = new ArrayList<>();
		lists.add(new HelpCate());
		helpCateMapperMock.returns(lists).findAll();
		assertEquals(1, helpCateService.findAll().size());
	}
}

