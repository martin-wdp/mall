package com.ningpai.information.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 实体类-文章自定义属性
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月26日下午4:56:23
 */
public class InfoUserDefined {
    /** 编号 */
    private Long infoUdId;
    /** 属性名称 */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String name;
    /** 排序 */
    private Integer sort;
    /** 是否删除 */
    private String delflag;
    /** 创建人ID */
    private Long createUserId;
    /** 创建时间 */
    private Date createDate;
    /** 修改人ID */
    private Long updateUserId;
    /** 修改时间 */
    private Date updateDate;

    public Long getInfoUdId() {
        return infoUdId;
    }

    public void setInfoUdId(Long infoUdId) {
        this.infoUdId = infoUdId;
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
     * @return Date 创建时间 {@link com.ningpai.information.bean.InfoUserDefined#createDate}
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
     * @return Date 修改时间 {@link com.ningpai.information.bean.InfoUserDefined#updateDate}
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

}
