/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.system.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.system.bean.WebCert;
import com.ningpai.system.service.WebCertService;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 控制器-网站认证
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年6月5日下午4:59:47
 */
@Controller
public class WebCertController {

    private static final String REDIRECT = "queryWebCertByPageBean.htm";

    /** 网站认证业务接口 */
    @Resource(name = "WebCertService")
    private WebCertService webCertService;

    /**
     * 分页查看网站认证
     * 
     * @param pb
     * @return
     */
    @RequestMapping("/queryWebCertByPageBean")
    public ModelAndView queryWebCertByPageBean(PageBean pb) {

        return new ModelAndView("jsp/system/webcert_list", "pb", this.webCertService.selectAllByPb(pb));
    }

    /**
     * 添加网站认证
     * 
     * @param request
     * @param webCert
     * @return
     */
    @RequestMapping("/createWebCert")
    public ModelAndView createWebCert(MultipartHttpServletRequest request, WebCert webCert) {
        MultipartFile muFile = request.getFile("imageSrc");
        if (muFile.getSize() > 0) {
            webCert.setImgsrc(UploadUtil.uploadFileOne(muFile, request));
        }

        this.webCertService.saveWebCert(webCert);
        return new ModelAndView(new RedirectView(REDIRECT));
    }

    /**
     * 修改网站认证
     * 
     * @param request
     * @param webCert
     * @return
     */
    @RequestMapping("/updateWebCert")
    public ModelAndView updateWebCert(MultipartHttpServletRequest request, WebCert webCert) {
        MultipartFile muFile = request.getFile("imageSrc");
        if (muFile.getSize() > 0) {
            webCert.setImgsrc(UploadUtil.uploadFileOne(muFile, request));
        }

        this.webCertService.updateWebCert(webCert);
        return new ModelAndView(new RedirectView(REDIRECT));
    }

    /**
     * 删除网站认证
     * 
     * @param certificationId
     * @return
     */
    @RequestMapping("/deleteWebCert")
    public ModelAndView deleteWebCert(Long certificationId) {
        this.webCertService.deleteWebCert(certificationId);
        return new ModelAndView(new RedirectView(REDIRECT));
    }
}
