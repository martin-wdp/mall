package com.ningpai.system.bean;

import java.util.Date;

/**
 * 区县实体
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月13日 上午10:19:38
 * @version 1.0
 */
public class District {
    /*
     *  区县ID
     */
    private Long districtId;
    /*
     *  区县名称
     */
    private String districtName;
    /*
     *  区县排序
     */
    private String districtSort;
    /*
     *  城市ID
     */
    private Long cityId;
    /*
     *  创建时间
     */
    private Date createTime;
    /* 
     * 修改时间
     */
    private Date modifyTime;
    /* 
     * 删除标记
     */
    private String delFlag;

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
    /**
     * 时间
     * @return
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 时间
     * @return
     */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                this.createTime = tEmp;
            }
        }
    }
    /**
     * 时间
     * @return
     */
    public Date getModifyTime() {
        if (this.modifyTime != null) {
            return new Date(this.modifyTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 时间
     * @return
     */
    public void setModifyTime(Date modifyTime) {
        if (modifyTime != null) {
            Date tEmp = (Date) modifyTime.clone();
            if (tEmp != null) {
                this.modifyTime = tEmp;
            }
        }
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDistrictSort() {
        return districtSort;
    }

    public void setDistrictSort(String districtSort) {
        this.districtSort = districtSort;
    }

}
