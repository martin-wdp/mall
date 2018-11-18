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

import com.ningpai.system.bean.City;
import com.ningpai.system.dao.CityMapper;
import com.ningpai.system.service.CityService;
import com.ningpai.system.service.impl.CityServiceImpl;
import com.ningpai.system.util.AddressUtil;
import com.ningpai.system.util.SelectBean;
import com.ningpai.system.vo.CityVo;
import com.ningpai.util.PageBean;

/**
 *  城市信息Service测试类
 * @author djk
 *
 */
public class CityServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private CityService cityService = new CityServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<CityMapper> cityMapperMock;
 	
	/**
	 * 保存城市信息测试
	 */
	@Test
	public void testSaveCity()
	{
		cityMapperMock.returns(1).insertSelective(new City());
		assertEquals(1, cityService.saveCity("1", "1", "1"));
	}
	
	/**
	 * 删除城市信息测试
	 */
	@Test
	public void testDelPCity()
	{
		cityMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, cityService.delPCity(1L));
	}
	
	/**
	 * 更新城市信息测试
	 */
	@Test
	public void testUpdateCity()
	{
		cityMapperMock.returns(1).updateByPrimaryKeySelective(new City());
		assertEquals(1, cityService.updateCity("1", "1", "1"));
	}
	
	/**
	 * 根据主键ID查询城市信息测试
	 */
	@Test
	public void testFindCityByPrimaryKey()
	{
		cityMapperMock.returns(new City()).selectByPrimaryKey(1L);
		assertNotNull(cityService.findCityByPrimaryKey(1L));
	}
	
	/**
	 * 根据分页实体以及查询参数查询列表测试
	 */
	@Test
	public void testFindListByPageBean()
	{
	
    	// 生成分页参数Bean
        SelectBean selectBean = new SelectBean();
        selectBean.setCondition("1");
        selectBean.setSearchText("千米");
        
        // 生成分页辅助类
        PageBean pb = new PageBean();
        pb.setStartRowNum(0);
        pb.setEndRowNum(15);

        // 设置参数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        
        // 设置返回的对象
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        
        cityMapperMock.returns(1).queryTotalCount(map);
        cityMapperMock.returns(list).queryCityListByPb(map);
		assertEquals(1, cityService.findListByPageBean(pb,selectBean).getList().size());
	}
	
	/**
	 * 批量删除城市实体测试
	 */
	@Test
	public void testBatchDelCity()
	{
		String [] cityIds = {"1"};
		cityMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, cityService.batchDelCity(cityIds));
	}
	
	/**
	 * 根据省份ID查询城市的列表测试
	 */
	@Test
	public void testQueryCityByProvinceId()
	{
		List<CityVo> list = new ArrayList<CityVo>();
		list.add(new CityVo());
		cityMapperMock.returns(list).queryCityByProvinceId(1L);
		assertEquals(1, cityService.queryCityByProvinceId(1L).size());
	}
	
	/**
	 * 验证城市名称是否可用测试
	 */
	@Test
	public void testCheckCityName()
	{
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cityName", "a");
		cityMapperMock.returns(0).queryCityByCityName(map);
		assertEquals(true, cityService.checkCityName("a"));
	}
	
	/**
	 * 根据城市名称查询所属的省份名称
	 */
	@Test
	public void testQueryAddressNameByCityId()
	{
		cityMapperMock.returns(new AddressUtil()).queryProvinceNameByCityId(1L);
		assertNotNull(cityService.queryAddressNameByCityId(1L));
	}
	
	/**
	 * 根据省份id和城市名称查询所有城市测试
	 */
	@Test
	public void testQueryCityByProvinceIdAndCityName()
	{
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("provinceId", 1L);
        paramMap.put("cityName",  "a");
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 1000);
        
        List<Object> list = new ArrayList<Object>();
        list.add(new Object());
        
        cityMapperMock.returns(list).queryCityListByPb(paramMap);
		assertEquals(1, cityService.queryCityByProvinceIdAndCityName(1L, "a").size());
	}
	
}
