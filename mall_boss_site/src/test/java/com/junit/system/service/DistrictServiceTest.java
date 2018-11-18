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

import com.ningpai.system.bean.District;
import com.ningpai.system.dao.DistrictMapper;
import com.ningpai.system.service.DistrictService;
import com.ningpai.system.service.impl.DistrictServiceImpl;
import com.ningpai.system.util.AddressUtil;
import com.ningpai.system.vo.DistrictVo;
import com.ningpai.util.PageBean;

/**
 * 区县Service 测试类
 * @author djk
 *
 */
public class DistrictServiceTest extends UnitilsJUnit3
{ 
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private DistrictService districtService = new DistrictServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<DistrictMapper> districtMapperMock ;
	
	/**
	 * 增加区域信息测试
	 */
	@Test
	public void testSaveDistrict()
	{
		districtMapperMock.returns(1).insertSelective(new District());
		assertEquals(1, districtService.saveDistrict("a", "1", "1"));
	}
	
	/**
	 * 删除区县信息测试
	 */
	@Test
	public void testDelDistrict()
	{
		districtMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, districtService.delDistrict(1L));
	}
	
	/**
	 * 更新区县信息测试
	 */
	@Test
	public void testUpdateDistrict()
	{
		districtMapperMock.returns(1).updateByPrimaryKeySelective(new District());
		assertEquals(1, districtService.updateDistrict("1", "1", "1"));
	}
	
	/**
	 * 根据主键查询实体测试
	 */
	@Test
	public void testFindDistrictByPrimaryKey()
	{
		districtMapperMock.returns(new District()).selectByPrimaryKey(1L);
		assertNotNull(districtService.findDistrictByPrimaryKey(1L));
	}
	
	/**
	 * 
	 */
	@Test
	public void testFindListByPageBean()
	{
		PageBean pb = new PageBean();
	    Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        districtMapperMock.returns(1).queryTotalCount(map);
        List<Object> list = new ArrayList<Object>();
        list.add(new Object());
        districtMapperMock.returns(list).queryDistrictListByPb(map);
		assertEquals(1, districtService.findListByPageBean(pb, null).getList().size());
	}
	
	/**
	 *  批量删除区县信息测试
	 */
	@Test
	public void testBatchDelDistrict()
	{
		String  [] ids = {"1"};
		districtMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, districtService.batchDelDistrict(ids));
	}
	
	/**
	 * 根据城市信息查询所有的区县信息测试
	 */
	@Test
	public void testQueryDistrictByCityId()
	{
		List<DistrictVo> list = new ArrayList<DistrictVo>();
		list.add(new DistrictVo());
		districtMapperMock.returns(list).queryDistrictListByCityId(1L);
		assertEquals(1, districtService.queryDistrictByCityId(1L).size());
	}
	
	/**
	 * 验证区县名称是否可用测试
	 */
	@Test
	public void testCheckDistrictName()
	{
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("districtName", "a");
        districtMapperMock.returns(0).queryDistrictByDistrictName(map);
		assertEquals(true, districtService.checkDistrictName("a"));
	}
	
	/**
	 * 根据区县ID查询所属的城市和省份名称测试
	 */
	@Test
	public void testQueryAddressNameByDistrictId()
	{
		districtMapperMock.returns(new AddressUtil()).queryAddressNameByDistrictId(1L);
		assertNotNull(districtService.queryAddressNameByDistrictId(1L));
	}
	
	/**
	 * 根据城市id和县区名称查询所有县区测试
	 */
	@Test
	public void testQueryDistrictByCityIdAndDistName()
	{
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cityId", 1L);
        paramMap.put("districtName", "a");
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 1000);
        List<Object> lists = new ArrayList<Object>();
        lists.add(new Object());
        districtMapperMock.returns(lists).queryDistrictListByPb(paramMap);
		assertEquals(1, districtService.queryDistrictByCityIdAndDistName(1L, "a").size());
	}
}
