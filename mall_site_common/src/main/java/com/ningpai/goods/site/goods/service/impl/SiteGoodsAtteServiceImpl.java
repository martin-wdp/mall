/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.site.goods.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.goods.site.goods.bean.SiteGoodsAtte;
import com.ningpai.goods.site.goods.dao.SiteGoodsAtteMapper;
import com.ningpai.goods.site.goods.service.SiteGoodsAtteService;

/**  *
 * 商品关注Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月12日 下午4:38:32
 * @version 1.0
 */
@Service("GoodsSiteGoodsAtteService")
public class SiteGoodsAtteServiceImpl implements SiteGoodsAtteService {
    /**  
     *商品关注DAO
     */
    private SiteGoodsAtteMapper goodsAtteMapper;

    /**  
     * 保存商品关注信息
     *
     * @param custId
     *            会员ID
     * @param productId
     *            货品ID
     * @see com.ningpai.site.goods.service.SiteGoodsAtteService#saveGoodsAtte(java .lang.Long, java.lang.Long, java.lang.Long)
     */
    public int saveGoodsAtte(Long custId, Long productId) {
        if (!this.checkAtte(custId, productId)) {
            // 创建关注对象并保存
            SiteGoodsAtte siteGoodsAtte = new SiteGoodsAtte();
            try {
                siteGoodsAtte.setCustId(custId);
                siteGoodsAtte.setProductId(productId);
                //保存商品关注
                return this.goodsAtteMapper.saveGoodsAtte(siteGoodsAtte);
            } finally {
                siteGoodsAtte = null;
            }
        } else {
            return -1;
        }
    }

    /**  
     * 查询商品是否已经被关注
     *
     * @param custId
     *            会员ID
     * @param productId
     *            货品ID
     * @return boolean 表示已经关注过 false 表示未被关注
     * @see com.ningpai.site.goods.service.SiteGoodsAtteService#checkAtte(java.lang .Long, java.lang.Long)
     */
    public boolean checkAtte(Long custId, Long productId) {
        //定义一个HashMap集合
        Map<String, Long> map = new HashMap<String, Long>();
        try {
            map.put("custId", custId);
            map.put("productId", productId);
            //根据参数查询关注记录
            return this.goodsAtteMapper.queryAtteHistByCustIdAndProId(map) > 0 ? true : false;
        } finally {
            map = null;
        }
    }

    public SiteGoodsAtteMapper getGoodsAtteMapper() {
        return goodsAtteMapper;
    }

    @Resource(name = "GoodsSiteGoodsAtteMapper")
    public void setGoodsAtteMapper(SiteGoodsAtteMapper goodsAtteMapper) {
        this.goodsAtteMapper = goodsAtteMapper;
    }

}
