package com.ningpai.topic.bean;

import java.util.Date;

/**
 * 话题图片实体
 * 
 * @author qiyuanyuan
 * 
 */
public class GroupTopicImg {

    // 话题附件ID
    private Long topicImgId;

    // 话题ID
    private Long topicId;

    // 附件地址
    private String topicImgUrl;

    // 是否删除：0：正常 1：删除
    private String topicImgDelFlag;

    // 创建时间
    private Date topicImgCreateTime;

    // 附件类型：0图片 1视频
    private String topicType;

    public Long getTopicImgId() {
        return topicImgId;
    }

    public void setTopicImgId(Long topicImgId) {
        this.topicImgId = topicImgId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTopicImgUrl() {
        return topicImgUrl;
    }

    public void setTopicImgUrl(String topicImgUrl) {
        this.topicImgUrl = topicImgUrl;
    }

    public String getTopicImgDelFlag() {
        return topicImgDelFlag;
    }

    public void setTopicImgDelFlag(String topicImgDelFlag) {
        this.topicImgDelFlag = topicImgDelFlag;
    }
    /**
     * 获取创建时间
     * */
    public Date getTopicImgCreateTime() {
        return (Date) topicImgCreateTime.clone();
    }
    /**
     * 设置创建时间
     * */
    public void setTopicImgCreateTime(Date topicImgCreateTime) {
        this.topicImgCreateTime = topicImgCreateTime == null ? null : (Date) topicImgCreateTime.clone();
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }
}
