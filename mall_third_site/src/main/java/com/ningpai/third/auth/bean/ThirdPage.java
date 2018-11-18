package com.ningpai.third.auth.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 第三方管理员权限关系
 * </p>
 * 
 * @author zhanghl
 * @since 20150730
 * @version 2.0
 */
public class ThirdPage {
    /**
     * 页面标号
     */
    private Long id;
    /**
     * 页面明曾
     */
    private String designation;
    /**
     * 路径
     */
    private String url;
    /**
     * 图片路径
     */
    private String imgUrl;
    /**
     * 选中后图片路径
     */
    private String imgUrlSelected;
    /**
     * 父编号
     */
    private Long parentId;
    /**
     * 分级
     */
    private Long grade;
    /**
     * 排序
     */
    private Long sort;
    /**
     * 页面类型
     */
    private String type;
    /**
     * 页面备注
     */
    private String characterization;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modTime;
    /**
     * 状态
     */
    private String flag;

    /**
     * 子菜单集合
     * 
     * @see #getMenuVos()
     * @see #setMenuVos(List)
     */
    private List<ThirdPage> menuVos = new ArrayList<ThirdPage>(0);

    /**
     * 商家标签
     * 
     * @return
     */
    public List<ThirdPage> getMenuVos() {
        return menuVos;
    }

    public void setMenuVos(List<ThirdPage> menuVos) {
        this.menuVos = menuVos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrlSelected() {
        return imgUrlSelected;
    }

    public void setImgUrlSelected(String imgUrlSelected) {
        this.imgUrlSelected = imgUrlSelected;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCharacterization() {
        return characterization;
    }

    public void setCharacterization(String characterization) {
        this.characterization = characterization;
    }


    /**
     * 获取创建日期
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
     * 设置创建日期
     * @param createTime
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
     * 获取修改日期
     * @return
     */
    public Date getModTime() {
        if (this.modTime != null) {
            return new Date(this.modTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置修改日期
     * @param modTime
     */
    public void setModTime(Date modTime) {
        if (modTime != null) {
            Date tEmp = (Date) modTime.clone();
            if (tEmp != null) {
                this.modTime = tEmp;
            }
        }
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
