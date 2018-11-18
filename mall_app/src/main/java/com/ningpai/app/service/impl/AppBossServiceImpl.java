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
import com.ningpai.app.util.DBUtil;
import com.ningpai.manager.bean.Authority;
import com.ningpai.manager.bean.valuebean.MenuVo;
import com.ningpai.manager.service.impl.AuthorityService;
import com.ningpai.manager.service.impl.PageService;
import com.ningpai.osgi.activator.Activator;
import com.ningpai.util.PropertieUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @Description: np_app的service的实现类:
 * @author Ningpai-HEHU
 * @date 2015-06-10 18:21:40
 * @version V1.0
 */
@Service("AppBossService")
public class AppBossServiceImpl implements AppService {

    /** 记录日志对象 */
    private static final Logger LOGGER = Logger.getLogger(AppBossServiceImpl.class);

    /**
     * file
     */
    private static final String FILES = "file:";
    /** App安装记录接口 */
    @Resource(name = "AppInstallService")
    private AppInstallService appInstallService;

    /** 菜单接口 */
    @Resource(name = "PageService")
    private PageService pageService;

    /** 角色、权限接口 */
    @Resource(name = "authorityService")
    private AuthorityService authorityService;

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
        LOGGER.info("==========正在下载安装Boss应用==============");
        // 1、从服务器端查询应用信息
        App app = (App) appClientService.queryAppDetail(id);
        // 2、安装完成后，将安装记录保存
        saveAppInstall(app);
        String bundleLocation = null;
        Bundle bundle = null;
        if (StringUtils.isNotEmpty(app.getAppLocation())) {
            // 3、从服务器端下载应用包
            bundleLocation = downloadAppFromServer(id, app.getAppVersion(), app.getAppLocation());

            // 4、下载应用后，将应用包安装到容器
            if (bundleLocation != null) {
                LOGGER.info("==========Boss应用下载完成，准备安装==============");
                bundle = Activator.getBundleContext().installBundle(FILES + bundleLocation);
                LOGGER.info("==========Boss应用安装完成==============");
            }
        }
        //5、保存server和bundle之间的关系
        saveServerBundle(app,serverId,bundle);
        // 6.执行sql
        excuteSql(app.getLoadSql());
        return bundle;
    }

    /**
     * 保存应用安装记录
     * 
     * @param app
     *            应用信息
     */
    public void saveAppInstall(App app) {
        // 安装记录，客户自己安装应用的记录
        AppInstall appInstall = appInstallService.selectByAppId(app.getId());
        // 如果未安装过，
        if (appInstall == null) {
            appInstall = new AppInstall();
        }
        Properties properties = PropertieUtil.readPropertiesFile(AppBossServiceImpl.class.getClassLoader().getResourceAsStream("com/ningpai/web/config/app.properties"));
        // 本地karaf路径
        String karafHome = properties.getProperty(AppConstant.LOCAL_KARAF_HOME);
        //设置安装参数，供下面修改或者保存
        appInstall.setAppId(app.getId());
        appInstall.setAppName(app.getAppName());
        appInstall.setAppIntroduction(app.getAppIntroduction());
        appInstall.setInstallTime(new Date());
        appInstall.setAppKey(app.getAppId());
        appInstall.setInstallVersion(app.getAppVersion());
        appInstall.setBundleName(app.getBundleName());
        appInstall.setAppLogo(app.getAppLogo());
        appInstall.setLoadSql(app.getLoadSql());
        appInstall.setStartSql(app.getStartSql());
        appInstall.setStopSql(app.getStopSql());
        appInstall.setUnloadSql(app.getUnloadSql());
        appInstall.setSubTitle(app.getSubTitle());
        if (StringUtils.isNotEmpty(app.getAppLocation())) {
            appInstall.setBundleLocation(karafHome + AppConstant.APP_DOWNLOAD_PATH + app.getAppLocation());
        }
        if (StringUtils.isNotEmpty(app.getAppSiteLocation())) {
            appInstall.setAppSiteLocation(karafHome + AppConstant.APP_DOWNLOAD_PATH + app.getAppSiteLocation());
        }
        if (StringUtils.isNotEmpty(app.getAppThirdLocation())) {
            appInstall.setAppThirdLocation(karafHome + AppConstant.APP_DOWNLOAD_PATH + app.getAppThirdLocation());
        }
        //如果安装过，就更新
        if(appInstall.getInstallId()!=null) {
            appInstall.setInstallStatus(appInstall.getInstallStatus());
            appInstallService.update(appInstall);
        } else {
            //如果未安装过，就保存
            appInstall.setInstallStatus("2");
            appInstallService.insert(appInstall);
        }
    }

    /**
     * 保存Server和Bundle之间的关系
     * @param app           应用信息
     * @param serverId     服务器id
     * @param bundle       bundle信息
     */
    public void saveServerBundle(App app,Long serverId,Bundle bundle) {
        //如果应用包含Boss文件包
        if (StringUtils.isNotEmpty(app.getAppLocation())) {
            //查询文件包是否已安装到这个服务器上
            AppServerBundle appServerBundle = appServerBundleService.selectByAppIdAndServerId(app.getId(), serverId);
            //如果没安装，就把关系保存下来
            if(appServerBundle==null) {
                appServerBundle = new AppServerBundle();
                appServerBundle.setAppId(app.getId());
                appServerBundle.setBundleId(bundle == null ? null : bundle.getBundleId());
                appServerBundle.setServerId(serverId);
                appServerBundleService.insert(appServerBundle);
            } else {
                appServerBundle.setAppId(app.getId());
                appServerBundle.setBundleId(bundle == null ? null : bundle.getBundleId());
                appServerBundle.setServerId(serverId);
                appServerBundleService.update(appServerBundle);
            }
        }
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
        // 判断是否已安装过，且应用存在前台文件包
        if (appInstall != null && StringUtils.isEmpty(appInstall.getBundleLocation())) {
            return null;
        }
        // 判断是否已安装过，且版本与服务器版本一致
        if (appInstall != null && StringUtils.isNotEmpty(appInstall.getBundleLocation())) {
            File file = new File(appInstall.getBundleLocation());
            if (file.exists() && StringUtils.equals(appVersion, appInstall.getInstallVersion())) {
                return appInstall.getBundleLocation();
            }
        }
        Properties properties = PropertieUtil.readPropertiesFile(AppBossServiceImpl.class.getClassLoader().getResourceAsStream("com/ningpai/web/config/app.properties"));
        URL url = null;
        URLConnection conn = null;
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
            url = new URL(serverUrl + "downloadapp.htm?id=" + id + "&appType=1&appMarketKey=" + marketKey.getAppMarketKey());
            conn = url.openConnection();
            inStream = conn.getInputStream();
            // String fileName = getFileNameFromUrlConn(conn);
            LOGGER.info("fileName=====================" + appLocation);
            // 下载
            fos = new FileOutputStream(karafHome + AppConstant.APP_DOWNLOAD_PATH + appLocation);
            byte[] buffer = new byte[1024 * 1024];
            int byteread = 0;
            while ((byteread = inStream.read(buffer)) != -1) {
                fos.write(buffer, 0, byteread);
                fos.flush();
            }
            bundleLocation = karafHome + AppConstant.APP_DOWNLOAD_PATH + appLocation;
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
            bundle = Activator.getBundleContext().installBundle(FILES + appInstall.getBundleLocation());
            // 4、将应用的状态改为3，表示已安装未启动
            appInstall.setInstallStatus("2");
            appInstall.setBundleId(bundle.getBundleId());
        } catch (BundleException e) {
            // 4、将应用的状态改为3，表示已下载未安装
            appInstall.setInstallStatus("1");
            LOGGER.error("应用安装失败", e);
        }
        // 5.执行sql
        excuteSql(appInstall.getLoadSql());
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
                LOGGER.info("==========Boss应用准备启动==============");
                // 3、从容器中查询bundle信息
                bundle = Activator.getBundleContext().getBundle(appServerBundle.getBundleId());
                // 4、启动
                if(bundle!=null) {
                    bundle.start();
                }
                LOGGER.info("==========Boss应用启动完成==============");
            }

            // 5、先删除模块包含的菜单和权限，防止id冲突
            uninstallMenuAndAuth(appInstall);

            // 6、初始化菜单和权限
            initMenuAndAuth(appInstall);

            // 7、将应用的状态改为3，表示在启动状态
            appInstall.setInstallStatus("3");
            LOGGER.info("应用" + appInstall.getAppName() + "正在启动");
        } catch (BundleException e) {
            // 7、将应用的状态改为2，表示已安装未启动
            appInstall.setInstallStatus("2");
            LOGGER.error("应用启动失败", e);
        }
        // 8、更新bundle的运行状态
        appInstallService.update(appInstall);
        return bundle;
    }

    /**
     * 根据app启动时的sql初始化菜单和权限
     * 
     * @param app
     *            app信息
     */
    @Transactional
    private void initMenuAndAuth(AppInstall app) {
        // 1、启用时执行菜单插入sql
        excuteSql(app.getStartSql());
        // 2、查询模块所有菜单
        List<MenuVo> menuVos = pageService.queryMenuByBundleName(app.getBundleName());
        //如果有菜单，就分配权限
        if(menuVos!=null && menuVos.size()>0) {
            // 3、查询管理员角色
            Authority authority = authorityService.querySupperAuthor();
            // 4、批量添加角色-权限对应关系，即赋权
            LOGGER.info("--------------------------------------------添加权限-----------------------------------------------------------");
            authorityService.addAuthorityPageBatch(authority.getId(), menuVos);
        }
    }

    /**
     * 根据app启动时的sql删除菜单和权限
     * 
     * @param appInstall
     *            app信息
     */
    @Transactional
    private void uninstallMenuAndAuth(AppInstall appInstall) {
        // 1、删除权限
        authorityService.deleteAuthByBundleName(appInstall.getBundleName());
        // 2、删除模块中包含的菜单
        pageService.deleteMenuByBundleName(appInstall.getBundleName());
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
                LOGGER.info("==========Boss应用准备停止==============");
                // 3、从容器中查询bundle信息
                bundle = Activator.getBundleContext().getBundle(appServerBundle.getBundleId());
                // 4、停止bundle
                if(bundle!=null) {
                    bundle.stop();
                }
            }
            // 5、停用时执行sql
            excuteSql(appInstall.getStopSql());
            // 6、先删除模块包含的菜单和权限，防止id冲突
            uninstallMenuAndAuth(appInstall);
            // 7、将应用的状态改为2，表示停止
            appInstall.setInstallStatus("2");
            LOGGER.info("应用" + appInstall.getAppName() + "正在停止");
        } catch (BundleException e) {
            // 7、将应用的状态改为3，表示还在启动状态
            appInstall.setInstallStatus("3");
            LOGGER.error("应用停止失败", e);
        }
        // 8、更新bundle的运行状态
        appInstallService.update(appInstall);
        return bundle;
    }

    private void excuteSql(String sql) {
        if (sql != null && !"".equals(sql)) {
            if (sql.indexOf(";") > 0) {
                String[] sqls = sql.split(";");
                DBUtil.excuteBatchUpdateSql(sqls);
            } else {
                DBUtil.excuteUpdateSql(sql);
            }
        }
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
                if(bundle!=null) {
                    bundle.uninstall();
                }
                appServerBundleService.delete(appServerBundle.getServerBundleId());
            }

            // 5、初始化菜单和权限
            uninstallMenuAndAuth(appInstall);
            //6、执行卸载的sql
            excuteSql(appInstall.getUnloadSql());
            // 7、将应用安装记录删除
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
     * @return         bundle信息
     */
    @Override
    public Bundle updateApp(Long id, Long serverId) throws BundleException, IOException {
        LOGGER.info("==========正在更新Boss应用==============");
        // 1、从服务器端查询应用信息
        App app = (App) appClientService.queryAppDetail(id);
        // 2、安装完成后，将安装记录保存
        saveAppInstall(app);
        String bundleLocation = null;
        Bundle bundle = null;
        if (StringUtils.isNotEmpty(app.getAppLocation())) {
            // 3、从服务器端下载应用包
            bundleLocation = downloadAppFromServer(id, app.getAppVersion(), app.getAppLocation());

            // 4、下载应用后，将应用包安装到容器
            if (bundleLocation != null) {
                LOGGER.info("==========Boss应用下载完成，准备更新==============");
                if(bundle!=null) {
                    bundle.update(new FileInputStream(new File(bundleLocation)));
                }
                LOGGER.info("==========Boss应用更新完成==============");
            }
        }
        // 5.执行sql
        excuteSql(app.getUpdateSql());
        return bundle;
    }

}