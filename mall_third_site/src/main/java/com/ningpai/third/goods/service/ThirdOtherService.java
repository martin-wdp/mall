/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.goods.service;

import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.bean.GoodsTag;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;
import java.util.List;

/**
 * <p>
 * 第三方Other service
 * </p>
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月9日 上午10:37:22
 * @version 1.0
 */
public interface ThirdOtherService {

    /**
     * 根据第三方ID查询所有的签约的分类信息
     *
     * @param thirdId
     *            第三方ID {@link Long}
     * @return 查询到的分类列表 {@link com.ningpai.goods.bean.GoodsCate}
     */
    List<GoodsCate> queryGrandCateForThirdnew(Long thirdId, Long catId);

    /**
     * 根据第三方ID查询签约的品牌
     * 
     * @param thirdId
     *            第三方ID {@link Long}
     * @return 查询到的集合 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsBrand}
     */
    List<GoodsBrand> queryGrandBrandByThirdId(Long thirdId);

    /**
     * 查询所有的商品标签
     *
     * @return 查询到的集合 {@link com.ningpai.goods.bean.GoodsTag}
     */
    List<GoodsTag> queryAllGoodsTagForThird();

    /**
     * 根据第三方ID查询所有的签约的分类信息
     *
     * @param thirdId
     *            第三方ID {@link Long}
     * @return 查询到的分类列表 {@link com.ningpai.goods.bean.GoodsCate}
     */
    List<GoodsCate> queryGrandCateForThird(Long thirdId);

    /**
     * 根据第三方ID查询所有的签约的分类信息
     *
     * @param thirdId
     *            第三方ID {@link Long}
     * @return 查询到的分类列表 {@link com.ningpai.goods.bean.GoodsCate}
     */
    List<GoodsCate> queryGrandCateForThirdtwo(Long thirdId, Long catId);

    /**
     * 根据第三方分类ID查询第三方签约的分类信息
     *
     * @param thirdId
     *            分类ID {@link Long}
     * @return 查询到的分类列表 {@link com.ningpai.goods.bean.GoodsCate}
     */
    GoodsCate queryGoodsCateForThirdById(Long thirdId, Long cateId);

    /**
     * 根据第三方的信息 查询相关的商品集合
     *
     * @param pb
     *            分页辅助Bean {@link com.ningpai.util.PageBean}
     * @param thirdId
     *            第三方签约ID {@link Long }
     * @param goodsCatId
     *            选择的宁派分类ID {@link Long}
     * @return 将查询到的集合封装进去的分页辅助Bean {@link com.ningpai.util.PageBean}
     */
    PageBean queryAboutGoodsForThirdByThirdInfo(PageBean pb, Long thirdId, Long goodsCatId);

    /**
     * 根据第三方的信息，查询相关的商品组合
     *
     * @param pb
     *            分页辅助Bean {@link com.ningpai.util.PageBean}
     * @param thirdId
     *            thirdId 第三方签约ID {@link Long }
     * @return 将查询到的集合封装进去的分页辅助Bean {@link com.ningpai.util.PageBean}
     */
    PageBean queryThirdGroupByParam(PageBean pb, Long thirdId, SelectBean bean);

    /**
     * 保存签约的品牌信息
     * 
     * @param thirdId
     *            第三方店铺ID {@link Long}
     * @param brandId
     *            品牌ID {@link Long}
     * @return 插入的行数 {@link Integer}
     */
    int saveGrantBrand(Long thirdId, Long brandId);

    /**
     * 保存签约的分类信息
     * 
     * @param thirdId
     *            第三方店铺ID {@link Long}
     * @param catId
     *            分类ID {@link Long}
     * @return 插入的行数 {@link Integer}
     */
    int saveGrantCat(Long thirdId, Long catId);
}
