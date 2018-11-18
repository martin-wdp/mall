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

import com.ningpai.system.bean.AuthCheckConf;
import com.ningpai.system.dao.IAuthCheckConfDao;
import com.ningpai.system.service.IAuthCheckConfBiz;
import com.ningpai.system.service.impl.AuthCheckConfBizImpl;
import com.ningpai.util.PageBean;

/**
 * 验证设置业务接口测试类
 * @author djk
 *
 */
public class IAuthCheckConfBizTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private IAuthCheckConfBiz iAuthCheckConfBiz =new  AuthCheckConfBizImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<IAuthCheckConfDao> iAuthCheckConfDaoMock;
	
	/**
	 * 验证设置
	 */
	private AuthCheckConf authCheckConf;
	
	/**
	 * 分页辅助类
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
		authCheckConf = new AuthCheckConf();
		pageBean = new PageBean();
		
		map = new HashMap<>();
			
		map.put("startRowNum", pageBean.getStartRowNum());
		map.put("endRowNum", pageBean.getEndRowNum());
		
		list = new ArrayList<>();
		list.add(new Object());
			
	}
	
	/**
	 * 保存验证设置测试
	 */
	@Test
	public void testSaveAuthCheckConf()
	{
		iAuthCheckConfDaoMock.returns(true).saveAuthCheckConf(authCheckConf);
		assertEquals(true, iAuthCheckConfBiz.saveAuthCheckConf(authCheckConf));
	}
	
	/**
	 * 修改验证设置测试
	 */
	@Test
	public void testUpdateAuthCheckConf()
	{
		iAuthCheckConfDaoMock.returns(1).updateAuthCheckConf(authCheckConf);
		assertEquals(1, iAuthCheckConfBiz.updateAuthCheckConf(authCheckConf));
	}
	
	/**
	 * 根据验证设置对象的id查询验证设置对象测试
	 */
	@Test
	public void testGetAuthCheckConfById()
	{
		iAuthCheckConfDaoMock.returns(authCheckConf).getAuthCheckConfById(1);
		assertNotNull(iAuthCheckConfBiz.getAuthCheckConfById(1));
	}
	
	/**
	 * 根据验证设置对象的id字符集合查询验证设置对象测试
	 */
	@Test
	public void testGetAuthCheckConfByIds()
	{
		List<AuthCheckConf> list = new ArrayList<>();
		list.add(authCheckConf);
		iAuthCheckConfDaoMock.returns(list).getAuthCheckConfByIds("1");
		assertEquals(1, iAuthCheckConfBiz.getAuthCheckConfByIds("1").size());
	}
	
	/**
	 * 根据验证设置对象的id字符集合删除验证设置对象测试
	 */
	@Test
	public void testDeleteAuthCheckConf()
	{
		iAuthCheckConfDaoMock.returns(1).deleteAuthCheckConf("1");
		assertEquals(1, iAuthCheckConfBiz.deleteAuthCheckConf("1"));
	}
	
	/**
	 * 更新验证设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）测试
	 */
	@Test
	public void testUpdateAuthCheckConfFieldById()
	{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ids", "1,2");
		iAuthCheckConfDaoMock.returns(1).updateAuthCheckConfFieldById(map);
		assertEquals(1, iAuthCheckConfBiz.updateAuthCheckConfFieldById(map));
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		assertEquals(list, map.get("ids"));
	}
	
	/**
	 * 根据验证设置对象的单个字段查询验证设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum

	 */
	@Test
	public void testGetAuthCheckConfByFieldTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iAuthCheckConfDaoMock.returns(1).getAuthCheckConfByFieldTotal(map);
		assertEquals(1, iAuthCheckConfBiz.getAuthCheckConfByFieldTotal(map));
	}
	
	/**
	 * 根据验证设置对象的单个字段查询验证设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）

	 */
	@Test
	public void testGetAuthCheckConfByField()
	{
		iAuthCheckConfDaoMock.returns(1).getAuthCheckConfByFieldTotal(map);
		
		iAuthCheckConfDaoMock.returns(list).getAuthCheckConfByField(map);
		assertEquals(1, iAuthCheckConfBiz.getAuthCheckConfByField(map, pageBean).getList().size());
	}
	
	/**
	 * 查询验证设置对象信息总测试
	 */
	@Test
	public void testQueryAuthCheckConfTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iAuthCheckConfDaoMock.returns(1).queryAuthCheckConfTotal(map);
		assertEquals(1, iAuthCheckConfBiz.queryAuthCheckConfTotal(map));
	}
	
	/**
	 * 分页查询验证设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum测试
	 */
	@Test
	public void testQueryAuthCheckConfByPage()
	{
		iAuthCheckConfDaoMock.returns(1).queryAuthCheckConfTotal(map);
		iAuthCheckConfDaoMock.returns(list).queryAuthCheckConfByPage(map);
		assertEquals(1, iAuthCheckConfBiz.queryAuthCheckConfByPage(map, pageBean).getList().size());
	}
}
