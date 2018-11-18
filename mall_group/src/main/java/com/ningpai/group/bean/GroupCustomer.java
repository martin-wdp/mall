package com.ningpai.group.bean;

import java.util.Date;

/**
 * 小组成员信息实体
 * 
 * @author qiyuanyuan
 * 
 */
public class GroupCustomer {

    /**
     * 主键ID
     */
    private Long groupCustomerId;

    /**
     * 小组ID
     */
    private Long groupId;

    /**
     * 成员ID
     */
    private Long customerId;

    /**
     * 成员权限 0普通成员 1管理员 2组长
     */
    private String customerPower;

    /**
     * 是否删除 0：正常 1：删除
     */
    private String delFlag;

    /**
     * 成员创建时间
     */
    private Date customerCreateTime;

    /**
     * 成员姓名
     */
    private String customerName;

    /**
     * 成员头像
     */
    private String infoHeadimg;

    /**
     * 分页开始的条数
     */
    private int startRowNum;

    /**
     * 分页结束的条数
     */
    private int endRowNum;

    /**
     * 现在显示数量
     */
    private int limitNum;

    /**
     * 省名
     */
    private String provinceName;

    /**
     * 市名
     */
    private String cityName;

    /**
     * 分页标记
     */
    private String pageFlag;

    /**
     * 1 共同小组， 2共同城市 3共同好友
     */
    private Integer focusFlag;

    /**
     * fansFlag
     */
    private String fansFlag;

    public Long getGroupCustomerId() {
        return groupCustomerId;
    }

    public void setGroupCustomerId(Long groupCustomerId) {
        this.groupCustomerId = groupCustomerId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerPower() {
        return customerPower;
    }

    public void setCustomerPower(String customerPower) {
        this.customerPower = customerPower;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getStartRowNum() {
        return startRowNum;
    }

    public void setStartRowNum(int startRowNum) {
        this.startRowNum = startRowNum;
    }

    public int getEndRowNum() {
        return endRowNum;
    }

    public void setEndRowNum(int endRowNum) {
        this.endRowNum = endRowNum;
    }

    public int getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }

    /**
     * get创建时间
     * 
     */
    public Date getCustomerCreateTime() {
        return (Date) customerCreateTime.clone();
    }

    /**
     * set创建时间
     * 创建时间不为空时复制时间对象
     */
    public void setCustomerCreateTime(Date customerCreateTime) {
        this.customerCreateTime = customerCreateTime == null ? null : (Date) customerCreateTime.clone();
    }

    public String getInfoHeadimg() {
        return infoHeadimg;
    }

    public void setInfoHeadimg(String infoHeadimg) {
        this.infoHeadimg = infoHeadimg;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPageFlag() {
        return pageFlag;
    }

    public void setPageFlag(String pageFlag) {
        this.pageFlag = pageFlag;
    }

    public Integer getFocusFlag() {
        return focusFlag;
    }

    public void setFocusFlag(Integer focusFlag) {
        this.focusFlag = focusFlag;
    }

    public String getFansFlag() {
        return fansFlag;
    }

    public void setFansFlag(String fansFlag) {
        this.fansFlag = fansFlag;
    }

}
