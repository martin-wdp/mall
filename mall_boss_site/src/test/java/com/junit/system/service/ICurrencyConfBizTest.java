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

import com.ningpai.system.bean.CurrencyConf;
import com.ningpai.system.dao.ICurrencyConfDao;
import com.ningpai.system.service.ICurrencyConfBiz;
import com.ningpai.system.service.impl.CurrencyConfBizImpl;
import com.ningpai.util.PageBean;


/**
 *  货币设置业务接口测试类
 * @author djk
 *
 */
public class ICurrencyConfBizTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private ICurrencyConfBiz iCurrencyConfBiz = new CurrencyConfBizImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<ICurrencyConfDao> iCurrencyConfDaoMock;
	
	/**
	 * 货币设置实体
	 */
	private CurrencyConf currencyConf;

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
		currencyConf = new CurrencyConf();
		pageBean = new PageBean();
		
		map = new HashMap<>();
		
		map.put("startRowNum", pageBean.getStartRowNum());
		map.put("endRowNum", pageBean.getEndRowNum());
		
		list = new ArrayList<>();
		list.add(new Object());
	}
	
	/**
	 * 保存货币设置测试
	 */
	@Test
	public void testSaveCurrencyConf()
	{
		iCurrencyConfDaoMock.returns(true).saveCurrencyConf(currencyConf);
		assertEquals(true, iCurrencyConfBiz.saveCurrencyConf(currencyConf));
	}
	
	/**
	 *  修改货币设置测试
	 */
	@Test
	public void testUpdateCurrencyConf()
	{
		iCurrencyConfDaoMock.returns(1).updateCurrencyConf(currencyConf);
		assertEquals(1, iCurrencyConfBiz.updateCurrencyConf(currencyConf));
	}
	
	/**
	 * 根据货币设置对象的id查询货币设置对象测试
	 */
	@Test
	public void testGetCurrencyConfById()
	{
		iCurrencyConfDaoMock.returns(currencyConf).getCurrencyConfById(1);
		assertNotNull(iCurrencyConfBiz.getCurrencyConfById(1));
	}
	
	/**
	 * 根据货币设置对象的id字符集合查询货币设置对象测试
	 */
	@Test
	public void testGetCurrencyConfByIds()
	{
		List<CurrencyConf> list = new ArrayList<>();
		list.add(currencyConf);
		iCurrencyConfDaoMock.returns(list).getCurrencyConfByIds("1");
		assertEquals(1, iCurrencyConfBiz.getCurrencyConfByIds("1").size());
	}
	
	/**
	 * 根据货币设置对象的id字符集合删除货币设置对象测试
	 */
	@Test
	public void testDeleteCurrencyConf()
	{
		iCurrencyConfDaoMock.returns(1).deleteCurrencyConf("1");
		assertEquals(1, iCurrencyConfBiz.deleteCurrencyConf("1"));
	}
	
	
	/**
	 * 更新货币设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）测试
	 */
	@Test
	public void testUpdateCurrencyConfFieldById()
	{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ids", "1,2");
		iCurrencyConfDaoMock.returns(1).updateCurrencyConfFieldById(map);
		assertEquals(1, iCurrencyConfBiz.updateCurrencyConfFieldById(map));
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		assertEquals(list, map.get("ids"));
	}
	
	/**
	 * 根据货币设置对象的单个字段查询货币设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
	 */
	@Test
	public void testGetCurrencyConfByFieldTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iCurrencyConfDaoMock.returns(1).getCurrencyConfByFieldTotal(map);
		assertEquals(1, iCurrencyConfBiz.getCurrencyConfByFieldTotal(map));
	}
	
	/**
	 *根据货币设置对象的单个字段查询货币设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
	 */
	@Test
	public void testGetCurrencyConfByField()
	{
		iCurrencyConfDaoMock.returns(1).getCurrencyConfByFieldTotal(map);
		iCurrencyConfDaoMock.returns(list).getCurrencyConfByField(map);
		assertEquals(1,iCurrencyConfBiz.getCurrencyConfByField(map, pageBean).getList().size());
	}
	
	/**
	 *  查询货币设置对象信息总数测试
	 */
	@Test
	public void testQueryCurrencyConfTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iCurrencyConfDaoMock.returns(1).queryCurrencyConfTotal(map);
		assertEquals(1, iCurrencyConfBiz.queryCurrencyConfTotal(map));
	}
	
	/**
	 * 分页查询货币设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
	 */
	@Test
	public void testQueryCurrencyConfByPage()
	{
		iCurrencyConfDaoMock.returns(1).queryCurrencyConfTotal(map);
		iCurrencyConfDaoMock.returns(list).queryCurrencyConfByPage(map);
		assertEquals(1, iCurrencyConfBiz.queryCurrencyConfByPage(map, pageBean).getList().size());
	}
}
