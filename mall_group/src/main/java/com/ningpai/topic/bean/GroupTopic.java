package com.ningpai.topic.bean;

import java.util.Date;
import java.util.List;

/**
 * 小组话题实体
 * 
 * @author qiyuanyuan
 * 
 */
public class GroupTopic {
    // 话题ID
    private Long topicId;

    // 小组ID
    private Long groupId;

    // 话题标题
    private String topicTitle;

    // 是否可回复：0可回复 1不可回复
    private String topicReplyFlag;

    // 话题热度：记录访问次数
    private Long topicHot;

    // 精华：0普通 1 精华贴
    private String topicEssence;

    // 热帖：0普通 1热帖
    private String topicFever;

    // 首页显示：0首页显示 1审核 2显示到首页
    private String topicIndexView;

    // 置顶：0 不置顶 1普通置顶 2特别置顶
    private String topicTopView;

    // 用户ID
    private Long customerId;

    // 用户姓名
    private String customerName;

    // 发布时间
    private Date topicCreateTime;

    // 发布时间到
    private Date topicCreateTimeTo;

    // 修改时间
    private Date topicModifyTime;

    // 修改时间到
    private Date topicModifyTimeTo;

    // 话题推荐时间
    private Date topicRecommendTime;

    // 是否删除：0：正常 1：删除
    private String topicDelFlag;

    // 话题内容
    private String topicContent;

    // 话题推荐次数
    private Long topicRecommend;

    // 话题评论次数
    private Long replyCount;

    // 用户头像
    private String customerHead;

    // 小组名称
    private String groupName;

    // 分类ID
    private Long groupTypeId;

    // 小组图片附件
    private List<GroupTopicImg> topicImgs;

    // 是否私密小组(0:公开 1：私密)
    private String groupSecret;

    // 话题所属分类( 0：客厅 1：卧室 2：书房 3： 厨房 4：餐厅 5：阳台花园 6：卫浴间 7：飘窗 8：套房 9：其他)
    private String topicCateId;

    // seo标题
    private String topicSeoTitle;

    // seo关键字
    private String topicSeoKeyword;

    // seo描述
    private String topicSeoDescription;

    private String changeFlag;

    private String changeVal;

    // 使用心得（0：一般 1:使用心得）
    private String topicIsUse;

    // 删除话题用户Id
    private Long topicDelCustomerId;

    // 申请恢复话题理由
    private String topicApplyReason;

    // 删除话题用户名
    private String topicDelCustomerName;

    // 拒绝申请恢复话题理由
    private String topicApplyRefuseReason;

    // 话题恢复申诉处理标记 0：未处理 1：已处理
    private String topicApplyFlag;

    private List<GroupTopicImg> piclist;

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicReplyFlag() {
        return topicReplyFlag;
    }

    public void setTopicReplyFlag(String topicReplyFlag) {
        this.topicReplyFlag = topicReplyFlag;
    }

    public Long getTopicHot() {
        return topicHot;
    }

    public void setTopicHot(Long topicHot) {
        this.topicHot = topicHot;
    }

    public String getTopicEssence() {
        return topicEssence;
    }

    public void setTopicEssence(String topicEssence) {
        this.topicEssence = topicEssence;
    }

    public String getTopicFever() {
        return topicFever;
    }

    public void setTopicFever(String topicFever) {
        this.topicFever = topicFever;
    }

    public String getTopicIndexView() {
        return topicIndexView;
    }

    public void setTopicIndexView(String topicIndexView) {
        this.topicIndexView = topicIndexView;
    }

    public String getTopicTopView() {
        return topicTopView;
    }

