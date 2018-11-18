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

import com.ningpai.system.bean.CurrencyBase;
import com.ningpai.system.bean.CurrencyInfo;
import com.ningpai.system.dao.CurrencyBaseMapper;
import com.ningpai.system.dao.CurrencyInfoMapper;
import com.ningpai.system.service.CurrencyInfoService;
import com.ningpai.system.service.impl.CurrencyInfoServiceImpl;
import com.ningpai.system.vo.CurrencyInfoVo;
import com.ningpai.util.PageBean;

/**
 *  货币信息SERVICE测试类
 * @author djk
 *
 */
public class CurrencyInfoServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private CurrencyInfoService currencyInfoService = new CurrencyInfoServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<CurrencyInfoMapper> currencyInfoMapperMock;
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<CurrencyBaseMapper> currencyBaseMapperMock;
	
	/**
	 * 根据主键删除货币信息测试
	 */
	@Test
	public void testDeleteCurrencyInfo()
	{
		currencyInfoMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, currencyInfoService.deleteCurrencyInfo(1L));
	}
	
	/**
	 * 批量删除货币信息测试
	 */
	@Test
	public void testBatchDelCurrencyInfo()
	{
		Long [] ids = {1L};
		currencyInfoService.batchDelCurrencyInfo(ids);
	}
	
	/**
	 * 添加货币信息测试
	 */
	@Test
	public void testSaveCurrencyInfo()
	{
		currencyInfoMapperMock.returns(1).insert(new CurrencyInfo());
		assertEquals(1, currencyInfoService.saveCurrencyInfo(new CurrencyInfo()));
	}
	
	/**
	 * 更新货币信息测试
	 */
	@Test
	public void testUpdateCurrencyInfo()
	{
		currencyInfoMapperMock.returns(1).updateByPrimaryKeySelective(new CurrencyInfo());
		assertEquals(1,currencyInfoService.updateCurrencyInfo(new CurrencyInfo()));
	}
	
	/**
	 * 根据主键查询货币信息测试
	 */
	@Test
	public void testGetCurrencyInfo()
	{
		currencyInfoMapperMock.returns(new CurrencyInfoVo()).selectByPrimaryKey(1L);
		assertNotNull(currencyInfoService.getCurrencyInfo(1L));
	}
	
	/**
	 * 根据分页参数查询货币信息集合测试
	 */
	@Test
	public void testQueryCurrencyInfoByPageBean()
	{
		List<Object> list = new ArrayList<Object>();
		list.add(new Object());
		PageBean pb = new PageBean();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        currencyInfoMapperMock.returns(1).queryTotalCount();
        currencyInfoMapperMock.returns(list).queryByPageBean(map);
		assertEquals(1, currencyInfoService.queryCurrencyInfoByPageBean(pb).getList().size());
	}
	
	/**
	 * 获取所有货币基础信息测试
	 */
	@Test
	public void testFindAllCurrencyBase()
	{
		List<CurrencyBase> list = new ArrayList<CurrencyBase>();
		list.add(new CurrencyBase());
		currencyBaseMapperMock.returns(list).selectAllCurrencyBase();
		assertEquals(1, currencyInfoService.findAllCurrencyBase().size());
	}
	
	/**
	 *  根据编号查询货币基础信息测试
	 */
	@Test
	public void testGetCurrencyBaseById()
	{
		currencyBaseMapperMock.returns(new CurrencyBase()).selectByPrimaryKey(1L);
		assertNotNull(currencyInfoService.getCurrencyBaseById(1L));
	}
}
