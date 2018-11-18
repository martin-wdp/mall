/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.goods.dao;

import com.ningpai.third.goods.vo.SalesProductVo;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 第三方商品DAO
 * </p>
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月12日 下午3:02:07
 * @version 2.0
 */
public interface ThirdGoodsMapper {
    /**
     * 查询第三方商品列表
     * 
     * @param map
     *            封装查询参数 {@link java.util.Map}
     * @return 查询到的集合 {@link java.util.List}
     */
    List<Object> queryThirdGoodsList(Map<String, Object> map);

    /**
     * 查询第三方商品行数
     * 
     * @param map
     *            封装查询参数 {@link java.util.Map}
     * @return 查询到的行数 {@link Integer}
     */
    int queryThirdGoodsCount(Map<String, Object> map);

    /**
     * 根据条件查询第三方商品行数
     * 
     * @param map
     * @return
     */
    int queryThirdGoodsCountByFlag(Map<String, Object> map);

    /**
     * 根据条件查询第三方商品列表
     * 
     * @param map
     * @return
     */
    List<Object> queryThirdGoodsListByFlag(Map<String, Object> map);

    /**
     * 查询店铺销量排行商品
     *
     * @param map
     *            参数Map
     * @return 查询到的前三条记录
     */
    List<SalesProductVo> queryTopSalesByThirdId(Map<String, Object> map);

    /**
     * 查询第三方商品复制列表
     *
     * @param map
     *            封装查询参数 {@link java.util.Map}
     * @return 查询到的集合 {@link java.util.List}
     */
    List<Object> queryThirdGoodsCopyList(Map<String, Object> map);

    /**
     * 查询第三方商品辅助行数
     *
     * @param map
     *            封装查询参数 {@link java.util.Map}
     * @return 查询到的行数 {@link Integer}
     */
    int queryThirdGoodsCopyCount(Map<String, Object> map);

    /**
     * 获取前台地址
     * 
     * @return
     */
    String bsetUrl();
}
