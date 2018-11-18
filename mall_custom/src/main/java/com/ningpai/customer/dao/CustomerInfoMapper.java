/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao;

import java.util.Map;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerInfo;
import com.ningpai.customer.bean.RegisterPoint;
import com.ningpai.other.bean.CustomerAllInfo;

/**
 * 会员详细信息接口
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月18日 下午6:49:14
 * @version
 */
public interface CustomerInfoMapper {
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
     * 
     * 修改会员详细信息
     * 
     * @see {@link com.ningpai.customer.bean.CustomerInfo}
     * @see {@link java.lang.Integer}
     * @param customerInfo
     *            会员详细信息 {@link com.ningpai.customer.bean.CustomerInfo}
     * @return Integer {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(CustomerAllInfo customerInfo);

    /**
     * 查询会员详细信息
     * 
     * @param customerId
     * @return
     */
    CustomerInfo selectCustInfoById(Long customerId);

    /**
     * 根据主键查询会员详细信息
     * @param customerId
     * @return
     */
    CustomerInfo selectByPrimaryKey(Long customerId);

    /**
     * 根据会员id查询详细信息
     * @param customerId
     * @return
     */
    CustomerInfo selectByCustomerId(Long customerId);
    /**
     * 根据会员手机号查询详细信息
     * @param infoMobile
     * @return
     * */
    CustomerInfo selectByMobile(String infoMobile);
     /**
     * 修改会员信息 --积分
     * 
     * @param info
     * @return
     */
    int updateInfoByCustId(CustomerInfo info);

    /**
     * 查询邮箱
     * 
     * @return
     */
    CustomerInfo email(Long customerId);

    /**
     * 查询手机
     * 
     * @return
     */
    CustomerInfo mobile(Long customerId);

    // int updateCusSumPoint(Map<String, Object> paraMap);

    /**
     * 手动更新会员等级
     * 
     * @return
     */
    int upCusLevel(Map<String, Object> paraMap);

    int insertSelective(CustomerAllInfo customerAllInfo);

}
