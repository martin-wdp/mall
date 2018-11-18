package com.ningpai.temp.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 模板设置
 * 
 * @Title: Entity
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-29 17:43:53
 * @version V1.0
 */
@Component("infoTemp")
public class SysTemp  implements Serializable {
    /**
     * 记录ID
     * 
     * @see #getTempId()
     * @see #setTempId(int)
     */
    private int tempId;
    /**
     * 模板名称
     * 
     * @see #getTempName()
     * @see #setTempName(String)
     */
    private String tempName;
    /**
     * 模板url
     * 
     * @see #getTempUrl()
     * @see #setTempUrl(String)
     */
    private String tempUrl;
    /**
     * 模板参数
     * 
     * @see #getTempPa()
     * @see #setTempPa(String)
     */
    private String tempPa;
    /**
     * 模板图片url
     * 
     * @see #getTempImageUrl()
     * @see #setTempImageUrl(String)
     */
    private String tempImageUrl;
    /**
     * 描述信息
     * 
     * @see #getDes()
     * @see #setDes(String)
     */
    private String des;
    /**
     * 版本信息
     * 
     * @see #getVersion()
     * @see #setVersion(String)
     */
    private String version;
    /**
     * 模板类型
     * 
     * @see #getTempType()
     * @see #setTempType(int)
     */
    private int tempType;
    /** 模板类型显示值 */
    private String tempTypeValue;
    /**
     * 头部区域
     * 
     * @see #getHeadArea()
     * @see #setHeadArea(String)
     */
    private String headArea;
    /**
     * 页面导航
     * 
     * @see #getPageNav()
     * @see #setPageNav(String)
     */
    private String pageNav;
    /**
     * 商品分类区域
     * 
     * @see #getGoodClassifyArea()
     * @see #setGoodClassifyArea(String)
     */
    private String goodClassifyArea;
    /**
     * 商品分类级数
     * 
     * @see #getGoodClassifyLevel()
     * @see #setGoodClassifyLevel(int)
     */
    private int goodClassifyLevel;
    /**
     * 轮播大广告图
     * 
     * @see #getRollBigAdImage()
     * @see #setRollBigAdImage(String)
     */
    private String rollBigAdImage;
    /**
     * 轮播小广告图
     * 
     * @see #getRollSmallAdImage()
     * @see #setRollSmallAdImage(String)
     */
    private String rollSmallAdImage;
    /**
     * 新闻公告
     * 
     * @see #getNewNotice()
     * @see #setNewNotice(String)
     */
    private String newNotice;
    /**
     * 首页图片
     * 
     * @see #getIndexImage()
     * @see #setIndexImage(String)
     */
    private String indexImage;
    /**
     * 品牌
     * 
     * @see #getTrademark()
     * @see #setTrademark(String)
     */
    private String trademark;
    /**
     * 楼层
     * 
     * @see #getFloor()
     * @see #setFloor(String)
     */
    private String floor;
    /**
     * 友情链接
     * 
     * @see #getFriendLink()
     * @see #setFriendLink(String)
     */
    private String friendLink;
    /**
     * 页面底部区域
     * 
     * @see #getPageBottomArea()
     * @see #setPageBottomArea(String)
     */
    private String pageBottomArea;
    /**
     * 备用1(页签广告)
     * 
     * @see #getStandby1()
     * @see #setStandby1(String)
     */
    private String standby1;
    /**
     * 备用2
     * 
     * @see #getStandby2()
     * @see #setStandby2(String)
     */
    private String standby2;
    /**
     * 备用3
     * 
     * @see #getStandby3()
     * @see #setStandby3(String)
     */
    private String standby3;
    /**
     * 备用4
     * 
     * @see #getStandby4()
     * @see #setStandby4(String)
     */
    private String standby4;
    /**
     * 备用5
     * 
     * @see #getStandby5()
     * @see #setStandby5(String)
     */
    private String standby5;
    /**
     * 启用
     * 
     * @see #getUsedStatus()
     * @see #setUsedStatus(String)
     */
    private String usedStatus;
    /**
     * 扩展字段1(文章栏目ID)
     * 
     * @see #getExpFleid1()
     * @see #setExpFleid1(String)
     */
    private String expFleid1;
    /**
     * 扩展字段2(新闻公告模块名称)
     * 
     * @see #getExpFleid2()
     * @see #setExpFleid2(String)
     */
    private String expFleid2;
    /**
     * 扩展字段3
     * 
     * @see #getExpFleid3()
     * @see #setExpFleid3(String)
     */
    private String expFleid3;
    /**
     * 扩展字段4
     * 
     * @see #getExpFleid4()
     * @see #setExpFleid4(String)
     */
    private String expFleid4;
    /**
     * 扩展字段5
     * 
     * @see #getExpFleid5()
     * @see #setExpFleid5(String)
     */
    private String expFleid5;
    /**
     * 添加人
     * 
     * @see #getInsertId()
     * @see #setInsertId(int)
     */
    private int insertId;
    /**
     * 添加时间
     * 
     * @see #getInsertDate()
     * @see #setInsertDate(java.util.Date)
     */
    private Date insertDate;
    /**
     * 修改人
     * 
     * @see #getModifyId()
     * @see #setModifyId(int)
     */
    private int modifyId;
    /**
     * 修改时间
     * 
     * @see #getModifyDate()
     * @see #setModifyDate(java.util.Date)
     */
    private Date modifyDate;
    /**
     * 删除标识
     * 
     * @see #getDeleteStatus()
     * @see #setDeleteStatus(int)
     */
    private int deleteStatus;

