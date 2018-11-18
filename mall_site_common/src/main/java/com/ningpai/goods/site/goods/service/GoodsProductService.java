/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.site.goods.service;

import java.util.List;

import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.site.goods.bean.GoodsDetailBean;
import com.ningpai.goods.site.goods.vo.GoodsProductVo;

/**
 * 货品Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月20日 上午11:00:51
 * @version 1.0
 */
public interface GoodsProductService {
    /**
     * 根据商品ID查询货品列表
     * 
     * @param goodsId
     *            商品ID
     * @return 货品列表
     */
    List<Object> queryAllProductListByGoodsId(Long goodsId);

    /**
     * 根据货品ID查询货品信息
     * 
     * @param productId
     *            货品信息ID
     * @return 查询到的货品信息实体
     */
    GoodsProductVo queryProductByProductId(Long productId);

    /**
     * 根据分类ID查询货品信息的集合
     * 
     * @param catId
     *            分类ID
     * @param rowCount
     *            查询的行数
     * @return 查询到的集合
     */
    List<GoodsProduct> queryTopSalesByCatIds(Long catId, Integer rowCount);

    /**
     * 根据分类ID查询最新上架的货品
     * 
     * @param catId
     *            分类ID
     * @param rowCount
     *            查询的行数
     * @return 查询到的货品
     */
    List<GoodsProduct> queryTopNewByCatIds(Long catId, Integer rowCount);

    /**
     * 根据货品ID查询
     * 
     * @param productId
     *            货品ID {@link java.lang.Long }
     * @param distinctId
     *            选择的地区ID
     * @return 查询到的组装商品详细页的Bean {@link com.ningpai.goods.site.goods.bean.GoodsDetailBean}
     */
    GoodsDetailBean queryDetailBeanByProductId(Long productId, Long distinctId);

    /**
     * 保存商品咨询
     * 
     * @param type
     *            咨询类型
     * @param comment
     *            咨询内容
     * @param custId
     *            会员ID
     * @param productId
     *            货品ID {@link java.lang.Long }
     * @return 是否保存成功
     */
    int saveProductCommentAsk(int type, String comment, Long custId, Long productId);

    /**
     * 把对比的货品ID插入到cookie中
     * 
     * @param productId
     *            待插入的货品ID {@link Long}
     * @param request
     *            请求对象
     * @param response
     *            响应对象
     */

    /**
     * 进行对比
     * 
     * @param productIds
     *            需要对比的商品列表 {@link List}
     * @return 需要对比的货品详细信息的集合 {@link List}
     */
    List<GoodsDetailBean> execCompProduct(List<Long> productIds);
}
