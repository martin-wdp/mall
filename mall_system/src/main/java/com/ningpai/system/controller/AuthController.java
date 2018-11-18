/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.controller;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.Auth;
import com.ningpai.system.service.AuthService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 第三方登录控制层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月25日 下午2:32:37
 * @version 1.0
 */
@Controller
public class AuthController {

    /**
     * 日志记录
     */
    private static final MyLogger LOGGER = new MyLogger(AuthController.class);

    private static final String LOGGERINFO1 = ",用户名:";
    private static final String LOGGERINFO2 = "删除登录接口设置";

    /** spring注解 */
    @Resource(name = "authService")
    private AuthService authService;

    // 页面跳转路径
    private String page = "authset.htm";

    // 微信登录设置
    private String wxpage = "wxLoginSet.htm";

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    /**
     * 分页显示第三方登录信息
     * 
     * @param request
     * @param response
     * @param pb
     * @return ModelAndView
     */
    @RequestMapping("/authset")
    public ModelAndView barSet(HttpServletRequest request, HttpServletResponse response, PageBean pb, SelectBean selectBean) {
        // 设置导航
        MenuSession.sessionMenu(request);
        // 判断查询文本是否为空
        if ("".equals(selectBean.getSearchText())) {
            // 若为空 将条件也设置为空
            selectBean.setCondition("");
        }

        // 参数设置到作用域中
        request.setAttribute("selectBean", selectBean);

        return new ModelAndView("jsp/system/authset", "pb", authService.findByPageBean(pb, selectBean));

    }

    /**
     * 微信登录信息
     * 
     * @param request
     * @param response
     * @param pb
     * @return ModelAndView
     */
    @RequestMapping("/wxLoginSet")
    public ModelAndView wxLoginSet(HttpServletRequest request, HttpServletResponse response, PageBean pb, SelectBean selectBean) {
        // 设置导航
        MenuSession.sessionMenu(request);
        // 判断查询文本是否为空
        if ("".equals(selectBean.getSearchText())) {
            // 若为空 将条件也设置为空
            selectBean.setCondition("");
        }
        // 参数设置到作用域中
        request.setAttribute("selectBean", selectBean);
        return new ModelAndView("jsp/system/wxauthset", "pb", authService.findBywxPageBean(pb, selectBean));

    }

