package com.ningpai.api.controller;

import com.ningpai.api.util.OpenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;


/**
 * @author lih
 * @version 2.0
 * @since 15/9/1
 */
@Controller
public class InitContorller {
    /**
     * 初始key
     *
     * @return 返回页面
     */
    @RequestMapping(value = "init", produces = OpenUtil.PRODUCESSTR)
    public ModelAndView initKey() {
        // 初始化秘药
        return new ModelAndView("init.jsp");

    }

}
