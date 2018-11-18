package com.ningpai.group.bean;

/**
 * 黑名单实�?
 * 
 * @author qiyuanyuan
 * 
 */
public class GroupBlack {
    /**
     * 黑名单ID
     */
    private Long blackId;

    /**
     * 小组ID
     */
    private Long groupId;

    /**
     *  用户ID
     */
    private Long customerId;

    /**
     * 是否删除�?：正�? 1：删�?
     */
    private String blackDelFlag;

    /**
     * 拉黑名单
     */
    private String blackName;

    /**
     *  分页�?��的条�?
     */
    private int startRowNum;

    /**
     *  分页结束的条�?
     */
    private int endRowNum;

    /**
     * 用户头像
     */
    private String infoHeadimg;

    /**
     *  �?���?
     */
    private String provinceName;

    /**
     * �?���?
     */
    private String cityName;

    public Long getBlackId() {
        return blackId;
    }

    public void setBlackId(Long blackId) {
        this.blackId = blackId;
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

    public String getBlackDelFlag() {
        return blackDelFlag;
    }

    public void setBlackDelFlag(String blackDelFlag) {
        this.blackDelFlag = blackDelFlag;
    }

    public String getBlackName() {
        return blackName;
    }

    public void setBlackName(String blackName) {
        this.blackName = blackName;
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

}
