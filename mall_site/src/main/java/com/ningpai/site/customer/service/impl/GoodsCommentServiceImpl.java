/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.service.impl;

import com.ningpai.comment.bean.Comment;
import com.ningpai.comment.service.CommentServiceMapper;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.customer.service.CustomerPointServiceMapper;
import com.ningpai.other.util.IPAddress;
import com.ningpai.site.customer.mapper.GoodsCommentMapper;
import com.ningpai.site.customer.service.GoodsCommentService;
import com.ningpai.site.customer.vo.GoodsBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 商品评论ServiceImpl
 * 
 * @see com.ningpai.site.customer.service.GoodsCommentService
 * @author NINGPAI-zhangqiang
 * @since 2014年6月16日 上午10:37:49
 * @version 0.0.1
 */
@Service("goodsCommentService")
public class GoodsCommentServiceImpl implements GoodsCommentService {

    private static final String GOODSID = "goodsId";
    private static final String CUSTOMERID = "customerId";

    /**
     * 商品评论Mapper
     */
    private GoodsCommentMapper goodsCommentMapper;
    /**
     * 评论服务类接口
     */
    private CommentServiceMapper commentServiceMapper;
    /**
     * 会员积分接口
     */
    private CustomerPointServiceMapper customerPointServiceMapper;

