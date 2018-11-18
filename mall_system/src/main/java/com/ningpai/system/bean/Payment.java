package com.ningpai.system.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @ClassName: Payment
 * @Description: 实体类-支付方式
 * @author Wanghy
 * @date 2014年10月11日 下午2:38:06
 * 
 */
public class Payment {
    /** 编号 */
    private Long paymentId;
    /** 支付方式名称 */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String name;
    /** 是否开启 0：未开启 1：开启 */
    private String isOpen;
    /** 删除标记 0：未删除 1：已删除 */
    private String delflag;
    /** 创建人ID */
    private Long createUserId;
    /** 创建时间 */
    private Date createDate;
    /** 修改人ID */
    private Long updateUserId;
    /** 修改时间 */
    private Date updateDate;
    /** 扩展字段1 */
    private String temp1;
    /** 扩展字段2 */
    private String temp2;
    /** 扩展字段3 */
    private String temp3;
    /** 扩展字段4 */
    private String temp4;
    /** 扩展字段5 */
    private String temp5;

    /**
     * 获取paymentId
     * 
     * @see #paymentId
     * @return the paymentId
     */
    public Long getPaymentId() {
        return paymentId;
    }

    /**
     * 设置paymentId
     * 
     * @see #paymentId
     * @param paymentId
     *            the paymentId to set
     */
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * 获取name
     * 
     * @see #name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name
     * 
     * @see #name
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取isOpen
     * 
     * @see #isOpen
     * @return the isOpen
     */
    public String getIsOpen() {
        return isOpen;
    }

    /**
     * 设置isOpen
     * 
     * @see #isOpen
     * @param isOpen
     *            the isOpen to set
     */
    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    /**
     * 获取delflag
     * 
     * @see #delflag
     * @return the delflag
     */
    public String getDelflag() {
        return delflag;
    }

    /**
     * 设置delflag
     * 
     * @see #delflag
     * @param delflag
     *            the delflag to set
     */
    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    /**
     * 获取createUserId
     * 
     * @see #createUserId
     * @return the createUserId
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置createUserId
     * 
     * @see #createUserId
     * @param createUserId
     *            the createUserId to set
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取createDate
     * 
     * @see #createDate
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置createDate
     * 
     * @see #createDate
     * @param createDate
     *            the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取updateUserId
     * 
     * @see #updateUserId
     * @return the updateUserId
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 设置updateUserId
     * 
     * @see #updateUserId
     * @param updateUserId
     *            the updateUserId to set
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 获取updateDate
     * 
     * @see #updateDate
     * @return the updateDate
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置updateDate
     * 
     * @see #updateDate
     * @param updateDate
     *            the updateDate to set
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取temp1
     * 
     * @see #temp1
     * @return the temp1
     */
    public String getTemp1() {
        return temp1;
    }

    /**
     * 设置temp1
     * 
     * @see #temp1
     * @param temp1
     *            the temp1 to set
     */
    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    /**
     * 获取temp2
     * 
     * @see #temp2
     * @return the temp2
     */
    public String getTemp2() {
        return temp2;
    }

    /**
     * 设置temp2
     * 
     * @see #temp2
     * @param temp2
     *            the temp2 to set
     */
    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    /**
     * 获取temp3
     * 
     * @see #temp3
     * @return the temp3
     */
    public String getTemp3() {
        return temp3;
    }

    /**
     * 设置temp3
     * 
     * @see #temp3
     * @param temp3
     *            the temp3 to set
     */
    public void setTemp3(String temp3) {
        this.temp3 = temp3;
    }

    /**
     * 获取temp4
     * 
     * @see #temp4
     * @return the temp4
     */
    public String getTemp4() {
        return temp4;
    }

    /**
     * 设置temp4
     * 
     * @see #temp4
     * @param temp4
     *            the temp4 to set
     */
    public void setTemp4(String temp4) {
        this.temp4 = temp4;
    }

    /**
     * 获取temp5
     * 
     * @see #temp5
     * @return the temp5
     */
    public String getTemp5() {
        return temp5;
    }

    /**
     * 设置temp5
     * 
     * @see #temp5
     * @param temp5
     *            the temp5 to set
     */
    public void setTemp5(String temp5) {
        this.temp5 = temp5;
    }

}
