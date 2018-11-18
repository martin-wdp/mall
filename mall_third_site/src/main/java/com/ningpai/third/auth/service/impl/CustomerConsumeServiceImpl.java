/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.third.auth.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ningpai.third.auth.bean.CustomerConsume;
import com.ningpai.third.auth.mapper.CustomerConsumeMapper;
import com.ningpai.third.auth.service.CustomerConsumeService;
import com.ningpai.util.PageBean;

/**
 * SERVICE实体类-会员消费记录
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月16日下午2:57:52
 * @version 2.0
 */
@Service("customerConsumeService")
public class CustomerConsumeServiceImpl implements CustomerConsumeService {

    /* mapper依赖 */
    private CustomerConsumeMapper customerConsumeMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.site.customer.service.CustomerConsumeService#deleteConsume
     * (java.lang.Long)
     */
    @Override
    public int deleteConsume(Long balanceId) {
        return this.customerConsumeMapper.deleteByPrimaryKey(balanceId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.site.customer.service.CustomerConsumeService#saveConsume(
     * com.ningpai.site.customer.bean.CustomerConsume)
     */
    @Override
    public int saveConsume(CustomerConsume record) {
        return this.customerConsumeMapper.insertSelective(record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.site.customer.service.CustomerConsumeService#updateConsume
     * (com.ningpai.site.customer.bean.CustomerConsume)
     */
    @Override
    public int updateConsume(CustomerConsume record) {
        return this.customerConsumeMapper.updateByPrimaryKeySelective(record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.site.customer.service.CustomerConsumeService#getConsumeById
     * (java.lang.Long)
     */
    @Override
    public CustomerConsume getConsumeById(Long balanceId) {
        return this.customerConsumeMapper.selectByPrimaryKey(balanceId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.site.customer.service.CustomerConsumeService#queryAllConsumeByCid
     * (com.ningpai.util.PageBean, java.lang.Long, java.lang.Integer)
     */
    @Override
    public PageBean queryAllConsumeByCid(PageBean pb, Long customerId, Integer date) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("customerId", customerId);
            map.put("date", date);
            // 查询数据的总行数并设置到PageBean中
            pb.setRows(this.customerConsumeMapper.queryConsumeByCidCount(map));
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(this.customerConsumeMapper.queryAllConsumeByCid(map));
        } finally {
            map = null;
        }
        return pb;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.site.customer.service.CustomerConsumeService#selectTotalNumByCid
     * (java.lang.Long)
     */
    @Override
    public BigDecimal selectTotalNumByCid(Long customerId) {
        return this.customerConsumeMapper.selectTotalNumByCid(customerId);
    }

    public CustomerConsumeMapper getCustomerConsumeMapper() {
        return customerConsumeMapper;
    }

    @Resource(name = "siteCustomerConsumeMapper")
    public void setCustomerConsumeMapper(CustomerConsumeMapper customerConsumeMapper) {
        this.customerConsumeMapper = customerConsumeMapper;
    }

}
