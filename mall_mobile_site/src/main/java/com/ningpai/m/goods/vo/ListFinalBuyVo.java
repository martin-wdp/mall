/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.goods.vo;

import com.ningpai.goods.bean.GoodsProduct;

/**
 * 列表页最终购买辅助Bean
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年7月4日 下午4:22:41
 * @version 1.0
 */
public class ListFinalBuyVo {
    // 货品信息
    private GoodsProduct product;
    // 百分比
    private String precent;

    public GoodsProduct getProduct() {
        return product;
    }

    public void setProduct(GoodsProduct product) {
        this.product = product;
    }

    public String getPrecent() {
        return precent;
    }

    public void setPrecent(String precent) {
        this.precent = precent;
    }

}
