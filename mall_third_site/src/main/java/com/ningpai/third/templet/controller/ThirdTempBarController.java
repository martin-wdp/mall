/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.third.templet.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.bean.ChannelBar;
import com.ningpai.channel.service.ChannelBarService;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.ThirdTempService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 控制器-频道
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月27日下午2:25:56
 */
@Controller
public class ThirdTempBarController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ThirdTempBarController.class);

    private static final String LOGINUSERID = "loginUserId";

    private static final String THIRDID = "thirdId";

    private static final String SHOW_VIEW = "showThirdTempBar.htm?tempId=";

    private static final String LIST_VIEW = "queryThirdTempBarByPageBean.htm?tempId=";

    /** 频道业务接口 */
    @Resource(name = "ThirdTempService")
    private ThirdTempService tempService;

    /** 导航设置业务接口 */
    @Resource(name = "ChannelBarService")
    private ChannelBarService channelBarService;

    /**
     * 分页查询页面导航
     * 
     * @param pb
     * @param tempId
     * @return
     */
    @RequestMapping("/queryThirdTempBarByPageBean")
    public ModelAndView queryThirdTempBarByPageBean(HttpServletRequest request, PageBean pb, Long tempId) {
        SysTemp temp = this.tempService.getSystempById(tempId);
        // 获取第三方商家ID
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        /* 设置查询参数，获取查询后的pb */
        return new ModelAndView("temp/temp_nav_list", "pb", channelBarService.selectChannelBarByParam(pb, null, tempId, thirdId.toString())).addObject("temp", temp);
    }

    /**
     * 查看页面导航
     * 
     * @param barId
     * @param tempId
     * @return
     */
    @RequestMapping("/showThirdTempBar")
    public ModelAndView showThirdTempBar(HttpServletRequest request, Long barId, Long tempId) {
        Map<String, Object> map = new HashMap<String, Object>();
        SysTemp temp = this.tempService.getSystempById(tempId);
        // 获取第三方商家ID
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        map.put("temp", temp);
        map.put(THIRDID, thirdId);

        if (null != barId) {
            ChannelBar channelBar = channelBarService.getChannelBarById(barId);
            map.put("channelBar", channelBar);
        }
        return new ModelAndView("temp/show_temp_nav", "map", map);
    }

    /**
     * 添加页面导航
     * 
     * @param request
     * @param channelBar
     * @param tempId
     * @return
     */
    @RequestMapping("/createThirdTempBar")
    public ModelAndView createThirdTempBar(HttpServletRequest request, ChannelBar channelBar, Long tempId) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                loginUserId = 1L;
            }
            channelBar.setInsertId(loginUserId);
            // 添加导航设置
            int n = channelBarService.saveChannelBar(channelBar);
            if (n > 0) {
                LOGGER.debug("保存导航设置成功！");
                mav.setView(new RedirectView(LIST_VIEW + tempId));
            } else {
                LOGGER.debug("保存导航设置失败！");
                mav.setView(new RedirectView(SHOW_VIEW + tempId));
            }
        } catch (Exception e) {
            LOGGER.error("保存导航设置对象异常！", e);
            mav.setView(new RedirectView(SHOW_VIEW + tempId));
        }
        return mav;
    }

    /**
     * 修改页面导航
     * 
     * @param request
     * @param channelBar
     * @param tempId
     * @return
     */
    @RequestMapping("/updateThirdTempBar")
    public ModelAndView updateThirdTempBar(HttpServletRequest request, ChannelBar channelBar, Long tempId) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                loginUserId = 1L;
            }
            channelBar.setModifyId(loginUserId);
            int n = channelBarService.updateChannelBar(channelBar);
            if (n > 0) {
                LOGGER.debug("保存导航设置成功！");
                mav.setView(new RedirectView(LIST_VIEW + tempId));
            } else {
                LOGGER.debug("保存导航设置失败！");
                mav.setView(new RedirectView(SHOW_VIEW + tempId));
            }
        } catch (Exception e) {
            LOGGER.error("保存导航设置对象异常！", e);
            mav.setView(new RedirectView(SHOW_VIEW + tempId));
        }
        return mav;
    }

    /**
     * 删除页面导航
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/deleteThirdTempBar")
    public ModelAndView deleteThirdTempBar(HttpServletRequest request, Long[] barIds, Long tempId) {
        for (int i = 0; i < barIds.length; i++) {
            channelBarService.deleteChannelBar(barIds[i]);
        }
        return new ModelAndView(new RedirectView(LIST_VIEW + tempId));
    }

}
