package com.junit.custom.customer.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import com.alibaba.fastjson.JSON;
import com.ningpai.customer.bean.CustomerInfo;
import com.ningpai.customer.bean.CustomerPoint;
import com.ningpai.customer.bean.CustomerPointLevel;
import com.ningpai.customer.bean.RegisterPoint;
import com.ningpai.customer.dao.CustomerInfoMapper;
import com.ningpai.customer.dao.CustomerPointMapper;
import com.ningpai.customer.dao.IntegralSetMapper;
import com.ningpai.customer.service.CustomerPointServiceMapper;
import com.ningpai.customer.service.PointLevelServiceMapper;
import com.ningpai.customer.service.impl.CustomerPointServiceMapperImpl;
import com.ningpai.other.bean.IntegralSet;
import com.ningpai.util.PageBean;

/**
 * 会员积分Service接口单元测试
 * @author qiyuanyuan
 *
 */
public class CustomerPointServiceMapperTest extends UnitilsJUnit3 {

    /**
     * 需要测试的service
     */
    @TestedObject
    private CustomerPointServiceMapper customerPointServiceMapper = new CustomerPointServiceMapperImpl();

    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<CustomerPointMapper> customerPointMapperMock;
    @InjectIntoByType
    Mock<IntegralSetMapper> integralSetMapperMock;
    @InjectIntoByType
    Mock<CustomerInfoMapper> customerInfoMapperMock;
    @InjectIntoByType
    Mock<PointLevelServiceMapper> pointLevelServiceMapperMock;

    /**
     * JS数据
     */
    @FileContent("customerPointList.js")
    private String customerPointListJS;
    @FileContent("customerPoint.js")
    private String customerPointJS;
    @FileContent("registerPointList.js")
    private String registerPointListJS;
    @FileContent("customerPointLevelList.js")
    private String customerPointLevelListJS;
    @FileContent("customerInfo.js")
    private String customerInfoJS;
    @FileContent("integralSet.js")
    private String integralSetJS;

    /**
     * 共享数据
     */
    List<CustomerPoint> customerPointList;
    CustomerPoint customerPoint;
    List<RegisterPoint> registerPointList;
    List<CustomerPointLevel> customerPointLevelList;
    CustomerInfo customerInfo;
    IntegralSet integralSet;

    public void setUp() {
        customerPointList = JSON.parseArray(customerPointListJS,CustomerPoint.class);
        customerPoint = JSON.parseObject(customerPointJS, CustomerPoint.class);
        registerPointList = JSON.parseArray(registerPointListJS,RegisterPoint.class);
        customerPointLevelList = JSON.parseArray(customerPointLevelListJS,CustomerPointLevel.class);
        customerInfo = JSON.parseObject(customerInfoJS, CustomerInfo.class);
        integralSet = JSON.parseObject(integralSetJS, IntegralSet.class);
    }

    /**
     * 测试根据条件查询会员推广积分记录
     */
    
      @Test 
      public void testQueryregisterpoint(){ 
          Map<String, Object> paramMap= new HashMap<String, Object>();
          
          Date date =  new Date();
          paramMap.put("regPointReferee","test");// 推荐人
          paramMap.put("regPointRecom", "test1");// 被推荐人 
          paramMap.put("startTime",date);// 开始时间 
          paramMap.put("endTime",date);// 结束时间
         customerPointMapperMock.returns(1).selectRegisterPointSize(paramMap);
          customerPointMapperMock.returns(registerPointList).selectRegisterPont(paramMap);
          RegisterPoint rp = new RegisterPoint();
          rp.setRegPointReferee("test");
          rp.setRegPointRecom("test1");
          rp.setStartTime(date);
          rp.setEndTime(date);
          rp.setCreateTimeF("1");
          rp.setCreateTimeT("1");
          assertEquals(1,customerPointServiceMapper.queryregisterpoint(rp, new PageBean()).getList().size()); 
      }
     
    /**
     * 测试查询所有会员积分记录
     */
    @Test
    public void testSelectAllCustomerPoint() {
        Map<String, Integer> paramMap = new HashMap<String, Integer>();
        customerPointMapperMock.returns(3).selectAllCustomerCount();
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 15);
        customerPointMapperMock.returns(customerPointList).selectAllCustomerPoint(paramMap);
        assertEquals(3,customerPointServiceMapper.selectAllCustomerPoint(new PageBean()).getList().size());
    }

    /**
     * 测试删除会员积分记录
     */
    @Test
    public void testDeleteCustomerPoint() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parameterValues", new String[] { "1" });
        customerPointMapperMock.returns(1).deleteCustomerPointByPids(paramMap);
        assertEquals(1,customerPointServiceMapper.deleteCustomerPoint(new String[] { "1" }));
    }

    /**
     * 测试按条件查找积分记录
     */
    @Test
    public void testSelectCustPointByCustPoint() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 15);
        customerPointMapperMock.returns(3).selectCustmerPointSize(new CustomerPoint());
        customerPointMapperMock.returns(customerPointList).selectCustPointByCustPoint(new CustomerPoint());
        assertEquals(3,customerPointServiceMapper.selectCustPointByCustPoint(new CustomerPoint(),new PageBean()).getList().size());
    }

    /**
     * 测试根据类型添加积分 会员编号
     */
    @Test
    public void testAddIntegralByType() {
        integralSetMapperMock.returns(integralSet).findPointSet();
        customerPointMapperMock.returns(1).insertSelective(new CustomerPoint());
        customerInfoMapperMock.returns(customerInfo).selectCustInfoById(1L);
        pointLevelServiceMapperMock.returns(customerPointLevelList).selectAllPointLevel();
        customerInfoMapperMock.returns(1).updateInfoByCustId(customerInfo);
        assertEquals(0, customerPointServiceMapper.addIntegralByType(1L, "1"));
    }

    /**
     * 测试添加或扣除会员积分
     */
    @Test
    public void testSaveCustomerPoint() {
        customerPointMapperMock.returns(1).insertSelective(customerPoint);
        customerInfoMapperMock.returns(customerInfo).selectByCustomerId(1L);
        pointLevelServiceMapperMock.returns(customerPointLevelList).selectAllPointLevel();
        customerInfoMapperMock.returns(1).updateInfoByCustId(customerInfo);
        assertEquals(12,customerPointServiceMapper.saveCustomerPoint(1L, customerPoint));
    }

    /**
     * 测试根据类型添加积分 会员编号
     */
    public void testAddIntegralByTypeTwo() {
        integralSetMapperMock.returns(integralSet).findPointSet();
        customerPointMapperMock.returns(1).insertSelective(new CustomerPoint());
        customerInfoMapperMock.returns(customerInfo).selectByCustomerId(1L);
        pointLevelServiceMapperMock.returns(customerPointLevelList).selectAllPointLevel();
        assertEquals(0, customerPointServiceMapper.addIntegralByType(1L, "6", 10d));
    }
}
