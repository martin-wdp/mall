/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsSpec;
import com.ningpai.goods.dao.GoodsSpecMapper;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.vo.GoodsSpecVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品规格DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月18日 下午6:17:57
 * @version 1.0
 */
@Repository("GoodsSpecMapper")
public class GoodsSpecMapperImpl extends BasicSqlSupport implements
        GoodsSpecMapper {
    /**
     * 根据主键删除商品规格
     *
     * @param map
     *            删除的参数 包含删除的ID，删除人名称 {@link java.util.Map}
     * @return 影响的行数 {@link java.lang.Integer}
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsSpecMapper.deleteByPrimaryKey",
                        map);
    }

    /**
     * 插入规格记录 (全属性 不推荐)
     *
     * @param record
     *            商品规格实体 {@link com.ningpai.goods.bean.GoodsSpec}
     * @return 插入的实体的id {@link java.lang.Integer}
     */
    public int insert(GoodsSpec record) {
        return this.insert("com.ningpai.goods.dao.GoodsSpecMapper.insert",
                record);
    }

    /**
     * 插入规格记录 (可选属性 推荐)
     *
     * @param record
     *            商品规格实体 {@link com.ningpai.goods.bean.GoodsSpec}
     * @return 插入的实体的id {@link java.lang.long}
     */
    public Long insertSelective(GoodsSpec record) {
        return (long) this
                .insert("com.ningpai.goods.dao.GoodsSpecMapper.insertSelective",
                        record);
    }

    /**
     * 根据主键查询
     *
     * @param specId
     *            规格ID {@link java.lang.Long}
     * @return 查询到的规格实体 {@link com.ningpai.goods.bean.GoodsSpec}
     */
    public GoodsSpec selectByPrimaryKey(Long specId) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsSpecMapper.selectByPrimaryKey",
                specId);
    }

    /**
     * 更新规格(可选属性 推荐)
     *
     * @param record
     *            待更新的规格实体 {@link com.ningpai.goods.bean.GoodsSpec}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(GoodsSpec record) {
        return this
                .update("com.ningpai.goods.dao.GoodsSpecMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 更新规格(全属性 不推荐)
     *
     * @param record
     *            待更新的规格实体 {@link com.ningpai.goods.bean.GoodsSpec}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKey(GoodsSpec record) {
        return this.update(
                "com.ningpai.goods.dao.GoodsSpecMapper.updateByPrimaryKey",
                record);
    }

    /**
     * 查询所有的行数
     *
     * @return 所有的行数{@link java.lang.Integer}
     */
    public int queryTotalCount() {
        return this
                .selectOne("com.ningpai.goods.dao.GoodsSpecMapper.queryTotalCount");
    }

    /**
     * 根据分页参数查询分页列表
     *
     * @param map
     *            封装分页参数 开始行数和结束行数 {@link java.util.Map}
     * @return 查询到的集合 {@link java.util.List}
     */
    public List<Object> queryListByPageBean(Map<String, Integer> map) {

        return this.selectList(
                "com.ningpai.goods.dao.GoodsSpecMapper.queryListByPageBean",
                map);
    }

    /**
     * 根据规格ID查询Vo
     *
     * @param specId
     *            规格ID {@link java.lang.Long}
     * @return 查询到的Vo对象 {@link com.ningpai.goods.vo.GoodsSpecVo}
     */
    public GoodsSpecVo querySpecVoBySpecId(Long specId) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsSpecMapper.querySpecVoBySpecId",
                specId);
    }

    /**
     * 查询最新插入的ID
     *
     * @return 查询到的ID {@link java.lang.Long}
     */
    public Long selectLastId() {
        return this
                .selectOne("com.ningpai.goods.dao.GoodsSpecMapper.selectLastId");
    }

    /**
     * 查询所有的商品规格
     *
     * @return 查询到的商品规格的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsSpec}
     */
    public List<GoodsSpec> queryAllSpec() {
        return this
                .selectList("com.ningpai.goods.dao.GoodsSpecMapper.queryAllSpec");
    }

    /**
     * 根据商品ID查询关联的规格VO
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.vo.GoodsSpecVo}
     */
    public List<GoodsSpecVo> querySpecVoByGoodsId(Long goodsId) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsSpecMapper.querySpecVoByGoodsId",
                goodsId);
    }

    /**
     * 查询所有的商品规格包含删除的
     *
     * @return 查询到的商品规格的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsSpec}
     */
    public List<GoodsSpec> queryAllSpecIncludeDel() {
        return this
                .selectList("com.ningpai.goods.dao.GoodsSpecMapper.queryAllSpecIncludeDel");
    }

    /**
     * 根据货品ID查询关联的规格
     *
     * @param goodsId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.vo.GoodsSpecVo}
     */
    @Override
    public List<GoodsSpecVo> querySpecVoByGoodsInfoId(Long goodsInfoId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsSpecMapper.querySpecVoByGoodsInfoId",
                        goodsInfoId);
    }

    /**
     * 分页查询总行数
     *
     * @param selectBean
     * @return int
     */
    public int searchTotalCount(SelectBean selectBean) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsSpecMapper.searchTotalCount",
                selectBean);

    }

    /**
     * 分页查询信息
     *
     * @param map
     * @return List
     */
    public List<Object> searchAllSpec(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsSpecMapper.searchAllSpec", map);
    }

    /**
     * 根据规格名称查询行数
     *
     * @param specName
     *            规格名称 {@link java.lang.String}
     * @return 查询到的行数 {@link java.lang.Integer}
     */
    public int queryCountBySpecName(String specName) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsSpecMapper.queryCountBySpecName",
                specName);
    }

}
