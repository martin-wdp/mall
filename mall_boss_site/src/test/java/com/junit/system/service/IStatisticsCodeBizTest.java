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

import com.ningpai.system.bean.StatisticsCode;
import com.ningpai.system.dao.IStatisticsCodeDao;
import com.ningpai.system.service.IStatisticsCodeBiz;
import com.ningpai.system.service.impl.StatisticsCodeBizImpl;
import com.ningpai.util.PageBean;

/**
 *  统计代码业务接口测试类
 * @author djk
 *
 */
/**
 * @author NP-Heh
 *
 */
public class IStatisticsCodeBizTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private IStatisticsCodeBiz iStatisticsCodeBiz = new StatisticsCodeBizImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<IStatisticsCodeDao> iStatisticsCodeDaoMock;
	
	/**
	 * 统计代码实体类
	 */
	private StatisticsCode statisticsCode;
	
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
		statisticsCode = new StatisticsCode();
		pageBean = new PageBean();
		map = new HashMap<>();
		
		map.put("startRowNum", pageBean.getStartRowNum());
		map.put("endRowNum", pageBean.getEndRowNum());
		
		list = new ArrayList<>();
		list.add(new Object());
	}
	
	/**
	 * 保存统计代码测试
	 */
	@Test
	public void testSaveStatisticsCode()
	{
		iStatisticsCodeDaoMock.returns(true).saveStatisticsCode(statisticsCode);
		assertEquals(true, iStatisticsCodeBiz.saveStatisticsCode(statisticsCode));
	}
	
	/**
	 * 修改统计代码测试
	 */
	@Test
	public void testUpdateStatisticsCode()
	{
		iStatisticsCodeDaoMock.returns(1).updateStatisticsCode(statisticsCode);
		assertEquals(1, iStatisticsCodeBiz.updateStatisticsCode(statisticsCode));
	}
	
	
	/**
	 * 根据统计代码对象的id查询统计代码对象测试
	 */
	@Test
	public void testGetStatisticsCodeById()
	{
		iStatisticsCodeDaoMock.returns(statisticsCode).getStatisticsCodeById(1);
		assertNotNull(iStatisticsCodeBiz.getStatisticsCodeById(1));
	}
	
	/**
	 * 根据统计代码对象的id字符集合查询统计代码对象
	 */
	@Test
	public void testGetStatisticsCodeByIds()
	{
		 List<StatisticsCode>  lists = new ArrayList<>();
		 lists.add(statisticsCode);
		 iStatisticsCodeDaoMock.returns(lists).getStatisticsCodeByIds("1");
		 assertEquals(1, iStatisticsCodeBiz.getStatisticsCodeByIds("1").size());
	}
	
	/**
	 * 根据统计代码对象的id字符集合删除统计代码对象测试
	 */
	@Test
	public void testDeleteStatisticsCode()
	{
		iStatisticsCodeDaoMock.returns(1).deleteStatisticsCode("1");
		assertEquals(1, iStatisticsCodeBiz.deleteStatisticsCode("1"));
	}
	
	/**
	 * 更新统计代码对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）测试
	 */
	@Test
	public void testUpdateStatisticsCodeFieldById()
	{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ids", "1,2");
		iStatisticsCodeDaoMock.returns(1).updateStatisticsCodeFieldById(map);
		assertEquals(1, iStatisticsCodeBiz.updateStatisticsCodeFieldById(map));
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		assertEquals(list, map.get("ids"));
	}
	
	/**
     * 根据统计代码对象的单个字段查询统计代码对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
	 */
	@Test
	public void testGetStatisticsCodeByFieldTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iStatisticsCodeDaoMock.returns(1).getStatisticsCodeByFieldTotal(map);
		assertEquals(1, iStatisticsCodeBiz.getStatisticsCodeByFieldTotal(map));
	}
	
	/**
     * 根据统计代码对象的单个字段查询统计代码对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
	 */
	@Test
	public void testGetStatisticsCodeByField()
	{
		iStatisticsCodeDaoMock.returns(1).getStatisticsCodeByFieldTotal(map);
		iStatisticsCodeDaoMock.returns(list).getStatisticsCodeByField(map);
		assertEquals(1, iStatisticsCodeBiz.getStatisticsCodeByField(map, pageBean).getList().size());
	}
	
	/**
	 * 查询统计代码对象信息总数测试
	 */
	@Test
	public void testQueryStatisticsCodeTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iStatisticsCodeDaoMock.returns(1).queryStatisticsCodeTotal(map);
		assertEquals(1, iStatisticsCodeBiz.queryStatisticsCodeTotal(map));
	}
	
	/**
	 * 分页查询统计代码对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum测试
	 */
	@Test
	public void testQueryStatisticsCodeByPage()
	{
		iStatisticsCodeDaoMock.returns(1).queryStatisticsCodeTotal(map);
		iStatisticsCodeDaoMock.returns(list).queryStatisticsCodeByPage(map);
		assertEquals(1, iStatisticsCodeBiz.queryStatisticsCodeByPage(map, pageBean).getList().size());
	}
	
	/**
	 * 获取当前统计代码
	 */
	@Test
	public void testGetCurrStatisticsCode()
	{
		 List<StatisticsCode> lists = new ArrayList<>();
		 lists.add(statisticsCode);
		 iStatisticsCodeDaoMock.returns(lists).selectAllStatisticsCode();
		 assertEquals(1, iStatisticsCodeBiz.getCurrStatisticsCode().size());
	}
	
	/**
	 * 修改统计代码启用状态测试
	 */
	@Test
	public void testChangeUserdStatus()
	{
		statisticsCode.setUsedStatus("0");
		iStatisticsCodeDaoMock.returns(statisticsCode).getStatisticsCodeById(1);
		iStatisticsCodeDaoMock.returns(1).updateStatisticsCode(statisticsCode);
		assertEquals(true, iStatisticsCodeBiz.changeUserdStatus(1L));
		assertEquals("1", statisticsCode.getUsedStatus());
	}
}
