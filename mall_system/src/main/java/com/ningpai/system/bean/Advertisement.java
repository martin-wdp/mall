package com.ningpai.system.bean;

import java.util.Date;

/**
 * 广告设置表
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月14日 下午6:55:04
 * @version 1.0
 */
public class Advertisement {
    /* 
     * 广告编号
     */
    private Long adverId;
    /* 
     * 广告名称
     */
    private String adverName;
    /*
     *  广告图片路径
     */
    private String adverPath;
    /*
     *  广告链接
     */
    private String adverHref;
    /*
     *  广告分类Id
     */
    private Long atId;
    /*
     *  广告位置
     */
    private Integer atPosition;
    /*
     *  排序
     */
    private Integer adverSort;
    /* 
     * 创建时间
     */
    private Date createTime;
    /*
     *  修改时间
     */
    private Date modifyTime;
    /*
     *  删除标记
     */
    private String delFlag;

    public Long getAdverId() {
        return adverId;
    }

    public void setAdverId(Long adverId) {
        this.adverId = adverId;
    }

    public String getAdverName() {
        return adverName;
    }

    public void setAdverName(String adverName) {
        this.adverName = adverName;
    }

    public String getAdverPath() {
        return adverPath;
    }

    public void setAdverPath(String adverPath) {
        this.adverPath = adverPath;
    }

    public String getAdverHref() {
        return adverHref;
    }

    public void setAdverHref(String adverHref) {
        this.adverHref = adverHref;
    }

    public Long getAtId() {
        return atId;
    }

    public void setAtId(Long atId) {
        this.atId = atId;
    }

    public Integer getAdverSort() {
        return adverSort;
    }

    public void setAdverSort(Integer adverSort) {
        this.adverSort = adverSort;
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

    public Integer getAtPosition() {
        return atPosition;
    }

    public void setAtPosition(Integer atPosition) {
        this.atPosition = atPosition;
    }
}
