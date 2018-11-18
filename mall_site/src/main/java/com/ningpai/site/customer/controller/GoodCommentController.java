/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.controller;

import com.ningpai.comment.bean.Comment;
import com.ningpai.comment.service.CommentServiceMapper;
import com.ningpai.comment.service.ShareService;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.goods.service.GoodsReleTagService;
import com.ningpai.goods.vo.GoodsReleTagVo;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.other.util.LoginUtil;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.service.GoodsCommentService;
import com.ningpai.site.customer.vo.CustomerAllInfo;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.temp.service.MegawizardService;
import com.ningpai.temp.service.TempService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品评价Controller
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年6月16日 上午10:02:28
 * @version 0.0.1
 */
@Controller
public class GoodCommentController {
    /**
     * 商品评论Service
     */
    private GoodsCommentService goodsCommentService;
    /**
     * 评论服务类接口
     */
    private CommentServiceMapper commentServiceMapper;
    /**
     * 商品关联标签Service
     */
    private GoodsReleTagService goodsReleTagService;
    /**
     * 晒单Service
     */
    private ShareService shareServiceMapper;
    /**
     * spring 注解 会员service
     */
    private CustomerServiceMapper customerServiceMapper;

    /**
     * 站点信息
     */
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    /**
     * 获取头尾
     */
    private TopAndBottomService topAndBottomService;

    public static final int NUM24 = 24;

    /**
     * 页面说明Service
     */
    private MegawizardService megawizardSerivce;

    /** 模板设置业务类 */
    private TempService tempService;

    /**
     * spring 注解 会员service
     */
    private CustomerServiceInterface customerServiceInterface;

