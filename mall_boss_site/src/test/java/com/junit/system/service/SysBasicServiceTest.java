package com.junit.system.service;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.system.bean.SysBasic;
import com.ningpai.system.dao.SysBasicMapper;
import com.ningpai.system.service.SysBasicService;
import com.ningpai.system.service.impl.SysBasicServiceImpl;

/**
 * SERVICE-后台Logo设置测试
 * @author  djk
 *
 */
public class SysBasicServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private SysBasicService sysBasicService = new SysBasicServiceImpl();
	
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<SysBasicMapper> sysBasicMapperMock;
	
	/**
	 * 查看当前，没有创建一个测试
	 */
	@Test
	public void testGetSysBasic()
	{
		SysBasic sysBasic = new SysBasic();
		sysBasicMapperMock.returns(null).selectCurr();
		sysBasicMapperMock.returns(1).insertSelective(sysBasic);
		assertNotNull(sysBasicService.getSysBasic());
	}
	
	/**
	 * 修改测试
	 */
	@Test
	public void testUpdateSysBasic()
	{
		sysBasicMapperMock.returns(1).updateByPrimaryKeySelective(new SysBasic());
		assertEquals(1, sysBasicService.updateSysBasic(new SysBasic()));
	}
}