    /**
     * 查询订单商品
     * 
     * @param goodsId
     *            商品编号
     * @param orderId
     *            订单编号
     * @param customerId
     *            会员编号
     * @return
     */
    @Override
    public GoodsBean selectOrderGoods(Long goodsId, Long orderId, Long customerId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put(GOODSID, goodsId);
            paramMap.put(CUSTOMERID, customerId);
            paramMap.put(ConstantUtil.ORDERID, orderId);
            // 查询订单商品
            return goodsCommentMapper.selectOrderGoods(paramMap);
        } finally {
            paramMap = null;
        }
    }

    /**
     * 添加商品评论
     * 
     * @param request
     * @param comment
     *            评论实体 {@link Comment}
     * @param orderId
     * @return
     */
    @Override
    @Transactional
    public int addGoodsComment(HttpServletRequest request, Comment comment, Long orderId) {
        // 添加评论 返回评论编号
        comment.setIsConsult("0");
        comment.setPublishTime(new Date());
        comment.setIsDisplay("1");
        comment.setDelFlag("0");
        comment.setPublishIp(IPAddress.getIpAddr(request));
        int score = Integer.parseInt(comment.getCommentScore().toString());
        if (score < 2) {
            comment.setCommentType("2");
        } else if (2 <= score && score <= 3) {
            comment.setCommentType("1");
        } else {
            comment.setCommentType("0");
        }
        // 添加商品评论
        commentServiceMapper.addGoodsComment(comment);
        // 添加评论积分
        customerPointServiceMapper.addIntegralByType(comment.getCustomerId(), "4");
        // 修改订单商品为已评价 并更新评论编号
        GoodsBean goodsBean = new GoodsBean();
        goodsBean.setCommentId(comment.getCommentId());
        goodsBean.setEvaluateFlag("1");
        goodsBean.setOrderId(orderId);
        goodsBean.setGoodsId(comment.getGoodsId());
        // 修改订单商品评论信息
        goodsCommentMapper.updateOrderGoods(goodsBean);
        return 0;
    }

    /**
     * 添加商品评论
     * 
     * @param request
     * @param comment
     *            评论实体 {@link Comment}
     * @return
     */
    @Override
    public Long addGoodsComment(HttpServletRequest request, Comment comment) {
        // 添加评论 返回评论编号
        comment.setIsConsult("0");
        comment.setPublishTime(new Date());
        comment.setIsDisplay("1");
        comment.setDelFlag("0");
        comment.setPublishIp(IPAddress.getIpAddr(request));
        int score = Integer.parseInt(comment.getCommentScore().toString());
        if (score < 2) {
            comment.setCommentType("2");
        } else if (2 <= score && score <= 3) {
            comment.setCommentType("1");
        } else {
            comment.setCommentType("0");
        }
        // 添加商品评论
        commentServiceMapper.addGoodsComment(comment);
        // 添加评论积分
        customerPointServiceMapper.addIntegralByType(comment.getCustomerId(), "4");
        // 修改订单商品为已评价 并更新评论编号
        GoodsBean goodsBean = new GoodsBean();
        goodsBean.setCommentId(comment.getCommentId());
        goodsBean.setEvaluateFlag("1");
        goodsBean.setOrderGoodsId(comment.getOrderGoodsId());
        goodsBean.setGoodsId(comment.getGoodsId());
        // 修改订单商品评论信息
        goodsCommentMapper.updateOrderGoods(goodsBean);
        return comment.getCommentId();

    }

    /**
     * 查询商品评论
     * 
     * @param goodsId
     *            商品编号 {@link Long}
     * @param orderId
     *            订单编号 {@link Long}
     * @param customerId
     *            会员编号 {@link Long}
     * @return
     */
    @Override
    public Comment selectGoodsComment(Long goodsId, Long orderId, Long customerId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        GoodsBean goodsBean = null;
        try {
            paramMap.put(GOODSID, goodsId);
            paramMap.put(CUSTOMERID, customerId);
            paramMap.put(ConstantUtil.ORDERID, orderId);
            // 查询订单商品
            goodsBean = goodsCommentMapper.selectOrderGoods(paramMap);
            // 根据评论编号 查询评论
            return commentServiceMapper.selectByCommentId(goodsBean.getCommentId());
        } finally {
            paramMap = null;
            goodsBean = null;
        }
    }

    /**
     * 查询订单商品
     * 
     * @param goodsId
     *            商品编号
     * @param orderId
     *            订单编号
     * @param customerId
     *            会员编号
     * @return
     */
    @Override
    public GoodsBean selectOrderGoodsToComment(Long goodsId, Long orderId, Long customerId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put(GOODSID, goodsId);
            paramMap.put(CUSTOMERID, customerId);
            paramMap.put(ConstantUtil.ORDERID, orderId);
            // 查询订单商品
            return goodsCommentMapper.selectOrderGoodsToComment(paramMap);
        } finally {
            paramMap = null;
        }
    }

    /**
     * 检验订单商品是否已评价
     * 
     * @param orderId
     *            订单编号 {@link Long}
     * @param goodsId
     *            商品编号 {@link Long}
     * @return
     */
    @Override
    public Long checkCommGoodFlag(Long orderId, Long goodsId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put(GOODSID, goodsId);
            paramMap.put(ConstantUtil.ORDERID, orderId);
            // 检验订单商品是否已评价
            return goodsCommentMapper.checkCommGoodFlag(paramMap);
        } finally {
            paramMap = null;
        }

    }

    /**
     * 第三方店铺评级
     * 
     * @param thirdId
     *            店铺Id{@link java.lang.Long}
     * @return
     */
    @Override
    public Comment selectSellerComment(Long thirdId) {
        // 查询第三方店铺的评级
        return commentServiceMapper.selectSellerComment(thirdId);
    }

    /**
     * 判断是订单商品是否是会员的
     * 
     * @param orderGoodsId
     *            订单货品编号
     * @param customerId
     *            用户编号
     * @param flag
     *            1 评论 2 晒单
     * @return boolean
     */
    @Override
    public boolean checkCommGoodIsUser(Long orderGoodsId, Long customerId, String flag) {
        // 封装容器
        Map<String, Object> map = new HashMap<>();
        // 订单商品编号
        map.put("orderGoodsId", orderGoodsId);
        // 会员编号
        map.put(CUSTOMERID, customerId);
        // 评论晒单 标记
        map.put("flag", flag);
        // 判断
        Long count = goodsCommentMapper.checkCommGoodIsUser(map);
        if (count > 0) {
            // 是
            return true;
        }
        // 否
        return false;
    }

    public GoodsCommentMapper getGoodsCommentMapper() {
        return goodsCommentMapper;
    }

    @Resource(name = "goodsCommentMapper")
    public void setGoodsCommentMapper(GoodsCommentMapper goodsCommentMapper) {
        this.goodsCommentMapper = goodsCommentMapper;
    }

    public CommentServiceMapper getCommentServiceMapper() {
        return commentServiceMapper;
    }

    @Resource(name = "commentServiceMapper")
    public void setCommentServiceMapper(CommentServiceMapper commentServiceMapper) {
        this.commentServiceMapper = commentServiceMapper;
    }

    public CustomerPointServiceMapper getCustomerPointServiceMapper() {
        return customerPointServiceMapper;
    }

    @Resource(name = "customerPointServiceMapper")
    public void setCustomerPointServiceMapper(CustomerPointServiceMapper customerPointServiceMapper) {
        this.customerPointServiceMapper = customerPointServiceMapper;
    }

}
