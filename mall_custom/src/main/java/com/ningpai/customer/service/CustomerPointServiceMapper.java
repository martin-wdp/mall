/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.service;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerPoint;
import com.ningpai.customer.bean.RegisterPoint;
import com.ningpai.other.bean.IntegralSet;
import com.ningpai.util.PageBean;

/**
 * 会员积分接口
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年3月20日 上午11:20:22
 * @version 0.0.1
 */
public interface CustomerPointServiceMapper {

    /**
     * 根据条件查询会员推广积分记录
     * 
     * @param point
     *            积分条件
     * @param pageBean
     *            分页PageBean
     * @return pageBean {@link com.ningpai.util.PageBean}
     */
    PageBean queryregisterpoint(RegisterPoint point, PageBean pageBean);

    /**
     * 不同状态赠送积分的数量
     * 
     * @return
     */
    IntegralSet findPointSet();

    /***
     * 保存推荐出注册信息
     * 
     * @param point
     * @return
     */
    int insertRegisterPoint(RegisterPoint point);

    /***
     * 根据会员ID查询会员对象
     * 
     * @param cusId
     * @return
     */
    Customer selectCusById(Long cusId);

    /**
     * 查询所有会员积分记录
     * 
     *            查询参数 {@link java.util.Map}
     * @return PageBean {@link com.ningpai.util.PageBean}
     */
    PageBean selectAllCustomerPoint(PageBean pageBean);

    /**
     * 删除会员积分记录
     * 
     * @param parameterValues
     *            会员积分记录编号
     * @return 0 失败 1 成功
     */
    int deleteCustomerPoint(String[] parameterValues);

    /**
     * 按条件查找积分记录
     * 
     * @param point
     *            积分条件
     * @param pageBean
     *            分页PageBean
     * @return pageBean {@link com.ningpai.util.PageBean}
     */
    PageBean selectCustPointByCustPoint(CustomerPoint point, PageBean pageBean);

    /**
     * 根据类型添加积分
     * 
     *            会员编号
     * @param type
     *            0注册 1登录 2邮箱验证 3手机验证 4发表评论 5推荐用户
     * @return
     */
    int addIntegralByType(Long customerId, String type);

    /**
     * 添加或扣除会员积分
     * @param customerId
     * @param customerpoint
     * @return
     */
    int saveCustomerPoint(Long customerId, CustomerPoint customerpoint);

    /**
     * 根据类型添加积分
     * 
     *            会员编号
     * @param type
     *            0注册 1登录 2邮箱验证 3手机验证 4发表评论 5推荐用户 6 消费增加积分
     * @param orderPrice
     *            订单支付金额
     * @return
     */
    int addIntegralByType(Long customerId, String type, Double orderPrice);

    /**
     * 修改注册送积分
     * 
     * @param psetLogin
     * @return
     */
    int updateIntegralById(Integer psetLogin);

}
