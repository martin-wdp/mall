package com.ningpai.grant.bean;

import java.util.Date;

/**
 * 商品品牌实体类
 *
 * */
public class GrandBrand {
    /*
     *主键ID
      */
    private Long id;

    /*
     *商家ID
      */
    private Long thirdId;

    /*
     *品牌ID
      */
    private Long brandId;

    /*
     *删除标记
      */
    private String delFlag;

    /*
     *修改时间
      */
    private Date modifyTime;

    /*
     *审核状态 0:未审核 1:审核通过 2:拒绝
      */
    private String rateStatus;

    /*
     *拒绝原因
      */
    private String reason;

    /*
     *审核通过时间
      */
    private Date rateTime;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取修改时间
     * */
    public Date getModifyTime() {
        if (this.modifyTime != null) {
            return new Date(this.modifyTime.getTime());
        }
        return null;
    }
    /**
     * 设置修改时间
     * */
    public void setModifyTime(Date modifyTime) {
        if (modifyTime != null) {
            Date tEmp = (Date) modifyTime.clone();
            if (tEmp != null) {
                this.modifyTime = tEmp;
            }
        }
    }

    public String getRateStatus() {
        return rateStatus;
    }

    public void setRateStatus(String rateStatus) {
        this.rateStatus = rateStatus;
    }
    /**
     * 获取审核通过时间
     * */
    public Date getRateTime() {
        if (this.rateTime != null) {
            return new Date(this.rateTime.getTime());
        }
        return null;
    }
    /**
     * 设置审核通过时间
     * */
    public void setRateTime(Date rateTime) {
        if (rateTime != null) {
            Date tEmp = (Date) rateTime.clone();
            if (tEmp != null) {
                this.rateTime = tEmp;
            }
        }
    }
}
