package com.ningpai.comment.bean;

import java.util.Date;

import javax.validation.constraints.Pattern;

/**
 * 评论回复实体
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月20日 上午9:28:33
 * @version 0.0.1
 */
public class CommentReplay {
    /**
     * 回复编号
     * 
     * @see #getBalanceId()
     * @see #setBalanceId(Long)
     */
    private Long replayId;
    /**
     * 评论编号
     * 
     * @see #getCommentId()
     * @see #setCommentId(Long)
     */
    private Long commentId;
    /**
     * 会员编号
     * 
     * @see #getCustomerId()
     * @see #setCustomerId(Long)
     */
    private Integer customerId;
    /**
     * 内容
     * 
     * @see #getCommentContent()
     * @see #setCommentContent(String)
     */
    @Pattern(regexp = "[^\\<\\>]+")
    private String commentContent;
    /**
     * 发表时间
     * 
     * @see #getPublishTime()
     * @see #setPublishTime(Date)
     */
    private Date publishTime;
    /**
     * 修改时间
     * 
     * @see #getModifiedTime()
     * @see #setModifiedTime(Date)
     */
    private Date modifiedTime;
    /**
     * 删除时间
     * 
     * @see #getDelTime()
     * @see #setDelTime(Date)
     */
    private Date delTime;
    /**
     * 删除标记
     * 
     * @see #getDelFlag()
     * @see #setDelFlag(String)
     */
    private String delFlag;
    /**
     * 是否显示
     * 
     * @see #getIsDisplay()
     * @see #setIsDisplay(String)
     */
    private String isDisplay;
    /**
     * 回复IP
     * 
     * @see #getReplayIp()
     * @see #setReplayIp(String)
     */
    private String replayIp;
    /**
     * 昵称
     * 
     * @see #getCustomerNickname()
     * @see #setCustomerNickname(String)
     */
    @Pattern(regexp = "[^\\<\\>]+")
    private String customerNickname;

    /**
     * 获取customerNickname的值
     * 
     * @return customerNickname {@link com.ningpai.comment.bean.CommentReplay#customerNickname}
     */
    public String getCustomerNickname() {
        return customerNickname;
    }

    /**
     * 设置customerNickname的值
     * 
     * @param customerNickname
     *            {@link com.ningpai.comment.bean.CommentReplay#customerNickname}
     */
    public void setCustomerNickname(String customerNickname) {
        this.customerNickname = customerNickname;
    }

    /**
     * 获取replayIp的值
     * 
     * @return replayIp {@link com.ningpai.comment.bean.CommentReplay#replayIp}
     */
    public String getReplayIp() {
        return replayIp;
    }

    /**
     * 设置replayIp的值
     * 
     * @param replayIp
     *            {@link com.ningpai.comment.bean.CommentReplay#replayIp}
     */
    public void setReplayIp(String replayIp) {
        this.replayIp = replayIp;
    }

    /**
     * 获取replayId的值
     * 
     * @return replayId {@link com.ningpai.comment.bean.CommentReplay#replayId}
     */
    public Long getReplayId() {
        return replayId;
    }

    /**
     * 设置replayId的值
     * 
     * @param replayId
     *            {@link com.ningpai.comment.bean.CommentReplay#replayId}
     */
    public void setReplayId(Long replayId) {
        this.replayId = replayId;
    }

    /**
     * 获取commentId的值
     * 
     * @return commentId {@link com.ningpai.comment.bean.CommentReplay#commentId}
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * 设置commentId的值
     * 
     * @param commentId
     *            {@link com.ningpai.comment.bean.CommentReplay#commentId}
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    /**
     * 获取customerId的值
     * 
     * @return customerId {@link com.ningpai.comment.bean.CommentReplay#customerId}
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 设置customerId的值
     * 
     * @param customerId
     *            {@link com.ningpai.comment.bean.CommentReplay#customerId}
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取commentContent的值
     * 
     * @return commentContent {@link com.ningpai.comment.bean.CommentReplay#commentContent}
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * 设置commentContent的值
     * 
     * @param commentContent
     *            {@link com.ningpai.comment.bean.CommentReplay#commentContent}
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    /**
     * 获取publishTime的值
     * 
     * @return publishTime {@link com.ningpai.comment.bean.CommentReplay#publishTime}
     */
    public Date getPublishTime() {
        if (this.publishTime != null) {
            return new Date(this.publishTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置publishTime的值
     * 
     * @param publishTime
     *            {@link com.ningpai.comment.bean.CommentReplay#publishTime}
     */
    public void setPublishTime(Date publishTime) {
        if (publishTime != null) {
            Date timeTemp = (Date) publishTime.clone();
            if (timeTemp != null) {
                this.publishTime = timeTemp;
            }
        }
    }

    /**
     * 获取modifiedTime的值
     * 
     * @return modifiedTime {@link com.ningpai.comment.bean.CommentReplay#modifiedTime}
     */
    public Date getModifiedTime() {
        if (this.modifiedTime != null) {
            return new Date(this.modifiedTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置modifiedTime的值
     * 
     * @param modifiedTime
     *            {@link com.ningpai.comment.bean.CommentReplay#modifiedTime}
     */
    public void setModifiedTime(Date modifiedTime) {
        if (modifiedTime != null) {
            Date timeTemp = (Date) modifiedTime.clone();
            if (timeTemp != null) {
                this.modifiedTime = timeTemp;
            }
        }
    }

    /**
     * 获取delTime的值
     * 
     * @return delTime {@link com.ningpai.comment.bean.CommentReplay#delTime}
     */
    public Date getDelTime() {
        if (this.delTime != null) {
            return new Date(this.delTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置delTime的值
     * 
     * @param delTime
     *            {@link com.ningpai.comment.bean.CommentReplay#delTime}
     */
    public void setDelTime(Date delTime) {
        if (delTime != null) {
            Date timeTemp = (Date) delTime.clone();
            if (timeTemp != null) {
                this.delTime = timeTemp;
            }
        }
    }

    /**
     * 获取delFlag的值
     * 
     * @return delFlag {@link com.ningpai.comment.bean.CommentReplay#delFlag}
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置delFlag的值
     * 
     * @param delFlag
     *            {@link com.ningpai.comment.bean.CommentReplay#delFlag}
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取isDisplay的值
     * 
     * @return isDisplay {@link com.ningpai.comment.bean.CommentReplay#isDisplay}
     */
    public String getIsDisplay() {
        return isDisplay;
    }

    /**
     * 设置isDisplay的值
     * 
     * @param isDisplay
     *            {@link com.ningpai.comment.bean.CommentReplay#isDisplay}
     */
    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay;
    }
}
