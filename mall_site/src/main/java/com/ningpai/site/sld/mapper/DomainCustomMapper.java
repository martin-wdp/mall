/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.sld.mapper;

import com.ningpai.site.sld.bean.DomainCustom;

import java.util.List;

/**
 * 二级域名关联Mapper
 * 
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
     * 插入信息
     * @param record
     * @return
     */
    int insert(DomainCustom record);

    /**
     * 插入信息
     * @param record
     * @return
     */
    int insertSelective(DomainCustom record);

    /**
     * 根据主键查询
     * @param dmCuId
     * @return
     */
    DomainCustom selectByPrimaryKey(Long dmCuId);

    /**
     * 根据主键修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(DomainCustom record);

    /**
     * 根据主键修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(DomainCustom record);

    /**
     * 查询所有的二级域名用户设置
     * 
     * @return List<DomainCustom> {@link List}
     */
    List<DomainCustom> findAll();
}
