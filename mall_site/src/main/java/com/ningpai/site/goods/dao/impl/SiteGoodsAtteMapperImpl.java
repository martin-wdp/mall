/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.goods.bean.SiteGoodsAtte;
import com.ningpai.site.goods.dao.SiteGoodsAtteMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 商品关注DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月12日 下午4:26:55
 * @version 1.0
 */
@Repository("SiteGoodsAtteMapper")
public final class SiteGoodsAtteMapperImpl extends BasicSqlSupport implements
        SiteGoodsAtteMapper {
    /**
     * 保存商品关注
     * @param siteGoodsAtte
     * @return
     */
    public int saveGoodsAtte(SiteGoodsAtte siteGoodsAtte) {
        return this.insert(
                "com.ningpaihsite.goods.dao.GoodsProductMapper.saveGoodsAtte",
                siteGoodsAtte);
    }

    /**
     * 根据参数查询关注记录
     * @param map
     *            封装参数
     * @return
     */
    public Integer queryAtteHistByCustIdAndProId(Map<String, ?> map) {
        return this
                .selectOne(
                        "com.ningpaihsite.goods.dao.GoodsProductMapper.queryAtteHistByCustIdAndProId",
                        map);
    }

}
