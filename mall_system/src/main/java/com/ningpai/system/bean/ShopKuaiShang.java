package com.ningpai.system.bean;

import java.util.Date;

/**
 * 快商通实体类
 */
public class ShopKuaiShang implements java.io.Serializable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /*
     * 快商通id
     */
    private Long shangId;
    /*
     * 快商通登录id
     */
    private String shangLongId;
    /*
     * 快商通登录名
     */
    private String shangLoginName;
    /*
     * 登录密码
     */
    private String password;
    /*
     * 公司名称
     */
    private String companyName;
    /*
     * 公司网址
     */
    private String companyUrl;
    /*
     * 公司行业
     */
    private String trade;
    /*
     * 联系人
     */
    private String linkman;
    /*
     * 联系电话
     */
    private String telephone;
    /*
     * 邮箱
     */
    private String email;
    /*
     * 手机电话
     */
    private String mobilephone;
    /*
     * 快商通logo
     */
    private String shangLogo;
    /*
     * 介绍
     */
    private String introduce;
    /*
     * 升级介绍
     */
    private String upgradeIntroduce;
    /*
     * 快商通代码
     */
    private String operation;
    /*
     * 客户端地址
     */
    private String clientAddress;
    /*
     * 快商通联系qq
     */
    private String shangContantQq;
    /*
     * 快商通联系电话
     */
    private String shangTelephone;
    /*
     * 快商通联系人
     */
    private String shangLinkman;
    /*
     * 是否启用0启用,1不启用
     */
    private String isuseing;
    /*
     * 是否启用0不删除,1删除
     */
    private String deleteFlag;
    /*
     * 新建时间
     */
    private Date createTime;
    /*
     * 更新时间
     */
    private Date updateTime;

    public Long getShangId() {
        return shangId;
    }

    public void setShangId(Long shangId) {
        this.shangId = shangId;
    }

    public String getShangLongId() {
        return shangLongId;
    }

    public void setShangLongId(String shangLongId) {
        this.shangLongId = shangLongId;
    }

    public String getShangLoginName() {
        return shangLoginName;
    }

    public void setShangLoginName(String shangLoginName) {
        this.shangLoginName = shangLoginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getShangLogo() {
        return shangLogo;
    }

    public void setShangLogo(String shangLogo) {
        this.shangLogo = shangLogo;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getUpgradeIntroduce() {
        return upgradeIntroduce;
    }

    public void setUpgradeIntroduce(String upgradeIntroduce) {
        this.upgradeIntroduce = upgradeIntroduce;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getShangContantQq() {
        return shangContantQq;
    }

    public void setShangContantQq(String shangContantQq) {
        this.shangContantQq = shangContantQq;
    }

    public String getShangTelephone() {
        return shangTelephone;
    }

    public void setShangTelephone(String shangTelephone) {
        this.shangTelephone = shangTelephone;
    }

    public String getShangLinkman() {
        return shangLinkman;
    }

    public void setShangLinkman(String shangLinkman) {
        this.shangLinkman = shangLinkman;
    }

    public String getIsuseing() {
        return isuseing;
    }

    public void setIsuseing(String isuseing) {
        this.isuseing = isuseing;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    /**
     * 时间
     * @return
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        }
        return null;
    }
    /**
     * 时间
     * @return
     */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                this.createTime = tEmp;
            }
        }
    }
    /**
     * 时间
     * @return
     */
    public Date getUpdateTime() {
        if (this.updateTime != null) {
            return new Date(this.updateTime.getTime());
        }
        return null;
    }
    /**
     * 时间
     * @return
     */
    public void setUpdateTime(Date updateTime) {
        if (updateTime != null) {
            Date tEmp = (Date) updateTime.clone();
            if (tEmp != null) {
                this.updateTime = tEmp;
            }
        }
    }
}
