/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.vo;

import com.ningpai.goods.bean.GoodsSpecDetail;

/**
 * 商品开启规格值Vo
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年7月1日 下午2:54:22
 * @version 1.0
 */
public class GoodsOpenSpecValueVo {
    /*
     *主键ID
      */
    private Long id;
    /*
     *商品ID
      */
    private Long goodsId;
    /*
     *规格ID
      */
    private Long specId;
    /*
     *规格值ID
      */
    private Long specValueId;
    /*
     *规格值实体
      */
    private GoodsSpecDetail specDetail;
    /*
     *删除标记
      */
    private String delFlag;
    /*
     *图片地址
      */
    private String imgUrl;
    /*
     *用户自定义的规格值
      */
    private String specValueRemark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public Long getSpecValueId() {
        return specValueId;
    }

    public void setSpecValueId(Long specValueId) {
        this.specValueId = specValueId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public GoodsSpecDetail getSpecDetail() {
        return specDetail;
    }

    public void setSpecDetail(GoodsSpecDetail specDetail) {
        this.specDetail = specDetail;
    }

    public String getSpecValueRemark() {
        return specValueRemark;
    }

    public void setSpecValueRemark(String specValueRemark) {
        this.specValueRemark = specValueRemark;
    }

}
