package com.junit.third.sld.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.third.sld.bean.DomainCustom;
import com.ningpai.third.sld.dao.impl.DomainCustomMapperImpl;
import com.ningpai.third.sld.service.DomainCustomService;
import com.ningpai.third.sld.service.impl.DomainCustomServiceImpl;

/**
 * 二级域名关联测试
 * @author djk
 *
 */
public class DomainCustomServiceTest extends UnitilsJUnit3
{
	  /**
	   * 需要测试的类
	   */
	  @TestedObject
	  private DomainCustomService domainCustomService = new DomainCustomServiceImpl();
	  
	  /**
	   * 模拟
	   */
	  @InjectIntoByType
	  private Mock<DomainCustomMapperImpl> domainCustomMapperImplMock;
	  
	  /**
	   *  自定义的域
	   */
	  private DomainCustom domainCustom = new DomainCustom();
	  
	  /**
	   * 根据第三方id查询二级域名测试
	   */
	  @Test
	  public void testFindDomainCustom()
	  {
		  domainCustomMapperImplMock.returns(domainCustom).findByCustId(1L);
		  assertNotNull(domainCustomService.findDomainCustom(1L));
	  }
	  
	  /**
	   * 修改二级域名测试
	   */
	  @Test
	  public void testUpdateDomain()
	  {
		  domainCustomMapperImplMock.returns(1).updateByPrimaryKeySelective(domainCustom);
		  assertEquals(1, domainCustomService.updateDomain(domainCustom));
	  }
	  
	  /**
	   *  根据id查询二级域名测试
	   */
	  @Test
	  public void testQueryDomainByID()
	  {
		  domainCustomMapperImplMock.returns(domainCustom).selectByPrimaryKey(1L);
		  assertNotNull(domainCustomService.queryDomainByID(1L));
	  }
	  
	  /**
	   * 检查是否存在二级域名测试
	   */
	  @Test
	  public void testQueryByDomain()
	  {
		  domainCustomMapperImplMock.returns(1L).queryByDomain("a");
		  Long result = 1L;
		  assertEquals(result,domainCustomService.queryByDomain("a"));
	  }
	  
	  /**
	   *  查询全部域名测试
	   */
	  @Test
	  public void testFindAll()
	  {
		  List<DomainCustom> lists = new ArrayList<>();
		  lists.add(domainCustom);
		  domainCustomMapperImplMock.returns(lists).findAll();
		  assertEquals(1, domainCustomService.findAll().size());
	  }
}
