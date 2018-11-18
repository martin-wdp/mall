/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsGroup;
import com.ningpai.goods.dao.GoodsGroupMapper;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.vo.GoodsGroupVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品组合DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月31日 上午11:40:20
 * @version 1.0
 */
@Repository("GoodsGroupMapper")
public class GoodsGroupMapperImpl extends BasicSqlSupport implements
        GoodsGroupMapper {
    /**
     * 根据主键删除
     *
     * @param map
     *            封装参数的Map {@link java.util.Map}
     * @return 删除的行数
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this.update(
                "com.ningpai.goods.dao.GoodsGroupMapper.deleteByPrimaryKey",
                map);
    }

    /**
     * 新 删除商品组合
     * 
     * @param map
     * @return
     */
    public int deleteByPrimaryKeyNew(Map<String, String> map) {
        return this.update(
                "com.ningpai.goods.dao.GoodsGroupMapper.deleteByPrimaryKeyNew",
                map);
    }

    /**
     * 插入一条记录
     *
     * @param record
     *            待插入的实体{@link com.ningpai.goods.bean.GoodsGroup}
     * @return 插入的行数{@link java.lang.Long}
     */
    public Long insertSelective(GoodsGroup record) {
        this.insert("com.ningpai.goods.dao.GoodsGroupMapper.insertSelective",
                record);
        return this.selectLastId();
    }

    /**
     * 根据主键查询商品组合信息
     *
     * @param groupId
     *            主键ID {@link java.lang.Long}
     * @return 查询到的实体{@link com.ningpai.goods.bean.GoodsGroup}
     */
    public GoodsGroup selectByPrimaryKey(Long groupId) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsGroupMapper.selectByPrimaryKey",
                groupId);
    }

    /**
     * 更新组合信息
     *
     * @param record
     *            待更新的实体{@link com.ningpai.goods.bean.GoodsGroup}
     * @return 更新的行数{@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(GoodsGroup record) {
        return this
                .update("com.ningpai.goods.dao.GoodsGroupMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 根据分页参数分页查询列表
     *
     * @param map
     *            封装了分页查询的参数 {@link java.util.Map}
     * @return 查询到的列表 {@link java.util.List}
     */
    public List<Object> queryAllByPageBean(Map<String, Integer> map) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsGroupMapper.queryAllByPageBean",
                map);
    }

    /**
     * 查询所有的行数
     *
     * @return 查询到的行数 {@link java.lang.Integer}
     */
    public int queryTotalAcount() {
        return this
                .selectOne("com.ningpai.goods.dao.GoodsGroupMapper.queryTotalAcount");
    }

    /**
     * 根据主键ID查询组合VO
     *
     * @param groupId
     *            组合主键ID {@link java.lang.Long}
     * @returnx 查询到的VO信息
     */
    public GoodsGroupVo queryVoByPrimaryKey(Long groupId) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsGroupMapper.queryVoByPrimaryKey",
                groupId);
    }

    /**
     * 根据分页参数分页查询列表
     *
     * @param map
     *            封装了分页查询的参数 {@link java.util.Map}
     * @return 查询到的列表 {@link java.util.List}
     */
    public List<Object> queryAllByPageBeanAndSelBean(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsGroupMapper.queryAllByPageBeanAndSelBean",
                        map);
    }

    /**
     * 根据参数Bean查询行数
     *
     * @param selectBean
     * @return 查询到的行数
     */
    public int searchTotalCount(SelectBean selectBean) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsGroupMapper.searchTotalCount",
                selectBean);
    }

    /**
     * 查询最新插入的主键ID
     *
     * @return 查询到的主键ID
     */
    public Long selectLastId() {
        return this
                .selectOne("com.ningpai.goods.dao.GoodsGroupMapper.selectLastId");
    }

    /**
     * 根据货品ID查询所在的组合或者是套装
     *
     * @param productId
     *            货品ID {@link Long}
     * @return 查询到的集合 {@link List}
     */
    public List<GoodsGroupVo> queryGroupVoByProductId(Long productId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsGroupMapper.queryGroupVoByProductId",
                        productId);
    }

}
