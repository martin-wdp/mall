package com.junit.system.service;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.system.bean.ShopKuaiShang;
import com.ningpai.system.dao.ShopKuaiShangMapper;
import com.ningpai.system.service.ShopKuaiShangService;
import com.ningpai.system.service.impl.ShopKuaiShangServiceImpl;

/**
 * 快商通实现类测试
 * @author djk
 *
 */
public class ShopKuaiShangServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private ShopKuaiShangService shopKuaiShangService = new ShopKuaiShangServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<ShopKuaiShangMapper> shopKuaiShangMapperMock;

	/**
	 *  快商通实体类
	 */
	private ShopKuaiShang shopKuaiShang = new ShopKuaiShang();
	
	/**
	 * 添加一条区县记录测试
	 */
	@Test
	public void testInsertKuaiShang()
	{
		shopKuaiShangMapperMock.returns(1).insertKuaiShang(shopKuaiShang);
		assertEquals(1, shopKuaiShangService.insertKuaiShang(shopKuaiShang));
	}
	
	/**
	 * 根据主键查询快商通信息
	 */
	@Test
	public void testSelectInitone()
	{
		shopKuaiShangMapperMock.returns(shopKuaiShang).selectInitone();
		assertNotNull(shopKuaiShangService.selectInitone());
	}
	
	/**
	 *  申请快商通测试
	 */
	@Test
	public void testUpdateKuaiShangByPrimaryKey()
	{
		shopKuaiShangMapperMock.returns(1).updateKuaiShangByPrimaryKey(shopKuaiShang);
		assertEquals(1, shopKuaiShangService.updateKuaiShangByPrimaryKey(shopKuaiShang));
	}
}
