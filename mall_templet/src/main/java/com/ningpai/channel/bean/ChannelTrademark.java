package com.ningpai.channel.bean;

import java.util.Date;

import javax.validation.constraints.Pattern;

/**
 * 实体类-频道品牌
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月25日上午9:32:35
 */
public class ChannelTrademark {
    /** 频道品牌编号 */
    private Long channelTrademarkId;
    /** 频道ID */
    private Long channelId;
    /** 模板ID */
    private Long tempId;
    /** 楼层ID */
    private Long storeyId;
    /** 楼层标签ID */
    private Long storeyTagId;
    /** 品牌logo地址 */
    @Pattern(regexp = "[^\\<\\>]+")
    private String logoSrc;
    /** 品牌名称 */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String trademarkName;
    /** 描述 */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String des;
    /** 排序 */
    private Short sort;
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
    /** 备用字段1 （分类导航ID） */
    private String temp1;
    /** 备用字段2 （是否分类导航父框品牌 0：不是 1：是） */
    private String temp2;
    /** 备用字段3 第三方ID */
    private String temp3;
    /** 备用字段4 (品牌ID) */
    private String temp4;
    /** 备用字段5 */
    private String temp5;
    /** 显示类型 0:文字 1：图片 */
    private String showType;
    /** 品牌标题 */
    private String title;
    /** 品牌链接 */
    private String url;

    public Long getChannelTrademarkId() {
        return channelTrademarkId;
    }

    public void setChannelTrademarkId(Long channelTrademarkId) {
        this.channelTrademarkId = channelTrademarkId;
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

    public Long getStoreyId() {
        return storeyId;
    }

    public void setStoreyId(Long storeyId) {
        this.storeyId = storeyId;
    }

    public String getLogoSrc() {
        return logoSrc;
    }

    public void setLogoSrc(String logoSrc) {
        this.logoSrc = logoSrc;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
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

    public String getTrademarkName() {
        return trademarkName;
    }

    public void setTrademarkName(String trademarkName) {
        this.trademarkName = trademarkName;
    }

    public Long getStoreyTagId() {
        return storeyTagId;
    }

    public void setStoreyTagId(Long storeyTagId) {
        this.storeyTagId = storeyTagId;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
