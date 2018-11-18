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

import com.ningpai.system.bean.MessageServer;
import com.ningpai.system.dao.MessageServerMapper;
import com.ningpai.system.service.MessageServerService;
import com.ningpai.system.service.impl.MessageServerServiceImpl;
import com.ningpai.util.PageBean;

/**
 * 短信服务器服务接口层测试
 * @author djk
 *
 */
public class MessageServerServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private MessageServerService messageServerService = new MessageServerServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<MessageServerMapper> messageServerMapperMock;
	
	/**
	 * 查询短信接口信息测试
	 */
	@Test
	public void testFindMessage()
	{
		messageServerMapperMock.returns(new MessageServer()).findMessage();
		assertNotNull(messageServerService.findMessage());
	}
	
	/**
	 * 根据ID查询短信接口测试
	 */
	@Test
	public void testGetMessage()
	{
		messageServerMapperMock.returns(new MessageServer()).selectByPrimaryKey(1L);
		assertNotNull(messageServerService.getMessage(1L));
	}
	
	/**
	 * 修改短信接口信息测试
	 */
	@Test
	public void testUpdateMessage()
	{
		messageServerMapperMock.returns(1).updateByPrimaryKeySelective(new MessageServer());
		assertEquals(1, messageServerService.updateMessage(new MessageServer()));
	}
	
	/**
	 * 分页查询所有短信接口
	 */
	@Test
	public void testSelectAllByPb()
	{
		PageBean pb = new PageBean();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        messageServerMapperMock.returns(1).selectAllCount();
        List<Object> lists = new ArrayList<>();
        lists.add(new Object());
        messageServerMapperMock.returns(lists).selectAllByPb(map);
		assertEquals(1, messageServerService.selectAllByPb(pb).getList().size());
	}
	
	/**
	 * 修改启用状态测试
	 */
	@Test
	public void testUpdateSmsUsedStatus()
	{
		messageServerMapperMock.returns(1).closeAll();
		messageServerMapperMock.returns(new MessageServer()).selectByPrimaryKey(1L);
		messageServerMapperMock.returns(1).updateByPrimaryKeySelective(new MessageServer());
		assertEquals(1, messageServerService.updateSmsUsedStatus(1L));
	}
}
