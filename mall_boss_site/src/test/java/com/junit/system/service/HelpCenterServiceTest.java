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

import com.ningpai.system.bean.HelpCenter;
import com.ningpai.system.dao.HelpCenterMapper;
import com.ningpai.system.service.HelpCenterService;
import com.ningpai.system.service.impl.HelpCenterServiceImpl;
import com.ningpai.system.util.SelectBean;
import com.ningpai.system.vo.HelpCenterVo;
import com.ningpai.util.PageBean;

/**
 * 帮助中心服务层接口测试类
 * @author djk
 *
 */
public class HelpCenterServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private HelpCenterService helpCenterService = new HelpCenterServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<HelpCenterMapper> helpCenterMapperMock;
	
	/**
	 * 返回的数据
	 */
	private List<Object> list;
	
	/**
	 *  分页辅助类
	 */
	private PageBean pageBean = new PageBean();
	
	/**
	 * 分页参数Bean
	 */
	private SelectBean selectBean = new SelectBean();
	
	/**
	 * 参数map
	 */
	private Map<String, Object> map = new HashMap<>();

	/**
	 * 返回的数据集
	 */
	private List<HelpCenter> helpCenterLists = new ArrayList<>();
	
	@Override
	protected void setUp() throws Exception 
	{
		list = new ArrayList<>();
		list.add(new Object());
		helpCenterLists.add(new HelpCenter());
		
        map.put("condition", selectBean.getCondition());
        map.put("searchText", selectBean.getSearchText());
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
	}
	
	/**
	 * 根据帮助分类名称获取分类ID测试
	 */
	@Test
	public void testSelectCateByHelpname()
	{
		List<HelpCenterVo> lists = new ArrayList<>();
		lists.add(new HelpCenterVo());
		helpCenterMapperMock.returns(lists).selectCateByHelpname("a");
		assertEquals(1, helpCenterService.selectCateByHelpname("a").size());
	}
	
	/**
	 * 添加帮助中心测试
	 */
	@Test
	public void testInsertHelpCenter()
	{
		helpCenterMapperMock.returns(1).insertSelective(new HelpCenter());
		assertEquals(1, helpCenterService.insertHelpCenter(new HelpCenter()));
	}
	
	
	/**
	 * 修改帮助中心信息
	 */
	@Test
	public void testUpdateHelpCenter()
	{
		helpCenterMapperMock.returns(1).updateByPrimaryKeySelective(new HelpCenter());
		assertEquals(1, helpCenterService.updateHelpCenter(new HelpCenter()));
	}
	
	/**
	 * 分页查询帮助中心信息测试
	 */
	@Test
	public void testFindByPageBean()
	{
        helpCenterMapperMock.returns(1).findTotalCount(map);
        helpCenterMapperMock.returns(list).findByPageBean(map);
		assertEquals(1, helpCenterService.findByPageBean(pageBean, selectBean).getList().size());
	}
	
	/**
	 * 分页查询帮助中心信息测试
	 */
	@Test
	public void testFindByPageBeanAndId()
	{
	    map.put("helpcateId", 1L);
	    helpCenterMapperMock.returns(1).findTotalCount(map);
	    helpCenterMapperMock.returns(list).findByPageBean(map);
		assertEquals(1, helpCenterService.findByPageBean(pageBean, selectBean, 1L).getList().size());
	}
	
	/**
	 * 根据helpid查询帮助中心信息测试
	 */
	@Test
	public void testFindByHelpId()
	{
		helpCenterMapperMock.returns(new HelpCenter()).selectByPrimaryKey(1L);
		assertNotNull(helpCenterService.findByHelpId(1L));
	}
	
	/**
	 * 删除帮助中心信息
	 */
	@Test
	public void testDeleteHelpCenter()
	{
		String [] ids = {"1"};
		helpCenterMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, helpCenterService.deleteHelpCenter(ids));
	}
	
	/**
	 * 获取所有是底部的帮助测试
	 */
	@Test
	public void testSelectAllIsFoot()
	{
		helpCenterMapperMock.returns(helpCenterLists).selectAllIsFoot();
		assertEquals(1, helpCenterService.selectAllIsFoot().size());
	}
	
	/**
	 * 获取所有的帮助测试
	 */
	@Test
	public void testSelectAll()
	{
		helpCenterMapperMock.returns(helpCenterLists).selectAll();
		assertEquals(1, helpCenterService.selectAll().size());
	}
	
	/**
	 * 根据帮助类型获取帮助测试
	 */
	@Test
	public void testFindByCateId()
	{
		helpCenterMapperMock.returns(helpCenterLists).findByCateId(1L);
		assertEquals(1, helpCenterService.findByCateId(1L).size());
	}
}
