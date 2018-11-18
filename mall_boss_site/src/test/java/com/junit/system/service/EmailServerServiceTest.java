package com.junit.system.service;


import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.system.bean.EmailServer;
import com.ningpai.system.dao.EmailServerMapper;
import com.ningpai.system.service.EmailServerService;
import com.ningpai.system.service.impl.EmailServerServiceImpl;

/**
 * 邮箱服务器服务接口层测试
 * @author djk
 *
 */
public class EmailServerServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private EmailServerService emailServerService = new EmailServerServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<EmailServerMapper> emailServerMapperMock;
	
	/**
	 * 查询邮箱服务器信息测试
	 */
	@Test
	public void testFindServer()
	{
		emailServerMapperMock.returns(new EmailServer()).findServer();
		assertNotNull(emailServerService.findServer());
	}
	
	/**
	 * 修改服务器信息测试
	 */
	@Test
	public void testUpdateServer()
	{
		emailServerMapperMock.returns(1).updateByPrimaryKeySelective(new EmailServer());
		assertEquals(1, emailServerService.updateServer(new EmailServer()));
	}
}
