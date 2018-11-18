package com.junit.system.service;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.system.bean.PointSet;
import com.ningpai.system.dao.PointSetMapper;
import com.ningpai.system.service.PointSetService;
import com.ningpai.system.service.impl.PointSetServiceImpl;

/**
 * 积分设置服务层接口测试类
 * @author djk
 *
 */
public class PointSetServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private PointSetService pointSetService = new PointSetServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<PointSetMapper> pointSetMapperMock;

	/**
	 *  查找积分设置信息测试
	 */
	@Test
	public void testFindPointSet()
	{
		pointSetMapperMock.returns(new PointSet()).findPointSet();
		assertNotNull(pointSetService.findPointSet());
	}
	
	/**
	 * 修改积分设置测试
	 */
	@Test
	public void testUpdatePointSet()
	{
		pointSetMapperMock.returns(1).updateByPrimaryKeySelective(new PointSet());
		assertEquals(1, pointSetService.updatePointSet(new PointSet()));
	}
}
