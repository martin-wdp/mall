package com.ningpai.group.bean;

import java.util.Date;

/**
 * 小组实体
 * 
 * @author qiyuanyuan
 * 
 */
public class Group {

    /**
     * 小组ID
     */
    private Long groupId;

    /**
     * 类型ID
     */
    private Long groupTypeId;

    /**
     * 小组名称
     */
    private String groupName;

    /**
     * 小组备注
     */
    private String groupRemark;

    /**
     * 小组头像
     */
    private String groupHead;

    /**
     * 小组创建时间
     */
    private Date groupCreateTime;

    /**
     * 小组修改时间
     */
    private Date groupModifyTime;

    /**
     * 小组创建人
     */
    private Long groupCreateAuthorId;

    /**
     * 是否私密 0：公共 1：私密
     */
    private String groupSecret;

    /**
     * 是否解散小组 0：未解散 1：解散
     */
    private String groupDissolve;

    /**
     * 小组背景
     */
    private String groupBackground;

    /**
     * 背景形式 0：默认 1：平铺
     */
    private String groupBackgroundType;

    /**
     * 是否审核 0：未审核 1审核通过 2拒绝
     */
    private String groupCheckFlag;

    /**
     * 热门小组0：普通 1热门
     */
    private String groupIsHot;

    /**
     * 活跃小组0普通 1活跃
     */
    private String groupIsActive;

    /**
     * 推荐到首页：0：首页不显示 1：显示到首页
     */
    private String groupRecommend;

    /**
     * 推荐时间
     */
    private Date groupRecommendTime;

    /**
     * 小组状态
     */
    private String groupStatus;

    /**
     * 小组成员上限
     */
    private Long groupLimitMember;

    /**
     * 小组标签
     */
    private String groupLabel;

    /**
     * 小组审核未通过理由
     */
    private String refuseReason;
    /**
     * 小组标签类型
     */
    private GroupType npGroupTypeGroupTypeId;

    /**
     * 小组标签多个
     */
    private String[] groupLabelIds;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupTypeId() {
        return groupTypeId;
    }

    public void setGroupTypeId(Long groupTypeId) {
        this.groupTypeId = groupTypeId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupRemark() {
        return groupRemark;
    }

    public void setGroupRemark(String groupRemark) {
        this.groupRemark = groupRemark;
    }

    public String getGroupHead() {
        return groupHead;
    }

    public void setGroupHead(String groupHead) {
        this.groupHead = groupHead;
    }

    /**
     * get创建时间
     * 复制时间对象并返回
     */
    public Date getGroupCreateTime() {
        return (Date) groupCreateTime.clone();
    }

    /**
     * set创建时间
     * 创建时间 不为空时候 复制时间对象
     */
    public void setGroupCreateTime(Date groupCreateTime) {
        this.groupCreateTime = groupCreateTime == null ? null : (Date) groupCreateTime.clone();
    }

    /**
     * 复制时间对象并返回
     * @return
     */
    public Date getGroupModifyTime() {
        
        return (Date) groupModifyTime.clone();
    }

    /**
     * set修改时间
     * 修改时间 不为空时候 复制时间对象
     * 
     */
    public void setGroupModifyTime(Date groupModifyTime) {
        this.groupModifyTime = groupModifyTime == null ? null : (Date) groupModifyTime.clone();
    }

    public Long getGroupCreateAuthorId() {
        return groupCreateAuthorId;
    }

    public void setGroupCreateAuthorId(Long groupCreateAuthorId) {
        this.groupCreateAuthorId = groupCreateAuthorId;
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

    public String getGroupIsActive() {
        return groupIsActive;
    }

    public void setGroupIsActive(String groupIsActive) {
        this.groupIsActive = groupIsActive;
    }

    /**
     * get推荐时间
     * 复制时间对象并返回
     */
    public Date getGroupRecommendTime() {
        return (Date) groupRecommendTime.clone();
    }

    /**
     * set小组推荐时间
     * 推荐时间不为空时复制时间对象
     */
    public void setGroupRecommendTime(Date groupRecommendTime) {
        this.groupRecommendTime = groupRecommendTime == null ? null : (Date) groupRecommendTime.clone();
    }

    public void setNpGroupTypeGroupTypeId(GroupType npGroupTypeGroupTypeId) {
        this.npGroupTypeGroupTypeId = npGroupTypeGroupTypeId;
    }

    public GroupType getNpGroupTypeGroupTypeId() {
        return npGroupTypeGroupTypeId;
    }

    public String getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(String groupStatus) {
        this.groupStatus = groupStatus;
    }

    public Long getGroupLimitMember() {
        return groupLimitMember;
    }

    public void setGroupLimitMember(Long groupLimitMember) {
        this.groupLimitMember = groupLimitMember;
    }

    public String getGroupLabel() {
        return groupLabel;
    }

    public void setGroupLabel(String groupLabel) {
        this.groupLabel = groupLabel;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getGroupRecommend() {
        return groupRecommend;
    }

    public void setGroupRecommend(String groupRecommend) {
        this.groupRecommend = groupRecommend;
    }

    /**
     * get小组标签
     * @return
     */
    public String[] getGroupLabelIds() {
        return groupLabelIds.clone();
    }

    /**
     * 返回小组标签的赋值值
     * @param groupLabelIds
     */
    public void setGroupLabelIds(String[] groupLabelIds) {
        this.groupLabelIds = groupLabelIds.clone();
    }

}
