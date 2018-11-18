/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.vo;

import java.util.List;

import com.ningpai.goods.vo.GoodsTypeSpecVo;

/**
 * 商品类型VO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 上午11:13:25
 * @version 1.0
 */
public class GoodsTypeVo {
    // 商品类型ID
    private Long typeId;

    // 类型名称
    private String typeName;

    // 类型别名
    private String typeNickname;

    // 是否是实物 0：不是实物 1：是实物
    private String typeIsreal;

    // 关联的品牌的集合
    private List<GoodsTypeBrandVo> brands;

    // 扩展属性集合
    private List<GoodsTypeExpandParamVo> expandParams;
    // 商品规格集合
    private List<GoodsTypeSpecVo> specVos;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeNickname() {
        return typeNickname;
    }

    public void setTypeNickname(String typeNickname) {
        this.typeNickname = typeNickname;
    }

    public String getTypeIsreal() {
        return typeIsreal;
    }

    public void setTypeIsreal(String typeIsreal) {
        this.typeIsreal = typeIsreal;
    }

    public List<GoodsTypeBrandVo> getBrands() {
        return brands;
    }

    public void setBrands(List<GoodsTypeBrandVo> brands) {
        this.brands = brands;
    }

    public List<GoodsTypeExpandParamVo> getExpandParams() {
        return expandParams;
    }

    public void setExpandParams(List<GoodsTypeExpandParamVo> expandParams) {
        this.expandParams = expandParams;
    }

    public List<GoodsTypeSpecVo> getSpecVos() {
        return specVos;
    }

    public void setSpecVos(List<GoodsTypeSpecVo> specVos) {
        this.specVos = specVos;
    }
}
