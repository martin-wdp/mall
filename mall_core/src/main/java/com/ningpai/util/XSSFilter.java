package com.ningpai.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 拦截器
 * */
public class XSSFilter implements Filter {
    /**
     * 重写父类方法
     * */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    /**
     * 重写父类方法
     * */
    @Override
    public void destroy() {
    }
    /**
     * 重写父类方法
     * */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        /*
         * //设置request字符编码 request.setCharacterEncoding("UTF-8");
         * //设置response字符编码 response.setContentType("text/html;charset=UTF-8");
         */

        chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request),
                response);

    }

}