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

import com.ningpai.system.bean.ImageSet;
import com.ningpai.system.service.ImageSetService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 图片规则设置控制层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月17日 上午10:05:31
 * @version 1.0
 */
@Controller
public class ImageSetController {
    /** 日志logger */
    private static final MyLogger LOGGER = new MyLogger(
            ImageSetController.class);
    /** 图片设置服务 */
    @Resource(name = "imageSetService")
    private ImageSetService imageSetService;
    /** imageset.htm */
    private String page = "imageset.htm";

    /**
     * 显示图片规则信息
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/imageset")
    public ModelAndView imageSet(HttpServletRequest request,
            HttpServletResponse response, PageBean pb, SelectBean selectBean) {

        // 判断查询文本是否为空 若为空 将条件也设置为空
        if ("".equals(selectBean.getSearchText())) {
            selectBean.setCondition("");
        }

        // 参数设置到作用域中
        request.setAttribute("selectBean", selectBean);

        return new ModelAndView("jsp/system/imagesetup", "pb",
                imageSetService.findByPageBean(pb, selectBean));
    }

    /**
     * 添加图片设置信息
     * 
     * @param request
     * @param response
     * @param imageSet
     * @return int
     */
    @RequestMapping("/addimageset")
    public ModelAndView addImageSet(HttpServletRequest request,
            HttpServletResponse response, ImageSet imageSet) {
        // 添加图片设置
        imageSetService.insertImageSet(imageSet);

        return new ModelAndView(new RedirectView(page));
    }

    /**
     * 删除图片设置信息
     * 
     * @param request
     * @param response
     * @param ruleId
     */
    @RequestMapping("/delimageset")
    public ModelAndView delImageSet(HttpServletRequest request,
            HttpServletResponse response) {

        // 删除友情链接信息
        PrintWriter pr = null;

        try {
            pr = response.getWriter();
            pr.print(imageSetService.deleteImageSet(request
                    .getParameterValues("ruleIds[]")));

        } catch (IOException e) {
            // 错误日志输出
            LOGGER.error("",e);
        } finally {
            if (pr != null) {
                pr.close();
            }
        }
        return null;
    }

    /**
     * 修改图片设置信息
     * 
     * @param request
     * @param response
     * @param ruleId
     * @return
     */
    @RequestMapping("/updateimageset")
    public ModelAndView updateImageSet(HttpServletRequest request,
            HttpServletResponse response, ImageSet imageSet) {
        // 修改图片设置
        imageSetService.updateImageSet(imageSet);

        return new ModelAndView(new RedirectView(page));
    }

    /**
     * 查询图片设置信息
     * 
     * @param request
     * @param response
     * @param ruleId
     * @return
     */
    @RequestMapping("/findone")
    @ResponseBody
    public ImageSet findOne(Long ruleId) {

        return imageSetService.findByRuleId(ruleId);
    }
}
