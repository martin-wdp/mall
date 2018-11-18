/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.m.information.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.information.bean.Information;
import com.ningpai.information.service.InformationService;
import com.ningpai.m.common.service.SeoService;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.util.PageBean;

/**
 * 控制器-移动版文章
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月4日上午10:24:21
 */
@Controller
public class MobInfoSiteController {

    @Resource(name = "InformationService")
    private InformationService infoService;

    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    @Resource(name = "SeoService")
    private SeoService seoService;

    /**
     * 分页查询移动版文章列表
     * 
     * @param pb
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/queryMobInfoList")
    public ModelAndView queryMobInfoList(PageBean pb, Integer pageNo) {
        if (null != pageNo) {
            pb.setPageNo(pageNo);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("pb", infoService.queryByPageBeanForMobSite(pb, null, 2L));
        mav.addObject("sys", basicSetService.findBasicSet());
        mav.setViewName("information/articles_list");
        return seoService.getCurrSeo(mav);
    }

    /**
     * 显示文章
     * 
     * @param infoId
     * @return
     */
    @RequestMapping("/showMobInfo")
    public ModelAndView showMobInfo(Long infoId) {
        ModelAndView mav = new ModelAndView();
        Information info = new Information();
        if (infoId != null) {
            info = infoService.selectByPrimaryKey(infoId);
            if (null != info) {
                info.setHits(info.getHits() + 1);
                this.infoService.updateInfo(info);
                mav.addObject("info", info);
            } else {
                return new ModelAndView(new RedirectView("list/"));
            }
        }
        mav.addObject("sys", basicSetService.findBasicSet());
        mav.setViewName("information/article_details");
        return seoService.getCurrSeo(mav);
    }
}
