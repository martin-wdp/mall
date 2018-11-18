/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.vo;

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
    //父分类id
    //private Long parentCatId;
    // 父分类
    private GoodsBreadCrumbVo parentCat;

    /*public void setParentCatId(Long parentCatId) {
        this.parentCatId = parentCatId;
    }*/

    /*public Long getParentCatId() {
        return parentCatId;
    }*/

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
