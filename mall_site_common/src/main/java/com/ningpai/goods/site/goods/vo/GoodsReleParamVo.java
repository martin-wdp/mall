/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.site.goods.vo;

import com.ningpai.goods.bean.GoodsTypeParam;

/**
 * 商品关联类型详细参数Vo
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月25日 下午6:38:29
 * @version 1.0
 */
public class GoodsReleParamVo {
    /*
     *主键ID
     */
    private Long releParamId;
    /*
     *商品ID
     */
    private Long goodsId;
    /*
     *详细参数
     */
    private GoodsTypeParam param;
    /*
     *详细参数值
     */
    private String paramValue;

    public Long getReleParamId() {
        return releParamId;
    }

    public void setReleParamId(Long releParamId) {
        this.releParamId = releParamId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public GoodsTypeParam getParam() {
        return param;
    }

    public void setParam(GoodsTypeParam param) {
        this.param = param;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

}
