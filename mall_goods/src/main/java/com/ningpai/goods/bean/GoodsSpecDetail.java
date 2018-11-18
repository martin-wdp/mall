package com.ningpai.goods.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

/**
 * 商品规格值实体
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月18日 下午3:54:32
 * @version 1.0
 */
public class GoodsSpecDetail {
    /*
     * 规格值ID
     */
    private Long specDetailId;
    /*
     * 规格ID
     */
    private Long specId;
    /*
     * 规格值名称
     */
    @Length(min = 2, max = 32, message = "规格值名称 长度必须在 2字符 ~ 32字符之间.")
    @Pattern(regexp = "[^''\\[\\]\\<\\>?!]+", message = "规格值名称 输入不正确.")
    private String specDetailName;
    /*
     * 规格值别名
     */
    private String specDetailNickname;
    /*
     * 规格值图片
     */
    private String specDetailImg;
    /*
     * 规格值排序
     */
    @NotNull(message = "排序必须为正整数.")
    private Integer specDetailSort;
    /*
     * 规格值删除标记
     */
    private String specDetailDelflag;
    /*
     * 创建人名称
     */
    private String specDetailCreateName;
    /*
     * 创建时间
     */
    private Date specDetailCreateTime;
    /*
     * 修改人名称
     */
    private String specDetailModifiedName;
    /*
     * 修改时间
     */
    private Date specDetailModifiedTime;
    /*
     * 删除人名称
     */
    private String specDetailDelName;
    /*
     * 删除时间
     */
    private Date specDetailDelTime;

    public Long getSpecDetailId() {
        return specDetailId;
    }

    public void setSpecDetailId(Long specDetailId) {
        this.specDetailId = specDetailId;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public String getSpecDetailName() {
        return specDetailName;
    }

    public void setSpecDetailName(String specDetailName) {
        this.specDetailName = specDetailName.trim();
    }

    public String getSpecDetailNickname() {
        return specDetailNickname;
    }

    public void setSpecDetailNickname(String specDetailNickname) {
        this.specDetailNickname = specDetailNickname.trim();
    }

    public String getSpecDetailImg() {
        return specDetailImg;
    }

    public void setSpecDetailImg(String specDetailImg) {
        this.specDetailImg = specDetailImg;
    }

    public Integer getSpecDetailSort() {
        return specDetailSort;
    }

    public void setSpecDetailSort(Integer specDetailSort) {
        this.specDetailSort = specDetailSort;
    }

    public String getSpecDetailDelflag() {
        return specDetailDelflag;
    }

    public void setSpecDetailDelflag(String specDetailDelflag) {
        this.specDetailDelflag = specDetailDelflag;
    }

    public String getSpecDetailCreateName() {
        return specDetailCreateName;
    }

    public void setSpecDetailCreateName(String specDetailCreateName) {
        this.specDetailCreateName = specDetailCreateName;
    }
    /**
     * 获取创建时间
     * */
    public Date getSpecDetailCreateTime() {
        if (this.specDetailCreateTime != null) {
            return new Date(this.specDetailCreateTime.getTime());
        }
        return null;
    }
    /**
     * 设置创建时间
     * */
    public void setSpecDetailCreateTime(Date specDetailCreateTime) {
        if (specDetailCreateTime != null) {
            Date tEmp = (Date) specDetailCreateTime.clone();
            if (tEmp != null) {
                this.specDetailCreateTime = tEmp;
            }
        }
    }

    public String getSpecDetailModifiedName() {
        return specDetailModifiedName;
    }

    public void setSpecDetailModifiedName(String specDetailModifiedName) {
        this.specDetailModifiedName = specDetailModifiedName;
    }
    /**
     * 获取修改时间
     * */
    public Date getSpecDetailModifiedTime() {
        if (this.specDetailModifiedTime != null) {
            return new Date(this.specDetailModifiedTime.getTime());
        }
        return null;
    }
    /**
     * 设置修改时间
     * */
    public void setSpecDetailModifiedTime(Date specDetailModifiedTime) {
        if (specDetailModifiedTime != null) {
            Date tEmp = (Date) specDetailModifiedTime.clone();
            if (tEmp != null) {
                this.specDetailModifiedTime = tEmp;
            }
        }
    }

    public String getSpecDetailDelName() {
        return specDetailDelName;
    }

    public void setSpecDetailDelName(String specDetailDelName) {
        this.specDetailDelName = specDetailDelName;
    }
    /**
     * 获取删除时间
     * */
    public Date getSpecDetailDelTime() {
        if (this.specDetailDelTime != null) {
            return new Date(this.specDetailDelTime.getTime());
        }
        return null;
    }
    /**
     * 设置删除时间
     * */
    public void setSpecDetailDelTime(Date specDetailDelTime) {
        if (specDetailDelTime != null) {
            Date tEmp = (Date) specDetailDelTime.clone();
            if (tEmp != null) {
                this.specDetailDelTime = tEmp;
            }
        }
    }
}