    /**
     * 热门搜索
     */
    private String hotSearch;

    /**
     * 热销推荐
     */
    private String hotSale;

    /**
     * 页面标签
     */
    private String pageLabel;

    /**
     * 活动
     */
    private String activity;

    /**
     * 页面说明
     */
    private String pageDesc;

    /**
     * 取得：记录ID
     *
     * @return int 记录ID {@link com.ningpai.temp.bean.SysTemp#tempId}
     */
    public final int getTempId() {
        return this.tempId;
    }

    /**
     * 设置：记录ID
     * 
     * @param tempId
     *            记录ID
     */
    public final void setTempId(final int tempId) {
        this.tempId = tempId;
    }

    /**
     * 取得：模板名称
     * 
     * @return String 模板名称 {@link com.ningpai.temp.bean.SysTemp#tempName}
     */
    public final String getTempName() {
        return this.tempName;
    }

    /**
     * 设置：模板名称
     * 
     * @param tempName
     *            模板名称
     */
    public final void setTempName(final String tempName) {
        this.tempName = tempName;
    }

    /**
     * 取得：模板url
     * 
     * @return String 模板url {@link com.ningpai.temp.bean.SysTemp#tempUrl}
     */
    public final String getTempUrl() {
        return this.tempUrl;
    }

    /**
     * 设置：模板url
     * 
     * @param tempUrl
     *            模板url
     */
    public final void setTempUrl(final String tempUrl) {
        this.tempUrl = tempUrl;
    }

    /**
     * 取得：模板参数
     * 
     * @return String 模板参数 {@link com.ningpai.temp.bean.SysTemp#tempPa}
     */
    public final String getTempPa() {
        return this.tempPa;
    }

    /**
     * 设置：模板参数
     * 
     * @param tempPa
     *            模板参数
     */
    public final void setTempPa(final String tempPa) {
        this.tempPa = tempPa;
    }

    /**
     * 取得：模板图片url
     * 
     * @return String 模板图片url
     *         {@link com.ningpai.temp.bean.SysTemp#tempImageUrl}
     */
    public final String getTempImageUrl() {
        return this.tempImageUrl;
    }

    /**
     * 设置：模板图片url
     * 
     * @param tempImageUrl
     *            模板图片url
     */
    public final void setTempImageUrl(final String tempImageUrl) {
        this.tempImageUrl = tempImageUrl;
    }

    /**
     * 取得：描述信息
     * 
     * @return String 描述信息 {@link com.ningpai.temp.bean.SysTemp#des}
     */
    public final String getDes() {
        return this.des;
    }

    /**
     * 设置：描述信息
     * 
     * @param des
     *            描述信息
     */
    public final void setDes(final String des) {
        this.des = des;
    }

    /**
     * 取得：版本信息
     * 
     * @return String 版本信息 {@link com.ningpai.temp.bean.SysTemp#version}
     */
    public final String getVersion() {
        return this.version;
    }

    /**
     * 设置：版本信息
     * 
     * @param version
     *            版本信息
     */
    public final void setVersion(final String version) {
        this.version = version;
    }

    /**
     * 取得：模板类型
     * 
     * @return int 模板类型 {@link com.ningpai.temp.bean.SysTemp#tempType}
     */
    public final int getTempType() {
        return this.tempType;
    }

    /**
     * 设置：模板类型
     * 
     * @param tempType
     *            模板类型
     */
    public final void setTempType(final int tempType) {
        this.tempType = tempType;
    }

