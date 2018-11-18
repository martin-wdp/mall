/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.customer.bean.Educationinfo;
import com.ningpai.customer.dao.EducationinfoMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 教育接口实现类
 * 
 * @author jiping
 * @since 2014年7月7日 下午3:29:22
 * @version 0.0.1
 */
@Repository("educationinfoMapper")
public class EducationinfoMapperImpl extends BasicSqlSupport implements
        EducationinfoMapper {
    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.EducationinfoMapper#deleteByPrimaryKey(java.
     * lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long educationId) {
        return this
                .delete("com.ningpai.customer.mapper.EducationinfoMapper.deleteByPrimaryKey",
                        educationId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.EducationinfoMapper#insert(com.ningpai.customer
     * .bean.Educationinfo)
     */
    @Override
    public int insert(Educationinfo educationinfo) {
        return this
                .insert("com.ningpai.customer.mapper.EducationinfoMapper.insertSelective",
                        educationinfo);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.EducationinfoMapper#selectByPrimaryKey(java.
     * lang.Long)
     */
    @Override
    public Educationinfo selectByPrimaryKey(Long educationId) {
        return this
                .selectOne(
                        "com.ningpai.customer.mapper.EducationinfoMapper.selectByPrimaryKey",
                        educationId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.EducationinfoMapper#updateByPrimaryKey(com.ningpai
     * .customer.bean.Educationinfo)
     */
    @Override
    public int updateByPrimaryKey(Educationinfo educationinfo) {
        return this
                .update("com.ningpai.customer.mapper.EducationinfoMapper.updateByPrimaryKeySelective",
                        educationinfo);
    }

    /*
     * 
     * 
     * @see com.ningpai.customer.dao.EducationinfoMapper#selectAll()
     */
    @Override
    public List<Educationinfo> selectAll(long customerId) {
        return this.selectList(
                "com.ningpai.customer.mapper.EducationinfoMapper.selectAll",
                customerId);
    }

}
