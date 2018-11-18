/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao;

import java.util.List;
import java.util.Map;
import com.ningpai.customer.bean.CustomerPoint;

/**
 * 会员积分记录Mapper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年3月20日 上午11:25:18
 * @version 0.0.1
 */
public interface CustomerPointMapper {
    /***
     * 根据条件查询推荐链接注册积分记录
     * 
     * @return
     */
    List<Object> selectRegisterPont(Map<String, Object> map);

    /***
     * 推广记录总行数
     * 
     * @param point
     * @return
     */
    int selectRegisterPointSize(Map<String, Object> map);

    /**
     * 删除会员积分记录
     * 
     * @param pointId
     *            会员积分记录编号 {@link java.lang.Long }
     * @return int {@link java.lang.Integer }
     */
    int deleteByPrimaryKey(Long pointId);

    /**
     * 添加会员积分记录
     * 
     * @param CustomerPoint
     *            {@link com.ningpai.customer.bean.CustomerPoint}
     * @return int {@link java.lang.Integer }
     */
    int insert(CustomerPoint record);

    /**
     * 添加会员积分记录按条件
     * 
     * @param record
     *            {@link com.ningpai.customer.bean.CustomerPoint}
     * @return int {@link java.lang.Integer }
     */
    int insertSelective(CustomerPoint record);

    /**
     * 根据会员积分记录编号获取会员积分记录
     * 
     * @param pointId
     *            会员积分记录编号 {@link java.lang.Long}
     * @return CustomerPoint {@link com.ningpai.customer.bean.CustomerPoint}
     */
    CustomerPoint selectByPrimaryKey(Long pointId);

    /**
     * 按条件修改会员积分记录
     * 
     * @param record
     *            会员积分记录编号 {@link java.lang.Long}
     * @return int {@link java.lang.Integer }
     */
    int updateByPrimaryKeySelective(CustomerPoint record);

    /**
     * 根据会员积分记录编号修改会员等级
     * 
     * @param record
     *            会员积分记录编号 {@link java.lang.Long}
     * @return int {@link java.lang.Integer }
     */
    int updateByPrimaryKey(CustomerPoint record);

    /**
     * 查询所有会员积分记录
     * 
     * @param paramMap
     *            查询参数 {@link java.util.Map}
     * @return List<Object> {@link java.util.List}
     */
    List<Object> selectAllCustomerPoint(Map<String, Integer> paramMap);

    /**
     * 查询所有会员积分记录总条数
     * 
     * @return int {@link java.lang.Integer }
     */
    int selectAllCustomerCount();

    /**
     * 删除会员积分记录
     * 
     * @param parameterValues
     *            会员积分记录编号
     * @return 0 失败 1 成功
     */
    int deleteCustomerPointByPids(Map<String, Object> paramMap);

    /**
     * 查询按条件查询积分记录的条数
     * 
     * @param point
     *            查询条件
     * @return int {@link java.util.Integer}
     */
    int selectCustmerPointSize(CustomerPoint point);

    /**
     * 查询按条件查询积分记录
     * 
     * @param point
     *            查询条件
     * @return List {@link java.util.List}
     */
    List<Object> selectCustPointByCustPoint(CustomerPoint point);
}
