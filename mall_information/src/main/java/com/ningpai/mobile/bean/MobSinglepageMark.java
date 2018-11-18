package com.ningpai.mobile.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 实体类-移动版单页标签表
 * 
 * @author zhangsl
 * @since 2014年11月20日 上午9:53:55
 * @version
 */
public class MobSinglepageMark {
    /**
     * 编号
     * 
     * @see #getMarkId()
     * @see #setMarkId(Long)
     */
    private Long markId;

    /**
     * 商家ID
     * 
     * @see #getMerchantId()
     * @see #setMerchantId(Long)
     */
    private Long merchantId;

    /**
     * 标示名称
     * 
     * @see #getName()
     * @see #setName(String)
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String name;

    /**
     * 是否删除 0:未删除 1：已删除
     * 
     * @see #getDelflag()
     * @see #setDelflag(String)
     */
    private String delflag;

    /**
     * 创建人
     * 
     * @see #getCreateUserId()
     * @see #setCreateUserId(Long)
     */
    private Long createUserId;

    /**
     * 创建时间
     * 
     * @see #getCreateDate()
     * @see #setCreateDate(java.util.Date)
     * 
     */
    private Date createDate;

    /**
     * 更新人ID
     * 
     * @see #getUpdateUserId()
     * @see #setUpdateUserId(Long)
     */
    private Long updateUserId;

    /**
     * 更新时间
     * 
     * @see #getUpdateDate()
     * @see #setUpdateDate(java.util.Date)
     */
    private Date updateDate;

    private String temp1;

    private String temp2;

    private String temp3;

    private String temp4;

    private String temp5;

    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取创建时间
     * 
     * @return
     */
    public Date getCreateDate() {
        if (this.createDate != null) {
            return new Date(this.createDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置创建时间
     * 
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        if (createDate != null) {
            Date tEmp = (Date) createDate.clone();
            if (tEmp != null) {
                this.createDate = tEmp;
            }
        }
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 获取更新时间
     * 
     * @return
     */
    public Date getUpdateDate() {
        if (this.updateDate != null) {
            return new Date(this.updateDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置更新时间
     * 
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        if (updateDate != null) {
            Date tEmp = (Date) updateDate.clone();
            if (tEmp != null) {
                this.updateDate = tEmp;
            }
        }
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public String getTemp3() {
        return temp3;
    }

    public void setTemp3(String temp3) {
        this.temp3 = temp3;
    }

    public String getTemp4() {
        return temp4;
    }

    public void setTemp4(String temp4) {
        this.temp4 = temp4;
    }

    public String getTemp5() {
        return temp5;
    }

    public void setTemp5(String temp5) {
        this.temp5 = temp5;
    }
}
