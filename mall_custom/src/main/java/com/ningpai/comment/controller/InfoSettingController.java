/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.comment.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.comment.bean.Infosetting;
import com.ningpai.comment.service.InfoSettingServiceMapper;

/**
 * 消息设置
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月31日 下午6:24:44
 * @version 0.0.1
 */
@Controller("infoSettingController")
public class InfoSettingController {

    // spring注解
    private InfoSettingServiceMapper infoSettingServiceMapper;

    /**
     * 设置消息参数
     * 
     * @return
     */
    @RequestMapping("/updateInfoSetting")
    public ModelAndView updateInfoSetting(Infosetting infosetting,String location) {
        // 执行修改设置
        infoSettingServiceMapper.updateInfoSetting(infosetting);
        return new ModelAndView(new RedirectView("initInfoSetting.htm?location="+location));
    }

    /**
     * 设置消息参数
     * 
     * @return
     */
    @RequestMapping("/initInfoSetting")
    public ModelAndView initInfoSetting(String location) {
        return new ModelAndView("jsp/customer/infosetting").addObject("infoSetting", infoSettingServiceMapper.selectInfoSetting()).addObject("location",location);
    }

    public InfoSettingServiceMapper getInfoSettingServiceMapper() {
        return infoSettingServiceMapper;
    }

    @Resource(name = "infoSettingServiceMapper")
    public void setInfoSettingServiceMapper(InfoSettingServiceMapper infoSettingServiceMapper) {
        this.infoSettingServiceMapper = infoSettingServiceMapper;
    }

}
