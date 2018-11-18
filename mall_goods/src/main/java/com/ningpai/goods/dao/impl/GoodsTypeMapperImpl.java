/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsType;
import com.ningpai.goods.dao.GoodsTypeMapper;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.vo.GoodsTypeVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品类型Dao实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月23日 上午10:40:43
 * @version 1.0
 */
@Repository("GoodsTypeMapper")
public class GoodsTypeMapperImpl extends BasicSqlSupport implements
        GoodsTypeMapper {
    /**
     * 删除商品类型
     *
     * @param map
     *            主键ID，删除人名称 {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsTypeMapper.deleteByPrimaryKey",
                        map);
    }

    /**
     * 插入一条记录 （全属性 不推荐）
     *
     * @param record
     *            待插入的实体 {@link com.ningpai.goods.bean.GoodsType}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    public int insert(GoodsType record) {
        return this.insert("com.ningpai.goods.dao.GoodsTypeMapper.insert",
                record);
    }

    /**
     * 插入一条记录 （可选属性 推荐）
     *
     * @param record
     *            待插入的实体 {@link com.ningpai.goods.bean.GoodsType}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    public int insertSelective(GoodsType record) {
        return this
                .insert("com.ningpai.goods.dao.GoodsTypeMapper.insertSelective",
                        record);
    }

    /**
     * 根据主键查询
     *
     * @param typeId
     *            类型主键 {@link java.lang.Long}
     * @return 查询到的类型实体Vo {@link com.ningpai.goods.vo.GoodsTypeVo}
     */
    public GoodsTypeVo selectByPrimaryKey(Long typeId) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsTypeMapper.selectByPrimaryKey",
                typeId);
    }

    /**
     * 更新 (可选属性 推荐)
     *
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsType}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(GoodsType record) {
        return this
                .update("com.ningpai.goods.dao.GoodsTypeMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 更新 (全属性 不推荐)
     *
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsType}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKey(GoodsType record) {
        return this.update(
                "com.ningpai.goods.dao.GoodsTypeMapper.updateByPrimaryKey",
                record);
    }

    /**
     * 查询所有的商品类型
     *
     * @param map
     *            封装的分页参数
     * @return 查询到的类型列表 {@link com.ningpai.goods.bean.GoodsType}
     *         {@link java.util.Map}
     */
    public List<Object> selectAllType(Map<String, Integer> map) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsTypeMapper.selectAllType", map);
    }

    /**
     * 查询所有的行数 用来分页
     *
     * @return 查询到的行数 {@link java.lang.Integer}
     */
    public int queryTotalCount() {
        return this
                .selectOne("com.ningpai.goods.dao.GoodsTypeMapper.queryTotalCount");
    }

    /**
     * 查询最新插入的ID
     *
     * @return 查询到的ID {@link java.lang.Long}
     */
    public Long selectLastId() {
        return this
                .selectOne("com.ningpai.goods.dao.GoodsTypeMapper.selectLastId");
    }

    /**
     * 根据商品分类ID查询商品类型VO
     *
     * @param catId
     *            商品分类ID {@link java.lang.Long}
     * @return 查询到的VO {@link com.ningpai.goods.vo.GoodsTypeVo}
     */
    public GoodsTypeVo queryTypeVoByCatId(Long catId) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsTypeMapper.queryTypeVoByCatId",
                catId);
    }

    /**
     * 分页查询总行数
     *
     * @param selectBean
     * @return int
     */
    public int searchTotalCount(SelectBean selectBean) {

        return this.selectOne(
                "com.ningpai.goods.dao.GoodsTypeMapper.searchTotalCount",
                selectBean);
    }

    /**
     * 分页查询信息
     *
     * @param map
     * @return List
     */
    public List<Object> searchAllType(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsTypeMapper.searchAllType", map);
    }

    /**
     * 根据类型名称查询行数
     *
     * @param typeName
     *            类型名称 {@link java.lang.String}
     * @return 查询到的行数 {@link java.lang.Integer}
     */
    public int queryCountByTypeName(String typeName) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsTypeMapper.queryCountByTypeName",
                typeName);
    }

    /**
     * 取出所有可用的类型
     * 
     * @return
     */
    public List<GoodsType> selectAll() {
        return this
                .selectList("com.ningpai.goods.dao.GoodsTypeMapper.selectAll");
    }

}
