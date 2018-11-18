/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Service;

import com.ningpai.customer.service.CustomerPointServiceMapper;
import com.ningpai.m.common.bean.Sms;
import com.ningpai.m.common.dao.SmsMapper;
import com.ningpai.m.common.util.SmsPost;
import com.ningpai.m.customer.bean.Customer;
import com.ningpai.m.customer.mapper.CustomerMapper;
import com.ningpai.m.customer.service.CustomerService;
import com.ningpai.m.customer.vo.CustomerAllInfo;
import com.ningpai.m.customer.vo.CustomerConstants;
import com.ningpai.util.PageBean;

/**
 * @see com.ningpai.m.customer.service.CustomerService
 * @author NINGPAI-zhangqiang
 * @since 2014年8月20日 上午10:58:38
 * @version 0.0.1
 */
@Service("customerServiceM")
public class CustomerServiceImpl implements CustomerService {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(CustomerServiceImpl.class);

    // spring 注解
    private CustomerMapper customerMapper;
    private SmsMapper mapper;
    private CustomerPointServiceMapper customerPointServiceMapper;

    /*
     * 
     * 
     * @see com.ningpai.m.customer.service.CustomerService#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public CustomerAllInfo selectByPrimaryKey(Long customerId) {
        return customerMapper.selectByPrimaryKey(customerId);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.service.CustomerService#sendPost(javax.servlet.http.HttpServletRequest, java.lang.String)
     */
    @Override
    public int sendPost(HttpServletRequest request, String moblie) {
        Sms sms = mapper.selectSms();
        if (sms == null) {
            return 0;
        }
        sms.setSendSim(moblie);
        int num = (int) ((Math.random() * 9 + 1) * 100000);
        request.getSession().setAttribute("mcCode", num);
        request.getSession().setAttribute("userMobile", moblie);
        sms.setMsgContext(((Integer) num).toString());
        try {
            if (SmsPost.sendPost(sms)) {
                return 1;
            }
            return 0;
        } catch (IOException e) {
            LOGGER.error("",e);
            return 0;
        }
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.service.CustomerService#getMCode(javax.servlet.http.HttpServletRequest, java.lang.String)
     */
    @Override
    public int getMCode(HttpServletRequest request, String code) {
        if (code.equals((int) request.getSession().getAttribute("mcCode") + "")) {
            return 1;
        }
        return 0;
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.service.CustomerService#updateCusomerPwd(javax.servlet.http.HttpServletRequest, java.lang.String)
     */
    @Override
    public int updateCusomerPwd(HttpServletRequest request, String userKey) {
        CustomerAllInfo allInfo = new CustomerAllInfo();
        allInfo.setInfoMobile((String) request.getSession().getAttribute("userMobile"));
        allInfo.setCustomerPassword(userKey);
        int result = customerMapper.updateCusomerPwd(allInfo);
        if (result == 1) {
            request.getSession().setAttribute("muFlag", "1");
        }
        return result;
    }
    
    /**
     * 根据会员编号查询相应的会员积分明细
     * 
     * @param paramMap
     *            查询条件
     * @param pb
     * @return
     */
    @Override
    public PageBean selectAllCustomerPoint(Long customerId, PageBean pb,Long date,String type) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(CustomerConstants.CUSTOMERID, customerId);
        paramMap.put(CustomerConstants.DATE, date);
        paramMap.put("pointType", type);
        int count = customerMapper.queryPointMCount(paramMap).intValue();
        if(count>0){
            pb.setRows(count); 
        }else{
            pb.setRows(0);
        }
        //设置每页显示的数目
        pb.setPageSize(5);
        paramMap.put(CustomerConstants.STARTNUM, pb.getStartRowNum());
        paramMap.put(CustomerConstants.ENDNUM, pb.getEndRowNum());
        // 查询所有积分记录
        pb.setList(customerMapper.queryAllPointMList(paramMap));
        return pb;
    }

    public CustomerMapper getCustomerMapper() {
        return customerMapper;
    }

    @Resource(name = "customerMapperM")
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public SmsMapper getMapper() {
        return mapper;
    }

    @Resource(name = "smsMapperM")
    public void setMapper(SmsMapper mapper) {
        this.mapper = mapper;
    }

    public CustomerPointServiceMapper getCustomerPointServiceMapper() {
        return customerPointServiceMapper;
    }

    @Resource(name = "customerPointServiceMapper")
    public void setCustomerPointServiceMapper(CustomerPointServiceMapper customerPointServiceMapper) {
        this.customerPointServiceMapper = customerPointServiceMapper;
    }
    /*
     * 修改会员
     * (non-Javadoc)
     * @see com.ningpai.m.customer.service.CustomerService#updateCustomer(com.ningpai.m.customer.bean.Customer)
     */
    @Override
    public int updateCustomer(Customer customer) {
        return this.customerMapper.updateByPrimaryKeySelective(customer);
    }
    /*
     * 修改会员信息
     * (non-Javadoc)
     * @see com.ningpai.m.customer.service.CustomerService#updateCustomerInfo(com.ningpai.m.customer.vo.CustomerAllInfo)
     */
    @Override
    public int updateCustomerInfo(CustomerAllInfo customer) {
        return this.customerMapper.updateCustomerInfo(customer);
    }

}
