package com.ningpai.group.bean;

import java.util.Date;

/**
 * 用户
 */
public class Mood {
    /**
     * 编号
     */
    private Long moodId;
    /**
     * 用户Id
     */
    private Long customerId;
    /**
     * 心情内容
     */
    private String moodContent;
    /**
     * 创建时间
     */
    private Date moodCreateTime;
    /**
     * 是否删除
     */
    private String moodDelFlag;
    public Long getMoodId() {
        return moodId;
    }
    public void setMoodId(Long moodId) {
        this.moodId = moodId;
    }
    
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public String getMoodContent() {
        return moodContent;
    }
    public void setMoodContent(String moodContent) {
        this.moodContent = moodContent;
    }
    public Date getMoodCreateTime() {
        return moodCreateTime;
    }
    public void setMoodCreateTime(Date moodCreateTime) {
        this.moodCreateTime = moodCreateTime;
    }
    public String getMoodDelFlag() {
        return moodDelFlag;
    }
    public void setMoodDelFlag(String moodDelFlag) {
        this.moodDelFlag = moodDelFlag;
    }

}
