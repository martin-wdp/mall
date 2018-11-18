package com.ningpai.goods.bean;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

/**
 * 商品标签类
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月14日 下午1:49:27
 * @version 1.0
 */
public class GoodsTag {
    /*
     * 标签ID
     */
    private Long tagId;
    /*
     * 标签名称
     */
    @Length(min = 2, max = 16, message = "标签名称 长度必须在 2字符 ~ 16字符之间.")
    @Pattern(regexp = "[^''\\[\\]\\<\\>?!]+", message = "标签名称 输入不正确.")
    private String tagName;
    /*
     * 标签图片
     */
    private String tagImg;
    /*
     * 删除标记
     */
    private String tagDelflag;
    /*
     * 创建人名称
     */
    private String tagCreateName;
    /*
     * 创建时间
     */
    private Date tagCreateTime;
    /*
     * 修改人名称
     */
    private String tagModifiedName;
    /*
     * 修改时间
     */
    private Date tagModifiedTime;
    /*
     * 删除人名称
     */
    private String tagDelName;
    /*
     * 删除时间
     */
    private Date tagDelTime;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName.trim();
    }

    public String getTagDelflag() {
        return tagDelflag;
    }

    public void setTagDelflag(String tagDelflag) {
        this.tagDelflag = tagDelflag;
    }

    public String getTagCreateName() {
        return tagCreateName;
    }

    public void setTagCreateName(String tagCreateName) {
        this.tagCreateName = tagCreateName;
    }
    /**
     * 获取创建时间
     * */
    public Date getTagCreateTime() {
        if (this.tagCreateTime != null) {
            return new Date(this.tagCreateTime.getTime());
        }
        return null;
    }
    /**
     * 设置创建时间
     * */
    public void setTagCreateTime(Date tagCreateTime) {
        if (tagCreateTime != null) {
            Date tEmp = (Date) tagCreateTime.clone();
            if (tEmp != null) {
                this.tagCreateTime = tEmp;
            }
        }
    }

    public String getTagModifiedName() {
        return tagModifiedName;
    }

    public void setTagModifiedName(String tagModifiedName) {
        this.tagModifiedName = tagModifiedName;
    }
    /**
     * 获取修改时间
     * */
    public Date getTagModifiedTime() {
        if (this.tagModifiedTime != null) {
            return new Date(this.tagModifiedTime.getTime());
        }
        return null;
    }
    /**
     * 设置修改时间
     * */
    public void setTagModifiedTime(Date tagModifiedTime) {
        if (tagModifiedTime != null) {
            Date tEmp = (Date) tagModifiedTime.clone();
            if (tEmp != null) {
                this.tagModifiedTime = tEmp;
            }
        }
    }

    public String getTagDelName() {
        return tagDelName;
    }

    public void setTagDelName(String tagDelName) {
        this.tagDelName = tagDelName;
    }
    /**
     * 获取删除时间
     * */
    public Date getTagDelTime() {
        if (this.tagDelTime != null) {
            return new Date(this.tagDelTime.getTime());
        }
        return null;
    }
    /**
     * 设置删除时间
     * */
    public void setTagDelTime(Date tagDelTime) {
        if (tagDelTime != null) {
            Date tEmp = (Date) tagDelTime.clone();
            if (tEmp != null) {
                this.tagDelTime = tEmp;
            }
        }
    }

    public String getTagImg() {
        return tagImg;
    }

    public void setTagImg(String tagImg) {
        this.tagImg = tagImg;
    }

}
