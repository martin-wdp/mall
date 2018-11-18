package com.ningpai.uploadfileset.bean;

import java.util.Date;

/**
 * 实体类-上传文件设置类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月16日下午4:02:42
 */
public class UploadFileSet {
    /** 编号 */
    private Long uploadfilesetId;
    /** 可上传的文件大小 */
    private Long maxSize;
    /** 可上传的文件后缀，用“,”分隔 */
    private String suffixArray;
    /** 创建时间 */
    private Date createDate;
    /** 更新时间 */
    private Date updateDate;
    /** 扩展字段1 */
    private String temp1;
    /** 扩展字段2 */
    private String temp2;
    /** 扩展字段3 */
    private String temp3;
    /** 扩展字段4 */
    private String temp4;
    /** 扩展字段5 */
    private String temp5;

    public Long getUploadfilesetId() {
        return uploadfilesetId;
    }

    public void setUploadfilesetId(Long uploadfilesetId) {
        this.uploadfilesetId = uploadfilesetId;
    }

    public Long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Long maxSize) {
        this.maxSize = maxSize;
    }

    public String getSuffixArray() {
        return suffixArray;
    }

    public void setSuffixArray(String suffixArray) {
        this.suffixArray = suffixArray;
    }

    /**
     * 获得创建时间
     * @return
     */
    public Date getCreateDate() {
        return createDate==null?null:(Date) createDate.clone();
    }

    /**
     * 设置创建时间
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate == null ? null : (Date) createDate.clone();
    }

    /**
     * 获得修改时间
     * @return
     */
    public Date getUpdateDate() {
        return updateDate==null?null:(Date) updateDate.clone();
    }

    /**
     * 设置修改时间
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate == null ? null : (Date) updateDate.clone();
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public String getTemp3() {
        return temp3;
    }

    public void setTemp3(String temp3) {
        this.temp3 = temp3;
    }

    public String getTemp4() {
        return temp4;
    }

    public void setTemp4(String temp4) {
        this.temp4 = temp4;
    }

    public String getTemp5() {
        return temp5;
    }

    public void setTemp5(String temp5) {
        this.temp5 = temp5;
    }
}