    /**
     * 跳转会员中心--评论页
     * 
     * @param orderId
     * @return
     */
    @RequestMapping("/tocomment")
    public ModelAndView toShare(HttpServletRequest request, Long orderId, PageBean pb) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ModelAndView mav = null;
        pb.setPageSize(5);
        pb.setUrl("tocomment.htm");
        // 验证登录
        if (LoginUtil.checkLoginStatus(request)) {
            // 查询会员评论 前台我的评论使用
            resultMap.put(CustomerConstantStr.PB, commentServiceMapper.queryCommentByCust(orderId, (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID), pb));
            // 根据页面类型和模板ID查询单个页面说明
            resultMap.put("explain", megawizardSerivce.selectByType(1, Long.parseLong(tempService.getCurrTemp().getTempId() + "")));
            mav = new ModelAndView(CustomerConstantStr.NEWCOMMENT);
            mav.addAllObjects(resultMap);
            return topAndBottomService.getTopAndBottom(mav);
        } else {
            return new ModelAndView(new RedirectView("login.html?customer/myorder.html"));
        }
    }

    /**
     * 记载商品标签
     * 
     * @param goodsId
     * @return
     */
    @RequestMapping("/loadgoodstag")
    @ResponseBody
    public List<GoodsReleTagVo> loadGoodsTag(Long goodsId) {
        // 根据货品ID查询关联的记录
        return goodsReleTagService.queryreleListByProductId(goodsId);
    }

    /**
     * 跳转商品品论页面
     * 
     * @param request
     * @param goodsId
     *            商品编号
     * @param orderId
     *            订单编号
     * @return ModelAndView
     */
    @RequestMapping("/tradecomment")
    public ModelAndView toGoodsComment(HttpServletRequest request, Long goodsId, Long orderId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // 根据货品ID查询关联的记录
            resultMap.put("tagList", goodsReleTagService.queryreleListByProductId(goodsId));
            // 查询订单商品
            resultMap.put("good", goodsCommentService.selectOrderGoodsToComment(goodsId, orderId, (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));
            return topAndBottomService.getTopAndBottom(new ModelAndView(CustomerConstantStr.CUSTOMER + "/commgood").addAllObjects(resultMap));
        } finally {
            resultMap = null;
        }

    }

    /**
     * 添加商品评论
     * 
     * @param request
     * @param comment
     * @return
     */
    @RequestMapping("/addgoodscomment")
    public ModelAndView addGoodsComment(HttpServletRequest request, @Valid Comment comment, Long orderId) {
        if (LoginUtil.checkLoginStatus(request)) {
            // 当前登录会员id
            Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
            // 当前登录成功的会员信息
            CustomerAllInfo customerAllInfo = customerServiceInterface.selectByPrimaryKey(customerId);
            // 非空验证 商品名称
            if (null != comment.getGoodsName()) {
                // 操作日志
                OperaLogUtil.addOperaLog(request, customerAllInfo.getCustomerUsername(), "添加商品评论", "添加评论的商品为【" + comment.getGoodsName() + "】");
            }
            // 添加商品评论
            goodsCommentService.addGoodsComment(request, comment, orderId);
            return topAndBottomService.getTopAndBottom(new ModelAndView(new RedirectView(request.getContextPath() + "/comment-" + orderId + ".html")));
        } else {
            return new ModelAndView(new RedirectView(request.getContextPath() + "/login.html?url=customer/myorder.html"));
        }
    }

    /**
     * 添加商品评论
     * 
     * @param request
     * @param orderId
     * @return
     */
    @RequestMapping("/viewcomment")
    public ModelAndView viewComment(HttpServletRequest request, Long goodsId, Long orderId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // 当前登录会员id
            Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
            // 当前登录成功的会员信息
            CustomerAllInfo customerAllInfo = customerServiceInterface.selectByPrimaryKey(customerId);
            // 获取单个评论对象
            Comment comment = goodsCommentService.selectGoodsComment(goodsId, orderId, customerId);
            // 非空验证 商品名称
            if (null != comment.getGoodsName()) {
                // 操作日志
                OperaLogUtil.addOperaLog(request, customerAllInfo.getCustomerUsername(), "添加商品评论", "添加评论的商品为【" + comment.getGoodsName() + "】");
            }
            // 验证登录
            if (LoginUtil.checkLoginStatus(request)) {
                resultMap.put("comment", comment);
                // 查询订单商品
                resultMap.put("good", goodsCommentService.selectOrderGoods(goodsId, orderId, (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));
                return topAndBottomService.getTopAndBottom(new ModelAndView("customer/showcommgood").addAllObjects(resultMap));
            } else {
                return new ModelAndView(new RedirectView(request.getContextPath() + "/login.html?url=customer/myorder.html"));
            }

        } finally {
            resultMap = null;
        }

    }

    /**
     * 商品咨询
     * 
     * @param request
     * @return {@link ModelAndView}
     */
    @RequestMapping("/consult")
    public ModelAndView toViewConsult(HttpServletRequest request, PageBean pageBean, String flag) {
        pageBean.setUrl("2".equals(flag) ? "customer/consult" : "customer/consult-" + flag);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 设置显示行数
        pageBean.setPageSize(5);
        try {
            // 查询会员咨询
            resultMap.put(CustomerConstantStr.PB, commentServiceMapper.queryCustConsult((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID), flag, pageBean));
            resultMap.put("flag", flag);
            // 验证登录
            if (LoginUtil.checkLoginStatus(request)) {
                return topAndBottomService.getTopAndBottom(new ModelAndView(CustomerConstantStr.NEWCONSULT).addAllObjects(resultMap));
            } else {
                return new ModelAndView(new RedirectView(request.getContextPath() + "/login.html?url=customer/consult.html"));
            }
        } finally {
            resultMap = null;
        }
    }

    /**
     * 商品评论
     * 
     * @param request
     * @return {@link ModelAndView}
     */
    @RequestMapping("/comment")
    public ModelAndView toViewComment(HttpServletRequest request, PageBean pageBean) {
        pageBean.setUrl("customer/comment");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 设置行数
        pageBean.setPageSize(5);
        try {
            // 查询会员评论
            resultMap
                    .put(CustomerConstantStr.PB, commentServiceMapper.queryCommentByCust(null, (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID), pageBean));
            // 根据页面类型和模板ID查询单个页面说明
            resultMap.put("explain", megawizardSerivce.selectByType(1, Long.parseLong(tempService.getCurrTemp().getTempId() + "")));
            // 验证登录
            if (LoginUtil.checkLoginStatus(request)) {
                return topAndBottomService.getTopAndBottom(new ModelAndView(CustomerConstantStr.NEWCOMMENT).addAllObjects(resultMap));
            } else {
                return new ModelAndView(new RedirectView(request.getContextPath() + "/login.html?url=customer/comment.html"));
            }
        } finally {
            resultMap = null;
        }
    }

    /**
     * 根据订单商品id查看评论晒单 --会员中心-商品评价
     * 
     * @param request
     * @param orderGoodsId
     *            订单商品的编号
     * @return Comment
     */

    @RequestMapping("/findCommentShare")
    @ResponseBody
    public Comment findCommentShare(HttpServletRequest request, Long orderGoodsId) {
        if (LoginUtil.checkLoginStatus(request)) {
            Long customerId = (Long) request.getSession().getAttribute("customerId");
            // 根据订单商品编号和会员编号查询商品评论晒单信息
            return commentServiceMapper.queryCommentByOrderGoodsId(orderGoodsId, customerId);
        }
        return null;
    }

    /**
     * 晒单详情
     * 
     * @param request
     * @param shareId
     * @return
     */
    @RequestMapping("/share/detail")
    public ModelAndView bB(HttpServletRequest request, Long shareId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 晒单详情
        map.put("share", shareServiceMapper.queryShareById(shareId));
        // 热门晒单
        map.put("topShare", shareServiceMapper.getTopShare(NUM24));
        // 热门晒单
        map.put("good", shareServiceMapper.getTopShare(NUM24));
        // 查询站点信息
        map.put("bs", basicSetService.findBasicSet());
        return topAndBottomService.getTopAndBottom(new ModelAndView("/customer/sharedetail").addObject("map", map));
    }

    /**
     * 检验订单商品是否已评价
     * 
     * @param orderId
     *            订单id
     * @param goodsId
     *            商品id
     * @return
     */
    @RequestMapping("/checkcommgoodflag")
    @ResponseBody
    public Long checkCommGoodFlag(Long orderId, Long goodsId) {
        // 检验订单商品是否已评价
        return goodsCommentService.checkCommGoodFlag(orderId, goodsId);
    }

    public GoodsCommentService getGoodsCommentService() {
        return goodsCommentService;
    }

    @Resource(name = "goodsCommentService")
    public void setGoodsCommentService(GoodsCommentService goodsCommentService) {
        this.goodsCommentService = goodsCommentService;
    }

    public CommentServiceMapper getCommentServiceMapper() {
        return commentServiceMapper;
    }

    @Resource(name = "commentServiceMapper")
    public void setCommentServiceMapper(CommentServiceMapper commentServiceMapper) {
        this.commentServiceMapper = commentServiceMapper;
    }

    public GoodsReleTagService getGoodsReleTagService() {
        return goodsReleTagService;
    }

    @Resource(name = "GoodsReleTagService")
    public void setGoodsReleTagService(GoodsReleTagService goodsReleTagService) {
        this.goodsReleTagService = goodsReleTagService;
    }

    public TopAndBottomService getTopAndBottomService() {
        return topAndBottomService;
    }

    @Resource(name = "TopAndBottomService")
    public void setTopAndBottomService(TopAndBottomService topAndBottomService) {
        this.topAndBottomService = topAndBottomService;
    }

    public ShareService getShareServiceMapper() {
        return shareServiceMapper;
    }

    @Resource(name = "shareServiceNew")
    public void setShareServiceMapper(ShareService shareServiceMapper) {
        this.shareServiceMapper = shareServiceMapper;
    }

    public CustomerServiceMapper getCustomerServiceMapper() {
        return customerServiceMapper;
    }

    @Resource(name = "customerServiceMapper")
    public void setCustomerServiceMapper(CustomerServiceMapper customerServiceMapper) {
        this.customerServiceMapper = customerServiceMapper;
    }

    public MegawizardService getMegawizardSerivce() {
        return megawizardSerivce;
    }

    @Resource(name = "MegawizardService")
    public void setMegawizardSerivce(MegawizardService megawizardSerivce) {
        this.megawizardSerivce = megawizardSerivce;
    }

    public TempService getTempService() {
        return tempService;
    }

    @Resource(name = "TempService")
    public void setTempService(TempService tempService) {
        this.tempService = tempService;
    }

    public CustomerServiceInterface getCustomerServiceInterface() {
        return customerServiceInterface;
    }

    /**
     * spring 注入属性
     * 
     * @param customerServiceInterface
     */
    @Resource(name = "customerServiceSite")
    public void setCustomerServiceInterface(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

}
