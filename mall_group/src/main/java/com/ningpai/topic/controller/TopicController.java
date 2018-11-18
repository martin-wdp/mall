/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.topic.controller;

import com.ningpai.topic.bean.GroupTopic;
import com.ningpai.topic.service.TopicService;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 话题管理控制器后台
 * 
 * @author qiyuanyuan
 * 
 */
@Controller
public class TopicController {

    /** 记录日志对象 */
    private static final Logger LOGGER = Logger.getLogger(TopicController.class);

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    private TopicService topicService;

    /**
     * 公开话题列表
     * 
     * @param request
     * @return
     */
    @RequestMapping("/publictopic")
    public ModelAndView publicTopic(HttpServletRequest request, PageBean pb, GroupTopic topic, SelectBean selectBean, String createtime, String createtimeto) {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            topic.setTopicCreateTime(createtime == null || "".equals(createtime) ? null : formatDate.parse(createtime));
            topic.setTopicCreateTimeTo(createtimeto == null || "".equals(createtimeto) ? null : formatDate.parse(createtimeto));
        } catch (ParseException e) {
            LOGGER.error("公开话题列表查询失败！", e);
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        topic.setGroupSecret("0");
        resultMap.put("pb", topicService.topicList(pb, topic, selectBean));
        // 参数设置到作用域
        request.setAttribute("selectBean", selectBean);
        return new ModelAndView("jsp/topic/publictopic").addAllObjects(resultMap).addObject("tp", topic);
    }

    /**
     * 私密话题列表
     * 
     * @param request
     * @return
     */
    @RequestMapping("/privatetopic")
    public ModelAndView privateTopic(HttpServletRequest request, PageBean pb, GroupTopic topic, SelectBean selectBean, String createtime, String createtimeto) {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            topic.setTopicCreateTime(createtime == null || "".equals(createtime) ? null : formatDate.parse(createtime));
            topic.setTopicCreateTimeTo(createtimeto == null || "".equals(createtimeto) ? null : formatDate.parse(createtimeto));
        } catch (ParseException e) {
            LOGGER.error("私密话题列表查询，时间解析失败！", e);
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        topic.setGroupSecret("1");
        resultMap.put("pb", topicService.topicList(pb, topic, selectBean));
        return new ModelAndView("jsp/topic/privatetopic").addAllObjects(resultMap).addObject("tp", topic);
    }

    /**
     * 跳转修改话题页面
     * 
     * @param response
     * @param topicId
     *            话题Id{@link java.lang.Long}
     * @return
     */
    @RequestMapping("/updategrouptopic")
    public ModelAndView updateTopic(HttpServletResponse response, Long topicId) {
        return new ModelAndView("jsp/topic/updatetopic").addObject("topic", topicService.selectTopicById(topicId));
    }

    /**
     * 保存修改后的话题
     * 
     * @param response
     * @param request
     * @param topic
     *            话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    // @RequestMapping("/savegrouptopic")
    // public ModelAndView editGroupTopic(HttpServletResponse response,
    // HttpServletRequest request, GroupTopic topic) {
    // response.setContentType(ValueUtil.REQ_SETCONTENT);
    // try {
    // pw = response.getWriter();
    // pw.print(topicService.editTopic(topic));
    // String customerName = (String) request.getSession().getAttribute(NAME);
    // OperaLogUtil.addOperaLog(request, customerName, "修改话题",
    // request.getSession().getAttribute(OPERAPATH) + ",用户名:" + customerName);
    // } catch (IOException e) {
    // LOGGER.error("保存修改后的话题失败！", e);
    // // e.printStackTrace();
    // } finally {
    // if (pw != null) {
    // pw.close();
    // }
    // }
    // return null;
    // }

    /**
     * 根据话题Id删除话题
     * 
     * @param response
     * @param request
     * @return
     */
    // @RequestMapping("/deletegrouptopic")
    // public ModelAndView deleteTopic(HttpServletResponse response,
    // HttpServletRequest request) {
    // response.setContentType(ValueUtil.REQ_SETCONTENT);
    // try {
    // pw = response.getWriter();
    // pw.print(topicService.deleteTopic(request.getParameterValues("topicId[]")));
    // String customerName = (String) request.getSession().getAttribute(NAME);
    // OperaLogUtil.addOperaLog(request, customerName, "删除话题",
    // request.getSession().getAttribute(OPERAPATH) + ",用户名:" + customerName);
    // } catch (IOException e) {
    // LOGGER.error("根据话题Id删除话题失败！", e);
    // } finally {
    // if (pw != null) {
    // pw.close();
    // }
    // }
    // return null;
    // }

    /**
     * 根据话题Id申请话题到首页
     * 
     * @param response
     * @param request
     * @return
     */
    // @RequestMapping("/toindex")
    // public ModelAndView indexTopic(HttpServletResponse response,
    // HttpServletRequest request, String topicIndexView) {
    // response.setContentType(ValueUtil.REQ_SETCONTENT);
    // try {
    // pw = response.getWriter();
    // pw.print(topicService.updateToIndex(request.getParameterValues("topicId[]"),
    // topicIndexView));
    // String customerName = (String) request.getSession().getAttribute(NAME);
    // OperaLogUtil.addOperaLog(request, customerName, "申请话题到首页",
    // request.getSession().getAttribute(OPERAPATH) + ",用户名:" + customerName);
    // } catch (IOException e) {
    // LOGGER.error("根据话题Id申请话题到首页失败！", e);
    // } finally {
    // if (pw != null) {
    // pw.close();
    // }
    // }
    // return null;
    // }

    /**
     * 根据话题Id查询话题详情
     * 
     * @param topicId
     *            话题id{@link java.lang.Long}
     * @return
     */
    @RequestMapping("/publictopicdetail")
    public ModelAndView topicDetail(Long topicId) {
        return new ModelAndView("jsp/topic/topicdetail").addObject("topic", topicService.selectTopicById(topicId));
    }

    /**
     * 根据话题Id查询话题详情
     * 
     * @param topicId
     *            话题id{@link java.lang.Long}
     * @return
     */
    @RequestMapping("/privatetopicdetailNew")
    @ResponseBody
    public GroupTopic privatetopicDetail(Long topicId) {
        GroupTopic groupTopic = topicService.selectTopicById(topicId);
        if (null != groupTopic.getTopicTitle()) {
            LOGGER.info("获取【" + groupTopic.getTopicTitle() + "】的详细信息");
        }
        return groupTopic;
    }

    /**
     * 根据话题Id设置为使用心得
     * 
     * @return
     */
    // @RequestMapping("/useexperience")
    // public ModelAndView useException(HttpServletResponse response,
    // HttpServletRequest request, String topicIsUse) {
    // response.setContentType(ValueUtil.REQ_SETCONTENT);
    // try {
    // pw = response.getWriter();
    // pw.print(topicService.useException(request.getParameterValues("topicId[]"),
    // topicIsUse));
    // String customerName = (String) request.getSession().getAttribute(NAME);
    // OperaLogUtil.addOperaLog(request, customerName, "设置为使用心得",
    // request.getSession().getAttribute(OPERAPATH) + ",用户名:" + customerName);
    // } catch (IOException e) {
    // LOGGER.error("根据话题Id申请话题到首页失败！", e);
    // } finally {
    // if (pw != null) {
    // pw.close();
    // }
    // }
    // return null;
    // }

    public TopicService getTopicService() {
        return topicService;
    }

    @Resource(name = "TopicService")
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

}
