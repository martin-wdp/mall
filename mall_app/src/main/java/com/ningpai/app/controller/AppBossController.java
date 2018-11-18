package com.ningpai.app.controller;

import com.ningpai.app.bean.AppMarketKey;
import com.ningpai.app.service.AppMarketKeyService;
import com.ningpai.app.service.AppService;
import org.apache.log4j.Logger;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 属客户端
 * 客户端应用管理器
 * @author NP-Heh
 * 2015年6月30日 下午4:07:34
 */
@Controller
public class AppBossController {

    /** appClientService */
    @Resource(name="AppBossService")
    private AppService appService;

    /**
     * AppMarketKeyService,秘钥信息
     */
    @Resource(name="AppMarketKeyService")
    private AppMarketKeyService appMarketKeyService;

    /** 记录日志对象 */
    private static final Logger LOGGER = Logger.getLogger(AppBossController.class);

    /**
     * 下载并安装app
     * @param id 主键id
     * @return 安装结果
     * -1应用安装失败
     * -2应用下载失败
     * 1下载成功且安装成功
     */
    @RequestMapping("downloadbossapp")
    @ResponseBody
    public int downloadBossApp(Long id,Long serverId) {
        //安装应用
        try {
        //查询本地存储的密钥状态
            int appMarketKeyState = queryAppMarketKeyState();
            if(appMarketKeyState<0) {
                return appMarketKeyState;
            } else {
                appService.downloadAndInstallApp(id, serverId);
            }
        } catch (BundleException e) {
            //已下载未安装
            LOGGER.error("Boss应用安装失败",e);
            return -3;
        } catch (IOException e) {
            //未下载
            LOGGER.error("Boss应用下载失败",e);
            return -4;
        }
        return 1;
    }

    /**
     * 启动应用
     * @param id 应用id
     * @return bundle的状态
     *
     * int UNINSTALLED = 1;
     * int INSTALLED = 2;
     * int RESOLVED = 4;
     * int STARTING = 8;
     * int STOPPING = 16;
     * int ACTIVE = 32;
     */
    @RequestMapping("startbossapp")
    @ResponseBody
    public int startBossApp(Long id,Long serverId) {
        //通过App安装记录，获取App在karaf中的bundleid，通过bundleId获取Bundle，进而操作
        Bundle bundle = appService.startApp(id, serverId);
        return bundle==null?-1:bundle.getState();
    }

    /**
     * 停止应用
     * @param id 应用Id
     * @return bundle的状态
     *
     * int UNINSTALLED = 1;
     * int INSTALLED = 2;
     * int RESOLVED = 4;
     * int STARTING = 8;
     * int STOPPING = 16;
     * int ACTIVE = 32;
     */
    @RequestMapping("stopbossapp")
    @ResponseBody
    public int stopBossApp(Long id,Long serverId,HttpServletRequest request) {
        //通过App安装记录，获取App在karaf中的bundleid，通过bundleId获取Bundle，进而操作
        Bundle bundle = appService.stopApp(id, serverId);
        return bundle==null?-1:bundle.getState();
    }


    /**
     * 卸载应用
     * @param id 应用Id
     * @return bundle的状态
     *
     * int UNINSTALLED = 1;
     * int INSTALLED = 2;
     * int RESOLVED = 4;
     * int STARTING = 8;
     * int STOPPING = 16;
     * int ACTIVE = 32;
     */
    @RequestMapping("uninstallbossapp")
    @ResponseBody
    public int uninstallBossApp(Long id,Long serverId) {
        LOGGER.info("===============uninstallapp==================");
        //通过App安装记录，获取App在karaf中的bundleid，通过bundleId获取Bundle，进而操作
        Bundle bundle = appService.uninstallapp(id, serverId);
        return bundle==null?-1:bundle.getState();
    }

    /**
     * 卸载应用
     * @param id 应用Id
     * @return bundle的状态
     *
     * int UNINSTALLED = 1;
     * int INSTALLED = 2;
     * int RESOLVED = 4;
     * int STARTING = 8;
     * int STOPPING = 16;
     * int ACTIVE = 32;
     */
    @RequestMapping("updatebossapp")
    @ResponseBody
    public int updateBossApp(Long id,Long serverId) {
        LOGGER.info("===============unpdatebossapp==================");
        //通过App安装记录，获取App在karaf中的bundleid，通过bundleId获取Bundle，进而操作
        Bundle bundle = null;
        try {
            bundle = appService.updateApp(id, serverId);
        } catch (BundleException e) {
            LOGGER.error("更新后台失败", e);
        } catch (IOException e) {
            LOGGER.error("更新后台失败",e);
        }
        return bundle==null?-1:bundle.getState();
    }

    /**
     * 查询秘钥状态
     * @return  不存在，返回-1；秘钥正常，返回1；
     */
    public int queryAppMarketKeyState() {
        //查询本地存储的密钥
        AppMarketKey marketKey = appMarketKeyService.select(1L);
        //判断秘钥是否存在，不存在，返回-1；
        if(marketKey==null) {
            return -1;
        }
        //判断秘钥是否过期,过期，返回-2；
        if(marketKey.getEndTime().getTime()<System.currentTimeMillis()) {
            return -2;
        }
        //秘钥正常，返回1；
        return 1;
    }
}
