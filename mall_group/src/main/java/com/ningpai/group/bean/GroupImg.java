package com.ningpai.group.bean;

import java.util.Date;

/**
 * 小组图片实体
 * 
 * @author qiyuanyuan
 * 
 */
public class GroupImg {
    /**
     * 图片ID
     */
    private Long groupImgId;

    /**
     * 小组ID
     */
    private Long groupId;

    /**
     * 小组名称
     */
    private String groupName;

    /**
     *  图片地址
     */
    private String groupImgUrl;

    /**
     *  用户ID
     */
    private Long customerId;

    /**
     * 用户姓名
     */
    private String customerName;

    /**
     *  图片标题
     */
    private String groupImgTitle;

    /**
     * 图片描述
     */
    private String groupImgDes;

    /**
     * 创建时间
     */
    private Date groupImgCreateTime;

    /**
     * 修改时间
     */
    private Date groupImgModifyTime;

    /**
     *  图片回复数目
     */
    private String replyCount;

    /**
     * 是否删除 0：未删除 1：删�?
     */
    private String groupImgDelFlag;

    /**
     *  图片宽度
     */
    private int groupImgWidth;

    /**
     * 图片高度
     */
    private int groupImgHeight;

    public Long getGroupImgId() {
        return groupImgId;
    }

    public void setGroupImgId(Long groupImgId) {
        this.groupImgId = groupImgId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupImgUrl() {
        return groupImgUrl;
    }

    public void setGroupImgUrl(String groupImgUrl) {
        this.groupImgUrl = groupImgUrl;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getGroupImgTitle() {
        return groupImgTitle;
    }

    public void setGroupImgTitle(String groupImgTitle) {
        this.groupImgTitle = groupImgTitle;
    }

    public String getGroupImgDes() {
        return groupImgDes;
    }

    public void setGroupImgDes(String groupImgDes) {
        this.groupImgDes = groupImgDes;
    }

    public Date getGroupImgCreateTime() {
        return groupImgCreateTime;
    }

    public void setGroupImgCreateTime(Date groupImgCreateTime) {
        this.groupImgCreateTime = groupImgCreateTime;
    }

    public Date getGroupImgModifyTime() {
        return groupImgModifyTime;
    }

    public void setGroupImgModifyTime(Date groupImgModifyTime) {
        this.groupImgModifyTime = groupImgModifyTime;
    }

    public String getGroupImgDelFlag() {
        return groupImgDelFlag;
    }

    public void setGroupImgDelFlag(String groupImgDelFlag) {
        this.groupImgDelFlag = groupImgDelFlag;
    }

    public String getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
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

    public int getGroupImgWidth() {
        return groupImgWidth;
    }

    public void setGroupImgWidth(int groupImgWidth) {
        this.groupImgWidth = groupImgWidth;
    }

    public int getGroupImgHeight() {
        return groupImgHeight;
    }

    public void setGroupImgHeight(int groupImgHeight) {
        this.groupImgHeight = groupImgHeight;
    }

}
