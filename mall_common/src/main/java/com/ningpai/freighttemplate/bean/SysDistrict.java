package com.ningpai.freighttemplate.bean;



import java.util.Date;

/**
 * 区县
 * @author ggn
 *
 */
public class SysDistrict {
    /*
     *区县ID
     */
    private Long districtId;
    /*
     *区县名称
     */
    private String districtName;
    /*
     *排序
     */
    private Long districtSort;
    /*
     *城市ID
     */
    private Long cityId;
    /*
     *创建时间
     */
    private Date createTime;
    /*
     *修改时间
     */
    private Date modifyTime;
    /*
     *是否删除
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

    public Long getDistrictSort() {
        return districtSort;
    }

    public void setDistrictSort(Long districtSort) {
        this.districtSort = districtSort;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
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
     * 社会修改时间
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
