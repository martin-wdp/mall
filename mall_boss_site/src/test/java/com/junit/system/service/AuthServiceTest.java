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

import com.ningpai.system.bean.Auth;
import com.ningpai.system.dao.AuthMapper;
import com.ningpai.system.service.AuthService;
import com.ningpai.system.service.impl.AuthServiceImpl;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 第三方登录服务层接口测试类
 * @author djk
 *
 */
public class AuthServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private AuthService authService = new AuthServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<AuthMapper> authMapperMock;
	
	/**
	 * 测试 第三方登录分页查询
	 */
	@Test
	public void testFindByPageBean()
	{
		
    	// 生成分页参数Bean
        SelectBean selectBean = new SelectBean();
        selectBean.setCondition("1");
        selectBean.setSearchText("千米");
        
        // 生成分页辅助类
        PageBean pb = new PageBean();
        pb.setStartRowNum(0);
        pb.setEndRowNum(15);

        // 设置参数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        map.put("condition", selectBean.getCondition());
        map.put("searchText", selectBean.getSearchText());
        
        // 设置返回的对象
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        
		
		authMapperMock.returns(1).findTotalCount(selectBean);
		authMapperMock.returns(list).findByPageBean(map);
		assertEquals(1, authService.findByPageBean(pb, selectBean).getList().size());
	}
	
	
	/**
	 * 测试 添加第三方登录
	 */
	@Test
	public void testInsertAuth()
	{
		Auth auth = new Auth();
		authMapperMock.returns(1).insertSelective(auth);
		assertEquals(1, authService.insertAuth(auth));
	}
	
	/**
	 * 测试删除第三方登录
	 */
	@Test
	public void testDeleteAuth()
	{
		String [] authIds = {"1"};
		authMapperMock.returns(1).deleteByPrimaryKey(new Auth());
		assertEquals(1, authService.deleteAuth(authIds));
	}
	
	/**
	 * 测试 根据id查询信息
	 */
	@Test
	public void testFindAuthByAuthId()
	{
		authMapperMock.returns(new Auth()).selectByPrimaryKey(1L);
		assertNotNull(authService.findAuthByAuthId(1L));
	}
	
	/**
	 * 修改第三方登录信息测试
	 */
	@Test
	public void testUpdateAuth()
	{
		authMapperMock.returns(1).updateByPrimaryKeySelective(new Auth());
		assertEquals(1, authService.updateAuth(new Auth()));
	}
	
	/**
	 * 测试根据类型查询
	 */
	@Test
	public void testFindAuthByAuthType()
	{
		authMapperMock.returns(new Auth()).findAuthByAuthType("1");
		assertNotNull(authService.findAuthByAuthType("1"));
	}
	
	/**
	 *  根据第三方登录接口ID修改启用状态测试
	 */
	@Test
	public void testUpdateUserdStatus()
	{
		Auth auth = new Auth();
		auth.setAuthShow("0");
		authMapperMock.returns(auth).selectByPrimaryKey(1L);
		authMapperMock.returns(1).updateByPrimaryKeySelective(auth);
		assertEquals(true, authService.updateUserdStatus(1L));
		assertEquals("1", auth.getAuthShow());
	}
	
	/**
	 * 测试 查找已启用的第三方登录接口
	 */
	@Test
	public void testfindByShow()
	{
		List<Auth> list = new ArrayList<Auth>(1);
		list.add(new Auth());
		authMapperMock.returns(list).findByShow();
		assertEquals(1, authService.findByShow().size());
	}
	
	/**
	 * 测试根据主键删除记录
	 */
	@Test
	public void testDeleteAuthById()
	{
		authMapperMock.returns(1).updateByPrimaryKeySelective(new Auth());
		assertEquals(1, authService.deleteAuthById(1L));
	}
	
	/**
	 * 测试 查询微信登录接口（移动版登录接口）
	 */
	@Test
	public void testFindBywxPageBean()
	{
		
    	// 生成分页参数Bean
        SelectBean selectBean = new SelectBean();
        selectBean.setCondition("1");
        selectBean.setSearchText("千米");
        
        // 生成分页辅助类
        PageBean pb = new PageBean();
        pb.setStartRowNum(0);
        pb.setEndRowNum(15);

        // 设置参数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        map.put("condition", selectBean.getCondition());
        map.put("searchText", selectBean.getSearchText());
        
        // 设置返回的对象
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        
        
        authMapperMock.returns(1).findTotalwxCount(selectBean);
        authMapperMock.returns(list).findByWxLogin(map);
        
		assertEquals(1, authService.findBywxPageBean(pb, selectBean).getList().size());
	}
}
