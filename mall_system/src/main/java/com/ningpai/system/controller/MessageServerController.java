/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ningpai.util.MenuSession;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.MessageServer;
import com.ningpai.system.service.MessageServerService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 短信接口设置表
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月14日 下午5:38:32
 * @version
 */
@Controller
public class MessageServerController {

    private static final MyLogger LOGGER = new MyLogger(MessageServerController.class);

    private static final String REDIRECT = "queryMessageSetByPb.htm";

    public static final String NAME = "name";

    public static final String OPERAPATH = "operaPath";

    @Resource(name = "messageServerService")
    private MessageServerService messageServerService;

    /**
     * 分页查询短信接口
     * 
     * @param pb
     * @return
     */
    @RequestMapping("queryMessageSetByPb")
    public ModelAndView queryMessageSetByPb(PageBean pb, HttpServletRequest request) {
        PageBean pbNew = pb;
        // 设置导航
        MenuSession.sessionMenu(request);
        pbNew = this.messageServerService.selectAllByPb(pbNew);
        return new ModelAndView("jsp/system/messageserver/messageserver_list", "pb", pbNew);
    }

    /**
     * 查看接口服务器信息
     * 
     * @return ModelAndView
     */
    @RequestMapping("/showMessageSet")
    public ModelAndView showMessageSet(Long smsId) {

        return new ModelAndView("jsp/system/messageserver/messagesetup", "message", messageServerService.getMessage(smsId));
    }

    /**
     * 查询接口服务器信息
     * 
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping("/messageset")
    public ModelAndView messageSet(HttpServletRequest request, HttpServletResponse response) {
        String flag = request.getParameter("flag");

        return new ModelAndView("jsp/system/messagesetup", "message", messageServerService.findMessage()).addObject("flag", flag == null ? 0 : flag);
    }

    /**
     * 修改短信接口信息
     * 
     * @param request
     * @param messageServer
     * @return bindingResult
     */
    @RequestMapping("/updatmess")
    public ModelAndView updatMess(HttpServletRequest request, @Valid MessageServer messageServer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(REDIRECT));
        }

        try {
            messageServerService.updateMessage(messageServer);
            if ("1".equals(messageServer.getIsOpen())) {
                this.messageServerService.updateSmsUsedStatus(messageServer.getSmsId());
            }
            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "修改短信接口设置", request.getSession().getAttribute(OPERAPATH).toString());
        } catch (Exception e) {
            LOGGER.error("修改短信接口设置错误：=>",e);
        }
        return new ModelAndView(new RedirectView(REDIRECT));
    }

    /**
     * 修改短信接口启用状态
     * 
     * @param smsId
     * @return
     */
    @RequestMapping("/changeMessageSetUserdStatus")
    public ModelAndView changeMessageSetUserdStatus(Long smsId) {
        this.messageServerService.updateSmsUsedStatus(smsId);
        return new ModelAndView(new RedirectView(REDIRECT));
    }
}
