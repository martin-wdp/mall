/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.comment.bean;

import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 晒单Bean
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年7月1日 上午9:50:50
 * @version 0.0.1
 */
public class Share {

    /**
     * 晒单信息id，主键
     */
    private Long shareId;

    /**
     * 评论id
     */
    private Long commentId;

    /**
     * 用户id
     */
    private Long customerId;

    /**
     * 货品id
     */
    private Long goodsId;
    /**
     * 订单货品id
     */
    private Long orderGoodsId;


    /**
     * 晒单时间
     */
    private Date createTime;

    /**
     * 是否显示：0，不显示；1，显示； 暂时未用
     */
    private String isDisplay;

    /**
     * 晒单内容
     */
    @Pattern(regexp = "[^\\<\\>]*")
    private String shareContent;

    /**
     * 用户名称
     */
    @Pattern(regexp = "[^\\<\\>]*")
    private String customerName;

    /**
     * 用户头像
     */
    @Pattern(regexp = "[^\\<\\>]*")
    private String customerHead;

    /**
     * 晒单标题
     */
    private String shareTitle;

    /**
     * 设置OrderGoodsId的值
     * */
    public void setOrderGoodsId(Long orderGoodsId){
        this.orderGoodsId=orderGoodsId;
    }
    /**
     * 获取OrderGOodsId的值
     * */
    public Long getOrderGoodsId(){
        return this.orderGoodsId;
    }
    /**
     * 获取ShareId 的值
     * */
    public Long getShareId() {
        return shareId;
    }
    /**
     * 设置ShareId的值
     * */
    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }
    /**
     * 获取CommentId的值
     * */
    public Long getCommentId() {
        return commentId;
    }
    /**
     * 设置CommentId的值
     * */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    /**
     * 获取CustomerId的值
     * */
    public Long getCustomerId() {
        return customerId;
    }
    /**
     * 设置CustomerId的值
     * */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    /**
     * 获取GoodsId的值
     * */
    public Long getGoodsId() {
        return goodsId;
    }
    /**
     * 设置GOodsId的值
     * */
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

    /**
     * 获取IsDisplay的值
     * */
    public String getIsDisplay() {
        return isDisplay;
    }
    /**
     * 设置IsDIsplay的值
     * */
    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay;
    }
    /**
     * 获取ShareCOntent的值
     * */
    public String getShareContent() {
        return shareContent;
    }
    /**
     * 设置ShareContent的值
     * */
    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
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