    /**
     * 取得：模板类型显示值
     */
    public final String getTempTypeValue() {
        return this.tempTypeValue;
    }

    /**
     * 设置：模板类型显示值
     */
    public final void setTempTypeValue(String tempTypeValue) {
        this.tempTypeValue = tempTypeValue;
    }

    /**
     * 取得：头部区域
     * 
     * @return String 头部区域 {@link com.ningpai.temp.bean.SysTemp#headArea}
     */
    public final String getHeadArea() {
        return this.headArea;
    }

    /**
     * 设置：头部区域
     * 
     * @param headArea
     *            头部区域
     */
    public final void setHeadArea(final String headArea) {
        this.headArea = headArea;
    }

    /**
     * 取得：页面导航
     * 
     * @return String 页面导航 {@link com.ningpai.temp.bean.SysTemp#pageNav}
     */
    public final String getPageNav() {
        return this.pageNav;
    }

    /**
     * 设置：页面导航
     * 
     * @param pageNav
     *            页面导航
     */
    public final void setPageNav(final String pageNav) {
        this.pageNav = pageNav;
    }

    /**
     * 取得：商品分类区域
     * 
     * @return String 商品分类区域
     *         {@link com.ningpai.temp.bean.SysTemp#goodClassifyArea}
     */
    public final String getGoodClassifyArea() {
        return this.goodClassifyArea;
    }

    /**
     * 设置：商品分类区域
     * 
     * @param goodClassifyArea
     *            商品分类区域
     */
    public final void setGoodClassifyArea(final String goodClassifyArea) {
        this.goodClassifyArea = goodClassifyArea;
    }

    /**
     * 取得：商品分类级数
     * 
     * @return int 商品分类级数
     *         {@link com.ningpai.temp.bean.SysTemp#goodClassifyLevel}
     */
    public final int getGoodClassifyLevel() {
        return this.goodClassifyLevel;
    }

    /**
     * 设置：商品分类级数
     * 
     * @param goodClassifyLevel
     *            商品分类级数
     */
    public final void setGoodClassifyLevel(final int goodClassifyLevel) {
        this.goodClassifyLevel = goodClassifyLevel;
    }

    /**
     * 取得：轮播大广告图
     * 
     * @return String 轮播大广告图
     *         {@link com.ningpai.temp.bean.SysTemp#rollBigAdImage}
     */
    public final String getRollBigAdImage() {
        return this.rollBigAdImage;
    }

    /**
     * 设置：轮播大广告图
     * 
     * @param rollBigAdImage
     *            轮播大广告图
     */
    public final void setRollBigAdImage(final String rollBigAdImage) {
        this.rollBigAdImage = rollBigAdImage;
    }

    /**
     * 取得：轮播小广告图
     * 
     * @return String 轮播小广告图
     *         {@link com.ningpai.temp.bean.SysTemp#rollSmallAdImage}
     */
    public final String getRollSmallAdImage() {
        return this.rollSmallAdImage;
    }

    /**
     * 设置：轮播小广告图
     * 
     * @param rollSmallAdImage
     *            轮播小广告图
     */
    public final void setRollSmallAdImage(final String rollSmallAdImage) {
        this.rollSmallAdImage = rollSmallAdImage;
    }

    /**
     * 取得：新闻公告
     * 
     * @return String 新闻公告 {@link com.ningpai.temp.bean.SysTemp#newNotice}
     */
    public final String getNewNotice() {
        return this.newNotice;
    }

    /**
     * 设置：新闻公告
     * 
     * @param newNotice
     *            新闻公告
     */
    public final void setNewNotice(final String newNotice) {
        this.newNotice = newNotice;
    }

    /**
     * 取得：首页图片
     * 
     * @return String 首页图片 {@link com.ningpai.temp.bean.SysTemp#indexImage}
     */
    public final String getIndexImage() {
        return this.indexImage;
    }

    /**
     * 设置：首页图片
     * 
     * @param indexImage
     *            首页图片
     */
    public final void setIndexImage(final String indexImage) {
        this.indexImage = indexImage;
    }

    /**
     * 取得：品牌
     * 
     * @return String 品牌 {@link com.ningpai.temp.bean.SysTemp#trademark}
     */
    public final String getTrademark() {
        return this.trademark;
    }

    /**
     * 设置：品牌
     * 
     * @param trademark
     *            品牌
     */
    public final void setTrademark(final String trademark) {
        this.trademark = trademark;
    }

