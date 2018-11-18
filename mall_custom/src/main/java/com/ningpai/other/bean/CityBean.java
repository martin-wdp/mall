package com.ningpai.other.bean;

import java.io.Serializable;

/**
 * 城市Bean
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月23日 上午11:12:01
 * @version
 */
public class CityBean implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = -5906633244764056549L;
    // 城市编号
    private Long cityId;
    // 城市名称
    private String cityName;
    // 省份编号
    private Long provinceId;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }
}
