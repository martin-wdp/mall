package com.ningpai.goods.bean;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

/**
 * 商品类型实体类
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 上午9:36:30
 * @version 1.0
 */
public class GoodsType {
    /*
     * 商品类型ID
     */
    private Long typeId;
    /*
     * 类型名称
     */
    @Length(min = 2, max = 32, message = "类型名称 长度必须在 2字符 ~ 32字符之间.")
    @Pattern(regexp = "[^''\\[\\]\\<\\>?!]+", message = "类型名称  输入不正确.")
    private String typeName;
    /*
     * 类型别名
     */
    private String typeNickname;
    /*
     * 是否是实物 0：不是实物 1：是实物
     */
    /*
     * @NotNull
     */
    private String typeIsreal;
    /*
     * 类型图片
     */
    private String typeImg;
    /*
     * 删除标记 0：未删除 1：已删除 默认0
     */
    private String typeDelflag;
    /*
     * 创建人名称
     */
    private String typeCreateName;
    /*
     * 创建时间 默认为插入时的当前时间
     */
    private Date typeCreateTime;
    /*
     * 修改人名称
     */
    private String typeModifiedName;
    /*
     * 修改时间 默认为修改时的当前时间
     */
    private Date typeModifiedTime;
    /*
     * 删除人名称
     */
    private String typeDelName;
    /*
     * 删除时间 默认为删除时的当前时间
     */
    private Date typeDelTime;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName.trim();
    }

    public String getTypeNickname() {
        return typeNickname;
    }

    public void setTypeNickname(String typeNickname) {
        this.typeNickname = typeNickname.trim();
    }

    public String getTypeIsreal() {
        return typeIsreal;
    }

    public void setTypeIsreal(String typeIsreal) {
        this.typeIsreal = typeIsreal;
    }

    public String getTypeDelflag() {
        return typeDelflag;
    }

    public void setTypeDelflag(String typeDelflag) {
        this.typeDelflag = typeDelflag;
    }

    public String getTypeCreateName() {
        return typeCreateName;
    }

    public void setTypeCreateName(String typeCreateName) {
        this.typeCreateName = typeCreateName;
    }
    /**
     * 获取创建时间
     * */
    public Date getTypeCreateTime() {
        if (this.typeCreateTime != null) {
            return new Date(this.typeCreateTime.getTime());
        }
        return null;
    }
    /**
     * 设置创建时间
     * */
    public void setTypeCreateTime(Date typeCreateTime) {
        if (typeCreateTime != null) {
            Date tEmp = (Date) typeCreateTime.clone();
            if (tEmp != null) {
                this.typeCreateTime = tEmp;
            }
        }
    }

    public String getTypeModifiedName() {
        return typeModifiedName;
    }

    public void setTypeModifiedName(String typeModifiedName) {
        this.typeModifiedName = typeModifiedName;
    }
    /**
     * 获取修改时间
     * */
    public Date getTypeModifiedTime() {
        if (this.typeModifiedTime != null) {
            return new Date(this.typeModifiedTime.getTime());
        }
        return null;
    }
    /**
     * 设置修改时间
     * */
    public void setTypeModifiedTime(Date typeModifiedTime) {
        if (typeModifiedTime != null) {
            Date tEmp = (Date) typeModifiedTime.clone();
            if (tEmp != null) {
                this.typeModifiedTime = tEmp;
            }
        }
    }

    public String getTypeDelName() {
        return typeDelName;
    }

    public void setTypeDelName(String typeDelName) {
        this.typeDelName = typeDelName;
    }
    /**
     * 获取删除时间
     * */
    public Date getTypeDelTime() {
        if (this.typeDelTime != null) {
            return new Date(this.typeDelTime.getTime());
        }
        return null;
    }
    /**
     * 设置删除时间
     * */
    public void setTypeDelTime(Date typeDelTime) {
        if (typeDelTime != null) {
            Date tEmp = (Date) typeDelTime.clone();
            if (tEmp != null) {
                this.typeDelTime = tEmp;
            }
        }
    }

    public String getTypeImg() {
        return typeImg;
    }

    public void setTypeImg(String typeImg) {
        this.typeImg = typeImg;
    }

}
