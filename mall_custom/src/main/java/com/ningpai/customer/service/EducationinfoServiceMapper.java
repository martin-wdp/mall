/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.service;

import java.util.List;

import com.ningpai.customer.bean.Educationinfo;

/**
 * 教育服务处理接口
 * 
 * @author jiping
 * @since 2014年7月7日 下午3:55:35
 * @version 0.0.1
 */
public interface EducationinfoServiceMapper {
    /**
     * 根据主键删除教育信息
     * 
     * @param educationId
     *            教育编号
     * @return int {@link java.util.Integer}
     */
    int deleteByPrimaryKey(Long educationId);

    /**
     * 插入教育信息
     * 
     * @param 教育bean
     *            com.ningpai.customer.bean.Educationinfo
     *            {@link com.ningpai.customer.bean.Educationinfo}
     * @return int {@link java.util.Integer}
     */
    int insert(Educationinfo educationinfo);

    /**
     * 根据主键查询教育信息
     * 
     * @param educationId
     *            教育编号
     * @return 教育信息 com.ningpai.customer.bean.Educationinfo
     *         {@link com.ningpai.customer.bean.Educationinfo}
     */
    Educationinfo selectByPrimaryKey(Long educationId);

    /**
     * 根据主键修改教育信息
     * 
     * @param 教育bean
     *            com.ningpai.customer.bean.Educationinfo
     *            {@link com.ningpai.customer.bean.Educationinfo}
     * @return int {@link java.util.Integer}
     */
    int updateByPrimaryKey(Educationinfo educationinfo);

    /**
     * 查询全部教育信息
     * 
     * @return {@link List#}
     */
    List<Educationinfo> selectAll(long customerId);

}
