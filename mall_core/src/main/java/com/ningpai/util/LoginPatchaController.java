/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 后台登录验证码显示开关控制器
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年7月17日上午10:35:14
 */
@Controller
public class LoginPatchaController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(LoginPatchaController.class);

    /**
     * 显示验证码显示开关
     * 
     * @return
     */
    @RequestMapping("/showLoginPatcha")
    public ModelAndView showLoginPatcha(HttpServletRequest request) {
        return new ModelAndView("jsp/system/setloginPatcha", "showpatcha", LoginPatchaUtil.getSHOWPATCHA()).addObject("msg", request.getParameter("msg"));
    }

    /**
     * 修改验证码显示开关
     * 
     * @param showpatcha
     * @return
     */
    @RequestMapping("/updateLoginPatcha")
    public ModelAndView updateLoginPatcha(int showpatcha) {
        LoginPatchaUtil.setSHOWPATCHA(showpatcha);
        return new ModelAndView(new RedirectView("showLoginPatcha.htm"), "msg", "操作成功！");
    }

    /**
     * AJAX获取验证码显示开关
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("/getLoginPatcha")
    public Integer getLoginPatcha() {
        return LoginPatchaUtil.getSHOWPATCHA();
    }

    /**
     * 修改验证码显示开关
     * 
     * @param showpatcha
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateLoginPatchaAjax")
    public Map<String, Object> updateLoginPatchaAjax(int showpatcha, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            LoginPatchaUtil.setSHOWPATCHA(showpatcha);
            map.put("showpatcha", showpatcha);
            map.put("msg", "操作成功！");
        } catch (Exception e) {
            map.put("msg", "操作失败！");
            LOGGER.error("修改登录验证码开关设置失败:=>" + e.getLocalizedMessage());
        }
        return map;
    }
}
