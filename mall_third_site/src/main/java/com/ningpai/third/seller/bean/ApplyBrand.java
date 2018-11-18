package com.ningpai.third.seller.bean;

import java.util.Date;

/**
 * <P>
 * 自定义品牌
 * </P>
 * 
 * @author zhanghl
 * @since 2014年5月19日17:41:01
 * @version 2.0
 */
public class ApplyBrand {
    // 品牌id
    private Long applyBrandId;
    // 品牌名称
    private String applyBrandName;
    // 品牌图片
    private String applyBrandPic;
    // 品牌状态 是否删除
    private String applyBrandDelFlag;
    // 创建时间
    private Date applyBrandCreateTime;
    // 修改时间
    private Date applyModifyTime;
    // 对应的商家ID
    private Long applyThirdId;
    // 品牌路径
    private String applyUrl;
    // 审核状态
    private String applyStatus;
    // 整数
    private String applyCertificate;
    // 拒绝原因
    private String applyRefusalReason;

    public String getApplyRefusalReason() {
        return applyRefusalReason;
    }

    public void setApplyRefusalReason(String applyRefusalReason) {
        this.applyRefusalReason = applyRefusalReason;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getApplyUrl() {
        return applyUrl;
    }

    public void setApplyUrl(String applyUrl) {
        this.applyUrl = applyUrl;
    }

    public Long getApplyBrandId() {
        return applyBrandId;
    }

    public void setApplyBrandId(Long applyBrandId) {
        this.applyBrandId = applyBrandId;
    }

    public String getApplyBrandName() {
        return applyBrandName;
    }

    public void setApplyBrandName(String applyBrandName) {
        this.applyBrandName = applyBrandName;
    }

    public String getApplyBrandPic() {
        return applyBrandPic;
    }

    public void setApplyBrandPic(String applyBrandPic) {
        this.applyBrandPic = applyBrandPic;
    }

    public String getApplyBrandDelFlag() {
        return applyBrandDelFlag;
    }

    public void setApplyBrandDelFlag(String applyBrandDelFlag) {
        this.applyBrandDelFlag = applyBrandDelFlag;
    }

    /**
     * 获取创建时间
     * */
    public Date getApplyBrandCreateTime() {
        return applyBrandCreateTime==null?null: (Date) applyBrandCreateTime.clone();
    }
    /**
     * 设置创建时间
     * */
    public void setApplyBrandCreateTime(Date applyBrandCreateTime) {
        this.applyBrandCreateTime = applyBrandCreateTime==null?null: (Date) applyBrandCreateTime.clone();
    }
    /**
     * 获取修改时间
     * */
    public Date getApplyModifyTime() {
        return applyModifyTime==null?null: (Date) applyModifyTime.clone();
    }
    /**
     * 设置修改时间
     * */
    public void setApplyModifyTime(Date applyModifyTime) {
        this.applyModifyTime = applyModifyTime==null?null: (Date) applyModifyTime.clone();
    }

    public Long getApplyThirdId() {
        return applyThirdId;
    }

    public void setApplyThirdId(Long applyThirdId) {
        this.applyThirdId = applyThirdId;
    }

    public String getApplyCertificate() {
        return applyCertificate;
    }

    public void setApplyCertificate(String applyCertificate) {
        this.applyCertificate = applyCertificate;
    }

}
