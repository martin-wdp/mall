package com.ningpai.group.bean;

import java.util.Date;

/**
 * 小组标签级联对象
 * 
 * @author qiyuanyuan
 * 
 */
public class LabelGroup {

    /**
     * 小组标签级联id
     */
    private Long labelGroupId;

    /**
     * 小组标签
     */
    private Long groupLabelId;

    /**
     * 小组Id
     */
    private Long groupId;

    /**
     * 小组分类Id
     */
    private Long groupTypeId;

    /**
     * 创建小组时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 删除标记 0:正常 1：删除
     */
    private String delFlag;

    public Long getLabelGroupId() {
        return labelGroupId;
    }

    public void setLabelGroupId(Long labelGroupId) {
        this.labelGroupId = labelGroupId;
    }

    public Long getGroupLabelId() {
        return groupLabelId;
    }

    public void setGroupLabelId(Long groupLabelId) {
        this.groupLabelId = groupLabelId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    /**
     * 获取创建时间
     * */
    public Date getCreateTime() {
        //复制时间对象并返回
        return (Date) createTime.clone();
    }
    /**
     * 设置创建时间
     * */
    public void setCreateTime(Date createTime) {
        //创建时间 不为空时候 复制时间对象
        this.createTime = createTime == null ? null : (Date) createTime.clone();
    }
    /**
     * 获取修改时间
     * */
    public Date getModifyTime() {
        //复制时间对象并返回
        return (Date) modifyTime.clone();
    }
    /**
     * 设置修改时间
     * */
    public void setModifyTime(Date modifyTime) {
        //修改时间 不为空时候 复制时间对象
        this.modifyTime = modifyTime == null ? null : (Date) modifyTime.clone();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getGroupTypeId() {
        return groupTypeId;
    }

    public void setGroupTypeId(Long groupTypeId) {
        this.groupTypeId = groupTypeId;
    }
}
