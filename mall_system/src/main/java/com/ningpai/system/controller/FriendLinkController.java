/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ningpai.util.MenuSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.ningpai.system.bean.FriendLink;
import com.ningpai.system.service.FriendLinkService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.common.safe.CSRFTokenManager;

/**
 * 友情链接控制层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月18日 上午9:50:47
 * @version 1.0
 */
@Controller
public class FriendLinkController {
    // 友情链接service
    @Resource(name = "friendLinkService")
    private FriendLinkService friendLinkService;
    // friendlink页面
    private String page = "friendlink.htm";
    // token
    private String token = "token";
    // 日志LOGGER
    private static final MyLogger LOGGER = new MyLogger(
            FriendLinkController.class);

    /**
     * 分页查询友情链接信息
     * 
     * @param pb
     * @param request
     * @param selectBean
     * @return ModelAndView
     */
    @RequestMapping("/friendlink")
    public ModelAndView findFrienfLink(HttpServletRequest request, PageBean pb,
            SelectBean selectBean) {
        MenuSession.sessionMenu(request);
        // 判断查询文本是否为空 若为空 将条件也设置为空
        if ("".equals(selectBean.getSearchText())
                && "".equals(selectBean.getSearchTextTwo())) {
            selectBean.setCondition("");
        }

        // 参数设置到作用域中
        request.setAttribute("selectBean", selectBean);
        return new ModelAndView("jsp/system/friendlink", "pb",
                friendLinkService.findByPageBean(pb, selectBean)).addObject(
                token, CSRFTokenManager.getTokenFromRequest(request));
    }

    /**
     * 添加友情链接信息
     * 
     * @param request
     * @param friendLink
     * @return ModelAndView
     */
    @RequestMapping("/addlink")
    public ModelAndView addLink(MultipartHttpServletRequest request,
            FriendLink friendLink) {
        // 添加友情链接
        friendLinkService.insertLink(friendLink);

        return new ModelAndView(new RedirectView(page));
    }

    /**
     * 删除友情链接信息
     * 
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping("/dellink")
    public ModelAndView dellink(HttpServletRequest request,
            HttpServletResponse response) {

        // 删除友情链接信息
        PrintWriter pr = null;

        try {
            pr = response.getWriter();
            pr.print(friendLinkService.deleteLink(request
                    .getParameterValues("linkIds[]")));

        } catch (IOException e) {
            // 日志输出
            LOGGER.error("",e);
        } finally {
            if (pr != null) {
                pr.close();
            }
        }
        return null;
    }

    /**
     * 查询单条友情链接信息
     * 
     * @param linkId
     * @return ModelAndView
     */
    @RequestMapping("/findlinkone")
    @ResponseBody
    public FriendLink findLinkone(Long linkId) {

        return friendLinkService.findByLinkId(linkId);
    }

    /**
     * 修改友情链接信息
     * 
     * @param request
     * @param friendLink
     * @return ModelAndView
     */
    @RequestMapping("/updatelink")
    public ModelAndView updateLink(MultipartHttpServletRequest request,
            FriendLink friendLink) {
        // 修改友情链接
        friendLinkService.updateLink(friendLink);
        return new ModelAndView(new RedirectView(page));
    }

}
