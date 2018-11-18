package com.ningpai.group.bean;

import java.util.Date;

/**
 * 粉丝实体类
 * 
 * @author qiyuanyuan
 */
public class Fans {

    private Long fansId;

    /**
     * 用户Id
     */
    private Long customerId;

    /**
     * 粉丝Id
     */
    private Long fansCustomerId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 粉丝关注状态
     */
    private String fansFlag;

    /**
     * 用户姓名
     */
    private String customerName;

    /**
     * 省
     */
    private String proName;

    /**
     * 市
     */
    private String cityName;

    /**
     * 头像
     */
    private String infoHeadimg;

    public Long getFansId() {
        return fansId;
    }

    public void setFansId(Long fansId) {
        this.fansId = fansId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getFansCustomerId() {
        return fansCustomerId;
    }

    public void setFansCustomerId(Long fansCustomerId) {
        this.fansCustomerId = fansCustomerId;
    }

    /**
     * get创建时间
     * 
     */
    public Date getCreateTime() {
        return (Date) createTime.clone();
    }

    /**
     * set创建时间
     * 创建时间不为空时复制时间对象
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime == null ? null : (Date) createTime.clone();
    }

    public String getFansFlag() {
        return fansFlag;
    }

    public void setFansFlag(String fansFlag) {
        this.fansFlag = fansFlag;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getInfoHeadimg() {
        return infoHeadimg;
    }

    public void setInfoHeadimg(String infoHeadimg) {
        this.infoHeadimg = infoHeadimg;
    }

}
