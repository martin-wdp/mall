/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 投诉VO
 * 
 * @author jiping
 * @since 2014年7月24日 上午11:15:53
 * @version 0.0.1
 */
public class ComplainVo {
    /*
     * 投诉编号
     */
    private Long complainId;
    /*
     * 投诉人编号
     */
    private Long customerId;
    /*
     * 投诉类型
     */
    private String complainType;
    /*
     * 订单编号
     */
    private String orderNo;
    /*
     * 投诉时间
     */
    private Date complainTime;
    /*
     * 投诉内容
     */
    private String complainContext;
    /*
     * 处理标记
     */
    private String dealFlag;
    /*
     * 回复内容
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String replayContext;
    /*
     * 回复人
     */
    private String replayUsername;
    /*
     * 删除标记
     */
    private String delFlag;

    /*
     * 创建时间
     */
    private Date createTime;
    /*
     * 修改时间
     */
    private Date modifyTime;
    /*
     * 删除时间
     */
    private Date delTime;

    // 查询到的时间
    private Date createTimeTo;

    public Long getComplainId() {
        return complainId;
    }

    public void setComplainId(Long complainId) {
        this.complainId = complainId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getComplainType() {
        return complainType;
    }

    public void setComplainType(String complainType) {
        this.complainType = complainType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取投诉时间
     * */
    public Date getComplainTime() {
        if (this.complainTime != null) {
            return new Date(this.complainTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置投诉时间
     * */
    public void setComplainTime(Date complainTime) {
        if (complainTime != null) {
            Date tEmp = (Date) complainTime.clone();
            if (tEmp != null) {
                this.complainTime = tEmp;
            }
        }
    }

    public String getComplainContext() {
        return complainContext;
    }

    public void setComplainContext(String complainContext) {
        this.complainContext = complainContext;
    }

    public String getDealFlag() {
        return dealFlag;
    }

    public void setDealFlag(String dealFlag) {
        this.dealFlag = dealFlag;
    }

    public String getReplayContext() {
        return replayContext;
    }

    public void setReplayContext(String replayContext) {
        this.replayContext = replayContext;
    }

    public String getReplayUsername() {
        return replayUsername;
    }

    public void setReplayUsername(String replayUsername) {
        this.replayUsername = replayUsername;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取创建时间
     * */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置创建时间
     * */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date timeTemp = (Date) createTime.clone();
            if (timeTemp != null) {
                this.createTime = timeTemp;
            }
        }
    }

    /**
     * 获取修改时间
     * */
    public Date getmodifyTime() {
        if (this.modifyTime != null) {
            return new Date(this.modifyTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置修改时间
     * */
    public void setmodifyTime(Date modifyTime) {
        if (modifyTime != null) {
            Date timeTemp = (Date) modifyTime.clone();
            if (timeTemp != null) {
                this.modifyTime = timeTemp;
            }
        }
    }

    /**
     * 获取删除时间
     * */
    public Date getDelTime() {
        if (this.delTime != null) {
            return new Date(this.delTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置删除时间
     * */
    public void setDelTime(Date delTime) {
        if (delTime != null) {
            Date timeTemp = (Date) delTime.clone();
            if (timeTemp != null) {
                this.delTime = timeTemp;
            }
        }
    }

    /**
     * 获取查询到的时间
     * */
    public Date getCreateTimeTo() {
        if (this.createTimeTo != null) {
            return new Date(this.createTimeTo.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置查询到的时间
     * */
    public void setCreateTimeTo(Date createTimeTo) {
        if (createTimeTo != null) {
            Date tEmp = (Date) createTimeTo.clone();
            if (tEmp != null) {
                this.createTimeTo = tEmp;
            }
        }
    }

}
