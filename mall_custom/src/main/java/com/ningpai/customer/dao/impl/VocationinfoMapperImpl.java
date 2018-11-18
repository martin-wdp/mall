/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.customer.bean.Vocationinfo;
import com.ningpai.customer.dao.VocationinfoMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 职业接口实现类
 * 
 * @author jiping
 * @since 2014年7月7日 下午3:41:19
 * @version 0.0.1
 */
@Repository("vocationinfoMapper")
public class VocationinfoMapperImpl extends BasicSqlSupport implements
        VocationinfoMapper {

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.VocationinfoMapper#deleteByPrimaryKey(java.lang
     * .Long)
     */
    @Override
    public int deleteByPrimaryKey(Long vocationId) {
        return this
                .delete("com.ningpai.customer.mapper.VocationinfoMapper.deleteByPrimaryKey",
                        vocationId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.VocationinfoMapper#insert(com.ningpai.customer
     * .bean.Vocationinfo)
     */
    @Override
    public int insert(Vocationinfo vocationinfo) {
        return this
                .insert("com.ningpai.customer.mapper.VocationinfoMapper.insertSelective",
                        vocationinfo);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.VocationinfoMapper#selectByPrimaryKey(java.lang
     * .Long)
     */
    @Override
    public Vocationinfo selectByPrimaryKey(Long vocationId) {
        return this
                .selectOne(
                        "com.ningpai.customer.mapper.VocationinfoMapper.selectByPrimaryKey",
                        vocationId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.VocationinfoMapper#updateByPrimaryKey(com.ningpai
     * .customer.bean.Vocationinfo)
     */
    @Override
    public int updateByPrimaryKey(Vocationinfo vocationinfo) {
        return this
                .update("com.ningpai.customer.mapper.VocationinfoMapper.updateByPrimaryKeySelective",
                        vocationinfo);
    }

    /*
     * 
     * 
     * @see com.ningpai.customer.dao.VocationinfoMapper#selectAllVocation()
     */
    @Override
    public List<Vocationinfo> selectAllVocation(long customerId) {
        return this.selectList(
                "com.ningpai.customer.mapper.VocationinfoMapper.selectAll",
                customerId);
    }

}
