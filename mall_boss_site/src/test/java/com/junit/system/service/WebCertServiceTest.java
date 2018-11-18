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

import com.ningpai.system.bean.WebCert;
import com.ningpai.system.dao.WebCertMapper;
import com.ningpai.system.service.WebCertService;
import com.ningpai.system.service.impl.WebCertServiceImpl;
import com.ningpai.util.PageBean;

/**
 * SERVICE-网站认证测试
 * @author djk
 *
 */
public class WebCertServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private WebCertService webCertService = new WebCertServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<WebCertMapper> webCertMapperMock;
	
	/**
	 * 根据主键删除测试
	 */
	@Test
	public void testDeleteWebCert()
	{
		webCertMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, webCertService.deleteWebCert(1L));
	}
	
	/**
	 * 添加测试
	 */
	@Test
	public void testSaveWebCert()
	{
		webCertMapperMock.returns(1).insertSelective(new WebCert());
		assertEquals(1, webCertService.saveWebCert(new WebCert()));
	}
	
	/**
	 * 修改测试
	 */
	@Test
	public void testUpdateWebCert()
	{
		webCertMapperMock.returns(1).updateByPrimaryKeySelective(new WebCert());
		assertEquals(1, webCertService.updateWebCert(new WebCert()));
	}
	
	/**
	 * 根据ID查询测试
	 */
	@Test
	public void testSelectByPrimaryKey()
	{
		webCertMapperMock.returns(new WebCert()).selectByPrimaryKey(1L);
		assertNotNull(webCertService.selectByPrimaryKey(1L));
	}
	
	/**
	 * 根据分页参数查询认证测试
	 */
	@Test
	public void testSelectAllByPb()
	{
		PageBean pageBean = new PageBean();
		
		Map<String, Object> map = new HashMap<String, Object>();
		webCertMapperMock.returns(1).selectCountByPb();
		map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        List<Object> lists = new ArrayList<>();
        lists.add(new Object());
        webCertMapperMock.returns(lists).selectAllByPb(map);
		assertEquals(1, webCertService.selectAllByPb(pageBean).getList().size());
	}
	
	/**
	 *  查询所有认证-前台展示用测试
	 */
	@Test
	public void testSelectAll()
	{
		List<WebCert> lists = new ArrayList<>();
		lists.add(new WebCert());
		webCertMapperMock.returns(lists).selectAll();
		assertEquals(1, webCertService.selectAll().size());
	}
}
