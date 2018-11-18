package com.ningpai.temp.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
@Component("Temp")
public class SysTemp {
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
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String tempName;
    /**
     * 模板url
     * 
     * @see #getTempUrl()
     * @see #setTempUrl(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String tempUrl;
    /**
     * 模板参数
     * 
     * @see #getTempPa()
     * @see #setTempPa(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String tempPa;
    /**
     * 模板图片url
     * 
     * @see #getTempImageUrl()
     * @see #setTempImageUrl(String)
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String tempImageUrl;
    /**
     * 描述信息
     * 
     * @see #getDes()
     * @see #setDes(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String des;
    /**
     * 版本信息
     * 
     * @see #getVersion()
     * @see #setVersion(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String version;
    /**
     * 模板类型
     * 
     * @see #getTempType()
     * @see #setTempType(Long)
     */
    private Long tempType;
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
     * 备用2(第三方商家标示)
     * 
     * @see #getStandby2()
     * @see #setStandby2(String)
     */
    private String standby2;
    /**
     * 备用3(第三方商家ID)
     * 
     * @see #getStandby3()
     * @see #setStandby3(String)
     */
    private String standby3;
    /**
     * 备用4(单页标签ID)
     * 
     * @see #getStandby4()
     * @see #setStandby4(String)
     */
    private String standby4;
    /**
     * 备用5(活动名称)
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
     * 扩展字段1（文章栏目ID）
     * 
     * @see #getExpFleid1()
     * @see #setExpFleid1(String)
     */
    private String expFleid1;
    /**
     * 扩展字段2（公告模块名称）
     * 
     * @see #getExpFleid2()
     * @see #setExpFleid2(String)
     */
    private String expFleid2;
    /**
     * 扩展字段3（分类导航是否显示广告）
     * 
     * @see #getExpFleid3()
     * @see #setExpFleid3(String)
     */
    private String expFleid3;
    /**
     * 扩展字段4（分类导航是否显示品牌）
     * 
     * @see #getExpFleid4()
     * @see #setExpFleid4(String)
     */
    private String expFleid4;
    /**
     * 扩展字段5 (是否显示分类导航)
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
     * @see #setInsertDate(Date)
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
     * @see #setModifyDate(Date)
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
     * @return int 记录ID {@link com.ningpai.system.bean.SysTemp#tempId}
     */
    public int getTempId() {
        return this.tempId;
    }

    /**
     * 设置：记录ID
     * 
     * @param tempId
     *            记录ID
     */
    public void setTempId(int tempId) {
        this.tempId = tempId;
    }

    /**
     * 取得：模板名称
     * 
     * @return String 模板名称 {@link com.ningpai.system.bean.SysTemp#tempName}
     */
    public String getTempName() {
        return this.tempName;
    }

    /**
     * 设置：模板名称
     * 
     * @param tempName
     *            模板名称
     */
    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    /**
     * 取得：模板url
     * 
     * @return String 模板url {@link com.ningpai.system.bean.SysTemp#tempUrl}
     */
    public String getTempUrl() {
        return this.tempUrl;
    }

    /**
     * 设置：模板url
     * 
     * @param tempUrl
     *            模板url
     */
    public void setTempUrl(String tempUrl) {
        this.tempUrl = tempUrl;
    }

    /**
     * 取得：模板参数
     * 
     * @return String 模板参数 {@link com.ningpai.system.bean.SysTemp#tempPa}
     */
    public String getTempPa() {
        return this.tempPa;
    }

    /**
     * 设置：模板参数
     * 
     * @param tempPa
     *            模板参数
     */
    public void setTempPa(String tempPa) {
        this.tempPa = tempPa;
    }

    /**
     * 取得：模板图片url
     * 
     * @return String 模板图片url
     *         {@link com.ningpai.system.bean.SysTemp#tempImageUrl}
     */
    public String getTempImageUrl() {
        return this.tempImageUrl;
    }

    /**
     * 设置：模板图片url
     * 
     * @param tempImageUrl
     *            模板图片url
     */
    public void setTempImageUrl(String tempImageUrl) {
        this.tempImageUrl = tempImageUrl;
    }

    /**
     * 取得：描述信息
     * 
     * @return String 描述信息 {@link com.ningpai.system.bean.SysTemp#des}
     */
    public String getDes() {
        return this.des;
    }

    /**
     * 设置：描述信息
     * 
     * @param des
     *            描述信息
     */
    public void setDes(String des) {
        this.des = des;
    }

