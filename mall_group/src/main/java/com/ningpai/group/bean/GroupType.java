package com.ningpai.group.bean;

import java.util.Date;

/**
 * 小组类型实体
 * 
 * @author qiyuanyuan
 * 
 */
public class GroupType {

    /**
     * 类型ID
     */
    private Long groupTypeId;

    /**
     * 类型名称
     */
    private String groupTypeName;

    /**
     * 类型描述
     */
    private String groupTypeRemark;

    // 创建时间
    private Date groupTypeCreateTime;

    /**
     * 修改时间
     */
    private Date groupTypeModifyTime;

    /**
     * 分类排序
     */
    private Long groupTypeSort;

    /**
     * 是否删除 0：未删除 1：删�?
     */
    private String groupTypeDelFlag;

    /**
     * 分类状态1：正常：停用
     */
    private String groupTypeStatus;

    /**
     * SEO标题
     */
    private String seoTitle;

    /**
     * SEO关键字
     */
    private String seoKeyWord;

    /**
     * SEO描述
     */
    private String seoRemark;

    /**
     * 分页开始条数
     */
    private int startRowNum;

    /**
     * 分页结束的条数
     */
    private int endRowNum;

    public Long getGroupTypeId() {
        return groupTypeId;
    }

    public void setGroupTypeId(Long groupTypeId) {
        this.groupTypeId = groupTypeId;
    }

    public String getGroupTypeName() {
        return groupTypeName;
    }

    public void setGroupTypeName(String groupTypeName) {
        this.groupTypeName = groupTypeName;
    }

    /**
     * get创建时间 
     * 复制时间对象并返回
     */
    public Date getGroupTypeCreateTime() {
        return (Date) groupTypeCreateTime.clone();
    }

    /**
     * set创建时间
     * 创建时间 不为空时候 复制时间对象
     */
    public void setGroupTypeCreateTime(Date groupTypeCreateTime) {
        this.groupTypeCreateTime = groupTypeCreateTime == null ? null : (Date) groupTypeCreateTime.clone();
    }

    /**
     * 复制时间对象并返回
     */
    public Date getGroupTypeModifyTime() {
        return (Date) groupTypeModifyTime.clone();
    }

    /**
     * 修改时间 不为空时候 复制时间对象
     * @param groupTypeModifyTime
     */
    public void setGroupTypeModifyTime(Date groupTypeModifyTime) {
        this.groupTypeModifyTime = groupTypeModifyTime == null ? null : (Date) groupTypeModifyTime.clone();
    }

    public String getGroupTypeDelFlag() {
        return groupTypeDelFlag;
    }

    public void setGroupTypeDelFlag(String groupTypeDelFlag) {
        this.groupTypeDelFlag = groupTypeDelFlag;
    }

    public String getGroupTypeStatus() {
        return groupTypeStatus;
    }

    public void setGroupTypeStatus(String groupTypeStatus) {
        this.groupTypeStatus = groupTypeStatus;
    }

    public String getGroupTypeRemark() {
        return groupTypeRemark;
    }

    public void setGroupTypeRemark(String groupTypeRemark) {
        this.groupTypeRemark = groupTypeRemark;
    }

    public Long getGroupTypeSort() {
        return groupTypeSort;
    }

    public void setGroupTypeSort(Long groupTypeSort) {
        this.groupTypeSort = groupTypeSort;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getSeoKeyWord() {
        return seoKeyWord;
    }

    public void setSeoKeyWord(String seoKeyWord) {
        this.seoKeyWord = seoKeyWord;
    }

    public String getSeoRemark() {
        return seoRemark;
    }

    public void setSeoRemark(String seoRemark) {
        this.seoRemark = seoRemark;
    }

    public int getStartRowNum() {
        return startRowNum;
    }

    public void setStartRowNum(int startRowNum) {
        this.startRowNum = startRowNum;
    }

    public int getEndRowNum() {
        return endRowNum;
    }

    public void setEndRowNum(int endRowNum) {
        this.endRowNum = endRowNum;
    }
}