    /**
     * 取得：楼层
     * 
     * @return String 楼层 {@link com.ningpai.temp.bean.SysTemp#floor}
     */
    public final String getFloor() {
        return this.floor;
    }

    /**
     * 设置：楼层
     * 
     * @param floor
     *            楼层
     */
    public final void setFloor(final String floor) {
        this.floor = floor;
    }

    /**
     * 取得：友情链接
     * 
     * @return String 友情链接 {@link com.ningpai.temp.bean.SysTemp#friendLink}
     */
    public final String getFriendLink() {
        return this.friendLink;
    }

    /**
     * 设置：友情链接
     * 
     * @param friendLink
     *            友情链接
     */
    public final void setFriendLink(final String friendLink) {
        this.friendLink = friendLink;
    }

    /**
     * 取得：页面底部区域
     * 
     * @return String 页面底部区域
     *         {@link com.ningpai.temp.bean.SysTemp#pageBottomArea}
     */
    public final String getPageBottomArea() {
        return this.pageBottomArea;
    }

    /**
     * 设置：页面底部区域
     * 
     * @param pageBottomArea
     *            页面底部区域
     */
    public final void setPageBottomArea(final String pageBottomArea) {
        this.pageBottomArea = pageBottomArea;
    }

    /**
     * 取得：备用1
     * 
     * @return String 备用1 {@link com.ningpai.temp.bean.SysTemp#standby1}
     */
    public final String getStandby1() {
        return this.standby1;
    }

    /**
     * 设置：备用1
     * 
     * @param standby1
     *            备用1
     */
    public final void setStandby1(final String standby1) {
        this.standby1 = standby1;
    }

    /**
     * 取得：备用2
     * 
     * @return String 备用2 {@link com.ningpai.temp.bean.SysTemp#standby2}
     */
    public final String getStandby2() {
        return this.standby2;
    }

    /**
     * 设置：备用2
     * 
     * @param standby2
     *            备用2
     */
    public final void setStandby2(final String standby2) {
        this.standby2 = standby2;
    }

    /**
     * 取得：备用3
     * 
     * @return String 备用3 {@link com.ningpai.temp.bean.SysTemp#standby3}
     */
    public final String getStandby3() {
        return this.standby3;
    }

    /**
     * 设置：备用3
     * 
     * @param standby3
     *            备用3
     */
    public final void setStandby3(final String standby3) {
        this.standby3 = standby3;
    }

    /**
     * 取得：备用4
     * 
     * @return String 备用4 {@link com.ningpai.temp.bean.SysTemp#standby4}
     */
    public final String getStandby4() {
        return this.standby4;
    }

    /**
     * 设置：备用4
     * 
     * @param standby4
     *            备用4
     */
    public final void setStandby4(final String standby4) {
        this.standby4 = standby4;
    }

    /**
     * 取得：备用5
     * 
     * @return String 备用5 {@link com.ningpai.temp.bean.SysTemp#standby5}
     */
    public final String getStandby5() {
        return this.standby5;
    }

    /**
     * 设置：备用5
     * 
     * @param standby5
     *            备用5
     */
    public final void setStandby5(final String standby5) {
        this.standby5 = standby5;
    }

    /**
     * 取得：启用
     * 
     * @return String 启用 {@link com.ningpai.temp.bean.SysTemp#usedStatus}
     */
    public final String getUsedStatus() {
        return this.usedStatus;
    }

    /**
     * 设置：启用
     * 
     * @param usedStatus
     *            启用
     */
    public final void setUsedStatus(final String usedStatus) {
        this.usedStatus = usedStatus;
    }

    /**
     * 取得：扩展字段1
     * 
     * @return String 扩展字段1 {@link com.ningpai.temp.bean.SysTemp#expFleid1}
     */
    public final String getExpFleid1() {
        return this.expFleid1;
    }

    /**
     * 设置：扩展字段1
     * 
     * @param expFleid1
     *            扩展字段1
     */
    public final void setExpFleid1(final String expFleid1) {
        this.expFleid1 = expFleid1;
    }

    /**
     * 取得：扩展字段2
     * 
     * @return String 扩展字段2 {@link com.ningpai.temp.bean.SysTemp#expFleid2}
     */
    public final String getExpFleid2() {
        return this.expFleid2;
    }

    /**
     * 设置：扩展字段2
     * 
     * @param expFleid2
     *            扩展字段2
     */
    public final void setExpFleid2(final String expFleid2) {
        this.expFleid2 = expFleid2;
    }

