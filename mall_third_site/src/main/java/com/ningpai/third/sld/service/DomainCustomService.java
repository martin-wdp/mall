/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.sld.service;

import java.util.List;

import com.ningpai.third.sld.bean.DomainCustom;

/**
 * 二级域名关联service
 * @author jiping 
 * @since 2014年12月4日 上午9:27:23 
 * @version 0.0.1
 */
public interface DomainCustomService {
    /**
     * 根据第三方id查询二级域名
     * @param customerId
     * @return
     */
    DomainCustom findDomainCustom(Long customerId);
    
    /**
     * 修改二级域名
     * @param domainCustom
     * @return
     */
    int updateDomain(DomainCustom domainCustom);
    
    /**
     * 根据id查询二级域名
     * @param dmCuId
     * @return
     */
    DomainCustom queryDomainByID(Long dmCuId);
    
    /**
     * 检查是否存在二级域名
     * @param domain
     * @return
     */
    Long queryByDomain(String domain);
    
    /**
     * 查询全部域名
     */
    List<DomainCustom> findAll();
}
