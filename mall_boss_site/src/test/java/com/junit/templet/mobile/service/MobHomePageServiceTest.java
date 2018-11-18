package com.junit.templet.mobile.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.handler.DispatcherServletWebRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.mobile.bean.MobHomePage;
import com.ningpai.mobile.dao.MobHomePageMapper;
import com.ningpai.mobile.service.MobHomePageService;
import com.ningpai.mobile.service.impl.MobHomePageServiceImpl;

/**
 * SERVICE-移动版可视化配置首页测试
 * @author djk
 *
 */
public class MobHomePageServiceTest  extends UnitilsJUnit3 
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private MobHomePageService mobHomePageService = new MobHomePageServiceImpl();
	 
	/**
     *  模拟MOCK
	 */
     @InjectIntoByType
	 private Mock<MobHomePageMapper> mobHomePageMapperMock;
	 
     /**
      * 实体类-移动版首页-可视化配置
      */
	 private MobHomePage mobHomePage = new MobHomePage();
	 
	 @Override
	protected void setUp() throws Exception
	 {
		 HttpServletRequest request = new MockMultipartHttpServletRequest();
		 
		 RequestContextHolder.setRequestAttributes(new DispatcherServletWebRequest(request));
	}
	 
	 /**
	  * 添加移动版可视化配置首页测试
	  */
	 @Test
	 public void testCreateHomePage()
	 {
		  mobHomePageMapperMock.returns(1).insertSelective(mobHomePage);
		  mobHomePageService.createHomePage(mobHomePage);
	 }
	 
	 /**
	  * 修改移动版可视化配置首页测试
	  */
	 @Test
	 public void testUpdateHomePage()
	 {
		 mobHomePageMapperMock.returns(1).updateByPrimaryKeySelective(mobHomePage);
		 mobHomePageService.updateHomePage(mobHomePage);
	 }
	 
	 /**
	  * 根据商家ID查询测试
	  */
	 @Test
	 public void testSelectHomePageByMerchantId()
	 {
		 mobHomePageMapperMock.returns(mobHomePage).selectByMerchantId(1L);
		 assertNotNull(mobHomePageService.selectHomePageByMerchantId(1L)); 
	 }
	 
	 /**
	  * 初始化商家的HomePage测试
	  */
	 @Test
	 public void testInitHomePage()
	 {
		 mobHomePageMapperMock.returns(1).insertSelective(mobHomePage);
		 assertNotNull(mobHomePageService.initHomePage(1L, "a", 1L));
	 }
	 
//	 /**
//	  * 生成HTML测试
//	  */
//	 @Test
//	 public void testMakeHtml()
//	 {
//		 mobHomePageService.makeHtml();
//	 }
	 
	 /**
	  * 根据商家ID查询该商家的所有模板测试
	  */
	 @Test
	 public void testSelectAllUnstatusByMerchantId()
	 {
		 List<MobHomePage> list = new ArrayList<>();
		 list.add(mobHomePage);
		 mobHomePageMapperMock.returns(list).selectAllUnstatusByMerchantId(1L);
		 assertEquals(1, mobHomePageService.selectAllUnstatusByMerchantId(1L).size());
	 }
	 
	 /**
	  * 根据ID获取模板信息测试
	  */
	 @Test
	 public void testGetHomePageById()
	 {
		 mobHomePageMapperMock.returns(mobHomePage).selectByPrimaryKey(1L);
		 assertNotNull(mobHomePageService.getHomePageById(1L));
	 }
	 
	 /**
	  * 根据ID删除模板信息测试
	  */
	 @Test
	 public void testDeleteHomePageById()
	 {
		 mobHomePageMapperMock.returns(1).deleteByPrimaryKey(1L);
		 mobHomePageService.deleteHomePageById(1L);
	 }
	 
	 /**
	  * 查询第三方移动模板信息测试
	  */
	 @Test
	 public void testSelectThirdMob()
	 {
		 mobHomePageMapperMock.returns(mobHomePage).selectThirdMob(1L);
		 assertNotNull(mobHomePageService.selectThirdMob(1L));
	 }
	 
	 /**
	  * 启用模板测试
	  */
	 @Test
	 public void testOpenHomePageByHIdAndMId()
	 {
		 mobHomePageMapperMock.returns(1).updateByMerchantId(1L);
		 mobHomePageMapperMock.returns(1).updateByhomepageIdAndMerchantId(mobHomePage);
		 mobHomePageService.openHomePageByHIdAndMId(1L, 1L);
	 }
	 
	 /**
	  *  根据商家ID获取该商家当前使用的模板信息测试
	  */
	 @Test
	 public void testGetCurrHomePageByMerchantId()
	 {
		 mobHomePageMapperMock.returns(mobHomePage).queryCurrHomePageByMerchantId(1L);
		 assertNotNull(mobHomePageService.getCurrHomePageByMerchantId(1L));
	 }
	 
	 /**
	  * 根据商家ID获取该商家当前使用的模板信息测试
	  */
	 @Test
	 public void testGetCurrHomePageByStoreId()
	 {
		 mobHomePageMapperMock.returns(mobHomePage).getCurrHomePageByStoreId(1L);
		 assertNotNull(mobHomePageService.getCurrHomePageByStoreId(1L));
	 }
}
