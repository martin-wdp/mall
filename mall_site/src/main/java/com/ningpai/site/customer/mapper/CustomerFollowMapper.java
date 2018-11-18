/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.mapper;

import com.ningpai.site.customer.bean.CustomerFollow;
import com.ningpai.site.goods.util.ProductCommentUtilBean;

import java.util.List;
import java.util.Map;

/**
 * 会员收藏Mapper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年1月13日 下午1:07:58
 * @version 0.0.1
 */
public interface CustomerFollowMapper {
    /**
     * 根据货品ID获取 该货品的评分
     * @param productId
     * @return
     */
    ProductCommentUtilBean queryCommentCountAndScoreByProductId(Long productId);
    /**
     * 根据主键删除
     * 
     * @param map
     *            收藏编号
     * @return 0失败 1成功
     */
    int deleteByPrimaryKey(Map<String,Object> map);

    /**
     * 插入数据 要插入的数据的对象
     * 
     * @param record
     * @return 0失败 1成功
     */
    int insert(CustomerFollow record);

    /**
     * 按条件插入数据
     * 
     * @param record
     *            要插入的数据的对象
     * @return 0失败 1成功
     */
    int insertSelective(CustomerFollow record);

    /**
     * 按照主键编号查找
     * 
     * @param followId
     *            收藏编号
     * @return Customer {@link com.ningpai.customer.bean.Customer}
     */
    CustomerFollow selectByPrimaryKey(Long followId);

    /**
     * 根据会员id和货品id查询是否已经关注过该商品
     * @param map
     * @return
     */
    CustomerFollow queryByCustIdAndProId(Map<String, Object> map);

    /**
     * 按条件修改信息
     * 
     * @param record
     *            要修改的对象
     * @return 0失败 1成功
     */
    int updateByPrimaryKeySelective(CustomerFollow record);

    /**
     * 根据主键编号修改信息
     * 
     * @param record
     *            要修改的对象
     * @return 0失败 1成功
     */
    int updateByPrimaryKey(CustomerFollow record);

    /**
     * 查询按条件查询消费记录
     * 
     * @param paramMap
     *            查询条件
     * @return List {@link java.util.List}
     */
    List<Object> selectCustFollowByCustId(Map<String, Object> paramMap);

    /**
     * 商品列表专用 查询当前会员是否
     * @param paramMap
     * @return
     */
    List<String> selectCustFollowByCustIdForList(Map<String, Object> paramMap);

    /**
     * 查询收藏总数
     * 
     * @param customerId
     *            会员编号
     * @return
     */
    Long selectCustomerFollowCount(Long customerId);
}
