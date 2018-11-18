package com.ningpai.information.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 实体类-资讯文章
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月17日 11:30:01
 * @version
 */
public class Information {
    /**
     * 文章ID
     * 
     * @see #getInfoId()
     * @see #setInfoId(Long)
     */
    private Long infoId;
    /**
     * 标题
     * 
     * @see #getTitle()
     * @see #setTitle(String)
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String title;
    /**
     * 链接地址
     * 
     * @see #getUrl()
     * @see #setUrl(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String url;
    /**
     * 简略标题
     * 
     * @see #getShortTitle()
     * @see #setShortTitle(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String shortTitle;
    /**
     * 自定义属性
     * 
     * @see #getUserDefined()
     * @see #setUserDefined(String)
     */
    private String userDefined;
    /**
     * TAG标签
     * 
     * @see #getTag()
     * @see #setTag(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String tag;
    /**
     * 文章配图地址
     * 
     * @see #getImgSrc()
     * @see #setImgSrc(String)
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String imgSrc;
    /**
     * 文章来源
     * 
     * @see #getSource()
     * @see #setSource(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String source;
    /**
     * 作者
     * 
     * @see #getAuthor()
     * @see #setAuthor(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String author;
    /**
     * 栏目ID
     * 
     * @see #getInfoTypeId()
     * @see #setInfoTypeId(Long)
     */
    private Long infoTypeId;
    /**
     * 关键字
     * 
     * @see #getKeyword()
     * @see #setKeyword(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String keyword;
    /**
     * 内容摘要
     * 
     * @see #getDescription()
     * @see #setDescription(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String description;
    /**
     * 浏览权限
     * 
     * @see #getBrowseable()
     * @see #setBrowseable(Long)
     */
    private Long browseable;
    /**
     * 点击数
     * 
     * @see #getHits()
     * @see #setHits(Long)
     */
    private Long hits = 0L;
    /**
     * 排序
     * 
     * @see #getSort()
     * @see #setSort(Long)
     */
    private Integer sort;
    /**
     * 是否显示<br>
     * 0:不显示 1：显示
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
     * 是否删除<br>
     * 0：未删除 1：已删除
     * 
     * @see #getDelflag()
     * @see #setDelflag(String)
     */
    private String delflag;
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
     * 修改人ID
     * 
     * @see #getUpdateUserId()
     * @see #setUpdateUserId(Long)
     */
    private Long updateUserId;
    /**
     * 修改时间
     * 
     * @see #getUpdateDate()
     * @see #setUpdateDate(java.util.Date)
     */
    private Date updateDate;
    /**
     * 文章内容
     * 
     * @see #getContent()
     * @see #setContent(String)
     */
    private String content;
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
     * 类型标识 0：普通 1：移动版
     * 
     * @see #getTemp3()
     * @see #setTemp3(String)
     */
    private String temp3;

    /**
     * 获取文章ID
     * 
     * @return
     */
    public Long getInfoId() {
        return infoId;
    }

    /**
     * 设置文章ID
     * 
     * @param infoId
     */
    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    /**
     * 获取标题
     * 
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     * 
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取作者
     * 
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     * 
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取文章配图
     * 
     * @return
     */
    public String getImgSrc() {
        return imgSrc;
    }

    /**
     * 设置文章配图
     * 
     * @param imgSrc
     */
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    /**
     * 获取关键字
     * 
     * @return
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置关键字
     * 
     * @param keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * 获取内容摘要
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置内容摘要
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取是否显示<br>
     * 0:不显示 1：显示
     * 
     * @return
     */
    public String getIsShow() {
        return isShow;
    }

    /**
     * 设置是否显示<br>
     * 0:不显示 1：显示
     * 
     * @param isShow
     */
    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    /**
     * 获取是否删除<br>
     * 0：未删除 1：已删除
     * 
     * @return
     */
    public String getDelflag() {
        return delflag;
    }

    /**
     * 设置是否删除<br>
     * 0：未删除 1：已删除
     * 
     * @param delflag
     */
    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    /**
     * 获取创建人ID
     * 
     * @return
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建人ID
     * 
     * @param createUserId
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取创建时间
     * 
     * @return
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
     * 
     * @param createDate
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
     * 
     * @return
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 设置修改人ID
     * 
     * @param updateUserId
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 获取修改时间
     * 
     * @return
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
     * 
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        if (updateDate != null) {
            Date tEmp = (Date) updateDate.clone();
            if (tEmp != null) {
                this.updateDate = tEmp;
            }
        }
    }

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
     * 获取文章内容
     * 
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文章内容
     * 
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getUserDefined() {
        return userDefined;
    }

    public void setUserDefined(String userDefined) {
        this.userDefined = userDefined;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getBrowseable() {
        return browseable;
    }

    public void setBrowseable(Long browseable) {
        this.browseable = browseable;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getHits() {
        return hits;
    }

    public void setHits(Long hits) {
        this.hits = hits;
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
