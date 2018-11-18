/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.storestreet.controller;

import com.ningpai.third.storestreet.bean.StoreStreetThirdImage;
import com.ningpai.third.storestreet.service.StoreStreetService;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * zhanghailong 20150615
 */
@Controller
public class StoreStreetController {

    private static final String THIRDID = "thirdId";

    private static final String STORESTREET_HTML = "/storeStreet.html";

    @Resource(name = "storeStreetService")
    private StoreStreetService storeStreetService;

    /**
     * 查询该店铺下面的店铺街展示广告信息
     * 
     * @param request
     * @param n
     * @param l
     * @return
     */
    @RequestMapping("/storeStreetthird")
    public ModelAndView selectExpress(HttpServletRequest request, String n, String l) {
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        // 装载数据
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 根据条件获取店铺街图片
        List<Object> storeInfoImageList = this.storeStreetService.selectStoreStreetListImage((Long) request.getSession().getAttribute(THIRDID));
        resultMap.put("storestreetimage", storeInfoImageList);
        return new ModelAndView("storestreet/storestreetimage").addObject("storeInfoImageList", storeInfoImageList);
    }

    /**
     * 添加图片
     * 
     * @param request
     * @param storeStreetThirdImage
     * @return
     */
    @RequestMapping("saveStoreStreetImage")
    public ModelAndView saveStoreStreetImage(MultipartHttpServletRequest request, StoreStreetThirdImage storeStreetThirdImage) {
        MultipartFile muFile = request.getFile("imgSrc");
        // 是否上传了图片
        if (muFile.getSize() > 0) {
            // 店铺Id
            storeStreetThirdImage.setStoreId((Long) request.getSession().getAttribute(THIRDID));
            // 图片地址
            storeStreetThirdImage.setImageAddress(UploadUtil.uploadFileOne(muFile, request));
            // 创建时间
            storeStreetThirdImage.setCreatetime(new Date());
            // 保存图片
            this.storeStreetService.saveStoreStreetImage(storeStreetThirdImage);

            /*
             * infoImageManage.setThirdId((Long)
             * request.getSession().getAttribute(THIRDID));
             * infoImageManage.setImageManageUrl
             * (UploadUtil.uploadFileOne(muFile, request));
             * infoImageManage.setImageOnlineDate(new Date());
             * infoImageManageService.saveInfoImageManage(infoImageManage);
             */
        }
        return new ModelAndView(new RedirectView(request.getContextPath() + STORESTREET_HTML));
    }

    /**
     * 修改图片状态
     *
     * @return
     */
    @RequestMapping("updateStoreStreetImage")
    public ModelAndView updateStoreStreetImage(HttpServletRequest request, StoreStreetThirdImage storeStreetThirdImage) {
        this.storeStreetService.updateStoreStreetImage(storeStreetThirdImage);
        return new ModelAndView(new RedirectView(request.getContextPath() + STORESTREET_HTML));
    }

    /**
     * 修改图片信息
     * 
     * @return
     */
    @RequestMapping("updateStoremage")
    public ModelAndView updateStoremage(MultipartHttpServletRequest request, StoreStreetThirdImage storeStreetThirdImage) {
        MultipartFile muFile = request.getFile("imgSrc");
        // 图片地址
        storeStreetThirdImage.setImageAddress(UploadUtil.uploadFileOne(muFile, request));
        this.storeStreetService.updateStoreStreetImage(storeStreetThirdImage);
        return new ModelAndView(new RedirectView(request.getContextPath() + STORESTREET_HTML));
    }

    /**
     * 根据主键获取单个的图片信息
     *
     * @param imageId
     *            主键
     * @return
     */
    @RequestMapping("selectStoreStreetImageById")
    @ResponseBody
    public StoreStreetThirdImage selectStoreStreetImageById(Long imageId) {
        return this.storeStreetService.selectStoreStreetImageById(imageId);
    }

    /**
     * 批量删除
     * 
     * @param request
     * @param imageId
     * @return
     */
    @RequestMapping("delAllImage")
    public ModelAndView delAllImage(HttpServletRequest request, Long[] imageId) {
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        this.storeStreetService.updateImages(imageId, thirdId);
        return new ModelAndView(new RedirectView(request.getContextPath() + STORESTREET_HTML));
    }
}
