package com.ningpai.osgi.filter;

import com.ningpai.osgi.activator.Activator;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
/**
 * Session共享使用的Filter，
 * 放行前，将共享区的session同步到自己bundle中。
 * 放行后，将自己bundle的session同步到共享区
 * @author NP-Heh
 * 2015年6月27日 下午2:51:29
 */
public class BundleSessionFilter implements Filter {
    /**
     * 初始化
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //初始化，目前不需要做任何事情
    }

    /**
     * 过滤
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        synchSessionIn(request);
        chain.doFilter(request, response);
        synchSessionOut(request);
    }

    /**
     * 销毁
     */
    @Override
    public void destroy() {
        // 销毁时，要把session里面的内容清空
    }

    /**
     * 将共享区的session同步到自己的bundle中
     * @param request 访问请求
     */
    public void synchSessionIn(ServletRequest request) {
        HttpServletRequest bundleHttpServletRequest = (HttpServletRequest)request;
        HttpSession sharedSession = Activator.getHttpSessionService().getSession(bundleHttpServletRequest.getSession().getId());
        if(sharedSession==null) {
            return;
        }
        //将共享区的session同步过来
        @SuppressWarnings("unchecked")
        Enumeration<String> sharedSessionAttributteNames = sharedSession.getAttributeNames();
        String attributeName = null;
        while(sharedSessionAttributteNames.hasMoreElements()) {
            attributeName = sharedSessionAttributteNames.nextElement();
            bundleHttpServletRequest.getSession().setAttribute(attributeName, sharedSession.getAttribute(attributeName));
        }

        //删除已被删除的attribute
        @SuppressWarnings("unchecked")
        Enumeration<String> bundleSessionAttributteNames = bundleHttpServletRequest.getSession().getAttributeNames();
        String bundleSessionAttributeName = null;
        while(bundleSessionAttributteNames.hasMoreElements()) {
            bundleSessionAttributeName = bundleSessionAttributteNames.nextElement();
            if(sharedSession.getAttribute(bundleSessionAttributeName)==null) {
                bundleHttpServletRequest.getSession().removeAttribute(bundleSessionAttributeName);
            }
        }
    }

    /**
     * 将自己bundle中的session放入共享区
     * @param request 访问请求
     */
    public void synchSessionOut(ServletRequest request) {
        HttpServletRequest bundleHttpServletRequest = (HttpServletRequest)request;
        Activator.getHttpSessionService().setSession(bundleHttpServletRequest.getSession());
    }

}
