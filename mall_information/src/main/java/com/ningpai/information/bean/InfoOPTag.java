package com.ningpai.information.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 实体类-单页标签
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月10日下午1:25:48
 */
public class InfoOPTag {
    /** 单页标签Id */
    private Long infoopTagId;
    /** 单页标签名称 */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String name;
    /** 单页标签排序 */
    private Integer sort;
    /** 是否删除 0：未删除 1：已删除 */
    private String delflag;
    /** 创建人 */
    private Long createUserId;
    /** 创建时间 */
    private Date createDate;
    /** 更新人 */
    private Long updateUserId;
    /** 更新时间 */
    private Date updateDate;
    /** 模板ID */
    private String temp1;
    /** 扩展字段2 */
    private String temp2;
    /** 扩展字段3 */
    private String temp3;
    /** 扩展字段4 */
    private String temp4;
    /** 扩展字段5 */
    private String temp5;

    public Long getInfoopTagId() {
        return infoopTagId;
    }

    public void setInfoopTagId(Long infoopTagId) {
        this.infoopTagId = infoopTagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
    /**
     * 取得：创建时间
     *
     * @return Date 创建时间 {@link com.ningpai.information.bean.InfoOPTag#createDate}
     */
    public Date getCreateDate() {
        if (this.createDate != null) {
            return new Date(this.createDate.getTime());
        } else {
            return null;
        }

    }
    /**
     * 设置：创建时间
     *
     * @param createDate
     *            创建时间
     */
    public void setCreateDate(Date createDate) {
        if (createDate != null) {
            Date tEmp = (Date) createDate.clone();
            if (tEmp != null) {
                this.createDate = tEmp;
            }
        }
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
    /**
     * 取得：修改时间
     *
     * @return Date 修改时间 {@link com.ningpai.information.bean.InfoOPTag#updateDate}
     */
    public Date getUpdateDate() {
        if (this.updateDate != null) {
            return new Date(this.updateDate.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置：修改时间
     *
     * @param updateDate
     *            修改时间
     */
    public void setUpdateDate(Date updateDate) {
        if (updateDate != null) {
            Date tEmp = (Date) updateDate.clone();
            if (tEmp != null) {
                this.updateDate = tEmp;
            }
        }
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public String getTemp3() {
        return temp3;
    }

    public void setTemp3(String temp3) {
        this.temp3 = temp3;
    }

    public String getTemp4() {
        return temp4;
    }

    public void setTemp4(String temp4) {
        this.temp4 = temp4;
    }

    public String getTemp5() {
        return temp5;
    }

    public void setTemp5(String temp5) {
        this.temp5 = temp5;
    }
}
