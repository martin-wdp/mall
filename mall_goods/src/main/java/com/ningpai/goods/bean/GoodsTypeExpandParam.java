package com.ningpai.goods.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 商品类型扩展属性表
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 上午9:42:38
 * @version 1.0
 */
public class GoodsTypeExpandParam {
    /*
     * 主键ID
     */
    private Long expandparamId;
    /*
     * 类型ID
     */
    private Long typeId;
    /*
     * 扩展属性的名称
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?!]+", message = "扩展属性名输入不正确或未输入.")
    private String expandparamName;
    /*
     * 扩展属性别名
     */
    private String expandparamNickname;
    /*
     * 显示方式
     */
    @NotNull
    private Integer expandparamShowtype;
    /*
     * 是否显示 0：不显示 1：显示
     */
    @NotNull
    private String expanparamIsshow;
    /*
     * 排序
     */
    @NotNull(message = "排序必须为正整数.")
    private Integer expandparamSort;
    /*
     * 删除标记 0：未删除 1：已删除 默认0
     */
    private String expandparamDelflag;
    /*
     * 创建人名称
     */
    private String expandparamCreateName;
    /*
     * 创建时间 默认为插入时的当前时间
     */
    private Date expandparamCreateTime;
    /*
     * 修改人名称
     */
    private String expandparamModifiedName;
    /*
     * 修改时间 默认为修改时的时间
     */
    private Date expandparamModifiedTime;
    /*
     * 删除人名称
     */
    private String expandparamDelName;
    /*
     * 删除时间 默认为删除时的时间
     */
    private Date expandparamDelTime;

    public Long getExpandparamId() {
        return expandparamId;
    }

    public void setExpandparamId(Long expandparamId) {
        this.expandparamId = expandparamId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getExpandparamName() {
        return expandparamName;
    }

    public void setExpandparamName(String expandparamName) {
        this.expandparamName = expandparamName.trim();
    }

    public String getExpandparamNickname() {
        return expandparamNickname;
    }

    public void setExpandparamNickname(String expandparamNickname) {
        this.expandparamNickname = expandparamNickname.trim();
    }

    public Integer getExpandparamShowtype() {
        return expandparamShowtype;
    }

    public void setExpandparamShowtype(Integer expandparamShowtype) {
        this.expandparamShowtype = expandparamShowtype;
    }

    public String getExpanparamIsshow() {
        return expanparamIsshow;
    }

    public void setExpanparamIsshow(String expanparamIsshow) {
        this.expanparamIsshow = expanparamIsshow;
    }

    public Integer getExpandparamSort() {
        return expandparamSort;
    }

    public void setExpandparamSort(Integer expandparamSort) {
        this.expandparamSort = expandparamSort;
    }

    public String getExpandparamDelflag() {
        return expandparamDelflag;
    }

    public void setExpandparamDelflag(String expandparamDelflag) {
        this.expandparamDelflag = expandparamDelflag;
    }

    public String getExpandparamCreateName() {
        return expandparamCreateName;
    }

    public void setExpandparamCreateName(String expandparamCreateName) {
        this.expandparamCreateName = expandparamCreateName;
    }
    /**
     * 获取创建时间
     * */
    public Date getExpandparamCreateTime() {
        if (this.expandparamCreateTime != null) {
            return new Date(this.expandparamCreateTime.getTime());
        }
        return null;
    }
    /**
     * 设置创建时间
     * */
    public void setExpandparamCreateTime(Date expandparamCreateTime) {
        if (expandparamCreateTime != null) {
            Date tEmp = (Date) expandparamCreateTime.clone();
            if (tEmp != null) {
                this.expandparamCreateTime = tEmp;
            }
        }
    }

    public String getExpandparamModifiedName() {
        return expandparamModifiedName;
    }

    public void setExpandparamModifiedName(String expandparamModifiedName) {
        this.expandparamModifiedName = expandparamModifiedName;
    }
    /**
     * 获取修改时间
     * */
    public Date getExpandparamModifiedTime() {
        if (this.expandparamModifiedTime != null) {
            return new Date(this.expandparamModifiedTime.getTime());
        }
        return null;
    }
    /**
     * 设置修改时间
     * */
    public void setExpandparamModifiedTime(Date expandparamModifiedTime) {
        if (expandparamModifiedTime != null) {
            Date tEmp = (Date) expandparamModifiedTime.clone();
            if (tEmp != null) {
                this.expandparamModifiedTime = tEmp;
            }
        }
    }

    public String getExpandparamDelName() {
        return expandparamDelName;
    }

    public void setExpandparamDelName(String expandparamDelName) {
        this.expandparamDelName = expandparamDelName;
    }
    /**
     * 获取删除时间
     * */
    public Date getExpandparamDelTime() {
        if (this.expandparamDelTime != null) {
            return new Date(this.expandparamDelTime.getTime());
        }
        return null;
    }
    /**
     * 设置删除时间
     * */
    public void setExpandparamDelTime(Date expandparamDelTime) {
        if (expandparamDelTime != null) {
            Date tEmp = (Date) expandparamDelTime.clone();
            if (tEmp != null) {
                this.expandparamDelTime = tEmp;
            }
        }
    }
}
