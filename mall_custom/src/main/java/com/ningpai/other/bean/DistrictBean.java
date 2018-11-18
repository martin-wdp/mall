package com.ningpai.other.bean;

import java.io.Serializable;

/**
 * 地区bean
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月23日 上午11:12:30
 * @version
 */
public class DistrictBean implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 6735080242616634895L;
    // 乡镇编号
    private Long districtId;
    // 乡镇名称
    private String districtName;
    // 城市编号
    private Long cityId;

    private boolean chkDisabled;

    public boolean isChkDisabled() {
        return chkDisabled;
    }

    public void setChkDisabled(boolean chkDisabled) {
        this.chkDisabled = chkDisabled;
    }

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
