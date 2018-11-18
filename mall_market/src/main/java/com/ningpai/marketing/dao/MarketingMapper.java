/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.marketing.bean.Marketing;

/**
 * @author ggn 2014-03-24 促销信息接口
 */
public interface MarketingMapper {
    /**
     *
     * @param marketingId
     * @return
     */
    int delleteMarkting(Long marketingId,Long businessId);

    /**
     * 查询促销信息列表
     * 
     * @param paramMap
     * @{link java.util.Map}
     * @return MarketingMapper
     */
    List<Object> marketOrderList(Map<String, Object> paramMap);

    /**
     * 查询促销总数
     * 
     * @param paramMap
     * @{link java.util.Map}
     * @return int
     */
    int marketOrderListCount(Map<String, Object> paramMap);

    /**
     * 查询促销详细信息
     * 
     * @param marketingId
     * @{link java.lang.Long}
     * @return Marketing
     */
    Marketing marketingDetail(Long marketingId);

    /**
     * 查询促销详细信息（不根据时间）
     * 
     * @param marketingId
     * @return
     */
    Marketing marketingDetailNotTime(Long marketingId);

    /**
     * 根据日期查询促销信息
     * 
     * @param marketingId
     * @return
     */
    Marketing marketingDetailForTime(Long marketingId);

    /**
     * 创建营销
     * 
     * @param marketing
     * @{link java.lang.Long}
     * @return int
     */
    int insertMarketing(Marketing marketing);

    /**
     * 修改促销
     * 
     * @param marketing
     * @return
     */
    int modifyMarketing(Marketing marketing);

    /**
     * 查询营销ID
     * 
     * @return Long
     */
    Long selectLastId();

    /**
     * 修改营销
     * 
     * @param marketing
     *            {@link com.ningpai.marketing.bean.Marketing}
     * @return int
     */
    int updateMarketing(Marketing marketing);

    /**
     * 删除营销
     *
     * @param
     * @{link java.lang.Long}
     * @return int
     */
    int deleteMarketingById(Map<String, Object> paramMap);

    /**
     * 批量删除促销
     *
     * @param
     * @{link java.util.List}
     * @return int
     */
    int delAllMarketingByIds(Map<String, Object> paramMap);

    /**
     * 查询即将过期的促销
     * 
     * @param paramMap
     *            查询参数
     * @return int
     */
    int sellerMarketingOverdueCount(Map<String, Object> paramMap);

    /**
     * 查询某个商品下的订单促销
     * 
     * @return 查询到的促销
     * @author NINGPAI-LIH
     */
    List<Marketing> queryOrderMarketingByGoodsId(Map<String, Object> map);

    /**
     * 根据商品id和促销类型查询促销信息
     *
     * @param
     * @return
     */
    Marketing queryMarketingByGoodIdAndtype(Map<String, Object> map);


    /**
     * 根据商品id和促销类型查询促销信息的集合
     * @param map
     * @return
     */
    List<Marketing> queryMarketingByGoodIdAndtypeList(Map<String, Object> map);

    /**
     * 根据商品和活动分组id查询优惠id
     * 
     * @return 优惠id
     * @author NINGPAI-LIH
     */
    Marketing queryByCreatimeMarketing(Map<String, Object> map);

    /**
     * 根据商品和活动分组id查询优惠id
     * 
     * @return 优惠id
     * @author NINGPAI-LIH
     */
    List<Marketing> queryByCreatimemarketings(Map<String, Object> map);

    /**
     * 查询团购所用的促销信息
     * 
     * @return 优惠id
     * @author NINGPAI-LIH
     */
    List<Marketing> queryByCreatimeMarketingGroupon(Map<String, Object> map);

    /**
     * 查询抢购所用的促销信息
     * 
     * @return 优惠id
     */
    List<Marketing> queryByCreatimeMarketingRush(Map<String, Object> map);

    /**
     * 根据marketingId查询促销信息
     * 
     * @param marketingId
     * @return
     */
    Marketing searchMarketByMarketingId(Long marketingId);

    /**
     * 查询促销信息列表
     * 
     * @param paramMap
     * @{link java.util.Map}
     * @return MarketingMapper
     */
    List<Object> marketingList(Map<String, Object> paramMap);

    /**
     * 查询促销总数
     * 
     * @param paramMap
     * @{link java.util.Map}
     * @return int
     */
    int marketingListCount(Map<String, Object> paramMap);
}
