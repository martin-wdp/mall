package com.ningpai.mobile.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 实体类-移动版广告<br/>
 * 用于首页广告、楼层广告。用于首页时，storeyId楼层ID为-1<br/>
 * tempId为以后可能添加模板的情况预留
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月21日上午10:08:42
 */
public class MobAdver {
    /** 编号 */
    private Long modAdverId;
    /** 模板ID */
    private Long tempId;
    /** 楼层ID */
    private Long storeyId;
    /** 广告标题 */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String adverTitle;
    /** 广告副标题 */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String adverSub;
    /** 广告图片地址 */
    @NotNull
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String adverImgSrc;
    /** 广告链接地址 */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String adverHref;
    /** 广告排序 */
    private Integer adverSort;
    /** 启用状态 0：未启用 1：已启用 */
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

    public Long getModAdverId() {
        return modAdverId;
    }

    public void setModAdverId(Long modAdverId) {
        this.modAdverId = modAdverId;
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

    public String getAdverTitle() {
        return adverTitle;
    }

    public void setAdverTitle(String adverTitle) {
        this.adverTitle = adverTitle;
    }

    public String getAdverSub() {
        return adverSub;
    }

    public void setAdverSub(String adverSub) {
        this.adverSub = adverSub;
    }

    public String getAdverImgSrc() {
        return adverImgSrc;
    }

    public void setAdverImgSrc(String adverImgSrc) {
        this.adverImgSrc = adverImgSrc;
    }

    public String getAdverHref() {
        return adverHref;
    }

    public void setAdverHref(String adverHref) {
        this.adverHref = adverHref;
    }

    public Integer getAdverSort() {
        return adverSort;
    }

    public void setAdverSort(Integer adverSort) {
        this.adverSort = adverSort;
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
}
