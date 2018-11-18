package com.ningpai.mobile.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 实体类-移动版单页表
 * 
 * @author zhangsl
 * @since 2014年11月20日 上午9:49:32
 * @version 0.0.1
 */
public class MobSinglepage {
    /**
     * 编号
     * 
     * @see #getSinglepageId()
     * @see #setSinglepageId(Long)
     */
    private Long singlepageId;

    /**
     * 商家ID
     * 
     * @see #getMerchantId()
     * @see #setMerchantId(Long)
     */
    private Long merchantId;

    /**
     * 标题
     * 
     * @see #getTitle()
     * @see #setTitle(String)
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String title;

    /**
     * SEO关键字
     * 
     * @see #getKeyword()
     * @see #setKeyword(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String keyword;

    /**
     * SEO描述
     * 
     * @see #getDescription()
     * @see #setDescription(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String description;

    /**
     * 标示ID
     * 
     * @see #getMarkId()
     * @see #setMarkId(Long)
     */
    private Long markId;

    /**
     * 是否显示 0:不显示 1：显示
     * 
     * @see #getIsShow()
     * @see #setIsShow(String)
     */
    private String isShow;

    /**
     * 是否删除 0：未删除 1：删除
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
     */
    private Date createDate;

    /**
     * 更新人
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

    /**
     * 内容
     * 
     * @see #getContent()
     * @see #setContent(String)
     */
    private String content;

    public Long getSinglepageId() {
        return singlepageId;
    }

    public void setSinglepageId(Long singlepageId) {
        this.singlepageId = singlepageId;
    }

    /**
     * 获取商家ID
     * 
     * @return
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * 设置商家ID
     * 
     * @param merchantId
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 获取标题
     * 
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     * 
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
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
     * 获取修改时间
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
     * 设置修改时间
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
