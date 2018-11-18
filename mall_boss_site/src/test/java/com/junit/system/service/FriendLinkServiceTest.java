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

import com.ningpai.system.bean.FriendLink;
import com.ningpai.system.dao.FriendLinkMapper;
import com.ningpai.system.service.FriendLinkService;
import com.ningpai.system.service.impl.FriendLinkServiceImpl;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 友情链接服务层接口测试类
 * @author djk
 *
 */
public class FriendLinkServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private FriendLinkService friendLinkService = new FriendLinkServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<FriendLinkMapper> friendLinkMapperMock;
	
	/**
	 * 分页查询友情链接信息测试
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
        map.put("searchTextTwo", selectBean.getSearchTextTwo());
        friendLinkMapperMock.returns(1).findTotalCount(selectBean);
        List<Object> lists = new ArrayList<Object>();
        lists.add(new Object());
        friendLinkMapperMock.returns(lists).findByPageBean(map);
		assertEquals(1, friendLinkService.findByPageBean(pageBean, selectBean).getList().size());
	}
	
	/**
	 * 添加友情链接
	 */
	@Test
	public void testInsertLink()
	{
		friendLinkMapperMock.returns(1).insertSelective(new FriendLink());
		assertEquals(1, friendLinkService.insertLink(new FriendLink()));
	}
	
	/**
	 *  删除友情链接测试
	 */
	@Test
	public void testDeleteLink()
	{
		String [] ids = {"1"};
		friendLinkMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, friendLinkService.deleteLink(ids));
	}
	
	/**
	 * 查询单条友情链接信息测试
	 */
	@Test
	public void testFindByLinkId()
	{
		friendLinkMapperMock.returns(new FriendLink()).selectByPrimaryKey(1L);
		assertNotNull(friendLinkService.findByLinkId(1L));
	}
	
	/**
	 *  修改友情链接信息测试
	 */
	@Test
	public void testUpdateLink()
	{
		friendLinkMapperMock.returns(1).updateByPrimaryKeySelective(new FriendLink());	
		assertEquals(1, friendLinkService.updateLink(new FriendLink()));
	}

	/**
	 * 查询所有友情链接
	 */
	@Test
	public void testselectAllFriendLink()
	{
		List<FriendLink> lists = new ArrayList<>();
		lists.add(new FriendLink());
		friendLinkMapperMock.returns(lists).selectAllFriendLink();
		assertEquals(1, friendLinkService.selectAllFriendLink().size());
	}
}
