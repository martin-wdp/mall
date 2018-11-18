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
import com.ningpai.system.bean.HelpCate;
import com.ningpai.system.service.HelpCateService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.MenuSession;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 帮助分类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月20日 上午11:11:47
 * @version 1.0
 */
@Controller
public class HelpCateController {

    /** 日志logger */
    private static final MyLogger LOGGER = new MyLogger(HelpCateController.class);

    /**
     * 跳转页面
     */
    private String page = "tohelpcate.htm";

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    /** 帮助分类service */
    @Resource(name = "helpCateService")
    private HelpCateService helpCateService;

    /**
     * 跳转到帮助中心分类页面
     * 
     * @param request
     * @param response
     * @param pb
     * @return ModelAndView
     */
    @RequestMapping("/tohelpcate")
    public ModelAndView toHelpCate(HttpServletRequest request, HttpServletResponse response, PageBean pb, SelectBean selectBean) {
        MenuSession.sessionMenu(request);
        // 判断查询文本是否为空 若为空 将条件也设置为空
        if ("".equals(selectBean.getSearchText())) {
            selectBean.setCondition("");
        }

        // 参数设置到作用域中
        request.setAttribute("selectBean", selectBean);
        return new ModelAndView("jsp/system/helpcate", "pb", helpCateService.findByPageBean(pb, selectBean));
    }

    /**
     * 添加帮助分类
     * 
     * @param request
     * @param response
     * @param helpCate
     * @return ModelAndView
     */
    @RequestMapping("/addhelpcate")
    public ModelAndView addHelpCate(MultipartHttpServletRequest request, HttpServletResponse response, @Valid HelpCate helpCate, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(page));
        }
        try {
            // 待文件上传区
            MultipartFile file = request.getFile("netLogo");
            // 若有数据则上传文件
            if (file != null && !file.isEmpty()) {
                    helpCate.setHelpcateImg(UploadUtil.uploadFileOne(file, request));
            }
            // 添加帮助分类
            helpCateService.insertHelpCate(helpCate);
            // 用户名称
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 日志输出
            OperaLogUtil.addOperaLog(request, customerName, "添加帮助分类", request.getSession().getAttribute(OPERAPATH) + ", 添加用户名:" + customerName);
        } catch (Exception e) {
            // 错误日志输出
            LOGGER.error("添加帮助分类错误：=>" ,e);
        }
        return new ModelAndView(new RedirectView(page));
    }

    /**
     * 删除帮助分类
     * 
     * @param request
     * @param response
     * 
     * @return ModelAndView
     */
    @RequestMapping("/delhelpcate")
    public ModelAndView delHelpCate(HttpServletRequest request, HttpServletResponse response) {

        PrintWriter pr = null;

        try {
            pr = response.getWriter();
            // 批量删除帮助分类
            pr.print(helpCateService.deleteHelpCate(request.getParameterValues("cateIds[]")));
            // 用户名称
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 日志输出
            OperaLogUtil.addOperaLog(request, customerName, "删除帮助分类", request.getSession().getAttribute(OPERAPATH) + ", 删除用户名:" + customerName);
        } catch (IOException e) {
            // 错误日志输出
            LOGGER.error("删除帮助分类错误：=>",e);
        } finally {
            if (pr != null) {
                pr.close();
            }
        }

        return null;
    }

    /**
     * 根据helpCateId查询帮助分类
     * 
     * @param helpcateId
     * @return HelpCate
     */
    @RequestMapping("/findhelpcateone")
    @ResponseBody
    public HelpCate findHelpCateOne(Long helpcateId) {

        return helpCateService.findByHelpcateId(helpcateId);
    }

    /**
     * 修改帮助分类
     * 
     * @param request
     * @param response
     * @param helpCate
     * @return ModelAndView
     */
    @RequestMapping("/updatehelpcate")
    public ModelAndView updateHelpCate(MultipartHttpServletRequest request, HttpServletResponse response, @Valid HelpCate helpCate, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(page));
        }
        try {
            // 待文件上传区
            MultipartFile file = request.getFile("netLogo");
            // 若有数据则上传文件
            if (file != null && !file.isEmpty()) {
                    helpCate.setHelpcateImg(UploadUtil.uploadFileOne(file, request));
            }
            // 调用服务层
            helpCateService.updateHelpCate(helpCate);
            // 用户名称
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 日志输出
            OperaLogUtil.addOperaLog(request, customerName, "修改帮助分类", request.getSession().getAttribute(OPERAPATH) + ", 修改用户名:" + customerName);
        } catch (Exception e) {
            // 错误日志输出
            LOGGER.error("修改帮助分类错误：=>" ,e);
        }

        return new ModelAndView(new RedirectView(page));
    }
}
