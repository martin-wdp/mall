/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.version.controller;

import javax.annotation.Resource;

import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.util.PageBean;
import com.ningpai.version.bean.Version;
import com.ningpai.version.service.VersionService;

/**
 * 版本Controller
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年6月26日 上午10:26:21
 * @version 0.0.1
 */
@Controller
public class VersionController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(VersionController.class);

    private static final String REDIRECT = "allversion.htm";

    private VersionService versionService;

    /**
     * 初始化版本信息
     * 
     * @return
     */
    @RequestMapping("/initversion")
    public ModelAndView initVersion() {

        return new ModelAndView("jsp/log", "verList", versionService.seleceVersion());
    }

    /**
     * 分页 版本信息
     * 
     * @return
     */
    @RequestMapping("/allversion")
    public ModelAndView allVersion(PageBean pageBean, Version version) {
        pageBean.setUrl(REDIRECT);
        return new ModelAndView("jsp/core/manager/versionlist", "pageBean", versionService.selectAllVersion(pageBean, version));
    }

    /**
     * 增加版本信息
     * 
     * @param version
     * @return
     */
    @RequestMapping("/addversion")
    public ModelAndView addVersion(Version version, String date) {
        //非空验证版本号
        if(null != version.getVersionCode()){
            LOGGER.info("新增版本信息，新增的版本号为:"+version.getVersionCode());
        }
        versionService.addVersion(version, date);
        return new ModelAndView(new RedirectView(REDIRECT));
    }

    /**
     * 修改版本信息
     * 
     * @param version
     * @return
     */
    @RequestMapping("/updateversion")
    public ModelAndView updateVersion(Version version, String date) {
        //非空验证版本号
        if(null != version.getVersionCode()){
            LOGGER.info("修改版本信息，修改的版本号为:"+version.getVersionCode());
        }
        versionService.updateVersion(version, date);
        return new ModelAndView(new RedirectView(REDIRECT));
    }

    /**
     * 查询版本信息
     * 
     * @param versionId
     * @return
     */
    @RequestMapping("/showversion")
    @ResponseBody
    public Version showVersion(Long versionId) {
        //非空验证 版本ID
        if(null != versionId){
            Version version = versionService.showVersion(versionId);
            LOGGER.info("获取版本号为："+version.getVersionCode()+"的版本信息");
        }
        return versionService.showVersion(versionId);
    }

    /**
     * 查询最新版本信息
     *
     * @return
     */
    @RequestMapping("/shownewversion")
    @ResponseBody
    public Version showNewVersion() {
        Version version = versionService.showNewVersion();

        if(null != version.getVersionCode()){
            LOGGER.info("查询最新版本信息,版本号为："+version.getVersionCode());
        }
        return version;
    }

    public VersionService getVersionService() {
        return versionService;
    }

    @Resource(name = "versionService")
    public void setVersionService(VersionService versionService) {
        this.versionService = versionService;
    }

}
