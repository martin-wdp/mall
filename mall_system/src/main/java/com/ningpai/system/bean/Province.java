package com.ningpai.system.bean;

import java.util.Date;

/**
 * 地区管理——省份类
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月12日 下午3:05:05
 * @version 1.0
 */
public class Province {
    /*
     *  主键ID
     */
    private Long provinceId;
    /*
     * 省份名称
     */
    private String provinceName;
    /*
     * 省份排序
     */
    private String provinceSort;
    /*
     *  创建时间
     */
    private Date createTime;
    /*
     *  修改时间
     */
    private Date modifyTime;
    /*
     * 删除标记
     */
    private String delFlag;

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

    public String getProvinceSort() {
        return provinceSort;
    }

    public void setProvinceSort(String provinceSort) {
        this.provinceSort = provinceSort;
    }

}
