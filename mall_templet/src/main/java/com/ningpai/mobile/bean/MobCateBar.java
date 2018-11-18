package com.ningpai.mobile.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 实体类-移动版分类导航
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月19日上午11:00:48
 */
public class MobCateBar {
    /** 编号 */
    private Long cateBarId;
    /** 商品分类ID */
    private Long cateId;
    /** 分类导航名称 */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String name;
    /** 一级分类导航图片 */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String imgSrc;
    /** 层级 */
    private Integer grade;
    /** 排序 */
    private Integer sort;
    /** 父分类导航 */
    private Long parentId;
    /** 是否启用 0：不启用；1：启用 */
    private String isUsing;
    /** 删除标记 0：未删除；1：已删除 */
    private String delflag;
    /** 创建人ID */
    private Long createUserId;
    /** 创建时间 */
    private Date createDate;
    /** 修改人ID */
    private Long updateUserId;
    /** 修改时间 */
    private Date updateDate;
    /** 自定义链接地址 */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String temp1;
    /** 是否启用自定义链接 */
    @Pattern(regexp = "\\d+|()")
    private String temp2;
    /** 是否单一类目 */
    @Pattern(regexp = "\\d+|()")
    private String temp3;

    private String temp4;

    private String temp5;

    public Long getCateBarId() {
        return cateBarId;
    }

    public void setCateBarId(Long cateBarId) {
        this.cateBarId = cateBarId;
    }

    public Long getCateId() {
        return cateId;
    }

    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(String isUsing) {
        this.isUsing = isUsing;
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
