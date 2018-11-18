/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.manager.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.manager.bean.Page;
import com.ningpai.manager.bean.valuebean.MenuVo;
import com.ningpai.manager.service.MenuServiceInterface;
import com.ningpai.manager.service.PageServiceInterface;
import com.ningpai.manager.service.impl.MenuService;
import com.ningpai.manager.util.ManagerValueUtil;

/**
 * 菜单控制器
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月8日 下午10:42:23
 * @version 1.0
 */
@Controller
public class PageController {
    // spring 注解
    private PageServiceInterface pageserviceInterface;
    // spring 注解
    private MenuService menuServiceInterface;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(PageController.class);

    /**
     * 删除Page
     * 
     * @param pageId
     *            待删除的PageId
     */
    @RequestMapping("/delPage")
    public ModelAndView delPage(Long pageId, String active) {
        // 非空验证 页面ID
        if (null != pageId) {
            // 获取页面对象
            Page page = pageserviceInterface.queryPageByPrimaryKey(pageId);
            // 验证页面名称是否为空
            if (null != page.getDesignation()) {
                LOGGER.info("删除【" + page.getDesignation() + "】页面");
            }
        }
        this.pageserviceInterface.delPage(pageId);
        if (active != null) {
            return new ModelAndView(new RedirectView(
                    ManagerValueUtil.INITSETTING));
        } else {
            return new ModelAndView(new RedirectView(
                    ManagerValueUtil.JUMPFORPAGEVIEW));
        }
    }

    /**
     * 批量删除Page
     * 
     * @param pageIds
     *            待删除的PageID数组
     */
    @RequestMapping("/batchDelPage")
    public ModelAndView batchDelPage(Long[] pageIds, String active, Long pageId) {
        if (active == null) {
            // 旧的Boss
            this.pageserviceInterface.batchDelPage(pageIds);
            return new ModelAndView(new RedirectView(
                    ManagerValueUtil.JUMPFORPAGEVIEW));
        } else {
            // 新Boss删除菜单
            this.pageserviceInterface.newBatchDelPage(pageId);
            return new ModelAndView(new RedirectView(
                    ManagerValueUtil.INITSETTING));
        }

    }

    /**
     * 增加Page
     * 
     * @param page
     *            待增加的Page实体
     */
    @RequestMapping("/savePage")
    public ModelAndView savePage(Page page, String active,
            MultipartHttpServletRequest multipartRequest,
            HttpServletRequest request) {
        // 验证页面名称是否为空
        if (null != page.getDesignation()) {
            LOGGER.info("增加【" + page.getDesignation() + "】页面");
        }
        this.pageserviceInterface.savePage(page);
        if (active != null) {
            return new ModelAndView(new RedirectView(
                    ManagerValueUtil.INITSETTING));
        } else {
            return new ModelAndView(new RedirectView(
                    ManagerValueUtil.JUMPFORPAGEVIEW));
        }

    }

    /**
     * 设置会员头像 若前断没有选择图片 则不上传
     * 
     * @param allinfo
     *            会员详细信息
     * @param request
     * @param multipartRequest
     */
    public void setPageImgUrl1(Page page, HttpServletRequest request,
            MultipartHttpServletRequest multipartRequest) {
        String imgUrl = "";
        String imgUrlSelected = "";
        // 设置会员头像 若前断没有选择图片 则不上传
        if (multipartRequest.getFile(Constants.IMGURL) != null
                && !"".equals(multipartRequest.getFile(Constants.IMGURL)
                        .getOriginalFilename())) {
            // 使用上传后路径
            imgUrl = UploadUtil.uploadFileOne(
                    multipartRequest.getFile(Constants.IMGURL), request);
        }
        if (multipartRequest.getFile(Constants.IMGURLSELECTED) != null
                && !"".equals(multipartRequest
                        .getFile(Constants.IMGURLSELECTED)
                        .getOriginalFilename())) {
            // 使用上传后路径
            imgUrlSelected = UploadUtil
                    .uploadFileOne(
                            multipartRequest.getFile(Constants.IMGURLSELECTED),
                            request);
        }
        page.setImgUrl(imgUrl);
        page.setImgUrlSelected(imgUrlSelected);
    }

