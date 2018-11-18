package com.ningpai.channel.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 频道导航
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月9日下午5:59:40
 */
public class ChannelBar {
    /**
     * ID
     */
    private Long barId;
    /**
     * TEMPID
     */
    private Long tempId;
    /**
     * 导航名称
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String barName;
    /**
     * 导航地址
     */
    private Integer barPosition;
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String barUrl;

    /**
     * 导航排序
     */
    private Integer barSort;

    /**
     * 导航类型
     */
    private Long barType;
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String des;
    /** 是否开启频道 0：不开启 1：开启 */
    private String openChannel;

    /**
     * 是否启用
     */
    private String usedStatus;
    /** 第三方ID */
    private String expFleid1;
    /**
     * 备用2
     */
    private String expFleid2;

    /**
     * 插入人ID
     */
    private Long insertId;
    /**
     * 插入时间
     */
    private Date insertDate;

    /**
     * 修改人ID
     */
    private Long modifyId;
    /**
     * 修改日期
     */
    private Date modifyDate;

    /**
     * 删除状态
     */
    private Integer deleteStatus;

    /**
     * 频道ID
     */
    private Long channelId;

    public Long getBarId() {
        return barId;
    }

    public void setBarId(Long barId) {
        this.barId = barId;
    }

    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    public String getBarName() {
        return barName;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }

    public Integer getBarPosition() {
        return barPosition;
    }

    public void setBarPosition(Integer barPosition) {
        this.barPosition = barPosition;
    }

    public String getBarUrl() {
        return barUrl;
    }

    public void setBarUrl(String barUrl) {
        this.barUrl = barUrl;
    }

    public Integer getBarSort() {
        return barSort;
    }

    public void setBarSort(Integer barSort) {
        this.barSort = barSort;
    }

    public Long getBarType() {
        return barType;
    }

    public void setBarType(Long barType) {
        this.barType = barType;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getUsedStatus() {
        return usedStatus;
    }

    public void setUsedStatus(String usedStatus) {
        this.usedStatus = usedStatus;
    }

    public String getExpFleid1() {
        return expFleid1;
    }

    public void setExpFleid1(String expFleid1) {
        this.expFleid1 = expFleid1;
    }

    public String getExpFleid2() {
        return expFleid2;
    }

    public void setExpFleid2(String expFleid2) {
        this.expFleid2 = expFleid2;
    }

    public Long getInsertId() {
        return insertId;
    }

    public void setInsertId(Long insertId) {
        this.insertId = insertId;
    }

    /**
     * 时间
     * 
     * @return Date
     */
    public Date getInsertDate() {
        if (this.insertDate != null) {
            return new Date(this.insertDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 时间
     */
    public void setInsertDate(Date insertDate) {
        if (insertDate != null) {
            Date tEmp = (Date) insertDate.clone();
            if (tEmp != null) {
                this.insertDate = tEmp;
            }
        }
    }

    public Long getModifyId() {
        return modifyId;
    }

    public void setModifyId(Long modifyId) {
        this.modifyId = modifyId;
    }

    /**
     * 时间
     * 
     * @return Date
     */
    public Date getModifyDate() {
        if (this.modifyDate != null) {
            return new Date(this.modifyDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 时间
     */
    public void setModifyDate(Date modifyDate) {
        if (modifyDate != null) {
            Date tEmp = (Date) modifyDate.clone();
            if (tEmp != null) {
                this.modifyDate = tEmp;
            }
        }
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getOpenChannel() {
        return openChannel;
    }

    public void setOpenChannel(String openChannel) {
        this.openChannel = openChannel;
    }

}
