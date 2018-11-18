package com.junit.third.seller.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.third.seller.bean.ApplyBrand;
import com.ningpai.third.seller.mapper.ApplyBrandMapper;
import com.ningpai.third.seller.service.ApplyBrandService;
import com.ningpai.third.seller.service.impl.ApplyBrandServiceImpl;
import com.ningpai.util.PageBean;

/**
 * 申请品牌测试
 * @author djk
 *
 */
public class ApplyBrandServiceTest extends UnitilsJUnit3
{
	  /**
	   * 需要测试的类
	   */
	  @TestedObject
	  private ApplyBrandService applyBrandService = new ApplyBrandServiceImpl();
	  
	  /**
	   * 模拟
	   */
	  @InjectIntoByType
	  private Mock<ApplyBrandMapper> applyBrandMapperMock;
	  
	  /**
	   * 自定义品牌集合
	   */
	  private List<ApplyBrand> applyBrandLists = new ArrayList<>();
	  
	  /**
	   * 自定义品牌
	   */
	  private ApplyBrand applyBrand = new ApplyBrand();
	  
	  @Override
	  protected void setUp() throws Exception 
	  {
		  applyBrandLists.add(applyBrand);
	  }
	  
	  /**
	   * 获取自定义品牌的集合测试
	   */
	  @Test
	  public void testSelectApplyBrand()
	  {
		  applyBrandMapperMock.returns(applyBrandLists).selectApplyBrand(1L);
		  assertEquals(1, applyBrandService.selectApplyBrand(1L).size());
	  }
	  
	  /**
	   * 删除测试
	   */
	  @Test
	  public void testDelApplyBrand()
	  {
	      Map<String, Object> map = new HashMap<String, Object>();
	      map.put("applyBrandId",1L);
	      map.put("applyThirdId",1L);
	      applyBrandMapperMock.returns(1).delApplyBrand(map);
		  assertEquals(1, applyBrandService.delApplyBrand(1L, 1L));
	  }
	  
	  /**
	   * 添加品牌
	   */
	  @Test
	  public void testAddApplyBrand()
	  {
		  
		  assertNotNull(applyBrandService.addApplyBrand(new MockMultipartHttpServletRequest(), applyBrand));
	  }
	  
	  /**
	   * 查询自定义品牌列表测试
	   */
	  @Test
	  public void testQueryApplyBrand()
	  {
		  PageBean pb = new PageBean();
		  pb.setObjectBean(applyBrand);
		  Map<String, Object> pmap = new HashMap<String, Object>();
		  pmap.put("thirdId", 1L);
          pmap.put("applyBrandName", applyBrand.getApplyBrandName());
          pmap.put("applyStatus", applyBrand.getApplyStatus());
          applyBrandMapperMock.returns(1).queryApplyBrandCount(pmap);
          pmap.put("startRowNum", pb.getStartRowNum());
          pmap.put("endRowNum", pb.getEndRowNum());
          applyBrandMapperMock.returns(applyBrandLists).queryApplyBrand(pmap);
		  assertEquals(1, applyBrandService.queryApplyBrand(pb, 1L).getList().size());
	  }
	  
	  /**
	   * 批量删除测试
	   */
	  @Test
	  public void testDelApplyBrands()
	  {
		  Long[] applyBrandIds = {1L};
	      Map<String, Object> map = new HashMap<String,Object>();
	      map.put("applyBrand",applyBrandIds);
	      map.put("applyThirdId",1L);
	      applyBrandMapperMock.returns(1).delApplyBrands(map);
		  assertEquals(1, applyBrandService.delApplyBrands(applyBrandIds, 1L));
	  }
}
