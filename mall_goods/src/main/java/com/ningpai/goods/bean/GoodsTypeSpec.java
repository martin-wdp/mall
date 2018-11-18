package com.ningpai.goods.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * 类型关联规格实体
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 上午9:51:31
 * @version 1.0
 */
public class GoodsTypeSpec {
    /*
     * 主键ID
     */
    private Long typeSpecId;
    /*
     * 类型ID
     */
    private Long typeId;
    /*
     * 规格ID
     */
    @NotNull
    private Long specId;
    /*
     * 显示方式
     */
    @NotNull
    private Integer typeSpecShowtype;
    /*
     * 排序
     */
    @NotNull(message = "排序必须为正整数.")
    private Integer typeSpecSort;
    /*
     * 删除标记 0：未删除 1：已删除
     */
    private String typeSpecDelflag;
    /*
     * 创建人名称
     */
    private String typeSpecCreateName;
    /*
     * 创建时间 默认为插入时的当前时间
     */
    private Date typeSpecCreateTime;
    /*
     * 修改人名称
     */
    private String typeSpecModifiedName;
    /*
     * 修改时间 默认为修改时的当前时间
     */
    private Date typeSpecModifiedTime;
    /*
     * 删除人名称
     */
    private String typeSpecDelName;
    /*
     * 删除时间 默认为删除时的当前时间
     */
    private Date typeSpecDelTime;

    public Long getTypeSpecId() {
        return typeSpecId;
    }

    public void setTypeSpecId(Long typeSpecId) {
        this.typeSpecId = typeSpecId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public Integer getTypeSpecShowtype() {
        return typeSpecShowtype;
    }

    public void setTypeSpecShowtype(Integer typeSpecShowtype) {
        this.typeSpecShowtype = typeSpecShowtype;
    }

    public Integer getTypeSpecSort() {
        return typeSpecSort;
    }

    public void setTypeSpecSort(Integer typeSpecSort) {
        this.typeSpecSort = typeSpecSort;
    }

    public String getTypeSpecDelflag() {
        return typeSpecDelflag;
    }

    public void setTypeSpecDelflag(String typeSpecDelflag) {
        this.typeSpecDelflag = typeSpecDelflag;
    }

    public String getTypeSpecCreateName() {
        return typeSpecCreateName;
    }

    public void setTypeSpecCreateName(String typeSpecCreateName) {
        this.typeSpecCreateName = typeSpecCreateName;
    }
    /**
     * 获取创建时间
     * */
    public Date getTypeSpecCreateTime() {
        if (this.typeSpecCreateTime != null) {
            return new Date(this.typeSpecCreateTime.getTime());
        }
        return null;
    }
    /**
     * 设置创建时间
     * */
    public void setTypeSpecCreateTime(Date typeSpecCreateTime) {
        if (typeSpecDelTime != null) {
            Date tEmp = (Date) typeSpecDelTime.clone();
            if (tEmp != null) {
                this.typeSpecDelTime = tEmp;
            }
        }
    }

    public String getTypeSpecModifiedName() {
        return typeSpecModifiedName;
    }

    public void setTypeSpecModifiedName(String typeSpecModifiedName) {
        this.typeSpecModifiedName = typeSpecModifiedName;
    }
    /**
     * 获取修改时间
     * */
    public Date getTypeSpecModifiedTime() {
        if (this.typeSpecModifiedTime != null) {
            return new Date(this.typeSpecModifiedTime.getTime());
        }
        return null;
    }
    /**
     * 设置修改时间
     * */
    public void setTypeSpecModifiedTime(Date typeSpecModifiedTime) {
        if (typeSpecModifiedTime != null) {
            Date tEmp = (Date) typeSpecModifiedTime.clone();
            if (tEmp != null) {
                this.typeSpecModifiedTime = tEmp;
            }
        }
    }

    public String getTypeSpecDelName() {
        return typeSpecDelName;
    }

    public void setTypeSpecDelName(String typeSpecDelName) {
        this.typeSpecDelName = typeSpecDelName;
    }
    /**
     * 获取删除时间
     * */
    public Date getTypeSpecDelTime() {
        if (this.typeSpecDelTime != null) {
            return new Date(this.typeSpecDelTime.getTime());
        }
        return null;
    }
    /**
     * 设置删除时间
     * */
    public void setTypeSpecDelTime(Date typeSpecDelTime) {
        if (typeSpecDelTime != null) {
            Date tEmp = (Date) typeSpecDelTime.clone();
            if (tEmp != null) {
                this.typeSpecDelTime = tEmp;
            }
        }
    }
}
