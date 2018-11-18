/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.goods.service;

import com.ningpai.third.goods.util.ThirdGoodsSearchBean;
import com.ningpai.third.goods.vo.SalesProductVo;
import com.ningpai.util.PageBean;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>第三方商品Service</p>
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月12日 下午3:05:37
 * @version 2.0
 */
public interface ThirdGoodsService {
    /**
     * 根据分页辅助Bean查询第三方商品列表
     * 
     * @param pb
     *            分页辅助Bean {@link com.ningpai.util.PageBean}
     * @param searchBean
     *            查询辅助Bean {@link com.ningpai.third.goods.util.ThirdGoodsSearchBean}
     * @param thirdId
     *            第三方店铺的签约ID {@link Long}
     * @param goodsAddedSta
     *            查询商品列表的上下架状态 {@link Long}
     * @return 封装查询列表的分页对象 {@link com.ningpai.util.PageBean}
     */
    PageBean queryThirdGoodsList(PageBean pb, Long thirdId, ThirdGoodsSearchBean searchBean, Long goodsAddedSta);

    /**
     *
     * @param pb 分页辅助Bean {@link com.ningpai.util.PageBean}
     * @param thirdId 第三方店铺的签约ID {@link Long}
     * @param searchBean 查询辅助Bean {@link com.ningpai.third.goods.util.ThirdGoodsSearchBean}
     * @param goodsAddedSta 查询商品列表的上下架状态 {@link Long}
     * @param flag 商品审核状态标记
     * @return
     */
    PageBean queryThirdGoodsListByFlag(PageBean pb, Long thirdId, ThirdGoodsSearchBean searchBean, Long goodsAddedSta, Long flag);

    /**
     * 根据第三方ID查询销量排行的商品
     *
     * @param thirdId
     *            第三方ID {@link Long}
     * @return 查询到的前5条记录 {@link java.util.List}
     */
    List<SalesProductVo> queryTopSalesGoods(Long thirdId);

    /**
     * 根据分页辅助Bean查询第三方商品复制列表
     *
     * @param pb
     *            分页辅助Bean {@link com.ningpai.util.PageBean}
     * @param searchBean
     *            查询辅助Bean {@link com.ningpai.third.goods.util.ThirdGoodsSearchBean}
     * @param thirdId
     *            第三方店铺的签约ID {@link Long}
     * @param goodsAddedSta
     *            查询商品列表的上下架状态 {@link Long}
     * @return 封装查询列表的分页对象 {@link com.ningpai.util.PageBean}
     */
    PageBean queryThirdGoodsCopyList(PageBean pb, Long thirdId, ThirdGoodsSearchBean searchBean, Long goodsAddedSta);

    /**
     * 根据商品id数组复制商品
     * 
     * @param goodsIds 需要复制的商品id数组
     * @param request
     * @return
     */
    int copyByGoodsIds(Long[] goodsIds, HttpServletRequest request);
    
    /**
     * 获取前台地址
     * @return
     */
    String bsetUrl();

}
