/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.HelpCenter;
import com.ningpai.system.service.HelpCateService;
import com.ningpai.system.service.HelpCenterService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.MenuSession;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 帮助中心控制层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月20日 下午2:40:04
 * @version 1.0
 */
@Controller
public class HelpCenterController {

    /** 日志logger */
    private static final MyLogger LOGGER = new MyLogger(HelpCenterController.class);

    private static final String REDIRECT = "findcenter.htm";

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    /** 帮助中心服务 */
    @Resource(name = "helpCenterService")
    private HelpCenterService helpCenterService;

    /** 帮助分类服务 */
    @Resource(name = "helpCateService")
    private HelpCateService helpCateService;

    /**
     * 查询帮助列表
     */
    @RequestMapping(value = "/findcenter")
    public ModelAndView findPageBean(HttpServletRequest request, HttpServletResponse response, PageBean pb, SelectBean selectBean) {
        MenuSession.sessionMenu(request);
        // 判断查询文本是否为空 若为空 将条件也设置为空
        if ("".equals(selectBean.getSearchText())) {
            selectBean.setCondition("");
        }

        // 参数设置到作用域中
        request.setAttribute("selectBean", selectBean);

        return new ModelAndView("jsp/system/helplist", "pb", helpCenterService.findByPageBean(pb, selectBean)).addObject("helpcate", helpCateService.findAll());
    }

    /**
     * 跳转到添加帮助中心
     * 
     * @return ModelAndView
     */
    @RequestMapping("/tohelpcenter")
    public ModelAndView toHelpCenter(HttpServletRequest request, HttpServletResponse response, Long helpId) {

        // 如果cateid不为空，保存分类id,并跳转到添加帮助中心页面(修改页面)
        if (helpId != null) {
            request.setAttribute("help", helpCenterService.findByHelpId(helpId));
        }

        return new ModelAndView("jsp/system/addhelp", "helpcate", helpCateService.findAll());
    }

    /**
     * 修改查询
     * 
     * @return ModelAndView
     */
    @RequestMapping("/tohelpcenterajax")
    @ResponseBody
    public HelpCenter tohelpcenterajax(Long helpId) {
        return helpCenterService.findByHelpId(helpId);
    }

    /**
     * 添加帮助中心
     * 
     * @return ModelAndView
     */
    @RequestMapping("/addhelp")
    public ModelAndView addHelpCenter(MultipartHttpServletRequest mrequest, HttpServletResponse response, @Valid HelpCenter helpCenter, BindingResult bindingResult) {
        MultipartFile muFile = mrequest.getFile("helpPic");
        // 文件非空验证
        if (muFile != null) {
            helpCenter.setHelpImg(UploadUtil.uploadFileOne(muFile, mrequest));
        }
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(REDIRECT));
        }
        // 添加帮助信息
        helpCenterService.insertHelpCenter(helpCenter);
        // 登录名称
        String customerName = (String) mrequest.getSession().getAttribute(NAME);
        // 日志输出
        OperaLogUtil.addOperaLog(mrequest, customerName, "添加帮助信息", mrequest.getSession().getAttribute(OPERAPATH) + ",用户名:" + customerName);

        return new ModelAndView(new RedirectView(REDIRECT));
    }

    /**
     * 修改帮助中心
     * 
     * @return ModelAndView
     */
    @RequestMapping("/updatehelp")
    public ModelAndView updateHelpCenter(MultipartHttpServletRequest request, HttpServletResponse response, @Valid HelpCenter helpCenter, BindingResult bindingResult) {
        MultipartFile muFile = request.getFile("helpPic");
        helpCenter.setHelpImg(UploadUtil.uploadFileOne(muFile, request));
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(REDIRECT));
        }
        // 修改帮助中心
        helpCenterService.updateHelpCenter(helpCenter);
        // 登录名称
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 日志输出
        OperaLogUtil.addOperaLog(request, customerName, "修改帮助信息", (String) request.getSession().getAttribute(OPERAPATH));

        return new ModelAndView(new RedirectView(REDIRECT));
    }

    /**
     * 删除帮助中心
     * 
     * @return ModelAndView
     */
    @RequestMapping("/delhelp")
    public ModelAndView updateHelpCenter(HttpServletRequest request, HttpServletResponse response) {

        PrintWriter pr = null;

        try {
            pr = response.getWriter();
            pr.print(helpCenterService.deleteHelpCenter(request.getParameterValues("helpIds[]")));
            // 登录名称
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 日志输出
            OperaLogUtil.addOperaLog(request, customerName, "删除帮助信息", request.getSession().getAttribute(OPERAPATH) + ",用户名:" + customerName);
        } catch (IOException e) {
            // 错误日志输出
            LOGGER.error("删除帮助信息错误：=>" ,e);
        } finally {
            if (pr != null) {
                pr.close();
            }
        }

        return new ModelAndView(new RedirectView(REDIRECT));
    }
}
