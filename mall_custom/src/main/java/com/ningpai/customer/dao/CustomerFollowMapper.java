/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.customer.bean.CustomerFollow;
import com.ningpai.customer.bean.ProductCommentUtilBean;

/**
 * 会员关注商品Mappper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年4月1日 下午5:58:08
 * @version 0.0.1
 */
public interface CustomerFollowMapper {
    /**
     * 查询按条件查询消费记录
     * 
     * @param paramMap
     *            查询条件
     * @return List {@link java.util.List}
     */
    List<CustomerFollow> selectCustFollowByCustId(long customerId);
    /**
     * 查询按条件查询消费记录Count
     *
     * @param paramMap
     *            查询条件
     * @return List {@link java.util.List}
     */
    int selectCustFollowByCustIdCount(long customerId);

    /**
     * 根据货品的id查询关注的会员id
     * 
     * @param goodId
     * @return
     */
    List<CustomerFollow> selectSendId(Long goodsInfoId);


    /**
     * 根据货品ID获取 该货品的评分
     * @param productId
     * @return
     */
    ProductCommentUtilBean queryCommentCountAndScoreByProductId(Long productId);
    int deleteFolloByGoodsIdUId(Map<String,Object> param);
}
