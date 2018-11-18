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

import com.ningpai.system.bean.InterLogin;
import com.ningpai.system.dao.InterLoginMapper;
import com.ningpai.system.service.InterLoginService;
import com.ningpai.system.service.impl.InterLoginServiceImpl;
import com.ningpai.util.PageBean;

/**
 * 登录接口服务层接口测试类
 * @author djk
 *
 */
public class InterLoginServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private InterLoginService interLoginService = new InterLoginServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<InterLoginMapper> interLoginMapperMock;
	
	/**
	 * 登录接口实体类
	 */
	private InterLogin interLogin = new InterLogin();
	
	/**
	 * 修改登录接口信息测试
	 */
	@Test
	public void testUpdateLogin()
	{
		interLoginMapperMock.returns(1).updateByPrimaryKeySelective(interLogin);
		assertEquals(1, interLoginService.updateLogin(interLogin));
	}
	
	/**
	 * 分页查询登录信息测试
	 */
	@Test
	public void testFindByPageBean()
	{
		PageBean pageBean = new PageBean();
		
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        List<Object> lists = new ArrayList<>();
        lists.add(new Object());
        interLoginMapperMock.returns(1).findTotalCount();
        interLoginMapperMock.returns(lists).findByPageBean(map);
		assertEquals(1, interLoginService.findByPageBean(pageBean).getList().size());
	}
	
	/**
	 * 添加登录信息测试
	 */
	@Test
	public void testInsertLogin()
	{
		interLoginMapperMock.returns(1).insertSelective(interLogin);
		assertEquals(1, interLoginService.insertLogin(interLogin));
	}
	
	/**
	 *  删除登录接口信息测试
	 */
	@Test
	public void testDeleteLogin()
	{
		String [] ids = {"1"};
		interLoginMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, interLoginService.deleteLogin(ids));
	}
	
	/**
	 * 根据ID查询登录接口信息
	 */
	@Test
	public void testFindLoginByLoginId()
	{
		interLoginMapperMock.returns(interLogin).selectByPrimaryKey(1L);
		assertNotNull(interLoginService.findLoginByLoginId(1L));
	}
	
}
