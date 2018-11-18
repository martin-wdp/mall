/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.customer.bean.CustomerConsume;
import com.ningpai.customer.dao.CustomerConsumeMapper;
import com.ningpai.customer.service.CustomerConsumeServiceMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.util.PageBean;

/**
 * @see com.ningpai.customer.service.CustomerConsumeServiceMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年3月21日 下午3:06:17
 * @version 0.0.1
 */
@Service("customerConsumeServiceMapper")
public class CustomerConsumeServiceMapperImpl extends BasicSqlSupport implements
        CustomerConsumeServiceMapper {
    // spring注解
    private CustomerConsumeMapper customerConsumeMapper;

    /*
     * 查询所有会员积分记录
     * 
     * @see com.ningpai.customer.service.CustomerConsumeServiceMapper#
     * selectAllCustomerConsume (com.ningpai.util.PageBean)
     */
    @Override
    public PageBean selectAllCustomerConsume(PageBean pageBean) {
        return null;
    }

    /*
     * 删除会员积分记录
     * 
     * @see com.ningpai.customer.service.CustomerConsumeServiceMapper#
     * deleteCustomerConsume(java.lang.String[])
     */
    @Override
    public int deleteCustomerConsume(String[] parameterValues) {
        int count = 0;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put("parameterValues", parameterValues);
            count = customerConsumeMapper.deleteCustomerConsumeByBids(paramMap);
        } finally {
            paramMap = null;
        }

        return count;
    }

    /*
     * 按条件查找积分记录
     * 
     * @see com.ningpai.customer.service.CustomerConsumeServiceMapper#
     * selectCustConsumeByCustConsume
     * (com.ningpai.customer.bean.CustomerConsume, com.ningpai.util.PageBean)
     */
    @Override
    public PageBean selectCustConsumeByCustConsume(CustomerConsume consume,
            PageBean pageBean, String type) {
        Map<String, Object> paramMap = null;
        int no = 0;
        try {
            consume.setBalanceType(type);
            // 设置总行数
            pageBean.setRows(customerConsumeMapper
                    .selectCustmerConsumeSize(consume));
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean
                    .getRows() / pageBean.getPageSize() : (pageBean.getRows()
                    / pageBean.getPageSize() + 1);
            no = no == 0 ? 1 : no;
            // 若页码超过最大页码 则显示最后一个
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }
            paramMap = new HashMap<String, Object>();
            // 设置查询条件
            paramMap.put("consume", consume);
            paramMap.put("startRowNum", pageBean.getStartRowNum());
            paramMap.put("endRowNum", pageBean.getEndRowNum());
            paramMap.put("type", type);
            // 查询会员信息
            pageBean.setList(customerConsumeMapper
                    .selectCustConsumeByParamMap(paramMap));
        } finally {
            paramMap = null;
        }
        return pageBean;
    }

    public CustomerConsumeMapper getCustomerConsumeMapper() {
        return customerConsumeMapper;
    }

    @Resource(name = "customerConsumeMapper")
    public void setCustomerConsumeMapper(
            CustomerConsumeMapper customerConsumeMapper) {
        this.customerConsumeMapper = customerConsumeMapper;
    }

}
