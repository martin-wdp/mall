/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.temp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.bean.ChannelTrademark;
import com.ningpai.channel.service.ChannelTrademarkService;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.TempService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 控制器-模板品牌
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月5日上午10:54:08
 */
@Controller
public class TempBrandController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(TempBrandController.class);

    private static final String LOGINUSERID = "loginUserId";

    public static final String NAME = "name";

    public static final String OPERAPATH = "operaPath";

    private static final String QUERYTEMPTRADEMARKBYPAGEBEAN_HTM = "queryTempTrademarkByPageBean.htm?tempId=";
    private static final String LOGGERINFO1 = ",用户名:";

    /** 频道业务接口 */
    private TempService tempService;
    /** 模板品牌设置业务类 */
    private ChannelTrademarkService channelTrademarkService;

    /**
     * 分页查询模板品牌
     * 
     * @param pb
     * @param tempId
     * @return
     */
    @RequestMapping("/queryTempTrademarkByPageBean")
    public ModelAndView queryTempTrademarkByPageBean(PageBean pb, Long tempId) {
        SysTemp temp = this.tempService.getSystempById(tempId);
        return new ModelAndView("jsp/temp/temp_trademark_list", "pb", channelTrademarkService.selectchannelTrademarkByParam(pb, null, tempId, null, null, null, null)).addObject(
                "temp", temp);
    }

    /**
     * 查看模板品牌
     * 
     * @param trademarkId
     * @param tempId
     * @return
     */
    @RequestMapping("/showTempTrademark")
    public ModelAndView showTempTrademark(Long trademarkId, Long tempId) {
        SysTemp temp = this.tempService.getSystempById(tempId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("temp", temp);
        if (null != trademarkId) {
            ChannelTrademark channelTrademark = this.channelTrademarkService.getChannelTrademarkById(trademarkId);
            map.put("channelTrademark", channelTrademark);
        }
        return new ModelAndView("jsp/temp/show_temp_trademark", "map", map);
    }

    /**
     * 添加模板品牌
     * 
     * @param request
     * @param channelTrademark
     * @param tempId
     * @return
     */
    @RequestMapping("/createTempTrademark")
    public ModelAndView createTempTrademark(HttpServletRequest request, @Valid ChannelTrademark channelTrademark, BindingResult bindingResult, Long tempId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYTEMPTRADEMARKBYPAGEBEAN_HTM + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                channelTrademark.setCreateUserId(1L);
            } else {
                channelTrademark.setCreateUserId(loginUserId);
            }
            // 添加模板品牌
            int n = channelTrademarkService.saveChannelTrademark(channelTrademark);
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "添加模板品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("保存模板品牌成功！");
            } else {
                LOGGER.debug("保存模板品牌失败！");
            }
        } catch (Exception e) {
            LOGGER.error("保存模板品牌异常！", e);
        }
        mav.setView(new RedirectView(QUERYTEMPTRADEMARKBYPAGEBEAN_HTM + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
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
    @RequestMapping("/modifTempTrademark")
    public ModelAndView updateTempTrademark(HttpServletRequest request, @Valid ChannelTrademark channelTrademark, BindingResult bindingResult, Long tempId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYTEMPTRADEMARKBYPAGEBEAN_HTM + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
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
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "修改模板品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("修改模板品牌成功！");
            } else {
                LOGGER.debug("修改模板品牌失败！");
            }
        } catch (Exception e) {
            LOGGER.error("修改模板品牌异常！", e);
        }
        mav.setView(new RedirectView(QUERYTEMPTRADEMARKBYPAGEBEAN_HTM + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        return mav;
    }

    /**
     * 删除模板品牌
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delTempTrademark")
    public void deleteTempTrademark(HttpServletRequest request, HttpServletResponse response) {
        String[] trademarkIds = request.getParameterValues("trademarkIds[]");
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        for (int i = 0; i < trademarkIds.length; i++) {
            channelTrademarkService.deleteChannelTrademark(Long.valueOf(trademarkIds[i]), loginUserId);
        }
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除模板品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    public TempService getTempService() {
        return tempService;
    }

    @Resource(name = "TempService")
    public void setTempService(TempService tempService) {
        this.tempService = tempService;
    }

    public ChannelTrademarkService getChannelTrademarkService() {
        return channelTrademarkService;
    }

    @Resource(name = "ChannelTrademarkService")
    public void setChannelTrademarkService(ChannelTrademarkService channelTrademarkService) {
        this.channelTrademarkService = channelTrademarkService;
    }
}
