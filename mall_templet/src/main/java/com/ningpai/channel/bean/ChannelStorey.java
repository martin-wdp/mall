package com.ningpai.channel.bean;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 实体类-频道楼层
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月29日上午10:43:52
 */
public class ChannelStorey implements Serializable{


    private static final long serialVersionUID = 1094593262511040855L;
    /** 频道楼层编号 */
    private Long channelStoreyId;
    /** 频道楼层名称 */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String storeyName;
    /** 频道ID */
    private Long channelId;
    /** 频道模板ID */
    private Long tempId;
    /** 频道楼层层数 */
    private Integer floorId;
    /** 频道楼层图片地址 */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String storeyImg;
    /** 频道楼层图片链接地址 */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String storeyImgHref;
    /** 频道楼层SEO */
    private String storeySeo = "";
    /** 频道楼层商品分类ID */
    private Long goodsCatId;
    /** 是否显示楼层图片 0：不显示 1：显示 */
    private String showImg;
    /** 是否显示热销排行 0：不显示 1：显示 */
    private String showHotRecommend;
    /** 是否显示楼层广告 0：不显示 1：显示 */
    private String showAdver;
    /** 是否显示楼层标签 0：不显示 1：显示 */
    private String showTag;
    /** 是否显示楼层品牌 0：不显示 1：显示 */
    private String showTrademark;
    /** 是否显示楼层布告板 0：不显示 1：显示 */
    private String showBillboard;
    /** 是否显示楼层分类的子分类 0：不显示 1：显示 */
    private String showChildCate;
    /** 是否启用 0：不启用； 1：启用 */
    private String userStatus;
    /** 是否删除 0：未删除 1：已删除 */
    private String delflag;
    /** 创建人ID */
    private Long createUserId;
    /** 创建时间 */
    private Date createDate;
    /** 修改人ID */
    private Long updateUserId;
    /** 修改时间 */
    private Date updateDate;
    /** 备用字段1 第三方ID */
    private String temp1;
    /** 备用字段2 楼层右侧导航图片 */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String temp2;
    /** 备用字段3 */
    private String temp3;
    /** 备用字段4 */
    private String temp4;
    /** 备用字段5 */
    private String temp5;

    public Long getChannelStoreyId() {
        return channelStoreyId;
    }

    public void setChannelStoreyId(Long channelStoreyId) {
        this.channelStoreyId = channelStoreyId;
    }

    public String getStoreyName() {
        return storeyName;
    }

    public void setStoreyName(String storeyName) {
        this.storeyName = storeyName;
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

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public String getStoreyImg() {
        return storeyImg;
    }

    public void setStoreyImg(String storeyImg) {
        this.storeyImg = storeyImg;
    }

    public String getStoreyImgHref() {
        return storeyImgHref;
    }

    public void setStoreyImgHref(String storeyImgHref) {
        this.storeyImgHref = storeyImgHref;
    }

    public String getStoreySeo() {
        return storeySeo;
    }

    public void setStoreySeo(String storeySeo) {
        this.storeySeo = storeySeo;
    }

    public Long getGoodsCatId() {
        return goodsCatId;
    }

    public void setGoodsCatId(Long goodsCatId) {
        this.goodsCatId = goodsCatId;
    }

    public String getShowImg() {
        return showImg;
    }

    public void setShowImg(String showImg) {
        this.showImg = showImg;
    }

    public String getShowHotRecommend() {
        return showHotRecommend;
    }

    public void setShowHotRecommend(String showHotRecommend) {
        this.showHotRecommend = showHotRecommend;
    }

    public String getShowAdver() {
        return showAdver;
    }

    public void setShowAdver(String showAdver) {
        this.showAdver = showAdver;
    }

    public String getShowTag() {
        return showTag;
    }

    public void setShowTag(String showTag) {
        this.showTag = showTag;
    }

    public String getShowTrademark() {
        return showTrademark;
    }

    public void setShowTrademark(String showTrademark) {
        this.showTrademark = showTrademark;
    }

    public String getShowBillboard() {
        return showBillboard;
    }

    public void setShowBillboard(String showBillboard) {
        this.showBillboard = showBillboard;
    }

    public String getShowChildCate() {
        return showChildCate;
    }

    public void setShowChildCate(String showChildCate) {
        this.showChildCate = showChildCate;
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
     * @param createDate
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
     * @param updateDate
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
