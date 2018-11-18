/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.gift.bean;

import java.util.Date;
import java.util.List;

/**
 * 赠品分类
 * 
 * @author ggn 2014-03-18
 * 
 */
public class GiftCate {
    /*
     *  赠品分类ID
     */
    private Long giftCateId;
    /*
     *  分类名称
     */
    private String giftCateName;
    /*
     *  赠品备注
     */
    private String giftCateRemark;
    /*
     *  创建时间
     */
    private Date createTime;
    /*
     *  修改时间
     */
    private Date modifyTime;
    /*
     *  删除标记 0 正常 1删除
     */
    private String delFlag;
    /*
     *  商家ID
     */
    private Long busniessId;
    /*
     *  父类ID
     */
    private Long giftParentId;
    /*
     * 赠品集合
     */
    private List<GiftCate> cateVos;

    public List<GiftCate> getCateVos() {
        return cateVos;
    }

    public void setCateVos(List<GiftCate> cateVos) {
        this.cateVos = cateVos;
    }

    public Long getGiftParentId() {
        return giftParentId;
    }

    public void setGiftParentId(Long giftParentId) {
        this.giftParentId = giftParentId;
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

    public String getGiftCateRemark() {
        return giftCateRemark;
    }

    public void setGiftCateRemark(String giftCateRemark) {
        this.giftCateRemark = giftCateRemark;
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
}
