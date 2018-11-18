/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.mobile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 控制器-移动版首页设置
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月21日下午2:15:43
 */
@Controller
public class MobMainController {
    /**
     * 设置移动版首页
     * 
     * @return
     */
    @RequestMapping("/setMobMain")
    public ModelAndView setMobMain() {
        return new ModelAndView("jsp/mobile/set_mob_main");
    }
}
