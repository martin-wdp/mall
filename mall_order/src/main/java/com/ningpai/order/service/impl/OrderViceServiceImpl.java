/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.order.bean.OrderVice;
import com.ningpai.order.dao.OrderViceMapper;
import com.ningpai.order.service.OrderViceService;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;

/**
 * 抢购团购service
 * 
 * @author ggn
 *
 */
@Service("OrderViceService")
public class OrderViceServiceImpl implements OrderViceService {

    /** 货品 **/
    @Resource(name = "GoodsProductService")
    private GoodsProductService goodsProductService;

    /** 团购抢购 **/
    @Resource(name = "OrderViceMapper")
    private OrderViceMapper mapper;

    /**
     * 抢购团购订单列表
     * 
     * @param orderVice
     *            查询参赛
     * @param pageBean
     *            分页参数
     * @return
     */
    @Override
    public PageBean searchOrderList(OrderVice orderVice, PageBean pageBean) {
        orderVice.setDelFlag("0");
        // 分装实体类属性
        Map<String, Object> paramMap = MapUtil.getParamsMap(orderVice);
        paramMap.put("startRowNum", pageBean.getStartRowNum());
        paramMap.put("endRowNum", pageBean.getEndRowNum());
        // 查询总数
        pageBean.setRows(mapper.searchOrderViceCount(paramMap));
        // 查询条件封装
        paramMap.put("start", pageBean.getStartRowNum());
        paramMap.put("number", pageBean.getEndRowNum());
        try {
            // 查询列表页
            pageBean.setList(mapper.searchOrderViceList(paramMap));
        } finally {
            paramMap = null;
        }
        return pageBean;
    }

    /**
     * 订单详情
     * 
     * @param orderId
     *            主键id
     * @return
     */
    @Override
    public OrderVice selectDetails(Long orderId) {
        OrderVice orderVice = mapper.selectByPrimaryKey(orderId);
        // 设置商品实体
        orderVice.setGoodsProductVo(goodsProductService.queryByPrimaryId(orderVice.getGoodsInfoId()));
        return orderVice;
    }

    /**
     * 插入订单
     * 
     * @param orderVice
     *            订单信息
     * @return
     */
    @Override
    public int insertOrder(OrderVice orderVice) {
        return mapper.insertSelective(orderVice);
    }

    /**
     * 根据订单编号查询订单信息
     * 
     * @param orderCode
     *            订单编号
     * @return
     */
    @Override
    public OrderVice payOrder(String orderCode) {
        return mapper.selectByOrderCode(orderCode);
    }

    /**
     * 查询订单号是否存在
     * 
     * @param orderCode
     *            订单编号
     * @return
     */
    @Override
    public int existOrderCode(String orderCode) {
        if (mapper != null && mapper.existOrderCode(orderCode) != 0) {
            return 1;
        }
        return 0;
    }

    /**
     * 修改订单信息
     * 
     * @param orderVice
     *            要修改的信息
     * @return
     */
    @Override
    public int updateOrderVice(OrderVice orderVice) {
        return mapper.updateByPrimaryKeySelective(orderVice);
    }

    /**
     * 根据id修改订单信息
     * 
     * @param orderId
     * @return
     */
    @Override
    public int updateOrderViceByOrderId(Long orderId) {
        OrderVice orderVice = mapper.selectByPrimaryKey(orderId);
        orderVice.setOrderStatus("3");
        return mapper.updateByPrimaryKeySelective(orderVice);
    }

}
