/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.util;

/**
 * 分页参数Bean
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月18日 下午2:56:14
 * @version 1.0
 */
public class SelectBean {

    // 条件标记
    private String condition = "";
    // 查询文本
    private String searchText = "";

    // 查询条件（sku名称）
    private String goodsName;

    // 查询条件（sku编号）
    private String goodsNo;

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

}
