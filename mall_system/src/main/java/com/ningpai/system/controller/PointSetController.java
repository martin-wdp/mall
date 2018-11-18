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

import com.ningpai.util.MenuSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.system.bean.PointSet;
import com.ningpai.system.service.PointSetService;
import com.ningpai.util.MyLogger;

/**
 * 积分设置控制层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月16日 下午2:41:19
 * @version 1.0
 */
@Controller
public class PointSetController {

    private static final MyLogger LOGGER = new MyLogger(PointSetController.class);

    @Resource(name = "pointSetService")
    private PointSetService pointSetService;

    /**
     * 传送数据到积分设置页面
     * 
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping("/pointset")
    public ModelAndView findPointSet(HttpServletRequest request, HttpServletResponse response) {
        // 设置导航
        MenuSession.sessionMenu(request);
        String flag = request.getParameter("flag");
        if (flag == null) {
            flag = "0";
        }
        return new ModelAndView("jsp/system/pointset", "pointset", pointSetService.findPointSet()).addObject("flag", flag);
    }

    /**
     * 传修改积分设置
     * 
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping("/updatpointset")
    public ModelAndView updatePointSet(HttpServletRequest request, HttpServletResponse response, PointSet pointSet) {

        PrintWriter pr = null;

        try {
            pr = response.getWriter();

            pr.print(pointSetService.updatePointSet(pointSet));
        } catch (IOException e) {
            LOGGER.error("",e);
        } finally {
            if (pr != null) {
                pr.close();
            }
        }

        return null;
    }

    /**
     * 传送数据到积分设置页面
     * 
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping("/grouppointset")
    public ModelAndView findGroupPointSet(HttpServletRequest request, HttpServletResponse response) {
        // 设置导航
        MenuSession.sessionMenu(request);
        String flag = request.getParameter("flag");
        if (flag == null) {
            flag = "0";
        }
        return new ModelAndView("jsp/social/group_integral", "pointset", pointSetService.findPointSet()).addObject("flag", flag);
    }
}
