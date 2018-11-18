/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.estore.controller;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.estore.bean.EStore;
import com.ningpai.system.estore.service.impl.EStoreServiceImpl;
import com.ningpai.util.MenuSession;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * E店宝控制器
 * 
 * @author jiping
 * @since 2014年12月2日 上午11:13:09
 * @version 0.0.1
 */
@Controller
public class EStoreController {
    private EStoreServiceImpl estoreService;

    /** 用户名称 */
    public static final String NAME = "name";

    /** "operaPath" */
    public static final String OPERAPATH = "operaPath";

    private static final MyLogger LOGGER = new MyLogger(EStoreController.class);

    /**
     * 显示E店宝配置信息
     */
    @RequestMapping("queryestore")
    public ModelAndView queryEStore(HttpServletRequest request) {
        // 设置导航
        MenuSession.sessionMenu(request);
        String flag = request.getParameter("flag");
        if (flag == null) {
            flag = "0";
        }
        return new ModelAndView("jsp/system/estore", "estore", estoreService.findEStore()).addObject("flag", flag);
    }

    /**
     * 显示E店宝配置信息
     */
    @RequestMapping("editEstore")
    public ModelAndView editEstore(HttpServletRequest request) {
        // 设置导航
        MenuSession.sessionMenu(request);
        String flag = request.getParameter("flag");
        if (flag == null) {
            flag = "0";
        }
        return new ModelAndView("jsp/system/estore_edit", "estore", estoreService.findEStore()).addObject("flag", flag);
    }

    /**
     * 修改E店宝信息
     * 
     * @param estore
     * @param bindingResult
     * @param request
     * @return
     */
    @RequestMapping("/updateestore")
    public ModelAndView updateEStore(@Valid EStore estore, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView("queryestore.htm")).addObject("flag", request.getParameter("flag"));
        }
        PrintWriter pr = null;

        try {
            pr = response.getWriter();
            // 修改邮箱信息，并返回值
            pr.print(estoreService.updateEStore(estore));
            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "修改E店宝设置", request.getSession().getAttribute(OPERAPATH) + ",用户名:" + customerName);

        } catch (IOException e) {
            LOGGER.error("修改E店宝设置错误：=>",e);
            return new ModelAndView(new RedirectView("queryestore.htm"));
        } finally {
            if (pr != null) {
                pr.close();
            }
        }

        return null;
    }

    public EStoreServiceImpl getEstoreService() {
        return estoreService;
    }

    @Resource(name = "estoreService")
    public void setEstoreService(EStoreServiceImpl estoreService) {
        this.estoreService = estoreService;
    }
}
