package com.junit.common.freighttemplate.service;


import com.alibaba.fastjson.JSON;
import com.ningpai.freighttemplate.bean.*;
import com.ningpai.freighttemplate.dao.*;
import com.ningpai.freighttemplate.service.FreightTemplateService;
import com.ningpai.freighttemplate.service.impl.FreightTemplateServiceImpl;
import com.ningpai.util.MapUtil;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 运费模板
 *
 */
public class FreightTemplateServiceTest  extends UnitilsJUnit3 {

  /**
   * 需要测试的Service
   */
  @TestedObject
  private FreightTemplateService freightTemplateService = new FreightTemplateServiceImpl();

  /**
   * 模拟Mock
   */
  @InjectIntoByType
  Mock<FreightTemplateMapper> freightTemplateMapperMock ;
  @InjectIntoByType
  Mock<FreightExpressMapper> freightExpressMapperMock ;
  @InjectIntoByType
  Mock<SysLogisticsCompanyMapper> sysLogisticsCompanyMapperMock;
  @InjectIntoByType
  Mock<ExpressInfoMapper> expressInfoMapperMock;
  @InjectIntoByType
  Mock<SysCityMapper> sysCityMapperMock;
  @InjectIntoByType
  Mock<FreightExpressAllMapper> freightExpressAllMapperMock;



  @FileContent("freightList.js")
  private String freightListJs;
  @FileContent("feList.js")
  private String feListJs;
  @FileContent("sysLogisticsCompany.js")
  private String sysLogisticsCompanyJs;
  @FileContent("express.js")
  private String expressJs;
  @FileContent("sysCity.js")
  private String sysCityJs;
  @FileContent("fall.js")
  private String fallJs;
  /**
   * 共享数据
   */
  List<FreightTemplate> freightList;
  List<FreightExpress> feList;
  List<FreightExpressAll> fall;
  SysLogisticsCompany sysLogisticsCompany;
  Express express;
  SysCity sysCity;


  /**
   * 初始化
   */
  @Override
  public void setUp(){
    freightList = JSON.parseArray(freightListJs,FreightTemplate.class);
    feList = JSON.parseArray(feListJs,FreightExpress.class);
    sysLogisticsCompany = JSON.parseObject(sysLogisticsCompanyJs, SysLogisticsCompany.class);
    express = JSON.parseObject(expressJs, Express.class);
    sysCity = JSON.parseObject(sysCityJs, SysCity.class);
    fall = JSON.parseArray(fallJs, FreightExpressAll.class);
    feList.get(0).setFreightExpressAll(fall);
  }
  /**
   * 查询所有运费模板
   */
  @Test
  public void testSearchAllTemplate(){
    FreightTemplate freightTemplate = new FreightTemplate();
    freightTemplate.setFreightThirdId(0L);
    Map<String,Object> paramMap = MapUtil.getParamsMap(freightTemplate);
    freightTemplateMapperMock.returns(freightList).searchAllTemplateList(paramMap);
   freightExpressMapperMock.returns(feList).selectTemplateExpress(1L);
    sysLogisticsCompanyMapperMock.returns(sysLogisticsCompany).selectCompanyById(1L);
    expressInfoMapperMock.returns(express).selectByshoreExpId(1L);
    sysCityMapperMock.returns(sysCity).selectCityById(1L);
    assertEquals(1, freightTemplateService.searchAllTemplate(freightTemplate).size());
  }

  /**
   * 复制模板
   */
  @Test
  public void testCopyFreightTemplate(){
    FreightTemplate freightTemplate = freightList.get(0);
    freightTemplateMapperMock.returns(freightTemplate).selectFreightTemplateById(1L);
    // 插入新的模板
    freightTemplateMapperMock.returns(1).insertNewFreightTemplate(freightTemplate);
    freightTemplateMapperMock.returns(1L).selectFreightTemplateLastId();
    freightExpressMapperMock.returns(feList).selectTemplateExpress(1L);
    freightExpressMapperMock.returns(1).insertNewFreightExpress(feList.get(0));
    freightExpressMapperMock.returns(1L).selectLastDistributionId();
    freightExpressAllMapperMock.returns(1).insertFreightExpressAll(fall);
    assertEquals(1, freightTemplateService.copyFreightTemplate(1L));
  }


