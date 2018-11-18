package com.ningpai.goods.bean;

import java.util.Date;

import javax.validation.constraints.Pattern;

/**
 * 商品关联扩展属性值
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月24日 下午2:40:13
 * @version 1.0
 */
public class GoodsReleExpandParam {
    /*
     * 主键ID
     */
    private Long releExpandparamId;
    /*
     * 商品ID
     */
    private Long goodsId;
    /*
     * 扩展属性ID
     */
    private Long expandparamId;
    /*
     * 扩展属性值ID
     */
    private Long expangparamValueId;
    /*
     * 扩展属性值
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?!]+", message = "属性值输入不正确.")
    private String expandparamValueName;
    /*
     * 创建人名称
     */
    private String releExpandparamCreateName;
    /*
     * 创建时间
     */
    private Date releExpandparamCreateTime;
    /*
     * 修改人名称
     */
    private String releExpandparamModifiedName;
    /*
     * 修改时间
     */
    private Date releExpandparamModifiedTime;
    /*
     * 删除人名称
     */
    private String releExpandparamDelName;
    /*
     * 删除时间
     */
    private Date releExpandparamDelTime;
    /*
     * 删除标记
     */
    private String releExpandparamDelflag;

    public Long getReleExpandparamId() {
        return releExpandparamId;
    }

    public void setReleExpandparamId(Long releExpandparamId) {
        this.releExpandparamId = releExpandparamId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getExpandparamId() {
        return expandparamId;
    }

    public void setExpandparamId(Long expandparamId) {
        this.expandparamId = expandparamId;
    }

    public Long getExpangparamValueId() {
        return expangparamValueId;
    }

    public void setExpangparamValueId(Long expangparamValueId) {
        this.expangparamValueId = expangparamValueId;
    }

    public String getExpandparamValueName() {
        return expandparamValueName;
    }

    public void setExpandparamValueName(String expandparamValueName) {
        this.expandparamValueName = expandparamValueName;
    }

    public String getReleExpandparamCreateName() {
        return releExpandparamCreateName;
    }

    public void setReleExpandparamCreateName(String releExpandparamCreateName) {
        this.releExpandparamCreateName = releExpandparamCreateName;
    }
    /**
     * 获取创建时间
     * */
    public Date getReleExpandparamCreateTime() {
        if (this.releExpandparamCreateTime != null) {
            return new Date(this.releExpandparamCreateTime.getTime());
        }
        return null;
    }
    /**
     * 设置创建时间
     * */
    public void setReleExpandparamCreateTime(Date releExpandparamCreateTime) {
        if (releExpandparamCreateTime != null) {
            Date tEmp = (Date) releExpandparamCreateTime.clone();
            if (tEmp != null) {
                this.releExpandparamCreateTime = tEmp;
            }
        }
    }

    public String getReleExpandparamModifiedName() {
        return releExpandparamModifiedName;
    }

    public void setReleExpandparamModifiedName(
            String releExpandparamModifiedName) {
        this.releExpandparamModifiedName = releExpandparamModifiedName;
    }
    /**
     * 获取修改时间
     * */
    public Date getReleExpandparamModifiedTime() {
        if (this.releExpandparamModifiedTime != null) {
            return new Date(this.releExpandparamModifiedTime.getTime());
        }
        return null;
    }
    /**
     * 设置修改时间
     * */
    public void setReleExpandparamModifiedTime(Date releExpandparamModifiedTime) {
        if (releExpandparamModifiedTime != null) {
            Date tEmp = (Date) releExpandparamModifiedTime.clone();
            if (tEmp != null) {
                this.releExpandparamModifiedTime = tEmp;
            }
        }
    }

    public String getReleExpandparamDelName() {
        return releExpandparamDelName;
    }

    public void setReleExpandparamDelName(String releExpandparamDelName) {
        this.releExpandparamDelName = releExpandparamDelName;
    }
    /**
     * 获取删除时间
     * */
    public Date getReleExpandparamDelTime() {
        if (this.releExpandparamDelTime != null) {
            return new Date(this.releExpandparamDelTime.getTime());
        }
        return null;
    }
    /**
     * 设置删除时间
     * */
    public void setReleExpandparamDelTime(Date releExpandparamDelTime) {
        if (releExpandparamDelTime != null) {
            Date tEmp = (Date) releExpandparamDelTime.clone();
            if (tEmp != null) {
                this.releExpandparamDelTime = tEmp;
            }
        }
    }

    public String getReleExpandparamDelflag() {
        return releExpandparamDelflag;
    }

    public void setReleExpandparamDelflag(String releExpandparamDelflag) {
        this.releExpandparamDelflag = releExpandparamDelflag;
    }
}
