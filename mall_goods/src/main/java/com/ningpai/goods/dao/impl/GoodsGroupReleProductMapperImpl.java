/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsGroupReleProduct;
import com.ningpai.goods.dao.GoodsGroupReleProductMapper;
import com.ningpai.goods.vo.GoodsGroupReleProductVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品组合关联货品Dao实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月31日 下午3:37:19
 * @version 1.0
 */
@Repository("GoodsGroupReleProductMapper")
public class GoodsGroupReleProductMapperImpl extends BasicSqlSupport implements
        GoodsGroupReleProductMapper {
    /**
     * 删除
     *
     * @param map
     *            封装删除参数{@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsGroupReleProductMapper.deleteByPrimaryKey",
                        map);
    }

    /**
     * 插入记录
     *
     * @param record
     *            待插入的实体{@link com.ningpai.goods.bean.GoodsGroupReleProduct}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    public int insertSelective(GoodsGroupReleProduct record) {
        return this
                .insert("com.ningpai.goods.dao.GoodsGroupReleProductMapper.insertSelective",
                        record);
    }

    /**
     * 根据主键查询
     *
     * @param releProductid
     *            主键iD {@link java.lang.Long}
     * @return 查询到的实体{@link com.ningpai.goods.bean.GoodsGroupReleProduct}
     */
    public GoodsGroupReleProduct selectByPrimaryKey(Long releProductid) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsGroupReleProductMapper.selectByPrimaryKey",
                        releProductid);
    }

    /**
     * 更新
     *
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsGroupReleProduct}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(GoodsGroupReleProduct record) {
        return this
                .update("com.ningpai.goods.dao.GoodsGroupReleProductMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 根据主键ID和选中的货品ID批量删除不存在于选中的货品的记录
     *
     * @param map
     *            封装参数{@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    public int batchDelWithCheckProductId(Map<String, Long> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsGroupReleProductMapper.batchDelWithCheckProductId",
                        map);
    }

    /**
     * 根据商品组合ID查询关联的关联记录列表
     *
     * @param goodsGroupId
     *            商品组合ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsGroupReleProduct}
     */
    public List<GoodsGroupReleProduct> queryListByGoodsGroupId(Long goodsGroupId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsGroupReleProductMapper.queryListByGoodsGroupId",
                        goodsGroupId);
    }

    /**
     * 根据商品组合ID查询关联的关联记录列表
     *
     * @param goodsGroupId
     *            商品组合ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsGroupReleProduct}
     */
    public List<GoodsGroupReleProductVo> queryListByGoodsInfoGroupId(
            Long goodsGroupId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsGroupReleProductMapper.queryListByGoodsGroupId",
                        goodsGroupId);
    }

    /**
     * 根据组合ID和货品ID查询关联对象
     *
     * @param map
     *            封装查询参数 {@link java.util.Map}
     * @return 查询到的对象 {@link com.ningpai.goods.bean.GoodsGroupReleProduct}
     */
    public GoodsGroupReleProduct queryGroupReleProductByGroupIdAndProductId(
            Map<String, Long> map) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsGroupReleProductMapper.queryGroupReleProductByGroupIdAndProductId",
                        map);
    }

}
