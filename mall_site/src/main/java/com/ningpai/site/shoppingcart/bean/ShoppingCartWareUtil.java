/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.shoppingcart.bean;

/**
 * 地区工具类
 * 
 * @author NINGPAI-LIH
 * @since 2014年7月8日10:40:05
 * 
 */
public class ShoppingCartWareUtil {
    // 地区id
    private Long districtId;

    // 省名称
    private String provinceName;

    // 市名称
    private String cityName;

    // 区名称
    private String distinctName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistinctName() {
        return distinctName;
    }

    public void setDistinctName(String distinctName) {
        this.distinctName = distinctName;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
