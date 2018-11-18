package com.ningpai.information.vo;

import com.ningpai.information.bean.InfoOPTag;

import java.util.Date;

/**
 * VO实体类-资讯单页
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月24日 17:30:01
 * @version
 */
public class InformationOnePageVo {
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
    private String title;
    /**
     * 标识
     * 
     * @see #getMark()
     * @see #setMark(String)
     */
    private String mark;
    /**
     * url
     *
     * @see #getUrl()
     * @see #setUrl(String)
     */
    private String url;
    /**
     * 删除标记
     *
     * @see #getDelflag()
     * @see #setDelflag(String)
     */
    private String delflag;
    /**
     * 创建时间
     *
     * @see #getCreateDate()
     * @see #setCreateDate(Date)
     */
    private Date createDate;
    /**
     * 更新时间
     *
     * @see #getUpdateDate()
     * @see #setUpdateDate(Date)
     */
    private Date updateDate;
    /**
     * 单页标签ID
     */
    private Long infoOPTagId;
    /**
     * 单页标签对象
     */
    private InfoOPTag infoOPTag;
    /**
     * 单页图片地址
     */
    private String imgSrc;
    /**
     * 是否展示
     */
    private String isShow;

    public Long getInfoOPId() {
        return infoOPId;
    }

    public void setInfoOPId(Long infoOPId) {
        this.infoOPId = infoOPId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }
    /**
     * 取得：创建时间
     *
     * @return Date 创建时间
     *         {@link com.ningpai.information.vo.InformationOnePageVo#createDate}
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
     * 取得：更新时间
     *
     * @return Date 更新时间
     *         {@link com.ningpai.information.vo.InformationOnePageVo#updateDate}
     */
    public Date getUpdateDate() {
        if (this.updateDate != null) {
            return new Date(this.updateDate.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置：更新时间
     *
     * @param updateDate
     *            更新时间
     */
    public void setUpdateDate(Date updateDate) {
        if (updateDate != null) {
            Date tEmp = (Date) updateDate.clone();
            if (tEmp != null) {
                this.updateDate = tEmp;
            }
        }
    }

    public InfoOPTag getInfoOPTag() {
        return infoOPTag;
    }

    public void setInfoOPTag(InfoOPTag infoOPTag) {
        this.infoOPTag = infoOPTag;
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

    public Long getInfoOPTagId() {
        return infoOPTagId;
    }

    public void setInfoOPTagId(Long infoOPTagId) {
        this.infoOPTagId = infoOPTagId;
    }
}
