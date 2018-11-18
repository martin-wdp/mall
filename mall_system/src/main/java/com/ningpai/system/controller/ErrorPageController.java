/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.system.bean.ErrorPage;
import com.ningpai.system.service.ErrorPageService;
import com.ningpai.util.PageBean;

/**
 * 异常页面控制层
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月25日 16:37:38
 * @version
 */
@Controller
public class ErrorPageController {
    // 异常页面service
    private ErrorPageService errorPageService;

    public ErrorPageService getErrorPageService() {
        return errorPageService;
    }

    @Resource(name = "ErrorPageService")
    public void setErrorPageService(ErrorPageService errorPageService) {
        this.errorPageService = errorPageService;
    }

    /**
     * 查询异常页面分页数据
     */
    @RequestMapping(value = "/queryErrorPagesByPageBean")
    public ModelAndView queryErrorPagesByPageBean(PageBean pb) {
        return new ModelAndView("jsp/system/errorPageList", "pb", errorPageService.queryErrorPageByPageBean(pb));
    }

    /**
     * 跳转到显示异常页面<br/>
     * 异常页面编号不为空时查询异常页面并绑定到页面，修改异常页面<br/>
     * 异常页面编号为空时，添加异常页面
     * 
     * @return ModelAndView
     */
    @RequestMapping("/showErrorPage")
    public ModelAndView showErrorPage(Long errorPageId) {
        // 初始化异常页面
        ErrorPage errorPage = new ErrorPage();
        if (null != errorPageId) {
            // 获取异常页面
            errorPage = errorPageService.getErrorPage(errorPageId);
        }
        return new ModelAndView("jsp/system/showErrorPage", "errorPage", errorPage);
    }

    /**
     * 添加异常页面
     * 
     * @return ModelAndView
     */
    @RequestMapping("/addErrorPage")
    public ModelAndView addErrorPage(ErrorPage errorPage) {
        // 删除标记
        errorPage.setDelflag("0");
        // 添加人
        errorPage.setCreateUserId(1L);
        // 创建日期
        errorPage.setCreateDate(new Date());
        // 保存异常页面
        errorPageService.saveErrorPage(errorPage);
        return new ModelAndView(new RedirectView("queryErrorPagesByPageBean.htm"));
    }

    /**
     * 修改异常页面
     * 
     * @return ModelAndView
     */
    @RequestMapping("/updateErrorPage")
    public ModelAndView updateErrorPage(ErrorPage errorPage) {
        // 修改人
        errorPage.setUpdateUserId(1L);
        // 修改时间
        errorPage.setUpdateDate(new Date());
        // 修改异常页面
        errorPageService.updateErrorPage(errorPage);
        return new ModelAndView(new RedirectView("queryErrorPagesByPageBean.htm"));
    }

    /**
     * 删除异常页面
     * 
     * @return ModelAndView
     */
    @RequestMapping("/delErrorPage")
    public void delErrorPage(HttpServletRequest request, HttpServletResponse response) {
        // 获取id
        String[] ids = request.getParameterValues("errorPageIds[]");
        // 循环批量删除异常页面
        for (int i = 0; i < ids.length; i++) {
            Long id = Long.valueOf(ids[i]);
            errorPageService.delErrorPage(id);
        }
    }

}
