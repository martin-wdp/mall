package com.junit.system.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.system.bean.Floor;
import com.ningpai.system.dao.FloorMapper;
import com.ningpai.system.service.FloorService;
import com.ningpai.system.service.impl.FloorServiceImpl;

/**
 * 楼层层数服务层接口测试类
 * @author djk
 *
 */
public class FloorServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private FloorService floorService = new FloorServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<FloorMapper> floorMapperMock ;
	
	/**
	 * 查询所有楼层信息测试
	 */
	@Test
	public void testFindAll()
	{
		List<Floor> list = new ArrayList<Floor>();
		list.add(new Floor());
		floorMapperMock.returns(list).findAll();
		assertEquals(1, floorService.findAll().size());
	}
}
