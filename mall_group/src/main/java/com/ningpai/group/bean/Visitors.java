package com.ningpai.group.bean;



import java.util.Date;

/**
 * 访客信息表
 * @author qiyuanyuan
 *
 */
public class Visitors {
    /**
     * 访客信息Id
     */
    private Long visitorsId;
    /**
     * 访客Id
     */
    private Long visitorscustomerId;
    /**
     * 用户Id
     */
    private Long customerId;
    /**
     * 访问时间
     */
    private Date visitorsTime;
    /**
     * 访客Ip
     */
    private String visitorsIp;
    /**
     * 头像
     */
    private String infoHeadimg;
    /**
     * 城市
     */
    private String cityName;
    /**
     * 用户名
     */
    private String memberName;
    /**
     * 昵称
     */
    private String memberNickname;
    /**
     *
     */
    private String fansFlag ;
    /**
     *
     */
    private String provinceName;
    
    
    
    
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getFansFlag() {
        return fansFlag;
    }

    public void setFansFlag(String fansFlag) {
        this.fansFlag = fansFlag;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public String getInfoHeadimg() {
        return infoHeadimg;
    }

    public void setInfoHeadimg(String infoHeadimg) {
        this.infoHeadimg = infoHeadimg;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Long getVisitorsId() {
        return visitorsId;
    }

    public void setVisitorsId(Long visitorsId) {
        this.visitorsId = visitorsId;
    }

    public Long getVisitorscustomerId() {
        return visitorscustomerId;
    }

    public void setVisitorscustomerId(Long visitorscustomerId) {
        this.visitorscustomerId = visitorscustomerId;
    }

    public Date getVisitorsTime() {
        return visitorsTime;
    }

    public void setVisitorsTime(Date visitorsTime) {
        this.visitorsTime = visitorsTime;
    }

    public String getVisitorsIp() {
        return visitorsIp;
    }

    public void setVisitorsIp(String visitorsIp) {
        this.visitorsIp = visitorsIp;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
