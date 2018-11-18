/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.bean;

import com.ningpai.comment.bean.Comment;
import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.vo.*;
import com.ningpai.marketing.bean.Marketing;
import com.ningpai.site.goods.vo.GoodsCateVo;
import com.ningpai.site.goods.vo.GoodsProductVo;
import com.ningpai.thirdaudit.bean.StoreInfo;

import java.util.List;

/**
 * 商品详情页Bean
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月10日 下午2:01:51
 * @version 1.0
 */
public final class GoodsDetailBean {
    // 货品VO
    private GoodsProductVo productVo;
    // 分类VO
    private GoodsCateVo cateVo;
    // 商品品牌
    private GoodsBrand brand;
    // 商品关联的标签
    private List<GoodsReleTagVo> tags;
    // 商品关联的扩展参数
    private List<GoodsReleExpandParamVo> expandPrams;
    // 商品关联详细参数
    private List<GoodsReleParamVo> param;
    // 关联的货品
    private List<GoodsProduct> releProductList;
    // 获取同一商品组合关联的货品
    private List<GoodsGroupVo> groupVos;
    // 服务支持集合
    private List<GoodsProductSuppVo> suppList;
    //促销信息集合
    private List<Marketing> marketList;
    //商铺信息
    private StoreInfo storeInfo;
    //评分信息
    private Comment comment;
    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public StoreInfo getStoreInfo() {
        return storeInfo;
    }

    public void setStoreInfo(StoreInfo storeInfo) {
        this.storeInfo = storeInfo;
    }

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

    public List<GoodsProductSuppVo> getSuppList() {
        return suppList;
    }

    public void setSuppList(List<GoodsProductSuppVo> suppList) {
        this.suppList = suppList;
    }

    public List<Marketing> getMarketList() {
        return marketList;
    }

    public void setMarketList(List<Marketing> marketList) {
        this.marketList = marketList;
    }

}
