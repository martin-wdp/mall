/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.site.goods.util;

/**
 * 查询商品的帮助Bean
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月14日 上午10:20:27
 * @version 1.0
 */
public class GoodsSiteSearchBean {

    /*
     *标题
     */
    private String title = "";
    /*
     *是否显示
     */
    private String showMode = "0";
    /*
     *排序
     */
    private String sort = "";
    /*
     *库存
     */
    private String showStock = "";
    /*
     *地区
     */
    private Long distinctId;
    
    

    public Long getDistinctId() {
        return distinctId;
    }

    public void setDistinctId(Long distinctId) {
        this.distinctId = distinctId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShowMode() {
        return showMode;
    }

    public void setShowMode(String showMode) {
        this.showMode = showMode;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getShowStock() {
        return showStock;
    }

    public void setShowStock(String showStock) {
        this.showStock = showStock;
    }

}
