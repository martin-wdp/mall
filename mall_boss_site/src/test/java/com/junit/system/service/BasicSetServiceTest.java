package com.junit.system.service;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.system.bean.BasicSet;
import com.ningpai.system.dao.BasicSetMapper;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.system.service.impl.BasicSetServiceImpl;

/**
 *  站点设置服务层接口测试类
 * @author djk
 *
 */
public class BasicSetServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private BasicSetService basicSetService = new BasicSetServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<BasicSetMapper> basicSetMapperMock;
	
	/**
	 * 测试 查询站点信息
	 */
	@Test
	public void testFindBasicSet()
	{
		basicSetMapperMock.returns(new BasicSet()).findBasicSet();
		assertNotNull(basicSetService.findBasicSet());
	}
	
	/**
	 * 测试修改配置
	 */
	@Test
	public void testUpdateBasicSet()
	{
		basicSetMapperMock.returns(1).updateByPrimaryKeySelective(new BasicSet());
		assertEquals(1, basicSetService.updateBasicSet(new BasicSet()));
	}
}
