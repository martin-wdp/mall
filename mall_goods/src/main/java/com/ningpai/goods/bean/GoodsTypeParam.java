package com.ningpai.goods.bean;

import java.util.Date;

import javax.validation.constraints.Pattern;

/**
 * 商品类型详细参数实体
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 上午9:48:50
 * @version 1.0
 */
public class GoodsTypeParam {
    /*
     * 详细参数主键ID
     */
    private Long paramId;
    /*
     * 商品类型ID
     */
    private Long typeId;
    /*
     * 详细参数名称
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?!]+", message = "参数名输入不正确.")
    private String paramName;
    /*
     * 详细参数别名
     */
    private String paramNickname;
    /*
     * 详细参数 删除标记 0:未删除 1：已删除
     */
    private String paramDelflag;
    /*
     * 创建人名称
     */
    private String paramCreateName;
    /*
     * 创建时间 默认为当前插入时的时间
     */
    private Date paramCreateTime;
    /*
     * 修改人名称
     */
    private String paramModifiedName;
    /*
     * 修改时间 默认为修改时的当前时间
     */
    private Date paramModifiedTime;
    /*
     * 删除人名称
     */
    private String paramDelName;
    /*
     * 删除时间 默认为删除时的当前时间
     */
    private Date paramDelTime;

    public Long getParamId() {
        return paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName.trim();
    }

    public String getParamNickname() {
        return paramNickname;
    }

    public void setParamNickname(String paramNickname) {
        this.paramNickname = paramNickname.trim();
    }

    public String getParamDelflag() {
        return paramDelflag;
    }

    public void setParamDelflag(String paramDelflag) {
        this.paramDelflag = paramDelflag;
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
}
