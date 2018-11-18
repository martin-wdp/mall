/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.service;

import javax.servlet.http.HttpServletRequest;

import com.ningpai.customer.bean.Customer;
import com.ningpai.m.customer.vo.CustomerAllInfo;

import java.util.Map;

/**
 * 手机端会员Service
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年8月20日 上午10:48:15
 * @version 0.0.1
 */
public interface CustomerService {
    /**
     * 查询会员基本信息
     * 
     * @param customerId
     *            会员编号 {@link Long}
     * @return CustomerAllInfo {@link com.ningpai.site.customer.vo.CustomerAllInfo}
     */
    CustomerAllInfo selectByPrimaryKey(Long customerId);

    /**
     * 发送手机验证码
     * 
     * @param request
     * @param moblie
     *            目标手机
     * @return 1 失败 0网络连接超时 -1没过90秒
     */
    int sendPost(HttpServletRequest request, String moblie,String type);


    /**
     * 验证手机验证码
     * 
     * @param request
     * @return 0失败 1成功 -1失效
     */
    int getMCode(HttpServletRequest request, String code);
    /**
     * 验证手机验证码
     *
     * @param request
     * @return 0失败 1成功 -1失效
     */
    int getMCode(HttpServletRequest request, String code,String mobile);

    /**
     * 修改密码
     * 
     * @param request
     * @param userKey
     *            手机用户新密码
     * @return 0 修改失败 1修改成功
     */
    int updateCusomerPwd(HttpServletRequest request, String userKey);
    /**
     * 重置密码
     *
     * @param request
     * @param userKey
     *            手机用户新密码
     * @return 0 修改失败 1修改成功
     */
    int updateCusomerPwd(HttpServletRequest request, String userKey,String userName);
    /**
     * 修改个人信息
     *
     * @param record
     *            手机用户新密码
     * @return 0 修改失败 1修改成功
     */
    int updateCustomerAjax( Customer record );

    /**
     * 按条件修改会员sex
     *
     * @param record
     *            要修改的会员对象
     * @return 0失败 1成功
     */
    int updateCustomerSex(Map<String,Object> record);
}
