/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.goods.bean.GoodsRelatedGoods;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsRelatedGoodsMapper;
import com.ningpai.goods.service.GoodsRelatedGoodsService;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.MyLogger;

/**
 * 商品关联商品Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月25日 下午4:50:14
 * @version 1.0
 */
@Service("GoodsRelatedGoodsService")
public class GoodsRelatedGoodsServiceImpl implements GoodsRelatedGoodsService {
    // 商品关联商品DAO
    private GoodsRelatedGoodsMapper goodsRelatedGoodsMapper;
    private CascDelMapper cascDelMapper;
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsRelatedGoodsServiceImpl.class);

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsRelatedGoodsMapper getGoodsRelatedGoodsMapper() {
        return goodsRelatedGoodsMapper;
    }

    @Resource(name = "GoodsRelatedGoodsMapper")
    public void setGoodsRelatedGoodsMapper(
            GoodsRelatedGoodsMapper goodsRelatedGoodsMapper) {
        this.goodsRelatedGoodsMapper = goodsRelatedGoodsMapper;
    }

    @Override
    public int deleteRelatedProduct(Long relatedId, Long relatedProductId) {
        return goodsRelatedGoodsMapper.deleteRelatedProduct(relatedId,relatedProductId);
    }

    /**
     * 添加商品关联商品记录
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @param aboutGoodsId
     *            关联的商品ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 插入的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int save(Long goodsId, Long aboutGoodsId, String username) {
        GoodsRelatedGoods relatedGoods = null;
        try {
            if (null != goodsId) {
                relatedGoods = new GoodsRelatedGoods();
                relatedGoods.setGoodsId(goodsId);
                relatedGoods.setRelaCreateName(username);
                relatedGoods.setRelaDelflag(ValueUtil.DEFAULTDELFLAG);
                relatedGoods.setRelaGoodsId(aboutGoodsId);
                // 添加商品关联商品记录
                return this.goodsRelatedGoodsMapper
                        .insertSelective(relatedGoods);
            }
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.SAVERELAGODOS + username);
            // 参数置空
            relatedGoods = null;
        }
        // 返回结果
        return 0;
    }

    /**
     * 根据商品ID删除所有的商品关联记录
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 删除的行数{@link java.lang.Integer}
     */
    @Transactional
    public int delAllRelaGoodsByGoodsId(Long goodsId, String username) {
        // 定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            // 把参数放入map集合中
            map.put("delName", username);
            map.put(ValueUtil.RELAGOODSGOODSID, goodsId.toString());
            // 根据商品ID删除所有的商品关联记录
            return this.goodsRelatedGoodsMapper.delAllRelaGoodsByGoodsId(map);
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.DELALLRELAGOODSBYGOODSID + username);
            this.cascDelMapper.cascDel(username);
            // 参数置空
            map = null;
        }
    }

    /**
     * 根据商品ID和关联的商品ID查询记录
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @param relaGoodsId
     *            关联的商品ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsRelatedGoods}
     */
    public GoodsRelatedGoods queryByGoodsIdAndRelaGoodsIdIncludeDel(
            Long goodsId, Long relaGoodsId) {
        // 定义一个HashMap集合
        Map<String, Long> map = new HashMap<String, Long>();
        try {
            // 把参数放进map集合中
            map.put("goodsId", goodsId);
            map.put("relaGoodsId", relaGoodsId);
            // 根据商品ID和关联的商品ID查询记录
            return this.goodsRelatedGoodsMapper
                    .queryByGoodsIdAndRelaGoodsIdIncludeDel(map);
        } finally {
            // 参数置空
            map = null;
        }
    }

    /**
     * 更新商品关联商品信息
     *
     * @param relaGoods
     *            待更新的实体{@link com.ningpai.goods.bean.GoodsRelatedGoods}
     * @param usernmae
     *            操作人名称
     * @return 更新的行数
     */
    @Transactional
    public int updateRelaGoods(GoodsRelatedGoods relaGoods, String usernmae) {
        relaGoods.setRelaCreateName(usernmae);
        // 打印日志
        LOGGER.info(ValueUtil.UPDATERELAGOODS + usernmae);
        // 更新商品关联商品信息
        return this.goodsRelatedGoodsMapper
                .updateByPrimaryKeySelective(relaGoods);
    }

    /**
     * 根据商品ID和选中的ID删除未被选中的关联记录
     *
     * @param goodsId
     *            商品ID {@link java.lang.Longs}
     * @param ralaGoodsIds
     *            选中的商品ID的数组{@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 删除的行数{@link java.lang.Integer}
     */
    @Transactional
    public int delRelaGoodsByGoodsIdAndRelaGoodsIds(Long goodsId,
            String[] ralaGoodsIds, String username) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 把参数放入map集合中
            map.put("goodsId", goodsId);
            map.put("relaGoodsIds", ralaGoodsIds);
            map.put("delName", username);
            // 根据商品ID和选中的ID删除未被选中的关联记录
            return this.goodsRelatedGoodsMapper.delGoodsRelaGoods(map);
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.DELRELAGOODSBYGOODSIDANDRELAGOODSIDS
                    + username);
            // 参数置空
            map = null;
        }
    }

}
