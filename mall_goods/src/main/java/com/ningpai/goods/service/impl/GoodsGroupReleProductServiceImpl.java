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

import com.ningpai.goods.bean.GoodsGroupReleProduct;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsGroupReleProductMapper;
import com.ningpai.goods.service.GoodsGroupReleProductService;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.MyLogger;

/**
 * 商品组合关联货品Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月2日 下午2:25:08
 * @version 1.0
 */
@Service("GoodsGroupReleProductService")
public class GoodsGroupReleProductServiceImpl implements
        GoodsGroupReleProductService {
    private GoodsGroupReleProductMapper goodsGroupReleProductMapper;
    private CascDelMapper cascDelMapper;
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsGroupReleProductServiceImpl.class);

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsGroupReleProductMapper getGoodsGroupReleProductMapper() {
        return goodsGroupReleProductMapper;
    }

    @Resource(name = "GoodsGroupReleProductMapper")
    public void setGoodsGroupReleProductMapper(
            GoodsGroupReleProductMapper goodsGroupReleProductMapper) {
        this.goodsGroupReleProductMapper = goodsGroupReleProductMapper;
    }

    /**
     * 根据组合ID和货品ID查询关联对象
     *
     * @param groupId
     *            组合ID{@link java.lang.Long}
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的对象{@link com.ningpai.goods.bean.GoodsGroupReleProduct}
     */
    public GoodsGroupReleProduct queryGroupReleProductByGroupIdAndProductId(
            Long groupId, Long productId) {
        // 定义一个HashMap集合
        Map<String, Long> map = new HashMap<String, Long>();
        try {
            map.put("groupId", groupId);
            map.put("productId", productId);
            // 根据组合ID和货品ID查询关联对象
            return this.goodsGroupReleProductMapper
                    .queryGroupReleProductByGroupIdAndProductId(map);
        } finally {
            // 参数置空
            map = null;
        }
    }

    /**
     * 根据组合ID和货品ID删除记录
     *
     * @param groupId
     *            组合ID {@link java.lang.Long}
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 删除的行数{@link java.lang.Integer}
     */
    @Transactional
    public int delGroupReleProductByGroupIdAndProductId(Long groupId,
            Long productId) {
        // 定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            map.put("releProductid",
                    this.queryGroupReleProductByGroupIdAndProductId(groupId,
                            productId).getReleProductid().toString());
            // 根据组合ID和货品ID删除记录
            return this.goodsGroupReleProductMapper.deleteByPrimaryKey(map);
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.DELGROUPRELEPRODUCTBYGROUPIDANDPRODUCTID);
            this.cascDelMapper.cascDel("");
            // 参数置空
            map = null;
        }
    }

    /**
     * 根据组合ID和货品ID数组批量删除
     *
     * @param groupId
     *            组合ID {@link java.lang.Long}
     * @param productIds
     *            货品ID的数组 {@link java.lang.Long}
     * @return 删除的行数{@link java.lang.Integer}
     */
    @Transactional
    public int batchDelGroupReleProductByGroupIdAndProductIds(Long groupId,
            Long[] productIds) {
        Integer count = 0;
        if (null != productIds && productIds.length > 0) {
            for (int i = 0; i < productIds.length; i++) {
                // 根据组合ID和货品ID数组批量删除
                count += delGroupReleProductByGroupIdAndProductId(groupId,
                        productIds[i]);
            }
        }
        // 打印日志
        LOGGER.info(ValueUtil.BATCHDELGROUPRELEPRODUCTBYGROUPIDANDPRODUCTIDS);
        this.cascDelMapper.cascDel("");
        return count;
    }

    /**
     * 根据组合ID和货品ID数组添加
     *
     * @param groupId
     *            组合ID {@link java.lang.Long}
     * @param productIds
     *            货品ID数组 {@link java.lang.Long}
     * @return 插入的行数
     */
    @Transactional
    public int addGroupReleProduct(Long groupId, Long[] productIds) {
        Integer count = 0;
        GoodsGroupReleProduct goodsGroupReleProduct = null;
        if (null != productIds && productIds.length > 0) {
            for (int i = 0; i < productIds.length; i++) {
                // 根据组合ID和货品ID查询对象
                goodsGroupReleProduct = this
                        .queryGroupReleProductByGroupIdAndProductId(groupId,
                                productIds[i]);
                // 如果为空就新建记录
                if (null == goodsGroupReleProduct) {
                    goodsGroupReleProduct = new GoodsGroupReleProduct();
                    goodsGroupReleProduct.setGroupId(groupId);
                    goodsGroupReleProduct.setProductId(productIds[i]);
                    goodsGroupReleProduct.setReleProductDelflag("0");
                    // 根据组合ID和货品ID数组添加
                    count += this.goodsGroupReleProductMapper
                            .insertSelective(goodsGroupReleProduct);
                } else {
                    goodsGroupReleProduct.setReleProductDelflag("0");
                    // 根据组合ID和货品ID数组添加
                    count += this.goodsGroupReleProductMapper
                            .updateByPrimaryKeySelective(goodsGroupReleProduct);
                }
            }
        }
        // 打印日志
        LOGGER.info(ValueUtil.ADDGROUPRELEPRODUCT);
        return count;
    }

}
