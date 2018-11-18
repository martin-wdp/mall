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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.system.bean.Express;
import com.ningpai.system.service.ExpressService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 配送方式控制层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月18日 下午4:27:53
 * @version 1.0
 */
@Controller
public class ExpressController {
    /** 配送方式service */
    @Resource(name = "expressService")
    private ExpressService expressService;

    // 设置跳转的路径
    private String page = "express.htm";

    // 日志logger
    private static final MyLogger LOGGER = new MyLogger(ExpressController.class);

    /**
     * 分页查询配送方式信息
     * 
     * @param request
     * @param response
     * @param pb
     * @return ModelAndView
     */
    @RequestMapping("/express")
    public ModelAndView expressPage(HttpServletRequest request,
            HttpServletResponse response, PageBean pb, SelectBean selectBean) {

        // 判断查询文本是否为空 若为空 将条件也设置为空
        if ("".equals(selectBean.getSearchText())) {
            selectBean.setCondition("");
        }

        // 参数设置到作用域中
        request.setAttribute("selectBean", selectBean);

        return new ModelAndView("/jsp/system/express", "pb",
                expressService.findByPageBean(pb, selectBean));
    }

    /**
     * 添加配送方式信息
     * 
     * @param request
     * @param response
     * @param express
     * 
     * @return ModelAndView
     */
    @RequestMapping("/addexpress")
    public ModelAndView addExpress(HttpServletRequest request,
            HttpServletResponse response, Express express) {
        // 添加配送方式
        expressService.insertExpress(express);

        return new ModelAndView(new RedirectView(page));
    }

    /**
     * 删除配送方式信息
     * 
     * @param request
     * @param response
     * 
     * @return ModelAndView
     */
    @RequestMapping("/delexpress")
    public ModelAndView addExpress(HttpServletRequest request,
            HttpServletResponse response) {

        PrintWriter pr = null;

        try {
            pr = response.getWriter();
            pr.print(expressService.deleteExpress(request
                    .getParameterValues("expIds[]")));

        } catch (IOException e) {
            // 日志输出
            LOGGER.error("",e);
        } finally {
            if (pr != null) {
                pr.close();
            }

        }

        return new ModelAndView(new RedirectView(page));
    }

    /**
     * ajax查询单条配送记录
     * 
     * @param expid
     * @return
     */
    @RequestMapping("/findexpone")
    @ResponseBody
    public Express findExpOne(Long expid) {

        return expressService.findByExpid(expid);
    }

    /**
     * 修改配送信息
     * 
     * @param request
     * @param response
     * @param express
     * @return ModelAndView
     */
    @RequestMapping("/updateexpress")
    public ModelAndView updateExpress(HttpServletRequest request,
            HttpServletResponse response, Express express) {
        // 修改配送信息
        expressService.updateExpress(express);

        return new ModelAndView(new RedirectView(page));
    }
}
