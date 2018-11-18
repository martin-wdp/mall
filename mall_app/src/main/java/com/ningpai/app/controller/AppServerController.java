package com.ningpai.app.controller;

import com.ningpai.app.bean.AppServer;
import com.ningpai.app.service.AppServerService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

/**
 * AppServerController
 * 这里的服务器指的是：网站的根地址，根目录
 *客户端服务器控制器，
 * 注：是客户端运行APP的有哪些服务器。不是市场服务器
 * @author hehu
 * @date 2015/8/6
 */
@Controller
public class AppServerController {

    /**
     * 应用所运行的服务器Service
     */
    @Resource(name="AppServerService")
    private AppServerService appServerService;


    /**
     * 应用所运行的服务器列表
     * @param pageBean 分页参数
     * @param appServer 查询参数
     * @return 应用所运行的服务器列表
     */
    @RequestMapping("appServerList")
    public ModelAndView appServerList(PageBean pageBean,AppServer appServer) {
        return new ModelAndView("jsp/app/server_list").addObject("pageBean",appServerService.selectList(appServer,pageBean));
    }

    /**
     *
     * @param appServerId 应用所运行的服务器Id
     * @return 应用所运行的服务器信息
     */
    @RequestMapping("selectAppServerById")
    @ResponseBody
    public AppServer selectAppServerById(Long appServerId) {
        return appServerService.select(appServerId);
    }

    /**
     * 根据id删除App运行的服务器
     * @param appServerId app运行服务器id
     * @return 删除结果
     */
    @RequestMapping("deleteAppServer")
    @ResponseBody
    public Integer deleteAppServer(Long appServerId) {
        return appServerService.delete(appServerId);
    }

    /**
     * 添加App运行的服务器
     * @param appServer App运行的服务器参数信息
     * @return 添加结果
     */
    @RequestMapping("addAppServer")
    public ModelAndView addAppServer(AppServer appServer) {
        appServerService.insert(appServer);
        return new ModelAndView(new RedirectView("appServerList.htm"));
    }

    /**
     * 修改App运行的服务器
     * @param appServer 服务器参数信息
     * @return 修改结果
     */
    @RequestMapping("updateAppServer")
    public ModelAndView updateAppServer(AppServer appServer) {
        appServerService.update(appServer);
        return new ModelAndView(new RedirectView("appServerList.htm"));
    }


}
