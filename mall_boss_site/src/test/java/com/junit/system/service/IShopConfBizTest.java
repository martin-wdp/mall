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

import com.ningpai.system.bean.ShopConf;
import com.ningpai.system.dao.IShopConfDao;
import com.ningpai.system.service.IShopConfBiz;
import com.ningpai.system.service.impl.ShopConfBizImpl;
import com.ningpai.util.PageBean;


/**
 * 购物设置业务接口测试类
 * @author djk
 *
 */
public class IShopConfBizTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private IShopConfBiz iShopConfBiz = new ShopConfBizImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<IShopConfDao> iShopConfDaoMock;
	
	/**
	 * 购物设置实体
	 */
	private ShopConf shopConf;
	
	
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
		shopConf = new ShopConf();
		pageBean = new PageBean();
		map = new HashMap<>();
		
		map.put("startRowNum", pageBean.getStartRowNum());
		map.put("endRowNum", pageBean.getEndRowNum());
		
		list = new ArrayList<>();
		list.add(new Object());
	}
	
	/**
	 * 保存购物设置测试
	 */
	@Test
	public void testSaveShopConf()
	{
		iShopConfDaoMock.returns(true).saveShopConf(shopConf);
		assertEquals(true, iShopConfBiz.saveShopConf(shopConf));
	}
	
	/**
	 * 修改购物设置测试
	 */
	@Test
	public void testUpdateShopConf()
	{
		iShopConfDaoMock.returns(1).updateShopConf(shopConf);
		assertEquals(1, iShopConfBiz.updateShopConf(shopConf));
	}
	
	/**
	 * 根据购物设置对象的id查询购物设置对象测试 
	 */
	@Test
	public void testGetShopConfById()
	{
		iShopConfDaoMock.returns(shopConf).getShopConfById(1);
		assertNotNull(iShopConfBiz.getShopConfById(1));
	}
	
	/**
	 *  根据购物设置对象的id字符集合查询购物设置对象
	 */
	@Test
	public void testGetShopConfByIds()
	{
		List<ShopConf> lists = new ArrayList<>();
		lists.add(shopConf);
		iShopConfDaoMock.returns(lists).getShopConfByIds("1");
		assertEquals(1, iShopConfBiz.getShopConfByIds("1").size());
	}
	
	/**
	 * 根据购物设置对象的id字符集合删除购物设置对象测试
	 */
	@Test
	public void testDeleteShopConf()
	{
		iShopConfDaoMock.returns(1).deleteShopConf("1");
		assertEquals(1, iShopConfBiz.deleteShopConf("1"));
	}
	
	/**
	 * 更新购物设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）c测试
	 */
	@Test
	public void testUpdateShopConfFieldById()
	{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ids", "1,2");
		iShopConfDaoMock.returns(1).updateShopConfFieldById(map);
		assertEquals(1, iShopConfBiz.updateShopConfFieldById(map));
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		assertEquals(list, map.get("ids"));
	}
	
	/**
     * 根据购物设置对象的单个字段查询购物设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
	 */
	@Test
	public void testGetShopConfByFieldTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iShopConfDaoMock.returns(1).getShopConfByFieldTotal(map);
		assertEquals(1, iShopConfBiz.getShopConfByFieldTotal(map));
	}
	
	/**
     * 根据购物设置对象的单个字段查询购物设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
	 */
	@Test
	public void testGetShopConfByField()
	{
		iShopConfDaoMock.returns(1).getShopConfByFieldTotal(map);
		iShopConfDaoMock.returns(list).getShopConfByField(map);
		assertEquals(1, iShopConfBiz.getShopConfByField(map, pageBean).getList().size());
	}
	
	/**
	 * 查询购物设置对象信息总数测试
	 */
	@Test
	public void testQueryShopConfTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iShopConfDaoMock.returns(1).queryShopConfTotal(map);
		assertEquals(1, iShopConfBiz.queryShopConfTotal(map));
	}
	
	/**
	 * 分页查询购物设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum测试
	 */
	@Test
	public void testQueryShopConfByPage()
	{
		iShopConfDaoMock.returns(1).queryShopConfTotal(map);
		iShopConfDaoMock.returns(list).queryShopConfByPage(map);
		assertEquals(1, iShopConfBiz.queryShopConfByPage(map, pageBean).getList().size());
	}
}
