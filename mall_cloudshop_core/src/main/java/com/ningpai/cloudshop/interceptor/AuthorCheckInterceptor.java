package com.ningpai.cloudshop.interceptor;

import com.ningpai.cloudshop.util.OpenTokenUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证账户授权信息拦截器 Created by liangck on 15/6/27.
 */
public class AuthorCheckInterceptor extends HandlerInterceptorAdapter {

    /** 需要验证的url **/
    private String[] unCheckOperate = { "/getInventoryItems.htm",
            "/getOnsaleItems.htm" };

    /* tokenUtil */
    @Resource(name = "openTokenUtil")
    private OpenTokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        super.preHandle(request, response, handler);
        // 获取当前请求路径
        String requestUri = request.getRequestURI();
        for (String uri : unCheckOperate) {
            if (requestUri.indexOf(uri) > 0) {
                if (!tokenUtil.checkAccessToken()) {
                    response.sendRedirect(request.getContextPath()
                            + "/noAuthorPage.htm");
                    return false;
                }
                return true;
            }
        }
        return true;
    }

}
