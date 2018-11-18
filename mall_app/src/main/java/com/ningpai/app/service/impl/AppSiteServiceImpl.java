/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.service.impl;

import com.ningpai.app.bean.App;
import com.ningpai.app.bean.AppInstall;
import com.ningpai.app.bean.AppMarketKey;
import com.ningpai.app.bean.AppServerBundle;
import com.ningpai.app.service.*;
import com.ningpai.app.util.AppConstant;
import com.ningpai.osgi.activator.Activator;
import com.ningpai.util.PropertieUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

/**
 * @Description: np_app的service的实现类:
 * @author Ningpai-HEHU
 * @date 2015-06-10 18:21:40
 * @version V1.0
 */
@Service("AppSiteService")
public class AppSiteServiceImpl implements AppService {

    /** 记录日志对象 */
    private static final Logger LOGGER = Logger.getLogger(AppSiteServiceImpl.class);

    /** App安装记录接口 */
    @Resource(name = "AppInstallService")
    private AppInstallService appInstallService;

    /** 应用市场秘钥接口 */
    @Resource(name = "AppMarketKeyService")
    private AppMarketKeyService appMarketKeyService;

    /** APPClientService接口，负责调度 */
    @Resource(name = "AppClientService")
    private AppClientService appClientService;

    /** AppServerBundleService接口，提供模块和服务器地址之间关系的服务 */
    @Resource(name = "AppServerBundleService")
    private AppServerBundleService appServerBundleService;

    /**
     * 安装Boss端app， 从app市场服务器上下载到karaf_home中
     * 
     * @param id
     *            id
     * @param serverId
     *            服务器ID
     * @return 安装结果
     * @throws BundleException
     * @throws IOException
     */
    @Override
    public Bundle downloadAndInstallApp(Long id, Long serverId) throws BundleException, IOException {
        // 1、从服务器端查询应用信息
        App app = (App) appClientService.queryAppDetail(id);
        String bundleLocation;
        Bundle bundle = null;
        if (StringUtils.isNotEmpty(app.getAppSiteLocation())) {
            // 2、从服务器端下载应用包
            bundleLocation = downloadAppFromServer(id, app.getAppVersion(), app.getAppSiteLocation());
            // 3、下载应用后，将应用包安装到容器
            if (bundleLocation != null) {
                bundle = Activator.getBundleContext().installBundle("file:" + bundleLocation);
                // 4、安装完成后，将安装记录保存
                saveAppInstall(app, bundle, serverId);
            }
        }
        return bundle;
    }

    /**
     * 保存应用安装记录
     * 
     * @param app
     *            应用信息
     * @param bundle
     *            bundle信息
     * @param serverId
     *            服务器ID
     */
    public void saveAppInstall(App app, Bundle bundle, Long serverId) {
        AppServerBundle appServerBundle = new AppServerBundle();
        appServerBundle.setAppId(app.getId());
        appServerBundle.setBundleId(bundle == null ? null : bundle.getBundleId());
        appServerBundle.setServerId(serverId);
        appServerBundleService.insert(appServerBundle);
    }

    /**
     * 从服务器下载应用程序包
     * 
     * @param id
     *            应用id
     * @param appVersion
     *            应用程序版本
     * @param appLocation
     *            应用程序位置
     * @return 下载到本地后的全路径
     * @throws IOException
     */
    public String downloadAppFromServer(Long id, String appVersion, String appLocation) throws IOException {
        AppInstall appInstall = appInstallService.selectByAppId(id);
        // 已安装过，且应用不存在前台文件包，就不要去下载了
        if (appInstall != null && StringUtils.isEmpty(appInstall.getAppSiteLocation())) {
            return null;
        }
        // 已安装过，且存在文件包，且版本与服务器版本一致
        if (appInstall != null && StringUtils.isNotEmpty(appInstall.getAppSiteLocation())) {
            File file = new File(appInstall.getAppSiteLocation());
            if (file.exists() && StringUtils.equals(appVersion, appInstall.getInstallVersion())) {
                return appInstall.getAppSiteLocation();
            }
        }
        // 未安装，文件包不存在，服务器版本不一致，就去下载
        Properties properties = PropertieUtil.readPropertiesFile(AppSiteServiceImpl.class.getClassLoader().getResourceAsStream(AppConstant.APP_PROP_LOCATION));
        URL url;
        URLConnection conn;
        InputStream inStream = null;

        FileOutputStream fos = null;
        String bundleLocation = null;
        try {
            // app市场服务器地址
            String serverUrl = properties.getProperty(AppConstant.APP_SERVER);
            // 本地karaf路径
            String karafHome = properties.getProperty(AppConstant.LOCAL_KARAF_HOME);
            // 查询本地存储的密钥
            AppMarketKey marketKey = appMarketKeyService.select(1L);
            // 下载地址
            url = new URL(serverUrl + "downloadapp.htm?id=" + id + "&appType=3&appMarketKey=" + marketKey.getAppMarketKey());
            conn = url.openConnection();
            inStream = conn.getInputStream();
            // String fileName = getFileNameFromUrlConn(conn);
            LOGGER.info("fileName=====================" + appLocation);
            // 下载
            fos = new FileOutputStream(karafHome + "download/" + appLocation);
            byte[] buffer = new byte[1024 * 1024];
            int byteread;
            while ((byteread = inStream.read(buffer)) != -1) {
                fos.write(buffer, 0, byteread);
                fos.flush();
            }
            bundleLocation = karafHome + "download/" + appLocation;

            fos.close();
        } finally {
            try {
                if (null != inStream) {
                    inStream.close();
                }
                if (null != fos) {
                    fos.close();
                }
            } catch (IOException e) {
                LOGGER.error("应用下载失败", e);
            }
        }
        return bundleLocation;
    }

