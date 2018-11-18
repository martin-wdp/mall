/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.goods.util;

import org.springframework.stereotype.Component;

/**
 * 查询商品的帮助Bean
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月14日 上午10:20:27
 * @version 1.0
 */
@Component("GoodsSearchBean")
public class GoodsSiteSearchBean {
    /**
     *标题
     * */
    private String title = "";
    /**
     * 是否显示
     * */
    private String showMode = "0";
    /**
     * 排序
     * */
    private String sort = "";
    /**
     * 是否显示库存
     * */
    private String showStock = "";
    /**
     * 地区id
     * */
    private Long distinctId;
    /**
    *店铺Id
     */
    private Long storeId;
    /**
     * 设置StoreId
     * */
    public void setStoreId(Long storeId){
        this.storeId=storeId;
    }
    /**
     * 获取StoreId
     * */
    public Long getStoreId(){
        return this.storeId;
    }
    /**
     * 获取Title
     * */
    public String getTitle() {
        return title;
    }
    /**
     * 设置Title
     * */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * 获取SHowMode
     * */
    public String getShowMode() {
        return showMode;
    }
    /**
     * 设置ShowMode
     * */
    public void setShowMode(String showMode) {
        this.showMode = showMode;
    }
    /**
     * 获取Sort
     * */
    public String getSort() {
        return sort;
    }
    /**
     * 设置Sort
     * */
    public void setSort(String sort) {
        this.sort = sort;
    }
    /**
     * 获取SHowStock
     * */
    public String getShowStock() {
        return showStock;
    }
    /**
     * 设置SHowStock
     * */
    public void setShowStock(String showStock) {
        this.showStock = showStock;
    }
    /**
     * 获取DistinctId
     * */
    public Long getDistinctId() {
        return distinctId;
    }
    /**
     * 设置DistinctId
     * */
    public void setDistinctId(Long distinctId) {
        this.distinctId = distinctId;
    }

}
