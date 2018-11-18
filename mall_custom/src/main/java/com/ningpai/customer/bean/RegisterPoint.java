package com.ningpai.customer.bean;

import java.util.Date;

/***
 * 推荐注册赠送积分明细
 * 
 * @author zhanghailong
 *
 */
public class RegisterPoint {
    // 赠送积分明细ID
    private Long regPointId;
    // 推荐人
    private String regPointReferee;
    // 被推荐人
    private String regPointRecom;
    // 推荐注册时间
    private Date regPointTime;
    // 赠送的积分
    private Integer regPointNumber;
    // 开始时间(转换后)
    private Date startTime;
    // 结束时间(转换后)
    private Date endTime;
    // 开始时间
    private String createTimeF;
    // 结束时间
    private String createTimeT;

    public RegisterPoint() {

    }

    /**
     * 获取开始时间
     * */
    public Date getStartTime() {
        return (Date) startTime.clone();
    }

    /**
     * 设置开始时间
     * */
    public void setStartTime(Date startTime) {
        this.startTime = startTime == null ? null : (Date) startTime.clone();
    }

    public String getCreateTimeF() {
        return createTimeF;
    }

    public void setCreateTimeF(String createTimeF) {
        this.createTimeF = createTimeF;
    }

    public String getCreateTimeT() {
        return createTimeT;
    }

    public void setCreateTimeT(String createTimeT) {
        this.createTimeT = createTimeT;
    }
    /**
     * 获取结束时间
     * */
    public Date getEndTime() {
        return (Date) endTime.clone();
    }

    /**
     * 设置结束时间
     * */
    public void setEndTime(Date endTime) {
        this.endTime = endTime == null ? null : (Date) endTime.clone();
    }

    public Long getRegPointId() {
        return regPointId;
    }

    public void setRegPointId(Long regPointId) {
        this.regPointId = regPointId;
    }

    public String getRegPointReferee() {
        return regPointReferee;
    }

    public void setRegPointReferee(String regPointReferee) {
        this.regPointReferee = regPointReferee;
    }

    public String getRegPointRecom() {
        return regPointRecom;
    }

    public void setRegPointRecom(String regPointRecom) {
        this.regPointRecom = regPointRecom;
    }
    /**
     * 获取注册时间
     * */
    public Date getRegPointTime() {
        return (Date) regPointTime.clone();
    }
    /**
     * 修改注册时间
     * */
    public void setRegPointTime(Date regPointTime) {
        this.regPointTime = regPointTime == null ? null : (Date) regPointTime.clone();
    }

    public Integer getRegPointNumber() {
        return regPointNumber;
    }

    public void setRegPointNumber(Integer regPointNumber) {
        this.regPointNumber = regPointNumber;
    }

}
