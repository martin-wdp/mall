package com.ningpai.util;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import com.ningpai.manager.bean.Page;
import com.ningpai.manager.service.PageServiceInterface;

/**
 * session中的菜单管理 主要为了标记菜单的位置
 * 
 * @author NP-HEHU
 * @date 2015-8-28 17:36:17
 */
@Controller
public class MenuSession {

    private static final String MENUID = "menuId";
    private static final String MENUPARENTID = "menuParentId";
    private static final String MYSELFID = "myselfId";

    /**
     * 菜单接口
     */
    private static PageServiceInterface pageserviceInterface;
    /**
     * 菜单接口
     */
    private PageServiceInterface pageInterface;

    /**
     * 初始化
     * */
    @PostConstruct
    public void init() {
        pageserviceInterface = this.pageInterface;
    }

    public PageServiceInterface getPageInterface() {
        return pageInterface;
    }

    /**
     * spring注入 PageService
     * 
     * @param pageInterface
     *            PageServiceInterface
     */
    @Resource(name = "PageService")
    public void setPageInterface(PageServiceInterface pageInterface) {
        this.pageInterface = pageInterface;
    }

    /**
     * 设置Session里当前的菜单选项
     * 
     * @param request
     *            HttpServletRequest
     */
    public static void sessionMenu(HttpServletRequest request) {

        if (request.getParameter(MENUID) != null) {
            request.getSession().setAttribute(MENUID, request.getParameter(MENUID));
            Page page = pageserviceInterface.queryPageByPrimaryKey(Long.valueOf(request.getParameter(MENUID)));
            request.getSession().setAttribute("pageNameRoot", page.getDesignation());
        }
        if (request.getParameter(MENUPARENTID) != null) {
            request.getSession().setAttribute(MENUPARENTID, request.getParameter(MENUPARENTID));
            Page page = pageserviceInterface.queryPageByPrimaryKey(Long.valueOf(request.getParameter(MENUPARENTID)));
            request.getSession().setAttribute("pageNameParent", page.getDesignation());
        }
        if (request.getParameter(MYSELFID) != null) {
            request.getSession().setAttribute(MYSELFID, request.getParameter(MYSELFID));
            Page page = pageserviceInterface.queryPageByPrimaryKey(Long.valueOf(request.getParameter(MYSELFID)));
            request.getSession().setAttribute("pageName", page.getDesignation());
            request.getSession().setAttribute("pageNameChild", page.getDesignation());
        } else {
            request.getSession().setAttribute("pageName", "后台管理");
        }

    }

}