  /**
   * 删除运费模板
   */
  @Test
  public void testDeleteFreightTemplate(){
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("freightTemplateId", 1);
      map.put("freightThirdId", 0);
      freightTemplateMapperMock.returns(1).deleteFreightTemplate(map);
      freightExpressMapperMock.returns(feList).selectTemplateExpress(101L);
      freightExpressMapperMock.returns(1).deleteTemplateExpress(1L);
      freightExpressAllMapperMock.returns(1).deleteTemplateExpressAll(1L);
      assertEquals(1,freightTemplateService.deleteFreightTemplate(1L, 0L));
    }

  /**
   * 设置模板默认
   */
  @Test
  public void testDefaultFreightTemplate(){
    freightTemplateMapperMock.returns(1).noDefaultFreightTemplate(freightList.get(0));
    freightTemplateMapperMock.returns(1).defaultFreightTemplate(freightList.get(0));
    assertEquals(1, freightTemplateService.defaultFreightTemplate(freightList.get(0)));
  }

  /**
   * 查询模板详细
   */
  @Test
  public void testSelectFreightTemplateDetail(){
    freightTemplateMapperMock.returns(freightList.get(0)).selectFreightTemplateById(1L);
    freightExpressMapperMock.returns(feList).selectTemplateExpress(1L);
    sysLogisticsCompanyMapperMock.returns(sysLogisticsCompany).selectCompanyById(1L);
    expressInfoMapperMock.returns(express).selectByshoreExpId(1L);
    sysCityMapperMock.returns(sysCity).selectCityById(1L);
    assertNotNull(freightTemplateService.selectFreightTemplateDetail(1L));
  }

  /**
   * 保存运费模板
   */
  @Test
  public void testSaveFreight(){
    freightExpressMapperMock.returns(feList).selectTemplateExpress(1L);
    freightExpressAllMapperMock.returns(1).deleteTemplateExpressAll(1L);
    freightExpressMapperMock.returns(1).deleteFreExpByTid(1L);
    freightTemplateMapperMock.returns(1).updateByPrimaryKeySelective(freightList.get(0));

    //Boss
    expressInfoMapperMock.returns(express).selectByshoreExpId(1L);
    freightExpressMapperMock.returns(1).insertNewFreightExpress(feList.get(0));
    freightExpressMapperMock.returns(1L).selectLastDistributionId();
    freightExpressAllMapperMock.returns(1).insertSelective(fall.get(0));

    //第三方
    sysLogisticsCompanyMapperMock.returns(sysLogisticsCompany).selectCompanyById(1L);
    freightExpressMapperMock.returns(1).insertNewFreightExpress(feList.get(0));
    freightExpressMapperMock.returns(1L).selectLastDistributionId();
    freightExpressAllMapperMock.returns(1).insertSelective(fall.get(0));

    MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
    request.setParameter("freightTemplateId","1");
    request.setParameter("logComId", new String[]{"1"});
    request.setParameter("shunfeng_start", new String[]{"1"});
    request.setParameter("shunfeng_postage",new String[]{"1"});
    request.setParameter("shunfeng_plus",new String[]{"1"});
    request.setParameter("shunfeng_postageplus",new String[]{"1"});
    request.setParameter("shunfeng_areas", new String[]{"1"});
    //boss
    request.getSession().setAttribute("thirdId", null);
    assertNotNull(freightTemplateService.saveFreight(request, freightList.get(0)));
    request.getSession().setAttribute("thirdId", "1");
    //third
    assertNotNull(freightTemplateService.saveFreight(request, freightList.get(0)));

  }


