package com.ningpai.system.bean;

import java.util.Date;

/**
 * 首页广告类型表
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月14日 下午6:57:07
 * @version 1.0
 */
public class AdverType {
    /*
     *  广告分类ID
     */
    private Long atId;
    /*
     *  广告分类名称
     */
    private String atName;
    /*
     *  创建时间
     */
    private Date createTime;
    /*
     * 修改时间
     */
    private Date modifyTime;
    /* 删除标记
     * 
     */
    private String delFlag;

    
    public Long getAtId() {
        return atId;
    }

    public void setAtId(Long atId) {
        this.atId = atId;
    }

    public String getAtName() {
        return atName;
    }

    public void setAtName(String atName) {
        this.atName = atName;
    }
    /**
     * 时间
     * @return
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        } else {
            return null;
        }
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
    public Date getModifyTime() {
        if (this.modifyTime != null) {
            return new Date(this.modifyTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 时间
     * @return
     */
    public void setModifyTime(Date modifyTime) {
        if (modifyTime != null) {
            Date tEmp = (Date) modifyTime.clone();
            if (tEmp != null) {
                this.modifyTime = tEmp;
            }
        }
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
