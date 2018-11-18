package com.ningpai.group.bean;

/**
 * 小组权限实体
 * 
 * @author qiyuanyuan
 * 
 */
public class GroupPermissions {
    /**
     * 主键ID
     */
    private Long limitId;

    /**
     * 小组ID
     */
    private Long groupId;

    /**
     * 加入方式0允许任何人加入（不需要审核） 1（需要审核） 2不允许加入)
     */
    private String limitAddType;

    /**
     * 加入条件0允许任何人加入1只女性用 2只男性用
     */
    private String limitCondition;

    /**
     * 回复帖子0小组帖子允许任何人回复1仅可以成员回复2不允许回复
     */
    private String limitReplyType;

    /**
     *回复删除权限0 回帖可删除1回帖不可删除)
     */
    private String limitReplyDelType;

    /**
     * 访问权限0 任何人可访问 1仅成员可访问 2访问可申请
     */
    private String limitAccessType;

    /**
     * 发帖限制0 成员可发帖1禁止发帖
     */
    private String limitCreateTopicType;

    /**
     * 修帖权限0 允许修改帖子 1不允许修改帖子
     */
    private String limitModifyTopicType;

    /**
     * 删帖权限0允许删除 1不允许删除
     */
    private String limitDelTopicType;

    /**
     *  图片上传权限0 成员可上传图片1成员禁止上传图片
     */
    private String limitCreatePicType;

    /**
     * 图片删除权限0 允许删除 1不允许删
     */
    private String limitDelPicType;

    /**
     * 是否删除:0 正常 1 删除
     */
    private String limitDelFlag;

    public Long getLimitId() {
        return limitId;
    }

    public void setLimitId(Long limitId) {
        this.limitId = limitId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getLimitAddType() {
        return limitAddType;
    }

    public void setLimitAddType(String limitAddType) {
        this.limitAddType = limitAddType;
    }

    public String getLimitReplyType() {
        return limitReplyType;
    }

    public void setLimitReplyType(String limitReplyType) {
        this.limitReplyType = limitReplyType;
    }

    public String getLimitReplyDelType() {
        return limitReplyDelType;
    }

    public void setLimitReplyDelType(String limitReplyDelType) {
        this.limitReplyDelType = limitReplyDelType;
    }

    public String getLimitAccessType() {
        return limitAccessType;
    }

    public void setLimitAccessType(String limitAccessType) {
        this.limitAccessType = limitAccessType;
    }

    public String getLimitCreateTopicType() {
        return limitCreateTopicType;
    }

    public void setLimitCreateTopicType(String limitCreateTopicType) {
        this.limitCreateTopicType = limitCreateTopicType;
    }

    public String getLimitModifyTopicType() {
        return limitModifyTopicType;
    }

    public void setLimitModifyTopicType(String limitModifyTopicType) {
        this.limitModifyTopicType = limitModifyTopicType;
    }

    public String getLimitDelTopicType() {
        return limitDelTopicType;
    }

    public void setLimitDelTopicType(String limitDelTopicType) {
        this.limitDelTopicType = limitDelTopicType;
    }

    public String getLimitCreatePicType() {
        return limitCreatePicType;
    }

    public void setLimitCreatePicType(String limitCreatePicType) {
        this.limitCreatePicType = limitCreatePicType;
    }

    public String getLimitDelPicType() {
        return limitDelPicType;
    }

    public void setLimitDelPicType(String limitDelPicType) {
        this.limitDelPicType = limitDelPicType;
    }

    public String getLimitDelFlag() {
        return limitDelFlag;
    }

    public void setLimitDelFlag(String limitDelFlag) {
        this.limitDelFlag = limitDelFlag;
    }

    public String getLimitCondition() {
        return limitCondition;
    }

    public void setLimitCondition(String limitCondition) {
        this.limitCondition = limitCondition;
    }

}
