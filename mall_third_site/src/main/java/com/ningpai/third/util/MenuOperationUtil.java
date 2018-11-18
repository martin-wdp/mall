/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于页面控制css选中
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年5月12日 下午6:23:24
 * @version 0.0.1
 */
public final class MenuOperationUtil {

    /**
     * 构造方法
     */
    private MenuOperationUtil() {
        super();
    }

    /**
     * 填充导航/左侧菜单索引 用于页面控制css选中
     * 
     * @param request
     * @param n
     *            导航父ID
     * @param l
     *            左侧菜单子ID
     */
    public static void fillSessionMenuIndex(HttpServletRequest request, String n, String l) {
        if (n != null && !"".equals(n)) {
            request.getSession().setAttribute("n", n);
        }
        if (l != null && !"".equals(l)) {
            request.getSession().setAttribute("l", l);
        }
    }

    /**
     * 保存登录前URL路径
     * 
     * @param request
     * @param url
     */
    public static void fillSessionHttpUrl(HttpServletRequest request, String url) {
        request.getSession().setAttribute("url", url);
    }

}
