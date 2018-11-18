/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.system.bean.ProvinceDefault;
import com.ningpai.system.dao.ProvinceDefaultMapper;
import com.ningpai.system.service.DefaultAddressService;

/**
 * 默认地址service
 * 
 * @author NINGPAI-LIH
 * @since 2015年1月10日13:13:11
 *
 */
@Service("DefaultAddressService")
public class DefaultAddressServiceImpl implements DefaultAddressService {

    /**
     * spring注解
     */
    @Resource(name = "ProvinceDefaultMapper")
    private ProvinceDefaultMapper mapper;

    /*
     * 插入默认地址
     * 
     * @see
     * com.ningpai.system.service.DefaultAddressService#insertAddressDefaultService
     * (com.ningpai.system.bean.ProvinceDefault)
     */
    @Override
    @Transactional
    public int insertAddressDefaultService(Long districtId) {
        ProvinceDefault provinceDefault = new ProvinceDefault();
        provinceDefault.setDistrictId(districtId);
        provinceDefault.setCreateTime(new Date());
        provinceDefault.setDeFalg("0");
        // 清空默认地址
        mapper.deleteAllDefault();
        return mapper.insertSelective(provinceDefault);
    }

    /*
     * 获取默认地址id
     * 
     * @see
     * com.ningpai.system.service.DefaultAddressService#getDefaultIdService()
     */
    @Override
    public Long getDefaultIdService() {
        return mapper.selectDefaultId();
    }
}
