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

import com.ningpai.system.bean.AreaPackage;
import com.ningpai.system.dao.IAreaPackageDao;
import com.ningpai.system.service.IAreaPackageBiz;
import com.ningpai.system.service.impl.AreaPackageBizImpl;
import com.ningpai.util.PageBean;

/**
 * 地区设置业务接口测试类
 * @author djk
 *
 */
public class IAreaPackageBizTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private IAreaPackageBiz iAreaPackageBiz = new AreaPackageBizImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<IAreaPackageDao> iAreaPackageDaoMock;
	
	/**
	 * 地区实体
	 */
	private AreaPackage areaPackage;
	
	private PageBean pageBean;
	
	@Override
	protected void setUp() throws Exception 
	{
		areaPackage  = new AreaPackage();
		pageBean = new PageBean();
	}
	
	/**
	 *  保存地区设置测试
	 */
	@Test
	public void testSaveAreaPackage()
	{
		iAreaPackageDaoMock.returns(true).saveAreaPackage(areaPackage);
		assertEquals(true, iAreaPackageBiz.saveAreaPackage(areaPackage));
	}
	
	/**
	 * 修改地区设置测试
	 */
	@Test
	public void testUpdateAreaPackage()
	{
		iAreaPackageDaoMock.returns(null).changeAllDefaultStatusToNot();
		iAreaPackageDaoMock.returns(1).updateAreaPackage(areaPackage);
		assertEquals(1, iAreaPackageBiz.updateAreaPackage(areaPackage));
	}
	
	/**
	 * 根据地区设置对象的id查询地区设置对象测试
	 */
	@Test
	public void testGetAreaPackageById()
	{
		iAreaPackageDaoMock.returns(areaPackage).getAreaPackageById(1);
		assertNotNull(iAreaPackageBiz.getAreaPackageById(1));
	}

	/**
	 * 根据地区设置对象的id字符集合查询地区设置对象测试
	 */
	@Test
	public void testGetAreaPackageByIds()
	{
		List<AreaPackage> lists = new ArrayList<>();
		lists.add(areaPackage);
		iAreaPackageDaoMock.returns(lists).getAreaPackageByIds("1");
		assertEquals(1, iAreaPackageBiz.getAreaPackageByIds("1").size());
	}
	
	/**
	 * 根据地区设置对象的id字符集合删除地区设置对象测试
	 */
	@Test
	public void testDeleteAreaPackage()
	{
		iAreaPackageDaoMock.returns(1).deleteAreaPackage("1");
		assertEquals(1, iAreaPackageBiz.deleteAreaPackage("1"));
	}
	
	/**
	 * 更新地区设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
	 */
	@Test
	public void testUpdateAreaPackageFieldById()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", "1,2");
		
		iAreaPackageDaoMock.returns(1).updateAreaPackageFieldById(map);
		assertEquals(1, iAreaPackageBiz.updateAreaPackageFieldById(map));
		List<String> lists = new ArrayList<>();
		lists.add("1");
		lists.add("2");
		assertEquals(lists, map.get("ids"));
	}
	
	/**
	 * 根据地区设置对象的单个字段查询地区设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
	 */
	@Test
	public void testGetAreaPackageByFieldTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iAreaPackageDaoMock.returns(1).getAreaPackageByFieldTotal(map);
		assertEquals(1, iAreaPackageBiz.getAreaPackageByFieldTotal(map));
	}
	
	/**
	 * 根据地区设置对象的单个字段查询地区设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
	 */
	@Test
	public void testGetAreaPackageByField()
	{
		List<Object> lists = new ArrayList<>();
		lists.add(new Object());
		Map<String, Object> map = new HashMap<>();
		map.put("startRowNum", pageBean.getStartRowNum());
		map.put("endRowNum", pageBean.getEndRowNum());
		
		iAreaPackageDaoMock.returns(1).getAreaPackageByFieldTotal(map);
		iAreaPackageDaoMock.returns(lists).getAreaPackageByField(map);
		assertEquals(1, iAreaPackageBiz.getAreaPackageByField(map, pageBean).getList().size());
	}
	
	/**
	 * 查询地区设置对象信息总数测试
	 */
	@Test
	public void testQueryAreaPackageTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iAreaPackageDaoMock.returns(1).queryAreaPackageTotal(map);
		assertEquals(1, iAreaPackageBiz.queryAreaPackageTotal(map));
	}
	
	/**
	 * 分页查询地区设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum测试
	 */
	@Test
	public void testQueryAreaPackageByPage()
	{
		List<Object> lists = new ArrayList<>();
		lists.add(new Object());
		Map<String, Object> map = new HashMap<>();
		map.put("startRowNum", pageBean.getStartRowNum());
		map.put("endRowNum", pageBean.getEndRowNum());
		
		iAreaPackageDaoMock.returns(1).queryAreaPackageTotal(map);
		iAreaPackageDaoMock.returns(lists).queryAreaPackageByPage(map);
		assertEquals(1, iAreaPackageBiz.queryAreaPackageByPage(map, pageBean).getList().size());
	}
	
	/**
	 *  根据主键删除地区包测试
	 */
	@Test
	public void testDeleteById()
	{
		iAreaPackageBiz.deleteById(1);
	}
}
