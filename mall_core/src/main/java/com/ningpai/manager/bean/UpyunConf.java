package com.ningpai.manager.bean;

import java.util.Date;

/**
 * 又拍云设置
 * 
 * @Title: Entity
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-24 15:44:09
 * @version V1.0
 */

public class UpyunConf implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 记录ID
     * 
     * @see #getUpyunId()
     * @see #setUpyunId(int)
     */
    private int upyunId;
    /**
     * 设置方案标题
     * 
     * @see #getTitle()
     * @see #setTitle(String)
     */
    private String title;
    /**
     * 设置方案代码
     * 
     * @see #getSetCode()
     * @see #setSetCode(String)
     */
    private String setCode;
    /**
     * 空间名
     * 
     * @see #getBucketName()
     * @see #setBucketName(String)
     */
    private String bucketName;
    /**
     * 用户名
     * 
     * @see #getUserName()
     * @see #setUserName(String)
     */
    private String userName;
    /**
     * 密码
     * 
     * @see #getPassWord()
     * @see #setPassWord(String)
     */
    private String passWord;
    /**
     * 空间地址
     * 
     * @see #getUrlPath()
     * @see #setUrlPath(String)
     */
    private String urlPath;
    /**
     * 链接时间
     * 
     * @see #getConnectTime()
     * @see #setConnectTime(int)
     */
    private int connectTime;
    /**
     * debug模式
     * 
     * @see #getDebug()
     * @see #setDebug(String)
     */
    private String debug;
    /**
     * 缩略图
     * 
     * @see #getGmkerl()
     * @see #setGmkerl(String)
     */
    private String gmkerl;
    /**
     * 旋转
     * 
     * @see #getRotate()
     * @see #setRotate(String)
     */
    private String rotate;
    /**
     * 裁剪
     * 
     * @see #getCrop()
     * @see #setCrop(String)
     */
    private String crop;
    /**
     * 操作类型
     * 
     * @see #getOptionType()
     * @see #setOptionType(int)
     */
    private int optionType;
    /** 操作类型显示值 */
    private String optionTypeValue;
    /**
     * ContentMD5
     * 
     * @see #getContentMd5()
     * @see #setContentMd5(String)
     */
    private String contentMd5;
    /**
     * 访问密钥
     * 
     * @see #getVisitSecretKey()
     * @see #setVisitSecretKey(String)
     */
    private String visitSecretKey;
    /**
     * 缩略图类型
     * 
     * @see #getThumbnailType()
     * @see #setThumbnailType(String)
     */
    private String thumbnailType;
    /**
     * 缩略图参数值
     * 
     * @see #getThumbnailNumber()
     * @see #setThumbnailNumber(String)
     */
    private String thumbnailNumber;
    /**
     * 缩略图质量
     * 
     * @see #getThumbnailQuality()
     * @see #setThumbnailQuality(int)
     */
    private int thumbnailQuality;
    /**
     * 缩略图锐化
     * 
     * @see #getThumbnailSharpen()
     * @see #setThumbnailSharpen(String)
     */
    private String thumbnailSharpen;
    /**
     * 缩略图版本名称
     * 
     * @see #getThumbnailVersion()
     * @see #setThumbnailVersion(String)
     */
    private String thumbnailVersion;
    /**
     * 图片旋转
     * 
     * @see #getImageRotation()
     * @see #setImageRotation(String)
     */
    private String imageRotation;
    /**
     * 图片裁剪
     * 
     * @see #getImageTailor()
     * @see #setImageTailor(String)
     */
    private String imageTailor;
    /**
     * 启用
     * 
     * @see #getUsedStatus()
     * @see #setUsedStatus(String)
     */
    private String usedStatus;
    /**
     * 扩展字段1
     * 
     * @see #getExpFleid1()
     * @see #setExpFleid1(String)
     */
    private String expFleid1;
    /**
     * 扩展字段2
     * 
     * @see #getExpFleid2()
     * @see #setExpFleid2(String)
     */
    private String expFleid2;
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
     * 取得：记录ID
     * 
     * @return int 记录ID {@link com.ningpai.system.bean.UpyunConf#upyunId}
     */
    public final int getUpyunId() {
        return this.upyunId;
    }

    /**
     * 设置：记录ID
     * 
     * @param upyunId
     *            记录ID
     */
    public final void setUpyunId(final int upyunId) {
        this.upyunId = upyunId;
    }

    /**
     * 取得：设置方案标题
     * 
     * @return String 设置方案标题 {@link com.ningpai.system.bean.UpyunConf#title}
     */
    public final String getTitle() {
        return this.title;
    }

    /**
     * 设置：设置方案标题
     * 
     * @param title
     *            设置方案标题
     */
    public final void setTitle(final String title) {
        this.title = title;
    }

    /**
     * 取得：设置方案代码
     * 
     * @return String 设置方案代码 {@link com.ningpai.system.bean.UpyunConf#setCode}
     */
    public final String getSetCode() {
        return this.setCode;
    }

    /**
     * 设置：设置方案代码
     * 
     * @param setCode
     *            设置方案代码
     */
    public final void setSetCode(final String setCode) {
        this.setCode = setCode;
    }

    /**
     * 取得：空间名
     * 
     * @return String 空间名 {@link com.ningpai.system.bean.UpyunConf#bucketName}
     */
    public final String getBucketName() {
        return this.bucketName;
    }

    /**
     * 设置：空间名
     * 
     * @param bucketName
     *            空间名
     */
    public final void setBucketName(final String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * 取得：用户名
     * 
     * @return String 用户名 {@link com.ningpai.system.bean.UpyunConf#userName}
     */
    public final String getUserName() {
        return this.userName;
    }

    /**
     * 设置：用户名
     * 
     * @param userName
     *            用户名
     */
    public final void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * 取得：密码
     * 
     * @return String 密码 {@link com.ningpai.system.bean.UpyunConf#passWord}
     */
    public final String getPassWord() {
        return this.passWord;
    }

    /**
     * 设置：密码
     * 
     * @param passWord
     *            密码
     */
    public final void setPassWord(final String passWord) {
        this.passWord = passWord;
    }

    /**
     * 取得：空间地址
     * 
     * @return String 空间地址 {@link com.ningpai.system.bean.UpyunConf#urlPath}
     */
    public final String getUrlPath() {
        return this.urlPath;
    }

    /**
     * 设置：空间地址
     * 
     * @param urlPath
     *            空间地址
     */
    public final void setUrlPath(final String urlPath) {
        this.urlPath = urlPath;
    }

    /**
     * 取得：链接时间
     * 
     * @return int 链接时间 {@link com.ningpai.system.bean.UpyunConf#connectTime}
     */
    public final int getConnectTime() {
        return this.connectTime;
    }

    /**
     * 设置：链接时间
     * 
     * @param connectTime
     *            链接时间
     */
    public final void setConnectTime(final int connectTime) {
        this.connectTime = connectTime;
    }

    /**
     * 取得：debug模式
     * 
     * @return String debug模式 {@link com.ningpai.system.bean.UpyunConf#debug}
     */
    public final String getDebug() {
        return this.debug;
    }

    /**
     * 设置：debug模式
     * 
     * @param debug
     *            debug模式
     */
    public final void setDebug(final String debug) {
        this.debug = debug;
    }

    /**
     * 取得：缩略图
     * 
     * @return String 缩略图 {@link com.ningpai.system.bean.UpyunConf#gmkerl}
     */
    public final String getGmkerl() {
        return this.gmkerl;
    }

    /**
     * 设置：缩略图
     * 
     * @param gmkerl
     *            缩略图
     */
    public final void setGmkerl(final String gmkerl) {
        this.gmkerl = gmkerl;
    }

    /**
     * 取得：旋转
     * 
     * @return String 旋转 {@link com.ningpai.system.bean.UpyunConf#rotate}
     */
    public final String getRotate() {
        return this.rotate;
    }

    /**
     * 设置：旋转
     * 
     * @param rotate
     *            旋转
     */
    public final void setRotate(final String rotate) {
        this.rotate = rotate;
    }

    /**
     * 取得：裁剪
     * 
     * @return String 裁剪 {@link com.ningpai.system.bean.UpyunConf#crop}
     */
    public final String getCrop() {
        return this.crop;
    }

    /**
     * 设置：裁剪
     * 
     * @param crop
     *            裁剪
     */
    public final void setCrop(final String crop) {
        this.crop = crop;
    }

    /**
     * 取得：操作类型
     * 
     * @return int 操作类型 {@link com.ningpai.system.bean.UpyunConf#optionType}
     */
    public final int getOptionType() {
        return this.optionType;
    }

    /**
     * 设置：操作类型
     * 
     * @param optionType
     *            操作类型
     */
    public final void setOptionType(final int optionType) {
        this.optionType = optionType;
    }

    /**
     * 取得：操作类型显示值
     */
    public final String getOptionTypeValue() {
        return this.optionTypeValue;
    }

    /**
     * 设置：操作类型显示值
     */
    public final void setOptionTypeValue(String optionTypeValue) {
        this.optionTypeValue = optionTypeValue;
    }

    /**
     * 取得：ContentMD5
     * 
     * @return String ContentMD5
     *         {@link com.ningpai.system.bean.UpyunConf#contentMd5}
     */
    public final String getContentMd5() {
        return this.contentMd5;
    }

    /**
     * 设置：ContentMD5
     * 
     * @param contentMd5
     *            ContentMD5
     */
    public final void setContentMd5(final String contentMd5) {
        this.contentMd5 = contentMd5;
    }

    /**
     * 取得：访问密钥
     * 
     * @return String 访问密钥
     *         {@link com.ningpai.system.bean.UpyunConf#visitSecretKey}
     */
    public final String getVisitSecretKey() {
        return this.visitSecretKey;
    }

    /**
     * 设置：访问密钥
     * 
     * @param visitSecretKey
     *            访问密钥
     */
    public final void setVisitSecretKey(final String visitSecretKey) {
        this.visitSecretKey = visitSecretKey;
    }

    /**
     * 取得：缩略图类型
     * 
     * @return String 缩略图类型
     *         {@link com.ningpai.system.bean.UpyunConf#thumbnailType}
     */
    public final String getThumbnailType() {
        return this.thumbnailType;
    }

    /**
     * 设置：缩略图类型
     * 
     * @param thumbnailType
     *            缩略图类型
     */
    public final void setThumbnailType(final String thumbnailType) {
        this.thumbnailType = thumbnailType;
    }

    /**
     * 取得：缩略图参数值
     * 
     * @return String 缩略图参数值
     *         {@link com.ningpai.system.bean.UpyunConf#thumbnailNumber}
     */
    public final String getThumbnailNumber() {
        return this.thumbnailNumber;
    }

    /**
     * 设置：缩略图参数值
     * 
     * @param thumbnailNumber
     *            缩略图参数值
     */
    public final void setThumbnailNumber(final String thumbnailNumber) {
        this.thumbnailNumber = thumbnailNumber;
    }

    /**
     * 取得：缩略图质量
     * 
     * @return int 缩略图质量
     *         {@link com.ningpai.system.bean.UpyunConf#thumbnailQuality}
     */
    public final int getThumbnailQuality() {
        return this.thumbnailQuality;
    }

    /**
     * 设置：缩略图质量
     * 
     * @param thumbnailQuality
     *            缩略图质量
     */
    public final void setThumbnailQuality(final int thumbnailQuality) {
        this.thumbnailQuality = thumbnailQuality;
    }

    /**
     * 取得：缩略图锐化
     * 
     * @return String 缩略图锐化
     *         {@link com.ningpai.system.bean.UpyunConf#thumbnailSharpen}
     */
    public final String getThumbnailSharpen() {
        return this.thumbnailSharpen;
    }

    /**
     * 设置：缩略图锐化
     * 
     * @param thumbnailSharpen
     *            缩略图锐化
     */
    public final void setThumbnailSharpen(final String thumbnailSharpen) {
        this.thumbnailSharpen = thumbnailSharpen;
    }

    /**
     * 取得：缩略图版本名称
     * 
     * @return String 缩略图版本名称
     *         {@link com.ningpai.system.bean.UpyunConf#thumbnailVersion}
     */
    public final String getThumbnailVersion() {
        return this.thumbnailVersion;
    }

    /**
     * 设置：缩略图版本名称
     * 
     * @param thumbnailVersion
     *            缩略图版本名称
     */
    public final void setThumbnailVersion(final String thumbnailVersion) {
        this.thumbnailVersion = thumbnailVersion;
    }

    /**
     * 取得：图片旋转
     * 
     * @return String 图片旋转
     *         {@link com.ningpai.system.bean.UpyunConf#imageRotation}
     */
    public final String getImageRotation() {
        return this.imageRotation;
    }

    /**
     * 设置：图片旋转
     * 
     * @param imageRotation
     *            图片旋转
     */
    public final void setImageRotation(final String imageRotation) {
        this.imageRotation = imageRotation;
    }

    /**
     * 取得：图片裁剪
     * 
     * @return String 图片裁剪 {@link com.ningpai.system.bean.UpyunConf#imageTailor}
     */
    public final String getImageTailor() {
        return this.imageTailor;
    }

    /**
     * 设置：图片裁剪
     * 
     * @param imageTailor
     *            图片裁剪
     */
    public final void setImageTailor(final String imageTailor) {
        this.imageTailor = imageTailor;
    }

    /**
     * 取得：启用
     * 
     * @return String 启用 {@link com.ningpai.system.bean.UpyunConf#usedStatus}
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
     * @return String 扩展字段1 {@link com.ningpai.system.bean.UpyunConf#expFleid1}
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
     * @return String 扩展字段2 {@link com.ningpai.system.bean.UpyunConf#expFleid2}
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
     * 取得：添加人
     * 
     * @return int 添加人 {@link com.ningpai.system.bean.UpyunConf#insertId}
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
     * @return Date 添加时间 {@link com.ningpai.system.bean.UpyunConf#insertDate}
     */
    public final Date getInsertDate() {
        if (this.insertDate != null) {
            return new Date(this.insertDate.getTime());
        }
        return null;
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
     * @return int 修改人 {@link com.ningpai.system.bean.UpyunConf#modifyId}
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
     * @return Date 修改时间 {@link com.ningpai.system.bean.UpyunConf#modifyDate}
     */
    public final Date getModifyDate() {
        if (this.modifyDate != null) {
            return new Date(this.modifyDate.getTime());
        }
        return null;
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
     * @return int 删除标识 {@link com.ningpai.system.bean.UpyunConf#deleteStatus}
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

    /**
     * 又拍云设置对象简明
     * 
     * @return 又拍云设置对象简明
     */
    public final String toString() {
        return "又拍云设置[" + this.upyunId + "_" + this.title + "]";
    }
}
