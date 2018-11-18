/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.seller.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.third.seller.bean.StoreContact;
import com.ningpai.third.seller.service.StroreContactService;

/**
 * 联系人信息控制
 * 
 * @author Zhouh
 * @since 2014年5月20日 下午6:07:38
 * @version 0.0.1
 * 
 */
@Controller
public class StroreContactController {

    private StroreContactService stroreContactService;

    /**
     * 
     * 查询联系人信息添加
     * 
     * @param request
     * @param storeContact
     * @return
     */
    @RequestMapping("third/insertstore")
    public ModelAndView addStore(HttpServletRequest request, StoreContact storeContact) {
        stroreContactService.insertStoreSelective(request, storeContact);

        return new ModelAndView(new RedirectView(request.getContextPath() + "/sellerinfo.html"));
    }

    /**
     * 查询联系人信息修改
     * 
     * @param request
     * @param storeContact
     * @return
     */
    @RequestMapping("third/updatestore")
    public ModelAndView updateSeller(HttpServletRequest request, StoreContact storeContact) {
        stroreContactService.updateByPrimaryKeySelective(storeContact);

        return new ModelAndView(new RedirectView(request.getContextPath() + "/sellerinfo.html"));
    }

    public StroreContactService getStroreContactService() {
        return stroreContactService;
    }

    @Resource(name = "StroreContactService")
    public void setStroreContactService(StroreContactService stroreContactService) {
        this.stroreContactService = stroreContactService;
    }
}
