/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 订单投诉Bean
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年4月22日 下午2:38:20
 * @version 0.0.1
 */
public class OrderComplain {
    /**
     * 投诉编号
     */
    private Long complainId;
    /**
     * 投诉人编号
     */
    private Long customerId;
    /**
     * 投诉类型
     */
    private String complainType;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 投诉时间
     */
    private Date complainTime;
    /**
     * 投诉内容
     */
    @NotNull
    @Pattern(regexp = "[^\\<\\>]+")
    private String complainContext;
    /**
     * 处理标记
     */
    private String dealFlag;
    /**
     * 回复内容
     */
    private String replayContext;
    /**
     * 回复人
     */
    private String replayUsername;
    /**
     * 删除标记
     */
    private String delFlag;

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
     * 获取申诉时间
     * @return
     */
    public Date getComplainTime() {
        //如果申诉时间不为空
        if (this.complainTime != null) {
            //返回申诉时间
            return new Date(this.complainTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置申诉时间
     * @param complainTime
     */
    public void setComplainTime(Date complainTime) {
        //如果申诉时间不为空
        if (complainTime != null) {
            //获取申诉时间
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
}
