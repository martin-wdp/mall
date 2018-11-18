/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.goods.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.m.goods.bean.GoodsDetailBean;
import com.ningpai.m.goods.vo.GoodsProductVo;
import com.ningpai.m.goods.vo.ListFinalBuyVo;

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
     * 查询最近一月该分类下的热销商品
     * 
     * @param catId
     *            分类ID {@link Long}
     * @param rowCount
     *            查询行数 {@link Integer}
     * @return 查询到的商品集合 {@link List}
     */
    List<GoodsProduct> queryHotSalesTopSix(Long catId, Integer rowCount);

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
     * @return 查询到的组装商品详细页的Bean {@link com.ningpai.site.goods.bean.GoodsDetailBean}
     */
    GoodsDetailBean queryDetailBeanByProductId(Long productId, Long distinctId);

    /**
     * 保存商品咨询
     * 
     * @param type
     *            咨询类型
     * @param comment
     *            咨询内容
     * @return 是否保存成功
     */
    int saveProductCommentAsk(int type, String comment, Long custId, Long productId);

    /**
     * 根据分类ID查询最终购买的列表
     * 
     * @param catId
     *            分类ID {@link Long}
     * @param rowCount
     *            查询的行数 {@link Long}
     * @return 查询到的辅助Bena的集合
     */
    List<ListFinalBuyVo> browCatFinalBuyAndPrecent(Long catId, Long rowCount);

    /**
     * 进行对比
     * 
     * @param productIds
     *            需要对比的商品列表 {@link List}
     * @return 需要对比的货品详细信息的集合 {@link List}
     */
    List<GoodsDetailBean> execCompProduct(List<Long> productIds);

    /**
     * 进行对比,传递请求
     * 
     * @param productIds
     *            需要对比的商品列表 {@link List}
     * @return 需要对比的货品详细信息的集合 {@link List}
     */
    List<GoodsDetailBean> execCompProduct(List<Long> productIds, HttpServletRequest request);

    /**
     * 根据货品ID和查询行数查询销售最高的几条记录
     * 
     * @param productId
     *            货品ID
     * @param rowCount
     *            查询的行数
     * @return 查询到的货品集合
     */
    List<GoodsProduct> queryTopSalesByProductId(Long productId, Integer rowCount);

    /**
     * 新增兑换积分
     * @param map
     * @return
     */
    int insertExchangeCusmomer(Map<String, Object> map);

    /**
     * 根据地区ID查询关联的仓库ID
     * @param distinctId 地区ID
     * @return 仓库ID
     */
    Long selectWareIdByDistinctId(Long distinctId);
}
