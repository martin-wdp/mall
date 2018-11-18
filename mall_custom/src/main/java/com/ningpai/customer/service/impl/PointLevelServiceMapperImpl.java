/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.customer.bean.CustomerPointLevel;
import com.ningpai.customer.dao.CustomerPointLevelMapper;
import com.ningpai.customer.service.PointLevelServiceMapper;
import com.ningpai.util.PageBean;

/**
 * 会员等级功能接口
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月19日 下午3:43:10
 * @version 0.0.1
 */
@Service("pointLevelServiceMapper")
public class PointLevelServiceMapperImpl implements PointLevelServiceMapper {
    private CustomerPointLevelMapper customerPointLevelMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.service.PointLevelServiceMapper#selectAllPointLevel
     * (com.ningpai.util.PageBean)
     */
    @Override
    public PageBean selectAllPointLevel(PageBean pageBean) {
        Map<String, Integer> paramMap = null;
        Integer no = 0;
        try {
            pageBean.setRows(customerPointLevelMapper.selectAllCount());
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean
                    .getRows() / pageBean.getPageSize() : (pageBean.getRows()
                    / pageBean.getPageSize() + 1);
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
                pageBean.setEndRowNum(no * pageBean.getPageSize());
            }
            paramMap = new HashMap<String, Integer>();
            paramMap.put("startRowNum", pageBean.getStartRowNum());
            paramMap.put("endRowNum", pageBean.getEndRowNum());
            pageBean.setList(customerPointLevelMapper
                    .selectPointLevelByLimit(paramMap));
        } finally {
            paramMap = null;
        }
        return pageBean;
    }

    /*
     * 
     * 
     * @see com.ningpai.customer.service.PointLevelServiceMapper#addPointLevel
     * (com.ningpai.customer.bean.CustomerPointLevel)
     */
    @Override
    public int addPointLevel(CustomerPointLevel customerPointLevel) {
        if ("1".equals(customerPointLevel.getIsDefault())) {
            cancelBeforeDefault();
        }
        return customerPointLevelMapper.insertSelective(customerPointLevel);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.service.PointLevelServiceMapper#selectPointLevelById
     * (java.lang.Long)
     */
    @Override
    public CustomerPointLevel selectPointLevelById(Long pointLevelId) {
        return customerPointLevelMapper.selectByPrimaryKey(pointLevelId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.service.PointLevelServiceMapper#updatePointLevel
     * (com.ningpai.customer.bean.CustomerPointLevel)
     */
    @Override
    public int updatePointLevel(CustomerPointLevel customerPointLevel) {
        if ("1".equals(customerPointLevel.getIsDefault())) {
            cancelBeforeDefault();
        }else{
            //查询数据库状态为1的是否还有记录，如果没有记录就不允许修改这个默认状态
            int count = this.customerPointLevelMapper.queryIsDefaultCount();
            if(count <= 1){
                customerPointLevel.setIsDefault("1");
            }
        }
        return customerPointLevelMapper
                .updateByPrimaryKeySelective(customerPointLevel);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.service.PointLevelServiceMapper#deletePointLevel
     * (java.lang.String[])
     */
    @Override
    public int deletePointLevel(String[] pointLevelIds) {
        Integer count = 0;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put("pointLevelIds", pointLevelIds);
            count = customerPointLevelMapper.deletePointLevelByIds(paramMap);
        } finally {
            paramMap = null;
        }
        return count;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.service.PointLevelServiceMapper#selectPointLevelByName
     * (java.lang.String)
     */
    @Override
    public Long selectPointLevelByName(String pointLevelName) {
        return customerPointLevelMapper.selectByPrimaryName(pointLevelName);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.service.PointLevelServiceMapper#selectAllPointLevel
     * ()
     */
    @Override
    public List<CustomerPointLevel> selectAllPointLevel() {
        return customerPointLevelMapper.selectAllPointLevel();
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.service.PointLevelServiceMapper#selectDefaultPointLevel
     * ()
     */
    @Override
    public Long selectDefaultPointLevel() {
        return customerPointLevelMapper.selectDefaultPointLevel();
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.service.PointLevelServiceMapper#cancelBeforeDefault
     * ()
     */
    @Override
    public int cancelBeforeDefault() {
        return customerPointLevelMapper.cancelBeforeDefault();
    }

    public CustomerPointLevelMapper getCustomerPointLevelMapper() {
        return customerPointLevelMapper;
    }

    @Resource(name = "customerPointLevelMapper")
    public void setCustomerPointLevelMapper(
            CustomerPointLevelMapper customerPointLevelMapper) {
        this.customerPointLevelMapper = customerPointLevelMapper;
    }

}
