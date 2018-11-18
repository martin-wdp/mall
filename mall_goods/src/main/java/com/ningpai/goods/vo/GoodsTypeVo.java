/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.vo;

import java.util.List;

import com.ningpai.goods.bean.GoodsTypeParam;

/**
 * 商品类型VO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 上午11:13:25
 * @version 1.0
 */
public class GoodsTypeVo {
    /*
     *商品类型ID
      */
    private Long typeId;

    /*
     *类型名称
      */
    private String typeName;

    /*
     *类型别名
      */
    private String typeNickname;

    /*
     *是否是实物 0：不是实物 1：是实物
      */
    private String typeIsreal;
    /*
     *类型图片
      */
    private String typeImg;

    /*
     *关联的品牌的集合
      */
    private List<GoodsTypeBrandVo> brands;

    /*
     *扩展属性集合
      */
    private List<GoodsTypeExpandParamVo> expandParams;

    /*
     *商品规格集合
      */
    private List<GoodsTypeSpecVo> specVos;

    /*
     *详细参数集合
      */
    private List<GoodsTypeParam> params;

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

    public List<GoodsTypeParam> getParams() {
        return params;
    }

    public void setParams(List<GoodsTypeParam> params) {
        this.params = params;
    }

    public String getTypeImg() {
        return typeImg;
    }

    public void setTypeImg(String typeImg) {
        this.typeImg = typeImg;
    }

}
