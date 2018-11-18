package com.ningpai.group.vo;

import java.util.Date;
import java.util.List;

import com.ningpai.group.bean.CustomerReply;

/**
 * 心情
 * 
 * @author ggn
 *
 */
public class MoodVo {
    private Long moodId;

    private Long customerId;
    // 内容
    private String moodContent;
    // 创建实际那
    private Date moodCreateTime;
    // 用户名
    private String memberName;
    // 昵称
    private String memberNickname;

    private String infoHeadimg;

    private List<CustomerReply> moodReply;

    // 是否删除
    private String moodDelFlag;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public List<CustomerReply> getMoodReply() {
        return moodReply;
    }

    public void setMoodReply(List<CustomerReply> moodReply) {
        this.moodReply = moodReply;
    }

    public String getInfoHeadimg() {
        return infoHeadimg;
    }

    public void setInfoHeadimg(String infoHeadimg) {
        this.infoHeadimg = infoHeadimg;
    }

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
