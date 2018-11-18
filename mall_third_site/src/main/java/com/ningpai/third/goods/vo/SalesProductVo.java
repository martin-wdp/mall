/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.goods.vo;

/**
 * <p>
 * 销售的商品
 * </p>
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年6月17日 下午6:03:53
 * @version 2.0
 */
public class SalesProductVo {
    // 货品ID
    private String goodsInfoId;
    // 货品名称
    private String goodsInfoName;
    // 销售数量
    private Integer count;

    public String getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(String goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public String getGoodsInfoName() {
        return goodsInfoName;
    }

    public void setGoodsInfoName(String goodsInfoName) {
        this.goodsInfoName = goodsInfoName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
