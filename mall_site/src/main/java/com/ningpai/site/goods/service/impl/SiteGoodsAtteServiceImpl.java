/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.service.impl;

import com.ningpai.site.customer.bean.CustomerFollow;
import com.ningpai.site.customer.mapper.CustomerFollowMapper;
import com.ningpai.site.goods.bean.SiteGoodsAtte;
import com.ningpai.site.goods.dao.SiteGoodsAtteMapper;
import com.ningpai.site.goods.service.SiteGoodsAtteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 商品关注Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月12日 下午4:38:32
 * @version 1.0
 */
@Service("SiteGoodsAtteService")
public class SiteGoodsAtteServiceImpl implements SiteGoodsAtteService {

    private SiteGoodsAtteMapper goodsAtteMapper;

    @Resource(name = "customerFollowMapperSite")
    private CustomerFollowMapper customerFollowMapper;

    /**
     * 保存商品关注信息
     * 
     * @param custId
     *            会员ID
     * @param productId
     * @param districtId
     * @param goodsprice
     * @return
     */
    public int saveGoodsAtte(Long custId, Long productId, Long districtId, BigDecimal goodsprice) {
        if (!this.checkAtte(custId, productId)) {
            // 创建关注对象并保存
            SiteGoodsAtte siteGoodsAtte = new SiteGoodsAtte();
            try {
                siteGoodsAtte.setCustId(custId);// 用户ID
                siteGoodsAtte.setProductId(productId); // 商品ID
                siteGoodsAtte.setDistrictId(districtId); // 区域ID
                siteGoodsAtte.setFollowPrice(goodsprice); // 收藏的价格
                return this.goodsAtteMapper.saveGoodsAtte(siteGoodsAtte);
            } finally {
                siteGoodsAtte = null;
            }
        } else {
            return -1;
        }
    }

    /**
     * jiaodongzhi
     * 
     * @param custId
     * @param productId
     * @param districtId
     * @param goodsprice
     * @return
     */
    @Override
    public int newsaveGoodsAtte(Long custId, Long productId, Long districtId, BigDecimal goodsprice) {

        Map<String, Object> map = new HashMap<>();
        map.put("custId", custId);
        map.put("productId", productId);
        CustomerFollow customerFollow = customerFollowMapper.queryByCustIdAndProId(map);
        // 关注货品为空时添加
        if (null == customerFollow) {
            // 创建关注对象并保存
            SiteGoodsAtte siteGoodsAtte = new SiteGoodsAtte();
            try {
                siteGoodsAtte.setCustId(custId);// 用户ID
                siteGoodsAtte.setProductId(productId); // 商品ID
                siteGoodsAtte.setDistrictId(districtId); // 区域ID
                siteGoodsAtte.setFollowPrice(goodsprice); // 收藏的价格
                return this.goodsAtteMapper.saveGoodsAtte(siteGoodsAtte);
            } finally {
                siteGoodsAtte = null;
            }
        }
        //这是商品详情页关注商品信息

            // 删除关注的商品
            map.put("customerId", custId);
            map.put("followId", customerFollow.getFollowId());
            customerFollowMapper.deleteByPrimaryKey(map);
            return -1;

    }

    /**
     * 查询商品是否已经被关注
     * 
     * @param custId
     *            会员ID
     * @param productId
     *            货品ID
     * @return
     */
    public boolean checkAtte(Long custId, Long productId) {
        Map<String, Long> map = new HashMap<String, Long>();
        try {
            map.put("custId", custId);
            map.put("productId", productId);
            return this.goodsAtteMapper.queryAtteHistByCustIdAndProId(map) > 0 ? true : false;
        } finally {
            map = null;
        }
    }

    public SiteGoodsAtteMapper getGoodsAtteMapper() {
        return goodsAtteMapper;
    }

    @Resource(name = "SiteGoodsAtteMapper")
    public void setGoodsAtteMapper(SiteGoodsAtteMapper goodsAtteMapper) {
        this.goodsAtteMapper = goodsAtteMapper;
    }

}
