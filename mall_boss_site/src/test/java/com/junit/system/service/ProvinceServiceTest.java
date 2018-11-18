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

import com.ningpai.system.bean.Province;
import com.ningpai.system.dao.ProvinceMapper;
import com.ningpai.system.service.ProvinceService;
import com.ningpai.system.service.impl.ProvinceServiceImpl;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 地区管理Service测试
 * @author djk
 *
 */
public class ProvinceServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private ProvinceService provinceService = new ProvinceServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<ProvinceMapper> provinceMapperMock;
	
	
	/**
	 * 分页辅助类
	 */
	private PageBean pageBean;
	
	/**
	 * 分页参数Bean
	 */
	private SelectBean selectBean;
	
	
	/**
	 * 参数
	 */
	private Map<String, Object> map;
	
	/**
	 * 返回结果
	 */
	private List<Object> lists;
	@Override
	protected void setUp() throws Exception 
	{
		pageBean = new PageBean();
		selectBean = new SelectBean();
		lists = new ArrayList<>();
		lists.add(new Object());
		map = new HashMap<String, Object>();
		map.put("condition", selectBean.getCondition());
		map.put("searchText", selectBean.getSearchText());
	}
	
	/**
	 * 保存省份信息测试
	 */
	@Test
	public void testSaveProvince()
	{
		provinceMapperMock.returns(1).insertSelective(new Province());
		assertEquals(1, provinceService.saveProvince("1", "1"));
	}
	
	/**
	 *  删除省份信息测试
	 */
	@Test
	public void testDelProvince()
	{
		provinceMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, provinceService.delProvince(1L));
	}
	
	/**
	 * 更新省份信息测试
	 */
	@Test
	public void testUpdateProvince()
	{
		provinceMapperMock.returns(1).updateByPrimaryKeySelective(new Province());
		assertEquals(1, provinceService.updateProvince("1", "a", "1"));
	}
	
	/**
	 * 根据主键ID查询省份信息测试
	 */
	@Test
	public void testFindProvinceByPrimaryKey()
	{
		provinceMapperMock.returns(new Province()).selectByPrimaryKey(1L);
		assertNotNull(provinceService.findProvinceByPrimaryKey(1L));
	}
	
	/**
	 *  根据分页实体以及查询参数查询列表测试
	 */
	@Test
	public void testFindListByPageBean()
	{
		provinceMapperMock.returns(1).queryTotalCount(map);
		map.put("startRowNum", pageBean.getStartRowNum());
		map.put("endRowNum", 10000);
		provinceMapperMock.returns(lists).queryAllProvice(map);
		assertEquals(1, provinceService.findListByPageBean(pageBean, selectBean).getList().size());
	}
	
	/**
	 * 批量删除省份实体测试
	 */
	@Test
	public void testBatchDelProvince()
	{
		String [] ids = {"1"};
		provinceMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, provinceService.batchDelProvince(ids));
	}
	
	/**
	 * 验证省份名称是否可用 测试
	 */
	@Test
	public void testCheckProvinceName()
	{
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("provinceName", "a");
        provinceMapperMock.returns(1).queryProvinceByName(map);
		assertEquals(false, provinceService.checkProvinceName("a"));
	}
	
	/**
	 *  查询所有的省份信息测试
	 */
	@Test
	public void testQueryAllProvince()
	{
		List<Object> list = new ArrayList<>();
		list.add(new Object());
		provinceMapperMock.returns(list).queryAllProvince();
		assertEquals(1, provinceService.queryAllProvince().size());
	}
	
	/**
	 *  查询所有省份、城市、地区、街道
	 */
	@Test
	public void testFindListByPageBeanNew()
	{
		
        Map<String, Object> limitmap = new HashMap<String, Object>();
        
        limitmap.put("searchText", selectBean.getSearchText());
        provinceMapperMock.returns(lists).queryAllProviceNew(limitmap);
		assertEquals(1, provinceService.findListByPageBeanNew(pageBean, selectBean).getList().size());
	}
	
	/**
	 * 根据名称查询所有省份测试
	 */
	@Test
	public void testQueryAllProvinceByName()
	{
		List<Object> lists = new ArrayList<>();
		lists.add(new Object());
		
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("searchText", "a");
        paramMap.put("condition", "1");
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 1000);
        provinceMapperMock.returns(lists).queryAllProvice(paramMap);
		assertEquals(1, provinceService.queryAllProvinceByName("a").size());
	}
}
