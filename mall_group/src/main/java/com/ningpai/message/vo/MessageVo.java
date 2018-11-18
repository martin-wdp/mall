package com.ningpai.message.vo;

import com.ningpai.message.bean.Message;

/**
 * 消息Vo
 * 
 * @author qiyuanyuan
 *
 */
public class MessageVo extends Message {

    // 消息接收
    private Long[] customerRecId;

    // 用户姓名
    private String customerName;

    // 小组名称
    private String groupName;

    // 用户头像
    private String customerHead;

    // 消息标志( 0:未读 1：已读)
    private String messageReadFlag;

    // 消息接收对象Id
    private Long messageCustomerId;

    // 消息模板类型
    private String messageTempType;

    // 消息模板操作
    private String messageTempOperation;

    // 接收Id
    private Long messageRecCustomerId;

    // 小组加入用户姓名
    private String addGroupCustomerName;

    // 创建小组姓名
    private String createMsgCustomerName;

    // 话题名称
    private String topicTitle;

    // 私信接收用户名
    private String customerUsername;

    // 消息创建者头像
    private String createCustomerHead;

    // 小组照片标题
    private String groupImgTitle;

    // 小组审核未通过原因
    private String refuseGroupReason;

    // 话题删除标记（0：正常 1：删除）
    private String topicDelFlag;

    // 话题申诉处理标记
    private String topicApplyFlag;

    // 招标号
    private String tenderTitle;
    /**
     * 获取CustoemrRecId
     * */
    public Long[] getCustomerRecId() {
        return customerRecId.clone();
    }
    /**
     * 设置CustomerRecId
     * */
    public void setCustomerRecId(Long[] customerRecId) {
        this.customerRecId = customerRecId.clone();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCustomerHead() {
        return customerHead;
    }

    public void setCustomerHead(String customerHead) {
        this.customerHead = customerHead;
    }

    public String getMessageReadFlag() {
        return messageReadFlag;
    }

    public void setMessageReadFlag(String messageReadFlag) {
        this.messageReadFlag = messageReadFlag;
    }

    public Long getMessageCustomerId() {
        return messageCustomerId;
    }

    public void setMessageCustomerId(Long messageCustomerId) {
        this.messageCustomerId = messageCustomerId;
    }

    public String getMessageTempType() {
        return messageTempType;
    }

    public void setMessageTempType(String messageTempType) {
        this.messageTempType = messageTempType;
    }

    public String getMessageTempOperation() {
        return messageTempOperation;
    }

    public void setMessageTempOperation(String messageTempOperation) {
        this.messageTempOperation = messageTempOperation;
    }

    public Long getMessageRecCustomerId() {
        return messageRecCustomerId;
    }

    public void setMessageRecCustomerId(Long messageRecCustomerId) {
        this.messageRecCustomerId = messageRecCustomerId;
    }

    public String getAddGroupCustomerName() {
        return addGroupCustomerName;
    }

    public void setAddGroupCustomerName(String addGroupCustomerName) {
        this.addGroupCustomerName = addGroupCustomerName;
    }

    public String getCreateMsgCustomerName() {
        return createMsgCustomerName;
    }

    public void setCreateMsgCustomerName(String createMsgCustomerName) {
        this.createMsgCustomerName = createMsgCustomerName;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getCreateCustomerHead() {
        return createCustomerHead;
    }

    public void setCreateCustomerHead(String createCustomerHead) {
        this.createCustomerHead = createCustomerHead;
    }

    public String getGroupImgTitle() {
        return groupImgTitle;
    }

    public void setGroupImgTitle(String groupImgTitle) {
        this.groupImgTitle = groupImgTitle;
    }

    public String getRefuseGroupReason() {
        return refuseGroupReason;
    }

    public void setRefuseGroupReason(String refuseGroupReason) {
        this.refuseGroupReason = refuseGroupReason;
    }

    public String getTopicDelFlag() {
        return topicDelFlag;
    }

    public void setTopicDelFlag(String topicDelFlag) {
        this.topicDelFlag = topicDelFlag;
    }

    public String getTopicApplyFlag() {
        return topicApplyFlag;
    }

    public void setTopicApplyFlag(String topicApplyFlag) {
        this.topicApplyFlag = topicApplyFlag;
    }

    public String getTenderTitle() {
        return tenderTitle;
    }

    public void setTenderTitle(String tenderTitle) {
        this.tenderTitle = tenderTitle;
    }
}
