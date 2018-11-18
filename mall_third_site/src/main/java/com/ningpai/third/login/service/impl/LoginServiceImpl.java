/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.login.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.ningpai.third.register.bean.Sms;
import org.springframework.stereotype.Service;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.dao.CustomerAddressMapper;
import com.ningpai.customer.dao.CustomerMapper;
import com.ningpai.customer.service.CustomerPointServiceMapper;
import com.ningpai.other.util.IPAddress;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.third.login.bean.IpRecord;
import com.ningpai.third.login.mapper.IpRecordMapper;
import com.ningpai.third.login.service.LoginService;
import com.ningpai.third.seller.bean.StoreInfo;
import com.ningpai.third.seller.mapper.StoreInfoMapper;

/**
 * #see com.ningpai.site.login.service.LoginService
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年4月15日 下午4:02:38
 * @version 2.0
 */
@Service("loginServiceThird")
public class LoginServiceImpl implements LoginService {

    /**
     * spring注解 会员Mapper
     */
    private CustomerMapper customerMapper;
    /**
     * 会员收货地址
     */
    private CustomerAddressMapper addressMapper;
    /**
     * 会员积分接口
     */
    private CustomerPointServiceMapper customerPointServiceMapper;
    /**
     * 登陆IP
     */
    private IpRecordMapper ipRecordMapper;
    /**
     * 店铺
     */
    private StoreInfoMapper sotreInfoMapper;
    /**
     * 站点信息
     */
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    /**
     * 如果当前登录的会员是商家角色下面创建的员工，改员工 对应的角色信息如果已经删除，就禁止该会员登录商家 结果大于1就是角色还存在可以正常登陆
     * 小于1就限制其登陆商家
     * 
     * @param username
     *            用户名
     * @return
     */
    @Override
    public int checkThirdAuthority(String username) {
        return this.sotreInfoMapper.checkThirdAuthority(username);
    }

    /**
     * 查询短信信息
     * 
     * @return
     */
    @Override
    public Sms selectSms() {
        return this.sotreInfoMapper.selectSms();
    }

    /**
     * 判断是否是供应商
     * 
     * @param customerId
     * @return 如果是供应商 则返回true 否则返回false
     */
    private boolean isSupplier(Long customerId) {
        // 活动店铺信息
        StoreInfo sinfo = sotreInfoMapper.selectByCustomerId(customerId);
        return sinfo != null && "1".equals(sinfo.getIsSupplier());
    }

    /**
     * 验证用户
     * 
     * @param request
     * @param username
     *            登录名
     * @param password
     *            密码
     * @return
     */
    public int checkCustomerExists(HttpServletRequest request, String username, String password) {
        // 根据用户名判断用户是否存在 如果不存在 则返回
        if (!isCustomerExists(username)) {
            // 用户名不存在
            updateErrCount(request);
            // 2表示用户不存在
            return 2;
        }

        // 根据登录名 登录密码 查询单个的用户对象
        Customer customer = getCustomerByUserNameAndPassword(username, password);

        // 如果根据用户名和密码 没有获得用户信息 则返回失败 表示用户名和密码错误
        if (null == customer) {
            // 密码错误
            updateErrCount(request);
            return 0;
        }

        // 如果是供应商 则直接返回6
        if (isSupplier(customer.getCustomerId())) {
            // 为供应商
            return 6;
        }

        if ("1".equals(customer.getIsFlag())) {
            return 7;
        }

        customer.setCustomerPassword(null);
        // 设置登录时间
        customer.setLoginTime(new Date());
        // 设置登录Ip
        customer.setLoginIp(IPAddress.getIpAddr(request));
        // 修改登陆时间
        customerMapper.updateCustomerLoginTime(customer);
        // 会员ID
        request.getSession().setAttribute("customerId", customer.getCustomerId());
        // 当前会员对象
        request.getSession().setAttribute("cust", customer);
        // 站点信息对象
        request.getSession().setAttribute("bsetDomain", basicSetService.findBasicSet().getBsetDomain());
        // 清除错误次数
        removeErrCount(request);
        if (customer.getThirdId() != null) {
            // 根据用户ID 查询单个的商品信息
            StoreInfo storeInfo = sotreInfoMapper.selectByCustomerId(customer.getCustomerId());
            if (storeInfo != null) {
                // 店铺审核未通过
                if ("0".equals(storeInfo.getCheckStatus()) && "0".equals(storeInfo.getIsSubmit()) && null != storeInfo.getRefuseContent()) {
                    return 10;
                }
                if ("0".equals(storeInfo.getCheckStatus())
                        && ("1".equals(storeInfo.getIsSubmit()) || storeInfo.getRefuseContent() != null && storeInfo.getRefuseContent().length() != 0)) {
                    return 4;
                }
                if ("1".equals(storeInfo.getCheckStatus()) && "1".equals(storeInfo.getIsSubmit())) {
                    // 登陆成功 清空拒绝的
                    storeInfo.setRefuseContent("");
                    sotreInfoMapper.updateByPrimaryKeySelective(storeInfo);
                    // 商家ID
                    request.getSession().setAttribute("thirdId", customer.getThirdId());
                    // 店铺名称
                    request.getSession().setAttribute("storeName", storeInfo.getStoreName());
                    // 是否显示店铺首页
                    request.getSession().setAttribute("isStoreIndex", storeInfo.getIsStoreIndex());
                    return 1;
                }
                if ("1".equals(storeInfo.getCheckStatus())) {
                    return 5;
                }
            } else {
                if ("0".equals(customer.getIsSeller())) {
                    // 此用户不是商家 也不是商家管理人员
                    return 3;
                }
            }
        }
        if ("0".equals(customer.getIsSeller())) {
            // 此用户不是商家 也不是商家管理人员
            return 3;
        }
        request.getSession().setAttribute("thirdId", customer.getThirdId());
        // 密码正确
        return 1;
    }

