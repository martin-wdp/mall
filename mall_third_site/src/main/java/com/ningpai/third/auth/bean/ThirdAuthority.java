package com.ningpai.third.auth.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 第三方权限Bean
 * </p>
 * 
 * @author zhanghl
 * @since 2014年5月10日 上午11:29:01
 * @version 2.0
 */
public class ThirdAuthority {
    /**
     * 权限编号
     */
    private Long id;
    /**
     * 店铺编号
     */
    private Long storeId;
    /**
     * 权限名称
     */
    private String designation;
    /**
     * 备注
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
     * 删除标记
     */
    private String flag;
    /**
     * 页面权限集合
     */
    private List<ThirdPage> pages = new ArrayList<ThirdPage>(0);

    public List<ThirdPage> getPages() {
        return pages;
    }

    public void setPages(List<ThirdPage> pages) {
        this.pages = pages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCharacterization() {
        return characterization;
    }

    public void setCharacterization(String characterization) {
        this.characterization = characterization;
    }

    /**
     * 获取创建时间
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
     * 设置创建时间
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
