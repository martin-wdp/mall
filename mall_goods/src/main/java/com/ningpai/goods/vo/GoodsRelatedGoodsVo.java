/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.vo;

import java.util.List;

/**
 * 商品关联商品Vo
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月25日 下午6:39:55
 * @version 1.0
 */
public class GoodsRelatedGoodsVo {
    /*
     *主键ID
      */
    private Long relatedId;
    /*
     *商品ID
      */
    private Long goodsId;
    /*
     *关联的商品
      */
    private List<GoodsListVo> releatedGoods;

    public Long getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(Long relatedId) {
        this.relatedId = relatedId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public List<GoodsListVo> getReleatedGoods() {
        return releatedGoods;
    }

    public void setReleatedGoods(List<GoodsListVo> releatedGoods) {
        this.releatedGoods = releatedGoods;
    }

}
