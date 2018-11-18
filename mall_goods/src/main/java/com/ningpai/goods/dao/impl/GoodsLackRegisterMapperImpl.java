/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsLackRegister;
import com.ningpai.goods.dao.GoodsLackRegisterMapper;
import com.ningpai.goods.util.LackRegisterSearchBean;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 到货通知DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月18日 上午11:15:13
 * @version 1.0
 */
@Repository("GoodsLackRegisterMapper")
public class GoodsLackRegisterMapperImpl extends BasicSqlSupport implements
        GoodsLackRegisterMapper {
    /**
     * 根据主键删除到货通知
     *
     * @param lackRegisterId
     *            {@link java.lang.Long}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int deleteByPrimaryKey(Long lackRegisterId) {
        return this
                .delete("com.ningpai.goods.dao.GoodsLackRegisterMapper.deleteByPrimaryKey",
                        lackRegisterId);
    }

    /**
     * 插入一条数据(全属性 不推荐)
     *
     * @param record
     *            到货通知实体{@link com.ningpai.goods.bean.GoodsLackRegister}
     * @return 插入的主键ID {@link java.lang.Integer}
     */
    public int insert(GoodsLackRegister record) {
        return this.insert(
                "com.ningpai.goods.dao.GoodsLackRegisterMapper.insert", record);
    }

    /**
     * 插入一条数据 (可选属性 推荐)
     *
     * @param record
     *            到货通知实体{@link com.ningpai.goods.bean.GoodsLackRegister}
     * @return 插入的主键ID {@link java.lang.Integer}
     */
    public int insertSelective(GoodsLackRegister record) {
        return this
                .insert("com.ningpai.goods.dao.GoodsLackRegisterMapper.insertSelective",
                        record);
    }

    /**
     * 根据主键查询到货通知实体
     *
     * @param lackRegisterId
     *            {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsLackRegister}
     */
    public GoodsLackRegister selectByPrimaryKey(Long lackRegisterId) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsLackRegisterMapper.selectByPrimaryKey",
                        lackRegisterId);
    }

    /**
     * 更新到货通知 (可选属性 推荐)
     *
     * @param record
     *            {@link com.ningpai.goods.bean.GoodsLackRegister}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(GoodsLackRegister record) {
        return this
                .update("com.ningpai.goods.dao.GoodsLackRegisterMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 更新到货通知 (全属性 不推荐)
     *
     * @param record
     *            {@link com.ningpai.goods.bean.GoodsLackRegister}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKey(GoodsLackRegister record) {
        return this
                .update("com.ningpai.goods.dao.GoodsLackRegisterMapper.updateByPrimaryKey",
                        record);
    }

    /**
     * 查询所有的行数
     *
     * @return {@link java.lang.Integer}
     */
    public int queryTotalCount() {
        return this
                .selectOne("com.ningpai.goods.dao.GoodsLackRegisterMapper.queryTotalCount");
    }

    /**
     * 根据分页参数查询列表
     *
     * @param map
     *            封装分页参数 {@link java.util.Map}
     * @return {@link java.util.List}
     */
    public List<Object> queryAllByPageBean(Map<String, Integer> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsLackRegisterMapper.queryAllByPageBean",
                        map);
    }

    /**
     * 更改通知状态
     *
     * @param lackId
     *            {@link java.lang.Long}
     * @return {@link java.lang.Integer}
     */
    public int updateNoticeStatus(Long lackId) {
        return this
                .update("com.ningpai.goods.dao.GoodsLackRegisterMapper.updateNoticeStatus",
                        lackId);
    }

    /**
     * 根据查询参数查询行数
     *
     * @param searchBean
     * @return
     */
    public int queryTotalCountBySearchBean(LackRegisterSearchBean searchBean) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsLackRegisterMapper.queryTotalCountBySearchBean",
                        searchBean);
    }

    /**
     * 高级查询
     *
     * @param map
     *            封装参数 {@link java.util.Maps}
     * @return 查询到的列表
     */
    public List<Object> queryByPageBeanAndSearchBean(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsLackRegisterMapper.queryByPageBeanAndSearchBean",
                        map);
    }

    /**
     * 根据货品ID更新到货通知状态
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int updateStatusByProductId(Long productId) {
        return this
                .update("com.ningpai.goods.dao.GoodsLackRegisterMapper.updateStatusByProductId",
                        productId);
    }

}
