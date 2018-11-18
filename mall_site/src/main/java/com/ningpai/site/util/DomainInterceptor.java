/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.util;

import com.ningpai.site.sld.bean.DomainCustom;
import com.ningpai.site.sld.filter.Constants;
import com.ningpai.site.sld.service.impl.DomainCustomServiceImpl;
import com.ningpai.system.service.impl.BasicSetServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 泛域名拦截器
 *
 * @author jiping
 * @version 0.0.1
 * @since 2014年12月22日 下午2:59:29
 */
public class DomainInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = Logger.getLogger(DomainInterceptor.class);

    // spring注解
    @Resource(name = "domainCustomService")
    private DomainCustomServiceImpl domainCustomService;

    @Resource(name = "basicSetService")
    private BasicSetServiceImpl basicSetService;

    private List<String> noFilterURLs;

    private static String DOMAIN_END = "." + Constants.DOMAIN; // .you.com

    private static Map<String, Object> NAME_MAP = new HashMap<String, Object>();
    //map初始化标记
    private AtomicBoolean isInit = new AtomicBoolean(false);

    /**
     * 加载店铺域名数据
     */
    public void initDomain() {
        NAME_MAP.clear();
        Constants.DOMAIN = basicSetService.findBasicSet().getBsetDomain();
        DOMAIN_END = "." + Constants.DOMAIN;
        Constants.WWW_DOMAIN = "www." + Constants.DOMAIN;
        List<DomainCustom> dcList = domainCustomService.findAll();
        for (DomainCustom dc : dcList) {
            String name = dc.getDomain();
            if (StringUtils.isNotEmpty(name)) {
                NAME_MAP.put(name, dc.getCustomerId());
            }
        }
        //初始化后,将标记设置为true
        isInit.compareAndSet(false,true);
    }

    /**
     * 在请求处理前拦截URL 进行相应处理
     */
    @Override
    public boolean preHandle(HttpServletRequest requests, HttpServletResponse response, Object handler) {
        // 获取请求路径
        String requestURI = requests.getRequestURI();
        // 如果是跳转后的地址，就不进行拦截
        if (requestURI.indexOf("indexThirdView") != -1) {
            return true;
        }
        //如果初始化标记为true,则不在进行初始化
        if(isInit.get()){
            initDomain();
        }
        // 获取域名
        // String serverName = requests.getServerName().toLowerCase();
        // String realURI = getRealRequestURI(serverName, requestURI);

        boolean isFilter = true;
        // boolean hasAuty=false;
        // 判断请求路径是否需要拦截
        for (String url : noFilterURLs) {
            if (requestURI.indexOf(url) != -1) {
                isFilter = false;
                break;
            }
        }

        if (isFilter) {
            /*
             * if(realURI.equals("message")){ dispacherPath(response); return
             * false; }else{ // 使用serverName 查询数据库查询出thirdId Long thirdId =
             * null; try { if (null == serverName || "".equals(serverName))
             * return false; thirdId = (Long)
             * NAME_MAP.get(serverName.substring(0,serverName.indexOf(".")));
             * //查询系统里 站点地址 String webSiteUrl = "";
             * 
             * requests.getRequestDispatcher( webSiteUrl + "indexThirdView.htm"
             * + "?thirdId=" + thirdId).forward(requests, response); return
             * false; } catch (Exception e) { System.out.println("泛域名拦截出现异常"); }
             * }
             */
        } else {
            return true;
        }
        return true;
    }

    public String getRealRequestURI(String serverName, String requestURI) {
        if (Constants.WWW_DOMAIN.equals(serverName) || requestURI.startsWith("/static/") || Constants.DOMAIN.equals(serverName)) {
            return requestURI;
        }
        if (serverName.endsWith(DOMAIN_END)) {
            String secondDomain = serverName.substring(0, serverName.indexOf("."));
            // 网站id
            if (NumberUtils.isNumber(secondDomain)) {
                return getURI(secondDomain, requestURI);
            }
            // 网站英文名
            Object siteId = NAME_MAP.get(secondDomain);
            if (siteId == null) {
                // 保留的二级域名
                if (Constants.isPrivateSecondDomain(secondDomain)) {
                    return requestURI;
                }
                // return "/message?msg=不存在二级域名" + secondDomain;
                return "message";
                // throw new RuntimeException("do not exist second domain: " +
                // secondDomain);
            }
            return getURI(siteId + "", requestURI);
        }

        return requestURI;
    }

    private static String getURI(String siteId, String requestURI) {
        if (requestURI.endsWith(".html") || requestURI.endsWith(".htm")) {
            if (requestURI.indexOf("?") == -1) {
                return requestURI + "?cid=" + siteId;
            } else {
                return requestURI + "&cid=" + siteId;
            }
        }
        return requestURI;
    }

    /**
     * 域名不存在时，调用此方法
     */
    public String dispacherPath(ServletResponse response) {
        StringBuilder builderHead = new StringBuilder();
        StringBuilder builderTip = new StringBuilder();
        StringBuilder builderFoot = new StringBuilder();
        builderHead.append("<html lang='en'>");
        builderHead.append("<head> <meta charset='UTF-8'><title>域名不存在</title>" + "<link rel='stylesheet' type='text/css' href='css/base.min.css'/>");
        builderHead.append("<style type='text/css'>body{background: #dfedfa;min-height: 800px;}.yunduo{width:445px; margin:0px auto; background: url"
                + "(images/yunduo.png) no-repeat; height:440px; text-align: right; padding-right: 94px;}.yunduo h2{ font-family: '微软雅黑'; font-size: "
                + "24px; color: #333333; padding-top: 65px;}.yunduo p{ font-size: 12px; color: #666; margin-top: 28px;}.yunduo a{ display:inline-block;"
                + " zoom:1; width: 120px; height: 32px; background: #8fc9ff; border-radius: 3px; color: #fff; line-height: 32px; text-align: center; "
                + "font-size: 12px; margin-top: 10px;}.yunduo a:hover{ text-decoration: none;}");
        builderHead.append("</style></head><body>");
        builderFoot.append("</body></html>");

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out;

        try {
            out = response.getWriter();
            builderTip.append("<div style='padding-top:225px;'>");
            builderTip.append("<div class='yunduo'>");
            builderTip.append("<h2>您访问的域名不存在</h2>");
            builderTip.append("<p>不记得域名？试试统一登录入口</p>");
            builderTip.append("<a href='http://qpmall.qianmi.com/login.html'>统一登录入口</a>");
            builderTip.append("</div></div>");
            out.print(builderHead.append(builderTip).append(builderFoot).toString());
        } catch (IOException e) {
            LOGGER.error("PrintWriter 异常！", e);
        }

        return null;
    }

    public List<String> getNoFilterURLs() {
        return noFilterURLs;
    }

    public void setNoFilterURLs(List<String> noFilterURLs) {
        this.noFilterURLs = noFilterURLs;
    }
}
