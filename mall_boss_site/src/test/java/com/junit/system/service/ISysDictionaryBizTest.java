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

import com.ningpai.system.bean.SysDictionary;
import com.ningpai.system.dao.ISysDictionaryDao;
import com.ningpai.system.service.ISysDictionaryBiz;
import com.ningpai.system.service.impl.SysDictionaryBizImpl;
import com.ningpai.util.PageBean;


/**
 * 系统字典业务接口测试类
 * @author NP-Heh
 *
 */
public class ISysDictionaryBizTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private ISysDictionaryBiz iSysDictionaryBiz = new SysDictionaryBizImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<ISysDictionaryDao> iSysDictionaryDaoMock;
	
	/**
	 * 系统字典
	 */
	private SysDictionary sysDictionary;
	
	
	/**
	 *分页辅助类
	 */
	private PageBean pageBean;
	
	/**
	 * 参数map
	 */
	private Map<String,Object> map ;
	
	/**
	 * 查询的结果实体
	 */
	private List<Object> list ;
	
	
	@Override
	protected void setUp() throws Exception 
	{
		sysDictionary = new SysDictionary();
		
		pageBean = new PageBean();
		map = new HashMap<>();
		
		map.put("startRowNum", pageBean.getStartRowNum());
		map.put("endRowNum", pageBean.getEndRowNum());
		
		list = new ArrayList<>();
		list.add(new Object());
	}
	
	/**
	 * 保存系统字典测试
	 */
	@Test
	public void testSaveSysDictionary()
	{
		iSysDictionaryDaoMock.returns(true).saveSysDictionary(sysDictionary);
		assertEquals(true, iSysDictionaryBiz.saveSysDictionary(sysDictionary));
	}
	
	/**
	 * 修改系统字典测试
	 */
	@Test
	public void testUpdateSysDictionary()
	{
		iSysDictionaryDaoMock.returns(1).updateSysDictionary(sysDictionary);
		assertEquals(1, iSysDictionaryBiz.updateSysDictionary(sysDictionary));
	}
	
	/**
	 * 根据系统字典对象的id查询系统字典对象测试
	 */
	@Test
	public void testGetSysDictionaryById()
	{
		iSysDictionaryDaoMock.returns(sysDictionary).getSysDictionaryById(1);
		assertNotNull(iSysDictionaryBiz.getSysDictionaryById(1));
	}
	
	/**
	 * 根据系统字典对象的id字符集合查询系统字典对象
	 */
	@Test
	public void testGetSysDictionaryByIds()
	{
		List<SysDictionary>  lists = new ArrayList<>();
		lists.add(sysDictionary);
		iSysDictionaryDaoMock.returns(lists).getSysDictionaryByIds("1");
		assertEquals(1, iSysDictionaryBiz.getSysDictionaryByIds("1").size());
	}
	
	/**
	 *  根据系统字典对象的id字符集合删除系统字典对象测试
	 */
	@Test
	public void testDeleteSysDictionary()
	{
		iSysDictionaryDaoMock.returns(1).deleteSysDictionary("1");
		assertEquals(1, iSysDictionaryBiz.deleteSysDictionary("1"));
	}
	
	/**
	 * 
	 */
	@Test
	public void testUpdateSysDictionaryFieldById()
	{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ids", "1,2");
		iSysDictionaryDaoMock.returns(1).updateSysDictionaryFieldById(map);
		assertEquals(1, iSysDictionaryBiz.updateSysDictionaryFieldById(map));
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		assertEquals(list, map.get("ids"));
	}
	
	/**
     * 根据系统字典对象的单个字段查询系统字典对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
	 */
	@Test
	public void testGetSysDictionaryByFieldTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iSysDictionaryDaoMock.returns(1).getSysDictionaryByFieldTotal(map);
		assertEquals(1, iSysDictionaryBiz.getSysDictionaryByFieldTotal(map));
	}
	
	/**
     * 根据系统字典对象的单个字段查询系统字典对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
	 */
	@Test
	public void testGetSysDictionaryByField()
	{
		iSysDictionaryDaoMock.returns(1).getSysDictionaryByFieldTotal(map);
		iSysDictionaryDaoMock.returns(list).getSysDictionaryByField(map);
		assertEquals(1, iSysDictionaryBiz.getSysDictionaryByField(map, pageBean).getList().size());
	}
	
	/**
	 * 查询系统字典对象信息总数测试
	 */
	@Test
	public void testQuerySysDictionaryTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iSysDictionaryDaoMock.returns(1).querySysDictionaryTotal(map);
		assertEquals(1, iSysDictionaryBiz.querySysDictionaryTotal(map));
	}
	
	/**
	 * 分页查询系统字典对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum测试
	 */
	@Test
	public void testquerySysDictionaryByPage()
	{
		iSysDictionaryDaoMock.returns(1).querySysDictionaryTotal(map);
		iSysDictionaryDaoMock.returns(list).querySysDictionaryByPage(map);
		assertEquals(1, iSysDictionaryBiz.querySysDictionaryByPage(map, pageBean).getList().size());
	}
}
