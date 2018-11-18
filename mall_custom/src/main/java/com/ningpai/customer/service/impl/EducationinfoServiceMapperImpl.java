/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.customer.bean.Educationinfo;
import com.ningpai.customer.dao.EducationinfoMapper;
import com.ningpai.customer.service.EducationinfoServiceMapper;

/**
 * 教育服务处理接口实现类
 * 
 * @author jiping
 * @since 2014年7月7日 下午3:57:32
 * @version 0.0.1
 */
@Service("educationinfoServiceMapper")
public class EducationinfoServiceMapperImpl implements
        EducationinfoServiceMapper {
    // spring注解
    private EducationinfoMapper educationinfoMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.service.EducationinfoServiceMapper#deleteByPrimaryKey
     * (java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long educationId) {
        return educationinfoMapper.deleteByPrimaryKey(educationId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.service.EducationinfoServiceMapper#insert(com.ningpai
     * .customer.bean.Educationinfo)
     */
    @Override
    public int insert(Educationinfo educationinfo) {
        return educationinfoMapper.insert(educationinfo);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.service.EducationinfoServiceMapper#selectByPrimaryKey
     * (java.lang.Long)
     */
    @Override
    public Educationinfo selectByPrimaryKey(Long educationId) {
        return educationinfoMapper.selectByPrimaryKey(educationId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.service.EducationinfoServiceMapper#updateByPrimaryKey
     * (com.ningpai.customer.bean.Educationinfo)
     */
    @Override
    public int updateByPrimaryKey(Educationinfo educationinfo) {
        return educationinfoMapper.updateByPrimaryKey(educationinfo);
    }

    /*
     * 
     * 
     * @see com.ningpai.customer.service.EducationinfoServiceMapper#selectAll()
     */
    @Override
    public List<Educationinfo> selectAll(long customerId) {
        return educationinfoMapper.selectAll(customerId);
    }

    public EducationinfoMapper getEducationinfoMapper() {
        return educationinfoMapper;
    }

    @Resource(name = "educationinfoMapper")
    public void setEducationinfoMapper(EducationinfoMapper educationinfoMapper) {
        this.educationinfoMapper = educationinfoMapper;
    }

}
