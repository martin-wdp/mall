package com.ningpai.message.bean;

import java.util.Date;

/**
 * 消息模板
 * 
 * @author qiyuanyuan
 *
 */
public class MessageTemplate {
    // 小组模板主键
    private Long messageTempId;

    // 消息类型
    private String messageType;

    // 消息操作
    private String messageOperation;

    // 消息模板
    private String messageTemplate;

    // 消息创建时间
    private Date messageCreateTime;

    // 消息修改时间
    private Date messageModifyTime;

    // 删除模板:0：正常 1：删除
    private String messageDelFlag;

    public Long getMessageTempId() {
        return messageTempId;
    }

    public void setMessageTempId(Long messageTempId) {
        this.messageTempId = messageTempId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageOperation() {
        return messageOperation;
    }

    public void setMessageOperation(String messageOperation) {
        this.messageOperation = messageOperation;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }

    public void setMessageTemplate(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    /**
     * 获取创建时间
     * */
    public Date getMessageCreateTime() {
        return (Date) messageCreateTime.clone();
    }
    /**
     * 设置创建时间
     * */
    public void setMessageCreateTime(Date messageCreateTime) {
        this.messageCreateTime = messageCreateTime == null ? null : (Date) messageCreateTime.clone();
    }
    /**
     * 获取创建时间
     * */
    public Date getMessageModifyTime() {
        return (Date) messageModifyTime.clone();
    }
    /**
     * 设置创建时间
     * */
    public void setMessageModifyTime(Date messageModifyTime) {
        this.messageModifyTime = messageModifyTime == null ? null : (Date) messageModifyTime.clone();
    }

    public String getMessageDelFlag() {
        return messageDelFlag;
    }

    public void setMessageDelFlag(String messageDelFlag) {
        this.messageDelFlag = messageDelFlag;
    }
}
