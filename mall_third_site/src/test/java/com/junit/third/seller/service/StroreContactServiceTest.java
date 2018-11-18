package com.junit.third.seller.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.third.seller.bean.StoreContact;
import com.ningpai.third.seller.mapper.StoreContactMapper;
import com.ningpai.third.seller.service.StroreContactService;
import com.ningpai.third.seller.service.impl.StoreContactServiceImpl;

/**
 * 联系人信息测试
 * @author djk
 *
 */
public class StroreContactServiceTest extends UnitilsJUnit3
{
	  /**
	   * 需要测试的类
	   */
	  @TestedObject
	  private StroreContactService storeContactService = new StoreContactServiceImpl();
	  
	  /**
	   * 模拟
	   */
	  @InjectIntoByType
	  private Mock<StoreContactMapper> storeContactMapperMock;
	  
	  /**
	   * 店铺联系人集合
	   */
	  private  List<StoreContact> storeContactLists = new ArrayList<>();
	  
	  /**
	   * 店铺联系人
	   */
	  private StoreContact storeContact = new StoreContact();
	  
	  @Override
	  protected void setUp() throws Exception 
	  {
		  storeContactLists.add(storeContact);
	  }
	  
	  /**
	   * 根据店铺查询联系人信息测试
	   */
	  @Test
	  public void testSelectByStoreId()
	  {
		  storeContactMapperMock.returns(storeContactLists).selectByStoreId(1L);
		  assertEquals(1, storeContactService.selectByStoreId(1L).size());
	  }
	  
	  /**
	   * 修改卖家信息测试
	   */
	  @Test
	  public void testUpdateByPrimaryKeySelective()
	  {
		  storeContactMapperMock.returns(1).updateByPrimaryKeySelective(storeContact);
		  assertEquals(1, storeContactService.updateByPrimaryKeySelective(storeContact));
	  }
	  
	  /**
	   * 新增卖家信息测试
	   */
	  @Test
	  public void testInsertStoreSelective()
	  {
		  storeContactMapperMock.returns(1).insertStoreSelective(storeContact);
		  assertEquals(1, storeContactService.insertStoreSelective(new MockHttpServletRequest(), storeContact));
	  }
}
