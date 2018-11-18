/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.comment.bean.Comment;
import com.ningpai.m.customer.mapper.CustomerOrderMapper;
import com.ningpai.m.customer.vo.GoodsBean;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * @see com.ningpai.m.customer.mapper.CustomerOrderMapper
 * @author jiping
 * @since 2014年8月20日 下午1:43:56
 * @version 0.0.1
 */
@Repository("customerOrderMapperM")
public class CustomerOrderMapperImpl extends BasicSqlSupport implements CustomerOrderMapper {
    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerOrderMapper#queryAllMyOrders(java.util.Map)
     */
    @Override
    public List<Object> queryAllMyOrders(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.m.customer.mapper.CustomerMapper.selectOrderByCustomerIdAndStatus", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerOrderMapper#searchTotalCount(java.util.Map)
     */
    @Override
    public Long searchTotalCount(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.m.customer.mapper.CustomerMapper.searchTotalCount", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerOrderMapper#searchTotalCountO(java.util.Map)
     */
    @Override
    public Long searchTotalCountO(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.m.customer.mapper.CustomerMapper.searchTotalCountO", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerOrderMapper#queryOrderByParamMap(java.util.Map)
     */
    @Override
    public Object queryOrderByParamMap(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.m.customer.mapper.CustomerMapper.queryOrderByCustIdAndOid", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerOrderMapper#selectpendingOrderNotice(java.util.Map)
     */
    @Override
    public Long selectpendingOrderNotice(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.m.customer.mapper.CustomerMapper.selectpendingOrderNotice", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerOrderMapper#cancelOrder(java.util.Map)
     */
    @Override
    public int cancelOrder(Map<String, Object> paramMap) {
        return this.update("com.ningpai.m.customer.mapper.CustomerMapper.cancelOrder", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerOrderMapper#comfirmofGoods(java.lang.Long)
     */
    @Override
    public int comfirmofGoods(Long orderId) {
        return this.update("com.ningpai.m.customer.mapper.CustomerMapper.comfirmOfGooods", orderId);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerOrderMapper#addGoodsComment(com.ningpai.comment.bean.Comment)
     */
    @Override
    public int addGoodsComment(Comment comment) {
        return this.insert("com.ningpai.comment.dao.CommentMapper.addGoodsComment", comment);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerOrderMapper#updateOrderGoods(com.ningpai.m.customer.vo.GoodsBean)
     */
    @Override
    public int updateOrderGoods(GoodsBean goodsBean) {
        return this.update("com.ningpai.m.customer.mapper.GoodsCommentMapper.updateOrderGoods", goodsBean);
    }

}
