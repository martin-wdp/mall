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

import com.ningpai.system.bean.UpyunConf;
import com.ningpai.system.dao.IUpyunConfDao;
import com.ningpai.system.service.IUpyunConfBiz;
import com.ningpai.system.service.impl.UpyunConfBizImpl;
import com.ningpai.util.PageBean;


/**
 * 又拍云设置业务接口测试类
 * @author djk
 *
 */
public class IUpyunConfBizTest   extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private IUpyunConfBiz iUpyunConfBiz = new UpyunConfBizImpl();
	
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<IUpyunConfDao> iUpyunConfDaoMock;
	
	/**
	 * 又拍云设置
	 */
	private UpyunConf upyunConf;
	

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
		upyunConf = new UpyunConf();
		pageBean = new PageBean();
		map = new HashMap<>();
		
		map.put("startRowNum", pageBean.getStartRowNum());
		map.put("endRowNum", pageBean.getEndRowNum());
		
		list = new ArrayList<>();
		list.add(new Object());
	}
	
	/**
	 * 保存又拍云设置测试
	 */
	@Test
	public void testSaveUpyunConf()
	{
		iUpyunConfDaoMock.returns(true).saveUpyunConf(upyunConf);
		assertEquals(true, iUpyunConfBiz.saveUpyunConf(upyunConf));
	}
	
	/**
	 *  修改又拍云设置
	 */
	@Test
	public void testUpdateUpyunConf()
	{
		iUpyunConfDaoMock.returns(1).updateUpyunConf(upyunConf);
		assertEquals(1, iUpyunConfBiz.updateUpyunConf(upyunConf));
	}
	
	/**
	 * 根据又拍云设置对象的id查询又拍云设置对象测试 
	 */
	@Test
	public void testGetUpyunConfById()
	{
		iUpyunConfDaoMock.returns(upyunConf).getUpyunConfById(1);
		assertNotNull(iUpyunConfBiz.getUpyunConfById(1));
	}
	
	/**
	 * 根据又拍云设置对象的id字符集合查询又拍云设置对象测试
	 */
	@Test
	public void testGetUpyunConfByIds()
	{
		List<UpyunConf> lists = new ArrayList<>();
		lists.add(upyunConf);
		iUpyunConfDaoMock.returns(lists).getUpyunConfByIds("1");
		assertEquals(1, iUpyunConfBiz.getUpyunConfByIds("1").size());
	}
	
	/**
	 * 根据又拍云设置对象的id字符集合删除又拍云设置对象测试
	 */
	@Test
	public void testDeleteUpyunConf()
	{
		iUpyunConfDaoMock.returns(1).deleteUpyunConf("1");
		assertEquals(1, iUpyunConfBiz.deleteUpyunConf("1"));
	}
	
	/**
	 *  更新又拍云设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）测试
	 */
	@Test
	public void testUpdateUpyunConfFieldById()
	{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ids", "1,2");
		iUpyunConfDaoMock.returns(1).updateUpyunConfFieldById(map);
		assertEquals(1, iUpyunConfBiz.updateUpyunConfFieldById(map));
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		assertEquals(list, map.get("ids"));
	}
	
	/**
     * 根据又拍云设置对象的单个字段查询又拍云设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
	 */
	@Test
	public void testGetUpyunConfByFieldTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iUpyunConfDaoMock.returns(1).getUpyunConfByFieldTotal(map);
		assertEquals(1, iUpyunConfBiz.getUpyunConfByFieldTotal(map));
	}
	
	/**
     * 根据又拍云设置对象的单个字段查询又拍云设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
	 */
	@Test
	public void testGetUpyunConfByField()
	{
		iUpyunConfDaoMock.returns(1).getUpyunConfByFieldTotal(map);
		iUpyunConfDaoMock.returns(list).getUpyunConfByField(map);
		assertEquals(1, iUpyunConfBiz.getUpyunConfByField(map, pageBean).getList().size());
	}

	/**
	 *  查询又拍云设置对象信息总数测试
	 */
	@Test
	public void testQueryUpyunConfTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iUpyunConfDaoMock.returns(1).queryUpyunConfTotal(map);
		assertEquals(1, iUpyunConfBiz.queryUpyunConfTotal(map));
	}
	
	/**
	 * 分页查询又拍云设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum测试
	 */
	@Test
	public void testQueryUpyunConfByPage()
	{
		iUpyunConfDaoMock.returns(1).queryUpyunConfTotal(map);
		iUpyunConfDaoMock.returns(list).queryUpyunConfByPage(map);
		assertEquals(1, iUpyunConfBiz.queryUpyunConfByPage(map, pageBean).getList().size());
	}
	
}
