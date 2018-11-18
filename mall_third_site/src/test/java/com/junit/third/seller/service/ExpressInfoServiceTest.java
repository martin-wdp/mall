package com.junit.third.seller.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.third.seller.bean.Express;
import com.ningpai.third.seller.mapper.ExpressInfoMapper;
import com.ningpai.third.seller.service.ExpressInfoService;
import com.ningpai.third.seller.service.impl.ExpressInfoServiceImpl;

/**
 * 物流支持Service测试
 * @author djk
 *
 */
public class ExpressInfoServiceTest  extends UnitilsJUnit3
{
	  /**
	   * 需要测试的类
	   */
	  @TestedObject
	  private ExpressInfoService expressInfoService = new ExpressInfoServiceImpl();
	  
	  /**
	   * 模拟
	   */
	  @InjectIntoByType
	  private Mock<ExpressInfoMapper> expressInfoMapperMock; 
	  
	  /**
	   *  商家物流测试
	   */
	  private Express express = new Express();
	  
	  /**
	   * 商家物流集合测试
	   */
	  private List<Express> expressLists = new ArrayList<>();
	  
	  @Override
      protected void setUp() throws Exception 
	  {
		  expressLists.add(express);
	  }
	  
	  /**
	   * 根据商家ID和物流编号 查询单个物流信息测试
	   */
	  @Test
	  public void testSelectExpressByCom()
	  {
		  expressInfoMapperMock.returns(express).selectExpressByCom("a",1L);
		  assertNotNull(expressInfoService.selectExpressByCom("a", 1L));
	  }
	  
	  /**
	   *  根据店铺查询物流信息测试
	   */
	  @Test
	  public void testSelectByStoreId()
	  {
		  expressInfoMapperMock.returns(expressLists).selectByStoreId(express);
		  assertEquals(1, expressInfoService.selectByStoreId(express).size());
	  }
	  
	  /**
	   * 根据店铺查询物流信息测试
	   */
	  @Test
	  public void testSelectByStoreIds()
	  {
		  expressInfoMapperMock.returns(expressLists).selectByStoreIds(express);
		  assertEquals(1, expressInfoService.selectByStoreIds(express).size());
	  }
	  
	  /**
	   *  添加物流相关信息测试
	   */
	  @Test
	  public void testInsertExpress()
	  {
		  expressInfoMapperMock.returns(1).insertExpress(express);
		  assertEquals(1, expressInfoService.insertExpress(new MockHttpServletRequest(), express));
	  }
	  
	  /**
	   * 根据店铺查询物流信息测试
	   */
	  @Test
	  public void testSelectDefaultRows()
	  {
		  expressInfoMapperMock.returns(1).selectDefaultRows(1L);
		  assertEquals(1, expressInfoService.selectDefaultRows(1L));
	  }
	  
	  /**
	   *  修改物流信息测试
	   */
	  @Test
	  public void testUpdateExpress()
	  {
		  expressInfoMapperMock.returns(1).updateExpress(express);
		  assertEquals(1, expressInfoService.updateExpress(new MockHttpServletRequest(), express));
	  }
	  
	  /**
	   * 修改物流相关信息测试
	   */
	  @Test
	  public void testUpdateStateByShoreExpId()
	  {
		  expressInfoMapperMock.returns(1).updateStateByShoreExpId(express);
		  assertEquals(1, expressInfoService.updateStateByShoreExpId(express));
	  }
	  
	  /**
	   * 删除物流 修改标记测试
	   */
	  @Test
	  public void testUpdateStateBackByShoreExpId()
	  {
		  expressInfoMapperMock.returns(1).updateStateBackByShoreExpId(express);
		  assertEquals(1, expressInfoService.updateStateBackByShoreExpId(express));
	  }
	  
	  /**
	   * 删除物流,修改标记,设置为默认,默认唯一测试
	   */
	  @Test
	  public void testDeleteByShoreExpIdUpdate()
	  {
		  expressInfoMapperMock.returns(1).deleteByShoreExpIdUpdate(express);
		  assertEquals(1, expressInfoService.deleteByShoreExpIdUpdate(express));
	  }
	  
	  /**
	   * 根据ID查询单条数据测试
	   */
	  @Test
	  public void testSelectByshoreExpId()
	  {
		  expressInfoMapperMock.returns(express).selectByshoreExpId(1L);
		  assertNotNull(expressInfoService.selectByshoreExpId(1L));
	  }
	  
	  /**
	   * 批量修改操作测试
	   */
	  @Test
	  public void testUpdateBatch()
	  {
		  expressInfoMapperMock.returns(1).updateBatch(expressLists);
		  assertEquals(1, expressInfoService.updateBatch(expressLists));
	  }
}
