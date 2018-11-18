package com.ningpai.information.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 实体类-资讯单页
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月24日 17:30:01
 * @version
 */
public class InformationOnePage {
    /**
     * 单页ID
     * 
     * @see #getInfoOPId()
     * @see #setInfoOPId(Long)
     */
    private Long infoOPId;
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
     * 简略标题
     * 
     * @see #getShortTitle()
     * @see #setShortTitle(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String shortTitle;
    /**
     * 关键字
     * 
     * @see #getkeyword()
     * @see #setKeyword(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String keyword;
    /**
     * 描述
     * 
     * @see #getDescription()
     * @see #setDescription(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String description;
    /**
     * 标识
     * 
     * @see #getMark()
     * @see #setMark(String)
     */
    private String mark;
    /**
     * URL
     * 
     * @see #getUrl()
     * @see #setUrl(String)
     */   
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String url;
    /**
     * 模板ID
     * 
     * @see #getInfoTempId()
     * @see #setInfoTempId(Long)
     */
    private Long infoTempId;
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
     * 单页内容
     * 
     * @see #getContent()
     * @see #setContent(String)
     */
    private String content;
    /**
     * 单页标签ID
     */
    private Long infoOPTagId;
    /**
     * 单页图片地址
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String imgSrc;

    private String isShow;

    private InfoOPTag infoOPTag;

    /**
     * 获取单页ID
     * 
     * @return
     */
    public Long getInfoOPId() {
        return infoOPId;
    }

    /**
     * 设置单页ID
     * 
     * @param infoOPId
     */
    public void setInfoOPId(Long infoOPId) {
        this.infoOPId = infoOPId;
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
     * 获取描述
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取标识
     * 
     * @return
     */
    public String getMark() {
        return mark;
    }

    /**
     * 设置标识
     * 
     * @param mark
     */
    public void setMark(String mark) {
        this.mark = mark;
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
     * 获取模板ID
     * 
     * @return
     */
    public Long getInfoTempId() {
        return infoTempId;
    }

    /**
     * 设置模板ID
     * 
     * @param infoTempId
     */
    public void setInfoTempId(Long infoTempId) {
        this.infoTempId = infoTempId;
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
     * 获取单页内容
     * 
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置单页内容
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

    public Long getInfoOPTagId() {
        return infoOPTagId;
    }

    public void setInfoOPTagId(Long infoOPTagId) {
        this.infoOPTagId = infoOPTagId;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public InfoOPTag getInfoOPTag() {
        return infoOPTag;
    }

    public void setInfoOPTag(InfoOPTag infoOPTag) {
        this.infoOPTag = infoOPTag;
    }
}
