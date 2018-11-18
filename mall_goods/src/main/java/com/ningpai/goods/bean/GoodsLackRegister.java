package com.ningpai.goods.bean;

import java.util.Date;

/**
 * 到货通知实体类
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月18日 上午10:18:52
 * @version 1.0
 */
public class GoodsLackRegister {
    /*
     * 主键ID
     */
    private Long lackRegisterId;
    /*
     * 货品ID
     */
    private Long goodsInfoId;
    /*
     * 等级的会员ID
     */
    private Long customerId;
    /*
     * 通知状态
     */
    private String lackRegisterNoticeStatus;
    /*
     * 登记时间
     */
    private Date lackRegisterTime;
    /*
     * 通知时间
     */
    private Date lackRegisterNoticeTime;

    public Long getLackRegisterId() {
        return lackRegisterId;
    }

    public void setLackRegisterId(Long lackRegisterId) {
        this.lackRegisterId = lackRegisterId;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getLackRegisterNoticeStatus() {
        return lackRegisterNoticeStatus;
    }

    public void setLackRegisterNoticeStatus(String lackRegisterNoticeStatus) {
        this.lackRegisterNoticeStatus = lackRegisterNoticeStatus;
    }
    /**
     * 获取创建时间
     * */
    public Date getLackRegisterTime() {
        if (this.lackRegisterTime != null) {
            return new Date(this.lackRegisterTime.getTime());
        }
        return null;
    }
    /**
     * 设置创建时间
     * */
    public void setLackRegisterTime(Date lackRegisterTime) {
        if (lackRegisterTime != null) {
            Date tEmp = (Date) lackRegisterTime.clone();
            if (tEmp != null) {
                this.lackRegisterTime = tEmp;
            }
        }
    }
    /**
     * 获取通知时间
     * */
    public Date getLackRegisterNoticeTime() {
        if (this.lackRegisterNoticeTime != null) {
            return new Date(this.lackRegisterNoticeTime.getTime());
        }
        return null;
    }
    /**
     * 设置通知时间
     * */
    public void setLackRegisterNoticeTime(Date lackRegisterNoticeTime) {
        if (lackRegisterNoticeTime != null) {
            Date tEmp = (Date) lackRegisterNoticeTime.clone();
            if (tEmp != null) {
                this.lackRegisterNoticeTime = tEmp;
            }
        }
    }
}
