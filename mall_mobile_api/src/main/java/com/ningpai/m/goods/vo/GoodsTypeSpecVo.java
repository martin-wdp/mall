/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.goods.vo;

/**
 * 商品类型关联规格Vo
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 下午1:41:49
 * @version 1.0
 */
public class GoodsTypeSpecVo {
    // 主键ID
    private Long typeSpecId;

    // 类型ID
    private Long typeId;

    // 排序
    private Integer typeSpecSort;

    // 商品规格
    private GoodsSpecVo goodsSpec;

    // 删除标记 0：未删除 1：已删除
    private String typeSpecDelflag;

    public Long getTypeSpecId() {
        return typeSpecId;
    }

    public void setTypeSpecId(Long typeSpecId) {
        this.typeSpecId = typeSpecId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Integer getTypeSpecSort() {
        return typeSpecSort;
    }

    public void setTypeSpecSort(Integer typeSpecSort) {
        this.typeSpecSort = typeSpecSort;
    }

    public GoodsSpecVo getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(GoodsSpecVo goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public String getTypeSpecDelflag() {
        return typeSpecDelflag;
    }

    public void setTypeSpecDelflag(String typeSpecDelflag) {
        this.typeSpecDelflag = typeSpecDelflag;
    }

}
