/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.mapper;

import java.util.List;
import java.util.Map;

import com.ningpai.m.customer.bean.Customer;
import com.ningpai.m.customer.vo.CustomerAllInfo;

/**
 * 会员Mapper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年1月13日 下午1:07:58
 * @version 0.0.1
 */
public interface CustomerMapper {

    /**
     * 按照主键编号查找 基本信息
     * 
     * @param customerId
     *            会员主键编号
     * @return CustomerAllInfo {@link com.ningpai.site.customer.bean.CustomerAllInfo}
     */
    CustomerAllInfo selectByPrimaryKey(Long customerId);

    /**
     * 按条件修改会员信息
     * 
     * @param record
     *            要修改的会员对象
     * @return 0失败 1成功
     */
    int updateByPrimaryKeySelective(Customer record);

    /**
     * 检查用户是否存在
     * 
     * @param paramMap
     *            参数Map {@link Map}
     * @return 0 不存在 1 存在 {@link Long}
     */
    Long checkExistsByCustNameAndType(Map<String, Object> paramMap);

    /**
     * 根据会员名和密码验证用户
     * 
     * @param paramMap
     *            验证条件
     * @return null 密码错误 Customer 验证成功
     */
    Customer selectCustomerByNamePwdAndType(Map<String, Object> paramMap);

    /**
     * 查询验证码和失效时间
     * 
     * @param customerId
     *            会员编号
     * @return
     */
    Customer selectCaptcha(Long customerId);

    /**
     * 修改验证码
     * 
     * @param customer
     * @return
     */
    int updateSmsCaptcha(Customer customer);

    /**
     * 修改用户密码
     * 
     * @param allInfo
     *            包含手机用户信息 新密码
     * @return 0 修改失败 1 修改成功
     */
    int updateCusomerPwd(CustomerAllInfo allInfo);

    /**
     * 修改用户信息
     * 
     * @param allInfo
     *            包含手机用户信息
     * @return 0 修改失败 1 修改成功
     */
    int updateCustomerInfo(CustomerAllInfo allInfo);

    
    /**
     * 查询积分记录总数
     * 
     * @param paramMap
     *            查询条件
     * @return 记录总数
     */
    Long queryPointMCount(Map<String, Object> paramMap);

    /**
     * 查询所有积分记录
     * 
     * @param paramMap
     *            查询条件
     * @return 记录
     */
    List<Object> queryAllPointMList(Map<String, Object> paramMap);

}
