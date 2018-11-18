/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.Pay;
import com.ningpai.system.dao.PayMapper;
import com.ningpai.system.util.SelectBean;

/**
 * 支付方式接口层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月18日 下午8:01:43
 * @version 1.0
 */
@Repository("payMapper")
public class PayMapperImpl extends BasicSqlSupport implements PayMapper {

    /**
     * 删除支付方式
     */
    public int deleteByPrimaryKey(Long payId) {
        return this.delete(
                "com.ningpai.system.dao.PayMapper.deleteByPrimaryKey", payId);
    }

    /**
     * 添加支付方式信息
     * 
     * @param record
     * @return int
     */
    public int insert(Pay record) {
        return 0;
    }

    /**
     * 添加支付方式信息
     * 
     * @param record
     * @return int
     */
    public int insertSelective(Pay record) {
        // 添加支付方式
        return this.insert("com.ningpai.system.dao.PayMapper.insertSelective",
                record);
    }

    /**
     * 查询单个支付方式信息
     * 
     * @param record
     * @return int
     */
    public Pay selectByPrimaryKey(Long payId) {
        return this.selectOne(
                "com.ningpai.system.dao.PayMapper.selectByPrimaryKey", payId);
    }

    /**
     * 修改支付方式信息
     * 
     * @param record
     * @return int
     */
    public int updateByPrimaryKeySelective(Pay record) {
        return this.update(
                "com.ningpai.system.dao.PayMapper.updateByPrimaryKeySelective",
                record);
    }

    /**
     * 修改支付方式信息
     * 
     * @param record
     * @return int
     */
    public int updateByPrimaryKey(Pay record) {
        return 0;
    }

    /**
     * 查询支付总行数
     * 
     * @return int
     */
    public int findTotalCount(SelectBean selectBean) {
        // 查询总行数
        return this.selectOne(
                "com.ningpai.system.dao.PayMapper.findTotalCount", selectBean);
    }

    /**
     * 分页查询支付信息
     * 
     * @param map
     * @return List
     */
    public List<Object> findByPageBean(Map<String, Object> map) {
        // 查询分页列表
        return this.selectList(
                "com.ningpai.system.dao.PayMapper.findByPageBean", map);
    }

    /**
     * 根据默认方式查找信息
     * 
     * @param string
     * @return Pay
     */
    public Pay selectByDefault(String payDefault) {

        return this.selectOne(
                "com.ningpai.system.dao.PayMapper.selectByDefault", payDefault);
    }

    /**
     * 根据id修改默认
     * 
     * @see com.ningpai.system.dao.PayMapper#changeDefaultToNO(java.lang.Long)
     */
    @Override
    public int changeDefaultToNO(Long payId) {
        return this.update(
                "com.ningpai.system.dao.PayMapper.changeDefaultToNO", payId);
    }

    /**
     * 根据支付方式（微信支付）查询总记录数
     * 
     * @param selectBean
     * @return
     */
    @Override
    public int findTotalCountByPayType(SelectBean selectBean) {

        return this.selectOne(
                "com.ningpai.system.dao.PayMapper.findTotalCountByPayType",
                selectBean);
    }

    /**
     * 根据支付类型查询为微信支付的接口
     * 
     * @param map
     * @return
     */
    @Override
    public List<Object> findPayByPayType(Map<String, Object> map) {

        return this.selectList(
                "com.ningpai.system.dao.PayMapper.findPayByPayType", map);
    }

    /**
     * 
     * @param map
     * @return 
     */
    @Override
    public int upadtePayHelp(Pay pay) {
        return this.update("com.ningpai.system.dao.PayMapper.updatepayhelp", pay);
    }

}
