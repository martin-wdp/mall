package com.ningpai.channel.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 实体类-频道广告
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月1日下午4:03:36
 */
public class ChannelAdver {
    /*
     * 频道广告ID
     */
    private Long channelAdverId;
    /**
     * 频道ID
     */
    private Long channelId;
    /**
     * 模板ID
     */
    private Long tempId;
    /**
     * 楼层ID
     */
    private Long floorId;
    /** 楼层标签ID */
    private Long floorTagId;
    /** 广告标题 */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String adverName;
    /** 广告图片路径 */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String adverPath;
    /** 广告链接 */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String adverHref;
    /**
     * 广告分类-关联系统字典表的广告分类 <br/>
     * 157：轮播大图片 159：轮播小图片 161：首页图片
     */
    private Long atId;
    /** 广告排序 */
    private Integer adverSort;
    /**
     * 广告类型-关联系统字典表的广告类型<br/>
     * 151：频道广告
     */
    private Long adverType;
    /** 是否启用 */
    private String userStatus;
    /** 广告描述 */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String des;

    private String delflag;

    private Long createUserId;

    private Date createDate;

    private Long updateUserId;

    private Date updateDate;
    /** 分类导航ID */
    private String temp1;
    /** 副标题 */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String temp2;
    /** 是否分类导航父框广告 */
    private String temp3;
    /** 第三方ID */
    private String temp4;

    private String temp5;

    public Long getChannelAdverId() {
        return channelAdverId;
    }

    public void setChannelAdverId(Long channelAdverId) {
        this.channelAdverId = channelAdverId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public String getAdverName() {
        return adverName;
    }

    public void setAdverName(String adverName) {
        this.adverName = adverName;
    }

    public String getAdverPath() {
        return adverPath;
    }

    public void setAdverPath(String adverPath) {
        this.adverPath = adverPath;
    }

    public String getAdverHref() {
        return adverHref;
    }

    public void setAdverHref(String adverHref) {
        this.adverHref = adverHref;
    }

    public Long getAtId() {
        return atId;
    }

    public void setAtId(Long atId) {
        this.atId = atId;
    }

    public Integer getAdverSort() {
        return adverSort;
    }

    public void setAdverSort(Integer adverSort) {
        this.adverSort = adverSort;
    }

    public Long getAdverType() {
        return adverType;
    }

    public void setAdverType(Long adverType) {
        this.adverType = adverType;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
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

    public Long getFloorTagId() {
        return floorTagId;
    }

    public void setFloorTagId(Long floorTagId) {
        this.floorTagId = floorTagId;
    }
}
