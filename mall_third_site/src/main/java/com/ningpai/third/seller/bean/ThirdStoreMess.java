package com.ningpai.third.seller.bean;

import java.util.Date;

/**
 * 模块消息接收方式
 * 
 * @author zhangqiang
 * @since 2014年6月11日 下午7:17:38
 * @version 2.0
 */
public class ThirdStoreMess {
    /**
     * 关联编号
     */
    private Long relaId;
    /**
     * 店铺ID
     */
    private Long storeId;
    /**
     * 消息模块编号
     */
    private Long messModId;
    /**
     * 消息接收方式
     */
    private String messRecType;
    /**
     * 创建时间
     */
    private Date relaCreateTime;
    /**
     * 修改时间
     */
    private Date relaModTime;
    /**
     * 删除时间
     */
    private Date relaDelTime;
    /**
     * 是否删除
     */
    private String delFlag;
    /**
     * 邮箱
     */
    private String relaEmail;
    /**
     * 手机
     */
    private String relaMobile;

    public Long getRelaId() {
        return relaId;
    }

    public void setRelaId(Long relaId) {
        this.relaId = relaId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getMessModId() {
        return messModId;
    }

    public void setMessModId(Long messModId) {
        this.messModId = messModId;
    }

    public String getMessRecType() {
        return messRecType;
    }

    public void setMessRecType(String messRecType) {
        this.messRecType = messRecType;
    }

    /**
     * 获取创建时间
     * @return
     */
    public Date getRelaCreateTime() {
        if (this.relaCreateTime != null) {
            return new Date(this.relaCreateTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置创建日期
     * @param relaCreateTime
     */
    public void setRelaCreateTime(Date relaCreateTime) {
        if (relaCreateTime != null) {
            Date tEmp = (Date) relaCreateTime.clone();
            if (tEmp != null) {
                this.relaCreateTime = tEmp;
            }
        }
    }

    /**
     * 获取修改时间
     * @return
     */
    public Date getRelaModTime() {
        if (this.relaModTime != null) {
            return new Date(this.relaModTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置修改时间
     * @param relaModTime
     */
    public void setRelaModTime(Date relaModTime) {
        if (relaModTime != null) {
            Date tEmp = (Date) relaModTime.clone();
            if (tEmp != null) {
                this.relaModTime = tEmp;
            }
        }
    }

    /**
     * 获取删除时间
     * @return
     */
    public Date getRelaDelTime() {
        if (this.relaDelTime != null) {
            return new Date(this.relaDelTime.getTime());
        } else {
            return null;
        }
    }


    /**
     * 设置删除日期
     * @param relaDelTime
     */
    public void setRelaDelTime(Date relaDelTime) {
        if (relaDelTime != null) {
            Date tEmp = (Date) relaDelTime.clone();
            if (tEmp != null) {
                this.relaDelTime = tEmp;
            }
        }
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getRelaEmail() {
        return relaEmail;
    }

    public void setRelaEmail(String relaEmail) {
        this.relaEmail = relaEmail;
    }

    public String getRelaMobile() {
        return relaMobile;
    }

    public void setRelaMobile(String relaMobile) {
        this.relaMobile = relaMobile;
    }
}