    /**
     * 更新Page
     * 
     * @param page
     *            待更新的Page实体
     */
    @RequestMapping("/updatePage")
    public ModelAndView updatePage(Page page, String active,
            MultipartHttpServletRequest multipartRequest,
            HttpServletRequest request) {
        // 验证页面名称是否为空
        if (null != page.getDesignation()) {
            // 日志记录
            LOGGER.info("更新【" + page.getDesignation() + "】页面");
        }
        this.pageserviceInterface.updatePage(page);
        if (active != null) {
            return new ModelAndView(new RedirectView(
                    ManagerValueUtil.INITSETTING));
        } else {
            return new ModelAndView(new RedirectView(
                    ManagerValueUtil.JUMPFORPAGEVIEW));
        }
    }

    /**
     * 根据主键查询实体
     * 
     * @param pageId
     *            主键ID
     */
    @RequestMapping("/queryPageById")
    @ResponseBody
    public Page queryPageById(Long pageId) {
        Page page = this.pageserviceInterface.queryPageByPrimaryKey(pageId);
        // 验证页面名称是否为空
        if (null != page.getDesignation()) {
            // 日志记录
            LOGGER.info("获取【" + page.getDesignation() + "】页面的信息");
        }
        return page;
    }

    /**
     * 验证是否可以删除
     * 
     * @param pageId
     *            待验证的菜单ID
     * @return 验证的结果
     */
    @RequestMapping("/checkDelPage")
    @ResponseBody
    public boolean checkDel(Long pageId) {
        // 验证是否可以删除
        return this.pageserviceInterface.checkDelWithPageId(pageId);
    }

    /**
     * 查询所有的Page
     * 
     * @return 查询到的列表
     */
    @RequestMapping("/queryAllMenuVo")
    @ResponseBody
    public List<MenuVo> queruAllPage() {
        // 查询到的列表
        return this.pageserviceInterface.queryAllMenuVo();
    }

    /**
     * 根据 参数查询page并通过AJAX返回
     * 
     * @param pb
     *            分页帮助类
     * @param selectBean
     *            查询帮助类
     * @return PageBean
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/queryMenuVoList")
    @ResponseBody
    public PageBean queryMenuVoList(PageBean pb, SelectBean selectBean)
            throws UnsupportedEncodingException {
        pb.setList(this.pageserviceInterface.getCateList(pb, selectBean));
        return pb;
    }

    /**
     * 跳转到page管理的视图
     */
    @RequestMapping("/jumpForPageView")
    public ModelAndView jumpForPageView() {
        return new ModelAndView("jsp/core/manager/menu_list");
    }

    /**
     * 获取所有菜单
     * 
     * @return List
     */
    @RequestMapping("/queryAllMenu")
    @ResponseBody
    public List<MenuVo> queryAllMenu() {
        return menuServiceInterface.getAllMenu();
    }

    /**
     * 查询当前全下的菜单
     * 
     * @param request
     * @return List
     */
    @RequestMapping("/queryAllMenuByLogin")
    @ResponseBody
    public List<MenuVo> queryAllMenuByLogin(HttpServletRequest request) {
        return menuServiceInterface.getAllMenuByLogin((Long) request
                .getSession().getAttribute("loginUserId"));
    }

    public MenuServiceInterface getMenuServiceInterface() {
        return menuServiceInterface;
    }

    @Resource(name = "menuServiceInterface")
    public void setMenuServiceInterface(MenuService menuServiceInterface) {
        this.menuServiceInterface = menuServiceInterface;
    }

    public PageServiceInterface getPageserviceInterface() {
        return pageserviceInterface;
    }

    @Resource(name = "PageService")
    public void setPageserviceInterface(
            PageServiceInterface pageserviceInterface) {
        this.pageserviceInterface = pageserviceInterface;
    }
}
