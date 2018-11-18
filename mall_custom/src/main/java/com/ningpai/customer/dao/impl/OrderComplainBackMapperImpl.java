/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.customer.bean.ComplainVo;
import com.ningpai.customer.bean.OrderComplainBack;
import com.ningpai.customer.dao.OrderComplainBackMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * DAO-投诉接口实现类
 * 
 * @author jiping
 * @since 2014年7月22日 下午5:09:09
 * @version 0.0.1
 */
@Repository("OrderComplainBackMapper")
public class OrderComplainBackMapperImpl extends BasicSqlSupport implements
        OrderComplainBackMapper {
    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.OrderComplainBackMapper#selectByPrimaryKey(java
     * .lang.Long)
     */
    @Override
    public OrderComplainBack selectByPrimaryKey(Long complainId) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.OrderComplainBackMapper.selectByPrimaryKey",
                        complainId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.OrderComplainBackMapper#searchComplainCount(
     * com.ningpai.customer.bean.OrderComplainBack)
     */
    @Override
    public Long searchComplainCount(ComplainVo orderCB) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.OrderComplainBackMapper.searchComplainCount",
                        orderCB);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.OrderComplainBackMapper#selectComplainList(java
     * .util.Map)
     */
    @Override
    public List<Object> selectComplainList(Map<String, Object> paramMap) {
        return this
                .selectList(
                        "com.ningpai.customer.dao.OrderComplainBackMapper.selectComplainList",
                        paramMap);
    }

    @Override
    public int replayCom(Map<String, Object> paramMap) {
        return this.update(
                "com.ningpai.customer.dao.OrderComplainBackMapper.replayCom",
                paramMap);
    }

    @Override
    public Long searchComplainHadCount(ComplainVo orderCB) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.OrderComplainBackMapper.searchComplainhadCount",
                        orderCB);
    }

    @Override
    public List<Object> selectComplainHadList(Map<String, Object> paramMap) {
        return this
                .selectList(
                        "com.ningpai.customer.dao.OrderComplainBackMapper.selectComplainhadList",
                        paramMap);
    }

}
