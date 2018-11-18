/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.goods.vo;

/**
 * 面包屑辅助Bean
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月16日 上午10:48:56
 * @version 1.0
 */
public class GoodsBreadCrumbVo {
    // 分类ID
    private Long catId;
    // 分类名称
    private String catName;
    // 父分类
    private GoodsBreadCrumbVo parentCat;

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public GoodsBreadCrumbVo getParentCat() {
        return parentCat;
    }

    public void setParentCat(GoodsBreadCrumbVo parentCat) {
        this.parentCat = parentCat;
    }
}
