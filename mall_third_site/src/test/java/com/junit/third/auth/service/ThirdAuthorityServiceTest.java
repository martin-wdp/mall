/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.third.auth.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.dao.CustomerMapper;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.third.auth.bean.ThirdAuthority;
import com.ningpai.third.auth.bean.ThirdManagerAuthority;
import com.ningpai.third.auth.bean.ThirdPage;
import com.ningpai.third.auth.mapper.ThirdAuthorityMapper;
import com.ningpai.third.auth.mapper.ThirdAuthorityPageMapper;
import com.ningpai.third.auth.mapper.ThirdManagerAuthorityMapper;
import com.ningpai.third.auth.mapper.ThirdManagerMapper;
import com.ningpai.third.auth.service.ThirdAuthorityService;
import com.ningpai.third.auth.service.impl.ThirdAuthorityServiceImpl;
import com.ningpai.util.PageBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * <p>第三方权限角色Service测试</p>
 * @author guogn
 * @since 20150923
 * @version 2.0
 */
public class ThirdAuthorityServiceTest  extends UnitilsJUnit3 {


  /**
   * 需要测试的类
   */
    @TestedObject
    private ThirdAuthorityService thirdAuthorityService = new ThirdAuthorityServiceImpl();

  /**
   * 模拟
   */
    @InjectIntoByType
    Mock<ThirdAuthorityMapper> thirdAuthorityMapperMock;
    @InjectIntoByType
    Mock<ThirdAuthorityPageMapper> thirdAuthorityPageMapperMock;
    @InjectIntoByType
    Mock<CustomerMapper> customerMapperMock;
    @InjectIntoByType
    Mock<ThirdManagerAuthorityMapper> thirdManagerAuthorityMapperMock;
    @InjectIntoByType
    Mock<ThirdManagerMapper> thirdManagerMapperMock;
    @InjectIntoByType
    Mock<CustomerServiceMapper> customerServiceMapperMock;
  /**
   * JS数据
   */
    @FileContent("thirdAuthorityList.js")
    private String thirdAuthorityListJs;
    @FileContent("thirdPageList.js")
    private String thirdPageListJs;
  /**
   * 共享数据
   */
    List<ThirdAuthority> thirdAuthorityList;

    List<ThirdPage> thirdPageList;
  /**
   * 初始化
   */
    @Override
    public void setUp(){
      thirdAuthorityList = JSON.parseArray(thirdAuthorityListJs, ThirdAuthority.class);
      thirdPageList=JSON.parseArray(thirdPageListJs, ThirdPage.class);
    }
    /***
     * 根据主键获取单个的职位对象
     */
    public void  testSelectAuthorById(){
      thirdAuthorityMapperMock.returns(thirdAuthorityList.get(0)).selectAuthorById(1L);
      assertNotNull(thirdAuthorityService.selectAuthorById(1L));

    }
    /**
     * 根据商家编号查询商家权限列表
     */
    public void  testQueryThirdAuthorityByStotreId(){
      thirdAuthorityList.get(0).setPages(thirdPageList);
      thirdAuthorityMapperMock.returns(thirdAuthorityList).queryThirdAuthorityByStotreId(1L);
      Map<String, Object> parmaMap = new HashMap<String, Object>();
      // 权限ID
      parmaMap.put("authorityId", 1L);
      // 父类ID
      parmaMap.put("parentId", 1L);
      thirdAuthorityPageMapperMock.returns(thirdPageList).selectPageByAuthIdAndParentId(parmaMap);
      assertEquals(1, thirdAuthorityService.queryThirdAuthorityByStotreId(1L).size());

    }

