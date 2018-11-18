package com.ningpai.group.bean;

/**
 * 评论实体
 * @author qiyuanyuan
 */
public class CommonBean {

    /**
     * 系统消息
     */
    private Long sysCount;
    /**
     * 私信
     */
    private Long mesCount;
    /**
     * 评论
     */
    private Long replyCount;
    /**
     *回复
     */
    private Long reCount;
    /**
     * 关注
     */
    private Long guanzhu;
    /**
     * 粉丝
     */
    private Long fansCount;
    /**
     * 心情
     */
    private Long moodCount;
    
    public Long getSysCount() {
        return sysCount;
    }
    public void setSysCount(Long sysCount) {
        this.sysCount = sysCount;
    }
    public Long getMesCount() {
        return mesCount;
    }
    public void setMesCount(Long mesCount) {
        this.mesCount = mesCount;
    }
    public Long getReplyCount() {
        return replyCount;
    }
    public void setReplyCount(Long replyCount) {
        this.replyCount = replyCount;
    }
    public Long getReCount() {
        return reCount;
    }
    public void setReCount(Long reCount) {
        this.reCount = reCount;
    }
    public Long getGuanzhu() {
        return guanzhu;
    }
    public void setGuanzhu(Long guanzhu) {
        this.guanzhu = guanzhu;
    }
    public Long getFansCount() {
        return fansCount;
    }
    public void setFansCount(Long fansCount) {
        this.fansCount = fansCount;
    }
    public Long getMoodCount() {
        return moodCount;
    }
    public void setMoodCount(Long moodCount) {
        this.moodCount = moodCount;
    }
}
