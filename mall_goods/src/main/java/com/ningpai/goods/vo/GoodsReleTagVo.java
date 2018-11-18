/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.vo;

import com.ningpai.goods.bean.GoodsTag;

/**
 * 商品关联标签VO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月24日 下午4:28:15
 * @version 1.0
 */
public class GoodsReleTagVo {
    /*
     *主键ID
      */
    private Long relaTagId;
    /*
     *商品ID
      */
    private Long goodsId;
    /*
     *删除标记
      */
    private String relaTagDelflag;
    /*
     *商品标签
      */
    private GoodsTag goodsTag;

    public Long getRelaTagId() {
        return relaTagId;
    }

    public void setRelaTagId(Long relaTagId) {
        this.relaTagId = relaTagId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getRelaTagDelflag() {
        return relaTagDelflag;
    }

    public void setRelaTagDelflag(String relaTagDelflag) {
        this.relaTagDelflag = relaTagDelflag;
    }

    public GoodsTag getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(GoodsTag goodsTag) {
        this.goodsTag = goodsTag;
    }

}