    /**
     * 返回错误的条数
     * 
     * @param request
     */
    private void removeErrCount(HttpServletRequest request) {
        // 当前用户登陆的IP信息
        IpRecord ipRecord = ipRecordMapper.selectByIp(IPAddress.getIpAddr(request));
        if (ipRecord != null) {
            ipRecord.setErrCount(0L);
            // 修改登陆IP
            ipRecordMapper.updateByPrimaryKeySelective(ipRecord);
        }
    }

    /**
     * 获取错误的条数
     * 
     * @param request
     * @return
     */
    public Long getErrCount(HttpServletRequest request) {
        // 根据IP获取一组IP对象信息
        IpRecord ipRecord = ipRecordMapper.selectByIp(IPAddress.getIpAddr(request));
        // 判断当前用户IP登陆的错误次数
        return ipRecord != null ? ipRecord.getErrCount() : 0;
    }

    /**
     * 添加错误Ip记录
     * 
     * @param request
     */
    private void addErrIp(HttpServletRequest request) {
        // 创建一个IP对象
        IpRecord record = new IpRecord();
        // 设置创建时间
        record.setCaptTime(new Date());
        // 设置结束时间
        record.setEndCaptTime(new Date(System.currentTimeMillis() + 30 * 60 * 1000));
        // 设置当前用户的IP
        record.setIp(IPAddress.getIpAddr(request));
        // 是否删除
        record.setDelFlag("0");
        // 登陆的错误此时
        record.setErrCount(1L);
        // 保存一条IP对象
        ipRecordMapper.insertSelective(record);
    }

    /**
     * 修改错误次数
     * 
     * @param request
     */
    private void updateErrCount(HttpServletRequest request) {
        // 获取当前用户的IP信息
        IpRecord ipRecord = ipRecordMapper.selectByIp(IPAddress.getIpAddr(request));
        if (ipRecord == null) {
            // 添加错误Ip记录
            addErrIp(request);
            return;
        }
        // 设置当前用户错误此时
        ipRecord.setErrCount(ipRecord.getErrCount() + 1);
        // 更新IP信息
        ipRecordMapper.updateByPrimaryKeySelective(ipRecord);
    }

    public CustomerMapper getCustomerMapper() {
        return customerMapper;
    }

    @Resource(name = "customerMapper")
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
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

    public IpRecordMapper getIpRecordMapper() {
        return ipRecordMapper;
    }

    @Resource(name = "ipRecordMapper")
    public void setIpRecordMapper(IpRecordMapper ipRecordMapper) {
        this.ipRecordMapper = ipRecordMapper;
    }

    public StoreInfoMapper getSotreInfoMapper() {
        return sotreInfoMapper;
    }

    @Resource(name = "sotreInfoMapper")
    public void setSotreInfoMapper(StoreInfoMapper sotreInfoMapper) {
        this.sotreInfoMapper = sotreInfoMapper;
    }

    /**
     * 判断用户是否存在 如果存在 返回true 不存在 返回false
     * 
     * @return
     */
    private boolean isCustomerExists(String username) {
        return 0 != customerMapper.checkexistsByCustName(username);
    }

    /**
     * 根据用户名和密码 获得用户信息
     * 
     * @return
     */
    private Customer getCustomerByUserNameAndPassword(String username, String password) {
        Map<String, Object> paramMap = new HashMap<String, Object>(2);
        // 用户名
        paramMap.put("username", username);
        // 登录密码
        paramMap.put("password", password);
        // 根据登录名 登录密码 查询单个的用户对象
        return customerMapper.selectCustomerByNamePwd(paramMap);

    }

}
