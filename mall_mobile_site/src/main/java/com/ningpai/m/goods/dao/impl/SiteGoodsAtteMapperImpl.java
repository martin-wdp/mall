/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.goods.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.m.goods.bean.SiteGoodsAtte;
import com.ningpai.m.goods.dao.SiteGoodsAtteMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品关注DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月12日 下午4:26:55
 * @version 1.0
 */
@Repository("SiteGoodsAtteMapper")
public final class SiteGoodsAtteMapperImpl extends BasicSqlSupport implements SiteGoodsAtteMapper {
    /**
     * 保存商品关注
     *
     * @param siteGoodsAtte
     */
    public int saveGoodsAtte(SiteGoodsAtte siteGoodsAtte) {
        return this.insert("com.ningpaimsite.goods.dao.GoodsProductMapper.saveGoodsAtte", siteGoodsAtte);
    }

    /**
     * 根据参数查询关注记录
     *
     * @param map
     *            封装参数
     * @return 查询到的行数
     */
    public Integer queryAtteHistByCustIdAndProId(Map<String, ?> map) {
        return this.selectOne("com.ningpaimsite.goods.dao.GoodsProductMapper.queryAtteHistByCustIdAndProId", map);
    }

}
