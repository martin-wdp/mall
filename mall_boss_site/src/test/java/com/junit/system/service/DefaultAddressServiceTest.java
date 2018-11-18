package com.junit.system.service;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.system.bean.ProvinceDefault;
import com.ningpai.system.dao.ProvinceDefaultMapper;
import com.ningpai.system.service.DefaultAddressService;
import com.ningpai.system.service.impl.DefaultAddressServiceImpl;

/**
 * 默认地址测试类
 * @author djk
 *
 */
public class DefaultAddressServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private DefaultAddressService defaultAddressService = new DefaultAddressServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<ProvinceDefaultMapper> provinceDefaultMapperMock;
	
	/**
	 * 插入默认地址测试
	 */
	@Test
	public void testInsertAddressDefaultService()
	{
		provinceDefaultMapperMock.returns(null).deleteAllDefault();
		provinceDefaultMapperMock.returns(1).insertSelective(new ProvinceDefault());
		assertEquals(1, defaultAddressService.insertAddressDefaultService(1L));
	}
	
	/**
	 *  获取默认地址id测试
	 */
	@Test
	public void testGetDefaultIdService()
	{
		provinceDefaultMapperMock.returns(1L).selectDefaultId();
		assertEquals("1",defaultAddressService.getDefaultIdService()+"");
	}
}
