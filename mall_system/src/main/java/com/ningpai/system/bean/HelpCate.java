/*
 * Copyright 2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.system.bean;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 帮助中心分类实体
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月20日 上午11:03:35
 * @version 1.0
 */
public class HelpCate {
    /*
     *  帮助中心分类ID
     */
    private Long helpcateId;
    /* 
     * 帮助中心分类名称
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String helpcateName;
    /* 
     * 分类图片
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String helpcateImg;
    /*
     *  分类排序
     */
    private Integer helpcateSort;
    /*
     *  创建时间
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
    /*
     *  是否显示底部 0 否 1是
     */
    private String homeFloor;
    /*
     *  是否显示主页 0否 1是
     */
    private String homeShow;

    private List<HelpCenter> childs;

    public String getHomeFloor() {
        return homeFloor;
    }

    public void setHomeFloor(String homeFloor) {
        this.homeFloor = homeFloor;
    }

    public String getHomeShow() {
        return homeShow;
    }

    public void setHomeShow(String homeShow) {
        this.homeShow = homeShow;
    }

    public Long getHelpcateId() {
        return helpcateId;
    }

    public void setHelpcateId(Long helpcateId) {
        this.helpcateId = helpcateId;
    }

    public String getHelpcateName() {
        return helpcateName;
    }

    public void setHelpcateName(String helpcateName) {
        this.helpcateName = helpcateName;
    }

    public String getHelpcateImg() {
        return helpcateImg;
    }

    public void setHelpcateImg(String helpcateImg) {
        this.helpcateImg = helpcateImg;
    }

    public Integer getHelpcateSort() {
        return helpcateSort;
    }

    public void setHelpcateSort(Integer helpcateSort) {
        this.helpcateSort = helpcateSort;
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

    public List<HelpCenter> getChilds() {
        return childs;
    }

    public void setChilds(List<HelpCenter> childs) {
        this.childs = childs;
    }

}
