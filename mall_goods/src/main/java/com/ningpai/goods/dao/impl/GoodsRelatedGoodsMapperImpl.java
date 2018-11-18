/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsRelatedGoods;
import com.ningpai.goods.dao.GoodsRelatedGoodsMapper;
import com.ningpai.goods.vo.GoodsRelatedGoodsVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品关联商品DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月25日 下午4:42:17
 * @version 1.0
 */
@Repository("GoodsRelatedGoodsMapper")
public class GoodsRelatedGoodsMapperImpl extends BasicSqlSupport implements
        GoodsRelatedGoodsMapper {

    /**
     * 根据商品ID和商品关联ID删除关联商品
     * @param relatedId  商品关联ID
     * @param relatedProductId 商品ID
     * @return
     */
    @Override
    public int deleteRelatedProduct(Long relatedId, Long relatedProductId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("relatedId",relatedId);
        map.put("relatedProductId",relatedProductId);
        return this.delete("com.ningpai.goods.dao.GoodsRelatedGoodsMapper.deleteRelatedProduct",map);
    }

    /**
     * 删除关联商品记录 (更新字段)
     *
     * @param map
     *            封装所有的参数 {@link java.util.Map}
     * @return 影响的行数{@link java.lang.Integer}
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsRelatedGoodsMapper.deleteByPrimaryKey",
                        map);
    }

    /**
     * 插入一条记录(全属性 不推荐)
     *
     * @param record
     *            待插入的实体{@link com.ningpai.goods.bean.GoodsRelatedGoods}
     * @return 影响的行数
     */
    public int insert(GoodsRelatedGoods record) {
        return this.insert(
                "com.ningpai.goods.dao.GoodsRelatedGoodsMapper.insert", record);
    }

    /**
     * 插入一条记录(可选属性 推荐)
     *
     * @param record
     *            待插入的实体{@link com.ningpai.goods.bean.GoodsRelatedGoods}
     * @return 影响的行数
     */
    public int insertSelective(GoodsRelatedGoods record) {
        return this
                .insert("com.ningpai.goods.dao.GoodsRelatedGoodsMapper.insertSelective",
                        record);
    }

    /**
     * 根据主键ID查询关联对象
     *
     * @param relatedId
     *            主键ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsRelatedGoods}
     */
    public GoodsRelatedGoods selectByPrimaryKey(Long relatedId) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsRelatedGoodsMapper.selectByPrimaryKey",
                        relatedId);
    }

    /**
     * 更新商品关联商品记录(可选属性 推荐)
     *
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsRelatedGoods}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(GoodsRelatedGoods record) {
        return this
                .update("com.ningpai.goods.dao.GoodsRelatedGoodsMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 更新商品关联商品记录(全属性 不推荐)
     *
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsRelatedGoods}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKey(GoodsRelatedGoods record) {
        return this
                .update("com.ningpai.goods.dao.GoodsRelatedGoodsMapper.updateByPrimaryKey",
                        record);
    }

    /**
     * 根据商品ID查询所有的关联记录
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的关联记录的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsRelatedGoods}
     */
    public List<GoodsRelatedGoodsVo> queryAllByGoodsId(Long goodsId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsRelatedGoodsMapper.queryAllByGoodsId",
                        goodsId);
    }

    /**
     * 根据商品ID 删除所有的关联商品记录
     *
     * @param map
     *            封装所需的参数
     * @return 删除的行数 {@link java.lang.Integer}
     */
    public int delAllRelaGoodsByGoodsId(Map<String, String> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsRelatedGoodsMapper.delAllRelaGoodsByGoodsId",
                        map);
    }

    /**
     * 根据商品ID和关联的商品的ID查询管理的记录
     *
     * @param map
     *            封装参数
     * @return 查询到的关联记录实体{@link com.ningpai.goods.bean.GoodsRelatedGoods}
     */
    public GoodsRelatedGoods queryByGoodsIdAndRelaGoodsIdIncludeDel(
            Map<String, Long> map) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsRelatedGoodsMapper.queryByGoodsIdAndRelaGoodsIdIncludeDel",
                        map);
    }

    /**
     * 根据商品ID和选中的关联的商品ID删除未选中的
     *
     *            选中的关联的商品ID的数组
     * @return 删除的行数
     */
    public int delGoodsRelaGoods(Map<String, Object> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsRelatedGoodsMapper.delGoodsRelaGoods",
                        map);
    }

}