    /**
     * 取得：版本信息
     * 
     * @return String 版本信息 {@link com.ningpai.system.bean.SysTemp#version}
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * 设置：版本信息
     * 
     * @param version
     *            版本信息
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 取得：模板类型
     * 
     * @return int 模板类型 {@link com.ningpai.temp.bean.SysTemp#tempType}
     */
    public Long getTempType() {
        return this.tempType;
    }

    /**
     * 设置：模板类型
     * 
     * @param tempType
     *            模板类型
     */
    public void setTempType(Long tempType) {
        this.tempType = tempType;
    }

    /**
     * 取得：模板类型显示值
     */
    public String getTempTypeValue() {
        return this.tempTypeValue;
    }

    /**
     * 设置：模板类型显示值
     */
    public void setTempTypeValue(String tempTypeValue) {
        this.tempTypeValue = tempTypeValue;
    }

    /**
     * 取得：头部区域
     * 
     * @return String 头部区域 {@link com.ningpai.system.bean.SysTemp#headArea}
     */
    public String getHeadArea() {
        return this.headArea;
    }

    /**
     * 设置：头部区域
     * 
     * @param headArea
     *            头部区域
     */
    public void setHeadArea(String headArea) {
        this.headArea = headArea;
    }

    /**
     * 取得：页面导航
     * 
     * @return String 页面导航 {@link com.ningpai.system.bean.SysTemp#pageNav}
     */
    public String getPageNav() {
        return this.pageNav;
    }

    /**
     * 设置：页面导航
     * 
     * @param pageNav
     *            页面导航
     */
    public void setPageNav(String pageNav) {
        this.pageNav = pageNav;
    }

    /**
     * 取得：商品分类区域
     * 
     * @return String 商品分类区域
     *         {@link com.ningpai.system.bean.SysTemp#goodClassifyArea}
     */
    public String getGoodClassifyArea() {
        return this.goodClassifyArea;
    }

    /**
     * 设置：商品分类区域
     * 
     * @param goodClassifyArea
     *            商品分类区域
     */
    public void setGoodClassifyArea(String goodClassifyArea) {
        this.goodClassifyArea = goodClassifyArea;
    }

    /**
     * 取得：商品分类级数
     * 
     * @return int 商品分类级数
     *         {@link com.ningpai.system.bean.SysTemp#goodClassifyLevel}
     */
    public int getGoodClassifyLevel() {
        return this.goodClassifyLevel;
    }

    /**
     * 设置：商品分类级数
     * 
     * @param goodClassifyLevel
     *            商品分类级数
     */
    public void setGoodClassifyLevel(int goodClassifyLevel) {
        this.goodClassifyLevel = goodClassifyLevel;
    }

    /**
     * 取得：轮播大广告图
     * 
     * @return String 轮播大广告图
     *         {@link com.ningpai.system.bean.SysTemp#rollBigAdImage}
     */
    public String getRollBigAdImage() {
        return this.rollBigAdImage;
    }

    /**
     * 设置：轮播大广告图
     * 
     * @param rollBigAdImage
     *            轮播大广告图
     */
    public void setRollBigAdImage(String rollBigAdImage) {
        this.rollBigAdImage = rollBigAdImage;
    }

    /**
     * 取得：轮播小广告图
     * 
     * @return String 轮播小广告图
     *         {@link com.ningpai.system.bean.SysTemp#rollSmallAdImage}
     */
    public String getRollSmallAdImage() {
        return this.rollSmallAdImage;
    }

    /**
     * 设置：轮播小广告图
     * 
     * @param rollSmallAdImage
     *            轮播小广告图
     */
    public void setRollSmallAdImage(String rollSmallAdImage) {
        this.rollSmallAdImage = rollSmallAdImage;
    }

    /**
     * 取得：新闻公告
     * 
     * @return String 新闻公告 {@link com.ningpai.system.bean.SysTemp#newNotice}
     */
    public String getNewNotice() {
        return this.newNotice;
    }

    /**
     * 设置：新闻公告
     * 
     * @param newNotice
     *            新闻公告
     */
    public void setNewNotice(String newNotice) {
        this.newNotice = newNotice;
    }

    /**
     * 取得：首页图片
     * 
     * @return String 首页图片 {@link com.ningpai.system.bean.SysTemp#indexImage}
     */
    public String getIndexImage() {
        return this.indexImage;
    }

    /**
     * 设置：首页图片
     * 
     * @param indexImage
     *            首页图片
     */
    public void setIndexImage(String indexImage) {
        this.indexImage = indexImage;
    }

