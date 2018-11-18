/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 *
 */
package com.ningpai.channel.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.bean.Channel;
import com.ningpai.channel.bean.ChannelStoreyTag;
import com.ningpai.channel.service.ChannelService;
import com.ningpai.channel.service.ChannelStoreyTagService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.TempService;
import com.ningpai.util.PageBean;

/**
 * 控制器-页面标签
 *
 * @author NINGPAI-WangHaiYang
 * @since 2014年6月6日上午10:17:51
 */
@Controller
public class PageTagController {

    private static final String LOGINUSERID = "loginUserId";
    public static final String NAME = "name";
    private static final String CSRFTOKEN = "CSRFToken";
    private static final String CSRFTOKENSTRING = "&CSRFToken=";
    private static final String USERNAME = ",用户名:";
    public static final String OPERAPATH = "operaPath";
    private static final String QUERYPAGETAGBYPAGEBEAN_TEMPID = "queryPageTagByPageBean.htm?tempId=";
    private static final String QUERYPAGETAGBYPAGEBEAN_CHANNELID = "queryPageTagByPageBean.htm?channelId=";
    private static final String LOGGERINFO1 = "删除页面标签";

    /** 模板业务接口 */
    @Resource(name = "TempService")
    private TempService tempService;
    /** 频道业务接口 */
    @Resource(name = "ChannelService")
    private ChannelService channelService;
    /** 频道楼层标签业务接口 */
    @Resource(name = "ChannelStoreyTagService")
    private ChannelStoreyTagService channelStoreyTagService;

    /**
     * 分页查询页面标签
     *
     * @param pb
     * @param tempId
     * @param channelId
     * @return
     */
    @RequestMapping("/queryPageTagByPageBean")
    public ModelAndView queryPageTagByPageBean(PageBean pb, Long tempId, Long channelId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("pb", this.channelStoreyTagService.selectchannelStoreyTagByParam(pb, null, tempId, channelId));
        mav.addObject("tempId", tempId);
        mav.addObject("channelId", channelId);
        mav.setViewName("jsp/channel/page_tag_list");
        return mav;
    }

    /**
     * 分页查询页面标签（新）
     *
     * @param pb
     * @param tempId
     * @param channelId
     * @return
     */
    @RequestMapping("/querypagetagbypagebeanajax")
    @ResponseBody
    public Map<String, Object> queryPageTagByPageBeanAjax(PageBean pb, Long tempId, Long channelId, Long storeyId) {
        // 设置map
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置结婚
        map.put("pb", this.channelStoreyTagService.selectchannelStoreyTagByParam(pb, storeyId, tempId, channelId));
        // 返回结果
        return map;
    }

