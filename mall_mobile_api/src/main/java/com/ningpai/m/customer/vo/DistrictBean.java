/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.vo;

/**
 * 地区bean
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月23日 上午11:12:30
 * @version
 */
public class DistrictBean {
    // 乡镇编号
    private Long districtId;
    // 乡镇名称
    private String districtName;
    // 城市编号
    private Long cityId;

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
