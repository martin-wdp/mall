package com.ningpai.app.controller;

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
public class AppSiteController {

    /** appClientService */
    @Resource(name="AppSiteService")
    private AppService appService;

    /** 记录日志对象 */
    private static final Logger LOGGER = Logger.getLogger(AppSiteController.class);

    /**
     * 下载并安装app
     * @param id 主键id
     * @return 安装结果
     * -1应用安装失败
     * -2应用下载失败
     * 1下载成功且安装成功
     */
    @RequestMapping("downloadsiteapp")
    @ResponseBody
    public int downloadSiteApp(Long id,Long serverId) {
        //安装应用
        try {
        //查询本地存储的密钥状态
            appService.downloadAndInstallApp(id, serverId);
        } catch (BundleException e) {
            //已下载未安装
            LOGGER.error("前台应用安装失败",e);
            return -3;
        } catch (IOException e) {
            //未下载
            LOGGER.error("Site应用下载失败",e);
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
    @RequestMapping("startsiteapp")
    @ResponseBody
    public int startSiteApp(Long id,Long serverId) {
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
    @RequestMapping("stopsiteapp")
    @ResponseBody
    public int stopSiteApp(Long id,Long serverId,HttpServletRequest request) {
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
    @RequestMapping("uninstallsiteapp")
    @ResponseBody
    public int uninstallSiteApp(Long id,Long serverId) {
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
    @RequestMapping("updatesiteapp")
    @ResponseBody
    public int updateSiteApp(Long id,Long serverId) {
        LOGGER.info("===============updatesiteapp==================");
        //通过App安装记录，获取App在karaf中的bundleid，通过bundleId获取Bundle，进而操作
        Bundle bundle = null;
        try {
            bundle = appService.updateApp(id, serverId);
        } catch (BundleException e) {
            LOGGER.error("更新前台应用失败", e);
        } catch (IOException e) {
            LOGGER.error("更新前台应用失败", e);
        }
        return bundle==null?-1:bundle.getState();
    }
}
