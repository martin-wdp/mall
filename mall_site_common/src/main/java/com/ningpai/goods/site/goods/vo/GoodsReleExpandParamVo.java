/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.site.goods.vo;

import com.ningpai.goods.bean.GoodsTypeExpandParamValue;

/**
 * 商品关联类型详细参数Vo
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月25日 下午6:35:39
 * @version 1.0
 */
public class GoodsReleExpandParamVo {
    /*
     *主键ID
     */
    private Long releExpandparamId;
    /*
     *商品ID
     */
    private Long goodsId;
    /*
     *扩展属性
     */
    private GoodsTypeExpandParamVo expandParamVo;
    /*
     *扩展属性值
     */
    private GoodsTypeExpandParamValue expangparamValue;

    public Long getReleExpandparamId() {
        return releExpandparamId;
    }

    public void setReleExpandparamId(Long releExpandparamId) {
        this.releExpandparamId = releExpandparamId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public GoodsTypeExpandParamVo getExpandParamVo() {
        return expandParamVo;
    }

    public void setExpandParamVo(GoodsTypeExpandParamVo expandParamVo) {
        this.expandParamVo = expandParamVo;
    }

    public GoodsTypeExpandParamValue getExpangparamValue() {
        return expangparamValue;
    }

    public void setExpangparamValue(GoodsTypeExpandParamValue expangparamValue) {
        this.expangparamValue = expangparamValue;
    }

}
