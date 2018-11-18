/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * 站内信Bean
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年8月7日 上午10:53:50
 * @version 0.0.1
 */
public class InsideLetter {
    // 站内信编号
    private Long letterId;
    // 发送人编号 暂时没用
    private Long customerId;
    // 接收人编号 为null则为全部会员接收
    private Long receiveCustomerId;
    // 站内信标题
    @NotNull
    private String letterTitle;
    // 站内信内容
    private String letterContent;
    // 发布Ip
    private String letterIp;
    // 回复父Id 后期可用于回复
    private Long parentId;
    // 发布时间
    private Date createTime;
    // 删除时间
    private Date delTime;
    // 删除标记
    private String delFlag;
    // 是否已读
    private String isRead;
    // 是否已读
    private String isSms;

    public String getIsSms() {
        return isSms;
    }

    public void setIsSms(String isSms) {
        this.isSms = isSms;
    }

    public Long getLetterId() {
        return letterId;
    }

    public void setLetterId(Long letterId) {
        this.letterId = letterId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getReceiveCustomerId() {
        return receiveCustomerId;
    }

    public void setReceiveCustomerId(Long receiveCustomerId) {
        this.receiveCustomerId = receiveCustomerId;
    }

    public String getLetterTitle() {
        return letterTitle;
    }

    public void setLetterTitle(String letterTitle) {
        this.letterTitle = letterTitle;
    }

    public String getLetterContent() {
        return letterContent;
    }

    public void setLetterContent(String letterContent) {
        this.letterContent = letterContent;
    }

    public String getLetterIp() {
        return letterIp;
    }

    public void setLetterIp(String letterIp) {
        this.letterIp = letterIp;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    /**
     *获取创建时间
     * */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        } else {
            return null;
        }
    }
    /**
     *设置创建时间
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
     *获取删除时间
     * */
    public Date getDelTime() {
        if (this.delTime != null) {
            return new Date(this.delTime.getTime());
        } else {
            return null;
        }
    }
    /**
     *设置删除时间
     * */
    public void setDelTime(Date delTime) {
        if (delTime != null) {
            Date timeTemp = (Date) delTime.clone();
            if (timeTemp != null) {
                this.delTime = timeTemp;
            }
        }
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }
}
