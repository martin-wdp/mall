package com.ningpai.system.mobile.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 实体类-移动版站点基础设置
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月19日下午2:32:40
 */
public class MobSiteBasic {
    /** 编号 */
    private Long siteBasicId;
    /** 站点地址 */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String siteAddress;
    /** 站点名称 */
    private String name;
    /** 英文名称 */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String ename;
    /** 技术支持 */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String technicalSupport;
    /** 缓冲页背景色 */
    private String backgroudColor;
    /** 缓冲页logo地址 */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String backgroudImage;
    /** 缓冲页版权 */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String copyright;
    /** 是否显示缓冲页 */
    private String isShowBuffer;
    /** 创建时间 */
    private Date createDate;
    /** 修改时间 */
    private Date updateDate;
    /** 扩展字段1 (微信转发时的logo) */
    private String temp1;
    /** 扩展字段2 (是否使用新首页) */
    private String temp2;
    /** 扩展字段3 (转发到朋友圈的内容) */
    private String temp3;
    /** 扩展字段4 (转发给朋友的内容) */
    private String temp4;
    /** 扩展字段5 */
    private String temp5;

    public Long getSiteBasicId() {
        return siteBasicId;
    }

    public void setSiteBasicId(Long siteBasicId) {
        this.siteBasicId = siteBasicId;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getTechnicalSupport() {
        return technicalSupport;
    }

    public void setTechnicalSupport(String technicalSupport) {
        this.technicalSupport = technicalSupport;
    }

    public String getBackgroudColor() {
        return backgroudColor;
    }

    public void setBackgroudColor(String backgroudColor) {
        this.backgroudColor = backgroudColor;
    }

    public String getBackgroudImage() {
        return backgroudImage;
    }

    public void setBackgroudImage(String backgroudImage) {
        this.backgroudImage = backgroudImage;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getIsShowBuffer() {
        return isShowBuffer;
    }

    public void setIsShowBuffer(String isShowBuffer) {
        this.isShowBuffer = isShowBuffer;
    }
    /**
     * 时间
     * @return
     */
    public Date getCreateDate() {
        return createDate==null?null:(Date) createDate.clone();
    }
    /**
     * 时间
     * @return
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate == null ? null : (Date) createDate.clone();
    }
    /**
     * 时间
     * @return
     */
    public Date getUpdateDate() {
        return updateDate==null?null:(Date) updateDate.clone();
    }
    /**
     * 时间
     * @return
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate == null ? null : (Date) updateDate.clone();
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
