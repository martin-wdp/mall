package com.ningpai.marketing.bean;

import java.util.Date;

/**
 * 促销LOGO对象
 * 
 * @author qiyuanyuan
 *
 */
public class PromotionLogo {
    /**
     * 促销logoID
     */
    private Long promotionLogoId;

    /**
     * 促销logo名称
     */
    private String promotionLogoName;

    /**
     * 促销logo图片
     */
    private String promotionLogoUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 删除标记:0:正常 1:删除
     */
    private String delFlag;

    /**
     * 临时字段1
     */
    private String temp1;

    /**
     * 临时字段2
     */
    private String temp2;

    /**
     * 临时字段3
     */
    private String temp3;

    public Long getPromotionLogoId() {
        return promotionLogoId;
    }

    public void setPromotionLogoId(Long promotionLogoId) {
        this.promotionLogoId = promotionLogoId;
    }

    public String getPromotionLogoName() {
        return promotionLogoName;
    }

    public void setPromotionLogoName(String promotionLogoName) {
        this.promotionLogoName = promotionLogoName;
    }

    public String getPromotionLogoUrl() {
        return promotionLogoUrl;
    }

    public void setPromotionLogoUrl(String promotionLogoUrl) {
        this.promotionLogoUrl = promotionLogoUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
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
}