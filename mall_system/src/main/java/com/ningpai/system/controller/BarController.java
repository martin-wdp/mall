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

import com.ningpai.system.bean.Bar;
import com.ningpai.system.service.BarService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 导航信息控制层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月19日 下午4:09:05
 * @version 1.0
 */
@Controller
public class BarController {

    /**
     * 日志记录
     */
    private static final MyLogger LOGGER = new MyLogger(BarController.class);
    /** spring注解 */
    @Resource(name = "barService")
    private BarService barService;

    // 设置路径
    private String page = "barset.htm";

    /**
     * 分页显示导航列表信息
     * 
     * @param request
     * @param response
     * @param pb
     * @return
     */
    @RequestMapping("/barset")
    public ModelAndView barSet(HttpServletRequest request,
            HttpServletResponse response, PageBean pb, SelectBean selectBean) {
        // 判断查询文本是否为空
        if ("".equals(selectBean.getSearchText())) {
            // 若为空 将条件也设置为空
            selectBean.setCondition("");
        }

        // 参数设置到作用域中
        request.setAttribute("selectBean", selectBean);
        return new ModelAndView("jsp/system/bar", "pb",
                barService.findByPageBean(pb, selectBean));

    }

    /**
     * 添加导航列表信息
     * 
     * @param request
     * @param response
     * @param pb
     * @return
     */
    @RequestMapping("/addbar")
    public ModelAndView addBar(HttpServletRequest request,
            HttpServletResponse response, Bar bar) {
        // 添加导航信息
        barService.insertBar(bar);
        return new ModelAndView(new RedirectView(page));
    }

    /**
     * 删除导航信息
     * 
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping("/delbar")
    public ModelAndView delBar(HttpServletRequest request,
            HttpServletResponse response) {

        PrintWriter pr = null;

        try {
            pr = response.getWriter();
            // 删除导航信息
            pr.print(barService.deleteBar(request.getParameterValues("barId[]")));

        } catch (IOException e) {
            // 日志输出
            LOGGER.error("",e);
        }

        return null;

    }

    /**
     * ajax查找导航列表信息
     * 
     * @param request
     * @param response
     * @param pb
     * @return ModelAndView
     */
    @RequestMapping("/findbarone")
    @ResponseBody
    public Bar findBarOne(Long barId) {

        return barService.findByBarId(barId);

    }

    /**
     * 修改导航列表信息
     * 
     * @param request
     * @param response
     * @param pb
     * @return
     */
    @RequestMapping("/updatebar")
    public ModelAndView updateBar(HttpServletRequest request,
            HttpServletResponse response, Bar bar) {
        // 修改导航列表信息
        barService.updateBar(bar);

        return new ModelAndView(new RedirectView(page));

    }
}
