/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.system.vo;

import java.util.Date;
import java.util.List;

/**
 * 省份信息的VO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月13日 下午2:42:31
 * @version 1.0
 */
public class ProvinceVo {
    /* 
     * 主键ID
     */
    private Long provinceId;
    /*
     *  省份名称
     */
    private String provinceName;
    /*
     *  省份排序
     */
    private String provinceSort;
    /* 
     * 
     * 创建时间
     */
    private Date createTime;
    /*
     *  修改时间
     */
    private Date modifyTime;
    /*
     *  删除标记
     */
    private String delFlag;
    /* 
     * 省份下的所有城市的集合
     */
    private List<CityVo> cityvos;

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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

    public List<CityVo> getCityvos() {
        return cityvos;
    }

    public void setCityvos(List<CityVo> cityvos) {
        this.cityvos = cityvos;
    }

    public String getProvinceSort() {
        return provinceSort;
    }

    public void setProvinceSort(String provinceSort) {
        this.provinceSort = provinceSort;
    }

}
