/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.comment.vo;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;

import com.ningpai.comment.bean.ShareImg;
import com.ningpai.other.bean.GoodsBean;

/**
 * 晒单VO
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年7月2日 下午2:28:58
 * @version 0.0.1
 */
public class ShareVo {

    private Long shareId;
    private Long commentId;
    // 用户Id
    private Long customerId;

    private Long goodsId;

    private Date createTime;

    private String isDisplay;
    @Pattern(regexp = "[^\\<\\>]*")
    private String shareContent;
    @Pattern(regexp = "[^\\<\\>]*")
    private String customerName;

    private String customerHead;
    @Pattern(regexp = "[^\\<\\>]*")
    private String shareTitle;

    // 晒单图片
    private List<ShareImg> imageList;

    // 晒单回复
    private List<ShareReplyVo> replies;

    // 商品
    private GoodsBean good;
    // 商品名称
    @Pattern(regexp = "[^\\<\\>]*")
    private String goodsName;
    // 查询到的时间
    private Date createTimeTo;
    // 商品图片
    @Pattern(regexp = "[^\\<\\>]*")
    private String goodsImg;

    // 商品评分
    private Long goodsScore;

    public Long getGoodsScore() {
        return goodsScore;
    }

    public void setGoodsScore(Long goodsScore) {
        this.goodsScore = goodsScore;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    /**
     * 获取创建时间
     * */
    public Date getCreateTimeTo() {
        if (this.createTimeTo != null) {
            return new Date(this.createTimeTo.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置创建时间
     * */
    public void setCreateTimeTo(Date createTimeTo) {
        if (createTimeTo != null) {
            Date tEmp = (Date) createTimeTo.clone();
            if (tEmp != null) {
                this.createTimeTo = tEmp;
            }
        }
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public GoodsBean getGood() {
        return good;
    }

    public void setGood(GoodsBean good) {
        this.good = good;
    }

    public List<ShareReplyVo> getReplies() {
        return replies;
    }

    public void setReplies(List<ShareReplyVo> replies) {
        this.replies = replies;
    }

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    /**
     * @return the imageList
     */
    public List<ShareImg> getImageList() {
        return imageList;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName
     *            the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the customerHead
     */
    public String getCustomerHead() {
        return customerHead;
    }

    /**
     * @param customerHead
     *            the customerHead to set
     */
    public void setCustomerHead(String customerHead) {
        this.customerHead = customerHead;
    }

    /**
     * @param imageList
     *            the imageList to set
     */
    public void setImageList(List<ShareImg> imageList) {
        this.imageList = imageList;
    }

    /**
     * @return the shareTitle
     */
    public String getShareTitle() {
        return shareTitle;
    }

    /**
     * @param shareTitle
     *            the shareTitle to set
     */
    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

}
