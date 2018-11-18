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

import com.ningpai.system.bean.ServiceSupport;
import com.ningpai.system.dao.ServiceSupportMapper;
import com.ningpai.system.service.ServiceSupportMapperService;
import com.ningpai.system.service.impl.ServiceSupportMapperServiceImpl;
import com.ningpai.util.PageBean;

/**
 * 服务支持服务层测试
 * @author djk
 *
 */
public class ServiceSupportMapperServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private ServiceSupportMapperService serviceSupportMapperService = new ServiceSupportMapperServiceImpl();
	
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<ServiceSupportMapper> serviceSupportMapperMock;
	
	/**
	 * 服务支持
	 */
	private ServiceSupport serviceSupport = new ServiceSupport();
	
	/**
	 * 添加服务支持测试
	 */
	@Test
	public void testInsertSelective()
	{
		serviceSupportMapperMock.returns(1).insertSelective(serviceSupport);
		assertEquals(1, serviceSupportMapperService.insertSelective(serviceSupport));
	}
	
	/**
	 *  根据id查询单个服务支持测试
	 */
	@Test
	public void testSelectByPrimaryKey()
	{
		serviceSupportMapperMock.returns(serviceSupport).selectByPrimaryKey(1L);
		assertNotNull(serviceSupportMapperService.selectByPrimaryKey(1L));
	}
	
	/**
	 * 列表显示分页查询测试
	 */
	@Test
	public void testSelectAllServiceSupport()
	{
		PageBean pb = new PageBean();
	    Map<String, Object> map = new HashMap<String, Object>();
	    serviceSupportMapperMock.returns(1).selectCount();
	    map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        serviceSupportMapperMock.returns(list).selectAllServiceSupport(map);
		assertEquals(1, serviceSupportMapperService.selectAllServiceSupport(pb).getList().size());
	}
	
	/**
	 * 修改单个服务支持测试
	 */
	@Test
	public void testUpdateByPrimaryKeySelective()
	{
		serviceSupportMapperMock.returns(1).updateByPrimaryKeySelective(serviceSupport);
		assertEquals(1, serviceSupportMapperService.updateByPrimaryKeySelective(serviceSupport));
	}
	
	/**
	 * 修改删除标记测试
	 */
	@Test
	public void testUpdateServiceSupportByDelfalg()
	{
		String [] ids = {"1"};
		List<Long> list = new ArrayList<>();
		list.add(1L);
		serviceSupportMapperMock.returns(1).updateServiceSupportByDelfalg(list);
		assertEquals(1, serviceSupportMapperService.updateServiceSupportByDelfalg(ids));
	}
	
	/**
	 *  查询所有测试
	 */
	@Test
	public void testSelectAll()
	{
		 List<ServiceSupport> lists = new ArrayList<>();
		 lists.add(serviceSupport);
		 serviceSupportMapperMock.returns(lists).selectAll();
		 assertEquals(1, serviceSupportMapperService.selectAll().size());
	}
}
