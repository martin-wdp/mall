/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.gift.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 赠品信息
 * 
 * @author ggn
 * 
 */
public class Gift {
    /*
     *  赠品ID
     */
    private Long giftId;
    /*
     *  赠品分类
     */
    private Long giftCateId;
    /*
     *  赠品分类名称
     */
    private String giftCateName;
    /*
     *  赠品名称
     */
    private String giftName;
    /*
     *  赠品编号
     */
    private String giftCode;
    /*
     *  兑换积分
     */
    private Long giftIntegral;
    /*
     *  开始时间
     */
    private Date giftStartTime;
    /*
     *  结束时间
     */
    private Date giftEndTime;
    /*
     *  是否发布
     */
    private String giftIssue;
    /*
     *  每单限购数量
     */
    private Long giftLimitBuy;
    /*
     *  赠品描述
     */
    private String giftDesc;
    /*
     *  赠品价格
     */
    private BigDecimal giftPrice;
    /*
     *  货号
     */
    private String giftArtNo;
    /*
     *  重量
     */
    private String giftWeight;
    /*
     *  库存
     */
    private Long giftStock;
    // 是否推荐
    private String giftRecommend;
    /*
     *  创建时间
     */
    private Date createTime;
    /*
     *  修改时间
     */
    private Date modifyTime;
    /*
     *  删除标记
     */
    private String delFlag;
    /*
     *  商家ID
     */
    private Long busniessId;
    /*
     *  详细描述 text类型
     */
    private String giftText;
    /*
     *  赠品图片
     */
    private List<GiftPic> giftPicList;
    /*
     * 图片id
     */
    private String picUrl;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public Long getGiftCateId() {
        return giftCateId;
    }

    public void setGiftCateId(Long giftCateId) {
        this.giftCateId = giftCateId;
    }

    public String getGiftCateName() {
        return giftCateName;
    }

    public void setGiftCateName(String giftCateName) {
        this.giftCateName = giftCateName;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftCode() {
        return giftCode;
    }

    public void setGiftCode(String giftCode) {
        this.giftCode = giftCode;
    }

    public Long getGiftIntegral() {
        return giftIntegral;
    }

    public void setGiftIntegral(Long giftIntegral) {
        this.giftIntegral = giftIntegral;
    }
    /**
     * 时间
     * 
     */
    public Date getGiftStartTime() {
        if (this.giftStartTime != null) {
            return new Date(this.giftStartTime.getTime()); // 正确值
        } else {
            return null;
        }
    }
    /**
     * 时间
     * 
     */
    public void setGiftStartTime(Date giftStartTime) {
        if (giftStartTime != null) {
            Date tEmp = (Date) giftStartTime.clone();
            if (tEmp != null) {
                this.giftStartTime = tEmp;
            }
        }
    }
    
    /**
     * 时间
     * 
     */
    public Date getGiftEndTime() {
        if (this.giftEndTime != null) {
            return new Date(this.giftEndTime.getTime()); // 正确值
        } else {
            return null;
        }
    }
    /**
     * 时间
     * 
     */
    public void setGiftEndTime(Date giftEndTime) {
        if (giftEndTime != null) {
            Date tEmp = (Date) giftEndTime.clone();
            if (tEmp != null) {
                this.giftEndTime = tEmp;
            }
        }
    }

    public String getGiftIssue() {
        return giftIssue;
    }

    public void setGiftIssue(String giftIssue) {
        this.giftIssue = giftIssue;
    }

    public Long getGiftLimitBuy() {
        return giftLimitBuy;
    }

    public void setGiftLimitBuy(Long giftLimitBuy) {
        this.giftLimitBuy = giftLimitBuy;
    }

    public String getGiftDesc() {
        return giftDesc;
    }

    public void setGiftDesc(String giftDesc) {
        this.giftDesc = giftDesc;
    }

    public BigDecimal getGiftPrice() {
        return giftPrice;
    }

    public void setGiftPrice(BigDecimal giftPrice) {
        this.giftPrice = giftPrice;
    }

    public String getGiftArtNo() {
        return giftArtNo;
    }

    public void setGiftArtNo(String giftArtNo) {
        this.giftArtNo = giftArtNo;
    }

    public String getGiftWeight() {
        return giftWeight;
    }

    public void setGiftWeight(String giftWeight) {
        this.giftWeight = giftWeight;
    }

    public Long getGiftStock() {
        return giftStock;
    }

    public void setGiftStock(Long giftStock) {
        this.giftStock = giftStock;
    }

    public String getGiftRecommend() {
        return giftRecommend;
    }

    public void setGiftRecommend(String giftRecommend) {
        this.giftRecommend = giftRecommend;
    }
    /**
     * 时间
     * 
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime()); // 正确值
        } else {
            return null;
        }
    }
    /**
     * 时间
     * 
     */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                this.createTime = tEmp;
            }
        }
    }
    /**
     * 时间
     * 
     */
    public Date getModifyTime() {
        if (this.modifyTime != null) {
            return new Date(this.modifyTime.getTime()); // 正确值
        } else {
            return null;
        }
    }
    /**
     * 时间
     * 
     */
    public void setModifyTime(Date modifyTime) {
        if (modifyTime != null) {
            Date tEmp = (Date) modifyTime.clone();
            if (tEmp != null) {
                this.modifyTime = tEmp;
            }
        }
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getBusniessId() {
        return busniessId;
    }

    public void setBusniessId(Long busniessId) {
        this.busniessId = busniessId;
    }

    public String getGiftText() {
        return giftText;
    }

    public void setGiftText(String giftText) {
        this.giftText = giftText;
    }

    public List<GiftPic> getGiftPicList() {
        return giftPicList;
    }

    public void setGiftPicList(List<GiftPic> giftPicList) {
        this.giftPicList = giftPicList;
    }

}
