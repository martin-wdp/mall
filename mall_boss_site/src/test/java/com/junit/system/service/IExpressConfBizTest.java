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

import com.ningpai.system.bean.ExpressConf;
import com.ningpai.system.bean.LogisticsCompany;
import com.ningpai.system.dao.IExpressConfDao;
import com.ningpai.system.dao.ILogisticsCompanyDao;
import com.ningpai.system.service.IExpressConfBiz;
import com.ningpai.system.service.impl.ExpressConfBizImpl;
import com.ningpai.util.PageBean;

/**
 * 配送方式设置业务接口测试类
 * @author djk
 *
 */
public class IExpressConfBizTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private IExpressConfBiz iExpressConfBiz = new ExpressConfBizImpl();
	
	/**
	 * 配送方式设置实体
	 */
	private ExpressConf expressConf;
	
	/**
	 * 分页辅助类实体
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
		expressConf = new ExpressConf();
		pageBean = new PageBean();
		
		map = new HashMap<>();
		
		map.put("startRowNum", pageBean.getStartRowNum());
		map.put("endRowNum", pageBean.getEndRowNum());
		
		list = new ArrayList<>();
		list.add(new Object());
	}
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<IExpressConfDao> iExpressConfDaoMock;
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<ILogisticsCompanyDao> iLogisticsCompanyDaoMock;
	
	/**
	 *  保存配送方式设置测试
	 */
	@Test
	public void testSaveExpressConf()
	{
		iExpressConfDaoMock.returns(true).saveExpressConf(expressConf);
		assertEquals(true, iExpressConfBiz.saveExpressConf(expressConf));
	}
	
	/**
	 * 修改配送方式设置测试
	 */
	@Test
	public void testUpdateExpressConf()
	{
		iExpressConfDaoMock.returns(1).updateExpressConf(expressConf);
		assertEquals(1, iExpressConfBiz.updateExpressConf(expressConf));
	}
	
	/**
	 * 根据配送方式设置对象的id查询配送方式设置对象测试
	 */
	@Test
	public void testGetExpressConfById()
	{
		iExpressConfDaoMock.returns(expressConf).getExpressConfById(1);
		assertNotNull(iExpressConfBiz.getExpressConfById(1));
	}
	
	/**
	 * 根据配送方式设置对象的id字符集合查询配送方式设置对象测试
	 */
	@Test
	public void testGetExpressConfByIds()
	{
		List<ExpressConf> list = new ArrayList<>();
		list.add(expressConf);
		iExpressConfDaoMock.returns(list).getExpressConfByIds("1");
		assertEquals(1, iExpressConfBiz.getExpressConfByIds("1").size());
	}
	
	/**
	 * 根据配送方式设置对象的id字符集合删除配送方式设置对象测试
	 */
	@Test
	public void testDeleteExpressConf()
	{
		iExpressConfDaoMock.returns(1).deleteExpressConf("1");
		assertEquals(1, iExpressConfBiz.deleteExpressConf("1"));
	}
	
	
	/**
	 *  更新配送方式设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）测试
	 */
	@Test
	public void testUpdateExpressConfFieldById()
	{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ids", "1,2");
		iExpressConfDaoMock.returns(1).updateExpressConfFieldById(map);
		assertEquals(1, iExpressConfBiz.updateExpressConfFieldById(map));
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		assertEquals(list, map.get("ids"));
	}
	
	/**
	 *  根据配送方式设置对象的单个字段查询配送方式设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
	 */
	@Test
	public void testGetExpressConfByFieldTotal()
	{
		
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iExpressConfDaoMock.returns(1).getExpressConfByFieldTotal(map);
		assertEquals(1, iExpressConfBiz.getExpressConfByFieldTotal(map));
	}
	
	/**
     * 根据配送方式设置对象的单个字段查询配送方式设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
	 */
	@Test
	public void testGetExpressConfByField()
	{
		iExpressConfDaoMock.returns(1).getExpressConfByFieldTotal(map);
		iExpressConfDaoMock.returns(list).getExpressConfByField(map);
		assertEquals(1, iExpressConfBiz.getExpressConfByField(map, pageBean).getList().size());
	}
	
	/**
	 * 查询所有的配送方式测试
	 */
	@Test
	public void testQueryAllExpress()
	{
		Map<String, Object> reslutMap = new HashMap<>();
		reslutMap.put("1", "1");
		
		Map<String, Object> map = new HashMap<String, Object>();
        // 分页查询开始位置
        map.put("startRowNum", 0);
        // 分页查询结束位置
        map.put("endRowNum", 1000);
        map.put("deleteStatus", 0);
        // 分页查询
        map.put("usedStatus", 1);
        iExpressConfDaoMock.returns(new ArrayList<ExpressConf>()).getExpressConfByField(map);
        iLogisticsCompanyDaoMock.returns(new ArrayList<LogisticsCompany>()).getLogisticsCompanyByField(map);
        map.put("expresss", "1");
        map.put("expressCompany",  "1");
		assertEquals(6, iExpressConfBiz.queryAllExpress().size());
	}
	
	/**
	 * 得到启用后的上门自提信息
	 */
	@Test
	public void testGetExpressConfByUsedField()
	{
		iExpressConfDaoMock.returns(expressConf).getExpressConfByUsedField();
		assertNotNull(iExpressConfBiz.getExpressConfByUsedField());
	}
	
	/**
	 * 查询配送方式设置对象信息总数测试
	 */
	@Test
	public void testQueryExpressConfTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iExpressConfDaoMock.returns(1).queryExpressConfTotal(map);
		assertEquals(1, iExpressConfBiz.queryExpressConfTotal(map));
	}
	
	/**
	 * 分页查询配送方式设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum测试
	 */
	@Test
	public void testQueryExpressConfByPage()
	{
		iExpressConfDaoMock.returns(1).queryExpressConfTotal(map);
		iExpressConfDaoMock.returns(list).queryExpressConfByPage(map);
		assertEquals(1, iExpressConfBiz.queryExpressConfByPage(map, pageBean).getList().size());
	}
	
	/**
	 * 根据配送方式查询物流公司快递100代码测试
	 */
	@Test
	public void testQueryKuaidi100CodeByExpressId()
	{
		iExpressConfDaoMock.returns("100").queryKuaidi100CodeByExpressId(1L);
		assertEquals("100", iExpressConfBiz.queryKuaidi100CodeByExpressId(1L));
	}
	
	/**
	 *  修改配送方式启用状态测试
	 */
	@Test
	public void testChangeUserdStatus()
	{
		expressConf.setUsedStatus("0");
		iExpressConfDaoMock.returns(expressConf).getExpressConfById(1);
		iExpressConfDaoMock.returns(1).updateExpressConf(expressConf);
		assertEquals(true, iExpressConfBiz.changeUserdStatus(1L));
		assertEquals("1", expressConf.getUsedStatus());
	}
	
	/**
	 * 根据配送方式获取物流公司
	 */
	@Test
	public void testGetLogisticsByExpressId()
	{
		iExpressConfDaoMock.returns(expressConf).getExpressConfById(1);
		iLogisticsCompanyDaoMock.returns(new LogisticsCompany()).getLogisticsCompanyById(0);
		assertNotNull(iExpressConfBiz.getLogisticsByExpressId(1L));
	}
	
	/**
	 *  删除单个配送方式
	 */
	@Test
	public void testDeleteOneExpressConf()
	{
		iExpressConfDaoMock.returns(1).updateExpressConf(expressConf);
		assertEquals(1, iExpressConfBiz.deleteOneExpressConf(1));
	}
}
