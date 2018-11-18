package com.junit.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.system.bean.SeoConf;
import com.ningpai.system.dao.ISeoConfDao;
import com.ningpai.system.service.ISeoConfBiz;
import com.ningpai.system.service.impl.SeoConfBizImpl;
import com.ningpai.util.PageBean;

/**
 * SEO设置业务接口测试类
 * @author djk
 *
 */
public class ISeoConfBizTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private ISeoConfBiz iSeoConfBiz = new SeoConfBizImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<ISeoConfDao> iSeoConfDaoMock;
	
	/**
	 * SEO设置
	 */
	private SeoConf seoConf;
	
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
		seoConf = new SeoConf();
		pageBean = new PageBean();
		map = new HashMap<>();
		
		map.put("startRowNum", pageBean.getStartRowNum());
		map.put("endRowNum", pageBean.getEndRowNum());
		
		list = new ArrayList<>();
		list.add(new Object());
	}
	
	/**
	 *  保存SEO设置测试
	 */
	@Test
	public void testSaveSeoConf()
	{
		iSeoConfDaoMock.returns(1).updateSeoConfByUsedStatus();
		iSeoConfDaoMock.returns(true).saveSeoConf(seoConf);
		assertEquals(true, iSeoConfBiz.saveSeoConf(seoConf));
	}
	
	/**
	 * 修改SEO设置测试
	 */
	@Test
	public void testUpdateSeoConf()
	{
		iSeoConfDaoMock.returns(1).updateSeoConf(seoConf);
		assertEquals(1, iSeoConfBiz.updateSeoConf(seoConf));
	}
	
	/**
	 * 创建测试
	 */
	@Test
	public void testCreateRoborts()
	{
		HttpServletRequest request = new MockMultipartHttpServletRequest();
		
		seoConf.setMeteDes("a");
		iSeoConfBiz.createRoborts(seoConf, request);	
	}
	
	/**
	 * 根据SEO设置对象的id查询SEO设置对象测试
	 */
	@Test
	public void testGetSeoConfById()
	{
		iSeoConfDaoMock.returns(seoConf).getSeoConfById(1);
		assertNotNull(iSeoConfBiz.getSeoConfById(1));
	}
	
	/**
	 * 根据SEO设置对象的id查询SEO设置对象测试
	 */
	@Test
	public void testGetSeoConfByIds()
	{
		List<SeoConf> lists = new ArrayList<>();
		lists.add(seoConf);
		iSeoConfDaoMock.returns(lists).getSeoConfByIds("1");
		assertEquals(1, iSeoConfBiz.getSeoConfByIds("1").size());
	}
	
	/**
	 * 根据SEO设置对象的id字符集合删除SEO设置对象测试
	 */
	@Test
	public void testDeleteSeoConf()
	{
		iSeoConfDaoMock.returns(1).deleteSeoConf("1");
		assertEquals(1, iSeoConfBiz.deleteSeoConf("1"));
	}
	
	/**
	 * 更新SEO设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）测试
	 */
	@Test
	public void testUpdateSeoConfFieldById()
	{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ids", "1,2");
		iSeoConfDaoMock.returns(1).updateSeoConfFieldById(map);
		assertEquals(1, iSeoConfBiz.updateSeoConfFieldById(map));
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		assertEquals(list, map.get("ids"));
	}
	
	/**
     * 根据SEO设置对象的单个字段查询SEO设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
	 */
	@Test
	public void testGetSeoConfByFieldTotal()
	{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("1", "1");
		iSeoConfDaoMock.returns(1).getSeoConfByFieldTotal(map);
		assertEquals(1, iSeoConfBiz.getSeoConfByFieldTotal(map));
	}
	
	/**
     * 根据SEO设置对象的单个字段查询SEO设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
	 */
	@Test
	public void testGetSeoConfByField()
	{
		iSeoConfDaoMock.returns(1).getSeoConfByFieldTotal(map);
		iSeoConfDaoMock.returns(list).getSeoConfByField(map);
		assertEquals(1, iSeoConfBiz.getSeoConfByField(map, pageBean).getList().size());
	}
	
	/**
	 * 查询SEO设置对象信息总数测试
	 */
	@Test
	public void testQuerySeoConfTotal()
	{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("1", "1");
		iSeoConfDaoMock.returns(1).querySeoConfTotal(map);
		assertEquals(1, iSeoConfBiz.querySeoConfTotal(map));
	}
	
	/**
	 * 分页查询SEO设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum测试
	 */
	@Test
	public void testQuerySeoConfByPage()
	{
		iSeoConfDaoMock.returns(1).querySeoConfTotal(map);
		iSeoConfDaoMock.returns(list).querySeoConfByPage(map);
		assertEquals(1, iSeoConfBiz.querySeoConfByPage(map, pageBean).getList().size());
	}
	
	/**
	 * 获取已启用的SEO设置对象测试
	 */
	@Test
	public void testquerySeoByUsedStatus()
	{
		iSeoConfDaoMock.returns(seoConf).querySeoByUsedStatus();
		assertNotNull(iSeoConfBiz.querySeoByUsedStatus());
	}
	
	/**
	 *  修改启用状态测试
	 */
	@Test
	public void testUpdateSeoConfByUsedStatus()
	{
		iSeoConfDaoMock.returns(1).updateSeoConfByUsedStatus();
		assertEquals(1, iSeoConfBiz.updateSeoConfByUsedStatus());
	}
	
	/**
	 * 删除SEO记录
	 */
	@Test
	public void testDeleteSeoConfById()
	{
		iSeoConfDaoMock.returns(1).deleteSeoByPrimaryKey(1L);
		assertEquals(1, iSeoConfBiz.deleteSeoConf(1L));
	}
}
