/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.vo;

import java.util.List;

import com.ningpai.goods.bean.GoodsSpec;

/**
 * 商品开启规格的Vo
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年7月1日 下午3:05:18
 * @version 1.0
 */
public class GoodsOpenSpecVo {
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
     *删除标记 0:未删除 1:已删除
      */
    private String delFlag;
    /*
     *商品规格
      */
    private GoodsSpec spec;
    /*
     *商品开启的规格值的集合
      */
    private List<GoodsOpenSpecValueVo> specValList;

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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public GoodsSpec getSpec() {
        return spec;
    }

    public void setSpec(GoodsSpec spec) {
        this.spec = spec;
    }

    public List<GoodsOpenSpecValueVo> getSpecValList() {
        return specValList;
    }

    public void setSpecValList(List<GoodsOpenSpecValueVo> specValList) {
        this.specValList = specValList;
    }

}
