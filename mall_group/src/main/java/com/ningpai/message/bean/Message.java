package com.ningpai.message.bean;

import java.util.Date;

/**
 * 消息信息实体
 * @author qiyuanyuan
 *
 */
public class Message  {
    //消息ID
    private Long messageId;

    //消息标题
    private String messageTitle;

    //创建时间
    private Date messageCreateTime;

    //创建人ID
    private Long messageAuthorId;

    //消息类型：0普通消息    1加入小组消息    2小组转让消息   3提升管理消息   4话题  5私信  6小组图片消息  7个人图片消息
    private String messageType;

    //是否删除：0：正常  1：删除
    private String messageDelFlag;

    //用户ID ：用于记录成员加入某小组
    private Long customerId;

    //小组ID：记录成员加入的小组
    private Long groupId;

    //图片ID：记录消息回复的图片
    private Long picId;

    //帖子ID：消息回复的话题ID
    private Long topicId;

    //是否操作：0：正常 1：操作
    private String messageOperationFlag;

    //消息内容
    private String messageContent;
    
    //消息模板Id
    private Long messageTempId;
    
    //招标Id
      private Long tenderId;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public Date getMessageCreateTime() {
        return messageCreateTime;
    }

    public void setMessageCreateTime(Date messageCreateTime) {
        this.messageCreateTime = messageCreateTime;
    }

    public Long getMessageAuthorId() {
        return messageAuthorId;
    }

    public void setMessageAuthorId(Long messageAuthorId) {
        this.messageAuthorId = messageAuthorId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageDelFlag() {
        return messageDelFlag;
    }

    public void setMessageDelFlag(String messageDelFlag) {
        this.messageDelFlag = messageDelFlag;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getMessageOperationFlag() {
        return messageOperationFlag;
    }

    public void setMessageOperationFlag(String messageOperationFlag) {
        this.messageOperationFlag = messageOperationFlag;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Long getMessageTempId() {
        return messageTempId;
    }

    public void setMessageTempId(Long messageTempId) {
        this.messageTempId = messageTempId;
    }

    public Long getTenderId() {
        return tenderId;
    }

    public void setTenderId(Long tenderId) {
        this.tenderId = tenderId;
    }
    
}