    public void setTopicTopView(String topicTopView) {
        this.topicTopView = topicTopView;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 时间
     * @return
     */
    public Date getTopicCreateTime() {
        return (Date) topicCreateTime.clone();
    }

    /**
     * 时间
     * @param topicCreateTime
     */
    public void setTopicCreateTime(Date topicCreateTime) {
        this.topicCreateTime = topicCreateTime == null ? null : (Date) topicCreateTime.clone();
    }

    /**
     * 时间
     * @return
     */
    public Date getTopicModifyTime() {
        return (Date) topicModifyTime.clone();
    }

    /**
     * 时间
     * @param topicModifyTime
     */
    public void setTopicModifyTime(Date topicModifyTime) {
        this.topicModifyTime = topicModifyTime == null ? null : (Date) topicModifyTime.clone();
    }

    public String getTopicDelFlag() {
        return topicDelFlag;
    }

    public void setTopicDelFlag(String topicDelFlag) {
        this.topicDelFlag = topicDelFlag;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getTopicRecommend() {
        return topicRecommend;
    }

    public void setTopicRecommend(Long topicRecommend) {
        this.topicRecommend = topicRecommend;
    }

    public Long getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Long replyCount) {
        this.replyCount = replyCount;
    }

    public List<GroupTopicImg> getTopicImgs() {
        return topicImgs;
    }

    public void setTopicImgs(List<GroupTopicImg> topicImgs) {
        this.topicImgs = topicImgs;
    }

    public String getCustomerHead() {
        return customerHead;
    }

    public void setCustomerHead(String customerHead) {
        this.customerHead = customerHead;
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

    public String getGroupSecret() {
        return groupSecret;
    }

    public void setGroupSecret(String groupSecret) {
        this.groupSecret = groupSecret;
    }

    /**
     * 时间
     * @return
     */
    public Date getTopicCreateTimeTo() {
        return (Date) topicCreateTimeTo.clone();
    }

    /**
     * 时间
     * @param topicCreateTimeTo
     */
    public void setTopicCreateTimeTo(Date topicCreateTimeTo) {
        this.topicCreateTimeTo = topicCreateTimeTo == null ? null : (Date) topicCreateTimeTo.clone();
    }

    /**
     * 时间
     * @return
     */
    public Date getTopicModifyTimeTo() {
        return (Date) topicModifyTimeTo.clone();
    }

    /**
     * 时间
     * @param topicModifyTimeTo
     */
    public void setTopicModifyTimeTo(Date topicModifyTimeTo) {
        this.topicModifyTimeTo = topicModifyTimeTo == null ? null : (Date) topicModifyTimeTo.clone();
    }

    public String getTopicSeoTitle() {
        return topicSeoTitle;
    }

    public void setTopicSeoTitle(String topicSeoTitle) {
        this.topicSeoTitle = topicSeoTitle;
    }

    public String getTopicSeoKeyword() {
        return topicSeoKeyword;
    }

    public void setTopicSeoKeyword(String topicSeoKeyword) {
        this.topicSeoKeyword = topicSeoKeyword;
    }

    public String getTopicSeoDescription() {
        return topicSeoDescription;
    }

    public void setTopicSeoDescription(String topicSeoDescription) {
        this.topicSeoDescription = topicSeoDescription;
    }

    /**
     * 时间
     * @return
     */
    public Date getTopicRecommendTime() {
        return (Date) topicRecommendTime.clone();
    }

    /**
     * 时间
     * @param topicRecommendTime
     */
    public void setTopicRecommendTime(Date topicRecommendTime) {
        this.topicRecommendTime = topicRecommendTime == null ? null : (Date) topicRecommendTime.clone();
    }

    public String getTopicCateId() {
        return topicCateId;
    }

    public void setTopicCateId(String topicCateId) {
        this.topicCateId = topicCateId;
    }

    public String getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(String changeFlag) {
        this.changeFlag = changeFlag;
    }

    public String getChangeVal() {
        return changeVal;
    }

    public void setChangeVal(String changeVal) {
        this.changeVal = changeVal;
    }

    public Long getTopicDelCustomerId() {
        return topicDelCustomerId;
    }

    public void setTopicDelCustomerId(Long topicDelCustomerId) {
        this.topicDelCustomerId = topicDelCustomerId;
    }

    public String getTopicApplyReason() {
        return topicApplyReason;
    }

    public void setTopicApplyReason(String topicApplyReason) {
        this.topicApplyReason = topicApplyReason;
    }

    public String getTopicApplyRefuseReason() {
        return topicApplyRefuseReason;
    }

    public void setTopicApplyRefuseReason(String topicApplyRefuseReason) {
        this.topicApplyRefuseReason = topicApplyRefuseReason;
    }

    public String getTopicDelCustomerName() {
        return topicDelCustomerName;
    }

    public void setTopicDelCustomerName(String topicDelCustomerName) {
        this.topicDelCustomerName = topicDelCustomerName;
    }

    public String getTopicApplyFlag() {
        return topicApplyFlag;
    }

    public void setTopicApplyFlag(String topicApplyFlag) {
        this.topicApplyFlag = topicApplyFlag;
    }

    public String getTopicIsUse() {
        return topicIsUse;
    }

    public void setTopicIsUse(String topicIsUse) {
        this.topicIsUse = topicIsUse;
    }

    public List<GroupTopicImg> getPiclist() {
        return piclist;
    }

    public void setPiclist(List<GroupTopicImg> piclist) {
        this.piclist = piclist;
    }

}
