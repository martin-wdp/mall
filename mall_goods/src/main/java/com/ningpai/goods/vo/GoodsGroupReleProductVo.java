/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.vo;

/**
 * 组合关联货品Vo
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月2日 上午11:36:33
 * @version 1.0
 */
public class GoodsGroupReleProductVo {
    /*
    *主键ID
     */
    private Long releProductid;
    /*
    *组合ID
     */
    private Long groupId;

    /*
    *货品id
     */
    private Long productId;

    /*
    *货品详细信息
     */
    private GoodsProductDetailVo productDetail;

    public Long getReleProductid() {
        return releProductid;
    }

    public void setReleProductid(Long releProductid) {
        this.releProductid = releProductid;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public GoodsProductDetailVo getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(GoodsProductDetailVo productDetail) {
        this.productDetail = productDetail;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
