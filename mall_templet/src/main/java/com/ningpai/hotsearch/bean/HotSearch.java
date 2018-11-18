package com.ningpai.hotsearch.bean;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 热门搜索类
 * 
 * @author YANhb
 * 
 */
public class HotSearch  implements Serializable {
    /**
     * @see #getHotSearchId()
     * @see #setHotSearchId(Long) 热门搜索Id
     */
    private Long hotSearchId;

    /**
     * 热门搜索关键字
     * 
     * @see #getKeyword()
     * @see #setKeyword(String)
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String keyword;
    /**
     * 模板ID
     */
    private Long tempid;
    /**
     * 频道Id
     */
    private Long channelid;
    /**
     * 搜索排序
     */
    private Integer sort;
    /**
     * 删除标记 0：未删除 1已删除
     */
    private String delFlag;
    /**
     * 创建用户ID
     */
    private Long createUserid;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改用户Id
     */
    private Long updateUserid;
    /**
     * 修改时间
     */
    private Date updateDate;
    /**
     * 备用
     */
    private String temp1;
    /**
     * 备用
     */
    private String temp2;
    /**
     * 备用
     */
    private String temp3;
    /**
     * 备用
     */
    private String temp4;
    /**
     * 备用
     */
    private String temp5;

    public Long getHotSearchId() {
        return hotSearchId;
    }

    public void setHotSearchId(Long hotSearchId) {
        this.hotSearchId = hotSearchId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getTempid() {
        return tempid;
    }

    public void setTempid(Long tempid) {
        this.tempid = tempid;
    }

    public Long getChannelid() {
        return channelid;
    }

    public void setChannelid(Long channelid) {
        this.channelid = channelid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(Long createUserid) {
        this.createUserid = createUserid;
    }

    /**
     * 时间
     * 
     * @return Date
     */
    public Date getCreateDate() {
        if (this.createDate != null) {
            return new Date(this.createDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 时间
     */
    public void setCreateDate(Date createDate) {
        if (createDate != null) {
            Date tEmp = (Date) createDate.clone();
            if (tEmp != null) {
                this.createDate = tEmp;
            }
        }
    }

    public Long getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(Long updateUserid) {
        this.updateUserid = updateUserid;
    }

    /**
     * 时间
     * 
     * @return Date
     */
    public Date getUpdateDate() {
        if (this.updateDate != null) {
            return new Date(this.updateDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 时间
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
