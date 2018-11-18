package com.ningpai.temp.bean;

import java.util.Date;

/**
 * 分类导航
 * 
 * @author ggn
 *
 */
public class ClassifyBarCate {
    /**
     * ID
     */
    private Long classifybarCateId;
    /**
     * 分类导航ID
     */
    private Long classifybarId;
    /**
     * 分类ID
     */
    private Long cateId;
    /**
     * 分类名称
     */
    private String cateName;

    /**
     * 是否删除
     */
    private String delflag;

    /**
     * 创建人
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改人
     */
    private Long updateUserId;

    /**
     * 修改时间
     */
    private Date updateDate;
    /** 三级子商品分类ID */
    private String temp1;

    /**
     * 备用
     */
    private String temp2;
    /**
     * 备用
     */
    private String temp3;
    /**
     * 备用
     */
    private String temp4;
    /**
     * 备用
     */
    private String temp5;

    public Long getClassifybarCateId() {
        return classifybarCateId;
    }

    public void setClassifybarCateId(Long classifybarCateId) {
        this.classifybarCateId = classifybarCateId;
    }

    public Long getClassifybarId() {
        return classifybarId;
    }

    public void setClassifybarId(Long classifybarId) {
        this.classifybarId = classifybarId;
    }

    public Long getCateId() {
        return cateId;
    }

    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
