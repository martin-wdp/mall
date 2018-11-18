package com.ningpai.information.vo;

import java.util.Date;

import com.ningpai.information.bean.InformationType;

/**
 * VO实体类-资讯文章
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月14日 下午15:39:01
 * @version
 */
public class InformationVo {
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
    private String title;
    /**
     * 链接地址
     * 
     * @see #getUrl()
     * @see #setUrl(String)
     */
    private String url;
    /**
     * 简略标题
     * 
     * @see #getShortTitle()
     * @see #setShortTitle(String)
     */
    private String shortTitle;
    /**
     * 自定义属性
     * 
     * @see #getUserDefined()
     * @see #setUserDefined(String)
     */
    private String userDefined;
    /**
     * 作者
     * 
     * @see #getAuthor()
     * @see #setAuthor(String)
     */
    private String author;
    /**
     * 文章配图地址
     * 
     * @see #getImgSrc()
     * @see #setImgSrc(String)
     */
    private String imgSrc;
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
     * @see #setSort(Integer)
     */
    private Integer sort;
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
     * 创建时间
     * 
     * @see #getCreateDate()
     * @see #setCreateDate(java.util.Date)
     */
    private Date createDate;
    /**
     * 修改时间
     * 
     * @see #getUpdateDate()
     * @see #setUpdateDate(java.util.Date)
     */
    private Date updateDate;

    /**
     * 资讯栏目ID
     * 
     * @see
     * @see
     */
    private Long infoTypeId;
    /**
     * 资讯栏目
     * 
     * @see #getInfoType()
     * @see #setInfoType(com.ningpai.information.bean.InformationType)
     */
    private InformationType infoType;

    /**
     * 浏览权限 会员等级名称
     * 
     * @see #getBrowseableValue()
     * @see #setBrowseableValue(String)
     */
    private String browseableValue;

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
     * 类型标识 0普通 1移动版
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
     * 获取文章配图地址
     * 
     * @return
     */
    public String getImgSrc() {
        return imgSrc;
    }

    /**
     * 设置文章配图地址
     * 
     * @param imgSrc
     */
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
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
     * 获取栏目
     * 
     * @return
     */
    public InformationType getInfoType() {
        return infoType;
    }

    /**
     * 设置栏目
     * 
     * @param infoType
     */
    public void setInfoType(InformationType infoType) {
        this.infoType = infoType;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getInfoTypeId() {
        return infoTypeId;
    }

    public void setInfoTypeId(Long infoTypeId) {
        this.infoTypeId = infoTypeId;
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

    public String getBrowseableValue() {
        return browseableValue;
    }

    public void setBrowseableValue(String browseableValue) {
        this.browseableValue = browseableValue;
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
