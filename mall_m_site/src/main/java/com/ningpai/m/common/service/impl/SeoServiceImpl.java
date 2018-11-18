/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.m.common.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.m.common.service.SeoService;
import com.ningpai.system.bean.SeoConf;
import com.ningpai.system.mobile.service.MobSiteBasicService;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.system.service.ISeoConfBiz;

/**
 * SERVICE实现类-SEO
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月25日下午3:13:17
 */
@Service("SeoService")
public class SeoServiceImpl implements SeoService {

    /** 站点SEO业务接口 */
    @Resource(name = "seoConfBizImpl")
    private ISeoConfBiz seoconfbiz;

    /** 站点信息业务接口 */
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    /** 移动版站点设置 */
    @Resource(name = "MobSiteBasicService")
    private MobSiteBasicService mobSiteBasicService;

    /*
     * 
     * 
     * @see
     * com.ningpai.m.common.service.SeoService#getCurrSeo(org.springframework
     * .web.servlet.ModelAndView)
     */
    @Override
    public ModelAndView getCurrSeo(ModelAndView mav) {
        mav.addObject("seo", getSeo());
        mav.addObject("sys", basicSetService.findBasicSet());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        mav.addObject("mobSiteBasic", mobSiteBasicService.selectCurrMobSiteBasic(request));
        return mav;
    }

    /**
     * 获取SEO信息
     * 
     * @return
     */
    private SeoConf getSeo() {
        SeoConf seo = seoconfbiz.querySeoByUsedStatus();
        if (null == seo) {
            seo = new SeoConf();
            String bsetName = basicSetService.findBasicSet().getBsetName();
            if (null != bsetName && (!"".equals(bsetName))) {
                seo.setMete(bsetName);
            } else {
                seo.setMete("");
            }
            seo.setMeteKey("");
            seo.setMeteDes("");
        }
        return seo;
    }
}
