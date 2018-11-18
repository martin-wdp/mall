/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.manager.bean.valuebean;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单显示数据封装类
 * 
 * @author AthrunNatu
 * @since 2013年11月21日下午3:46:26
 */
public class MenuVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     * 
     * @see #getId()
     * @see #setId(Long)
     */
    private Long id;

    /**
     * 父id
     * 
     * @see #getParentId()
     * @see #setParentId(Long)
     */
    private Long parentId;

    /**
     * 层级数
     * 
     * @see #getGrade()
     * @see #setGrade(Long)
     */
    private Long grade;

    /**
     * 页面名称
     * 
     * @see #getDesignation()
     * @see #setDesignation(String)
     */
    private String designation;

    /**
     * URL
     * 
     * @see #getUrl()
     * @see #setUrl(String)
     */
    private String url;

    /**
     * 子菜单集合
     * 
     * @see #getMenuVos()
     * @see #setMenuVos(java.util.List)
     */
    private List<MenuVo> menuVos;

    /**
     * 页面未选中图片
     * 
     * @see #getImgUrl()
     * @see #setImgUrl(String)
     */
    private String imgUrl;

    /**
     * 页面选中图片
     * 
     * @see #getImgUrlSeleted()
     * @see #setImgUrlSeleted(String)
     */
    private String imgUrlSelected;

    /***
     * 排序
     */
    private Long sort;

    /**
     * 菜单所属模块
     *
     */
    private String bundleName;

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    /**
     * 获取<code>imgUrlSelected</code>ID值
     * 
     * @return imgUrlSelected
     *         {@link com.ningpai.manager.bean.Page#imgUrlSelected}
     */
    public String getImgUrlSelected() {
        return imgUrlSelected;
    }

    /**
     * 设置<code>imgUrlSelected</code>ID值
     * 
     * @param imgUrlSelected
     *            {@link com.ningpai.manager.bean.Page#imgUrlSelected}
     */
    public void setImgUrlSelected(String imgUrlSelected) {
        this.imgUrlSelected = imgUrlSelected;
    }

    /**
     * 获取<code>imgUrl</code>ID值
     * 
     * @return imgUrl{@link com.ningpai.manager.bean.Page#imgUrl}
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置<code>imgUrl</code>ID值
     * 
     * @param imgUrl
     *            {@link com.ningpai.manager.bean.Page#imgUrl}
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<MenuVo> getMenuVos() {
        return menuVos;
    }

    public void setMenuVos(List<MenuVo> menuVos) {
        this.menuVos = menuVos;
    }

    /**
     * 获取<code>MenuVo</code>designation
     * 
     * @return designation{@link com.ningpai.manager.bean.MenuVo#designation}
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * 设置<code>Menu</code>designation
     * 
     * @param designation
     *            {@link com.ningpai.manager.bean.MenuVo#designation}
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * 获取<code>Menu</code>getUrl
     * 
     * @return getUrl{@link com.ningpai.manager.bean.MenuVo#url}
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置<code>Menu</code>url
     * 
     * @param url
     *            {@link com.ningpai.manager.bean.MenuVo#url}
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取<code>Menu</code>id
     * 
     * @return id{@link com.ningpai.manager.bean.MenuVo#id}
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置<code>Menu</code>id
     * 
     * @param id
     *            {@link com.ningpai.manager.bean.MenuVo#id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取<code>Menu</code>parentId
     * 
     * @return parentId{@link com.ningpai.manager.bean.MenuVo#parentId}
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置<code>Menu</code>parentId
     * 
     * @param parentId
     *            {@link com.ningpai.manager.bean.MenuVo#parentId}
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

}
