/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.comment.controller;

import com.ningpai.comment.bean.CommentReplay;
import com.ningpai.comment.service.CommentReplayServiceMapper;
import com.ningpai.comment.service.CommentServiceMapper;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.other.util.IPAddress;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 评论回复控制类
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月27日 下午5:31:31
 * @version 0.0.1
 */
@Controller("commReplayController")
public class CommReplayController {

    // spring注解
    private CommentReplayServiceMapper commentReplayServiceMapper;
    // spring注解
    private CommentServiceMapper commentServiceMapper;

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(CommReplayController.class);

    /**
     * 添加评论回复
     * 
     * @param request
     * @param replay
     * @return
     */
    @RequestMapping("/addCommReplay")
    public ModelAndView addCommReplay(HttpServletRequest request,HttpServletResponse response, @Valid CommentReplay replay) throws IOException{
        // 设置IP
        replay.setReplayIp(IPAddress.getIpAddr(request));
        // 插入评论回复
        commentReplayServiceMapper.insertSelective(replay);
        //新后台才用到的代码，返回新插入的回复id
        try {
            if("1".equals(request.getParameter("new"))) {
                PrintWriter pr = null;
                pr = response.getWriter();

                // 输出结果
                pr.print(replay.getReplayId());
                return null;
            }
        } catch (IOException e) {
            LOGGER.error("",e);
        }

        return new ModelAndView(new RedirectView("queryByCommentId.htm?commentId=" + replay.getCommentId() + ConstantUtil.CSRF + request.getSession().getAttribute("token")));
    }

    /**
     * 修改评论
     * 
     * @param request
     * @return
     */
    @RequestMapping("/updatecommentrep")
    @ResponseBody
    public int updateCommentRep(HttpServletRequest request, @Valid CommentReplay replay) {
         return commentReplayServiceMapper.updateCommentRep(replay);
        /*return new ModelAndView(new RedirectView("queryByCommentId.htm?commentId=" + replay.getCommentId() + ConstantUtil.CSRF + request.getSession().getAttribute("token")));*/
    }

    public CommentServiceMapper getCommentServiceMapper() {
        return commentServiceMapper;
    }

    @Resource(name = "commentServiceMapper")
    public void setCommentServiceMapper(CommentServiceMapper commentServiceMapper) {
        this.commentServiceMapper = commentServiceMapper;
    }

    public CommentReplayServiceMapper getCommentReplayServiceMapper() {
        return commentReplayServiceMapper;
    }

    @Resource(name = "commentReplayServiceMapper")
    public void setCommentReplayServiceMapper(CommentReplayServiceMapper commentReplayServiceMapper) {
        this.commentReplayServiceMapper = commentReplayServiceMapper;
    }

}
