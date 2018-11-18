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

import com.ningpai.system.bean.LogisticsCompany;
import com.ningpai.system.dao.IExpressConfDao;
import com.ningpai.system.dao.ILogisticsCompanyDao;
import com.ningpai.system.service.ILogisticsCompanyBiz;
import com.ningpai.system.service.impl.LogisticsCompanyBizImpl;
import com.ningpai.util.PageBean;

/**
 * 物流公司设置业务接口测试
 * @author djk
 *
 */
public class ILogisticsCompanyBizTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private ILogisticsCompanyBiz iLogisticsCompanyBiz = new LogisticsCompanyBizImpl();
	
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<ILogisticsCompanyDao> iLogisticsCompanyDaoMock;

	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<IExpressConfDao> iExpressConfDaoMock;
	
	/**
	 * 物流公司设置实体
	 */
	private LogisticsCompany logisticsCompany;
	
	/**
	 *  分页辅助类
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
		logisticsCompany = new LogisticsCompany();
		
		pageBean = new PageBean();
		
		map = new HashMap<>();
		
		map.put("startRowNum", pageBean.getStartRowNum());
		map.put("endRowNum", pageBean.getEndRowNum());
		
		list = new ArrayList<>();
		list.add(new Object());
	}
	
	/**
	 * 保存物流公司设置测试
	 */
	@Test
	public void testSaveLogisticsCompany()
	{
		iLogisticsCompanyDaoMock.returns(true).saveLogisticsCompany(logisticsCompany);
		assertEquals(true, iLogisticsCompanyBiz.saveLogisticsCompany(logisticsCompany));
	}
	
	/**
	 * 修改物流公司设置测试
	 */
	@Test
	public void testUpdateLogisticsCompany()
	{
		iLogisticsCompanyDaoMock.returns(1).updateLogisticsCompany(logisticsCompany);
		assertEquals(1, iLogisticsCompanyBiz.updateLogisticsCompany(logisticsCompany));
	}
	
	/**
	 *  根据物流公司设置对象的id查询物流公司设置对象测试
	 */
	@Test
	public void testGetLogisticsCompanyById()
	{
		iLogisticsCompanyDaoMock.returns(logisticsCompany).getLogisticsCompanyById(1);
		assertNotNull(iLogisticsCompanyBiz.getLogisticsCompanyById(1));
	}
	
	/**
	 * 第三方根据物流公司设置对象的id查询物流公司设置对象测试
	 */
	@Test
	public void testGetThirdLogisticsCompanyById()
	{
		iLogisticsCompanyDaoMock.returns(logisticsCompany).getThirdLogisticsCompanyById(1);
		assertNotNull(iLogisticsCompanyBiz.getThirdLogisticsCompanyById(1));
	}
	
	/**
	 * 根据物流公司设置对象的id字符集合查询物流公司设置对象测试
	 */
	@Test
	public void testGetLogisticsCompanyByIds()
	{
		List<LogisticsCompany>  lists = new ArrayList<>();
		lists.add(logisticsCompany);
		iLogisticsCompanyDaoMock.returns(lists).getLogisticsCompanyByIds("1");
		assertEquals(1, iLogisticsCompanyBiz.getLogisticsCompanyByIds("1").size());
	}
	
	/**
	 * 根据物流公司设置对象的id字符集合删除物流公司设置对象测试
	 */
	@Test
	public void testDeleteLogisticsCompany()
	{
		iLogisticsCompanyDaoMock.returns(1).deleteLogisticsCompany("1");
		assertEquals(1, iLogisticsCompanyBiz.deleteLogisticsCompany("1"));
	}
	
	/**
	 * 更新物流公司设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）测试
	 */
	@Test
	public void testUpdateLogisticsCompanyFieldById()
	{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ids", "1,2");
		iLogisticsCompanyDaoMock.returns(1).updateLogisticsCompanyFieldById(map);
		assertEquals(1, iLogisticsCompanyBiz.updateLogisticsCompanyFieldById(map));
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		assertEquals(list, map.get("ids"));
	}
	
	/**
     * 根据物流公司设置对象的单个字段查询物流公司设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
	 */
	@Test
	public void testGetLogisticsCompanyByFieldTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iLogisticsCompanyDaoMock.returns(1).getLogisticsCompanyByFieldTotal(map);
		assertEquals(1, iLogisticsCompanyBiz.getLogisticsCompanyByFieldTotal(map));
	}
	
	/**
     * 根据物流公司设置对象的单个字段查询物流公司设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
	 */
	@Test
	public void testGetLogisticsCompanyByField()
	{
		iLogisticsCompanyDaoMock.returns(1).getLogisticsCompanyByFieldTotal(map);
		iLogisticsCompanyDaoMock.returns(list).getLogisticsCompanyByField(map);
		assertEquals(1, iLogisticsCompanyBiz.getLogisticsCompanyByField(map, pageBean).getList().size());
	}
	
	/**
	 * 查询物流公司设置对象信息总数测试
	 */
	@Test
	public void testQueryLogisticsCompanyTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iLogisticsCompanyDaoMock.returns(1).getLogisticsCompanyByFieldTotal(map);
		iLogisticsCompanyDaoMock.returns(1).queryLogisticsCompanyTotal(map);
		assertEquals(1, iLogisticsCompanyBiz.queryLogisticsCompanyTotal(map));
	}
	
	/**
	 * 分页查询物流公司设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum测试
	 */
	@Test
	public void testQueryLogisticsCompanyByPage()
	{
		iLogisticsCompanyDaoMock.returns(1).queryLogisticsCompanyTotal(map);
		iLogisticsCompanyDaoMock.returns(list).queryLogisticsCompanyByPage(map);
		assertEquals(1, iLogisticsCompanyBiz.queryLogisticsCompanyByPage(map, pageBean).getList().size());
	}
	
	/**
	 * 获得物流公司当前最大排序值
	 */
	@Test
	public void testGetLogisticsCompanyMaxSort()
	{
		iLogisticsCompanyDaoMock.returns(1).getLogisticsCompanyMaxSort();
		assertEquals(1, iLogisticsCompanyBiz.getLogisticsCompanyMaxSort());
	}
	
	/**
	 * 修改物流公司启用状态
	 */
	@Test
	public void testChangeUserdStatus()
	{
		logisticsCompany.setUsedStatus("0");
		iLogisticsCompanyDaoMock.returns(logisticsCompany).getLogisticsCompanyById(1);
		iLogisticsCompanyDaoMock.returns(1).updateLogisticsCompany(logisticsCompany);
		assertEquals(true, iLogisticsCompanyBiz.changeUserdStatus(1L));
		assertEquals("1", logisticsCompany.getUsedStatus());
	}
	
	/**
	 * 验证物流公司是否可删除测试
	 */
	@Test
	public void testCheckDeleteLogistics()
	{
		iExpressConfDaoMock.returns(1).queryExpressCountByLogistics(1L);
		assertEquals(false, iLogisticsCompanyBiz.checkDeleteLogistics(1L));
	}
	
	/**
	 *  查询所有未删除、已启用的物流公司对象信息
	 */
	@Test
	public void testQueryAllLogisticsCompany()
	{
		List<LogisticsCompany> lists = new ArrayList<>();
		lists.add(logisticsCompany);
		iLogisticsCompanyDaoMock.returns(lists).queryAllLogisticsCompany();
		assertEquals(1, iLogisticsCompanyBiz.queryAllLogisticsCompany().size());
	}
	
	/**
	 * 根据主键删除物流公司
	 */
	@Test
	public void testDeleteLogisticsCompanyOne()
	{
		iLogisticsCompanyBiz.deleteLogisticsCompanyOne(1);
	}
}
