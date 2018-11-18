package com.ningpai.message.bean;

import java.util.Date;

/**
 * 消息接收实体
 * 
 * @author qiyuanyuan
 *
 */
public class MessageCustomer {
    // 接收ID
    private Long messageCustomerId;

    // 消息ID
    private Long messageId;

    // 接收人
    private Long messageRecCustomerId;

    // 消息状态：0：未读 1：已读
    private String messageFlag;

    // 查看时间
    private Date messageReadTime;

    // 是否删除 0：正常 1：删除
    private String messageDelFlag;

    public Long getMessageCustomerId() {
        return messageCustomerId;
    }

    public void setMessageCustomerId(Long messageCustomerId) {
        this.messageCustomerId = messageCustomerId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getMessageRecCustomerId() {
        return messageRecCustomerId;
    }

    public void setMessageRecCustomerId(Long messageRecCustomerId) {
        this.messageRecCustomerId = messageRecCustomerId;
    }

    public String getMessageFlag() {
        return messageFlag;
    }

    public void setMessageFlag(String messageFlag) {
        this.messageFlag = messageFlag;
    }
    /**
     * 获取读取时间
     * */
    public Date getMessageReadTime() {
        return (Date) messageReadTime.clone();
    }
    /**
     * 设置读取时间
     * */
    public void setMessageReadTime(Date messageReadTime) {
        this.messageReadTime = messageReadTime == null ? null : (Date) messageReadTime.clone();
    }

    public String getMessageDelFlag() {
        return messageDelFlag;
    }

    public void setMessageDelFlag(String messageDelFlag) {
        this.messageDelFlag = messageDelFlag;
    }
}
