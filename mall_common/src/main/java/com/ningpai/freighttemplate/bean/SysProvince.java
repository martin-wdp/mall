package com.ningpai.freighttemplate.bean;


import java.util.Date;
import java.util.List;

/**
 * 省 2014-12-17
 * @author ggn
 *
 */
public class SysProvince {
    /*
     *省ID
     */
    private Long provinceId;
    /*
     *名称
     */
    private String provinceName;
    /*
     *排序
     */
    private Long provinceSort;
    /*
     *创建时间
     */
    private Date createTime;
    /*
     *修改时间
     */
    private Date modifyTime;
    /*
     *delFlag
     */
    private String delFlag;
    /*
     *城市
     */
    private List<SysCity> cityList;
    

    public List<SysCity> getCityList() {
        return cityList;
    }

    public void setCityList(List<SysCity> cityList) {
        this.cityList = cityList;
    }

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

    public Long getProvinceSort() {
        return provinceSort;
    }

    public void setProvinceSort(Long provinceSort) {
        this.provinceSort = provinceSort;
    }

    /**
     * 获取创建时间
     * */
    public Date getCreateTime() {
        return (Date) createTime.clone();
    }
    /**
     * 设置创建时间
     * */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime==null?null: (Date) createTime.clone();
    }
    /**
     * 获取修改时间
     * */
    public Date getModifyTime() {
        return (Date) modifyTime.clone();
    }
    /**
     * 设置修改时间
     * */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime==null?null: (Date) modifyTime.clone();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
