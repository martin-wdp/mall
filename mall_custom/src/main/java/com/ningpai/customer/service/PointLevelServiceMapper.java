/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.service;

import java.util.List;

import com.ningpai.customer.bean.CustomerPointLevel;
import com.ningpai.util.PageBean;

/**
 * 会员等级功能接口
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月19日 下午3:43:10
 * @version 0.0.1
 */
public interface PointLevelServiceMapper {
    /**
     * 查询所有会员等级 用于等级页面展示
     * 
     * @see java.util.List {@link java.util.List}
     * @see com.ningpai.customer.bean.CustomerPointLevel
     *      {@link com.ningpai.customer.bean.CustomerPointLevel}
     * @return PageBean
     */
    PageBean selectAllPointLevel(PageBean pageBean);

    /**
     * 查询所有会员等级 用于添加会员时填充会员等级下拉列表
     * 
     * @see java.util.List {@link java.util.List}
     * @see com.ningpai.customer.bean.CustomerPointLevel
     *      {@link com.ningpai.customer.bean.CustomerPointLevel}
     * @return List<CustomerPointLevel> 会员等级列表
     */
    List<CustomerPointLevel> selectAllPointLevel();

    /**
     * 添加会员等级
     * 
     * @param customerPointLevel
     *            {@link com.ningpai.customer.bean.CustomerPointLevel}
     * @return Integer {@link java.lang.Integer }
     */
    int addPointLevel(CustomerPointLevel customerPointLevel);

    /**
     * 根据指定编号获取指定会员等级
     * 
     * @param pointLevelId
     *            等级编号 {@link java.lang.Long}
     * @return CustomerPointLevel 会员等级
     *         {@link com.ningpai.customer.bean.CustomerPointLevel}
     */
    CustomerPointLevel selectPointLevelById(Long pointLevelId);

    /**
     * 修改会员等级
     * 
     * @param CustomerPointLevel
     *            会员等级 {@link com.ningpai.customer.bean.CustomerPointLevel}
     * @return int {@link java.lang.Integer}
     */
    int updatePointLevel(CustomerPointLevel customerPointLevel);

    /**
     * 删除会员等级
     * 
     * @param String
     *            [] pointLevelId 会员编号
     * @see {@link java.lang.String}
     * @see {@link java.lang.Integer}
     * @return int {@link java.lang.Integer}
     */
    int deletePointLevel(String[] pointLevelId);

    /**
     * 根据指定等级名称获取指定会员等级,用于验证该等级是否存在
     * 
     * @param pointLevelName
     *            等级名称 {@link java.lang.String}
     * @param Long
     *            {@link java.lang.Long}
     * @return 1 存在 0 不存在
     */
    Long selectPointLevelByName(String pointLevelName);

    /**
     * 验证默认等级是否存在
     * 
     * @return 1 存在 0 不存在
     */
    Long selectDefaultPointLevel();

    /**
     * 取消以前的默认等级设置
     * 
     * @return 0成功 1不成功 {@link Integer}
     */
    int cancelBeforeDefault();
}
