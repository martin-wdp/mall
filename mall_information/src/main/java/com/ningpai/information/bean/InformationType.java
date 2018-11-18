package com.ningpai.information.bean;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 资讯类型实体
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月14日 上午11:30:01
 * @version
 */
public class InformationType {
    /**
     * 资讯类型编号
     * 
     * @see #getInfoTypeId()
     * @see #setInfoTypeId(Long)
     */
    private Long infoTypeId;
    /**
     * 资讯类型名称
     * 
     * @see #getName()
     * @see #setName(String)
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String name;
    /**
     * 父资讯类型编号
     * 
     * @see #getParentId()
     * @see #setParentId(Long)
     */
    private Long parentId;

    /**
     * 父分类名称
     * 
     * @see #getParentName()
     * @see #setParentName(String)
     */
    private String parentName;

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
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String url;
    /**
     * SEO标题
     * 
     * @see #getSeoTitle()
     * @see #setSeoTitle(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String seoTitle;
    /**
     * SEO关键字
     * 
     * @see #getSeoKeyword()
     * @see #setSeoKeyword(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String seoKeyword;
    /**
     * SEO描述
     * 
     * @see #getSeoDesc()
     * @see #setSeoDesc(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String seoDesc;
    /**
     * 是否显示<br>
     * 0：不显示 1：显示
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
     * 是否删除<br>
     * 0：未删除 1：已删除
     * 
     * @see #getDelflag()
     * @see #setDelflag(String)
     */
    private String delflag = "0";
    /**
     * 创建人ID
     * 
     * @see #getCreateUserId()
     * @see #setCreateUserId(Long)
     */
    private Long createUserId;
    /**
     * 创建时间
     * 
     * @see #getCreateDate()
     * @see #setCreateDate(java.util.Date)
     */
    private Date createDate;
    /**
     * 更新人ID
     * 
     * @see #getUpdateUserId()
     * @see #setUpdateUserId(Long)
     */
    private Long updateUserId;
    /**
     * 更新时间
     * 
     * @see #getUpdateDate()
     * @see #setUpdateDate(java.util.Date)
     */
    private Date updateDate;
    /**
     * 第三方标识
     */
    private String temp1;
    /**
     * 第三方商家ID
     */
    private String temp2;
    /**
     * 类型标识：0系统；1普通；2移动版
     */
    private String temp3;
    private List<InformationType> typeList;

    public List<InformationType> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<InformationType> typeList) {
        this.typeList = typeList;
    }

    /**
     * 获取栏目ID
     */
    public Long getInfoTypeId() {
        return infoTypeId;
    }

    /**
     * 设置栏目ID
     */
    public void setInfoTypeId(Long infoTypeId) {
        this.infoTypeId = infoTypeId;
    }

    /**
     * 获取栏目名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置栏目名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取父栏目ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父栏目ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取层级
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * 设置层级
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * 获取排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取SEO标题
     */
    public String getSeoTitle() {
        return seoTitle;
    }

    /**
     * 设置SEO标题
     */
    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    /**
     * 获取SEO关键字
     */
    public String getSeoKeyword() {
        return seoKeyword;
    }

    /**
     * 设置SEO关键字
     */
    public void setSeoKeyword(String seoKeyword) {
        this.seoKeyword = seoKeyword;
    }

    /**
     * 获取SEO描述
     */
    public String getSeoDesc() {
        return seoDesc;
    }

    /**
     * 设置SEO描述
     */
    public void setSeoDesc(String seoDesc) {
        this.seoDesc = seoDesc;
    }

    /**
     * 获取是否显示<br>
     * 0：不显示 1：显示
     */
    public String getIsShow() {
        return isShow;
    }

    /**
     * 设置是否显示<br>
     * 0：不显示 1：显示
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
     * 获取是否删除<br>
     * 0：未删除 1：已删除
     */
    public String getDelflag() {
        return delflag;
    }

    /**
     * 设置是否删除<br>
     * 0：未删除 1：已删除
     */
    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    /**
     * 获取创建人ID
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建人ID
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取创建时间
     */
    public Date getCreateDate() {
        if (this.createDate != null) {
            return new Date(this.createDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置创建时间
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
     * 获取修改人ID
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 设置修改人ID
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 获取修改时间
     */
    public Date getUpdateDate() {
        if (this.updateDate != null) {
            return new Date(this.updateDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置修改时间
     */
    public void setUpdateDate(Date updateDate) {
        if (updateDate != null) {
            Date tEmp = (Date) updateDate.clone();
            if (tEmp != null) {
                this.updateDate = tEmp;
            }
        }
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
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