  /**
   * 添加运费模板
   */
  @Test
  public void testAddFreight(){
    freightTemplateMapperMock.returns(1).insertNewFreightTemplate(freightList.get(0));
    freightTemplateMapperMock.returns(1L).selectFreightTemplateLastId();

    expressInfoMapperMock.returns(express).selectByshoreExpId(1L);
    freightExpressMapperMock.returns(1).insertNewFreightExpress(feList.get(0));
    freightExpressMapperMock.returns(1L).selectLastDistributionId();
    freightExpressAllMapperMock.returns(1).insertSelective(fall.get(0));

    sysLogisticsCompanyMapperMock.returns(sysLogisticsCompany).selectCompanyById(1L);
    freightExpressMapperMock.returns(1).insertNewFreightExpress(feList.get(0));
    freightExpressMapperMock.returns(1L).selectLastDistributionId();
    freightExpressAllMapperMock.returns(1).insertSelective(fall.get(0));

    MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
    request.setParameter("logComId", new String[]{"1"});
    request.setParameter("shunfeng_start", new String[]{"1"});
    request.setParameter("shunfeng_postage", new String[]{"1"});
    request.setParameter("shunfeng_plus", new String[]{"1"});
    request.setParameter("shunfeng_postageplus", new String[]{"1"});
    request.setParameter("shunfeng_areas", new String[]{"1"});
    //boss
    request.getSession().setAttribute("thirdId", null);
    assertEquals(1, freightTemplateService.addFreight(request, freightList.get(0)));
    request.getSession().setAttribute("thirdId", "1");
    //third
    assertEquals(1, freightTemplateService.addFreight(request, freightList.get(0)));

  }

  /**
   * 根据城市Id 和第三方Id ,商品数量 查询运费
   */
  @Test
  public void testGetExpressPrice(){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("freightIsDefault", "1");
    paramMap.put("freightThirdId", 0);
    freightTemplateMapperMock.returns(freightList.get(0)).selectFreightTemplateSubOrder(paramMap);
    freightExpressMapperMock.returns(feList).selectTemplateExpress(1L);
    assertEquals(new BigDecimal(1), freightTemplateService.getExpressPrice(1L, 1L, 0L, 1, new BigDecimal(1)));
    assertEquals(new BigDecimal(1), freightTemplateService.getExpressPrice(1L, 1L, 1L, 1, new BigDecimal(1)));


    feList.get(0).setFreightExpressAll(null);
    freightExpressMapperMock.returns(feList).selectTemplateExpress(1L);
    assertEquals(new BigDecimal(1), freightTemplateService.getExpressPrice(1L, 1L, 0L, 1, new BigDecimal(1)));
    assertEquals(new BigDecimal(1), freightTemplateService.getExpressPrice(1L, 1L, 1L, 1, new BigDecimal(1)));
  }

  /**
   * 查询默认物流模板
   */
  @Test
  public void testSelectFreightTemplateDefault(){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("freightIsDefault", "1");
    paramMap.put("freightThirdId", "0");
    freightTemplateMapperMock.returns(freightList.get(0)).selectFreightTemplateSubOrder(paramMap);
    freightExpressMapperMock.returns(feList).selectTemplateExpress(1L);
    sysLogisticsCompanyMapperMock.returns(sysLogisticsCompany).selectCompanyById(1L);
    assertEquals(1, freightTemplateService.selectFreightTemplateDefault().size());

  }

  /**
   * 查询默认物流模板 其他商家
   */
  @Test
  public void testSelectFreightExpressByDistriThirdId(){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("freightIsDefault", "1");
    paramMap.put("freightThirdId", 1L);
    freightTemplateMapperMock.returns(freightList.get(0)).selectFreightTemplateSubOrder(paramMap);
    freightExpressMapperMock.returns(feList).selectTemplateExpress(1L);
    expressInfoMapperMock.returns(express).selectByshoreExpId(1L);
    assertNotNull(freightTemplateService.selectFreightExpressByDistriThirdId(1L));
  }


    /**
     * 查询运费模板名称和id
     * */
    @Test
     public void testQueryTemplate(){
      freightTemplateMapperMock.returns(freightList).queryTemplate();
      assertEquals(1,freightTemplateService.queryTemplate().size());
      }

}
