/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.third.templet.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.information.bean.InformationType;
import com.ningpai.information.service.ThirdInforTypeService;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.ThirdTempService;
import com.ningpai.third.seller.bean.StoreInfo;
import com.ningpai.third.seller.service.SellerService;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.third.util.SellerConstants;
import com.ningpai.third.util.StringCommonUtil;
import com.ningpai.util.UploadUtil;

/**
 * 控制器-模板
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月4日下午4:03:06
 */
@Controller
public class ThirdTempController {

    /* 业务逻辑层依赖 */
    @Resource(name = "ThirdTempService")
    private ThirdTempService tempService;

    @Resource(name = "ThirdInforTypeService")
    private ThirdInforTypeService thirdInforTypeService;

    @Resource(name = "sellerService")
    private SellerService sellerService;

    private static final String TEMP = "temp";

    /**
     * 获取首页Json数据测试
     * 
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkThirdIndex")
    public boolean checkThirdIndex(HttpServletRequest request) {
        // 根据商家ID查询店铺信息
        StoreInfo storeInfo = sellerService.selectByCustomerId((Long) request.getSession().getAttribute("customerId"));
        if (storeInfo == null) {
            // 根据该店铺的员工ID查询店铺信息
            storeInfo = sellerService.selectByEmployeeId((Long) request.getSession().getAttribute("customerId"));
        }

        return "0".equals(storeInfo.getIsStoreIndex()) ? false : true;
    }

    /**
     * 修改第三方店铺的首页启用状态
     * 
     * @param storeInfo
     *            第三方店铺信息
     * @param multipartRequest
     * @param request
     * @return
     */
    @RequestMapping("/updateStoreIndex")
    public ModelAndView updateStoreIndex(StoreInfo storeInfo) {
        sellerService.updateStoreIndexState(storeInfo);
        return new ModelAndView(new RedirectView("sellerinfo.html?n=2&l=17"));
    }

    /**
     * 按第三方ID和模板类型查询模板信息
     * 
     * @param thirdId
     * @return
     */
    @RequestMapping("/queryThirdTempByType")
    public ModelAndView queryTempByType(HttpServletRequest request, String n, String l) {
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        List<SysTemp> indexList = tempService.querySystempByType(137L);
        return new ModelAndView("/temp/temp_list", "indexList", indexList).addObject("info",
                sellerService.selectByCustomerId((Long) request.getSession().getAttribute(SellerConstants.CUSTOMERID)));
    }

    /**
     * 查看模板信息
     * 
     * @param tempId
     * @return
     */
    @RequestMapping("/showThirdTempInfo")
    public ModelAndView showTempInfo(Long tempId) {
        return new ModelAndView("temp/show_temp", TEMP, tempService.getSystempById(tempId));
    }

    /**
     * 跳到修改模板信息页面
     * 
     * @param tempId
     * @return
     */
    @RequestMapping("/toUpdateThirdTempInfo")
    public ModelAndView toUpdateTempInfo(Long tempId) {
        return new ModelAndView("temp/update_temp", TEMP, tempService.getSystempById(tempId));
    }

    /**
     * 修改模板信息
     * 
     * @param temp
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/updateThirdTempInfo")
    public ModelAndView updateTempInfo(MultipartHttpServletRequest request, SysTemp temp) throws UnsupportedEncodingException {
        MultipartFile muFile = request.getFile("imgSrc");
        if (muFile.getSize() > 0) {
            temp.setTempImageUrl(UploadUtil.uploadFileOne(muFile, request));
        }
        temp.setTempName(StringCommonUtil.charsetConversion(temp.getTempName(), StringCommonUtil.ISO88591, StringCommonUtil.UTF8));
        temp.setDes(StringCommonUtil.charsetConversion(temp.getDes(), StringCommonUtil.ISO88591, StringCommonUtil.UTF8));
        temp.setVersion(StringCommonUtil.charsetConversion(temp.getVersion(), StringCommonUtil.ISO88591, StringCommonUtil.UTF8));
        this.tempService.updateSystemp(temp);
        return new ModelAndView(new RedirectView("queryThirdTempByType.htm"));
    }

    /**
     * 跳到修改模板新闻公告页面
     * 
     * @param tempId
     * @return
     */
    @RequestMapping("/toUpdateThirdTempInfoType")
    public ModelAndView toUpdateThirdTempInfoType(HttpServletRequest request, Long tempId) {
        // 获取第三方商家ID
        String thirdId = ((Long) request.getSession().getAttribute("thirdId")).toString();
        List<InformationType> infoTypes = thirdInforTypeService.selectInfoTypeByAttr(thirdId);
        return new ModelAndView("temp/temp_news", TEMP, tempService.getSystempById(tempId)).addObject("infoTypes", infoTypes);
    }

    /**
     * 修改模板新闻公告
     * 
     * @param temp
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/updateThirdTempInfoType")
    public ModelAndView updateTempInfoType(SysTemp temp, HttpServletRequest request) throws UnsupportedEncodingException {

        temp.setExpFleid2(StringCommonUtil.charsetConversion(temp.getExpFleid2(), StringCommonUtil.ISO88591, StringCommonUtil.UTF8));
        this.tempService.updateSystemp(temp);
        return new ModelAndView(new RedirectView("toUpdateThirdTempInfoType.htm?tempId=" + temp.getTempId()));
    }

    /**
     * 配置首页模板内容
     * 
     * @param temp
     * @return
     */
    @RequestMapping("/setThirdTemp")
    public ModelAndView setTemp(Long tempId) {
        return new ModelAndView("temp/temp_nav_list", TEMP, tempService.getSystempById(tempId));
    }

}
