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

import com.ningpai.system.bean.Empower;
import com.ningpai.system.bean.EmpowerLog;
import com.ningpai.system.dao.EmpowerMapper;
import com.ningpai.system.service.EmpowerService;
import com.ningpai.system.service.impl.EmpowerServiceImpl;
import com.ningpai.util.PageBean;

/**
 * 权限信息服务层接口测试
 * @author djk
 *
 */
public class EmpowerServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private EmpowerService empowerService = new EmpowerServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<EmpowerMapper> EmpowerMapperMock;
	
	/**
	 * 查询测试
	 */
	@Test
	public void testList()
	{
		PageBean pageBean = new PageBean();
		EmpowerMapperMock.returns(1).listCount();
		Map<String,Object> map=new HashMap<String,Object>();
        map.put("start", pageBean.getStartRowNum());
        map.put("end", pageBean.getEndRowNum());
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        EmpowerMapperMock.returns(list).list(map);
		assertEquals(1, empowerService.list(pageBean).getList().size());
	}
	
	/**
	 * 插入信息测试
	 */
	@Test
	public void testInsertEmpower()
	{
		EmpowerMapperMock.returns(1).insertEmpower(new Empower());
		assertEquals(1, empowerService.insertEmpower(new Empower()));
	}
	
	/**
	 * 修改角色测试
	 */
	@Test
	public void testUpdateEmpower()
	{
	    Map<String,Object> map=new HashMap<String,Object>();
        map.put("status","1");
        map.put("appId",1L);
        EmpowerMapperMock.returns(1).updateEmpower(map);
		assertEquals(1, empowerService.updateEmpower("1", 1L));
	}
	/**
	 * 删除角色测试
	 */
	@Test
	public void testDelEmpower()
	{
		EmpowerMapperMock.returns(1).delEmpower(1L);
		assertEquals(1, empowerService.delEmpower(1L));
	}
	
	/**
	 * 验证名字是否存在测试
	 */
	@Test
	public void testCheckUserName()
	{
		EmpowerMapperMock.returns(1).checkName("a");
		assertEquals(1, empowerService.checkUserName("a"));
	}
	
	/**
	 * 查询日志列表测试
	 */
	@Test
	public void testSelectLog()
	{
		  List<EmpowerLog> list = new ArrayList<>();
		  list.add(new EmpowerLog());
		  EmpowerMapperMock.returns(list).selectLog(1L);
		  assertEquals(1, empowerService.selectLog(1L).size());
	}
}
