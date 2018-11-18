package com.ningpai.information.vo;

import java.util.List;

/**
 * VO实体类-资讯栏目
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月14日 上午11:30:01
 * @version
 */
public class InformationTypeVo {
    /**
     * 资讯栏目ID
     * 
     * @see #getInfoTypeId()
     * @see #setInfoTypeId(Long)
     */
    private Long infoTypeId;
    /**
     * 资讯栏目名称
     * 
     * @see #getName()
     * @see #setName(String)
     */
    private String name;
    /**
     * 父资讯栏目编号
     * 
     * @see #getParentId()
     * @see #setParentId(Long)
     */
    private Long parentId;
    /**
     * 层级数
     * 
     * @see #getGrade()
     * @see #setGrade(Integer)
     */
    private Integer grade;
    /**
     * 排序
     * 
     * @see #getSort()
     * @see #setSort(Integer)
     */
    private Integer sort;
    /**
     * URL
     * 
     * @see #getUrl()
     * @see #setUrl(String)
     */
    private String url;

    /**
     * 是否显示
     * 
     * @see #getIsShow()
     * @see #setIsShow(String)
     */
    private String isShow;

    /**
     * 是否第三方新闻公告 0：不是 1：是
     */
    private String isThirdNews;

    /**
     * 浏览权限
     * 
     * @see #getBrowseable()
     * @see #setBrowseable(Long)
     */
    private Long browseable;
    /**
     * 栏目列表选项<br>
     * 0：默认页 1：列表第一页 2：动态页
     * 
     * @see #getListOptions()
     * @see #setListOptions(String)
     */
    private String listOptions;
    /**
     * 默认页名称
     * 
     * @see #getDefaultPage()
     * @see #setDefaultPage(String)
     */
    private String defaultPage;
    /**
     * 栏目属性
     * 
     * @see #getAttribute()
     * @see #setAttribute(String)
     */
    private String attribute;

    /**
     * 子资讯栏目集合
     * 
     * @see #getChilds()
     * @see #setChilds(java.util.List)
     */
    private List<InformationTypeVo> childs;

    private List<InformationVo> infos;

    /**
     * 第三方标示
     * 
     * @see #getTemp1()
     * @see #setTemp1(String)
     */
    private String temp1;

    /**
     * 第三方商家ID
     * 
     * @see #getTemp2()
     * @see #setTemp2(String)
     */
    private String temp2;

    /**
     * 类型标识：0系统；1普通；2移动版
     */
    private String temp3;

    /**
     * 获取栏目ID
     * 
     * @return
     */
    public Long getInfoTypeId() {
        return infoTypeId;
    }

    /**
     * 设置栏目ID
     * 
     * @param infoTypeId
     */
    public void setInfoTypeId(Long infoTypeId) {
        this.infoTypeId = infoTypeId;
    }

    /**
     * 获取栏目名称
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 设置栏目名称
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取父栏目ID
     * 
     * @return
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父栏目ID
     * 
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取层级
     * 
     * @return
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * 设置层级
     * 
     * @param grade
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * 获取排序
     * 
     * @return
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     * 
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取URL
     * 
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置URL
     * 
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取是否显示
     * 
     * @return
     */
    public String getIsShow() {
        return isShow;
    }

    /**
     * 设置是否显示
     * 
     * @param isShow
     */
    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    /**
     * 获取浏览权限
     * 
     * @return
     */
    public Long getBrowseable() {
        return browseable;
    }

    /**
     * 设置浏览权限
     * 
     * @param browseable
     */
    public void setBrowseable(Long browseable) {
        this.browseable = browseable;
    }

    /**
     * 获取栏目列表选项
     * 
     * @return
     */
    public String getListOptions() {
        return listOptions;
    }

    /**
     * 设置栏目列表选项
     * 
     * @param listOptions
     */
    public void setListOptions(String listOptions) {
        this.listOptions = listOptions;
    }

    /**
     * 获取默认页
     * 
     * @return
     */
    public String getDefaultPage() {
        return defaultPage;
    }

    /**
     * 设置默认页
     * 
     * @param defaultPage
     */
    public void setDefaultPage(String defaultPage) {
        this.defaultPage = defaultPage;
    }

    /**
     * 获取栏目属性
     * 
     * @return
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * 设置栏目属性
     * 
     * @param attribute
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    /**
     * 获取子资讯栏目集合
     * 
     * @return
     */
    public List<InformationTypeVo> getChilds() {
        return childs;
    }

    /**
     * 设置子资讯栏目集合
     * 
     * @param childs
     */
    public void setChilds(List<InformationTypeVo> childs) {
        this.childs = childs;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public List<InformationVo> getInfos() {
        return infos;
    }

    public void setInfos(List<InformationVo> infos) {
        this.infos = infos;
    }

    public String getIsThirdNews() {
        return isThirdNews;
    }

    public void setIsThirdNews(String isThirdNews) {
        this.isThirdNews = isThirdNews;
    }

    public String getTemp3() {
        return temp3;
    }

    public void setTemp3(String temp3) {
        this.temp3 = temp3;
    }

}
