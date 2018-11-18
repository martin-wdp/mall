/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.vo;

import java.util.Date;

import com.ningpai.group.bean.GroupPermissions;

/**
 * @version 2014年5月28日 下午2:28:56
 * @author qiyuanyuan
 */

public class GroupVo extends GroupPermissions {

    // 小组ID
    private Long groupId;

    // 小组名称
    private String groupName;

    // 小组头像
    private String groupHead;

    // 小组背景
    private String groupBackground;

    // 背景形式：0：默认 1：平铺
    private String groupBackgroundType;

    // 小组分类ID
    private Long groupTypeId;

    // 小组分类名称
    private String groupTypeName;

    // 小组创建时间
    private Date groupCreateTime;

    // 查询条件创建时间到
    private Date groupCreateTimeTo;

    // 小组修改时间
    private Date groupModifyTime;

    // 小组简介
    private String groupRemark;

    // 小组创建人ID
    private Long groupCreateAuthorId;

    // 小组创建人name
    private String groupCreateAuthorName;

    // 是否私密：0：公开 1：私密
    private String groupSecret;

    // 是否解散：0：未解散 1：解散
    private String groupDissolve;

    // 是否审核：0未审核 1审核通过 2拒绝
    private String groupCheckFlag;

    // 热门小组：0普通 1热门
    private String groupIsHot;

    // 活跃小组：0普通 1活跃
    private String groupIsActive;

    // 推荐到首页：0：首页不显示 1：显示到首页
    private String groupRecommend;

    // 小组状态
    private String groupStatus;

    // 小组成员上限
    private Long groupLimitMember;

    // 分页开始的条数
    private int startRowNum;

    // 分页结束的条数
    private int endRowNum;

    // 限制显示数量
    private int limitNum;

    // 小组成员数量
    private Long groupmember;

    // 成员角色
    private String customerPower;

    // 用户Id
    private Long customerId;

    // 小组话题数目
    private Long topicCount;

    private Long customerFlag;
    // 排序
    private String sort;

    // 小组标签
    private String groupLabel;

    // 小组组长Id
    private Long createId;

    // 小组标签
    private String groupLabelIds;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getGroupTypeId() {
        return groupTypeId;
    }

    public void setGroupTypeId(Long groupTypeId) {
        this.groupTypeId = groupTypeId;
    }

    public String getGroupTypeName() {
        return groupTypeName;
    }

    public void setGroupTypeName(String groupTypeName) {
        this.groupTypeName = groupTypeName;
    }
    /**
     * 获取创建时间
     * */
    public Date getGroupCreateTime() {
        return (Date) groupCreateTime.clone();
    }
    /**
     * 设置创建时间
     * */
    public void setGroupCreateTime(Date groupCreateTime) {
        this.groupCreateTime = groupCreateTime == null ? null : (Date) groupCreateTime.clone();
    }
    /**
     * 获取修改时间
     * */
    public Date getGroupModifyTime() {
        return (Date) groupModifyTime.clone();
    }
    /**
     * 设置修改时间
     * */
    public void setGroupModifyTime(Date groupModifyTime) {
        this.groupModifyTime = groupModifyTime == null ? null : (Date) groupModifyTime.clone();
    }

    public String getGroupRemark() {
        return groupRemark;
    }

    public void setGroupRemark(String groupRemark) {
        this.groupRemark = groupRemark;
    }

    public Long getGroupCreateAuthorId() {
        return groupCreateAuthorId;
    }

    public void setGroupCreateAuthorId(Long groupCreateAuthorId) {
        this.groupCreateAuthorId = groupCreateAuthorId;
    }

    public String getGroupCreateAuthorName() {
        return groupCreateAuthorName;
    }

    public void setGroupCreateAuthorName(String groupCreateAuthorName) {
        this.groupCreateAuthorName = groupCreateAuthorName;
    }

    public String getGroupSecret() {
        return groupSecret;
    }

    public void setGroupSecret(String groupSecret) {
        this.groupSecret = groupSecret;
    }

    public String getGroupDissolve() {
        return groupDissolve;
    }

    public void setGroupDissolve(String groupDissolve) {
        this.groupDissolve = groupDissolve;
    }

    public String getGroupCheckFlag() {
        return groupCheckFlag;
    }

    public void setGroupCheckFlag(String groupCheckFlag) {
        this.groupCheckFlag = groupCheckFlag;
    }

    public String getGroupIsHot() {
        return groupIsHot;
    }

    public void setGroupIsHot(String groupIsHot) {
        this.groupIsHot = groupIsHot;
    }

    public String getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(String groupStatus) {
        this.groupStatus = groupStatus;
    }

    public String getGroupHead() {
        return groupHead;
    }

    public void setGroupHead(String groupHead) {
        this.groupHead = groupHead;
    }

    public int getStartRowNum() {
        return startRowNum;
    }

    public void setStartRowNum(int startRowNum) {
        this.startRowNum = startRowNum;
    }

    public int getEndRowNum() {
        return endRowNum;
    }

    public void setEndRowNum(int endRowNum) {
        this.endRowNum = endRowNum;
    }

    /**
     * 获取创建时间
     * */
    public Date getGroupCreateTimeTo() {
        return (Date) groupCreateTimeTo.clone();
    }
    /**
     * 设置创建时间
     * */
    public void setGroupCreateTimeTo(Date groupCreateTimeTo) {
        this.groupCreateTimeTo = groupCreateTimeTo == null ? null : (Date) groupCreateTimeTo.clone();
    }

    public Long getGroupLimitMember() {
        return groupLimitMember;
    }

    public void setGroupLimitMember(Long groupLimitMember) {
        this.groupLimitMember = groupLimitMember;
    }

    public String getGroupIsActive() {
        return groupIsActive;
    }

    public void setGroupIsActive(String groupIsActive) {
        this.groupIsActive = groupIsActive;
    }

    public Long getGroupmember() {
        return groupmember;
    }

    public void setGroupmember(Long groupmember) {
        this.groupmember = groupmember;
    }

    public int getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }

    public String getCustomerPower() {
        return customerPower;
    }

    public void setCustomerPower(String customerPower) {
        this.customerPower = customerPower;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getGroupBackground() {
        return groupBackground;
    }

    public void setGroupBackground(String groupBackground) {
        this.groupBackground = groupBackground;
    }

    public String getGroupBackgroundType() {
        return groupBackgroundType;
    }

    public void setGroupBackgroundType(String groupBackgroundType) {
        this.groupBackgroundType = groupBackgroundType;
    }

    public Long getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(Long topicCount) {
        this.topicCount = topicCount;
    }

    public Long getCustomerFlag() {
        return customerFlag;
    }

    public void setCustomerFlag(Long customerFlag) {
        this.customerFlag = customerFlag;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getGroupLabel() {
        return groupLabel;
    }

    public void setGroupLabel(String groupLabel) {
        this.groupLabel = groupLabel;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public String getGroupRecommend() {
        return groupRecommend;
    }

    public void setGroupRecommend(String groupRecommend) {
        this.groupRecommend = groupRecommend;
    }

    public String getGroupLabelIds() {
        return groupLabelIds;
    }

    public void setGroupLabelIds(String groupLabelIds) {
        this.groupLabelIds = groupLabelIds;
    }

}
