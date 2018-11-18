/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.customer.bean.CustomerConsume;

/**
 * 会员充值消费Mapper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年3月21日 下午3:00:32
 * @version 0.0.1
 */
public interface CustomerConsumeMapper {
    /**
     * 删除会员消费记录
     * 
     * @param balanceId
     *            删除会员消费记录 {@link java.lang.Long }
     * @return int {@link java.lang.Integer }
     */
    int deleteByPrimaryKey(Long balanceId);

    /**
     * 添加会员消费记录
     * 
     * @param CustomerConsume
     *            {@link com.ningpai.customer.bean.CustomerConsume}
     * @return int {@link java.lang.Integer }
     */
    int insert(CustomerConsume record);

    /**
     * 添加会员消费记录按条件
     * 
     * @param CustomerConsume
     *            {@link com.ningpai.customer.bean.CustomerConsume}
     * @return int {@link java.lang.Integer }
     */
    int insertSelective(CustomerConsume record);

    /**
     * 根据会员消费记录编号获取会员消费记录
     * 
     * @param balanceId
     *            会员消费记录编号 {@link java.lang.Long}
     * @return CustomerConsume {@link com.ningpai.customer.bean.CustomerConsume}
     */
    CustomerConsume selectByPrimaryKey(Long balanceId);

    /**
     * 按条件修改会员消费记录
     * 
     * @param CustomerConsume
     *            {@link com.ningpai.customer.bean.CustomerConsume}
     * @return int {@link java.lang.Integer }
     */
    int updateByPrimaryKeySelective(CustomerConsume record);

    /**
     * 根据会员消费记录编号修改会员等级
     * 
     * @param CustomerConsume
     *            会员消费记录 {@link com.ningpai.customer.bean.CustomerConsume}
     * @return int {@link java.lang.Integer }
     */
    int updateByPrimaryKey(CustomerConsume record);

    /**
     * 查询按条件查询消费记录的条数
     * 
     * @param consume
     *            查询条件
     * @return int {@link java.util.Integer}
     */
    int selectCustmerConsumeSize(CustomerConsume consume);

    /**
     * 查询按条件查询消费记录
     * 
     * @param paramMap
     *            查询条件
     * @return List {@link java.util.List}
     */
    List<Object> selectCustConsumeByParamMap(Map<String, Object> paramMap);

    /**
     * 删除会员消费记录
     * 
     * @param parameterValues
     *            会员消费记录编号
     * @return 0 失败 1 成功
     */
    int deleteCustomerConsumeByBids(Map<String, Object> paramMap);
}
