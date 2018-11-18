/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.comment.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ningpai.comment.bean.ValueUtil;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.comment.bean.Comment;
import com.ningpai.comment.service.CommentReplayServiceMapper;
import com.ningpai.comment.service.CommentServiceMapper;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.customer.bean.Customer;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.util.PageBean;

/**
 * 评论控制类
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月24日 下午7:03:45
 * @version 0.0.1
 */
@Controller("commentController")
public class CommentController {

    // spring 注解
    private CommentServiceMapper commentServiceMapper;
    // spring注解
    private CommentReplayServiceMapper commentReplayServiceMapper;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(CommentController.class);

    /**
     * 初始化评论列表
     * 
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @return
     */
    @RequestMapping("/initComment")
    public ModelAndView initCommnet(PageBean pageBean) {
        // 设置访问路径
        pageBean.setUrl("initComment.htm");
        return new ModelAndView(CustomerConstantStr.COMMENTLIST, CustomerConstantStr.PAGEBEAN, commentServiceMapper.selectAllComment(pageBean, null));
    }

    /**
     * 按条件查询评论
     * 
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @param comment
     *            {@link com.ningpai.comment.bean.Comment}
     * @return
     */
    @RequestMapping("/queryByComment")
    public ModelAndView queryByComment(HttpServletRequest request, String publishtime, String publishtimeTo, PageBean pageBean, @Valid Comment comment, String[] attr) throws UnsupportedEncodingException, ParseException {
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 指定格式化参数
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 格式化时间
            comment.setPublishTime(publishtime == null || "".equals(publishtime) ? null : formatDate.parse(publishtime));
            comment.setPublishTimeTo(publishtimeTo == null || "".equals(publishtimeTo) ? null : formatDate.parse(publishtimeTo));
            resultMap.put(CustomerConstantStr.PAGEBEAN, commentServiceMapper.selectAllComment(pageBean, comment));
            resultMap.put(CustomerConstantStr.COMMENT, comment);
            resultMap.put("attr", attr);
            // 设置跳转路径
            pageBean.setUrl("queryByComment.htm");
            return new ModelAndView(CustomerConstantStr.COMMENTLIST).addAllObjects(resultMap);
        } finally {
            resultMap = null;
            formatDate = null;
        }
    }

    /**
     * 按条件查询评论
     * 
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @param comment
     *            {@link com.ningpai.comment.bean.Comment}
     * @return
     */
    @RequestMapping("/queryByCommentId")
    public ModelAndView queryByCommentId(HttpServletRequest request, String publishtime, String publishtimeTo, Long commentId) {
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap.put("comment", commentServiceMapper.selectByCommentId(commentId));
            resultMap.put("replist", commentReplayServiceMapper.selectByCommentId(commentId));
            return new ModelAndView(CustomerConstantStr.CONSULTDETAIL).addAllObjects(resultMap);
        } finally {
            resultMap = null;
        }

    }

    /**
     * 按条件查询评论ajax
     *
     * @param commentId
     *            咨询id
     * @return
     */
    @RequestMapping("/queryByCommentIdAjax")
    @ResponseBody
    public Map<String, Object> queryByCommentIdAjax(HttpServletRequest request,  Long commentId) {
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap.put("comment", commentServiceMapper.selectByCommentId(commentId));
            resultMap.put("replist", commentReplayServiceMapper.selectByCommentId(commentId));
            return resultMap;
        } finally {
            resultMap = null;
        }
    }

    /**
     * 删除评论
     * 
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/deleteComment")
    public ModelAndView deleteComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
            String[] commentIds = request.getParameterValues("parameterIds[]");
            if(commentIds==null) {
                commentIds = request.getParameterValues("parameterIds");
            }
            int num = commentServiceMapper.deleteComment(commentIds);
            //操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "批量删除评论", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) +
                    "批量删除评论,用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 输出结果
            pr.print(num);
        } finally {
            if (pr != null) {
                pr = null;
            }
        }
        return null;
    }

    /**
     * 修改评论
     * 
     * @param request
     * @param comment
     * @return
     */
    @RequestMapping("/updatecomment")
    @ResponseBody
    public int  updateComment(HttpServletRequest request, @Valid Comment comment) {
       return commentServiceMapper.updateComment(comment);
        /*if(comment.getCommentContent().length()>10){
            String str = comment.getCommentContent().substring(0,10);
            comment.setCommentContent(str);
            //操作日志
            //截取评论内容前10个字符用于操作日志显示
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "修改评论", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) +
                    "批量删除评论,用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));
            LOGGER.info("修改【"+comment.getGoodsName()+"】的评论");
        }
        return new ModelAndView(new RedirectView("queryByCommentId.htm?commentId=" + comment.getCommentId() + ConstantUtil.CSRF + request.getSession().getAttribute("token")));*/
    }

    /**
     * 初始化咨询列表
     * 
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @return
     */
    @RequestMapping("/initConsult")
    public ModelAndView initConsult(PageBean pageBean, HttpServletRequest request) {
        // 设置跳转路径
        pageBean.setUrl("initConsult.htm");
        return new ModelAndView(CustomerConstantStr.CONSULTLIST, CustomerConstantStr.PAGEBEAN, commentServiceMapper.selectAllConsult(pageBean, null));
    }

    /**
     * 按条件查询咨询
     * 
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @param comment
     *            {@link com.ningpai.comment.bean.Comment}
     * @return
     * @throws ParseException
     */
    @RequestMapping("/queryByConsult")
    public ModelAndView queryByConsult(HttpServletRequest request, String publishtime, String publishtimeTo, PageBean pageBean, @Valid Comment comment, String[] attr) throws UnsupportedEncodingException, ParseException {
        // 指定格式化日期格式
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        // 检查查询条件
        try {
            // 格式化日期
            comment.setPublishTime(publishtime == null || "".equals(publishtime) ? null : formatDate.parse(publishtime));
            comment.setPublishTimeTo(publishtimeTo == null || "".equals(publishtimeTo) ? null : formatDate.parse(publishtimeTo));
        } finally {
            formatDate = null;
        }
        // 设置跳转路径
        pageBean.setUrl("queryByConsult.htm");
        return new ModelAndView(CustomerConstantStr.CONSULTLIST, CustomerConstantStr.PAGEBEAN, commentServiceMapper.selectAllConsult(pageBean, comment)).addObject(CustomerConstantStr.COMMENT, comment).addObject("attr", attr);
    }

    /**
     * 判断查询条件
     * 
     * @param comment
     */
    public void check(Comment comment) {

        char ch = ',';
        if (comment.getCustomerNickname() != null) {
            int indexu = comment.getCustomerNickname().indexOf(ch);
            comment.setCustomerNickname(comment.getCustomerNickname().substring(0, indexu == -1 ? comment.getCustomerNickname().length() : indexu));
        }
        if (comment.getGoodsName() != null) {
            int indexr = comment.getGoodsName().indexOf(ch);
            comment.setGoodsName(comment.getGoodsName().substring(0, indexr == -1 ? comment.getGoodsName().length() : indexr));
        }
    }
    
    /**
     * 导出评论
     * @param response
     */
    @RequestMapping("exportComment")
    public void exporComment(HttpServletResponse response) {
        LOGGER.info("导出评论");
        commentServiceMapper.exportComment(response);
    }
    
    /**
     * 导出商品评论模板
     * @param response
     */
    @RequestMapping("exportCommentTemp")
    public void exportCommentTemp(HttpServletResponse response) {
        commentServiceMapper.exportCommentTemp(response);
    }

    
    /**
     * 导入商品评论
     * 
     * @param request
     *            请求
     * @param response
     *            响应对象
     * @param goodsCateId
     *            导入的分类ID
     */
    @RequestMapping("/importComment")
    public void importComment(HttpServletRequest request, HttpServletResponse response, Long goodsCateId, MultipartHttpServletRequest multiRequest) {
        String result = "200";
        try {
            result = this.commentServiceMapper.importCommentByExcel(request, response, multiRequest);
            PrintWriter out = response.getWriter();
            out.append("<script>parent.import_callback('"+result+"');</script>");
            out.flush();
            LOGGER.info("导入商品评论");
        } catch (Exception e) {
              Customer cust = (Customer) request.getSession().getAttribute("cust");
              OperaLogUtil.addOperaException(cust.getCustomerUsername(), e, request);
        } 
    }

    public CommentReplayServiceMapper getCommentReplayServiceMapper() {
        return commentReplayServiceMapper;
    }

    @Resource(name = "commentReplayServiceMapper")
    public void setCommentReplayServiceMapper(CommentReplayServiceMapper commentReplayServiceMapper) {
        this.commentReplayServiceMapper = commentReplayServiceMapper;
    }

    public CommentServiceMapper getCommentServiceMapper() {
        return commentServiceMapper;
    }

    @Resource(name = "commentServiceMapper")
    public void setCommentServiceMapper(CommentServiceMapper commentServiceMapper) {
        this.commentServiceMapper = commentServiceMapper;
    }
}
