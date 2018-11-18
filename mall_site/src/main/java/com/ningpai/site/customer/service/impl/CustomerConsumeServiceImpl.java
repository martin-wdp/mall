/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.site.customer.service.impl;

import com.ningpai.site.customer.bean.CustomerConsume;
import com.ningpai.site.customer.mapper.CustomerConsumeMapper;
import com.ningpai.site.customer.service.CustomerConsumeService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * SERVICE实体类-会员消费记录
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月16日下午2:57:52
 */
@Service("siteCustomerConsumeService")
public class CustomerConsumeServiceImpl implements CustomerConsumeService {

    /**
     * 会员消费记录
     */
    private CustomerConsumeMapper customerConsumeMapper;

    /**
     *
     * @param balanceId
     *            删除会员消费记录 {@link java.lang.Long }
     * @return
     */
    @Override
    public int deleteConsume(Long balanceId) {

        return this.customerConsumeMapper.deleteByPrimaryKey(balanceId);
    }

    /**
     * 添加会员消费记录
     * 
     * @param record
     *            {@link com.ningpai.site.customer.bean.CustomerConsume}
     * @return
     */
    @Override
    public int saveConsume(CustomerConsume record) {

        return this.customerConsumeMapper.insertSelective(record);
    }

    /**
     * 根据会员消费记录编号修改会员等级
     * 
     * @param record
     *            会员消费记录 {@link com.ningpai.site.customer.bean.CustomerConsume}
     * @return
     */
    @Override
    public int updateConsume(CustomerConsume record) {

        return this.customerConsumeMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据会员消费记录编号获取会员消费记录
     * 
     * @param balanceId
     *            会员消费记录编号 {@link java.lang.Long}
     * @return
     */
    @Override
    public CustomerConsume getConsumeById(Long balanceId) {

        return this.customerConsumeMapper.selectByPrimaryKey(balanceId);
    }

    /**
     * 按会员编号和时间标记查询消费记录的分页数据
     * 
     * @param pb
     *            查询条件
     * @param customerId
     * @param date
     * @return
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

    /**
     * 根据会员编号查询消费总和
     * 
     * @param customerId
     *            会员编号
     * @return
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
