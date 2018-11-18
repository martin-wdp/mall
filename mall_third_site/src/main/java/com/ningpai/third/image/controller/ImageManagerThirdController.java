/*
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.image.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.imagemanage.bean.InfoImageManage;
import com.ningpai.imagemanage.service.InfoImageClassifyService;
import com.ningpai.imagemanage.service.InfoImageManageService;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.uploadfileset.bean.UploadFileSet;
import com.ningpai.uploadfileset.service.UploadFileSetService;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * <p>
 * 商家图片
 * </p>
 * 
 * @author zhanghl
 * @since 2014年5月23日19:37:36
 * @version 2.0
 */
@Controller
public class ImageManagerThirdController {

    private static final Logger LOGGER = Logger.getLogger(ImageManagerThirdController.class);

    private static final String THIRDID = "thirdId";

    @Resource(name = "UploadFileSetService")
    private UploadFileSetService uploadFileSetService;

    @Resource(name = "InfoImageClassifyService")
     public InfoImageClassifyService infoImageClassifyService;

    @Resource(name = "InfoImageManageService")
    private InfoImageManageService infoImageManageService;

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    /**
     * 根据分类，分页查询图片信息，以供选择
     * 
     * @param pb
     * @param classifyId
     *            图片类型编号
     * @return
     */
    @RequestMapping("/queryImageManageByChoose")
    public ModelAndView queryImageByPbAndCidForChoose(HttpServletRequest request, PageBean pb, Long classifyId, Integer size) {
        Integer sizeNew = size;
        LOGGER.debug("======根据分类，分页查询图片信息======");
        ModelAndView mav = new ModelAndView();
        // 查询所有图片管理分类
      //  mav.addObject("classifyList", infoImageClassifyService.selectAllImageClassify());
        if (null == sizeNew) {
            sizeNew = 1;
        }
        mav.addObject("size", sizeNew);
        // 获取当前的上传文件设置
        UploadFileSet ufs = this.uploadFileSetService.getCurrUploadFileSet();
        mav.addObject("ufs", ufs);
        // 设置要访问的页面路径
        mav.setViewName("image/chooseImage");
        return mav;
    }

    /**
     * 图片管理
     * 
     * @param pb
     *            分页对象
     * @param request
     * @param n
     *            导航栏头部索引
     * @param l
     *            导航栏左部索引
     * @return
     */
    @RequestMapping("third/imageListManager")
    public ModelAndView imageListManager(PageBean pb, HttpServletRequest request, String n, String l) {
        String nNew = n;
        String lNew = l;
        // 新增图片后 N和L会为空
        if (nNew == null || lNew == null) {
            nNew = "2";
            lNew = "95";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        // 商家ID
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        // 根据第三方ID获取图片信息
        map.put("pb", infoImageManageService.selectImageByThirdId(pb, thirdId));
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, nNew, lNew);
        // 设置请求的路径
        pb.setUrl("imageListManager.html?n=" + nNew + "&l=" + lNew);
        // 设置要跳转的页面
        return new ModelAndView("image/imagelist").addObject("map", map);
    }

    /**
     * 添加图片
     * 
     * @param request
     * @param infoImageManage
     *            实体类-资源图片信息
     * @return
     */
    @RequestMapping("saveImageAction")
    public ModelAndView saveImageAction(MultipartHttpServletRequest request, InfoImageManage infoImageManage) {
        // 上传的图片信息
        MultipartFile muFile = request.getFile("imgSrc");
        if (muFile.getSize() > 0) {
            // 商家ID
            infoImageManage.setThirdId((Long) request.getSession().getAttribute(THIRDID));
            // 图片的路径
            infoImageManage.setImageManageUrl(UploadUtil.uploadFileOne(muFile, request));
            // 上传时间
            infoImageManage.setImageOnlineDate(new Date());
            // 保存图片
            infoImageManageService.saveInfoImageManage(infoImageManage);
        }
        // 重定向到 图片列表
        return new ModelAndView(new RedirectView(request.getContextPath() + "/imageListManager.html"));
    }

    /**
     * 单个删除
     * 
     * @param imageManageId
     *            图片信息编号
     * @param request
     * @return
     */
    @RequestMapping("updateImage")
    @ResponseBody
    public int updateImage(Long imageManageId, HttpServletRequest request) {
        // 商家ID
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        // 根基图片信息编号 删除单个的图片信息
        return infoImageManageService.updateImage(imageManageId, thirdId);
    }

    /**
     * 批量删除
     * 
     * @param request
     * @param imageManageIds
     *            要删除的图片信息编号
     * @return
     */
    @RequestMapping("updateImages")
    public ModelAndView updateImages(HttpServletRequest request, Long[] imageManageIds) {
        // 商家ID
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        // 批量删除图片信息
        infoImageManageService.updateImages(imageManageIds, thirdId);
        return new ModelAndView(new RedirectView(request.getContextPath() + "/imageListManager.html"));
    }

    /**
     * Ajax查询图片库，用于选取
     * 
     * @param pb
     * @param classifyId
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajaxQueryImageForChooseAndThirdId")
    public PageBean ajaxQueryImageForChoose(HttpServletRequest request, PageBean pb, Long classifyId) {
        LOGGER.debug("======根据分类，分页查询图片信息======");
        // 商家ID
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        if (null != classifyId && classifyId >= 0) {
            // 根据分类和第三方Id查询图片
            return infoImageManageService.selectImageManageByParam(pb, classifyId, thirdId);
        } else {
            // 根据分类和第三方Id查询图片
            return infoImageManageService.selectImageManageByParam(pb, null, thirdId);
        }
    }
}
