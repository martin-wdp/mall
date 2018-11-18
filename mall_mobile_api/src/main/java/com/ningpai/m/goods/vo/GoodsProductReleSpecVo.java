/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.goods.vo;

import com.ningpai.goods.bean.GoodsSpecDetail;

/**
 * 货品信息关联规格信息
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月27日 下午5:04:15
 * @version 1.0
 */
public class GoodsProductReleSpecVo {
    // 主键ID
    private Long releSpecDetailId;
    // 货品信息ID
    private Long goodsInfoId;
    // 自定义规格值
    private String specValueRemark;
    // 规格ID
    private GoodsSpecVo spec;
    // 规格值ID
    private GoodsSpecDetail goodsSpecDetail;

    public Long getReleSpecDetailId() {
        return releSpecDetailId;
    }

    public void setReleSpecDetailId(Long releSpecDetailId) {
        this.releSpecDetailId = releSpecDetailId;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public GoodsSpecVo getSpec() {
        return spec;
    }

    public void setSpec(GoodsSpecVo spec) {
        this.spec = spec;
    }

    public GoodsSpecDetail getGoodsSpecDetail() {
        return goodsSpecDetail;
    }

    public void setGoodsSpecDetail(GoodsSpecDetail goodsSpecDetail) {
        this.goodsSpecDetail = goodsSpecDetail;
    }

    public String getSpecValueRemark() {
        return specValueRemark;
    }

    public void setSpecValueRemark(String specValueRemark) {
        this.specValueRemark = specValueRemark;
    }

}
