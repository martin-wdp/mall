/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.login.service.impl;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.customer.dao.CustomerAddressMapper;
import com.ningpai.customer.service.CustomerPointServiceMapper;
import com.ningpai.other.util.IPAddress;
import com.ningpai.site.customer.mapper.CustomerMapper;
import com.ningpai.site.login.service.LoginService;
import com.ningpai.site.shoppingcart.service.ShoppingCartService;
import com.ningpai.util.UtilDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Pattern;

/**
 * #see com.ningpai.site.login.service.LoginService
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年4月15日 下午4:02:38
 * @version 0.0.1
 */
@Service("loginServiceSite")
public class LoginServiceImpl implements LoginService {

    private static final String UTYPE = "uType";

    // spring注解 会员Mapper
    private CustomerMapper customerMapper;
    private Customer customer;
    private CustomerAddress address;
    private CustomerAddressMapper addressMapper;
    private CustomerPointServiceMapper customerPointServiceMapper;
    private ShoppingCartService shoppingCartService;

    /**
     *
     */
    @SuppressWarnings("static-access")
    @Override
    public int checkCustomerExists(HttpServletRequest request, String username, String password) {
        // 判断用户名和密码是否为空
        if (username == null || password == null) {
            return 0;
        }
        Map<String, Object> paramMap = null;
        String nameEmp = username.trim();
        paramMap = new HashMap<String, Object>();
        if (nameEmp.indexOf("@") != -1) {
            paramMap.put(UTYPE, "email");
        } else if (Pattern.compile("^0?(13|15|17|18|14)[0-9]{9}$").matcher(nameEmp).find()) {
            paramMap.put(UTYPE, "mobile");
        } else {
            paramMap.put(UTYPE, "username");
        }
        paramMap.put("username", username);
        Long existsFlag = customerMapper.checkExistsByCustNameAndType(paramMap);
        if (!"0".equals(existsFlag.toString())) {
            paramMap.put("password", password);
            customer = customerMapper.selectCustomerByNamePwdAndType(paramMap);
            if (customer != null) {
                // 判断用户是否被冻结
                if ("1".equals(customer.getIsFlag())) {
                    return 3;
                }
                // 增加登录积分
                if (!UtilDate.todayFormatString(new Date()).equals(UtilDate.todayFormatString(customer.getLoginTime()))) {
                    customerPointServiceMapper.addIntegralByType(customer.getCustomerId(), "1");
                }
                customer.setCustomerPassword(null);
                // 设置登录key
                UUID uuid = UUID.randomUUID();
                customer.setLoginKey(uuid.toString());
                // 设置登录时间
                customer.setLoginTime(new Date());
                // 设置登录Ip
                customer.setLoginIp(IPAddress.getIpAddr(request));
                if (customer.getAeadTime() == null) {
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(new Date());
                    calendar.add(calendar.DATE, -1);
                    customer.setAeadTime(calendar.getTime());
                }
                customerMapper.updateByPrimaryKeySelective(customer);
                request.getSession().setAttribute("cust", customer);
                request.getSession().setAttribute("customerId", customer.getCustomerId());
                //增加企业会员标识 0-普通 1-企业 15-11-04 ly
                request.getSession().setAttribute("vip",customer.getIsEnterprise());
                // 保存默认收获地址
                address = addressMapper.selectDefaultAddr(customer.getCustomerId());
                request.getSession().setAttribute("address", address);
                // 密码正确
                shoppingCartService.loadCoodeShopping(request);
                return 1;
            } else {
                // 密码错误
                return 0;
            }
        } else {
            // 用户名不存在
            return 2;
        }
    }

    public CustomerMapper getCustomerMapper() {
        return customerMapper;
    }

    @Resource(name = "customerMapperSite")
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public ShoppingCartService getShoppingCartService() {
        return shoppingCartService;
    }

    @Resource(name = "ShoppingCartService")
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    public CustomerAddressMapper getAddressMapper() {
        return addressMapper;
    }

    @Resource(name = "customerAddressMapper")
    public void setAddressMapper(CustomerAddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public CustomerPointServiceMapper getCustomerPointServiceMapper() {
        return customerPointServiceMapper;
    }

    @Resource(name = "customerPointServiceMapper")
    public void setCustomerPointServiceMapper(CustomerPointServiceMapper customerPointServiceMapper) {
        this.customerPointServiceMapper = customerPointServiceMapper;
    }

}
