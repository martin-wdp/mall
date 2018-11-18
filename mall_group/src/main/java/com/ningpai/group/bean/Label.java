package com.ningpai.group.bean;

import java.util.Date;

/**
 * 小组标签实体类
 */
public class Label {
    /**
     * 小组标签ID
     */
    private Long groupLabelId;

    /**
     * 标签名称
     */
    private String groupLabelName;

    /**
     * 标签创建时间
     */
    private Date groupLabelCreateTime;

    /**
     * 标签修改时间
     */
    private Date groupLabelModifyTime;

    /**
     * 删除标记:0：正常1：删除
     */
    private String groupLabelDelFlag;

    /**
     * 停用标记:0：正常1：停用
     */
    private String groupLabelStatus;

    /**
     * 标签排序
     */
    private Long groupLabelSort;

    /**
     * SEO标题
     */
    private String groupLabelSeoTitle;

    /**
     * SEO关键字
     */
    private String groupLabelSeoKeyword;

    /**
     * SEO描述
     */
    private String groupLabelSeoRemark;

    public Long getGroupLabelId() {
        return groupLabelId;
    }

    public void setGroupLabelId(Long groupLabelId) {
        this.groupLabelId = groupLabelId;
    }

    public String getGroupLabelName() {
        return groupLabelName;
    }

    public void setGroupLabelName(String groupLabelName) {
        this.groupLabelName = groupLabelName;
    }

    /**
     * get小组标签创建时间
     * 复制时间对象并返回
     */
    public Date getGroupLabelCreateTime() {
        return (Date) groupLabelCreateTime.clone();
    }

    /**
     * set小组标签创建时间
     *  修改时间 不为空时候 复制时间对象
     */
    public void setGroupLabelCreateTime(Date groupLabelCreateTime) {
        this.groupLabelCreateTime = groupLabelCreateTime == null ? null : (Date) groupLabelCreateTime.clone();
    }

    /**
     * get小组标签修改时间
     * 复制时间对象并返回
     */
    public Date getGroupLabelModifyTime() {
        return (Date) groupLabelModifyTime.clone();
    }

    /**
     * set小组标签修改时间
     * 修改时间 不为空时候 复制时间对象
     */
    public void setGroupLabelModifyTime(Date groupLabelModifyTime) {
        this.groupLabelModifyTime = groupLabelModifyTime == null ? null : (Date) groupLabelModifyTime.clone();
    }

    public String getGroupLabelDelFlag() {
        return groupLabelDelFlag;
    }

    public void setGroupLabelDelFlag(String groupLabelDelFlag) {
        this.groupLabelDelFlag = groupLabelDelFlag;
    }

    public Long getGroupLabelSort() {
        return groupLabelSort;
    }

    public void setGroupLabelSort(Long groupLabelSort) {
        this.groupLabelSort = groupLabelSort;
    }

    public String getGroupLabelSeoTitle() {
        return groupLabelSeoTitle;
    }

    public void setGroupLabelSeoTitle(String groupLabelSeoTitle) {
        this.groupLabelSeoTitle = groupLabelSeoTitle;
    }

    public String getGroupLabelSeoKeyword() {
        return groupLabelSeoKeyword;
    }

    public void setGroupLabelSeoKeyword(String groupLabelSeoKeyword) {
        this.groupLabelSeoKeyword = groupLabelSeoKeyword;
    }

    public String getGroupLabelSeoRemark() {
        return groupLabelSeoRemark;
    }

    public void setGroupLabelSeoRemark(String groupLabelSeoRemark) {
        this.groupLabelSeoRemark = groupLabelSeoRemark;
    }

    public String getGroupLabelStatus() {
        return groupLabelStatus;
    }

    public void setGroupLabelStatus(String groupLabelStatus) {
        this.groupLabelStatus = groupLabelStatus;
    }

}
