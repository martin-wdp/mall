/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.site.customer.mapper.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.customer.bean.CustomerConsume;
import com.ningpai.site.customer.mapper.CustomerConsumeMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * DAO实现类-会员消费记录
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月16日下午2:23:48
 */
@Repository("siteCustomerConsumeMapper")
public class CustomerConsumeMapperImpl extends BasicSqlSupport implements
        CustomerConsumeMapper {

    /**
     * 删除会员消费记录
     * @param balanceId
     *            删除会员消费记录 {@link java.lang.Long }
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Long balanceId) {

        return this
                .delete("com.ningpai.site.customer.mapper.CustomerConsumeMapper.deleteByPrimaryKey",
                        balanceId);
    }

    /**
     * 添加会员消费记录
     * @param record
     *            {@link com.ningpai.site.customer.bean.CustomerConsume}
     * @return
     */
    @Override
    public int insert(CustomerConsume record) {

        return this
                .insert("com.ningpai.site.customer.mapper.CustomerConsumeMapper.insert",
                        record);
    }

    /**
     * 添加会员消费记录按条件
     * @param record
     *            {@link com.ningpai.site.customer.bean.CustomerConsume}
     * @return
     */
    @Override
    public int insertSelective(CustomerConsume record) {

        return this
                .insert("com.ningpai.site.customer.mapper.CustomerConsumeMapper.insertSelective",
                        record);
    }

    /**
     * 按条件修改会员消费记录
     * @param record
     *            {@link com.ningpai.site.customer.bean.CustomerConsume}
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(CustomerConsume record) {

        return this
                .update("com.ningpai.site.customer.mapper.CustomerConsumeMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 根据会员消费记录编号修改会员等级
     * @param record
     *            会员消费记录 {@link com.ningpai.site.customer.bean.CustomerConsume}
     * @return
     */
    @Override
    public int updateByPrimaryKey(CustomerConsume record) {

        return this.update("", record);
    }

    /**
     * 根据会员消费记录编号获取会员消费记录
     * @param balanceId
     *            会员消费记录编号 {@link java.lang.Long}
     * @return
     */
    @Override
    public CustomerConsume selectByPrimaryKey(Long balanceId) {

        return this
                .selectOne(
                        "com.ningpai.site.customer.mapper.CustomerConsumeMapper.selectByPrimaryKey",
                        balanceId);
    }

    /**
     * 按会员编号和时间标记查询消费记录的条数
     * @param map
     *            查询条件
     * @return
     */
    @Override
    public int queryConsumeByCidCount(Map<String, Object> map) {

        return this
                .selectOne(
                        "com.ningpai.site.customer.mapper.CustomerConsumeMapper.queryConsumeByCidCount",
                        map);
    }

    /**
     * 按会员编号和时间标记查询消费记录的分页数据
     * @param map
     *            查询条件
     * @return
     */
    @Override
    public List<Object> queryAllConsumeByCid(Map<String, Object> map) {

        return this
                .selectList(
                        "com.ningpai.site.customer.mapper.CustomerConsumeMapper.queryAllConsumeByCid",
                        map);
    }

    /**
     * 根据会员编号查询消费总和
     * @param customerId
     *            会员编号
     * @return
     */
    @Override
    public BigDecimal selectTotalNumByCid(Long customerId) {

        return this
                .selectOne(
                        "com.ningpai.site.customer.mapper.CustomerConsumeMapper.selectTotalNumByCid",
                        customerId);
    }

}
