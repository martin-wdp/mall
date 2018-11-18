package com.ningpai.comment.bean;

import java.util.Date;

import javax.validation.constraints.Pattern;

/**
 * 晒单回复
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年7月1日 上午9:50:25
 * @version 0.0.1
 */
public class ShareReply {

    private Long shareReplyId;

    private Long customerId;

    private Long shareId;

    private Long parentId;

    private Long replyLevel;
    @Pattern(regexp = "[^\\<\\>]+")
    private String replyContent;

    private Date createTime;

    /**
     * 是否显示：0，不显示；1，显示； 暂时未用
     */
    private String isDisplay;

    public Long getShareReplyId() {
        return shareReplyId;
    }

    public void setShareReplyId(Long shareReplyId) {
        this.shareReplyId = shareReplyId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getReplyLevel() {
        return replyLevel;
    }

    public void setReplyLevel(Long replyLevel) {
        this.replyLevel = replyLevel;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
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
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                this.createTime = tEmp;
            }
        }
    }

    public String getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay;
    }
}
