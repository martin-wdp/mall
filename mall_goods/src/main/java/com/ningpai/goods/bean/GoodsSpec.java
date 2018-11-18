package com.ningpai.goods.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 商品规格Bean
 * 
 * @author NINGPAI-YuanKangKang rennn
 * @since 2013年12月18日 下午5:31:09
 * @version 1.0
 */
public class GoodsSpec {
    /*
     * 规格ID
     */
    private Long specId;
    /*
     * 规格名称
     */
    @Length(min = 2, max = 16, message = "规格名称 长度必须在 2字符 ~ 16字符之间.")
    @Pattern(regexp = "[^''\\[\\]\\<\\>?!]+", message = "规格名称 输入格式不正确.")
    private String specName;
    /*
     * 规格备注
     */
    @Length(min = 2, max = 16, message = "规格备注 长度必须在 2字符 ~ 16字符之间.")
    @Pattern(regexp = "[^''\\[\\]\\<\\>?!]+", message = "规格备注输入格式不正确.")
    private String specRemark;
    /*
     * 规格别名
     */
    private String specNickname;
    /*
     * 显示类型 0:文字 1：图片
     */
    private String specShowtype;
    /*
     * 显示方式 0:下拉 1：平铺
     */
    private String specShowmode;
    /*
     * 删除标记
     */
    private String specDelflag;
    /*
     * 创建人名称
     */
    private String specCreateName;
    /*
     * 创建时间
     */
    private Date specCreateTime;
    /*
     * 修改人名称
     */
    private String specModifiedName;
    /*
     * 修改时间
     */
    private Date specModifiedTime;
    /*
     * 删除人名称
     */
    private String specDelName;
    /*
     * 删除时间
     */
    private Date specDelTime;

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName.trim();
    }

    public String getSpecRemark() {
        return specRemark;
    }

    public void setSpecRemark(String specRemark) {
        this.specRemark = specRemark.trim();
    }

    public String getSpecNickname() {
        return specNickname;
    }

    public void setSpecNickname(String specNickname) {
        this.specNickname = specNickname.trim();
    }

    public String getSpecShowtype() {
        return specShowtype;
    }

    public void setSpecShowtype(String specShowtype) {
        this.specShowtype = specShowtype;
    }

    public String getSpecShowmode() {
        return specShowmode;
    }

    public void setSpecShowmode(String specShowmode) {
        this.specShowmode = specShowmode;
    }

    public String getSpecDelflag() {
        return specDelflag;
    }

    public void setSpecDelflag(String specDelflag) {
        this.specDelflag = specDelflag;
    }

    public String getSpecCreateName() {
        return specCreateName;
    }

    public void setSpecCreateName(String specCreateName) {
        this.specCreateName = specCreateName;
    }
    /**
     * 获取创建时间
     * */
    public Date getSpecCreateTime() {
        if (this.specCreateTime != null) {
            return new Date(this.specCreateTime.getTime());
        }
        return null;
    }
    /**
     * 设置创建时间
     * */
    public void setSpecCreateTime(Date specCreateTime) {
        if (specCreateTime != null) {
            Date tEmp = (Date) specCreateTime.clone();
            if (tEmp != null) {
                this.specCreateTime = tEmp;
            }
        }
    }

    public String getSpecModifiedName() {
        return specModifiedName;
    }

    public void setSpecModifiedName(String specModifiedName) {
        this.specModifiedName = specModifiedName;
    }
    /**
     * 获取修改时间
     * */
    public Date getSpecModifiedTime() {
        if (this.specModifiedTime != null) {
            return new Date(this.specModifiedTime.getTime());
        }
        return null;
    }
    /**
     * 设置修改时间
     * */
    public void setSpecModifiedTime(Date specModifiedTime) {
        if (specModifiedTime != null) {
            Date tEmp = (Date) specModifiedTime.clone();
            if (tEmp != null) {
                this.specModifiedTime = tEmp;
            }
        }
    }

    public String getSpecDelName() {
        return specDelName;
    }

    public void setSpecDelName(String specDelName) {
        this.specDelName = specDelName;
    }
    /**
     * 获取删除时间
     * */
    public Date getSpecDelTime() {
        if (this.specDelTime != null) {
            return new Date(this.specDelTime.getTime());
        }
        return null;
    }
    /**
     * 设置删除时间
     * */
    public void setSpecDelTime(Date specDelTime) {
        if (specDelTime != null) {
            Date tEmp = (Date) specDelTime.clone();
            if (tEmp != null) {
                this.specDelTime = tEmp;
            }
        }
    }
}
