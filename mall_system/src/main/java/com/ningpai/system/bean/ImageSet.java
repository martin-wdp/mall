/*
 * Copyright 2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.system.bean;

import java.util.Date;

/**
 * 商品图片设置表
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月17日 下午1:53:54
 * @version 1.0
 */
public class ImageSet {
    /*
     *  图片规则ID
     */
    private Long ruleId;
    /*
     *  图片类型名称
     */
    private String ruleName;
    /* 
     * 图片高度
     */
    private String ruleHeight;
    /* 
     * 图片宽度
     */
    private String ruleWidth;
    /* 
     * 图片存放路径
     */
    private String rulePath;
    /*
     *  图片后缀
     */
    private String ruleSuffix;
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

    public String getRulePath() {
        return rulePath;
    }

    public void setRulePath(String rulePath) {
        this.rulePath = rulePath;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleHeight() {
        return ruleHeight;
    }

    public void setRuleHeight(String ruleHeight) {
        this.ruleHeight = ruleHeight;
    }

    public String getRuleWidth() {
        return ruleWidth;
    }

    public void setRuleWidth(String ruleWidth) {
        this.ruleWidth = ruleWidth;
    }

    public String getRuleSuffix() {
        return ruleSuffix;
    }

    public void setRuleSuffix(String ruleSuffix) {
        this.ruleSuffix = ruleSuffix;
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
