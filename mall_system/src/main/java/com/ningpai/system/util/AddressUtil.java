/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.system.util;

/**
 * 地区帮助Bean
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月15日 上午10:07:55
 * @version 1.0
 */
public class AddressUtil {
    // 省份名称
    private String provinceName;
    // 城市名称
    private String cityName;
    // 区县名称
    private String districtName;
    // 街道名称
    private String streetName;
    // 地区的排序
    private String sort;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

}
