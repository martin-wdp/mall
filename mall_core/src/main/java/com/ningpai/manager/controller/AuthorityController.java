/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.manager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.manager.bean.Authority;
import com.ningpai.manager.bean.AuthorityPage;
import com.ningpai.manager.bean.ManagerAuthority;
import com.ningpai.manager.service.AuthorityServiceInterface;
import com.ningpai.util.MenuSession;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 权限控制器
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年1月9日 上午1:11:31
 * @version 0.0.1
 */
@Controller("authorityController")
public class AuthorityController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(AuthorityController.class);

    private static final String LOGINUSERID = "loginUserId";

    private static final String INITAUTHORITY_HTM = "initAuthority.htm";

    // spring 注解
    private AuthorityServiceInterface authorityServiceInterface;

    /**
     * 查询所有权限列表
     * 
     * @return List<Authority>
     */
    @RequestMapping("/queryAllAuthority")
    @ResponseBody
    public List<Authority> queryAllAuthority(HttpServletRequest request) {
        Long createId = null;
        // 判断是否是超级管理员
        if ((Integer) request.getSession().getAttribute("managerFlag") != 1) {
            createId = (Long) request.getSession().getAttribute(LOGINUSERID);
        }
        // 查询权限
        return authorityServiceInterface.queryAuthoerityList(createId);
    }

    /**
     * 根据管理员编号查找权限
     * 
     * @param mid
     *            管理员编号
     * @return
     */
    @RequestMapping("/queryAuthorByManagerId")
    @ResponseBody
    public ManagerAuthority queryAuthorByManagerId(Long mid) {
        // 查询权限
        return authorityServiceInterface.queryAuthorByManagerId(mid);
    }

    /**
     * 加载管理员列表
     * 
     * @return 管理员列表
     */
    @RequestMapping("/initAuthority")
    public ModelAndView initAuthority(PageBean pageBean, HttpServletRequest request, Authority authority) {
        // 设置菜单
        MenuSession.sessionMenu(request);
        // 获取管理员标识
        Integer flag = (Integer) request.getSession().getAttribute("managerFlag");
        Long createId = null;
        if (flag != null && flag != 1) {
            // 获取管理员ID
            createId = (Long) request.getSession().getAttribute(LOGINUSERID);
        }
        pageBean.setUrl(INITAUTHORITY_HTM);
        // 查询管理员权限
        return new ModelAndView("jsp/core/manager/authoritylist").addObject("pageBean", authorityServiceInterface.queryAuthoerityList(authority, pageBean, createId));
    }

    /**
     * 管理员名称是否已经存在
     *
     * @param username
     *            管理员名称
     * @return 0 不存在 1存在
     */
    @RequestMapping("/checkManagerExist")
    @ResponseBody
    public Authority checkManagerExist(String username) {
        // 非空验证 管理员名称是否为空
        if (null != username) {
            LOGGER.info("验证权限名称【" + username + "】是否存在！");
        }
        // 验证名称
        return authorityServiceInterface.checkManagerExist(username);

    }

    /**
     * 加载管理员列表
     * 
     * @return 管理员列表
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/queryAuthority")
    public ModelAndView queryAuthority(Authority authority, PageBean pageBean, String[] attr) throws UnsupportedEncodingException {
        // 设置跳转URL
        pageBean.setUrl("queryAuthority.htm");
        // 查询管理员列表
        return new ModelAndView("jsp/core/manager/authoritylist").addObject("pageBean", authorityServiceInterface.queryAuthoerityList(authority, pageBean, null))
                .addObject("authority", authority).addObject("attr", attr);
    }

    /**
     * 删除权限
     * 
     * @return
     */
    @RequestMapping("/deleteAuthority")
    public ModelAndView deleteAuthority(HttpServletRequest request, HttpServletResponse response, Long authId) {
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
            // 删除会员信息 输出信息 0 失败 1成功
            if (authId != null) {
                String[] strArray = new String[1];
                strArray[0] = authId + "";
                pr.print(authorityServiceInterface.deleteAuthority(strArray));

            } else {
                // 删除
                pr.print(authorityServiceInterface.deleteAuthority(request.getParameterValues("parameterIds[]")));
            }

        } catch (IOException e) {
            LOGGER.error("",e);
            pr = null;
        } finally {
            pr = null;
        }
        return null;
    }

    /**
     * 批量删除
     * 
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping("/deleteAllAuthority")
    public ModelAndView deleteAllAuthority(HttpServletRequest request, HttpServletResponse response) {
        // 批量删除方法
        authorityServiceInterface.deleteAuthority(request.getParameterValues("authorid"));
        return new ModelAndView(new RedirectView(INITAUTHORITY_HTM));
    }

    /**
     * 添加权限
     * 
     * @return
     */
    @RequestMapping("/addAuthority")
    public ModelAndView addAuthority(HttpServletRequest request, HttpServletResponse response, Authority authority, String catId) {
        PrintWriter pr = null;
        Long createId = (Long) request.getSession().getAttribute(LOGINUSERID);
        try {
            pr = response.getWriter();
            // 添加权限信息 输出信息 0 失败 1成功
            pr.print(authorityServiceInterface.insertAuthorities(catId, authority, createId));
        } catch (IOException e) {
            LOGGER.error("添加权限错误：" + e);
        } finally {
            pr = null;
        }
        return new ModelAndView(new RedirectView(INITAUTHORITY_HTM));
    }

    /**
     * 修改权限
     * 
     * @param authority
     * @return
     */
    @RequestMapping("/updateAuthority")
    public ModelAndView updateAuthority(Authority authority, String catId) {
        // 非空验证 权限名称
        if (null != authority.getDesignation()) {
            // 日志记录
            LOGGER.info("修改权限,权限名称为：" + authority.getDesignation());
        }
        authorityServiceInterface.deleteAuthorityPage(authority.getId());
        // 删除标识 默认为0 :不删除
        authority.setFlag("0");
        authorityServiceInterface.mergeAuthority(catId, authority);
        return new ModelAndView(new RedirectView(INITAUTHORITY_HTM));
    }

    /**
     * 根据权限编号查找权限
     * 
     * @param id
     *            权限编号
     * @return 权限
     */
    @RequestMapping("/queryAuthorityById")
    @ResponseBody
    public Authority queryAuthorityById(Long id) {
        Authority authority = authorityServiceInterface.queryAuthorId(id);
        // 非空验证 权限名称
        if (null != authority.getDesignation()) {
            // 日志记录
            LOGGER.info("根据权限编号查找权限,权限名称为：" + authority.getDesignation());
        }
        return authority;
    }

    /**
     * 根据权限编号查找权限页面关系信息ID
     * 
     * @param id
     *            权限编号
     * @return 权限页面关系ID
     */
    @RequestMapping("/queryAuthorityByAId")
    @ResponseBody
    public List<AuthorityPage> queryAuthorityByAId(Long id) {
        return authorityServiceInterface.queryAuthorityByAId(id);
    }

    /**
     * 验证权限名称是否已经存在
     * 
     * @param authName
     *            权限名称
     * @return 0 不存在 1存在
     */
    @RequestMapping("/checkauthexist")
    @ResponseBody
    public Long checkAuthExist(String authName) {

        /*
         * try { authName= java.net.URLDecoder.decode(authName,"UTF-8"); } catch
         * (UnsupportedEncodingException e) { authName=""; }
         */

        // 非空验证 权限名称是否为空
        if (null != authName) {
            LOGGER.info("验证权限名称【" + authName + "】是否存在！");
        }
        // 验证名称
        return authorityServiceInterface.checkAuthExist(authName);

    }

    public AuthorityServiceInterface getAuthorityServiceInterface() {
        return authorityServiceInterface;
    }

    @Resource(name = "authorityService")
    public void setAuthorityServiceInterface(AuthorityServiceInterface authorityServiceInterface) {
        this.authorityServiceInterface = authorityServiceInterface;
    }
}
