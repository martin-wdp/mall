package com.ningpai.mobile.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 实体类-移动版楼层
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月21日下午7:56:46
 */
public class MobStorey {
    /** 编号 */
    private Long mobStoreyId;
    /** 楼层名称 */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String storeyName;
    /** 模板ID */
    private Long tempId;
    /** 排序 */
    @NotNull
    private Integer sort;
    /** 是否启用 0：不启用 1：已启用 */
    private String userStatus;
    /** 删除标记 0：未删除 1：已删除 */
    private String delflag;
    /** 创建人ID */
    private Long createUserId;
    /** 创建时间 */
    private Date createDate;
    /** 修改人ID */
    private Long updateUserId;
    /** 修改时间 */
    private Date updateDate;
    /** 扩展字段1 */
    private String temp1;
    /** 扩展字段2 */
    private String temp2;
    /** 扩展字段3 */
    private String temp3;
    /** 扩展字段4 */
    private String temp4;
    /** 扩展字段5 */
    private String temp5;

    public Long getMobStoreyId() {
        return mobStoreyId;
    }

    public void setMobStoreyId(Long mobStoreyId) {
        this.mobStoreyId = mobStoreyId;
    }

    public String getStoreyName() {
        return storeyName;
    }

    public void setStoreyName(String storeyName) {
        this.storeyName = storeyName;
    }

    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

}
