/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**  
 * @Description: np_app_install表的实体bean
 * @author Ningpai-HEHU
 * @date 2015-06-12 11:40:17
 * @version V1.0
 */
public class AppInstall implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *主键id
    */
    private Long installId;

    /**
    *应用id
    */
    private Long appId;

    /**
    *APPID
    */
    private String appKey;

    /**
    *应用名称
    */
    private String appName;

    /**
    *安装状态：0未下载，1已下载未安装，2已安装未启动，3已启动，4已停止。
    */
    private String installStatus;

    /**
    *安装时间
    */
    private Date installTime;

    /**
    *安装时的版本
    */
    private String installVersion;

    /**
     * bundle唯一标识
     */
    private Long bundleId;

    /**
     * 应用下载全路径
     */
    private String bundleLocation;
    /**
     * 前台应用文件包全路径
     */
    private String appSiteLocation;
    /**
     * 第三方应用文件包全路径
     */
    private String appThirdLocation;

    /**
    *删除状态：0未删除，1已删除
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
     * 模块唯一标示名称，卸载应用时，根据此字段值删除权限
     */
    private String bundleName;

    /**
     * AppLogo
     */
    private String appLogo;
    /**
     * 所需内存
     */
    private BigDecimal needMemory;
    /**
     * APP简介
     */
    private String appIntroduction;

    /**
     * 副标题
     */
    private String subTitle;

    /**
     * 更新标记，0表示不用更新，1表示待更新可更新
     */
    private String updateFlag;

    public String getAppLogo() {
        return appLogo;
    }

    public void setAppLogo(String appLogo) {
        this.appLogo = appLogo;
    }

    public Long getInstallId() {
        return installId;
    }

    public void setInstallId(Long installId) {
        this.installId = installId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getInstallStatus() {
        return installStatus;
    }

    public void setInstallStatus(String installStatus) {
        this.installStatus = installStatus;
    }

    public Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    public String getInstallVersion() {
        return installVersion;
    }

    public void setInstallVersion(String installVersion) {
        this.installVersion = installVersion;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getBundleId() {
        return bundleId;
    }

    public void setBundleId(Long bundleId) {
        this.bundleId = bundleId;
    }

    public String getBundleLocation() {
        return bundleLocation;
    }

    public void setBundleLocation(String bundleLocation) {
        this.bundleLocation = bundleLocation;
    }

    public String getAppSiteLocation() {
        return appSiteLocation;
    }

    public void setAppSiteLocation(String appSiteLocation) {
        this.appSiteLocation = appSiteLocation;
    }

    public String getAppThirdLocation() {
        return appThirdLocation;
    }

    public void setAppThirdLocation(String appThirdLocation) {
        this.appThirdLocation = appThirdLocation;
    }

    public String getLoadSql() {
        return loadSql;
    }

    public void setLoadSql(String loadSql) {
        this.loadSql = loadSql;
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

    public String getAppIntroduction() {
        return appIntroduction;
    }

    public void setAppIntroduction(String appIntroduction) {
        this.appIntroduction = appIntroduction;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }
}