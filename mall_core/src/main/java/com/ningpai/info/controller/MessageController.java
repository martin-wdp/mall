/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.info.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.info.bean.MessageBean;
import com.ningpai.info.service.MessageService;
import com.ningpai.util.PageBean;
/**
 * 发送消息控制类
 * @author huangyi
 *
 */
@Controller
public class MessageController {
    /** 日志记录对象 */
    private static final MyLogger LOGGER = new MyLogger(MessageController.class);
    //Spring注入
    @Resource(name="MessageService")
    private MessageService messageService;
    /**
     * 消息修改页面跳转
     * @param map 消息封装对象
     * @param informId 消息主键ID
     * @return 消息修改页面
     */
    @RequestMapping("informUpdate")
    public ModelAndView update(int informId,ModelMap map){
        MessageBean message = messageService.selectById(informId);
        //非空判断   消息名称
        if(null != message.getInformSubject()){
            //日志记录
            LOGGER.info("消息页面跳转，当前消息的名称为："+message.getInformSubject());
        }
        map.put("message", message);
        return new ModelAndView("jsp/system/informSet");
    }
    /**
     * 修改确定
     * @param message 消息封装对象
     * @return ModelAndView 消息信息页面
     */
    @RequestMapping("informUpdateAction")
    public ModelAndView updateAction(MessageBean message){
        //非空判断   消息名称
        if(null != message.getInformSubject()){
            //日志记录
            LOGGER.info("修改信息、邮件对象，当前消息的名称为："+message.getInformSubject());
        }
        //修改
        messageService.updateInform(message);        
        return new ModelAndView(new RedirectView("informList.htm"));
    }
    
    /**
     * 消息列表
     * @param pb 分页参数对象
     * @param request HttpServletRequest
     * @return ModelAndView 消息列表页面
     */
    @RequestMapping("informList")
    public ModelAndView informList(PageBean pb,HttpServletRequest request){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("jsp/system/informList");
        mav.addObject("pageBean", messageService.selectList(pb));
        return mav; 
    }
    
    
}