    /**
     * 取得：品牌
     * 
     * @return String 品牌 {@link com.ningpai.system.bean.SysTemp#trademark}
     */
    public String getTrademark() {
        return this.trademark;
    }

    /**
     * 设置：品牌
     * 
     * @param trademark
     *            品牌
     */
    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    /**
     * 取得：楼层
     * 
     * @return String 楼层 {@link com.ningpai.system.bean.SysTemp#floor}
     */
    public String getFloor() {
        return this.floor;
    }

    /**
     * 设置：楼层
     * 
     * @param floor
     *            楼层
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * 取得：友情链接
     * 
     * @return String 友情链接 {@link com.ningpai.system.bean.SysTemp#friendLink}
     */
    public String getFriendLink() {
        return this.friendLink;
    }

    /**
     * 设置：友情链接
     * 
     * @param friendLink
     *            友情链接
     */
    public void setFriendLink(String friendLink) {
        this.friendLink = friendLink;
    }

    /**
     * 取得：页面底部区域
     * 
     * @return String 页面底部区域
     *         {@link com.ningpai.system.bean.SysTemp#pageBottomArea}
     */
    public String getPageBottomArea() {
        return this.pageBottomArea;
    }

    /**
     * 设置：页面底部区域
     * 
     * @param pageBottomArea
     *            页面底部区域
     */
    public void setPageBottomArea(String pageBottomArea) {
        this.pageBottomArea = pageBottomArea;
    }

    /**
     * 取得：备用1
     * 
     * @return String 备用1 {@link com.ningpai.system.bean.SysTemp#standby1}
     */
    public String getStandby1() {
        return this.standby1;
    }

    /**
     * 设置：备用1
     * 
     * @param standby1
     *            备用1
     */
    public void setStandby1(String standby1) {
        this.standby1 = standby1;
    }

    /**
     * 取得：备用2
     * 
     * @return String 备用2 {@link com.ningpai.system.bean.SysTemp#standby2}
     */
    public String getStandby2() {
        return this.standby2;
    }

    /**
     * 设置：备用2
     * 
     * @param standby2
     *            备用2
     */
    public void setStandby2(String standby2) {
        this.standby2 = standby2;
    }

    /**
     * 取得：备用3
     * 
     * @return String 备用3 {@link com.ningpai.system.bean.SysTemp#standby3}
     */
    public String getStandby3() {
        return this.standby3;
    }

    /**
     * 设置：备用3
     * 
     * @param standby3
     *            备用3
     */
    public void setStandby3(String standby3) {
        this.standby3 = standby3;
    }

    /**
     * 取得：备用4
     * 
     * @return String 备用4 {@link com.ningpai.system.bean.SysTemp#standby4}
     */
    public String getStandby4() {
        return this.standby4;
    }

    /**
     * 设置：备用4
     * 
     * @param standby4
     *            备用4
     */
    public void setStandby4(String standby4) {
        this.standby4 = standby4;
    }

    /**
     * 取得：备用5
     * 
     * @return String 备用5 {@link com.ningpai.system.bean.SysTemp#standby5}
     */
    public String getStandby5() {
        return this.standby5;
    }

    /**
     * 设置：备用5
     * 
     * @param standby5
     *            备用5
     */
    public void setStandby5(String standby5) {
        this.standby5 = standby5;
    }

    /**
     * 取得：启用
     * 
     * @return String 启用 {@link com.ningpai.system.bean.SysTemp#usedStatus}
     */
    public String getUsedStatus() {
        return this.usedStatus;
    }

    /**
     * 设置：启用
     * 
     * @param usedStatus
     *            启用
     */
    public void setUsedStatus(String usedStatus) {
        this.usedStatus = usedStatus;
    }

    /**
     * 取得：扩展字段1
     * 
     * @return String 扩展字段1 {@link com.ningpai.system.bean.SysTemp#expFleid1}
     */
    public String getExpFleid1() {
        return this.expFleid1;
    }

    /**
     * 设置：扩展字段1
     * 
     * @param expFleid1
     *            扩展字段1
     */
    public void setExpFleid1(String expFleid1) {
        this.expFleid1 = expFleid1;
    }

    /**
     * 取得：扩展字段2
     * 
     * @return String 扩展字段2 {@link com.ningpai.system.bean.SysTemp#expFleid2}
     */
    public String getExpFleid2() {
        return this.expFleid2;
    }

    /**
     * 设置：扩展字段2
     * 
     * @param expFleid2
     *            扩展字段2
     */
    public void setExpFleid2(String expFleid2) {
        this.expFleid2 = expFleid2;
    }

