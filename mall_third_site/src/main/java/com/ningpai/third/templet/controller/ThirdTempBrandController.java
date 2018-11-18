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

import com.ningpai.channel.bean.ChannelTrademark;
import com.ningpai.channel.service.ChannelTrademarkService;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.ThirdTempService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 控制器-模板品牌
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月5日上午10:54:08
 */
@Controller
public class ThirdTempBrandController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ThirdTempBrandController.class);

    private static final String LOGINUSERID = "loginUserId";

    private static final String SHOW_VIEW = "showThirdTempTrademark.htm?tempId=";

    private static final String LIST_VIEW = "queryThirdTempTrademarkByPageBean.htm?tempId=";

    private static final String THIRDID = "thirdId";

    @Resource(name = "ThirdTempService")
    private ThirdTempService tempService;

    /** 模板品牌设置业务类 */
    @Resource(name = "ChannelTrademarkService")
    private ChannelTrademarkService channelTrademarkService;

    /**
     * 分页查询模板品牌
     * 
     * @param pb
     * @param tempId
     * @return
     */
    @RequestMapping("/queryThirdTempTrademarkByPageBean")
    public ModelAndView queryThirdTempTrademarkByPageBean(HttpServletRequest request, PageBean pb, Long tempId) {
        SysTemp temp = this.tempService.getSystempById(tempId);
        // 获取第三方商家ID
        String thirdId = ((Long) request.getSession().getAttribute(THIRDID)).toString();
        return new ModelAndView("temp/temp_brand_list", "pb", channelTrademarkService.selectchannelTrademarkByParam(pb, null, tempId, null, null, null, thirdId)).addObject("temp",
                temp);
    }

    /**
     * 查看模板品牌
     * 
     * @param trademarkId
     * @param tempId
     * @return
     */
    @RequestMapping("/showThirdTempTrademark")
    public ModelAndView showThirdTempTrademark(HttpServletRequest request, Long trademarkId, Long tempId) {
        Map<String, Object> map = new HashMap<String, Object>();
        SysTemp temp = this.tempService.getSystempById(tempId);
        // 获取第三方商家ID
        String thirdId = ((Long) request.getSession().getAttribute(THIRDID)).toString();
        map.put("temp", temp);
        map.put(THIRDID, thirdId);
        if (null != trademarkId) {
            ChannelTrademark channelTrademark = this.channelTrademarkService.getChannelTrademarkById(trademarkId);
            map.put("channelTrademark", channelTrademark);
        }
        return new ModelAndView("temp/show_temp_brand", "map", map);
    }

    /**
     * 添加模板品牌
     * 
     * @param request
     * @param channelTrademark
     * @param tempId
     * @return
     */
    @RequestMapping("/createThirdTempTrademark")
    public ModelAndView createThirdTempTrademark(HttpServletRequest request, ChannelTrademark channelTrademark, Long tempId) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                channelTrademark.setCreateUserId(1L);
            } else {
                channelTrademark.setCreateUserId(loginUserId);
            }
            // 添加频道品牌
            int n = channelTrademarkService.saveChannelTrademark(channelTrademark);
            if (n > 0) {
                LOGGER.debug("保存模板品牌成功！");
                mav.setView(new RedirectView(LIST_VIEW + tempId));
            } else {
                LOGGER.debug("保存模板品牌失败！");
                mav.setView(new RedirectView(SHOW_VIEW + tempId));
            }
        } catch (Exception e) {
            LOGGER.error("保存模板品牌异常！", e);
            mav.setView(new RedirectView(SHOW_VIEW + tempId));
        }
        return mav;
    }

    /**
     * 修改模板品牌
     * 
     * @param request
     * @param channelTrademark
     * @param tempId
     * @return
     */
    @RequestMapping("/modifThirdTempTrademark")
    public ModelAndView modifThirdTempTrademark(HttpServletRequest request, ChannelTrademark channelTrademark, Long tempId) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                channelTrademark.setUpdateUserId(1L);
            } else {
                channelTrademark.setUpdateUserId(loginUserId);
            }
            // 修改频道品牌
            int n = this.channelTrademarkService.updateChannelTrademark(channelTrademark);
            if (n > 0) {
                LOGGER.debug("修改模板品牌成功！");
                mav.setView(new RedirectView(LIST_VIEW + tempId));
            } else {
                LOGGER.debug("修改模板品牌失败！");
                mav.setView(new RedirectView(SHOW_VIEW + tempId));
            }
        } catch (Exception e) {
            LOGGER.error("修改模板品牌异常！", e);
            mav.setView(new RedirectView(SHOW_VIEW + tempId));
        }
        return mav;
    }

    /**
     * 删除模板品牌
     * 
     * @param request
     * @param trademarkIds
     *
     */
    @RequestMapping("/delThirdTempTrademark")
    public ModelAndView delThirdTempTrademark(HttpServletRequest request, Long[] trademarkIds, Long tempId) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        for (int i = 0; i < trademarkIds.length; i++) {
            channelTrademarkService.deleteChannelTrademark(trademarkIds[i], loginUserId);
        }
        return new ModelAndView(new RedirectView(LIST_VIEW + tempId));
    }

}
