/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.customer.bean.Vocationinfo;
import com.ningpai.customer.dao.VocationinfoMapper;
import com.ningpai.customer.service.VocationinfoServiceMapper;

/**
 * 职业服务接口实现类
 * 
 * @author jiping
 * @since 2014年7月7日 下午3:50:48
 * @version 0.0.1
 */
@Service("vocationinfoServiceMapper")
public class VocationinfoServiceMapperImpl implements VocationinfoServiceMapper {
    // spring注解
    private VocationinfoMapper vocationinfoMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.service.VocationinfoServiceMapper#deleteByPrimaryKey
     * (java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long vocationId) {
        return vocationinfoMapper.deleteByPrimaryKey(vocationId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.service.VocationinfoServiceMapper#insert(com.ningpai
     * .customer.bean.Vocationinfo)
     */
    @Override
    public int insert(Vocationinfo vocationinfo) {
        return vocationinfoMapper.insert(vocationinfo);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.service.VocationinfoServiceMapper#selectByPrimaryKey
     * (java.lang.Long)
     */
    @Override
    public Vocationinfo selectByPrimaryKey(Long vocationId) {
        return vocationinfoMapper.selectByPrimaryKey(vocationId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.service.VocationinfoServiceMapper#updateByPrimaryKey
     * (com.ningpai.customer.bean.Vocationinfo)
     */
    @Override
    public int updateByPrimaryKey(Vocationinfo vocationinfo) {
        return vocationinfoMapper.updateByPrimaryKey(vocationinfo);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.service.VocationinfoServiceMapper#selectAllVocation
     * ()
     */
    @Override
    public List<Vocationinfo> selectAllVocation(long customerId) {
        return vocationinfoMapper.selectAllVocation(customerId);
    }

    public VocationinfoMapper getVocationinfoMapper() {
        return vocationinfoMapper;
    }

    @Resource(name = "vocationinfoMapper")
    public void setVocationinfoMapper(VocationinfoMapper vocationinfoMapper) {
        this.vocationinfoMapper = vocationinfoMapper;
    }

}
