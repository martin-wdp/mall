/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.service;

import java.util.List;

import com.ningpai.customer.bean.Vocationinfo;

/**
 * 教育服务处理接口
 * 
 * @author jiping
 * @since 2014年7月7日 下午3:48:39
 * @version 0.0.1
 */
public interface VocationinfoServiceMapper {
    /**
     * 根据主键删除职业信息
     * 
     * @param vocationId
     *            职业编号
     * @return int {@link java.util.Integer}
     */
    int deleteByPrimaryKey(Long vocationId);

    /**
     * 插入职业信息
     * 
     * @param 职业bean
     *            com.ningpai.customer.bean.Vocationinfo
     *            {@link com.ningpai.customer.bean.Vocationinfo}
     * @return int {@link java.util.Integer}
     */
    int insert(Vocationinfo vocationinfo);

    /**
     * 根据主键查询职业信息
     * 
     * @param vocationId
     *            纸业编号
     * @return 职业bean com.ningpai.customer.bean.Vocationinfo
     *         {@link com.ningpai.customer.bean.Vocationinfo}
     */
    Vocationinfo selectByPrimaryKey(Long vocationId);

    /**
     * 根据主键修改职业信息
     * 
     * @param 职业bean
     *            com.ningpai.customer.bean.Vocationinfo
     *            {@link com.ningpai.customer.bean.Vocationinfo}
     * @return int {@link java.util.Integer}
     */
    int updateByPrimaryKey(Vocationinfo vocationinfo);

    /**
     * 查询全部职业信息
     * 
     * @return {@link List#}
     */
    List<Vocationinfo> selectAllVocation(long customerId);
}
