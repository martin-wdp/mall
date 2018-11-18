/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.m.common.service;

import org.springframework.web.servlet.ModelAndView;

/**
 * SERVICE-SEO
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月25日下午3:10:34
 */
public interface SeoService {
    /**
     * 获取当前的seo信息<br/>
     * ModelAndView中保存的seo信息对象名称为seo
     * 
     * @param mav
     * @return
     */
    ModelAndView getCurrSeo(ModelAndView mav);
}