    /**
     * 添加第三方登录接口
     * 
     * @param request
     * @param response
     * @param auth
     * @return
     */
    @RequestMapping("/addauth")
    public ModelAndView addAuth(HttpServletRequest request, HttpServletResponse response, Auth auth, @ModelAttribute("uploadForm") FileUploadForm uploadForm) {

        try {
            // 取出登录的session
            MultipartFile picPath = uploadForm.getPartPic();
            // 判断图片路径是否为空
            if (picPath != null && !"".equals(picPath.getName())) {
                // 设置图片路径
                auth.setAuthPic(UploadUtil.uploadFileOne(picPath, request));
            }
            // 添加第三方登录接口
            authService.insertAuth(auth);

            // 获取会员名称
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 添加日志
            OperaLogUtil.addOperaLog(request, customerName, "添加登录接口设置", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("添加登录接口设置错误：=>",e);
        }
        return new ModelAndView(new RedirectView(page));
    }

    /**
     * 删除登录接口设置
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/delauth")
    public ModelAndView delAuth(HttpServletRequest request, HttpServletResponse response) {

        PrintWriter pr = null;

        try {
            pr = response.getWriter();

            pr.print(authService.deleteAuth(request.getParameterValues("authIds[]")));

            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, LOGGERINFO2, request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);

        } catch (IOException e) {
            LOGGER.error("删除登录接口设置错误：=>" ,e);
        }

        return new ModelAndView(new RedirectView(page));
    }

    /**
     * 删除微信登录接口设置（移动版微信登录接口）
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/delwxAuth")
    @ResponseBody
    public int delwxAuth(HttpServletRequest request, HttpServletResponse response) {
        // 删除登录接口
        int n = authService.deleteAuth(request.getParameterValues("authIds[]"));
        // 判断是否删除成功
        if (n > 0) {
            // 获取会员名称
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 添加日志
            OperaLogUtil.addOperaLog(request, customerName, LOGGERINFO2, request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        }

        return n;
    }

    /**
     * 删除单个登录接口设置
     *
     * @param authId
     *            主键id
     * @return
     */
    @RequestMapping("/delauthone")
    public ModelAndView delAuthOne(Long authId, HttpServletRequest request) {
        // 删除登录接口
        authService.deleteAuthById(authId);
        // 获取会员名称
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 添加日志
        OperaLogUtil.addOperaLog(request, customerName, LOGGERINFO2, request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        return new ModelAndView(new RedirectView(page));
    }

    /**
     * 批量删除登录接口设置
     *
     * @param authIds
     *            主键id
     * @return
     */
    @RequestMapping("/delauthbatch")
    public ModelAndView delAuthBatch(Long[] authIds, HttpServletRequest request) {
        // 循环删除登录接口设置
        for (Long authId : authIds) {
            authService.deleteAuthById(authId);
        }
        // 会员名称
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 添加日志
        OperaLogUtil.addOperaLog(request, customerName, LOGGERINFO2, request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        return new ModelAndView(new RedirectView(page));
    }

    /**
     * ajax查询第三方登录信息
     * 
     * @param authId
     * @return Auth
     */
    @RequestMapping("/findauthone")
    @ResponseBody
    public Auth findAuthOne(Long authId) {

        return authService.findAuthByAuthId(authId);
    }


    /**
     *  修改第三方登录信息
     * @param request
     * @param response
     * @param auth
     * @param bindingResult
     * @param uploadForm
     * @return ModelAndView
     */
    @RequestMapping("/updateauth")
    public ModelAndView updateAuth(HttpServletRequest request, HttpServletResponse response, @Valid Auth auth, BindingResult bindingResult,
            @ModelAttribute("uploadForm") FileUploadForm uploadForm) {
        // 验证是否有错
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(page));
        }
        try {
            // 获得图片路径
            MultipartFile picPath = uploadForm.getPartPic();
            // 判读图片路径是否为空
            if (picPath != null && !"".equals(picPath.getName())) {
                // 设置图片路径
                auth.setAuthPic(UploadUtil.uploadFileOne(picPath, request));
            }
            // 修改第三方登录信息
            authService.updateAuth(auth);
            // 会员名称
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 添加日志
            OperaLogUtil.addOperaLog(request, customerName, "修改登录接口设置", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);

        } catch (Exception e) {
            LOGGER.error("修改登录接口设置错误：=>",e);
        }

        return new ModelAndView(new RedirectView(page));
    }


    /**
     *  修改微信登录信息（移动版微信登录接口）
     * @param request
     * @param response
     * @param auth
     * @param bindingResult
     * @param uploadForm
     * @return ModelAndView
     */
    @RequestMapping("/updatewxAuth")
    public ModelAndView updatewxAuth(HttpServletRequest request, HttpServletResponse response, @Valid Auth auth, BindingResult bindingResult,
            @ModelAttribute("uploadForm") FileUploadForm uploadForm) {
        // 验证是否有错
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(wxpage));
        }
        try {
            // 获取图片路径
            MultipartFile picPath = uploadForm.getPartPic();
            // 判断图片路径是否为空
            if (picPath != null && !"".equals(picPath.getName())) {
                // 设置图片路径
                auth.setAuthPic(UploadUtil.uploadFileOne(picPath, request));
            }
            // 修改微信登录信息（移动版微信登录接口）
            authService.updateAuth(auth);
            // 会员名称
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 添加日志
            OperaLogUtil.addOperaLog(request, customerName, "修改登录接口设置", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);

        } catch (Exception e) {
            LOGGER.error("修改登录接口设置错误：=>",e);
        }

        return new ModelAndView(new RedirectView(wxpage));
    }

    /**
     * 根据ID修改第三方登录接口启用状态<br/>
     * 已启用改为不启用，不启用改为已启用
     * 
     * @param authId
     * @param request
     * @return
     */
    @RequestMapping("/updateUserdStatusForAuth")
    public ModelAndView updateUserdStatusForAuth(Long authId, HttpServletRequest request) {
        try {
            // 根据ID修改第三方登录接口启用状态
            this.authService.updateUserdStatus(authId);
            // 获取会员名称
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 添加日志
            OperaLogUtil.addOperaLog(request, customerName, "修改登录接口启用状态", request.getSession().getAttribute(OPERAPATH).toString());
        } catch (Exception e) {
            LOGGER.error("修改登录接口启用状态错误：=>",e);
        }
        return new ModelAndView(new RedirectView(page));
    }

    /**
     * 根据ID修改移动版微信登录接口启用状态<br/>
     * 已启用改为不启用，不启用改为已启用
     * 
     * @param authId
     * @param request
     * @return
     */
    @RequestMapping("/updateUserdStatusForwx")
    public ModelAndView updateUserdStatusForwx(Long authId, HttpServletRequest request) {
        try {
            // 根据ID修改移动版微信登录接口启用状态
            this.authService.updateUserdStatus(authId);
            // 获取会员名称
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 添加日志
            OperaLogUtil.addOperaLog(request, customerName, "修改登录接口启用状态", request.getSession().getAttribute(OPERAPATH).toString());
        } catch (Exception e) {
            LOGGER.error("修改登录接口启用状态错误：=>",e);
        }
        return new ModelAndView(new RedirectView(wxpage));
    }
}
