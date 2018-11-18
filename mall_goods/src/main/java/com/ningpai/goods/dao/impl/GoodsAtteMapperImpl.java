/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.dao.GoodsAtteMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品关注Dao实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年3月18日 下午4:30:33
 * @version 1.0
 */
@Repository("GoodsAtteMapper")
public class GoodsAtteMapperImpl extends BasicSqlSupport implements
        GoodsAtteMapper {
    /**
     * 根据主键删除
     *
     * @param map
     *            参数Map {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    public int deleteByPrimaryKey(Map<String, Object> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsAtteMapper.deleteByPrimaryKey",
                        map);
    }

    /**
     * 批量删除
     *
     * @param map
     *            封装的参数 {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    public int batchDelete(Map<String, Object> map) {
        return this.update("com.ningpai.goods.dao.GoodsAtteMapper.batchDelete",
                map);
    }

    /**
     * 根据参数查询所有的行数,去除重复的货品ID
     *
     * @param map
     *            封装的参数 {@link java.util.Map}
     * @return 查询到的行数 {@link java.lang.Integer}
     */
    public int queryTotalCount(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsAtteMapper.queryTotalCount", map);
    }

    /**
     * 根据参数查询集合
     *
     * @param map
     *            封装的参数 {@link java.util.Map}
     * @return 查询到的集合 {@link java.util.List}
     */
    public List<Object> queryByParam(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsAtteMapper.queryAtteListByParam",
                map);
    }

    /**
     * 根据货品Id查询关注的列表
     *
     * @param map
     *            参数 {@link java.util.Map}
     * @return 查询到的结果 {@link java.util.List}
     */
    public List<Object> queryByProductId(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsAtteMapper.queryByProductId", map);
    }

    /**
     * 查询货品所有的关注信息行数
     *
     * @param map
     *            封装的参数 {@link java.util.Map}
     * @return 查询到的行数 {@link java.lang.Integer}
     */
    public int queryTotalCountToProduct(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsAtteMapper.queryTotalCountToProduct",
                        map);
    }

}
