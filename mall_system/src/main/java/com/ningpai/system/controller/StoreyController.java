/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.controller;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.system.bean.Storey;
import com.ningpai.system.service.FloorService;
import com.ningpai.system.service.StoreyService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 楼层控制层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月6日 上午9:19:03
 * @version 1.0
 */
@Controller
public class StoreyController {

    private static final MyLogger LOGGER = new MyLogger(StoreyController.class);

    @Resource(name = "storeyService")
    private StoreyService storeyService;
    // 楼层层数服务层
    @Resource(name = "floorService")
    private FloorService floorService;

    // 设置页面路径
    private String page = "storey.htm";

    /**
     * 分页查询楼层信息
     * 
     * @param request
     * @param response
     * @param pb
     * @return ModelAndView
     */
    @RequestMapping("/storey")
    public ModelAndView paySet(HttpServletRequest request, HttpServletResponse response, PageBean pb, SelectBean selectBean) {

        // 判断查询文本是否为空 若为空 将条件也设置为空
        if ("".equals(selectBean.getSearchText())) {
            selectBean.setCondition("");
        }

        // 参数设置到作用域中
        request.setAttribute("selectBean", selectBean);

        ModelAndView model = new ModelAndView();
        // 查询楼层信息
        model.addObject("pb", storeyService.findByPageBean(pb, selectBean));
        // 查询商品父分类
        model.addObject("goodsCate", storeyService.findParentGoodsCate());
        // 查询楼层层数信息
        model.addObject("flist", floorService.findAll());

        model.setViewName("jsp/system/storey");

        return model;
    }

    /**
     * 添加一条楼层信息
     * 
     * @param request
     * @param response
     * @param storey
     * @return ModelAndView
     */
    @RequestMapping("/addstorey")
    public ModelAndView addStorey(MultipartHttpServletRequest request, HttpServletResponse response, Storey storey) {

        // 待文件上传区
        MultipartFile file = request.getFile("netLogo");
        // 若有数据则上传文件
        if (file.getSize() != 0) {
            storey.setSeImg(UploadUtil.uploadFileOne(file, request));
        }

        // 添加楼层信息
        storeyService.insertStorey(storey);

        return new ModelAndView(new RedirectView(page));
    }

    /**
     * ajax查询 根据楼层层数Id查询storey信息
     * 
     * @param floorId
     * @return
     */
    @RequestMapping("/findfloor")
    @ResponseBody
    public Storey findFloorOne(Long floorId) {

        return storeyService.findStoreyByFloorId(floorId);
    }

    /**
     * 删除楼层信息
     * 
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping("/delstorey")
    public ModelAndView deleteStorey(HttpServletRequest request, HttpServletResponse response) {

        PrintWriter pr = null;

        try {

            pr = response.getWriter();
            pr.print(storeyService.deleteStorey(request.getParameterValues("seIds[]")));
        } catch (Exception e) {
            LOGGER.error("",e);
        } finally {
            if (pr != null) {
                pr.close();
            }
        }

        return null;
    }

    /**
     * ajax查询 根据楼层Id查询storey信息
     * 
     * @param floorId
     * @return
     */
    @RequestMapping("/findstoreyone")
    @ResponseBody
    public Storey findStoreyOne(Long seId) {

        return storeyService.findStoreyById(seId);
    }

    /**
     * 修改一条楼层信息
     * 
     * @param request
     * @param response
     * @param storey
     * @return ModelAndView
     */
    @RequestMapping("/updatestorey")
    public ModelAndView updateStorey(MultipartHttpServletRequest request, HttpServletResponse response, Storey storey) {

        // 待文件上传区
        MultipartFile file = request.getFile("netLogo");
        // 若有数据则上传文件
        if (file.getSize() != 0) {
            storey.setSeImg(UploadUtil.uploadFileOne(file, request));
        }

        // 添加楼层信息
        storeyService.updateStorey(storey);

        return new ModelAndView(new RedirectView(page));
    }

}
