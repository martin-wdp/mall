package com.ningpai.customer.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 处罚记录
 * */
public class PunishRecord {
    /*
    *主键id
    */
    private Long id;
    /*
    * 第三方id
    * */
    private Long thirdId;
    /*
        * 处罚记录id
        * */
    private Long punishId;
    /*
    * 处罚原因
    * */
    private String punishReason;
    /*
    * 创建时间
    * */
    private Date createTime;

    private Long operatorId;

    private String operatorName;

    private Date closeStime;

    private Date closeEtime;

    private Integer reducePoint;

    private BigDecimal reduceMoney;

    private String rmoneyState;

    private Integer limitCount;

    private Date limitStime;

    private Date limitEtime;

    private String storeName;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    /**
     * 获取关闭时间
     * */
    public Date getCloseStime() {

        if (this.closeStime != null) {
            return new Date(this.closeStime.getTime()); // 正确值
        } else {
            return null;
        }
    }
    /**
     * 设置关闭时间
     * */
    public void setCloseStime(Date closeStime) {

        if (closeStime != null) {
            Date tEmp = (Date) closeStime.clone();
            if (tEmp != null) {
                this.closeStime = tEmp;
            }
        }
    }
    /**
     * 获取关闭时间
     * */
    public Date getCloseEtime() {
        if (this.closeEtime != null) {
            return new Date(this.closeEtime.getTime()); // 正确值
        } else {
            return null;
        }
    }
    /**
     * 设置关闭时间
     * */
    public void setCloseEtime(Date closeEtime) {
        if (closeEtime != null) {
            Date tEmp = (Date) closeEtime.clone();
            if (tEmp != null) {
                this.closeEtime = tEmp;
            }
        }
    }
    /**
     * 获取极限时间
     * */
    public Date getLimitStime() {
        if (this.limitStime != null) {
            return new Date(this.limitStime.getTime()); // 正确值
        } else {
            return null;
        }
    }
    /**
     * 设置极限时间
     * */
    public void setLimitStime(Date limitStime) {
        if (limitStime != null) {
            Date tEmp = (Date) limitStime.clone();
            if (tEmp != null) {
                this.limitStime = tEmp;
            }
        }
    }
    /**
     * 获取极限时间
     * */
    public Date getLimitEtime() {
        if (this.limitEtime != null) {
            return new Date(this.limitEtime.getTime()); // 正确值
        } else {
            return null;
        }
    }
    /**
     * 设置极限时间
     * */
    public void setLimitEtime(Date limitEtime) {
        if (limitEtime != null) {
            Date tEmp = (Date) limitEtime.clone();
            if (tEmp != null) {
                this.limitEtime = tEmp;
            }
        }
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

    public Long getPunishId() {
        return punishId;
    }

    public void setPunishId(Long punishId) {
        this.punishId = punishId;
    }

    public String getPunishReason() {
        return punishReason;
    }

    public void setPunishReason(String punishReason) {
        this.punishReason = punishReason;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getReducePoint() {
        return reducePoint;
    }

    public void setReducePoint(Integer reducePoint) {
        this.reducePoint = reducePoint;
    }

    public BigDecimal getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(BigDecimal reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    public String getRmoneyState() {
        return rmoneyState;
    }

    public void setRmoneyState(String rmoneyState) {
        this.rmoneyState = rmoneyState;
    }

    public Integer getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(Integer limitCount) {
        this.limitCount = limitCount;
    }

}
