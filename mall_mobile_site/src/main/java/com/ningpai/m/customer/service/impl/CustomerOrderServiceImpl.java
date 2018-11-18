/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ningpai.comment.bean.Comment;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.customer.service.CustomerPointServiceMapper;
import com.ningpai.m.customer.mapper.CustomerOrderMapper;
import com.ningpai.m.customer.service.CustomerOrderService;
import com.ningpai.m.customer.vo.CustomerConstants;
import com.ningpai.m.customer.vo.GoodsBean;
import com.ningpai.other.util.IPAddress;
import com.ningpai.util.PageBean;

/**
 * @see com.ningpai.m.customer.service.CustomerOrderService
 * @author jiping
 * @since 2014年8月20日 下午1:55:18
 * @version 0.0.1
 */
@Service("customerOrderServiceM")
public class CustomerOrderServiceImpl implements CustomerOrderService {
    // spring注解
    private CustomerOrderMapper customerOrderMapper;

    private CustomerPointServiceMapper customerPointServiceMapper;

    /*
     * 
     * 
     * @see com.ningpai.m.customer.service.CustomerOrderService#queryAllMyOrders(java.util.Map, com.ningpai.util.PageBean)
     */
    @Override
    public PageBean queryAllMyOrders(Map<String, Object> paramMap, PageBean pb) {
        Long count = customerOrderMapper.searchTotalCount(paramMap);
        pb.setRows(Integer.parseInt(count == null ? "0" : count.toString()));
        if (pb.getPageNo() > pb.getLastPageNo()) {
            pb.setPageNo(pb.getLastPageNo());
        }
        if (pb.getPageNo() == 0) {
            pb.setPageNo(1);
        }
        paramMap.put(CustomerConstants.STARTNUM, pb.getStartRowNum());
        paramMap.put(CustomerConstants.ENDNUM, pb.getEndRowNum());
        pb.setList(customerOrderMapper.queryAllMyOrders(paramMap));
        return pb;
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.service.CustomerOrderService#queryOrderByCustIdAndOrderId(java.lang.Long, java.lang.Long)
     */
    @Override
    public Object queryOrderByCustIdAndOrderId(Long orderId, Long customerId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put("customerId", customerId);
            paramMap.put(ConstantUtil.ORDERID, orderId);
            return customerOrderMapper.queryOrderByParamMap(paramMap);
        } finally {
            paramMap = null;
        }
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.service.CustomerOrderService#selectNotice(java.lang.Long)
     */
    @Override
    public Map<String, Object> selectNotice(Long customerId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put(CustomerConstants.CUSTOMERID, customerId);
            paramMap.put("flag", 0);
            resultMap.put("pendingNum", customerOrderMapper.selectpendingOrderNotice(paramMap));
            paramMap.put("flag", 1);
            resultMap.put("commentNum", customerOrderMapper.selectpendingOrderNotice(paramMap));
            return resultMap;
        } finally {
            resultMap = null;
            paramMap = null;
        }
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.service.CustomerOrderService#cancelOrder(java.lang.Long, java.lang.String)
     */
    @Override
    public int cancelOrder(Long orderId, String reason) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put(ConstantUtil.ORDERID, orderId);
            paramMap.put("reason", reason);
            return customerOrderMapper.cancelOrder(paramMap);
        } finally {
            paramMap = null;
        }
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.service.CustomerOrderService#comfirmofGoods(java.lang.Long)
     */
    @Override
    public int comfirmofGoods(Long orderId) {
        return customerOrderMapper.comfirmofGoods(orderId);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.service.CustomerOrderService#addGoodsComment(com.ningpai.comment.bean.Comment)
     */
    @Override
    public int addGoodsComment(Comment comment) {
        return customerOrderMapper.addGoodsComment(comment);
    }

    @Override
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
        customerOrderMapper.addGoodsComment(comment);
        // 添加评论积分
        customerPointServiceMapper.addIntegralByType(comment.getCustomerId(), "4");
        // 修改订单商品为已评价 并更新评论编号
        GoodsBean goodsBean = new GoodsBean();
        goodsBean.setCommentId(comment.getCommentId());
        goodsBean.setEvaluateFlag("1");
        goodsBean.setOrderId(orderId);
        goodsBean.setGoodsId(comment.getGoodsId());
        customerOrderMapper.updateOrderGoods(goodsBean);
        return 0;
    }

    public CustomerOrderMapper getCustomerOrderMapper() {
        return customerOrderMapper;
    }

    @Resource(name = "customerOrderMapperM")
    public void setCustomerOrderMapper(CustomerOrderMapper customerOrderMapper) {
        this.customerOrderMapper = customerOrderMapper;
    }

    public CustomerPointServiceMapper getCustomerPointServiceMapper() {
        return customerPointServiceMapper;
    }

    @Resource(name = "customerPointServiceMapper")
    public void setCustomerPointServiceMapper(CustomerPointServiceMapper customerPointServiceMapper) {
        this.customerPointServiceMapper = customerPointServiceMapper;
    }

}
