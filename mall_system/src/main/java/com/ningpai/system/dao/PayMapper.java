/*
 * Copyright 2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.Pay;
import com.ningpai.system.util.SelectBean;

/**
 * 支付方式接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月18日 下午7:56:54
 * @version 1.0
 */
public interface PayMapper {

    /**
     * 删除支付方式
     * 
     * @param payId
     * @return
     */
    int deleteByPrimaryKey(Long payId);

    /**
     * 添加支付方式信息
     * 
     * @param record
     * @return int
     */
    int insert(Pay record);

    /**
     * 添加支付方式信息
     * 
     * @param record
     * @return int
     */
    int insertSelective(Pay record);

    /**
     * 查询单个支付方式信息
     * 
     * @param record
     * @return int
     */
    Pay selectByPrimaryKey(Long payId);

    /**
     * 修改支付方式信息
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Pay record);

    /**
     * 修改支付方式信息
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Pay record);

    /**
     * 查询支付总行数
     * 
     * @return int
     */
    int findTotalCount(SelectBean selectBean);

    /**
     * 分页查询支付信息
     * 
     * @param map
     * @return List
     */
    List<Object> findByPageBean(Map<String, Object> map);

    /**
     * 根据默认方式查找信息
     * 
     * @param string
     * @return Pay
     */
    Pay selectByDefault(String payDefault);

    /**
     * 根据id修改默认
     * 
     * @param string
     * @return Pay
     */
    int changeDefaultToNO(Long payId);

    /**
     * 根据支付方式（微信支付）查询总记录数
     * 
     * @param selectBean
     * @return
     */
    int findTotalCountByPayType(SelectBean selectBean);

    /**
     * 根据支付类型查询为微信支付的接口
     * 
     * @param map
     * @return
     */
    List<Object> findPayByPayType(Map<String, Object> map);
    
    /**
     * 修改支付问题描述信息
     * @param pay 支付对象
     * @return int
     */
    int upadtePayHelp(Pay pay);
}
