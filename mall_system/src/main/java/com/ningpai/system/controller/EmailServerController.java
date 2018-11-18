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

import com.ningpai.util.MenuSession;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.EmailServer;
import com.ningpai.system.service.EmailServerService;
import com.ningpai.util.MyLogger;

/**
 * 邮箱服务器控制层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月14日 上午11:38:24
 * @version 1.0
 */
@Controller
public class EmailServerController {
    /** 邮箱服务service */
    @Resource(name = "emailServerService")
    private EmailServerService emailServerService;

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    /**
     * 日志
     */
    private static final MyLogger LOGGER = new MyLogger(
            EmailServerController.class);

    /**
     * 查询邮箱服务器信息
     * 
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping("/emailset")
    public ModelAndView emailSet(HttpServletRequest request,
            HttpServletResponse response) {
        // 设置导航
        MenuSession.sessionMenu(request);
        String flag = request.getParameter("flag");
        if (flag == null) {
            flag = "0";
        }
        return new ModelAndView("jsp/system/emailserver", "server",
                emailServerService.findServer()).addObject("flag", flag);
    }

    /**
     * 修改邮箱服务器信息
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updatemail")
    public ModelAndView updateMail(HttpServletRequest request,
            HttpServletResponse response, @Valid EmailServer emailServer,
            BindingResult bindingResult) {
        // 是否有错
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView("emailset.htm"));
        }
        PrintWriter pr = null;

        try {
            pr = response.getWriter();
            // 修改邮箱信息，并返回值
            pr.print(emailServerService.updateServer(emailServer));
            // 获取session中的用户名
            String customerName = (String) request.getSession().getAttribute(
                    NAME);
            // 添加操作日志
            OperaLogUtil.addOperaLog(request, customerName, "修改邮箱服务设置", request
                    .getSession().getAttribute(OPERAPATH)
                    + ",用户名:"
                    + customerName);
        } catch (IOException e) {
            // 错误日志输出
            LOGGER.error("修改邮箱服务设置错误：=>",e);
            return new ModelAndView(new RedirectView("emailset.htm"));
        } finally {
            if (pr != null) {
                pr.close();
            }
        }

        return null;
    }
}
