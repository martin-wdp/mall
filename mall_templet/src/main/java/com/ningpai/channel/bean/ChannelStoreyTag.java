package com.ningpai.channel.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 实体类-楼层标签
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午3:10:42
 */
public class ChannelStoreyTag {
    /** 编号 */
    private Long channelStoreyTagId;
    /** 楼层ID */
    private Long storeyId;
    /** 标签名称 */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String name;
    /** 是否显示标签品牌 */
    private String showTrademark;
    /** 是否显示标签广告 */
    private String showAdver;
    /** 排序 */
    private Long sort;
    /** 是否启用 */
    private String userStatus;
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
    /** 备用字段1 (模板ID) */
    private String temp1;
    /** 备用字段2 (频道ID) */
    private String temp2;
    /** 备用字段3 */
    private String temp3;
    /** 备用字段4 */
    private String temp4;
    /** 备用字段5 */
    private String temp5;

    public Long getChannelStoreyTagId() {
        return channelStoreyTagId;
    }

    public void setChannelStoreyTagId(Long channelStoreyTagId) {
        this.channelStoreyTagId = channelStoreyTagId;
    }

    public Long getStoreyId() {
        return storeyId;
    }

    public void setStoreyId(Long storeyId) {
        this.storeyId = storeyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShowTrademark() {
        return showTrademark;
    }

    public void setShowTrademark(String showTrademark) {
        this.showTrademark = showTrademark;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
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
     * 时间
     * 
     * @return Date
     */
    public Date getCreateDate() {
        if (this.createDate != null) {
            return new Date(this.createDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 时间
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
     * 时间
     * 
     * @return Date
     */
    public Date getUpdateDate() {
        if (this.updateDate != null) {
            return new Date(this.updateDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 时间
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

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getShowAdver() {
        return showAdver;
    }

    public void setShowAdver(String showAdver) {
        this.showAdver = showAdver;
    }

}
