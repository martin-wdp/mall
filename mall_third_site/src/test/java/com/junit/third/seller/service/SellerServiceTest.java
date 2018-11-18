package com.junit.third.seller.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.customer.dao.CustomerMapper;
import com.ningpai.third.goods.service.ThirdOtherService;
import com.ningpai.third.seller.bean.StoreInfo;
import com.ningpai.third.seller.bean.ThirdMessageModel;
import com.ningpai.third.seller.bean.ThirdStoreMess;
import com.ningpai.third.seller.mapper.ApplyBrandMapper;
import com.ningpai.third.seller.mapper.StoreInfoMapper;
import com.ningpai.third.seller.mapper.ThirdMessageModelMapper;
import com.ningpai.third.seller.service.SellerService;
import com.ningpai.third.seller.service.impl.SellerServiceImpl;

/**
 * 商家信息测试
 * @author djk
 *
 */
public class SellerServiceTest extends UnitilsJUnit3
{
	  /**
	   * 需要测试的类
	   */
	  @TestedObject
	  private SellerService sellerService = new SellerServiceImpl();
	  
	  /**
	   * 模拟
	   */
	  @InjectIntoByType
	  private Mock<StoreInfoMapper> storeInfoMapperMock;
	  
	  /**
	   * 模拟
	   */
	  @InjectIntoByType
	  private Mock<ApplyBrandMapper> applyBrandMapperMock;
	  
	  /**
	   * 模拟
	   */
	  @InjectIntoByType
	  private Mock<ThirdMessageModelMapper> thirdMessageModelMapperMock;
	  
	  /**
	   * 模拟
	   */
	  @InjectIntoByType
	  private Mock<CustomerMapper> customerMapperMock;
	  
	  /**
	   * 模拟
	   */
	  @InjectIntoByType
	  private Mock<ThirdOtherService> thirdOtherServiceMock;
	  
	  /**
	   * 店铺信息
	   */
	  private StoreInfo storeInfo = new StoreInfo();
	  
	  /**
	   * 第三方消息接收
	   */
	  private ThirdMessageModel thirdMessageModel = new ThirdMessageModel();
	  
	  /**
	   *  模块消息接收方式
	   */
	  private ThirdStoreMess thirdStoreMess = new ThirdStoreMess();
	  
	  /**
	   * 第三方消息接收集合
	   */
	  private List<ThirdMessageModel> thirdMessageModelLists = new ArrayList<>();
	  
	  /**
	   *  店铺信息集合
	   */
	  private List<StoreInfo> storeInfoLists = new ArrayList<>();
	  
	  @Override
      protected void setUp() throws Exception 
	  {
		  storeInfo.setStoreId(1L);
		  storeInfo.setCustomerid(1L);
		  storeInfoLists.add(storeInfo);
		  thirdMessageModelLists.add(thirdMessageModel);
	  }
	  
	  /**
	   * 根据店铺员工ID获取店铺信息测试
	   */
	  @Test
	  public void testSelectByEmployeeId()
	  {
		  storeInfoMapperMock.returns(storeInfo).selectByEmployeeId(1L);
		  assertNotNull(sellerService.selectByEmployeeId(1L));
	  }
	  
	  /**
	   * 很据卖家编号查找店铺信息测试
	   */
	  @Test
	  public void testSelectByCustomerId()
	  {
		  storeInfoMapperMock.returns(storeInfo).selectByCustomerId(1L);
		  assertNotNull(sellerService.selectByCustomerId(1L));
	  }
	  
	  /**
	   * 修改商家信息测试
	   */
	  @Test
	  public void testUpdateByStoreInfo()
	  {
		  storeInfoMapperMock.returns(1).updateByPrimaryKeySelective(storeInfo);
		  assertEquals(1, sellerService.updateByStoreInfo(storeInfo, new MockMultipartHttpServletRequest(), new MockHttpServletRequest()));
	  }
	  
	  
	  /**
	   * 根据商家编号查找商家消息设置测试
	   */
	  @Test
	  public void testSelectMessByStoreId()
	  {
		  thirdMessageModelMapperMock.returns(thirdMessageModelLists).selectAllMessModel(1L);
		  assertEquals(1, sellerService.selectMessByStoreId(1L).size());
	  }
	  
