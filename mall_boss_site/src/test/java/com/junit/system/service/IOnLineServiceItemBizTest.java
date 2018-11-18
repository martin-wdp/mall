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

import com.ningpai.system.bean.OnLineServiceItem;
import com.ningpai.system.dao.IOnLineServiceItemDao;
import com.ningpai.system.service.IOnLineServiceItemBiz;
import com.ningpai.system.service.impl.OnLineServiceItemBizImpl;
import com.ningpai.util.PageBean;

/**
 * 在线客服项业务接口测试类
 * @author djk
 *
 */
public class IOnLineServiceItemBizTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private IOnLineServiceItemBiz iOnLineServiceItemBiz = new OnLineServiceItemBizImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<IOnLineServiceItemDao> iOnLineServiceItemDaoMock;
	
	/**
	 *  在线客服项
	 */
	private OnLineServiceItem onLineServiceItem;
	
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
		onLineServiceItem = new OnLineServiceItem();
		pageBean = new PageBean();
		
		map = new HashMap<>();
		
		map.put("startRowNum", pageBean.getStartRowNum());
		map.put("endRowNum", pageBean.getEndRowNum());
		
		list = new ArrayList<>();
		list.add(new Object());
	}
	
	/**
	 *  保存在线客服项测试
	 */
	@Test
	public void testSaveOnLineServiceItem()
	{
		iOnLineServiceItemDaoMock.returns(true).saveOnLineServiceItem(onLineServiceItem);
		assertEquals(true, iOnLineServiceItemBiz.saveOnLineServiceItem(onLineServiceItem));
	}
	
	/**
	 * 修改在线客服项测试
	 */
	@Test
	public void testUpdateOnLineServiceItem()
	{
		iOnLineServiceItemDaoMock.returns(1).updateOnLineServiceItem(onLineServiceItem);
		assertEquals(1, iOnLineServiceItemBiz.updateOnLineServiceItem(onLineServiceItem));
	}
	
	/**
	 * 根据在线客服项对象的id查询在线客服项对象测试
	 */
	@Test
	public void testGetOnLineServiceItemById()
	{
		iOnLineServiceItemDaoMock.returns(onLineServiceItem).getOnLineServiceItemById(1);
		assertNotNull(iOnLineServiceItemBiz.getOnLineServiceItemById(1));
	}
	
	/**
	 *  根据在线客服项对象的id字符集合查询在线客服项对象测试
	 */
	@Test
	public void testGetOnLineServiceItemByIds()
	{
		 List<OnLineServiceItem> lists = new ArrayList<>();
		 lists.add(onLineServiceItem);
		 iOnLineServiceItemDaoMock.returns(lists).getOnLineServiceItemByIds("1");
		 assertEquals(1, iOnLineServiceItemBiz.getOnLineServiceItemByIds("1").size());
	}
	
	/**
	 *  根据在线客服项对象的id字符集合删除在线客服项对象
	 */
	@Test
	public void testDeleteOnLineServiceItem()
	{
		iOnLineServiceItemDaoMock.returns(1).deleteOnLineServiceItem("1");
		assertEquals(1, iOnLineServiceItemBiz.deleteOnLineServiceItem("1"));
	}
	
	/**
	 * 更新在线客服项对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）测试
	 */
	@Test
	public void testUpdateOnLineServiceItemFieldById()
	{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ids", "1,2");
		iOnLineServiceItemDaoMock.returns(1).updateOnLineServiceItemFieldById(map);
		assertEquals(1, iOnLineServiceItemBiz.updateOnLineServiceItemFieldById(map));
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		assertEquals(list, map.get("ids"));
	}
	
	/**
     * 根据在线客服项对象的单个字段查询在线客服项对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
	 */
	@Test
	public void testGetOnLineServiceItemByFieldTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1","1");
		iOnLineServiceItemDaoMock.returns(1).getOnLineServiceItemByFieldTotal(map);
		assertEquals(1, iOnLineServiceItemBiz.getOnLineServiceItemByFieldTotal(map));
	}
	
	
	/**
     * 根据在线客服项对象的单个字段查询在线客服项对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
	 */
	@Test
	public void testGetOnLineServiceItemByField()
	{
		iOnLineServiceItemDaoMock.returns(1).getOnLineServiceItemByFieldTotal(map);
		iOnLineServiceItemDaoMock.returns(list).getOnLineServiceItemByField(map);
		assertEquals(1, iOnLineServiceItemBiz.getOnLineServiceItemByField(map, pageBean).getList().size());
	}
	
	/**
	 * 查询在线客服项对象信息总数测试
	 */
	@Test
	public void testQueryOnLineServiceItemTotal()
	{	
		Map<String, Object> map = new HashMap<>();
		map.put("1","1");
		iOnLineServiceItemDaoMock.returns(1).queryOnLineServiceItemTotal(map);
		assertEquals(1, iOnLineServiceItemBiz.queryOnLineServiceItemTotal(map));
	}
	
	/**
	 * 分页查询在线客服项对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum测试
	 */
	@Test
	public void testQueryOnLineServiceItemByPage()
	{
		iOnLineServiceItemDaoMock.returns(1).queryOnLineServiceItemTotal(map);
		iOnLineServiceItemDaoMock.returns(list).queryOnLineServiceItemByPage(map);
		assertEquals(1, iOnLineServiceItemBiz.queryOnLineServiceItemByPage(map, pageBean).getList().size());
	}
	
	/**
	 *  根据在线客服id删除在线客服项测试
	 */
	@Test
	public void testDeleteOnLineServiceItemById()
	{
		iOnLineServiceItemDaoMock.returns(1).deleteOnLineServiceItem(1);
		assertEquals(1, iOnLineServiceItemBiz.deleteOnLineServiceItem(1));
	}
	
	/**
	 *  根据在线客服信息ID查询客服数量测试
	 */
	@Test
	public void testSelectCountByOnLineService()
	{
		iOnLineServiceItemDaoMock.returns(1).selectCountByOnLineService(1);
		assertEquals(1, iOnLineServiceItemBiz.selectCountByOnLineService(1));
	}
	
	/**
	 * 根据主键删除测试
	 */
	@Test
	public void testDelOnLineServiceItem()
	{
		iOnLineServiceItemDaoMock.returns(1).delOnLineServiceItem(1);
		assertEquals(1, iOnLineServiceItemBiz.delOnLineServiceItem(1));
	}
	
	/**
	 *  排序上移测试
	 */
	@Test
	public void testUpItem()
	{
		OnLineServiceItem item1 = new OnLineServiceItem();
		OnLineServiceItem item2 = new OnLineServiceItem();
		iOnLineServiceItemDaoMock.returns(item1).getOnLineServiceItemById(1);
		iOnLineServiceItemDaoMock.returns(item2).getOnLineServiceItemById(2);
		iOnLineServiceItemDaoMock.returns(1).updateOnLineServiceItem(item1);
		iOnLineServiceItemDaoMock.returns(1).updateOnLineServiceItem(item2);
		assertEquals(true, iOnLineServiceItemBiz.upItem(1, 2));
	}
	
	/**
	 * 排序下移测试
	 */
	@Test
	public void testDownItem()
	{
		OnLineServiceItem item1 = new OnLineServiceItem();
		OnLineServiceItem item2 = new OnLineServiceItem();
		iOnLineServiceItemDaoMock.returns(item1).getOnLineServiceItemById(1);
		iOnLineServiceItemDaoMock.returns(item2).getOnLineServiceItemById(2);
		iOnLineServiceItemDaoMock.returns(1).updateOnLineServiceItem(item1);
		iOnLineServiceItemDaoMock.returns(1).updateOnLineServiceItem(item2);
		assertEquals(true, iOnLineServiceItemBiz.downItem(1, 2));
	}
	
	/**
	 *  根据类型查询平台客服
	 */
	@Test
	public void testSelectItemsByType()
	{
		List<OnLineServiceItem> list = new ArrayList<>();
		list.add(onLineServiceItem);
		iOnLineServiceItemDaoMock.returns(list).selectItemsByType(1);
		assertEquals(1, iOnLineServiceItemBiz.selectItemsByType(1).size());
	}
}
