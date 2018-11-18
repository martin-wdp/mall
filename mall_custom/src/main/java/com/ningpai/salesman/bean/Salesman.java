package com.ningpai.salesman.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Salesman {
    private Long salesmanId;

    @NotNull
    @Length(min = 2, max = 50, message = "")
    private String salesmanName;

    @NotNull
    @Length(min = 2, max = 100, message = "")
    private String salesmanPhone;

    @NotNull
    @Length(min = 2, max = 100, message = "")
    private String salesmanDepartment;

    private String delFlag;

    private String isEnabled;

    private Date createTime;

    private Date modifyTime;

    public Long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Long salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName == null ? null : salesmanName.trim();
    }

    public String getSalesmanPhone() {
        return salesmanPhone;
    }

    public void setSalesmanPhone(String salesmanPhone) {
        this.salesmanPhone = salesmanPhone == null ? null : salesmanPhone.trim();
    }

    public String getSalesmanDepartment() {
        return salesmanDepartment;
    }

    public void setSalesmanDepartment(String salesmanDepartment) {
        this.salesmanDepartment = salesmanDepartment == null ? null : salesmanDepartment.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled == null ? null : isEnabled.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}