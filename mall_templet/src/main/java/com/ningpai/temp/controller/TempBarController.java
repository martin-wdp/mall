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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.bean.ChannelBar;
import com.ningpai.channel.service.ChannelBarService;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.TempService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 控制器-频道
 *
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月27日下午2:25:56
 */
@Controller
public class TempBarController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(TempBarController.class);

    private static final String REDIRECT = "queryTempBarByPageBean.htm?tempId=";
    private static final String LOGGERINFO1 = ",用户名:";
    private static final String LOGGERINFO2 = "保存导航设置成功！";
    private static final String LOGGERINFO3 = "保存导航设置失败！";
    private static final String LOGGERINFO4 = "保存导航设置对象异常！";

    /** 频道业务接口 */
    private TempService tempService;

    /** 导航设置业务接口 */
    private ChannelBarService channelBarService;

    private static final String LOGINUSERID = "loginUserId";

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    /**
     * 分页查询页面导航
     *
     * @param pb
     * @param tempId
     * @return
     */
    @RequestMapping("/queryTempBarByPageBean")
    public ModelAndView queryTempBarByPageBean(PageBean pb, Long tempId) {
        SysTemp temp = this.tempService.getSystempById(tempId);
        /* 设置查询参数，获取查询后的pb */
        return new ModelAndView("jsp/temp/temp_bar_list", "pb", channelBarService.selectChannelBarByParam(pb, null, tempId, null)).addObject("temp", temp);
    }

    /**
     * 查询页面导航列表
     * 
     * @param pb
     *            分页参数
     * @param tempId
     *            模板id
     * @return
     */
    @RequestMapping("/queryTempBarByAjax")
    @ResponseBody
    public Map<String, Object> queryTempBarByAjax(PageBean pb, Long tempId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置列表以及分页参数
        map.put("pb", channelBarService.selectChannelBarByParam(pb, null, tempId, null));
        // 根据主键查询模板设置对象
        map.put("temp", this.tempService.getSystempById(tempId));
        return map;
    }

    /**
     * 查看页面导航
     *
     * @param barId
     * @param tempId
     * @return
     */
    @RequestMapping("/showTempBar")
    public ModelAndView showTempBar(Long barId, Long tempId) {
        Map<String, Object> map = new HashMap<String, Object>();
        SysTemp temp = this.tempService.getSystempById(tempId);
        map.put("temp", temp);
        if (null != barId) {
            ChannelBar channelBar = channelBarService.getChannelBarById(barId);
            map.put("channelBar", channelBar);
        }
        return new ModelAndView("jsp/temp/show_temp_bar", "map", map);
    }

    /**
     * 根据页面导航id查询页面导航信息
     * 
     * @param barId
     * @return
     */
    @RequestMapping("/showtempbarbyid")
    @ResponseBody
    public ChannelBar showTempBarById(Long barId) {
        // 根据id查询页面导航信息
        return channelBarService.getChannelBarById(barId);
    }

    /**
     * 添加页面导航
     *
     * @param request
     * @param channelBar
     * @param tempId
     * @return
     */
    @RequestMapping("/createTempBar")
    public ModelAndView createTempBar(HttpServletRequest request, @Valid ChannelBar channelBar, BindingResult bindingResult, Long tempId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(REDIRECT + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
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
                // 操作人
                String customerName = (String) request.getSession().getAttribute(NAME);
                // 日志记录
                OperaLogUtil.addOperaLog(request, customerName, "添加页面导航", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug(LOGGERINFO2);
            } else {
                LOGGER.debug(LOGGERINFO3);
            }
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO4, e);
        }
        mav.setView(new RedirectView(REDIRECT + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        return mav;
    }

    /**
     * 添加页面导航（新）
     *
     * @param request
     * @param channelBar
     *            添加参数
     * @param tempId
     *            模板id
     * @return
     */
    @RequestMapping("/createTempBarAjax")
    @ResponseBody
    public int createTempBarAjax(HttpServletRequest request, @Valid ChannelBar channelBar, BindingResult bindingResult, Long tempId) {
        if (bindingResult.hasErrors()) {
            return 0;
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        try {
            if (null == loginUserId) {
                loginUserId = 1L;
            }
            channelBar.setInsertId(loginUserId);
            // 添加导航设置
            int n = channelBarService.saveChannelBar(channelBar);
            if (n > 0) {
                // 操作人
                String customerName = (String) request.getSession().getAttribute(NAME);
                // 日志记录
                OperaLogUtil.addOperaLog(request, customerName, "添加页面导航", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug(LOGGERINFO2);
            } else {
                LOGGER.debug(LOGGERINFO3);
            }
            return n;
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO4, e);
            return 0;
        }
    }

    /**
     * 修改页面导航
     *
     * @param request
     * @param channelBar
     * @param tempId
     * @return
     */
    @RequestMapping("/updateTempBar")
    public ModelAndView updateTempBar(HttpServletRequest request, @Valid ChannelBar channelBar, BindingResult bindingResult, Long tempId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView("showTempInfo.htm?tempId=" + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                loginUserId = 1L;
            }
            channelBar.setModifyId(loginUserId);
            int n = channelBarService.updateChannelBar(channelBar);
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "修改页面导航", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug(LOGGERINFO2);
            } else {
                LOGGER.debug(LOGGERINFO3);
            }
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO4, e);
        }
        mav.setView(new RedirectView(REDIRECT + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        return mav;
    }

    /**
     * 修改页面导航(新)
     *
     * @param request
     * @param channelBar
     * @param tempId
     * @return
     */
    @RequestMapping("/updatetempbarbyajax")
    @ResponseBody
    public int updateTempBarByAjax(HttpServletRequest request, @Valid ChannelBar channelBar, BindingResult bindingResult, Long tempId) {
        // 判断是否符合规范
        if (bindingResult.hasErrors()) {
            return 0;
        }
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 返回结果
        int n = 0;
        try {
            // 如果获取不到id
            if (null == loginUserId) {
                // 如果为null
                loginUserId = 1L;
            }
            // 设置修改
            channelBar.setModifyId(loginUserId);
            // 修改内容
            n = channelBarService.updateChannelBar(channelBar);
            // 保存日志
            if (n > 0) {
                // 修改成功
                // 获取用
                String customerName = (String) request.getSession().getAttribute(NAME);
                // 记录日志
                OperaLogUtil.addOperaLog(request, customerName, "修改页面导航", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug(LOGGERINFO2);
            } else {
                // 修改失败
                LOGGER.debug(LOGGERINFO3);
            }
            return n;
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO4, e);
            return 0;
        }
    }

    /**
     * 删除页面导航
     *
     * @param request
     * @param response
     */
    @RequestMapping("/deleteTempBar")
    public void deleteTempBar(HttpServletRequest request, HttpServletResponse response) {
        String[] barIds = request.getParameterValues("barIds[]");
        for (int i = 0; i < barIds.length; i++) {
            channelBarService.deleteChannelBar(Long.valueOf(barIds[i]));
        }
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除页面导航", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    /**
     * 删除页面导航
     *
     * @param request
     * @param response
     */
    @RequestMapping("/deleteTempBarAjxs")
    @ResponseBody
    public void deleteTempBarAjxs(HttpServletRequest request, HttpServletResponse response, String[] barId) {
        for (int i = 0; i < barId.length; i++) {
            // 根据主键删除频道导航
            channelBarService.deleteChannelBar(Long.valueOf(barId[i]));
        }
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除页面导航", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    public TempService getTempService() {
        return tempService;
    }

    @Resource(name = "TempService")
    public void setTempService(TempService tempService) {
        this.tempService = tempService;
    }

    public ChannelBarService getChannelBarService() {
        return channelBarService;
    }

    @Resource(name = "ChannelBarServiceNew")
    public void setChannelBarService(ChannelBarService channelBarService) {
        this.channelBarService = channelBarService;
    }
}
