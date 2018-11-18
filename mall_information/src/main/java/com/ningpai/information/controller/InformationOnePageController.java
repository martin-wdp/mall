/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.information.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ningpai.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.information.bean.InfoOPTag;
import com.ningpai.information.bean.InformationOnePage;
import com.ningpai.information.service.InfoOPTagService;
import com.ningpai.information.service.InformationOnePageService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.publicpackage.InfoImageManagePublic;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.InfoSysTempService;

/**
 * 资讯单页控制层
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月24日 18:05:38
 * @version
 */
@Controller
public class InformationOnePageController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(InformationOnePageController.class);

    public static final String NAME = "name";

    public static final String OPERAPATH = "operaPath";

    private static final String LOGINUSERID = "loginUserId";

    private static final String QUERYINFOONEPAGESBYPAGEBEAN_HTM = "queryInfoOnePagesByPageBean.htm?CSRFToken=";
    private static final String LOGGERINFO1 = ",用户名:";
    private static final String LOGGERINFO2 = "==============================";

    /** 资讯单页Service */
    @Resource(name = "InformationOnePageService")
    private InformationOnePageService infoOnePageService;

    /** 频道模板设置业务类 */
    @Resource(name = "InfoSysTempService")
    private InfoSysTempService channelSysTempService;

    /** SERVICE-单页标签 */
    @Resource(name = "InfoOPTagService")
    private InfoOPTagService infoOPTagService;

    /** 资讯图片管理接口 */
    @Resource(name = "InfoImageManagePublic")
    private InfoImageManagePublic infoImageManagePublic;

    /**
     * 查询资讯单页分页数据
     * 
     * @param request
     * @param response
     * @param pb
     * @param selectBean
     * @return
     */
    @RequestMapping(value = "/queryInfoOnePagesByPageBean")
    public ModelAndView queryInfoOnePagesByPageBean(HttpServletRequest request, HttpServletResponse response, PageBean pb, SelectBean selectBean) {
        MenuSession.sessionMenu(request);
        LOGGER.debug("==============================查看单页列表==============================");
        /** 判断查询文本是否为空 若为空 将条件也设置为空 */
        if ("".equals(selectBean.getSearchText())) {
            selectBean.setCondition("");
        }
        /** 获取模板列表 */
        List<SysTemp> sysTempList = channelSysTempService.queryAllSystemp();
        /** 参数设置到作用域中 */
        request.setAttribute("selectBean", selectBean);
        /** 返回结果 */
        return new ModelAndView("jsp/information/infoOnePageList", "pb", infoOnePageService.queryInfoOnePageByPageBean(pb, selectBean)).addObject("sysTempList", sysTempList);
    }

    /**
     * 跳转到显示资讯单页<br/>
     * 资讯单页编号不为空时查询资讯单页并绑定到页面，修改资讯单页<br/>
     * 资讯单页编号为空时，添加资讯单页
     * 
     * @param request
     * @param response
     * @param infoOnePageId
     * @return ModelAndView
     */
    @RequestMapping("/showInfoOnePage")
    public ModelAndView showInfoOnePage(HttpServletRequest request, HttpServletResponse response, Long infoOnePageId) {
        /** 创建一个视图对象 */
        ModelAndView mav = new ModelAndView();
        /** 获取模板列表 */
        List<SysTemp> sysTempList = channelSysTempService.queryAllSystemp();
        /** sysTempList存入到视图对象中 */
        mav.addObject("sysTempList", sysTempList);

        /** 如果单页标签ID不为null */
        if (null != infoOnePageId) {
            LOGGER.debug("==============================查看单页并跳转到查看或修改页面==============================");
            /** 根据主键查询单页标签 */
            mav.addObject("infoOnePage", infoOnePageService.getInfoOnePageByID(infoOnePageId));
        }
        /** 设置视图对象 */
        mav.setViewName("jsp/information/showInfoOnePage");
        /** 返回结果 */
        return mav;
    }

    /**
     * 跳转到显示资讯单页<br/>
     * 资讯单页编号不为空时查询资讯单页并绑定到页面，修改资讯单页<br/>
     * 资讯单页编号为空时，添加资讯单页
     * 
     * @param request
     * @param response
     * @param infoOnePageId
     * @return ModelAndView
     */
    @RequestMapping("/showinfoonepageajax")
    @ResponseBody
    public InformationOnePage showinfoonepageajax(HttpServletRequest request, HttpServletResponse response, Long infoOnePageId) {
        LOGGER.debug("==============================修改页面==============================");
        /** 返回结果 根据主键查询单页标签 */
        return infoOnePageService.getInfoOnePageByID(infoOnePageId);
    }

    /**
     * 根据模板ID，AJAX获取单页标签列表
     * 
     * @param tempId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getInfoOPTagByTempId")
    public List<InfoOPTag> getInfoOPTagByTempId(String tempId) {
        /** 根据模板ID查询单页标签列表 */
        return infoOPTagService.findAllInfoOPTag(tempId);
    }

    /**
     * 添加资讯单页
     * 
     * @param request
     * @param response
     * @param infoOnePage
     * @param bindingResult
     * @return ModelAndView
     */
    @RequestMapping("/addInfoOnePage")
    public ModelAndView addInfoOnePage(MultipartHttpServletRequest request, HttpServletResponse response, @Valid InformationOnePage infoOnePage, BindingResult bindingResult) {
        /** 如果验证不通过则重定向到查询资讯单页分页数据 */
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYINFOONEPAGESBYPAGEBEAN_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        LOGGER.debug("==============================添加单页==============================");
        /** 获取存储在session中的登录者id */
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        try {
            /**
             * 如果登录id为空 就设置为1
             */
            if (null == loginUserId) {
                loginUserId = 1L;
            }
            /**
             * 获取页面中name为imageSrc文件流 判断获得的文件流失是否为null 如果不为null再判断文件流的size是否大于0
             * 如果大于0就设置图片路径 最后调用保存图片方法进行存储
             */
            MultipartFile muFile = request.getFile("imageSrc");
            if (muFile != null && muFile.getSize() > 0) {
                    /** 设置图片路径 */
                    infoOnePage.setImgSrc(UploadUtil.uploadFileOne(muFile, request));
                    /** 保存图片信息，以供以后选择 */
                    infoImageManagePublic.saveImage(infoOnePage.getImgSrc());
            }
            /** 设置创建者id */
            infoOnePage.setCreateUserId(loginUserId);
            /** 设置更新者id */
            infoOnePage.setUpdateUserId(loginUserId);
            /** 执行保存方法 */
            infoOnePageService.saveInfoOnePage(infoOnePage);
            /** 获取存储在session中的用户名称 */
            String customerName = (String) request.getSession().getAttribute(NAME);
            /** 记录更新日志 */
            OperaLogUtil.addOperaLog(request, customerName, "添加单页", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("==============================添加单页失败：",e);
        }
        /** 返回结果 */
        return new ModelAndView(new RedirectView(QUERYINFOONEPAGESBYPAGEBEAN_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 修改资讯单页
     * 
     * @param request
     * @param response
     * @param infoOnePage
     * @param bindingResult
     * @return ModelAndView
     */
    @RequestMapping("/updateInfoOnePage")
    public ModelAndView updateInfoOnePage(MultipartHttpServletRequest request, HttpServletResponse response, @Valid InformationOnePage infoOnePage, BindingResult bindingResult) {
        /** 如果验证不通过则重定向到查询资讯单页分页数据 */
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYINFOONEPAGESBYPAGEBEAN_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        /** 获取存储在session中的登录id */
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        LOGGER.debug("==============================修改单页==============================");
        try {
            /**
             * 判断登录id 是否为null 如果为null就设置为1
             */
            if (null == loginUserId) {
                loginUserId = 1L;
            }
            /**
             * 获取页面中name为iamgeSrc的文件流 判断文件流是否为null 如果不为Null 再判断文件流的size是否大于0
             * 如果大于0就设置图片 并保存图片
             */
            MultipartFile muFile = request.getFile("imageSrc");
            if (muFile != null && muFile.getSize() > 0) {
                    /** 设置图片路径 */
                    infoOnePage.setImgSrc(UploadUtil.uploadFileOne(muFile, request));
                    /** 保存图片信息，以供以后选择 */
                    infoImageManagePublic.saveImage(infoOnePage.getImgSrc());
            }
            /** 设置修改者的id */
            infoOnePage.setUpdateUserId(loginUserId);
            /** 调用更新方法 */
            infoOnePageService.updateInfoOnePage(infoOnePage);
            /** 获取存储在session中的用户名称 */
            String customerName = (String) request.getSession().getAttribute(NAME);
            /** 记录更新日志 */
            OperaLogUtil.addOperaLog(request, customerName, "修改单页", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("==============================修改单页失败：" ,e);
        }
        /** 返回结果 */
        return new ModelAndView(new RedirectView(QUERYINFOONEPAGESBYPAGEBEAN_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 删除资讯单页
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delInfoOnePage")
    public void delInfoOnePage(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("==============================删除单页==============================");
        /** 获取存储在session中的登录id */
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        try {
            /**
             * 判断登录id 是否为null 如果为null就设置为1
             */
            if (null == loginUserId) {
                loginUserId = 1L;
            }
            /** 获取页面中name为infoOnePageIds的数组 */
            String[] ids = request.getParameterValues("infoOnePageIds[]");
            /** 循环数组并一一执行删除方法 */
            for (int i = 0; i < ids.length; i++) {
                Long id = Long.valueOf(ids[i]);
                /** 根据主键删除资讯单页 */
                infoOnePageService.delInfoOnePage(id, loginUserId);
            }
            /** 获取存储在session中的用户名称 */
            String customerName = (String) request.getSession().getAttribute(NAME);
            /** 记录更新日志 */
            OperaLogUtil.addOperaLog(request, customerName, "删除单页", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (NumberFormatException e) {
            LOGGER.error("==============================删除单页失败：" ,e);
        }
    }

    /**
     * 验证单页标题是否存在
     * 
     * @param title
     *            待验证的单页标题
     * @return 验证的结果
     */
    @RequestMapping("/checkInfoOPByTitle")
    @ResponseBody
    public boolean checkInfoOPByTitle(String title, Long infoOPId) {
        if (null != infoOPId) {
            /** 根据单页ID查询出文章标题，判断老标题和新标题是否一样 */
            return this.infoOnePageService.checkAddInfoOPByTitle(title, infoOPId);
        } else {
            /** 根据单页标题查询单页数量判断标题是否存在 */
            return this.infoOnePageService.checkAddInfoOPByTitle(title);
        }
    }

}
