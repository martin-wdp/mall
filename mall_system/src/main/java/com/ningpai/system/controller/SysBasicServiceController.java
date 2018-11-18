/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.system.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.system.bean.SysBasic;
import com.ningpai.system.service.SysBasicService;

/**
 * 控制器-后台Logo设置
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月25日上午9:09:31
 */
@Controller
public class SysBasicServiceController {

    @Resource(name = "SysBasicService")
    private SysBasicService sysBasicService;

    /**
     * 查看后台logo
     * 
     * @return
     */
    @RequestMapping("/showSysBasic")
    public ModelAndView showSysBasic() {
        return new ModelAndView("jsp/show_sysbasic", "sysBasic", sysBasicService.getSysBasic());
    }

    /**
     * 修改后台logo
     * 
     * @return
     */
    @RequestMapping("/updateSysBasic")
    public ModelAndView updateSysBasic(SysBasic sysBasic) {
        sysBasicService.updateSysBasic(sysBasic);
        return new ModelAndView(new RedirectView("showSysBasic.htm"));
    }

    /**
     * Ajax获取后台logo
     * 
     * @return
     */
    @RequestMapping("/ajaxGetSysBasic")
    @ResponseBody
    public SysBasic ajaxGetSysBasic(HttpServletRequest request) {
        SysBasic sb = sysBasicService.getSysBasic();
        request.getSession().setAttribute("indexLogo", sb.getIndexLogo());
        return sb;
    }

    /**
     * Ajax修改后台logo
     * 
     * @return
     */
    @RequestMapping("/ajaxUpdateSysBasic")
    @ResponseBody
    public SysBasic ajaxUpdateSysBasic(SysBasic sysBasic) {
        sysBasicService.updateSysBasic(sysBasic);
        return sysBasic;
    }
}
