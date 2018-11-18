/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.vo;

/**
 * 商品关注列表Vo
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年3月26日 下午3:41:50
 * @version
 */
public class GoodsAtteListVo {
    /*
    *关注数
     */
    private Long atteCount;
    /*
    *货品
     */
    private GoodsProductVo product;

    public Long getAtteCount() {
        return atteCount;
    }

    public void setAtteCount(Long atteCount) {
        this.atteCount = atteCount;
    }

    public GoodsProductVo getProduct() {
        return product;
    }

    public void setProduct(GoodsProductVo product) {
        this.product = product;
    }

}
