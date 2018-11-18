/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.sld.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.site.sld.bean.DomainCustom;
import com.ningpai.site.sld.mapper.DomainCustomMapper;

/**
 * 涉及到为用户提供一个二级域名功能，实现方法如下：<br>
 * (1)首先要在域名服务商端做一个泛域名解析. 我用的是godaddy.com,就新建一个A记录(host=*,point
 * to指向我的服务器的ip),这样所有二级域名都会转到我的服务器来了<br>
 * (2)接着在web.xml配一个自已写的域名过滤器, 过滤器的代码如下。这里先将二级域名和全球域名和用户ID的映射,保存在一个数据表里,
 * 然后访问进来时对地址做一个判断, 再取出对应的用户ID. 再转一下就行了. 比如: http://zqq.ningpai.com/index
 * 用zqq对应用户ID为6,则访问效果有 http://www.ningpai.com/zqq/index相同,
 * 不过地址栏还是显示http://zqq.ningpai.com/index。
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年10月20日 上午11:05:40
 * @version 0.0.1
 */
public class URLFilter implements Filter {

    private static final String DOMAIN_END = "." + Constants.DOMAIN; // .you.com

    private static final Map<String, Object> NAME_MAP = new HashMap<String, Object>();

    // private static final Map<String, Object> DOMAIN_MAP = new HashMap<String,
    // Object>();

    private DomainCustomMapper domainCustomMapper;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        NAME_MAP.put("demo", "view/brand_list.html");
        NAME_MAP.put("shop", "view/help_list.html");
        // domainCustomMapper = new DomainCustomMapperImpl();
        domainCustomMapper = (DomainCustomMapper) WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext()).getBean("domainCustomMapper");
        List<DomainCustom> dcList = domainCustomMapper.findAll();

        for (DomainCustom dc : dcList) {
            String name = dc.getDomain();
            if (StringUtils.isNotEmpty(name)) {
                NAME_MAP.put(name, dc.getCustomerId());
            }
        }
    }
    /**
     * 回收
     * */
    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(ConstantUtil.UTF);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        String serverName = request.getServerName().toLowerCase();
        String realURI = getRealRequestURI(serverName, requestURI);
        request.getRequestDispatcher(realURI).forward(request, response);
    }

    private String getRealRequestURI(String serverName, String requestURI) {
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
                throw new RuntimeException("do not exist second domain: " + secondDomain);
            }
            return getURI(siteId + "", requestURI);
        }

        return requestURI;
    }

    private static String getURI(String siteId, String requestURI) {
        // if (requestURI.equals("/")) {
        // return "/" + siteId;
        // } else {
        // return "/" + siteId + requestURI+".htm";
        // }
        if (requestURI.endsWith(".html") || requestURI.endsWith(".htm")) {
            if (requestURI.indexOf("?") == -1) {
                return requestURI + "?cid=" + siteId;
            } else {
                return requestURI + "&cid=" + siteId;
            }
        }
        return requestURI;
    }

    public DomainCustomMapper getDomainCustomMapper() {
        return domainCustomMapper;
    }

    // @Resource(name = "domainCustomMapper")
    public void setDomainCustomMapper(DomainCustomMapper domainCustomMapper) {
        this.domainCustomMapper = domainCustomMapper;
    }

}