    /**
     * 取得：扩展字段3
     * 
     * @return String 扩展字段3 {@link com.ningpai.system.bean.SysTemp#expFleid3}
     */
    public String getExpFleid3() {
        return this.expFleid3;
    }

    /**
     * 设置：扩展字段3
     * 
     * @param expFleid3
     *            扩展字段3
     */
    public void setExpFleid3(String expFleid3) {
        this.expFleid3 = expFleid3;
    }

    /**
     * 取得：扩展字段4
     * 
     * @return String 扩展字段4 {@link com.ningpai.system.bean.SysTemp#expFleid4}
     */
    public String getExpFleid4() {
        return this.expFleid4;
    }

    /**
     * 设置：扩展字段4
     * 
     * @param expFleid4
     *            扩展字段4
     */
    public void setExpFleid4(String expFleid4) {
        this.expFleid4 = expFleid4;
    }

    /**
     * 取得：扩展字段5
     * 
     * @return String 扩展字段5 {@link com.ningpai.system.bean.SysTemp#expFleid5}
     */
    public String getExpFleid5() {
        return this.expFleid5;
    }

    /**
     * 设置：扩展字段5
     * 
     * @param expFleid5
     *            扩展字段5
     */
    public void setExpFleid5(String expFleid5) {
        this.expFleid5 = expFleid5;
    }

    /**
     * 取得：添加人
     * 
     * @return int 添加人 {@link com.ningpai.system.bean.SysTemp#insertId}
     */
    public int getInsertId() {
        return this.insertId;
    }

    /**
     * 设置：添加人
     * 
     * @param insertId
     *            添加人
     */
    public void setInsertId(int insertId) {
        this.insertId = insertId;
    }

    /**
     * 取得：添加时间
     * 
     * @return Date 添加时间 {@link com.ningpai.system.bean.SysTemp#insertDate}
     */
    public Date getInsertDate() {
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
    public void setInsertDate(Date insertDate) {
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
     * @return int 修改人 {@link com.ningpai.system.bean.SysTemp#modifyId}
     */
    public int getModifyId() {
        return this.modifyId;
    }

    /**
     * 设置：修改人
     * 
     * @param modifyId
     *            修改人
     */
    public void setModifyId(int modifyId) {
        this.modifyId = modifyId;
    }

    /**
     * 取得：修改时间
     * 
     * @return Date 修改时间 {@link com.ningpai.system.bean.SysTemp#modifyDate}
     */
    public Date getModifyDate() {
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
    public void setModifyDate(Date modifyDate) {
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
     * @return int 删除标识 {@link com.ningpai.system.bean.SysTemp#deleteStatus}
     */
    public int getDeleteStatus() {
        return this.deleteStatus;
    }

    /**
     * 设置：删除标识
     * 
     * @param deleteStatus
     *            删除标识
     */
    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * 模板设置对象简明
     * 
     * @return 模板设置对象简明
     */
    public String toString() {
        return "模板设置[" + this.tempId + "_" + this.tempName + "]";
    }

    /**
     * 热门搜索
     * 
     * @return String
     */
    public String getHotSearch() {
        return this.hotSearch;
    }

    /**
     * 热门搜索
     * 
     * @param hotSearch
     */
    public void setHotSearch(String hotSearch) {
        this.hotSearch = hotSearch;
    }

    /**
     * 热销推荐
     * 
     * @return String
     */
    public String getHotSale() {
        return this.hotSale;
    }

    /**
     * 热销推荐
     * 
     * @param hotSale
     */
    public void setHotSale(String hotSale) {
        this.hotSale = hotSale;
    }

    /**
     * 页面标签
     * 
     * @return String
     */
    public String getPageLabel() {
        return this.pageLabel;
    }

    /**
     * 页面标签
     * 
     * @param pageLabel
     */
    public void setPageLabel(String pageLabel) {
        this.pageLabel = pageLabel;
    }

    /**
     * 活动
     * 
     * @return
     */
    public String getActivity() {
        return this.activity;
    }

    /**
     * 活动
     * 
     * @param activity
     */
    public void setActivity(String activity) {
        this.activity = activity;
    }

    /**
     * 页面说明
     * 
     * @return String
     */
    public String getPageDesc() {
        return this.pageDesc;
    }

    /**
     * 页面说明
     * 
     * @param pageDesc
     */
    public void setPageDesc(String pageDesc) {
        this.pageDesc = pageDesc;
    }

}
