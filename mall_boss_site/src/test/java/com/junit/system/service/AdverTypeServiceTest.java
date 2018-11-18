package com.junit.system.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.system.bean.AdverType;
import com.ningpai.system.dao.AdverTypeMapper;
import com.ningpai.system.service.AdverTypeService;
import com.ningpai.system.service.impl.AdverTypeServiceImpl;

/**
 * 广告分类服务层接口测试类
 * @author djk
 *
 */
public class AdverTypeServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private AdverTypeService adverTypeService = new AdverTypeServiceImpl();
	
	
	/**
	 *  模拟MOCK
	 */
	 @InjectIntoByType
	private Mock<AdverTypeMapper> adverTypeMapperMock;
	 
	
	 /**
	  * 测试查询所有广告分类
	  */
	 @Test
	public void testFindAll()
	{
		List<AdverType> list = new ArrayList<AdverType>();
		list.add(new AdverType());
		
		adverTypeMapperMock.returns(list).selectAll();       
		assertEquals(1, adverTypeService.findAll().size());
	}
}
