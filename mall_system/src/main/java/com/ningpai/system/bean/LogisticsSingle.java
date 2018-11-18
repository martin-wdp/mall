package com.ningpai.system.bean;

import java.util.Date;

/**
 * 快递单模板
 * 
 * @author rennn
 *
 */
public class LogisticsSingle {
    /*
     * 快递单模板编号
     */
    private Long logisticsSingleId;
    /*
     * 物流公司编号
     */
    private Long companyId;
    /*
     * 商家编号
     */
    private Long thirdId;
    /*
     * 尺寸宽度
     */
    private Long logisticsSingleWidth;
    /*
     * 尺寸高度
     */
    private Long logisticsSingleHeight;
    /*
     * 背景图片
     */
    private String logisticsSingleImg;
    /*
     * 备用字段
     */
    private String logisticsSingleRemark;
    /*
     * 创建时间
     */
    private Date createTime;
    /*
     * 修改时间
     */
    private Date modifyTime;
    /*
     * 是否删除 0正常 1删除
     */
    private String delFlag;
    /*
     * 物流单内容
     */
    private String logisticsSingleContent;
    /*
     * 物流公司名称
     */
    private String companyName;
    /*
     * 商家名称
     */
    private String storeName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Long getLogisticsSingleId() {
        return logisticsSingleId;
    }

    public void setLogisticsSingleId(Long logisticsSingleId) {
        this.logisticsSingleId = logisticsSingleId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    public Long getLogisticsSingleWidth() {
        return logisticsSingleWidth;
    }

    public void setLogisticsSingleWidth(Long logisticsSingleWidth) {
        this.logisticsSingleWidth = logisticsSingleWidth;
    }

    public Long getLogisticsSingleHeight() {
        return logisticsSingleHeight;
    }

    public void setLogisticsSingleHeight(Long logisticsSingleHeight) {
        this.logisticsSingleHeight = logisticsSingleHeight;
    }

    public String getLogisticsSingleImg() {
        return logisticsSingleImg;
    }

    public void setLogisticsSingleImg(String logisticsSingleImg) {
        this.logisticsSingleImg = logisticsSingleImg;
    }

    public String getLogisticsSingleRemark() {
        return logisticsSingleRemark;
    }

        public void setLogisticsSingleRemark(String logisticsSingleRemark) {
        this.logisticsSingleRemark = logisticsSingleRemark;
    }
    /**
     * 时间
     * @return
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 时间
     * @return
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime == null ? null : (Date) createTime.clone();
    }
    /**
     * 时间
     * @return
     */
    public Date getModifyTime() {
        if(modifyTime!=null){
            return (Date) modifyTime.clone();
        }else{
            return null;
        }
    }
    /**
     * 时间
     * @return
     */
    public LogisticsSingle setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime == null ? null : (Date) modifyTime.clone();
        return this;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getLogisticsSingleContent() {
        return logisticsSingleContent;
    }

    public void setLogisticsSingleContent(String logisticsSingleContent) {
        this.logisticsSingleContent = logisticsSingleContent;
    }
}
