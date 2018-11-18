package com.ningpai.goods.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

/**
 * 类型扩展属性值
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 上午9:46:50
 * @version 1.0
 */
public class GoodsTypeExpandParamValue {
    /*
     * 扩展属性值ID
     */
    private Long expandparamValueId;
    /*
     * 类型扩展属性ID
     */
    private Long expandparamId;
    /*
     * 属性值名称
     */
    @Length(min = 2, max = 16, message = "属性值 输入不正确")
    @Pattern(regexp = "[^''\\[\\]\\<\\>?!]+")
    private String expandparamValueName;
    /*
     * 属性值排序
     */
    @NotNull(message = "排序必须为正整数.")
    private Integer expandparamValueSort;
    /*
     * 属性值删除标记 0：未删除 1：已删除 默认0
     */
    private String expandparamValueDelflag;
    /*
     * 创建人名称
     */
    private String expandparamValueCreateName;
    /*
     * 创建时间 默认为插入时的当前时间
     */
    private Date expandparamValueCreateTime;
    /*
     * 修改人名称
     */
    private String expandparamValueModifiedName;
    /*
     * 修改时间 默认为修改时的当前时间
     */
    private Date expandparamValueModifiedTime;
    /*
     * 删除人名称
     */
    private String expandparamValueDelName;
    /*
     * 删除时间 默认为删除时的当前时间
     */
    private Date expandparamValueDelTime;

    public Long getExpandparamValueId() {
        return expandparamValueId;
    }

    public void setExpandparamValueId(Long expandparamValueId) {
        this.expandparamValueId = expandparamValueId;
    }

    public Long getExpandparamId() {
        return expandparamId;
    }

    public void setExpandparamId(Long expandparamId) {
        this.expandparamId = expandparamId;
    }

    public String getExpandparamValueName() {
        return expandparamValueName;
    }

    public void setExpandparamValueName(String expandparamValueName) {
        this.expandparamValueName = expandparamValueName.trim();
    }

    public Integer getExpandparamValueSort() {
        return expandparamValueSort;
    }

    public void setExpandparamValueSort(Integer expandparamValueSort) {
        this.expandparamValueSort = expandparamValueSort;
    }

    public String getExpandparamValueDelflag() {
        return expandparamValueDelflag;
    }

    public void setExpandparamValueDelflag(String expandparamValueDelflag) {
        this.expandparamValueDelflag = expandparamValueDelflag;
    }

    public String getExpandparamValueCreateName() {
        return expandparamValueCreateName;
    }

    public void setExpandparamValueCreateName(String expandparamValueCreateName) {
        this.expandparamValueCreateName = expandparamValueCreateName;
    }
    /**
     * 获取创建时间
     * */
    public Date getExpandparamValueCreateTime() {
        if (this.expandparamValueCreateTime != null) {
            return new Date(this.expandparamValueCreateTime.getTime());
        }
        return null;
    }
    /**
     * 设置创建时间
     * */
    public void setExpandparamValueCreateTime(Date expandparamValueCreateTime) {
        if (expandparamValueCreateTime != null) {
            Date tEmp = (Date) expandparamValueCreateTime.clone();
            if (tEmp != null) {
                this.expandparamValueCreateTime = tEmp;
            }
        }
    }

    public String getExpandparamValueModifiedName() {
        return expandparamValueModifiedName;
    }

    public void setExpandparamValueModifiedName(
            String expandparamValueModifiedName) {
        this.expandparamValueModifiedName = expandparamValueModifiedName;
    }
    /**
     * 获取修改时间
     * */
    public Date getExpandparamValueModifiedTime() {
        if (this.expandparamValueModifiedTime != null) {
            return new Date(this.expandparamValueModifiedTime.getTime());
        }
        return null;
    }
    /**
     * 设置修改时间
     * */
    public void setExpandparamValueModifiedTime(
            Date expandparamValueModifiedTime) {
        if (expandparamValueModifiedTime != null) {
            Date tEmp = (Date) expandparamValueModifiedTime.clone();
            if (tEmp != null) {
                this.expandparamValueModifiedTime = tEmp;
            }
        }
    }

    public String getExpandparamValueDelName() {
        return expandparamValueDelName;
    }

    public void setExpandparamValueDelName(String expandparamValueDelName) {
        this.expandparamValueDelName = expandparamValueDelName;
    }
    /**
     * 获取删除时间
     * */
    public Date getExpandparamValueDelTime() {
        if (this.expandparamValueDelTime != null) {
            return new Date(this.expandparamValueDelTime.getTime());
        }
        return null;
    }
    /**
     * 设置删除时间
     * */
    public void setExpandparamValueDelTime(Date expandparamValueDelTime) {
        if (expandparamValueDelTime != null) {
            Date tEmp = (Date) expandparamValueDelTime.clone();
            if (tEmp != null) {
                this.expandparamValueDelTime = tEmp;
            }
        }
    }
}