    @Override
    public Bundle installApp(Long id) {
        // 1、从本地查询安装记录
        AppInstall appInstall = appInstallService.selectByAppId(id);
        // 3、从指定位置安装bundle
        Bundle bundle = null;
        try {
            bundle = Activator.getBundleContext().installBundle("file:" + appInstall.getAppSiteLocation());
            // 4、将应用的状态改为3，表示已安装未启动
            appInstall.setInstallStatus("2");
            appInstall.setBundleId(bundle.getBundleId());
        } catch (BundleException e) {
            // 4、将应用的状态改为3，表示已下载未安装
            appInstall.setInstallStatus("1");
            LOGGER.error("应用安装失败", e);
        }
        // 6、更新应用的运行状态
        appInstallService.update(appInstall);
        return bundle;
    }

    /**
     * 开启应用
     *
     * @param id
     *            bundle的唯一标识
     * @param serverId
     *            服务器ID
     * @return bundle信息
     */
    @Override
    public Bundle startApp(Long id, Long serverId) {
        // 1、从本地查询安装记录
        AppInstall appInstall = appInstallService.selectByAppId(id);
        AppServerBundle appServerBundle = appServerBundleService.selectByAppIdAndServerId(id, serverId);
        Bundle bundle = null;
        try {
            if (appServerBundle != null && appServerBundle.getBundleId() != null) {
                // 3、从容器中查询bundle信息
                bundle = Activator.getBundleContext().getBundle(appServerBundle.getBundleId());
                // 4、启动
                bundle.start();
            }
            // 7、将应用的状态改为3，表示在启动状态
            appInstall.setInstallStatus("3");
            LOGGER.info("前台应用" + appInstall.getAppName() + "正在启动");
        } catch (BundleException e) {
            // 7、将应用的状态改为2，表示已安装未启动
            appInstall.setInstallStatus("2");
            LOGGER.error("前台应用启动失败", e);
        }
        // 8、更新bundle的运行状态
        appInstallService.update(appInstall);
        return bundle;
    }

    /**
     * 停止应用
     *
     * @param id
     *            bundle的唯一标识
     * @param serverId
     *            服务器ID
     * @return bundle信息
     */
    @Override
    public Bundle stopApp(Long id, Long serverId) {
        // 1、从本地查询安装记录
        AppInstall appInstall = appInstallService.selectByAppId(id);
        AppServerBundle appServerBundle = appServerBundleService.selectByAppIdAndServerId(id, serverId);
        Bundle bundle = null;
        try {

            if (appServerBundle != null && appServerBundle.getBundleId() != null) {
                // 3、从容器中查询bundle信息
                bundle = Activator.getBundleContext().getBundle(appServerBundle.getBundleId());
                // 4、停止bundle
                bundle.stop();
            }
            // 7、将应用的状态改为2，表示停止
            appInstall.setInstallStatus("2");
            LOGGER.info("前台应用" + appInstall.getAppName() + "正在停止");
        } catch (BundleException e) {
            // 7、将应用的状态改为3，表示还在启动状态
            appInstall.setInstallStatus("3");
            LOGGER.error("前台应用停止失败", e);
        }
        // 8、更新bundle的运行状态
        appInstallService.update(appInstall);
        return bundle;
    }

    /**
     * 卸载应用
     *
     * @param id
     *            bundle的唯一标识
     * @param serverId
     *            服务器ID
     * @return bundle信息
     */
    @Override
    public Bundle uninstallapp(Long id, Long serverId) {
        // 1、从本地查询安装记录
        AppInstall appInstall = appInstallService.selectByAppId(id);
        AppServerBundle appServerBundle = appServerBundleService.selectByAppIdAndServerId(id, serverId);
        Bundle bundle = null;
        try {

            if (appServerBundle != null && appServerBundle.getBundleId() != null) {
                // 3、从容器中查询bundle信息
                bundle = Activator.getBundleContext().getBundle(appServerBundle.getBundleId());
                // 4、卸载
                bundle.uninstall();
                appServerBundleService.delete(appServerBundle.getServerBundleId());
            }

            // 6、将应用安装记录删除
            appInstall.setInstallStatus("4");
            appInstallService.update(appInstall);
            LOGGER.info("应用" + appInstall.getAppName() + "正在卸载");
        } catch (BundleException e) {
            LOGGER.error("应用卸载失败", e);
        }
        return bundle;
    }

    /**
     * 更新应用
     *
     * @param id       bundle的唯一标识
     * @param serverId 服务器id
     * @return bundle信息
     */
    @Override
    public Bundle updateApp(Long id, Long serverId) throws BundleException, IOException {
        String bundleLocation = null;
        // 1、从服务器端查询应用信息
        App app = (App) appClientService.queryAppDetail(id);
        Bundle bundle = null;
        if (StringUtils.isNotEmpty(app.getAppLocation())) {
            // 3、从服务器端下载应用包
            bundleLocation = downloadAppFromServer(id, app.getAppVersion(), app.getAppLocation());

            // 4、下载应用后，将应用包安装到容器
            if (bundleLocation != null) {
                LOGGER.info("==========Site应用下载完成，准备更新==============");
                if(bundle!=null) {
//                    bundle = Activator.getBundleContext().installBundle("file:" + bundleLocation);
                    bundle.update(new FileInputStream(new File(bundleLocation)));
                }
                LOGGER.info("==========Site应用更新完成==============");
            }
        }
        return bundle;
    }

}