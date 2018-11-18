/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.mapper.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.customer.bean.OrderComplain;
import com.ningpai.site.customer.mapper.OrderComplainMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @see com.ningpai.site.customer.mapper.OrderComplainMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年4月22日 下午3:16:47
 * @version 0.0.1
 */
@Repository("orderComplainMapper")
public class OrderComplainMapperImpl extends BasicSqlSupport implements
        OrderComplainMapper {
    /**
     * 根据申诉Id删除
     * @param complainId
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Long complainId) {
        return 0;
    }

    /**
     * 新增申诉
     * @param record
     * @return
     */
    @Override
    public int insert(OrderComplain record) {
        return 0;
    }

    /**
     * 新增申诉
     * @param record
     * @return
     */
    @Override
    public int insertSelective(OrderComplain record) {
        return this
                .insert("com.ningpai.site.customer.dao.OrderComplainMapper.insertSelective",
                        record);
    }

    /**
     * 根据申诉Id查询
     * @param complainId
     * @return
     */
    @Override
    public OrderComplain selectByPrimaryKey(Long complainId) {
        return null;
    }

    /**
     * 修改订单申诉信息
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(OrderComplain record) {
        return 0;
    }

    /**
     * 修改订单申诉信息
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKey(OrderComplain record) {
        return 0;
    }

    /**
     * 查询投诉记录条数
     * @param paramMap
     * @return
     */
    @Override
    public Long searchComplainCount(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.OrderComplainMapper.searchComplainCount",
                        paramMap);
    }

    /**
     * 查询投诉记录列表
     * @param paramMap
     * @return
     */
    @Override
    public List<Object> selectComplainList(Map<String, Object> paramMap) {
        return this
                .selectList(
                        "com.ningpai.site.customer.dao.OrderComplainMapper.selectComplainList",
                        paramMap);
    }

}