    /**
     * 添加权限角色
    */
    public void  testAddAuthority(){
      MockHttpServletRequest request = new MockHttpServletRequest("POST","");
      request.getSession().setAttribute("thirdId", 1L);
      thirdAuthorityMapperMock.returns(1).insertSelective(thirdAuthorityList.get(0));
      assertEquals(1, thirdAuthorityService.addAuthority(request, thirdAuthorityList.get(0)));
    }
    /**
     *
    * 查询权限角色存在性
    */
    public void  testCheckAuthorityExist(){
      thirdAuthorityMapperMock.returns(thirdAuthorityList).selectByDesignation(thirdAuthorityList.get(0).getDesignation(), 1L);
      MockHttpServletRequest request = new MockHttpServletRequest("POST","");
      request.getSession().setAttribute("thirdId", 1L);
      assertEquals(1, thirdAuthorityService.checkAuthorityExist(request, thirdAuthorityList.get(0)));
    }

   /**
     * 删除权限角色
    */
    public void  testDelAuthority(){
      thirdAuthorityMapperMock.returns(1).deleteByPrimaryKey(thirdAuthorityList.get(0).getId(), 1L);
      assertEquals(1, thirdAuthorityService.delAuthority(thirdAuthorityList.get(0), 1L));
    }

   /**
     * 修改权限名称 --重命名
    **/
    public void  testUpdateAuthorityName(){
      thirdAuthorityMapperMock.returns(1).updateByPrimaryKeySelective(thirdAuthorityList.get(0));
      assertEquals(1, thirdAuthorityService.updateAuthorityName(thirdAuthorityList.get(0)));
    }
  /**
    *
     * 查询商家员工列表
    **/
    public void  testQueryEmployeeListByStotreId(){
      thirdManagerMapperMock.returns(1L).queryEmployeeList(1L);
      Map<String, Object> paramMap = new HashMap<String, Object>();
      paramMap.put("storeId", 1L);
      // 分页起始页数
      paramMap.put("startRowNum", 0);
      // 分页结束页数
      paramMap.put("endRowNum", 10);
      thirdManagerMapperMock.returns(thirdAuthorityList).queryEmployeeListByStotreId(paramMap);
      assertEquals(1, thirdAuthorityService.queryEmployeeListByStotreId(1L, new PageBean()).getList().size());
    }
/**
    *
     * 添加员工
 */
    public void  testAddEmp(){
      CustomerAllInfo allInfo = new CustomerAllInfo();
      Customer customer = new Customer();
      customerServiceMapperMock.returns(1).addCustomer(allInfo);
      // 保存查询的条件
      Map<String, Object> paramMap = new HashMap<String, Object>();
      // 用户名
      paramMap.put("username", allInfo.getCustomerUsername());
      // 用户密码
      paramMap.put("password", allInfo.getCustomerPassword());
      // 根据条件查询出来的会员信息
     customerMapperMock.returns(customer).selectCustomerByNamePwd(paramMap);
      paramMap = new HashMap<String, Object>();
      // 设置会员ID
      paramMap.put("customerId", allInfo.getCustomerId());
      // 权限ID
      paramMap.put("authId", 1L);
      // 商家ID
      paramMap.put("storeId", 1L);
      thirdManagerAuthorityMapperMock.returns(1).addRecord(paramMap);
      MockHttpServletRequest request = new MockHttpServletRequest();
      request.getSession().setAttribute("storeId",1L);
      assertEquals(0, thirdAuthorityService.addEmp(request, allInfo, 1L));
    }
   /**
    *
    * 修改员工角色
   */
    public void  testUpdateByPrimaryKeySelective(){
      ThirdManagerAuthority tm = new ThirdManagerAuthority();
      tm.setManagerId(1L);
      thirdManagerAuthorityMapperMock.returns(1L).selectCustomerByManagerId(1L);
      thirdManagerAuthorityMapperMock.returns(1).updateByPrimaryKeySelective(tm);
      assertEquals(1, thirdAuthorityService.updateByPrimaryKeySelective(tm,1L));
    }

  /**
   *
   * 验证用户名是否存在
    */
    public void  testCheckUsernameExitOrNot(){

    }
}