	  /**
	   * 查询当前要设置的消息设置测试
	   */
	  @Test
	  public void testQueryStoreMessBySidAndMid()
	  {
	      Map<String, Object> paramMap = new HashMap<String, Object>();
          paramMap.put("storeId", null);
          paramMap.put("messModId", 1L);
          storeInfoMapperMock.returns(thirdStoreMess).queryStoreMessBySidAndMid(paramMap);
		  assertNotNull(sellerService.queryStoreMessBySidAndMid(new MockHttpServletRequest(), 1L));
	  }
	  
	  /**
	   * 修改消息接收设置测试
	   */
	  @Test
	  public void testUpdateStoreMess()
	  {
		  storeInfoMapperMock.returns(1).updateStoreMess(thirdStoreMess);
		  assertEquals(1, sellerService.updateStoreMess(new MockHttpServletRequest(), thirdStoreMess));
	  }
	  
	  /**
	   *  添加消息接收设置测试
	   */
	  @Test
	  public void testAddStoreMess()
	  {
		  storeInfoMapperMock.returns(1).addStoreMess(thirdStoreMess);
		  assertEquals(1, sellerService.addStoreMess(new MockHttpServletRequest(), thirdStoreMess));
	  }
	  
	  /**
	   * 保存商家信息测试
	   */
	  @Test
	  public void testSaveStoreInfo()
	  {
		  Long result = 1L;
		  storeInfoMapperMock.returns(1).insertSelective(storeInfo);
		  assertEquals(result, sellerService.saveStoreInfo(new MockHttpServletRequest(), storeInfo, new MockMultipartHttpServletRequest()));
	  }
	  
	  /**
	   * 保存商家选中的分类 品牌测试
	   */
	  @Test
	  public void testSaveStoreCateAndBrand()
	  {
		  storeInfoMapperMock.returns(storeInfo).selectByCustomerId(null);
		  assertEquals(1, sellerService.saveStoreCateAndBrand(new MockHttpServletRequest(), 1L, "1", "1"));
	  }
	  
	  /**
	   * 修改第三方店铺信息-不修改文件相关字段测试
	   */
	  @Test
	  public void testUpdateStoreIndexState()
	  {
		  storeInfoMapperMock.returns(1).updateByPrimaryKeySelective(storeInfo);
		  assertEquals(1, sellerService.updateStoreIndexState(storeInfo));
	  }
	  
	  /**
	   * 查询打回信息测试
	   */
	  @Test
	  public void testSelectRefuseInfo()
	  {
		  storeInfoMapperMock.returns(storeInfo).selectRefuseInfo(1L);
		  assertNotNull(sellerService.selectRefuseInfo(1L));
	  }
	  
	  /**
	   * 修改第三方店铺信息-不修改文件相关字段测试
	   */
	  @Test
	  public void testUpdateByStoreInfo2()
	  {
		  storeInfoMapperMock.returns(1).updateRefuseInfo(storeInfo);
		  assertEquals(1, sellerService.updateByStoreInfo(storeInfo, 1L));
	  }
	  
	  /**
	   * 根据店铺名称查找测试
	   */
	  @Test
	  public void testSelectByStoreName()
	  {
		  storeInfoMapperMock.returns(storeInfo).selectByStoreName("a");
		  assertNotNull(sellerService.selectByStoreName("a"));
	  }
	  
	  /**
	   * 很据卖家编号查找店铺信息测试
	   */
	  @Test
	  public void testSelectByStoreId()
	  {
		  storeInfoMapperMock.returns(storeInfo).selectByPrimaryKey(1L);
		  assertNotNull(sellerService.selectByStoreId(1L));
	  }
	  
	  /**
	   * 查询所有已开通的店铺信息测试
	   */
	  @Test
	  public void testSelectAll()
	  {
		  storeInfoMapperMock.returns(storeInfoLists).selectAll();
		  assertEquals(1, sellerService.selectAll().size());
	  }
	  
	  /**
	   * 查询店铺名称是否已使用测试
	   */
	  @Test
	  public void testQueryCountByStoreName()
	  {
		  storeInfoMapperMock.returns(1).queryCountByStoreName("a");
		  assertEquals(1, sellerService.queryCountByStoreName("a"));
	  }
}