    /**
     * 取得：扩展字段3
     * 
     * @return String 扩展字段3 {@link com.ningpai.temp.bean.SysTemp#expFleid3}
     */
    public final String getExpFleid3() {
        return this.expFleid3;
    }

    /**
     * 设置：扩展字段3
     * 
     * @param expFleid3
     *            扩展字段3
     */
    public final void setExpFleid3(final String expFleid3) {
        this.expFleid3 = expFleid3;
    }

    /**
     * 取得：扩展字段4
     * 
     * @return String 扩展字段4 {@link com.ningpai.temp.bean.SysTemp#expFleid4}
     */
    public final String getExpFleid4() {
        return this.expFleid4;
    }

    /**
     * 设置：扩展字段4
     * 
     * @param expFleid4
     *            扩展字段4
     */
    public final void setExpFleid4(final String expFleid4) {
        this.expFleid4 = expFleid4;
    }

    /**
     * 取得：扩展字段5
     * 
     * @return String 扩展字段5 {@link com.ningpai.temp.bean.SysTemp#expFleid5}
     */
    public final String getExpFleid5() {
        return this.expFleid5;
    }

    /**
     * 设置：扩展字段5
     * 
     * @param expFleid5
     *            扩展字段5
     */
    public final void setExpFleid5(final String expFleid5) {
        this.expFleid5 = expFleid5;
    }

    /**
     * 取得：添加人
     * 
     * @return int 添加人 {@link com.ningpai.temp.bean.SysTemp#insertId}
     */
    public final int getInsertId() {
        return this.insertId;
    }

    /**
     * 设置：添加人
     * 
     * @param insertId
     *            添加人
     */
    public final void setInsertId(final int insertId) {
        this.insertId = insertId;
    }

    /**
     * 取得：添加时间
     * 
     * @return Date 添加时间 {@link com.ningpai.temp.bean.SysTemp#insertDate}
     */
    public final Date getInsertDate() {
        if (this.insertDate != null) {
            return new Date(this.insertDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置：添加时间
     * 
     * @param insertDate
     *            添加时间
     */
    public final void setInsertDate(final Date insertDate) {
        if (insertDate != null) {
            Date tEmp = (Date) insertDate.clone();
            if (tEmp != null) {
                this.insertDate = tEmp;
            }
        }
    }

    /**
     * 取得：修改人
     * 
     * @return int 修改人 {@link com.ningpai.temp.bean.SysTemp#modifyId}
     */
    public final int getModifyId() {
        return this.modifyId;
    }

    /**
     * 设置：修改人
     * 
     * @param modifyId
     *            修改人
     */
    public final void setModifyId(final int modifyId) {
        this.modifyId = modifyId;
    }

    /**
     * 取得：修改时间
     * 
     * @return Date 修改时间 {@link com.ningpai.temp.bean.SysTemp#modifyDate}
     */
    public final Date getModifyDate() {
        if (this.modifyDate != null) {
            return new Date(this.modifyDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置：修改时间
     * 
     * @param modifyDate
     *            修改时间
     */
    public final void setModifyDate(final Date modifyDate) {
        if (modifyDate != null) {
            Date tEmp = (Date) modifyDate.clone();
            if (tEmp != null) {
                this.modifyDate = tEmp;
            }
        }
    }

    /**
     * 取得：删除标识
     * 
     * @return int 删除标识 {@link com.ningpai.temp.bean.SysTemp#deleteStatus}
     */
    public final int getDeleteStatus() {
        return this.deleteStatus;
    }

    /**
     * 设置：删除标识
     * 
     * @param deleteStatus
     *            删除标识
     */
    public final void setDeleteStatus(final int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getHotSearch() {
        return hotSearch;
    }

    public void setHotSearch(String hotSearch) {
        this.hotSearch = hotSearch;
    }

    public String getHotSale() {
        return hotSale;
    }

    public void setHotSale(String hotSale) {
        this.hotSale = hotSale;
    }

    public String getPageLabel() {
        return pageLabel;
    }

    public void setPageLabel(String pageLabel) {
        this.pageLabel = pageLabel;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getPageDesc() {
        return pageDesc;
    }

    public void setPageDesc(String pageDesc) {
        this.pageDesc = pageDesc;
    }

    /**
     * 模板设置对象简明
     * 
     * @return 模板设置对象简明
     */
    public final String toString() {
        return "模板设置[" + this.tempId + "_" + this.tempName + "]";
    }
}
