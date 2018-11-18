/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.customer.bean.CustomerPointLevel;

/**
 * 会员积分等级
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月19日 下午3:28:37
 * @version 1.0
 */
public interface CustomerPointLevelMapper {
    /**
     * 查询所有会员等级
     * 
     * @see java.util.List {@link java.util.List}
     * @see com.ningpai.customer.bean.CustomerPointLevel
     *      {@link com.ningpai.customer.bean.CustomerPointLevel}
     * @return List<CustomerPointLevel> 会员等级列表
     */
    List<CustomerPointLevel> selectAllPointLevel();

    /**
     * 查询所有会员等级-分页
     * 
     * @see java.util.List {@link java.util.List}
     * @see com.ningpai.customer.bean.CustomerPointLevel
     *      {@link com.ningpai.customer.bean.CustomerPointLevel}
     * @return List<CustomerPointLevel> 会员等级列表
     */
    List<Object> selectPointLevelByLimit(Map<String, Integer> paramMap);

    /**
     * 添加会员等级
     * 
     * @param customerPointLevel
     *            {@link com.ningpai.customer.bean.CustomerPointLevel}
     * @return int {@link java.lang.Integer }
     */
    int insertSelective(CustomerPointLevel customerPointLevel);

    /**
     * 根据会员等级编号获取会员等级
     * 
     * @param pointLelvelId
     *            会员编号 {@link java.lang.Long}
     * @return CustomerPointLevel
     *         {@link com.ningpai.customer.bean.CustomerPointLevel}
     */
    CustomerPointLevel selectByPrimaryKey(Long pointLelvelId);

    /**
     * 根据指定等级名称获取指定会员等级,用于验证该等级是否存在
     * 
     * @param pointLevelName
     *            等级名称 {@link java.lang.String}
     * @return CustomerPointLevel 会员等级
     *         {@link com.ningpai.customer.bean.CustomerPointLevel}
     */
    Long selectByPrimaryName(String pointLevelName);

    /**
     * 根据会员等级编号修改会员等级
     * 
     * @param pointLelvelId
     *            会员编号 {@link java.lang.Long}
     * @return int {@link java.lang.Integer }
     */
    int updateByPrimaryKeySelective(CustomerPointLevel customerPointLevel);

    /**
     * 删除会员等级
     * 
     * @param pointLevelId
     *            会员编号 {@link java.lang.Integer}
     * @return int {@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Long pointLelvelId);

    /**
     * 验证默认等级是否存在
     * 
     * @return 1 存在 0 不存在
     */
    Long selectDefaultPointLevel();

    /**
     * 批量删除会员等级
     * 
     * @param paramMap
     *            会员等级编号
     * @return Integer {@link java.util.Integer}
     */
    Integer deletePointLevelByIds(Map<String, Object> paramMap);

    /**
     * 查询所有条数
     * 
     * @return
     */
    Integer selectAllCount();

    /**
     * 取消以前的默认等级设置
     * 
     * @return 0成功 1不成功 {@link Integer}
     */
    int cancelBeforeDefault();

    /**
     * 查找默认等级
     * 
     * @return 会员等级 {@link CustomerPointLevel}
     */
    CustomerPointLevel selectDefaultCustomerLevel();

    /**
     * 根据会员id查询会员等级
     * 
     * @param customerId
     *            会员等级
     * @return 会员的折扣
     */
    CustomerPointLevel selectCustomerLevelById(Long customerId);

    /**
     * 查询所有设置为默认等级的行数
     *
     * @return int
     * */
    int queryIsDefaultCount();

}
