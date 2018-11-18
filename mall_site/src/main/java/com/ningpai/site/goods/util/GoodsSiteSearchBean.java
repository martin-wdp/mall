/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 查询商品的帮助Bean
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月14日 上午10:20:27
 * @version 1.0
 */
@Component("GoodsSearchBean")
public class GoodsSiteSearchBean {
    private String title = "";
    private String showMode = "0";
    private String sort = "";
    private String showStock = "";
    private Long distinctId;
    private BigDecimal priceMin;
    private BigDecimal priceMax;

    public BigDecimal getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(BigDecimal priceMin) {
        this.priceMin = priceMin;
    }

    public BigDecimal getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(BigDecimal priceMax) {
        this.priceMax = priceMax;
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

    public Long getDistinctId() {
        return distinctId;
    }

    public void setDistinctId(Long distinctId) {
        this.distinctId = distinctId;
    }

}
