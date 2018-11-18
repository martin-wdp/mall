/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.controller;

import com.ningpai.fastdfs.service.FastDFSInfoService;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.system.service.SysBasicService;
import com.ningpai.uploadfileset.service.UploadFileSetService;
import com.ningpai.util.MenuSession;
import com.ningpai.util.PageRowsUtil;
import com.ningpai.util.PropertieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

/**
 * 控制器-系统管理-高级管理
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年7月25日下午5:34:53
 */
@Controller
public class AdvancedSet {
    @Resource(name = "FastDFSInfoService")
    private FastDFSInfoService fastDFSService;

    @Resource(name = "UploadFileSetService")
    private UploadFileSetService uploadFileSetService;

    @Resource(name = "SysBasicService")
    private SysBasicService sysBasicService;

    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    /**
     * 跳转到高级设置页面
     * 
     * @return
     */
    @RequestMapping("/toAdvancedSet")
    public ModelAndView toAdvancedSet(HttpServletRequest request) {
        // 设置导航
        MenuSession.sessionMenu(request);
        //初始化mav
        ModelAndView mav = new ModelAndView("jsp/system/advanced_set");
        //页行数
        mav.addObject("pageRows", PageRowsUtil.getPageRows());
        //验证码
        mav.addObject("showpatcha", basicSetService.findBasicSet().getLoginPatcha());
        //fastDFS信息
        mav.addObject("fastDFSInfo", fastDFSService.getFastDFSInfoByCurr());
        //下载文件设置
        mav.addObject("ufs", uploadFileSetService.getCurrUploadFileSet());
        //后台设置
        mav.addObject("sysBasic", sysBasicService.getSysBasic());
        //属性文件
        Properties properties = PropertieUtil
                .readPropertiesFile(this
                        .getClass()
                        .getClassLoader()
                        .getResourceAsStream(
                                "com/ningpai/web/config/upload.properties"));
        //分割
        String[] extendNames = properties.get("FILE_EXTEND_NAME").toString()
                .split(",");
        mav.addObject("extendNames", extendNames);
        return mav;
    }
}
