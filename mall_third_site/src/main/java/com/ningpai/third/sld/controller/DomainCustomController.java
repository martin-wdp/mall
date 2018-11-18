/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.sld.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.third.sld.bean.DomainCustom;
import com.ningpai.third.sld.service.impl.DomainCustomServiceImpl;

/**
 * 二级域名关联控制器
 * 
 * @author jiping
 * @since 2014年12月4日 上午10:11:10
 * @version 0.0.1
 */
@Controller
public class DomainCustomController {
    /**
     * spring注解
     */
    private DomainCustomServiceImpl domainCustomService;

    /**
     * 二级域名关联service实现类GET方法
     * @return
     */
    public DomainCustomServiceImpl getDomainCustomService() {
        return domainCustomService;
    }

    /**
     * 二级域名关联service实现类SET方法
     * @param domainCustomService
     */
    @Resource(name = "domainCustomService")
    public void setDomainCustomService(
            DomainCustomServiceImpl domainCustomService) {
        this.domainCustomService = domainCustomService;
    }

    /**
     * 显示第三方泛域名
     * 
     * @return
     */
    @RequestMapping("/showdomainbycustid")
    public ModelAndView getDomainCustomByCustId(Long thirdId) {
        return new ModelAndView("seller/domain").addObject("domainCustom",
                domainCustomService.findDomainCustom(thirdId));
    }

    /**
     * 显示第三方泛域名
     * @param request
     * @param domainCustom
     * @return
     */
    @RequestMapping("/updatedomain")
    public ModelAndView updateDomain(HttpServletRequest request,
            DomainCustom domainCustom) {
//        Long thirdId = (Long) request.getSession().getAttribute("thirdId");
        domainCustomService.updateDomain(domainCustom);
        return new ModelAndView(new RedirectView("gofuncsetview.htm"));
    }

    /**
     * 检查二级域名名称是否存在
     * 
     * @return 1 存在 0 不存在
     * @throws IOException
     */
    @RequestMapping("/checkexistdomain")
    public ModelAndView checkExistDomain(String domain,
            HttpServletResponse response) throws IOException {
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
            // top域名
            if (!"top".equals(domain)) {
                /**
                 * 检查二级域名名称是否存在
                 */
                pr.print(domainCustomService.queryByDomain(domain));
            }
        } finally {
            pr = null;
        }
        return null;
    }
}
