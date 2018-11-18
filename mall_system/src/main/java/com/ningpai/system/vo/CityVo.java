/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.system.vo;

import java.util.Date;
import java.util.List;

/**
 * 城市Vo
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月13日 下午2:43:55
 * @version 1.0
 */
public class CityVo {
    /* 
     * 主键ID
     */
    private Long cityId;
    /*
     *  城市名称
     */
    private String cityName;
    /*
     *  城市排序
     */
    private String citySort;
    /*
     *  省份ID
     */
    private Long provinceId;
    /*
     *  创建时间
     */
    private Date createTime;
    /* 
     * 修改时间
     */
    private Date modifyTime;
    /*
     *  删除标记
     */
    private String delFlag;
    /* 
     * 区县集合
     */
    private List<DistrictVo> districtvos;

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

    public List<DistrictVo> getDistrictvos() {
        return districtvos;
    }

    public void setDistrictvos(List<DistrictVo> districtvos) {
        this.districtvos = districtvos;
    }

    public String getCitySort() {
        return citySort;
    }

    public void setCitySort(String citySort) {
        this.citySort = citySort;
    }

}
