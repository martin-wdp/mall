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

import com.ningpai.system.bean.Street;
import com.ningpai.system.dao.StreetMapper;
import com.ningpai.system.service.StreetService;
import com.ningpai.system.service.impl.StreetServiceImpl;
import com.ningpai.system.util.AddressUtil;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 *  街道Service测试
 * @author djk
 *
 */
public class StreetServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private StreetService streetService = new StreetServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<StreetMapper> streetMapperMock;
	
	/**
	 * 保存街道信息测试
	 */
	@Test
	public void testSaveStreet()
	{
		streetMapperMock.returns(1).insertSelective(new Street());
		assertEquals(1, streetService.saveStreet("1", "1", "1"));
	}
	
	/**
	 * 删除街道信息测试
	 */
	@Test
	public void testDelStreet()
	{
		streetMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, streetService.delStreet(1L));
	}
	
	/**
	 * 更新街道信息
	 */
	@Test
	public void testUpdateStreet()
	{
		streetMapperMock.returns(1).updateByPrimaryKeySelective(new Street());
		assertEquals(1, streetService.updateStreet("1", "1", "1"));
	}
	
	/**
	 * 根据主键查询测试
	 */
	@Test
	public void testFindStreetByPrimaryKey()
	{
		streetMapperMock.returns(new Street()).selectByPrimaryKey(1L);
		assertNotNull(streetService.findStreetByPrimaryKey(1L));
	}
	
	/**
	 * 根据分页参数以及查询参数查询列表测试
	 */
	@Test
	public void testFindListByPageBean()
	{
		PageBean pb = new PageBean();
		SelectBean selectBean = new SelectBean();
	    Map<String, Object> map = new HashMap<String, Object>();
		streetMapperMock.returns(1).queryTotalCount(map);
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        List<Object> lists = new ArrayList<>();
        lists.add(new Object());
        streetMapperMock.returns(lists).queryAllStreetByPb(map);
		assertEquals(1, streetService.findListByPageBean(pb, selectBean).getList().size());
	}
	
	/**
	 * 批量删除记录测试
	 */
	@Test
	public void testBatchDelStreet()
	{
		String [] ids = {"1"};
		streetMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, streetService.batchDelStreet(ids));
	}
	
	/**
	 * 根据区县ID查询街道信息
	 */
	@Test
	public void testQueryStreetByDistrictId()
	{
		 List<Street> lists = new ArrayList<>();
		 lists.add(new Street());
		 streetMapperMock.returns(lists).queryStreetByDistrictId(1L);
		 assertEquals(1, streetService.queryStreetByDistrictId(1L).size());
		 
	}
	
	/**
	 * 验证街道名称是否可用
	 */
	@Test
	public void testCheckStreetName()
	{
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("streetName", "a");
        streetMapperMock.returns(0).queryStreetByStreetName(map);
		assertEquals(true, streetService.checkStreetName("a"));
	}
	
	/**
	 * 根据街道ID查询所属的区县，城市和省份ID
	 */
	@Test
	public void testQueryAddressNameByStreetId()
	{
		streetMapperMock.returns(new AddressUtil()).queryAddressNameByStreetId(1L);
		assertNotNull(streetService.queryAddressNameByStreetId(1L));
	}
	
	/**
	 * 根据县区id和街道名称查询所有街道测试
	 */
	@Test
	public void testQueryStreetByDistrictIdAndStreetName()
	{
		List<Object> lists = new ArrayList<>();
		lists.add(new Object());
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("districtId", 1L);
        paramMap.put("streetName", "a");
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 1000);
		streetMapperMock.returns(lists).queryAllStreetByPb(paramMap);
		assertEquals(1, streetService.queryStreetByDistrictIdAndStreetName(1L, "a").size());
	}
}
