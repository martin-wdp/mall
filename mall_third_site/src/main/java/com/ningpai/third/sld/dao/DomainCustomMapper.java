/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.sld.dao;

import java.util.List;

import com.ningpai.third.sld.bean.DomainCustom;

/**
 * 二级域名关联Mapper
 * @author NINGPAI-zhangqiang 
 * @since 2014年10月20日 上午11:20:10 
 * @version 0.0.1
 */
public interface DomainCustomMapper {
    /**
     * 根据主键删除
     * @param dmCuId
     * @return
     */
    int deleteByPrimaryKey(Long dmCuId);

    /**
     * 自定义的域
     * @param record
     * @return
     */
    int insertSelective(DomainCustom record);

    /**
     *  根据ID获取自定义的域名
     * @param dmCuId
     * @return
     */
    DomainCustom selectByPrimaryKey(Long dmCuId);

    /**
     * 根据id修改二级域名关联
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(DomainCustom record);

    /**
     * 查询所有的二级域名用户设置
     * @return List<DomainCustom> {@link List}
     */
    List<DomainCustom> findAll();
    
    /**
     * 根据第三方id查询二级域名用户设置
     * @param customerId
     * @return
     */
    DomainCustom findByCustId(Long customerId);
    
    /**
     * 检查是否存在域名名称
     * @param domain
     * @return
     */
    Long queryByDomain(String domain);
}
