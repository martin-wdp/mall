/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.goods.dao;

import java.util.Map;

import com.ningpai.m.goods.bean.SiteGoodsAtte;

/**
 * 商品关注DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月12日 下午4:20:07
 * @version 1.0
 */
public interface SiteGoodsAtteMapper {
    /**
     * 保存商品关注
     * 
     * @param siteGoodsAtte
     *            {@link com.ningpai.site.goods.bean.}
     */
    int saveGoodsAtte(SiteGoodsAtte siteGoodsAtte);

    /**
     * 根据参数查询关注记录
     * 
     * @param map
     *            封装参数
     * @return 查询到的行数
     */
    Integer queryAtteHistByCustIdAndProId(Map<String, ?> map);
}
