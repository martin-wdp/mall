package com.ningpai.group.bean;

import java.util.Date;
import java.util.List;

/**
 * 用户回复实体
 * 
 * @author qiyuanyuan
 * 
 */
public class CustomerReply {
    /**
     * 回复ID
     */
    private Long replyId;

    /**
     * 引用ID
     */
    private Long replyShipId;

    /**
     * 回复人ID
     */
    private Long customerId;

    /**
     * 回复人姓�?
     */
    private String customerName;

    /**
     * 回复类型�?0 话题回复 1小组回复 2小组图片回复'
     */
    private String replyType;

    /**
     * 回复时间
     */
    private Date replyTime;

    /**
     * 是否删除0：未删除 1：删�?
     */
    private String replyDelFlag;

    /**
     * 回复引用内容
     */
    private String replyRemark;

    /**
     * 回复内容
     */
    private String replyContent;

    /**
     * 用户头像
     */
    private String customerHeadimg;

    /**
     * 回复的评论Id
     */
    private Long replyParentId;

    private List<CustomerReply> rlist;

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public Long getReplyShipId() {
        return replyShipId;
    }

    public void setReplyShipId(Long replyShipId) {
        this.replyShipId = replyShipId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getReplyType() {
        return replyType;
    }

    public void setReplyType(String replyType) {
        this.replyType = replyType;
    }

    /**
     * get回复时间
     * 
     */
    public Date getReplyTime() {
        return (Date) replyTime.clone();
    }

    /**
     * set回复时间
     * 回复时间不为空时复制时间对象
     */
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime == null ? null : (Date) replyTime.clone();
    }

    public String getReplyDelFlag() {
        return replyDelFlag;
    }

    public void setReplyDelFlag(String replyDelFlag) {
        this.replyDelFlag = replyDelFlag;
    }

    public String getReplyRemark() {
        return replyRemark;
    }

    public void setReplyRemark(String replyRemark) {
        this.replyRemark = replyRemark;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerHeadimg() {
        return customerHeadimg;
    }

    public void setCustomerHeadimg(String customerHeadimg) {
        this.customerHeadimg = customerHeadimg;
    }

    public Long getReplyParentId() {
        return replyParentId;
    }

    public void setReplyParentId(Long replyParentId) {
        this.replyParentId = replyParentId;
    }

    public List<CustomerReply> getRlist() {
        return rlist;
    }

    public void setRlist(List<CustomerReply> rlist) {
        this.rlist = rlist;
    }

}
