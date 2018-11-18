/*
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.Payment;
import com.ningpai.system.dao.PaymentMapper;

/**
 * @ClassName: PaymentMapperImpl
 * @Description: DAO实现类-支付方式
 * @author Wanghy
 * @date 2014年10月11日 下午3:03:52
 */
@Repository("PaymentMapper")
public class PaymentMapperImpl extends BasicSqlSupport implements PaymentMapper {
    /**
     * @Title: deleteByPrimaryKey
     * @Description: 根据ID删除
     * @param paymentId
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Long paymentId) {

        return this.delete(
                "com.ningpai.system.dao.PaymentMapper.deleteByPrimaryKey",
                paymentId);
    }

    /**
     * @Title: insert
     * @Description: 添加
     * @param record
     * @return
     */
    @Override
    public int insert(Payment record) {

        return this.insert("com.ningpai.system.dao.PaymentMapper.insert",
                record);
    }

    /**
     * @Title: insertSelective
     * @Description: 添加-字段可选
     * @param record
     * @return
     */
    @Override
    public int insertSelective(Payment record) {

        return this.insert(
                "com.ningpai.system.dao.PaymentMapper.insertSelective", record);
    }

    /**
     * @Title: selectByPrimaryKey
     * @Description: 根据ID查询
     * @param paymentId
     * @return
     */
    @Override
    public Payment selectByPrimaryKey(Long paymentId) {

        return this.selectOne(
                "com.ningpai.system.dao.PaymentMapper.selectByPrimaryKey",
                paymentId);
    }

    /**
     * @Title: updateByPrimaryKeySelective
     * @Description: 修改-字段可选
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(Payment record) {

        return this
                .update("com.ningpai.system.dao.PaymentMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * @Title: updateByPrimaryKey
     * @Description: 修改
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKey(Payment record) {

        return this.update(
                "com.ningpai.system.dao.PaymentMapper.updateByPrimaryKey",
                record);
    }

    /**
     * @Title: selectAllCount
     * @Description: 查询所有未删除的数量-分页用
     * @return
     */
    @Override
    public int selectAllCount() {

        return this
                .selectOne("com.ningpai.system.dao.PaymentMapper.selectAllCount");
    }

    /**
     * @Title: selectAllByPb
     * @Description: 分页查询所有未删除的
     * @param map
     * @return
     */
    @Override
    public List<Object> selectAllByPb(Map<String, Object> map) {

        return this.selectList(
                "com.ningpai.system.dao.PaymentMapper.selectAllByPb", map);
    }

    /**
     * @Title: selectAllForSite
     * @Description: 查询所有已启用、未删除的
     * @return
     */
    @Override
    public List<Payment> selectAllForSite() {

        return this
                .selectList("com.ningpai.system.dao.PaymentMapper.selectAllForSite");
    }

    /**
     * @Title: selectCountForSite
     * @Description: 查询所有已启用未删除的数量
     * @return
     */
    @Override
    public int selectCountForSite() {
        return this
                .selectOne("com.ningpai.system.dao.PaymentMapper.selectCountForSite");
    }
}
