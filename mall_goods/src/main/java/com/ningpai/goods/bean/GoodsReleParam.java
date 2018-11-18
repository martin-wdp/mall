package com.ningpai.goods.bean;

import java.util.Date;

import javax.validation.constraints.Pattern;

/**
 * 商品关联类型详细参数
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月24日 下午2:47:16
 * @version 1.0
 */
public class GoodsReleParam {
    /*
     * 主键ID
     */
    private Long releParamId;
    /*
     * 商品ID
     */
    private Long goodsId;
    /*
     * 详细参数ID
     */
    private Long paramId;
    /*
     * 详细参数值
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?!]+", message = "详细参数值输入不正确.")
    private String paramValue;
    /*
     * 创建人名称
     */
    private String paramCreateName;
    /*
     * 创建时间
     */
    private Date paramCreateTime;
    /*
     * 修改人名称
     */
    private String paramModifiedName;
    /*
     * 修改时间
     */
    private Date paramModifiedTime;
    /*
     * 删除人名称
     */
    private String paramDelName;
    /*
     * 删除时间
     */
    private Date paramDelTime;
    /*
     * 删除标记
     */
    private String paramDelflag;

    public Long getReleParamId() {
        return releParamId;
    }

    public void setReleParamId(Long releParamId) {
        this.releParamId = releParamId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getParamId() {
        return paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamCreateName() {
        return paramCreateName;
    }

    public void setParamCreateName(String paramCreateName) {
        this.paramCreateName = paramCreateName;
    }
    /**
     * 获取创建时间
     * */
    public Date getParamCreateTime() {
        if (this.paramCreateTime != null) {
            return new Date(this.paramCreateTime.getTime());
        }
        return null;
    }
    /**
     * 设置创建时间
     * */
    public void setParamCreateTime(Date paramCreateTime) {
        if (paramCreateTime != null) {
            Date tEmp = (Date) paramCreateTime.clone();
            if (tEmp != null) {
                this.paramCreateTime = tEmp;
            }
        }
    }

    public String getParamModifiedName() {
        return paramModifiedName;
    }

    public void setParamModifiedName(String paramModifiedName) {
        this.paramModifiedName = paramModifiedName;
    }
    /**
     * 获取修改时间
     * */
    public Date getParamModifiedTime() {
        if (this.paramModifiedTime != null) {
            return new Date(this.paramModifiedTime.getTime());
        }
        return null;
    }
    /**
     * 设置修改时间
     * */
    public void setParamModifiedTime(Date paramModifiedTime) {
        if (paramModifiedTime != null) {
            Date tEmp = (Date) paramModifiedTime.clone();
            if (tEmp != null) {
                this.paramModifiedTime = tEmp;
            }
        }
    }

    public String getParamDelName() {
        return paramDelName;
    }

    public void setParamDelName(String paramDelName) {
        this.paramDelName = paramDelName;
    }
    /**
     * 获取删除时间
     * */
    public Date getParamDelTime() {
        if (this.paramDelTime != null) {
            return new Date(this.paramDelTime.getTime());
        }
        return null;
    }
    /**
     * 设置删除时间
     * */
    public void setParamDelTime(Date paramDelTime) {
        if (paramDelTime != null) {
            Date tEmp = (Date) paramDelTime.clone();
            if (tEmp != null) {
                this.paramDelTime = tEmp;
            }
        }
    }

    public String getParamDelflag() {
        return paramDelflag;
    }

    public void setParamDelflag(String paramDelflag) {
        this.paramDelflag = paramDelflag;
    }
}
