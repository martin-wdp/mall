/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**  
 * @Description: np_app表的实体bean
 * @author Ningpai-HEHU
 * @date 2015-06-10 18:21:40
 * @version V1.0  
 */
public class App implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *主键id
    */
    private Long id;

    /**
    *APP ID
    */
    private String appId;

    /**
    *APP logo
    */
    private String appLogo;

    /**
    *app名称
    */
    private String appName;

    /**
    *应用描述
    */
    private String appDesc;

    /**
     *应用文件路径
     */
    private String appLocation;

    /**
     *应用版本
     */
    private String appVersion;
    /**
    *安装数量
    */
    private Long installCount;

    /**
    *发布状态：0未发布，1已发布
    */
    private String publishStatus;

    /**
    *创建时间
    */
    private Date createTime;

    /**
    *发布时间
    */
    private Date publishTime;

    /**
    *创建人id
    */
    private Long userId;

    /**
    *删除状态:0未删除，1已删除
    */
    private String delFlag;

    /**
     * 安装时执行的SQL
     */
    private String loadSql;

    /**
     * 启用时执行的SQL
     */
    private String startSql;

    /**
     * 停用时执行的SQL
     */
    private String stopSql;

    /**
     * 卸载时执行的SQL
     */
    private String unloadSql;

    /**
     * 更新时执行的SQL
     */
    private String updateSql;

    /**
     *应用文件路径(第三方)
     */

    private String appThirdLocation;

    /**
     *应用文件路径（前台）
     */
    private String appSiteLocation;

    /**
     * 菜单开始id
     */
    private Long startMenuId;

    /**
     * 菜单结束id
     */
    private Long endMenuId;

    /**
     * APP简介
     */
    private String appIntroduction;
    /**
     * 模块唯一标示名称，卸载应用时，根据此字段值删除权限
     */
    private String bundleName;
    /**
     * 所需内存
     */
    private BigDecimal needMemory;

    /**
     * 副标题
     */
    private String subTitle;

    public Long getStartMenuId() {
        return startMenuId;
    }

    public void setStartMenuId(Long startMenuId) {
        this.startMenuId = startMenuId;
    }

    public Long getEndMenuId() {
        return endMenuId;
    }

    public void setEndMenuId(Long endMenuId) {
        this.endMenuId = endMenuId;
    }

    public String getLoadSql() {
        return loadSql;
    }

    public void setLoadSql(String loadSql) {
        this.loadSql = loadSql;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppLogo() {
        return appLogo;
    }

    public void setAppLogo(String appLogo) {
        this.appLogo = appLogo;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public Long getInstallCount() {
        return installCount;
    }

    public void setInstallCount(Long installCount) {
        this.installCount = installCount;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getAppLocation() {
        return appLocation;
    }

    public void setAppLocation(String appLocation) {
        this.appLocation = appLocation;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getStartSql() {
        return startSql;
    }

    public void setStartSql(String startSql) {
        this.startSql = startSql;
    }

    public String getStopSql() {
        return stopSql;
    }

    public void setStopSql(String stopSql) {
        this.stopSql = stopSql;
    }

    public String getUnloadSql() {
        return unloadSql;
    }

    public void setUnloadSql(String unloadSql) {
        this.unloadSql = unloadSql;
    }

    public String getAppThirdLocation() {
        return appThirdLocation;
    }

    public void setAppThirdLocation(String appThirdLocation) {
        this.appThirdLocation = appThirdLocation;
    }

    public String getAppSiteLocation() {
        return appSiteLocation;
    }

    public void setAppSiteLocation(String appSiteLocation) {
        this.appSiteLocation = appSiteLocation;
    }

    public String getAppIntroduction() {
        return appIntroduction;
    }

    public void setAppIntroduction(String appIntroduction) {
        this.appIntroduction = appIntroduction;
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public BigDecimal getNeedMemory() {
        return needMemory;
    }

    public void setNeedMemory(BigDecimal needMemory) {
        this.needMemory = needMemory;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getUpdateSql() {
        return updateSql;
    }

    public void setUpdateSql(String updateSql) {
        this.updateSql = updateSql;
    }
}