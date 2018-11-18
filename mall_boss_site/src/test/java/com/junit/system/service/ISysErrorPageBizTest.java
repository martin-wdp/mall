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

import com.ningpai.system.bean.SysErrorPage;
import com.ningpai.system.dao.ISysErrorPageDao;
import com.ningpai.system.service.ISysErrorPageBiz;
import com.ningpai.system.service.impl.SysErrorPageBizImpl;
import com.ningpai.util.PageBean;

/**
 * 异常页面设置业务接口测试
 * @author djk
 *
 */
public class ISysErrorPageBizTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private ISysErrorPageBiz iSysErrorPageBiz = new SysErrorPageBizImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<ISysErrorPageDao> iSysErrorPageDaoMock;
	
	/**
	 * 异常页面设置
	 */
	private SysErrorPage sysErrorPage;
	
	
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
		sysErrorPage = new SysErrorPage();
		pageBean = new PageBean();
		map = new HashMap<>();
		
		map.put("startRowNum", pageBean.getStartRowNum());
		map.put("endRowNum", pageBean.getEndRowNum());
		
		list = new ArrayList<>();
		list.add(new Object());
	}
	
	
	/**
	 * 保存异常页面设置测试
	 */
	@Test
	public void testsaveSysErrorPage()
	{
		iSysErrorPageDaoMock.returns(true).saveSysErrorPage(sysErrorPage);
		assertEquals(true, iSysErrorPageBiz.saveSysErrorPage(sysErrorPage));
	}
	
	/**
	 * 修改异常页面设置测试 
	 */
	@Test
	public void testUpdateSysErrorPage()
	{
		iSysErrorPageDaoMock.returns(1).updateSysErrorPage(sysErrorPage);
		assertEquals(1, iSysErrorPageBiz.updateSysErrorPage(sysErrorPage));
	}
	
	/**
	 * 根据异常页面设置对象的id查询异常页面设置对象测试
	 */
	@Test
	public void testGetSysErrorPageById()
	{
		iSysErrorPageDaoMock.returns(sysErrorPage).getSysErrorPageById(1);
		assertNotNull(iSysErrorPageBiz.getSysErrorPageById(1));
	}
	
	/**
	 *  根据异常页面设置对象的id字符集合查询异常页面设置对象测试
	 */
	@Test
	public void testGetSysErrorPageByIds()
	{
		List<SysErrorPage> lists = new ArrayList<>();
		lists.add(sysErrorPage);
		iSysErrorPageDaoMock.returns(lists).getSysErrorPageByIds("1");
		assertEquals(1, iSysErrorPageBiz.getSysErrorPageByIds("1").size());
	}
	
	/**
	 * 根据异常页面设置对象的id字符集合删除异常页面设置对象测试
	 */
	@Test
	public void testDeleteSysErrorPage()
	{
		iSysErrorPageDaoMock.returns(1).deleteSysErrorPage("1");
		assertEquals(1, iSysErrorPageBiz.deleteSysErrorPage("1"));
	}
	
	/**
	 * 更新异常页面设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）测试
	 */
	@Test
	public void testUpdateSysErrorPageFieldById()
	{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ids", "1,2");
		iSysErrorPageDaoMock.returns(1).updateSysErrorPageFieldById(map);
		assertEquals(1, iSysErrorPageBiz.updateSysErrorPageFieldById(map));
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		assertEquals(list, map.get("ids"));
	}
	
	/**
     * 根据异常页面设置对象的单个字段查询异常页面设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
	 */
	@Test
	public void testGetSysErrorPageByFieldTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iSysErrorPageDaoMock.returns(1).getSysErrorPageByFieldTotal(map);
		assertEquals(1, iSysErrorPageBiz.getSysErrorPageByFieldTotal(map));
	}
	
	/**
     * 根据异常页面设置对象的单个字段查询异常页面设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
	 */
	@Test
	public void testGetSysErrorPageByField()
	{
		iSysErrorPageDaoMock.returns(1).getSysErrorPageByFieldTotal(map);
		iSysErrorPageDaoMock.returns(list).getSysErrorPageByField(map);
		assertEquals(1, iSysErrorPageBiz.getSysErrorPageByField(map, pageBean).getList().size());
	}
	
	/**
	 * 查询异常页面设置对象信息总数
	 */
	@Test
	public void testQuerySysErrorPageTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iSysErrorPageDaoMock.returns(1).querySysErrorPageTotal(map);
		assertEquals(1, iSysErrorPageBiz.querySysErrorPageTotal(map));
	}
	
	/**
	 * 分页查询异常页面设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum测试
	 */
	@Test
	public void testQuerySysErrorPageByPage()
	{
		iSysErrorPageDaoMock.returns(1).querySysErrorPageTotal(map);
		iSysErrorPageDaoMock.returns(list).querySysErrorPageByPage(map);
		assertEquals(1, iSysErrorPageBiz.querySysErrorPageByPage(map, pageBean).getList().size());
	}
	
	/**
	 *  根据页面名字查询异常页面
	 */
	@Test
	public void testQuerySysErrorByPageName()
	{
		iSysErrorPageDaoMock.returns(sysErrorPage).querySysErrorByPageName("a");
		assertNotNull(iSysErrorPageBiz.querySysErrorByPageName("a"));
	}
	
}