    /**
     * 查看页面标签
     *
     * @param tagId
     * @param channelId
     * @return
     */
    @RequestMapping("/showPageTag")
    public ModelAndView showPageTag(Long tagId, Long tempId, Long channelId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == channelId) {
            SysTemp temp = tempService.getSystempById(tempId);
            map.put("temp", temp);
        } else {
            Channel channel = channelService.findChannelByID(channelId);
            map.put("channel", channel);
        }
        if (null != tagId) {
            ChannelStoreyTag channelStoreyTag = channelStoreyTagService.getChannelStoreyTagById(tagId);
            map.put("channelStoreyTag", channelStoreyTag);
        }
        return new ModelAndView("jsp/channel/show_page_tag", "map", map);
    }

    /**
     * 查看页面标签（新）
     *
     * @param tagId
     * @param channelId
     * @return
     */
    @RequestMapping("/showpagetagajax")
    @ResponseBody
    public ChannelStoreyTag showPageTagAjax(Long tagId, Long tempId, Long channelId) {
        // 返回页面标签
        return channelStoreyTagService.getChannelStoreyTagById(tagId);
    }

    /**
     * 添加页面标签
     *
     * @param request
     * @param channelStoreyTag
     * @return
     */
    @RequestMapping("/createPageTag")
    public ModelAndView createPageTag(HttpServletRequest request, @Valid ChannelStoreyTag channelStoreyTag, BindingResult bindingResult, Long tempId, Long channelId) {
        // 判断是否有错误
        if (bindingResult.hasErrors()) {
            // 判断是否是频道页
            if (null == channelId) {
                return new ModelAndView(new RedirectView(QUERYPAGETAGBYPAGEBEAN_TEMPID + tempId + CSRFTOKENSTRING + request.getParameter(CSRFTOKEN)));
            } else {
                return new ModelAndView(new RedirectView(QUERYPAGETAGBYPAGEBEAN_CHANNELID + channelId + CSRFTOKENSTRING + request.getParameter(CSRFTOKEN)));
            }
        }
        // 返回登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        // 获取登录名
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        // 设置当前登录用户
        channelStoreyTag.setCreateUserId(loginUserId);
        // 添加频道标签
        channelStoreyTagService.saveChannelStoreyTag(channelStoreyTag);
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 添加日志
        OperaLogUtil.addOperaLog(request, customerName, "添加页面标签", request.getSession().getAttribute(OPERAPATH) + USERNAME + customerName);
        // 设置返回路劲
        if (null == channelId) {
            mav.setView(new RedirectView(QUERYPAGETAGBYPAGEBEAN_TEMPID + tempId + CSRFTOKENSTRING + request.getParameter(CSRFTOKEN)));
        } else {
            mav.setView(new RedirectView(QUERYPAGETAGBYPAGEBEAN_CHANNELID + channelId + CSRFTOKENSTRING + request.getParameter(CSRFTOKEN)));
        }

        return mav;
    }

    /**
     * 添加页面标签(新)
     *
     * @param channelStoreyTag
     * @param request
     * @return
     */
    @RequestMapping("/createpagetagajax")
    @ResponseBody
    public int createPageTagAjax(HttpServletRequest request, @Valid ChannelStoreyTag channelStoreyTag, BindingResult bindingResult, Long tempId, Long channelId) {
        // 判断是否有错误
        if (bindingResult.hasErrors()) {
            // 返回
            return 0;
        }
        // 返回登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 设置当前登录用户
        channelStoreyTag.setCreateUserId(loginUserId);
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 添加日志
        OperaLogUtil.addOperaLog(request, customerName, "添加页面标签", request.getSession().getAttribute(OPERAPATH) + USERNAME + customerName);
        // 返回值
        // 1：成功
        // 1：失败
        return channelStoreyTagService.saveChannelStoreyTag(channelStoreyTag);
    }

    /**
     * 修改页面标签
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updatePageTag")
    public ModelAndView updatePageTag(HttpServletRequest request, HttpServletResponse response, @Valid ChannelStoreyTag channelStoreyTag, BindingResult bindingResult, Long tempId,
            Long channelId) {
        // 判断参数是否符合规范
        if (bindingResult.hasErrors()) {
            // 判断是否是频道页
            if (null == channelId) {
                return new ModelAndView(new RedirectView(QUERYPAGETAGBYPAGEBEAN_TEMPID + tempId + CSRFTOKENSTRING + request.getParameter(CSRFTOKEN)));
            } else {
                return new ModelAndView(new RedirectView(QUERYPAGETAGBYPAGEBEAN_CHANNELID + channelId + CSRFTOKENSTRING + request.getParameter(CSRFTOKEN)));
            }
        }
        // 获取登陆id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        // 设置修改人
        channelStoreyTag.setUpdateUserId(loginUserId);
        // 修改频道标签
        this.channelStoreyTagService.updateChannelStoreyTag(channelStoreyTag);
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "修改页面标签", request.getSession().getAttribute(OPERAPATH) + USERNAME + customerName);
        if (null == channelId) {
            mav.setView(new RedirectView(QUERYPAGETAGBYPAGEBEAN_TEMPID + tempId + CSRFTOKENSTRING + request.getParameter(CSRFTOKEN)));
        } else {
            mav.setView(new RedirectView(QUERYPAGETAGBYPAGEBEAN_CHANNELID + channelId + CSRFTOKENSTRING + request.getParameter(CSRFTOKEN)));
        }
        return mav;
    }

    /**
     * 修改页面标签
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updatepagetagajax")
    @ResponseBody
    public int updatePageTagAjax(HttpServletRequest request, HttpServletResponse response, @Valid ChannelStoreyTag channelStoreyTag, BindingResult bindingResult, Long tempId,
            Long channelId) {
        // 判断参数是否符合规范
        if (bindingResult.hasErrors()) {
            // 判断是否是频道页
            return 0;
        }
        // 获取登陆id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // ModelAndView mav = new ModelAndView();
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        // 设置修改人
        channelStoreyTag.setUpdateUserId(loginUserId);
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "修改页面标签", request.getSession().getAttribute(OPERAPATH) + USERNAME + customerName);
        // 返回修改结果
        // 1：修改成功
        // 0：修改失败
        return this.channelStoreyTagService.updateChannelStoreyTag(channelStoreyTag);
    }

    /**
     * 删除页面标签
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deletePageTag")
    public void deletePageTag(HttpServletRequest request, HttpServletResponse response) {
        String[] tagIds = request.getParameterValues("tagIds[]");
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        for (int i = 0; i < tagIds.length; i++) {
            channelStoreyTagService.deleteChannelStoreyTag(Long.valueOf(tagIds[i]), loginUserId);
        }
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, LOGGERINFO1, request.getSession().getAttribute(OPERAPATH) + USERNAME + customerName);
    }

    /**
     * 删除页面标签
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deletepagetagajax")
    @ResponseBody
    public void deletePageTagAjax(HttpServletRequest request, HttpServletResponse response) {
        // 获取标签列表
        String[] tagIds = request.getParameterValues("tagId");
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 设置登录id
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        // 删除
        for (int i = 0; i < tagIds.length; i++) {
            channelStoreyTagService.deleteChannelStoreyTag(Long.valueOf(tagIds[i]), loginUserId);
        }
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 删除成功
        OperaLogUtil.addOperaLog(request, customerName, LOGGERINFO1, request.getSession().getAttribute(OPERAPATH) + USERNAME + customerName);
    }

    /**
     * 删除页面标签
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deletePageTagPro")
    public void deletePageTagPro(HttpServletRequest request, HttpServletResponse response) {
        String[] tagIds = request.getParameterValues("tagIds[]");
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        for (int i = 0; i < tagIds.length; i++) {
            channelStoreyTagService.deleteByPrimaryKeyCallPro(Long.valueOf(tagIds[i]));
        }
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, LOGGERINFO1, request.getSession().getAttribute(OPERAPATH) + USERNAME + customerName);
    }
}
