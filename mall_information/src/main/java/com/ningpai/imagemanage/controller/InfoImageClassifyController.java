/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.imagemanage.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.imagemanage.bean.InfoImageClassify;
import com.ningpai.imagemanage.service.InfoImageClassifyService;
import com.ningpai.imagemanage.service.InfoImageManageService;
import com.ningpai.imagemanage.vo.InfoImageClassifyVo;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 控制器-资源图片分类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月25日下午2:33:01
 */
@Controller
public class InfoImageClassifyController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(InfoImageClassifyController.class);

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    public static final String QUERYIMAGECLASSIFYBYPB_HTM = "queryImageClassifyByPb.htm?CSRFToken=";

    public static final String LOGGERINFO1 = ",用户名:";

    /** 资源图片类型SERVICE */
    private InfoImageClassifyService infoImageClassifyService;

    /** 资源图片信息SERVICE */
    private InfoImageManageService infoImageManageService;

    /**
     * 分页查询图片分类
     * 
     * @param pb
     * @return
     */
    @RequestMapping("/queryImageClassifyByPb")
    public ModelAndView queryImageClassifyByPb(PageBean pb) {
        LOGGER.debug("======分页查询图片分类======");
        // return new ModelAndView("jsp/infoImage/infoImageClassify","pb",
        // infoImageClassifyService.selectImageClassifyByParam(pb));
        /** 返回视图InfoImageClassify */
        return new ModelAndView("jsp/infoImage/infoImageClassify");
    }

    /**
     * Ajax分页查询图片分类
     * 
     * @param pb
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryImageClassifyByPbForAjax")
    public PageBean queryImageClassifyByPbForAjax(PageBean pb, HttpServletRequest request) {
        LOGGER.debug("======分页查询图片分类======");
        /**
         * 获取session中存储的参数menuId的值 判断所获得的值是否为null
         */
        if (request.getSession().getAttribute("menuId") != null) {
            /** 不为null时把分页的页数设置为10000 */
            pb.setPageSize(10000);
        }
        /** 返回结果 */
        return infoImageClassifyService.selectImageClassifyByParam(pb);
    }

    /**
     * Ajax查询所有图片分类<br/>
     * 用于分类管理选择父分类
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryAllImageClassifyForAjax")
    public List<InfoImageClassify> queryAllImageClassifyForAjax() {
        LOGGER.debug("======Ajax查询所有图片分类======");
        /** 返回结果 */
        return infoImageClassifyService.selectAllImageClassify();
    }

    /**
     * Ajax查询所有图片分类<br/>
     * 用于上传图片选择分类
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryAllImageClassifyForImg")
    public List<InfoImageClassify> queryAllImageClassifyForImg() {
        LOGGER.debug("======Ajax查询所有图片分类======");
        /** 返回结果 */
        return infoImageClassifyService.selectAllImageClassifyForImg();
    }

    /**
     * 添加图片分类
     * 
     * @param infoImageClassify
     * @return
     */
    @RequestMapping("/saveImageClassifyAction")
    public ModelAndView saveImageClassifyAction(@Valid InfoImageClassify infoImageClassify, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYIMAGECLASSIFYBYPB_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        LOGGER.debug("======添加图片分类======");
        /** 保存图片资源 */
        infoImageClassifyService.saveInfoImageClassify(infoImageClassify);
        /** 从session获取名称为name的值 */
        String customerName = (String) request.getSession().getAttribute(NAME);
        /** 添加操作日志 */
        OperaLogUtil.addOperaLog(request, customerName, "添加图片分类", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        /** 返回结果重定向到queryImageClassifyByPb控制器 */
        return new ModelAndView(new RedirectView(QUERYIMAGECLASSIFYBYPB_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 修改图片分类
     * 
     * @param infoImageClassify
     * @return
     */
    @RequestMapping("/updateImageClassifyAction")
    public ModelAndView updateImageClassifyAction(@Valid InfoImageClassify infoImageClassify, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYIMAGECLASSIFYBYPB_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        LOGGER.debug("======修改图片分类======");
        /** 修改图片资源 */
        infoImageClassifyService.updateInfoImageClassify(infoImageClassify);
        /** 从session获取名称为name的值 */
        String customerName = (String) request.getSession().getAttribute(NAME);
        /** 添加操作日志 */
        OperaLogUtil.addOperaLog(request, customerName, "修改图片分类", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        /** 返回结果重定向到queryImageClassifyByPb控制器 */
        return new ModelAndView(new RedirectView(QUERYIMAGECLASSIFYBYPB_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * Ajax根据类型查询信息数量，判断是否能删除
     * 
     * @param infoImageClassifyId
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkDeleteClassify")
    public boolean checkDeleteClassify(Long infoImageClassifyId) {
        LOGGER.debug("======Ajax根据类型查询信息数量，判断是否能删除======");
        /** 声明布尔类型变量flag，初始值为false */
        boolean flag = false;

        /** 根据infoImageClassfyId查询图片资源集合 */
        List<InfoImageClassifyVo> list = infoImageClassifyService.selectByParentId(infoImageClassifyId);
        /** 有子分类 */
        if (null != list && !list.isEmpty()) {
            flag = false;
            /** 没有子分类 */
        } else {
            /** 根据图片分类查询图片信息总行数 */
            int count = infoImageManageService.selectImageManageCountByClassifyId(infoImageClassifyId);
            LOGGER.debug("======" + count + "======");
            /** 有图片 */
            if (count > 0) {
                /** 将flag的值赋值为false */
                flag = false;
                /** 没图片 */
            } else {
                /** 将flag的值赋值为true */
                flag = true;
            }
        }
        /** 返回结果 */
        return flag;
    }

    /**
     * 删除图片分类
     * 
     * @param infoImageClassifyId
     * @return
     */
    @RequestMapping("/deleteImageClassifyAction")
    public ModelAndView deleteImageClassifyAction(Long infoImageClassifyId, HttpServletRequest request) {
        LOGGER.debug("======删除图片分类======");
        /** 根据传入的infoImageClassifyId的值来删除图片资源 */
        infoImageClassifyService.deleteInfoImageClassify(infoImageClassifyId);
        /** 获取session中存储的用户姓名 */
        String customerName = (String) request.getSession().getAttribute(NAME);
        /** 记录操作日志 */
        OperaLogUtil.addOperaLog(request, customerName, "删除图片分类", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        /** 重定向到对应的控制器 */
        return new ModelAndView(new RedirectView(QUERYIMAGECLASSIFYBYPB_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    public InfoImageClassifyService getInfoImageClassifyService() {
        return infoImageClassifyService;
    }

    @Resource(name = "InfoImageClassifyService")
    public void setInfoImageClassifyService(InfoImageClassifyService infoImageClassifyService) {
        this.infoImageClassifyService = infoImageClassifyService;
    }

    public InfoImageManageService getInfoImageManageService() {
        return infoImageManageService;
    }

    @Resource(name = "InfoImageManageService")
    public void setInfoImageManageService(InfoImageManageService infoImageManageService) {
        this.infoImageManageService = infoImageManageService;
    }

}
