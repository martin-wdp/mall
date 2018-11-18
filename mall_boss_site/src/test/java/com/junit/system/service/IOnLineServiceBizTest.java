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

import com.ningpai.system.bean.OnLineService;
import com.ningpai.system.dao.IOnLineServiceDao;
import com.ningpai.system.service.IOnLineServiceBiz;
import com.ningpai.system.service.impl.OnLineServiceBizImpl;
import com.ningpai.util.PageBean;

/**
 * 在线客服业务接口测试类
 * @author djk
 *
 */
public class IOnLineServiceBizTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private IOnLineServiceBiz iOnLineServiceBiz = new OnLineServiceBizImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<IOnLineServiceDao> iOnLineServiceDaoMock;
	
	/**
	 * 在线客服实体
	 */
	private OnLineService onLineService;
	
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
		onLineService = new OnLineService();
		pageBean = new PageBean();
		
		map = new HashMap<>();
		
		map.put("startRowNum", pageBean.getStartRowNum());
		map.put("endRowNum", pageBean.getEndRowNum());
		
		list = new ArrayList<>();
		list.add(new Object());
	}
	
	/**
	 * 保存在线客服测试
	 */
	@Test
	public void testSaveOnLineService()
	{
		iOnLineServiceDaoMock.returns(1).saveOnLineService(onLineService);
		assertEquals(1, iOnLineServiceBiz.saveOnLineService(onLineService));
	}
	
	/**
	 * 修改在线客服
	 */
	@Test
	public void testUpdateOnLineService()
	{
		iOnLineServiceDaoMock.returns(1).updateOnLineService(onLineService);
		assertEquals(1, iOnLineServiceBiz.updateOnLineService(onLineService));
	}
	
	/**
	 * 根据在线客服对象的id查询在线客服对象测试
	 */
	@Test
	public void testGetOnLineServiceById()
	{
		iOnLineServiceDaoMock.returns(onLineService).getOnLineServiceById(1);
		assertNotNull(iOnLineServiceBiz.getOnLineServiceById(1));
	}
	
	/**
	 * 根据在线客服对象的id字符集合查询在线客服对象测试
	 */
	@Test
	public void testGetOnLineServiceByIds()
	{
		List<OnLineService> lists = new ArrayList<>();
		lists.add(onLineService);
		iOnLineServiceDaoMock.returns(lists).getOnLineServiceByIds("1");
		assertEquals(1, iOnLineServiceBiz.getOnLineServiceByIds("1").size());
	}
	
	/**
	 * 根据在线客服对象的id字符集合删除在线客服对象测试
	 */
	@Test
	public void testDeleteOnLineService()
	{
		iOnLineServiceDaoMock.returns(1).deleteOnLineService("1");
		assertEquals(1, iOnLineServiceBiz.deleteOnLineService("1"));
	}
	
	/**
	 * 更新在线客服对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）测试
	 */
	@Test
	public void testUpdateOnLineServiceFieldById()
	{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ids", "1,2");
		iOnLineServiceDaoMock.returns(1).updateOnLineServiceFieldById(map);
		assertEquals(1, iOnLineServiceBiz.updateOnLineServiceFieldById(map));
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		assertEquals(list, map.get("ids"));
	}
	
	/**
     * 根据在线客服对象的单个字段查询在线客服对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
	 */
	@Test
	public void testGetOnLineServiceByFieldTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1","1");
		iOnLineServiceDaoMock.returns(1).getOnLineServiceByFieldTotal(map);
		assertEquals(1, iOnLineServiceBiz.getOnLineServiceByFieldTotal(map));
	}
	
	/**
     * 根据在线客服对象的单个字段查询在线客服对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
	 */
	@Test
	public void testGetOnLineServiceByField()
	{
		iOnLineServiceDaoMock.returns(1).getOnLineServiceByFieldTotal(map);
		iOnLineServiceDaoMock.returns(list).getOnLineServiceByField(map);
		assertEquals(1, iOnLineServiceBiz.getOnLineServiceByField(map, pageBean).getList().size());
	}
	
	/**
	 *  查询在线客服对象信息总数
	 */
	@Test
	public void testQueryOnLineServiceTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1","1");
		iOnLineServiceDaoMock.returns(1).queryOnLineServiceTotal(map);
		assertEquals(1, iOnLineServiceBiz.queryOnLineServiceTotal(map));
	}
	
	/**
	 * 分页查询在线客服对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum测试
	 */
	@Test
	public void testQueryOnLineServiceByPage()
	{
		iOnLineServiceDaoMock.returns(1).queryOnLineServiceTotal(map);
		iOnLineServiceDaoMock.returns(list).queryOnLineServiceByPage(map);
		assertEquals(1, iOnLineServiceBiz.queryOnLineServiceByPage(map, pageBean).getList().size());
	}
	
	/**
	 *  查询在线客服设置
	 */
	@Test
	public void testSelectSetting()
	{
		iOnLineServiceDaoMock.returns(onLineService).selectSetting();
		assertNotNull(iOnLineServiceBiz.selectSetting());
	}
}
