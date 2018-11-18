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
import com.ningpai.channel.bean.ChannelBar;
import com.ningpai.channel.service.ChannelBarService;
import com.ningpai.channel.service.ChannelService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.exception.NPException;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 控制器-频道
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月27日下午2:25:56
 */
@Controller
public class ChannelBarController {
    /** 频道业务接口 */
    private ChannelService channelService;
    /** 导航设置业务接口 */
    private ChannelBarService channelBarService;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ChannelBarController.class);

    private static final String LOGINUSERID = "loginUserId";
    private static final String SHOW_ACTION = "showChannelBar.htm";

    /**
     * 用户名称
     */
    public static final String NAME = "name";
    private static final String CHANNEL = "channel";
    private static final String CHANNELID = "channelId";
    private static final String QUERYCHANNELBAR = "queryChannelBarByPageBean.htm?channelId=";
    private static final String CSRFTOKEN = "CSRFToken";
    private static final String CSRFTOKENSTRING = "&CSRFToken=";
    private static final String USERNAME = ",用户名:";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    /**
     * 分页查询页面导航
     * 
     * @param pb
     *            分页参数
     * @param channelId
     *            标签id
     * @return
     * @throws NPException
     */
    @RequestMapping("/queryChannelBarByPageBean")
    @ResponseBody
    public Map queryChannelBarByPageBean(PageBean pb, Long channelId, HttpServletRequest request) {
        Map<String, Object> maps = new HashMap<>();

        PageBean pageBean = channelBarService.selectChannelBarByParam(pb, channelId, null, null);
        Channel channel = this.channelService.findChannelByID(channelId);
        maps.put("pb", pageBean);
        maps.put(CHANNEL, channel);
        maps.put(CHANNELID, channelId);
        return maps;
    }

    /**
     * 查看频道导航
     * 
     * @param barId
     *            主键id
     * @param channelId
     *            频道id
     * @return
     * @throws NPException
     */
    @RequestMapping("/showChannelBar")
    public ModelAndView showChannelBar(Long barId, Long channelId) {
        // 返回频道信息
        Channel channel = this.channelService.findChannelByID(channelId);
        // 参数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(CHANNEL, channel);
        // 判断id是否为空
        if (null != barId) {
            // 根据id查询详情
            ChannelBar sysBarConf = channelBarService.getChannelBarById(barId);
            // 设值
            map.put("sysBarConf", sysBarConf);
        }
        // 返回到页面
        return new ModelAndView("jsp/channel/showChannelBar", "map", map);
    }

    /**
     * 添加频道导航
     * 
     * @param request
     *            请求参数
     * @param sysBarConf
     *            修改参数
     * @return
     */
    @RequestMapping("/createChannelBar")
    @ResponseBody
    public int createChannelBar(HttpServletRequest request, HttpServletResponse response, ChannelBar sysBarConf, Long channelId) {
        // 判断是否符合规范
        // if (bindingResult.hasErrors()) {
        // //返回到列表页
        // return new ModelAndView(new RedirectView(QUERYCHANNELBAR
        // +sysBarConf.getChannelId() + CSRFTOKENSTRING +
        // request.getParameter(CSRFTOKEN)));
        // }
        // 登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 返回结果
        // ModelAndView mav = new ModelAndView();
        try {
            // 设置类型
            sysBarConf.setBarType(2L);
            // 设置登录id
            if (null == loginUserId) {
                sysBarConf.setInsertId(1L);
            } else {
                sysBarConf.setInsertId(loginUserId);
            }
            // 添加导航设置
            int n = channelBarService.saveChannelBar(sysBarConf);
            // 判断是否插入成功
            // 如果添加成功，记录成功日志
            // 添加失败，记录失败日志
            if (n > 0) {
                // 用户名
                String customerName = (String) request.getSession().getAttribute(NAME);
                // 记录
                OperaLogUtil.addOperaLog(request, customerName, "添加频道导航", request.getSession().getAttribute(OPERAPATH) + USERNAME + customerName);
                LOGGER.debug("保存导航设置成功！");
                // mav.setView(new RedirectView(QUERYCHANNELBAR
                // +sysBarConf.getChannelId() + CSRFTOKENSTRING +
                // request.getParameter(CSRFTOKEN)));
            } else {
                // 记录失败日志
                LOGGER.debug("保存导航设置失败！");
                // mav.setView(new RedirectView(SHOW_ACTION));
            }
            return n;
        } catch (Exception e) {
            LOGGER.error("保存导航设置对象异常！", e);
            // mav.setView(new RedirectView(SHOW_ACTION));
            return 0;
        }
        // return mav;
    }

    /**
     * 修改频道导航
     * 
     * @param sysBarConf
     *            修改参数
     * @param request
     *            请求地址
     * @return
     */
    @RequestMapping("/updateChannelBar")
    public ModelAndView updateChannelBar(HttpServletRequest request, @Valid ChannelBar sysBarConf, BindingResult bindingResult, Long channelId) {
        // 判断参数值是否符合规范
        if (bindingResult.hasErrors()) {
            // 返回列表，不进行修改
            return new ModelAndView(new RedirectView(QUERYCHANNELBAR + sysBarConf.getChannelId() + CSRFTOKENSTRING + request.getParameter(CSRFTOKEN)));
        }
        // 设置登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 容器
        ModelAndView mav = new ModelAndView();
        try {
            // 设置类型
            sysBarConf.setBarType(2L);
            if (null == loginUserId) {
                // 设置修改人id
                sysBarConf.setModifyId(1L);
            } else {
                // 设置修改人id
                sysBarConf.setModifyId(loginUserId);
            }
            sysBarConf.setDeleteStatus(0);
            int n = channelBarService.updateChannelBar(sysBarConf);
            mav.addObject(CHANNELID, channelId);
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "修改频道导航", request.getSession().getAttribute(OPERAPATH) + USERNAME + customerName);
                LOGGER.debug("保存导航设置成功！");
                mav.setView(new RedirectView(QUERYCHANNELBAR + sysBarConf.getChannelId() + CSRFTOKENSTRING + request.getParameter(CSRFTOKEN)));
            } else {
                LOGGER.debug("保存导航设置失败！");
                mav.setView(new RedirectView(SHOW_ACTION));
            }
        } catch (Exception e) {
            LOGGER.error("保存导航设置对象异常！", e);
            mav.setView(new RedirectView(SHOW_ACTION));
        }
        return mav;
    }

    /**
     * 删除频道导航
     * 
     * @param request
     *            请求参数
     * @param response
     */
    @RequestMapping("/deleteChannelBar")
    public void deleteChannelBar(HttpServletRequest request, HttpServletResponse response) {
        // 查询id
        String[] barIds = request.getParameterValues("barIds[]");
        // 循环删除
        for (int i = 0; i < barIds.length; i++) {
            // 根据用户进行
            channelBarService.deleteChannelBar(Long.valueOf(barIds[i]));
        }
        // 用户名称
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "删除频道导航", request.getSession().getAttribute(OPERAPATH) + USERNAME + customerName);
    }

    public ChannelService getChannelService() {
        return channelService;
    }

    @Resource(name = "ChannelService")
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    public ChannelBarService getChannelBarService() {
        return channelBarService;
    }

    @Resource(name = "ChannelBarService")
    public void setChannelBarService(ChannelBarService channelBarService) {
        this.channelBarService = channelBarService;
    }

}
