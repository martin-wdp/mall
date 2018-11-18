package com.ningpai.imagemanage.vo;

import java.util.Date;
import java.util.List;

/**
 * 实体类-资源图片类型
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月25日上午11:08:07
 */
public class InfoImageClassifyVo {
    /** 类型编号 */
    private Long classifyId;
    /** 类型名称 */
    private String classifyName;
    /** 父分类ID */
    private Long parentId;
    /** 层级 */
    private Integer grade;
    /**
     * 是否可包含图片 0：否；1：是<br/>
     * 图片只能上传到可包含图片的分类
     */
    private String isHasImg;
    /**
     * 是否是系统分类 0：否；1：是<br/>
     * 系统分类不可修改，不可删除
     */
    private String isSys;
    /** 创建时间 */
    private Date createDate;
    /** 修改时间 */
    private Date updateDate;
    /** 删除标记 */
    private String delflag;

    private List<InfoImageClassifyVo> childs;

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    /**
     * 取得：创建时间
     *
     * @return Date 创建时间 {@link com.ningpai.imagemanage.vo.InfoImageClassifyVo#createDate}
     */
    public Date getCreateDate() {
        if (this.createDate != null) {
            return new Date(this.createDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置：创建时间
     *
     * @param createDate
     *            创建时间
     */
    public void setCreateDate(Date createDate) {
        if (createDate != null) {
            Date tEmp = (Date) createDate.clone();
            if (tEmp != null) {
                this.createDate = tEmp;
            }
        }
    }

    /**
     * 取得：修改时间
     *
     * @return Date 修改时间 {@link com.ningpai.imagemanage.vo.InfoImageClassifyVo#updateDate}
     */
    public Date getUpdateDate() {
        if (this.updateDate != null) {
            return new Date(this.updateDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置：修改时间
     *
     * @param updateDate
     *            修改时间
     */
    public void setUpdateDate(Date updateDate) {
        if (updateDate != null) {
            Date tEmp = (Date) updateDate.clone();
            if (tEmp != null) {
                this.updateDate = tEmp;
            }
        }
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getIsHasImg() {
        return isHasImg;
    }

    public void setIsHasImg(String isHasImg) {
        this.isHasImg = isHasImg;
    }

    public String getIsSys() {
        return isSys;
    }

    public void setIsSys(String isSys) {
        this.isSys = isSys;
    }

    public List<InfoImageClassifyVo> getChilds() {
        return childs;
    }

    public void setChilds(List<InfoImageClassifyVo> childs) {
        this.childs = childs;
    }

}
