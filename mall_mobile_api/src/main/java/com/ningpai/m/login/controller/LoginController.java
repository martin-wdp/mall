/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.login.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.m.common.service.SeoService;
import com.ningpai.m.login.service.LoginService;
import com.ningpai.system.service.BasicSetService;

/**
 * 手机版登录Controller
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年8月19日 上午10:30:04
 * @version 0.0.1
 */
@Controller("loginControllerM")
public class LoginController {

    private static final String INDEX_HTML = "index.html";

    // spring 注解 登录service
    private LoginService loginService;

    @Resource(name = "SeoService")
    private SeoService seoService;

    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    /**
     * 跳转登录
     * 
     * @return ModelAndView
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/customer/login")
    public ModelAndView login(HttpServletRequest request, String url) throws UnsupportedEncodingException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String urlEmp = url;
        String preUrl = request.getHeader("Referer");
        if (preUrl != null) {
            String strRegex = "^((https|http|ftp|rtsp|mms)?://)"
            // ftp的user@
                    + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?"
                    // IP形式的URL- 199.194.52.184
                    // 允许IP和DOMAIN（域名）
                    + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" + "|"
                    // 域名- www.
                    + "([0-9a-z_!~*'()-]+\\.)*"
                    // 二级域名
                    + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\."
                    // first level domain- .com or .museum
                    + "[a-z]{2,6}|"
                    // 测试用 : 本地localhost.
                    + "([0-9a-z][0-9a-z-]{0,61}))"
                    // 端口- :80
                    + "(:[0-9]{1,4})?" +
                    // 项目名称
                    request.getContextPath() + "/";
            urlEmp = preUrl.replaceFirst(strRegex, "");
        } else {
            preUrl = (String) request.getSession().getAttribute("preferUrl");
            preUrl = preUrl == null ? urlEmp : preUrl;
            if (preUrl != null) {
                urlEmp = preUrl;
            } else {
                urlEmp = INDEX_HTML;
            }
        }
        if (urlEmp.indexOf("register") != -1 || urlEmp.indexOf("success") != -1 || urlEmp.length() == 0 || urlEmp.indexOf("updatesucess") != -1) {
            urlEmp = INDEX_HTML;
        }
        if (urlEmp.indexOf("validateidentity") != -1 || urlEmp.indexOf("reirectpem") != -1) {
            urlEmp = "customer/securitycenter.html";
        }
        if (urlEmp.indexOf(".html") == -1) {
            urlEmp = urlEmp + ".html";
        }
        urlEmp = "customer/index.html";
        setResultMap(request, resultMap, urlEmp);
        return seoService.getCurrSeo(new ModelAndView("/login/login").addAllObjects(resultMap));
    }

    /**
     * 退出 跳转登录
     * 
     * @return ModelAndView
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("customer/logout")
    public ModelAndView loginOut(HttpServletRequest request, String url) throws UnsupportedEncodingException {
        String urlNew =  url;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (urlNew == null) {
            urlNew = INDEX_HTML;
        }
        // 清空session已登录数据
        request.getSession().removeAttribute("cust");
        request.getSession().removeAttribute("customerId");
        request.getSession().removeAttribute("isThirdLogin");
        setResultMap(request, resultMap, urlNew);
        return seoService.getCurrSeo(new ModelAndView(new RedirectView(request.getContextPath() + "/login.html")));
    }

    /**
     * 验证登录
     * 
     * @param request
     * @param username
     *            用户名
     * @param password
     *            密码
     * @param url
     *            跳转路径
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/checklogin")
    @ResponseBody
    public Object checkLogin(HttpServletRequest request, HttpServletResponse response, String username, String password, String url, String type)
            throws UnsupportedEncodingException {
        if (username == null || password == null) {
            // 非法登录
            return -10024;
        }
        int status = loginService.checkCustomerExists(request, username, password);
        if (status == 1) {
            return url;
        } else {
            return status;
        }
    }

    /***
     * 获取注册协议
     * 
     * @return
     */
    @RequestMapping("/getXieYi")
    public ModelAndView getXieYi() {
        Map<String, Object> topmap = new HashMap<String, Object>();
        topmap.put("systembase", basicSetService.findBasicSet());
        return new ModelAndView("/login/xieyi").addObject("topmap", topmap);
    }

    /**
     * 设置返回MAP
     * 
     * @param request
     * @param resultMap
     * @param url
     * @throws UnsupportedEncodingException
     */
    public void setResultMap(HttpServletRequest request, Map<String, Object> resultMap, String url) throws UnsupportedEncodingException {
        String username = "";
        // 读取cookie中的信息
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie != null && "_npstore_username".equals(cookie.getName())) {
                    username = URLDecoder.decode(cookie.getValue(), ConstantUtil.UTF);
                    break;
                }
            }
        }
        resultMap.put("username", username);
        resultMap.put("url", url);
    }

    public LoginService getLoginService() {
        return loginService;
    }

    @Resource(name = "loginServiceM")
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
