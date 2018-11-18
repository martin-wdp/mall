package com.junit.third.login.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.dao.CustomerAddressMapper;
import com.ningpai.customer.dao.CustomerMapper;
import com.ningpai.customer.service.CustomerPointServiceMapper;
import com.ningpai.system.bean.BasicSet;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.third.login.mapper.IpRecordMapper;
import com.ningpai.third.login.service.LoginService;
import com.ningpai.third.login.service.impl.LoginServiceImpl;
import com.ningpai.third.register.bean.Sms;
import com.ningpai.third.seller.bean.StoreInfo;
import com.ningpai.third.seller.mapper.StoreInfoMapper;


/**
 * 登录Service测试
 * @author djk
 *
 */
public class LoginServiceTest   extends UnitilsJUnit3
{
	/**
     * 需要测试的Service
     */
    @TestedObject
    private LoginService loginService = new LoginServiceImpl();
    
    
    /**
     * 模拟
     */
    @InjectIntoByType
    private Mock<CustomerMapper> customerMapperMock;
    
    /**
     * 模拟
     */
    @InjectIntoByType
    private Mock<CustomerAddressMapper> customerAddressMapperMock;
    
    /**
     * 模拟
     */
    @InjectIntoByType
    private Mock<CustomerPointServiceMapper> customerPointServiceMapperMock;
    
    /**
     * 模拟
     */
    @InjectIntoByType
    private Mock<IpRecordMapper> ipRecordMapperMock;
    
    /**
     * 模拟
     */
    @InjectIntoByType
    private Mock<StoreInfoMapper> storeInfoMapperMock;
    
    /**
     * 模拟
     */
    @InjectIntoByType
    private Mock<BasicSetService> basicSetServiceMock;
    
    /**
	 *如果当前登录的会员是商家角色下面创建的员工，改员工
     * 对应的角色信息如果已经删除，就禁止该会员登录商家 结果大于1就是角色还存在可以正常登陆
     * 小于1就限制其登陆商家     
     * */
    @Test
    public void testCheckThirdAuthority()
    {
    	storeInfoMapperMock.returns(1).checkThirdAuthority("a");
    	assertEquals(1, loginService.checkThirdAuthority("a"));
    }
    
    /**
     * 查询短信信息测试
     */
    @Test
    public void testSelectSms()
    {
    	storeInfoMapperMock.returns(new Sms()).selectSms();
    	assertNotNull(loginService.selectSms());
    }
    
    /**
     * 验证用户
     */
    @Test
    public void testCheckCustomerExists()
    {
    	StoreInfo storeInfo = new StoreInfo();
    	storeInfo.setCheckStatus("1");
    	Customer customer = new Customer();
    	customer.setThirdId(1L);
    	customer.setCustomerId(1L);
    	customerMapperMock.returns(1L).checkexistsByCustName("djk");
        Map<String, Object>  paramMap = new HashMap<String, Object>(2);
        paramMap.put("username", "djk");
        paramMap.put("password", "1234");
        customerMapperMock.returns(customer).selectCustomerByNamePwd(paramMap);
        basicSetServiceMock.returns(new BasicSet()).findBasicSet();
        storeInfoMapperMock.returns(storeInfo).selectByCustomerId(1L);
    	assertEquals(5, loginService.checkCustomerExists(new MockHttpServletRequest(), "djk", "1234"));
    }
    
    /**
     * 获取错误次数
     */
    @Test
    public void testGetErrCount()
    {
    	Long result =0L;
    	assertEquals(result, loginService.getErrCount(new MockHttpServletRequest()));
    }
}
