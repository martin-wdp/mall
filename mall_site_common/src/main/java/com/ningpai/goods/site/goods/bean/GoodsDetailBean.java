/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.site.goods.bean;

import java.util.List;

import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.site.goods.vo.GoodsCateVo;
import com.ningpai.goods.site.goods.vo.GoodsProductVo;
import com.ningpai.goods.vo.GoodsGroupVo;
import com.ningpai.goods.vo.GoodsReleExpandParamVo;
import com.ningpai.goods.vo.GoodsReleParamVo;
import com.ningpai.goods.vo.GoodsReleTagVo;

/**
 * 商品详情页Bean
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月10日 下午2:01:51
 * @version 1.0
 */
public final class GoodsDetailBean {
    /*
     *货品VO
     */
    private GoodsProductVo productVo;

    /*
     *分类VO
     */
    private GoodsCateVo cateVo;

    /*
     *商品品牌
     */
    private GoodsBrand brand;

    /*
     *商品关联的标签
     */
    private List<GoodsReleTagVo> tags;

    /*
     *商品关联的扩展参数
     */
    private List<GoodsReleExpandParamVo> expandPrams;

    /*
     *商品关联详细参数
     */
    private List<GoodsReleParamVo> param;

    /*
     *关联的货品
     */
    private List<GoodsProduct> releProductList;

    /*
     *获取同一商品组合关联的货品
     */
    private List<GoodsGroupVo> groupVos;

    public GoodsProductVo getProductVo() {
        return productVo;
    }

    public void setProductVo(GoodsProductVo productVo) {
        this.productVo = productVo;
    }

    public GoodsCateVo getCateVo() {
        return cateVo;
    }

    public void setCateVo(GoodsCateVo cateVo) {
        this.cateVo = cateVo;
    }

    public List<GoodsReleTagVo> getTags() {
        return tags;
    }

    public void setTags(List<GoodsReleTagVo> tags) {
        this.tags = tags;
    }

    public GoodsBrand getBrand() {
        return brand;
    }

    public void setBrand(GoodsBrand brand) {
        this.brand = brand;
    }

    public List<GoodsReleExpandParamVo> getExpandPrams() {
        return expandPrams;
    }

    public void setExpandPrams(List<GoodsReleExpandParamVo> expandPrams) {
        this.expandPrams = expandPrams;
    }

    public List<GoodsReleParamVo> getParam() {
        return param;
    }

    public void setParam(List<GoodsReleParamVo> param) {
        this.param = param;
    }

    public List<GoodsProduct> getReleProductList() {
        return releProductList;
    }

    public void setReleProductList(List<GoodsProduct> releProductList) {
        this.releProductList = releProductList;
    }

    public List<GoodsGroupVo> getGroupVos() {
        return groupVos;
    }

    public void setGroupVos(List<GoodsGroupVo> groupVos) {
        this.groupVos = groupVos;
    }

}
