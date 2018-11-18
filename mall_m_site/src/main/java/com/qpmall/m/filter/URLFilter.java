package com.qpmall.m.filter;

import com.ningpai.common.util.ConstantUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2016/1/16.
 */
public class URLFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

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
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        if(requestURI.endsWith(".html") ||  requestURI.endsWith(".htm") || requestURI.endsWith(contextPath+"/")){
            String client_type = request.getParameter("at");
            HttpSession session = request.getSession();
            if(client_type !=null && !"".equals(client_type)){
                session.setAttribute("at", client_type);
            }
        }
        filterChain.doFilter(request, response);

    }
}
